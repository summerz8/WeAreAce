/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hangsun
 */
@Stateful
public class RetailProductPurchasePlanModule implements RetailProductPurchasePlanModuleLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public boolean generateRetailProductPurchasePlan(Long factoryRetailproductId,Calendar targetPeriod,Double amount){
        
        try{
            IntegratedPlannedOrderEntity integratedPlannedOrder = new IntegratedPlannedOrderEntity();
            Calendar generateDate = Calendar.getInstance();
            integratedPlannedOrder.setGeneratedDate(generateDate);
            integratedPlannedOrder.setTargetPeriod(targetPeriod);
            integratedPlannedOrder.setStatus("unconfirmed");
            FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class,factoryRetailproductId);
            String unit = factoryRetailProduct.getUnit();
            FactoryRetailProductAmountEntity factoryRetailProductAmount = new FactoryRetailProductAmountEntity();
            factoryRetailProductAmount.setUnit(unit);
            factoryRetailProductAmount.setAmount(amount);
            factoryRetailProductAmount.setFactoryRetailProduct(factoryRetailProduct);
            em.persist(factoryRetailProductAmount);
            integratedPlannedOrder.setFactoryRetailProductAmount(factoryRetailProductAmount);
            em.persist(integratedPlannedOrder);
            System.out.println("Generate Retail Product Purchase Plan!");
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
        }
        return true;
    }
    
    @Override
    public void editRetailProductPurchasePlan(Long id, String field,Object content){
        
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
    }
    
    @Override
    public boolean deleteRetailProductPurchasePlan(Long id){
        IntegratedPlannedOrderEntity integratedPlannedOrder = em.find(IntegratedPlannedOrderEntity.class, id);

        String status = integratedPlannedOrder.getStatus();
        
        if(status.equals("confirmed") || status.equals("accomplished")){
            return false;
        }
        
        else{
            em.remove(integratedPlannedOrder);
            return true;
        }
        
    }
    
     @Override
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlan(){
        Query q = em.createQuery("SELECT rppp FROM IntegratedPlannedOrderEntity rppp");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList();
        for(Object o : q.getResultList()){
            IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
            if(rppp.getFactoryRetailProductAmount()!= null)
                integratedPlannedOrderList.add(rppp);
            }
          return integratedPlannedOrderList;
        }
}
