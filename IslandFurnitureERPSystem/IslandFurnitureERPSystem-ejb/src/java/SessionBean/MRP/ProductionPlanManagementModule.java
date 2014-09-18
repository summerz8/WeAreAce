/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.FactoryProductEntity;
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
            
            case "productId":
                Long productId = (Long) content;
                FactoryProductEntity product = em.find(FactoryProductEntity.class,productId);
                productionPlan.setProduct(product);
                break;
            case "output":
                Double output = (Double) content;
                productionPlan.setQuantity(output);
                break;
            case "status":
                String status = (String) content;
                productionPlan.setStatus(status);
                break;
            case "remark":
                String remark = (String) content;
                productionPlan.setRemark(remark);
                break;
        }
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
    public List<ProductionPlanEntity> getProductionPlan(){
        Query q = em.createQuery("SELECT pp FROM ProductionPlanEntity pp");
        List<ProductionPlanEntity> productionPlanList = new ArrayList();
        for(Object o : q.getResultList()){
            ProductionPlanEntity pp = (ProductionPlanEntity) o;
            productionPlanList.add(pp);
            
        }
        
        
        return productionPlanList;
    }
    
}
