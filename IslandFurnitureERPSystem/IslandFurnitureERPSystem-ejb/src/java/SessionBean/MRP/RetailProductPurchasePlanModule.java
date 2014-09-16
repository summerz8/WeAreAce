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
public class RetailProductPurchasePlanModule implements RetailProductPurchasePlanModuleLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public boolean generateRetailProductPurchasePlan(String status,Calendar generateDate,Calendar targetPeriod,Double output,Long productId, String remark){
        
        try{
            FactoryProductEntity product = em.find(FactoryProductEntity.class,productId);
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
    public void editRetailProductPurchasePlan(Long productionPlanId, String field,Object content){
        
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
    public boolean deleteRetailProductPurchasePlan(Long productionPlanId){
        
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
    public List<ArrayList> getProductionPlan(){
        Query q = em.createQuery("SELECT pp FROM ProductionPlan pp");
        List productionPlanList = new ArrayList();
        for(Object o : q.getResultList()){
            ProductionPlanEntity pp = (ProductionPlanEntity) o;
            List productionPlan = new ArrayList();
            productionPlan.add(0,pp.getProductionPlanId());
            productionPlan.add(1,pp.getStatus());
            productionPlan.add(2,pp.getGenerateDate());
            productionPlan.add(3,pp.getTargetPeriod());
            productionPlan.add(4,pp.getProduct().getFactoryProductId());
            productionPlan.add(5,pp.getQuantity());
            productionPlan.add(6,pp.getConfirmDate());
            productionPlan.add(7,pp.getRemark());
            productionPlanList.add(productionPlan);
        }
        
        
        return productionPlanList;
    }
}
