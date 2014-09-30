/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.MRP.SalesOperationPlanEntity;
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
public class ProductionPlanManagementModule implements ProductionPlanManagementModuleLocal{
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public boolean generateProductionPlan(String status,Calendar generateDate,Calendar targetPeriod,Double output,Long productId, String remark){
        
        try{
            FactoryProductEntity product=em.find(FactoryProductEntity.class, productId);
            ProductionPlanEntity productionPlan = new ProductionPlanEntity(status,generateDate,targetPeriod,output,product,remark);
            em.persist(productionPlan);
            System.out.println("Generate production plan!");
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
        }
        return true;
    }
    
    @Override
    public void editProductionPlan(Long productionPlanId, String field,Object content){
        ProductionPlanEntity productionPlan = em.find(ProductionPlanEntity.class, productionPlanId);
        switch (field) {
            case "targetPeriod":
                Calendar targetPeriod = (Calendar) content;
                productionPlan.setTargetPeriod(targetPeriod);
                break;
            
            case "confirmDate":
                Calendar confirmDate = (Calendar) content;
                productionPlan.setConfirmDate(confirmDate);
                break;
            
            case "productId":
                Long productId = (Long) content;
                FactoryProductEntity product = em.find(FactoryProductEntity.class,productId);
                productionPlan.setFactoryProduct(product);
                break;
            case "quantity":
                Double quantity = (Double) content;
                productionPlan.setQuantity(quantity);
                break;
            case "status":
                String status = (String) content;
                productionPlan.setStatus(status);
                if(status.equals("cancelled")){
                Long id=productionPlan.getProductionPlanId();
                Query q = em.createQuery("SELECT pp FROM SalesOperationPlanEntity pp");
                for(Object o : q.getResultList()){
                SalesOperationPlanEntity pp = (SalesOperationPlanEntity) o;
                if(pp.getStatus().equals("confirmed")&&pp.getProductionPlan().getProductionPlanId().equals(id))
                    pp.setStatus("cancelled");
                }                 
                }
                break;
            case "remark":
                String remark = (String) content;
                productionPlan.setRemark(remark);
                break;
        }
        em.persist(productionPlan);
        em.flush();
        em.refresh(productionPlan);
    }
    
    @Override
    public boolean deleteProductionPlan(Long productionPlanId){
        
        ProductionPlanEntity productionPlan = em.find(ProductionPlanEntity.class, productionPlanId);
        String status = productionPlan.getStatus();
        
        if(status.equals("confirmed") || status.equals("accomplished")){
            return false;
        }
        
        else{
            em.remove(productionPlan);
            return true;
        }
        
    }
    
    @Override
    public List<ProductionPlanEntity> getProductionPlanUnconfirmed(Long id,String department){
            Query q = em.createQuery("SELECT pp FROM ProductionPlanEntity pp");
            List<ProductionPlanEntity> productionPlanList = new ArrayList();
            if(department.equals("H")){
                for(Object o : q.getResultList()){
                ProductionPlanEntity pp = (ProductionPlanEntity) o;
                if(pp.getStatus().equals("unconfirmed"))
                    productionPlanList.add(pp);
                }
            }
            
            else{
                for(Object o : q.getResultList()){
                ProductionPlanEntity pp = (ProductionPlanEntity) o;
                Long departmentId = pp.getFactoryProduct().getFactory().getFactoryId();
                if(pp.getStatus().equals("unconfirmed") && departmentId.equals(id))
                    productionPlanList.add(pp);
            
                }//for
            }//else
        
        return productionPlanList;
    }
    
    @Override
    public List<ProductionPlanEntity> getProductionPlanConfirmed(Long id,String department){
       Query q = em.createQuery("SELECT pp FROM ProductionPlanEntity pp");
            List<ProductionPlanEntity> productionPlanList = new ArrayList();
            if(department.equals("H")){
                for(Object o : q.getResultList()){
                ProductionPlanEntity pp = (ProductionPlanEntity) o;
                if(pp.getStatus().equals("confirmed"))
                    productionPlanList.add(pp);
                }
            }
            
            else{
                for(Object o : q.getResultList()){
                ProductionPlanEntity pp = (ProductionPlanEntity) o;
                Long departmentId = pp.getFactoryProduct().getFactory().getFactoryId();
                if(pp.getStatus().equals("confirmed") && departmentId.equals(id))
                    productionPlanList.add(pp);
            
                }//for
            }//else
        
        return productionPlanList;
    }
    
    @Override
    public List<ProductionPlanEntity> getProductionPlanCancelled(Long id,String department){
       Query q = em.createQuery("SELECT pp FROM ProductionPlanEntity pp");
            List<ProductionPlanEntity> productionPlanList = new ArrayList();
            if(department.equals("H")){
                for(Object o : q.getResultList()){
                ProductionPlanEntity pp = (ProductionPlanEntity) o;
                if(pp.getStatus().equals("cancelled"))
                    productionPlanList.add(pp);
                }
            }
            
            else{
                for(Object o : q.getResultList()){
                ProductionPlanEntity pp = (ProductionPlanEntity) o;
                Long departmentId = pp.getFactoryProduct().getFactory().getFactoryId();
                if(pp.getStatus().equals("cancelled") && departmentId.equals(id))
                    productionPlanList.add(pp);
            
                }//for
            }//else
        
        return productionPlanList;
    } 
   
    @Override
    public ProductionPlanEntity searchProductionPlan(Long id){
        ProductionPlanEntity productionPlan = em.find(ProductionPlanEntity.class,id);
        return productionPlan;
    }
    
}
