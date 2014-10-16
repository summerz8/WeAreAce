/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
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
            factoryRetailProductAmount.setFactoryRetailProduct(factoryRetailProduct);
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
            factory.getIntegratedPlannedOrders().add(integratedPlannedOrder);
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
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlan(Long id,String department){
        Query q = em.createQuery("SELECT rppp FROM IntegratedPlannedOrderEntity rppp");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList();
        if(department.equals("H")){          
            for(Object o : q.getResultList()){
                IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
                if(rppp.getFactoryRetailProductAmount()!= null)
                    integratedPlannedOrderList.add(rppp);
            }       
        }
        else{
            for(Object o : q.getResultList()){
                IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
                Long departmentId =rppp.getFactory().getFactoryId();
                if(rppp.getFactoryRetailProductAmount()!= null && departmentId.equals(id))
                    integratedPlannedOrderList.add(rppp);       
            }
        }
        return integratedPlannedOrderList;
    }
        
    
    @Override
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanUnconfirmed(Long id,String department){
        Query q = em.createQuery("SELECT rppp FROM IntegratedPlannedOrderEntity rppp");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList();       
        if(department.equals("H")){
            for(Object o : q.getResultList()){
                IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
                if(rppp.getFactoryRetailProductAmount()!= null){
                    Long departmentId =rppp.getFactory().getFactoryId();
                    if(rppp.getStatus().equals("unconfirmed"))
                        integratedPlannedOrderList.add(rppp);
                }
            }
        }
        else{
            for(Object o : q.getResultList()){
                IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
                if(rppp.getFactoryRetailProductAmount()!= null){
                    Long departmentId =rppp.getFactory().getFactoryId();
                    if(rppp.getStatus().equals("unconfirmed") && departmentId.equals(id))
                        integratedPlannedOrderList.add(rppp);
                }
            }
        }
          return integratedPlannedOrderList;
    }
    
    @Override
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanConfirmed(Long id,String department){
        Query q = em.createQuery("SELECT rppp FROM IntegratedPlannedOrderEntity rppp");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList();       
        if(department.equals("H")){
            for(Object o : q.getResultList()){
                IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
                if(rppp.getFactoryRetailProductAmount()!= null){
                    if(rppp.getStatus().equals("waiting"))
                        integratedPlannedOrderList.add(rppp);
                }
            }
        }
        else{
            for(Object o : q.getResultList()){
                IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
                if(rppp.getFactoryRetailProductAmount()!= null){
                    Long departmentId =rppp.getFactory().getFactoryId();
                    if(rppp.getStatus().equals("waiting") && departmentId.equals(id))
                        integratedPlannedOrderList.add(rppp);
                }
            }
        }
          return integratedPlannedOrderList;
        }
    
    @Override
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanCancelled(Long id,String department){
        Query q = em.createQuery("SELECT rppp FROM IntegratedPlannedOrderEntity rppp");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList();       
        if(department.equals("H")){
            for(Object o : q.getResultList()){
                IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
                if(rppp.getFactoryRetailProductAmount()!= null){
                    if(rppp.getStatus().equals("cancelled"))
                        integratedPlannedOrderList.add(rppp);
                }
            }
        }
        else{
            for(Object o : q.getResultList()){
                IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
                if(rppp.getFactoryRetailProductAmount()!= null){
                    Long departmentId =rppp.getFactory().getFactoryId();
                    if(rppp.getStatus().equals("cancelled") && departmentId.equals(id))
                        integratedPlannedOrderList.add(rppp);
                }
            }
        }
          return integratedPlannedOrderList;
        }
    
    @Override
    public Long getFactoryRetailProductId(Long integratedSalesForecastId){
        IntegratedSalesForecastEntity integratedSalesForecast = em.find(IntegratedSalesForecastEntity.class, integratedSalesForecastId);
        
        System.out.println("integratedSalesForecast " + integratedSalesForecastId);
        
        Long factoryRetailProductId = integratedSalesForecast.getFactoryRetailProduct().getFactoryRetailProdctId();
        System.out.println("factoryRetailProductId " + factoryRetailProductId);
        return factoryRetailProductId;
    }
    
    @Override
    public boolean findIntegratedSalesForecast(Long integratedSalesForecastId){   
        boolean flag = Boolean.FALSE;
        
        IntegratedSalesForecastEntity isf = em.find(IntegratedSalesForecastEntity.class,integratedSalesForecastId);
        Long factoryRetailProductId =isf.getFactoryRetailProduct().getFactoryRetailProdctId();
        Calendar targetPeriod = isf.getTargetPeriod();
        
        Query q =em.createQuery("SELECT rppp FROM IntegratedPlannedOrderEntity rppp");
            for(Object o : q.getResultList()){
                IntegratedPlannedOrderEntity rppp = (IntegratedPlannedOrderEntity) o;
                if(rppp.getFactoryRetailProductAmount()!=null){
                        Long retailProductId = rppp.getFactoryRetailProductAmount().getFactoryRetailProduct().getFactoryRetailProdctId();
                        Calendar TargetPeriod = rppp.getTargetPeriod();
                        if(retailProductId.equals(factoryRetailProductId) && TargetPeriod.equals(targetPeriod)){
                            flag = Boolean.TRUE;
                            return flag;
                        }
                            
                    }
            }
                    
            
        return flag;   
    }
}
