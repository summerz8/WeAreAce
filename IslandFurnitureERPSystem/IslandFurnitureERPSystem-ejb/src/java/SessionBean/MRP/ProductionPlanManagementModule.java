/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.ProductEntity;
import java.util.Date;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public void generateProductionPlanManagementModule(String status,Date generateDate,Date targetSalesStartDate,Date targetSalesEndDate,Integer output,Long productId, String remark){
        
        try{
            ProductEntity product = em.find(ProductEntity.class,productId);
            ProductionPlanEntity productionPlan = new ProductionPlanEntity(status,generateDate,targetSalesStartDate,targetSalesEndDate,output,product,remark);
            em.persist(productionPlan);
            System.out.println("Generate production plan!");
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void editProductionPlan(Long productionPlanId, String field,Object content){
        
        ProductionPlanEntity productionPlan = em.find(ProductionPlanEntity.class, productionPlanId);
        
        switch (field) {
            case "targetSalesStartDate":
                Date startDate = (Date) content;
                productionPlan.setTargetSalesStartDate(startDate);
                break;
            case "targetSalesEndDate":
                Date endDate = (Date) content;
                productionPlan.setTargetSalesEndDate(endDate);
                break;
            case "productId":
                Long productId = (Long) content;
                ProductEntity product = em.find(ProductEntity.class,productId);
                productionPlan.setProduct(product);
                break;
            case "output":
                Integer output = (Integer) content;
                productionPlan.setOutput(output);
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
    public String deleteProductionPlan(Long productionPlanId){
        
        ProductionPlanEntity productionPlan = em.find(ProductionPlanEntity.class, productionPlanId);
        String status = productionPlan.getStatus();
        
        if(status.equals("confirmed") || status.equals("accomplished")){
            return "Production Plan cannot be deleted, it is already confirmed or accomplished.";
        }
        
        else{
            em.remove(productionPlan);
            return "Production Plan is successfully deleted!!";
        }
        
    }
    
}
