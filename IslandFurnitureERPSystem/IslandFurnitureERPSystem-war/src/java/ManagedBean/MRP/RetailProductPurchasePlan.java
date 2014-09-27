/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import SessionBean.MRP.RetailProductPurchasePlanModuleLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hangsun
 */
@Named(value = "retailProductPurchasePlanView")
@ViewScoped
public class RetailProductPurchasePlan{

    /**
     * Creates a new instance of PlannedOrderList
     */
    
    private List<IntegratedPlannedOrderEntity> retailProductPurchasePlan;
    private List<IntegratedPlannedOrderEntity> retialProductPurchasePlanUnconfirmed;
    private List<IntegratedPlannedOrderEntity> retialProductPurchasePlanConfirmed;
    private List<IntegratedPlannedOrderEntity> retialProductPurchasePlanCancelled;
    private Long integratedSalesForecastId;
    
    @EJB
    private RetailProductPurchasePlanModuleLocal RPPP;
    
    @PostConstruct
    public void init(){
          retailProductPurchasePlan = RPPP.getRetailProductPurchasePlan();
          retialProductPurchasePlanUnconfirmed = RPPP.getRetailProductPurchasePlanUnconfirmed();
          retialProductPurchasePlanConfirmed = RPPP.getRetailProductPurchasePlanConfirmed();
          retialProductPurchasePlanCancelled = RPPP.getRetailProductPurchasePlanCancelled();
    }
    
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlan(){
        return retailProductPurchasePlan;
    } 

    public List<IntegratedPlannedOrderEntity> getRetialProductPurchasePlanUnconfirmed() {
        return retialProductPurchasePlanUnconfirmed;
    }

    public List<IntegratedPlannedOrderEntity> getRetialProductPurchasePlanConfirmed() {
        return retialProductPurchasePlanConfirmed;
    }

    public List<IntegratedPlannedOrderEntity> getRetialProductPurchasePlanCancelled() {
        return retialProductPurchasePlanCancelled;
    }

    public Long getIntegratedSalesForecastId() {
        return integratedSalesForecastId;
    }
    
    
    

    public String createRetailProductPurchasePlan(Long salesForecastId){
        Long factoryRetailProductId = RPPP.getFactoryRetailProductId(salesForecastId);        
        RPPP.generateRetailProductPurchasePlan(salesForecastId, factoryRetailProductId);
        return "/secured/restricted/Factory/MRP/RetailProductPurchasePlan/MRPRetailProductPurchasePlan?faces-redirect=true";
    }
    
    public String confirm(Long id){
        RPPP.editRetailProductPurchasePlan(id, "status", "confirmed");     
        return "/secured/restricted/Factory/MRP/RetailProductPurchasePlan/MRPRetailProductPurchasePlanUnconfirmed?faces-redirect=true";
    }
    
    public String cancel(Long id){
        RPPP.editRetailProductPurchasePlan(id, "status", "cancelled"); 
        return "/secured/restricted/Factory/MRP/RetailProductPurchasePlan/MRPRetailProductPurchasePlanUnconfirmed?faces-redirect=true";
    }
    
}
