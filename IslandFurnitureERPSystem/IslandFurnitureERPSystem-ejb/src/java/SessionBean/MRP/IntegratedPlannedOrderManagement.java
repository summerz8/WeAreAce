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
        FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class,factoryRawMaterialId);
        
        IntegratedPlannedOrderEntity integratedPlannedOrder = new IntegratedPlannedOrderEntity();
        FactoryRawMaterialAmountEntity factoryRawMaterialAmount = new FactoryRawMaterialAmountEntity();
        
        factoryRawMaterialAmount.setUnit(factoryRawMaterial.getUnit());
        factoryRawMaterialAmount.setFactoryRawMaterial(factoryRawMaterial);
        em.persist(factoryRawMaterialAmount);
        
        Calendar generateDate = Calendar.getInstance();
        Calendar targetperiod = targetPeriod;
        String status = "waiting";
        Long factoryId = factoryRawMaterial.getFactory().getFactoryId();   
        FactoryEntity factory = em.find(FactoryEntity.class,factoryId);
        Double amount =0D;
        List<PlannedOrderEntity> po = new ArrayList();
        
        int targetPeriodMonth = targetperiod.get(Calendar.MONTH) +1 ;
        
        for (PlannedOrderEntity confirmedPO : plannedOrderConfirmed) {
            int confirmPOMonth = confirmedPO.getTargetPeriod().get(Calendar.MONTH) +1;
            
            if(targetPeriodMonth == confirmPOMonth){
               
               po.add(confirmedPO);
               
               List<FactoryRawMaterialAmountEntity> factoryRawMaterialAmountList = confirmedPO.getFactoryRawMaterialAmountList();
               
               for(FactoryRawMaterialAmountEntity fam : factoryRawMaterialAmountList){                   
                   
                   if(fam.getFactoryRawMaterial().getFactoryRawMaterialId().equals(factoryRawMaterialId)){
                       amount += fam.getAmount();
                   }//if rawMaterialId 
               }//for factoryRawMaterial
            }//if targetPeriodMonth
        }//for confirmedPO
        
        factoryRawMaterialAmount.setAmount(amount);
        em.persist(factoryRawMaterialAmount);
        
        integratedPlannedOrder.setFactoryRawMaterialAmount(factoryRawMaterialAmount);
        integratedPlannedOrder.setGeneratedDate(generateDate);
        integratedPlannedOrder.setStatus(status);
        integratedPlannedOrder.setTargetPeriod(targetperiod);
        integratedPlannedOrder.setPlannedOrderList(po);
        integratedPlannedOrder.setFactory(factory);
        
        em.persist(integratedPlannedOrder);
        
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
}
