/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.MRP;

import SessionBean.MRP.ProductionPlanManagementModuleLocal;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author apple
 */
@Named(value = "productionPlanBean")
@Dependent
public class ProductionPlanBean {
    @EJB 
    ProductionPlanManagementModuleLocal plan;
      
    /**
     * Creates a new instance of ProductionPlanBean
     */
    
    public ProductionPlanBean() {
    }
    
    //public generateProductionPlan(Action){
        
    
//    } 
            
//    public boolean generateProductionPlan(String status, 
//            Calendar generateDate,
//            Calendar targetPeriod, 
//            Integer output, 
//            Long productId,
//            String remark);
//
//    public void editProductionPlan(Long productionPlanId, String field, Object content);
//
//    public boolean deleteProductionPlan(Long productionPlanId);
//
//    public List<ArrayList> getProductionPlan();
}
