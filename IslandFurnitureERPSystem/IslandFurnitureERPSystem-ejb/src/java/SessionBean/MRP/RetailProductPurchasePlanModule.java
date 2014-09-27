/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.MRP.SalesForecastEntity;
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
    public void generateRetailProductPurchasePlan(Long integratedSalesForecastId,Long factoryRetailProductId){
        
        try{       
            IntegratedSalesForecastEntity integratedSalesForecast = em.find(IntegratedSalesForecastEntity.class, integratedSalesForecastId);
            FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class,factoryRetailProductId);
            FactoryEntity factory = factoryRetailProduct.getFactory();
            String unit = factoryRetailProduct.getUnit();
            Calendar generateDate = Calendar.getInstance();
            Calendar targetPeriod = integratedSalesForecast.getTargetPeriod();
            Double amount = integratedSalesForecast.getAmount();
            
            FactoryRetailProductAmountEntity factoryRetailProductAmount = new FactoryRetailProductAmountEntity();
            factoryRetailProductAmount.setAmount(amount);
            factoryRetailProductAmount.setUnit(unit);         
            em.persist(factoryRetailProductAmount);
            em.flush();
            
            IntegratedPlannedOrderEntity integratedPlannedOrder = new IntegratedPlannedOrderEntity();
            integratedPlannedOrder.setGeneratedDate(generateDate);
            integratedPlannedOrder.setTargetPeriod(targetPeriod);
            integratedPlannedOrder.setStatus("unconfirmed");
            integratedPlannedOrder.setFactoryRetailProductAmount(factoryRetailProductAmount);
            integratedPlannedOrder.setFactory(factory);
            em.persist(integratedPlannedOrder);
            em.flush();
            System.out.println("Generate Retail Product Purchase Plan!");
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             
        }
        
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
        em.persist(integratedPlannedOrder);
        em.flush();
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
    
    @Override
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanUnconfirmed(){
        Query q = em.createQuery("SELECT rppp FROM IntegratedPlannedOrderEntity rppp");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList();
        for(Object o : q.getResultList()){
            IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
            if(rppp.getFactoryRetailProductAmount()!= null)
                if(rppp.getStatus().equals("unconfirmed"))
                    integratedPlannedOrderList.add(rppp);
            }
          return integratedPlannedOrderList;
        }
    
    @Override
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanConfirmed(){
        Query q = em.createQuery("SELECT rppp FROM IntegratedPlannedOrderEntity rppp");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList();
        for(Object o : q.getResultList()){
            IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
            if(rppp.getFactoryRetailProductAmount()!= null)
                if(rppp.getStatus().equals("confirmed"))
                    integratedPlannedOrderList.add(rppp);
            }
          return integratedPlannedOrderList;
        }
    
    @Override
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanCancelled(){
        Query q = em.createQuery("SELECT rppp FROM IntegratedPlannedOrderEntity rppp");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList();
        for(Object o : q.getResultList()){
            IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
            if(rppp.getFactoryRetailProductAmount()!= null)
                if(rppp.getStatus().equals("cancelled"))
                    integratedPlannedOrderList.add(rppp);
            }
          return integratedPlannedOrderList;
        }
    
    @Override
    public Long getFactoryRetailProductId(Long integratedSalesForecastId){
        IntegratedSalesForecastEntity integratedSalesForecast = em.find(IntegratedSalesForecastEntity.class, integratedSalesForecastId);
        Long factoryRetailProductId = integratedSalesForecast.getFactoryRetailProduct().getFactoryRetailProdctId();
        return factoryRetailProductId;
    }
}
