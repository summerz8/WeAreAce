/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.SalesOperationPlanEntity;
import SessionBean.MRP.SalesOperationPlanLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
/**
 *
 * @author apple
 */
@Named(value = "viewNewSalesOperationPlanBean")
@RequestScoped
public class ViewNewSalesOperationPlanBean implements Serializable {
//
    @EJB
    private SalesOperationPlanLocal salesOperationPlanLocal;
    
    private SalesOperationPlanEntity salesOperationPlan;
    
    
    public ViewNewSalesOperationPlanBean() {
    }
    
    
    @PostConstruct
    public void viewNewSalesOperationPlan() {
        System.out.println("44444444");
        Long salesOperationPlanId=(Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("salesOperationPlanId");
        System.out.println("5555555");
        salesOperationPlan=salesOperationPlanLocal.getSalesOperationPlan(salesOperationPlanId);
//        
//        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("salesOperationpPlanId");
    }

    public SalesOperationPlanEntity getSalesOperationPlan() {
        return salesOperationPlan;
    }

    public void setSalesOperationPlan(SalesOperationPlanEntity salesOperationPlan) {
        this.salesOperationPlan = salesOperationPlan;
    }

    public String Back(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("salesOperationPlanId");
        return "MRPSalesOperationPlan?faces-redirect=true";
    }
}
