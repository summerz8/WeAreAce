/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hangsun
 */
@Stateless
public class IntegratedPlannedOrderManagement implements IntegratedPlannedOrderManagementLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;
    
    
    @Override
    public void createIntegratedPlannedOrder(Calendar targetPeriod,Long factoryRawMaterialId){
        List<PlannedOrderEntity> plannedOrderConfirmed = getConfirmedPlannedOrder();
        System.out.println("factoryRawMaterialId"+factoryRawMaterialId+ " " + targetPeriod.toString());
        FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class,factoryRawMaterialId);
        
        IntegratedPlannedOrderEntity integratedPlannedOrder = new IntegratedPlannedOrderEntity();
        FactoryRawMaterialAmountEntity factoryRawMaterialAmount = new FactoryRawMaterialAmountEntity();
        
        factoryRawMaterialAmount.setUnit(factoryRawMaterial.getUnit());
        factoryRawMaterialAmount.setFactoryRawMaterial(factoryRawMaterial);
        em.persist(factoryRawMaterialAmount);
        em.flush();
        
        Calendar generateDate = Calendar.getInstance();
        Calendar targetperiod = targetPeriod;
        String status = "waiting"; 
        FactoryEntity factory = factoryRawMaterial.getFactory();
        List<PlannedOrderEntity> po = new ArrayList();
        Double amount = 0D;
        int targetMonth = targetPeriod.get(Calendar.MONTH)+1;
        int targetYear = targetPeriod.get(Calendar.YEAR);
        
        for (PlannedOrderEntity confirmedPO : plannedOrderConfirmed) {
            Calendar confirmedPOTargetPeriod = confirmedPO.getTargetPeriod();
            int confirmedTargetPeriodMonth = confirmedPOTargetPeriod.get(Calendar.MONTH)+1;
            int confirmedTargetPeriodYear = confirmedPOTargetPeriod.get(Calendar.YEAR);
            
            System.out.println("xxx"+targetMonth+"yyy"+ confirmedTargetPeriodMonth);
            
            if(targetMonth == confirmedTargetPeriodMonth && targetYear == confirmedTargetPeriodYear){
               
               po.add(confirmedPO);
               
               List<FactoryRawMaterialAmountEntity> factoryRawMaterialAmountList = confirmedPO.getFactoryRawMaterialAmountList();
               
               for(FactoryRawMaterialAmountEntity fam : factoryRawMaterialAmountList){                   
                   System.out.println("!@#!@$@!"+ fam.getAmount());
                   System.out.println("SHSHSHSHS" + factoryRawMaterialId);
                   System.out.println("!@#!$!@$" + fam.getFactoryRawMaterial().getFactoryRawMaterialId());
                   if(fam.getFactoryRawMaterial().getFactoryRawMaterialId().equals(factoryRawMaterialId)){
                       System.out.println(fam.getAmount()+ "QWEQEWEQEEWEQWEW");
                       amount += fam.getAmount();
                       System.out.println("Amount to be stored: " + amount);
                   }//if rawMaterialId 
               }//for factoryRawMaterial
            }//if targetPeriodMonth           
        }//for confirmedPO
        
        factoryRawMaterialAmount.setAmount(amount);
        em.persist(factoryRawMaterialAmount);
        em.flush();
        
        integratedPlannedOrder.setFactoryRawMaterialAmount(factoryRawMaterialAmount);
        integratedPlannedOrder.setGeneratedDate(generateDate);
        integratedPlannedOrder.setStatus(status);
        integratedPlannedOrder.setTargetPeriod(targetperiod);
        integratedPlannedOrder.setPlannedOrderList(po);
        integratedPlannedOrder.setFactory(factory);
        
        em.persist(integratedPlannedOrder);
        
        factory.getIntegratedPlannedOrders().add(integratedPlannedOrder);
        em.flush();
        
    }
    
    
    @Override
    public List<PlannedOrderEntity> getConfirmedPlannedOrder(){
        Query q = em.createQuery("SELECT po FROM PlannedOrderEntity po");
        List<PlannedOrderEntity> plannedOrderList = new ArrayList();
        for(Object o : q.getResultList()){
            PlannedOrderEntity po = (PlannedOrderEntity) o;
            if(po.getStatus().equals("confirmed"))
                plannedOrderList.add(po);
            }
          return plannedOrderList;
        }
    
    @Override
    public List<IntegratedPlannedOrderEntity> getIntegratedPlannedOrder(){
        Query q = em.createQuery("SELECT ipo FROM IntegratedPlannedOrderEntity ipo");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList();
        for(Object o : q.getResultList()){
            IntegratedPlannedOrderEntity ipo = (IntegratedPlannedOrderEntity) o;
            if(ipo.getFactoryRawMaterialAmount()!=null)
                integratedPlannedOrderList.add(ipo);
            }
          return integratedPlannedOrderList;
        }
    
    @Override
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlan(){
        Query q = em.createQuery("SELECT ipo FROM IntegratedPlannedOrderEntity ipo");
        List<IntegratedPlannedOrderEntity> retailProductPurchasePlanList = new ArrayList();
        for(Object o : q.getResultList()){
            IntegratedPlannedOrderEntity ipo = (IntegratedPlannedOrderEntity) o;
            if(ipo.getFactoryRetailProductAmount()!=null)
                retailProductPurchasePlanList.add(ipo);
            }
          return retailProductPurchasePlanList;
    }    
    
    @Override
    public void editIntegratedPlannedOrder(Long id, String field,Object content){
        
        IntegratedPlannedOrderEntity integratedPlannedOrder = em.find(IntegratedPlannedOrderEntity.class, id);
        
        switch (field) {
            case "targetPeriod":
                Calendar targetPeriod = (Calendar) content;
                integratedPlannedOrder.setTargetPeriod(targetPeriod);
                break;
            case "amount":
                Double amount = (Double) content;
                integratedPlannedOrder.getFactoryRetailProductAmount().setAmount(amount);
                break;
            case "status":
                String status = (String) content;
                integratedPlannedOrder.setStatus(status);
                break;
        }
        em.persist(integratedPlannedOrder);
        em.flush();
    }
}
