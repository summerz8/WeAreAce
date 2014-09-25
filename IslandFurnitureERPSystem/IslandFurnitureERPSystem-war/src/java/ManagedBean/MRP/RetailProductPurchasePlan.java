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
    
    @EJB
    private RetailProductPurchasePlanModuleLocal RPPP;
    
    @PostConstruct
    public void init(){
          retailProductPurchasePlan = RPPP.getRetailProductPurchasePlan();
    }
    
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlan(){
        return retailProductPurchasePlan;
    } 

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   
    
}
