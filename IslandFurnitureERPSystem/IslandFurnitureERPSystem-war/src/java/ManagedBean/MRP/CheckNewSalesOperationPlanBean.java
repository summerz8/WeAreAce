/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.SalesOperationPlanEntity;
import SessionBean.MRP.SalesOperationPlanLocal;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author apple
 */
@Named(value = "checkNewSalesOperationPlanBean")
@ViewScoped
public class CheckNewSalesOperationPlanBean {

    /**
     * Creates a new instance of CheckNewSalesOperationPlanBean
     */
    @EJB
    private SalesOperationPlanLocal salesOperationPlanLocal;

    SalesOperationPlanEntity salesOperationPlan;
    Integer workingDays;
    Double plannedEndInventory;
    Double productionPlanQuantit;
    Long productId;
    Long integratedSalesForecastId;
    Calendar targetPeriod;
    Double salesForecastQuantity;

    public CheckNewSalesOperationPlanBean() {
    }

    @PostConstruct
    public void Check() {
        workingDays = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("WorkingDay");
        plannedEndInventory = (Double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("PlannedOrderInventory");
        productionPlanQuantit = (Double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ProductionPlanQuantity");
        productId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ProductId");
        integratedSalesForecastId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("IntegratedSalesForecastId");
        targetPeriod = (Calendar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("TargetPeriod");
        salesForecastQuantity = (Double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SalesForecast");

    }

    public String Confirm() {

        salesOperationPlan = salesOperationPlanLocal.GenerateSalesOperationPlan(productId, targetPeriod, integratedSalesForecastId, productionPlanQuantit, plannedEndInventory, workingDays);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesOperationpPlanId", salesOperationPlan.getId());
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("WorkingDay");        
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("PlannedOrderInventory");        
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("ProductionPlanQuantity");        
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("ProductId");        
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("IntegratedSalesForecastId"); 
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("TargetPeriod");        
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("SalesForecast");
 
        
        return "MRPNewSalesOperationPlanConfirmed";
    }

    public Integer getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Integer workingDays) {
        this.workingDays = workingDays;
    }

    public Double getPlannedEndInventory() {
        return plannedEndInventory;
    }

    public void setPlannedEndInventory(Double plannedEndInventory) {
        this.plannedEndInventory = plannedEndInventory;
    }

    public Double getProductionPlanQuantit() {
        return productionPlanQuantit;
    }

    public void setProductionPlanQuantit(Double productionPlanQuantit) {
        this.productionPlanQuantit = productionPlanQuantit;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getIntegratedSalesForecastId() {
        return integratedSalesForecastId;
    }

    public void setIntegratedSalesForecastId(Long integratedSalesForecastId) {
        this.integratedSalesForecastId = integratedSalesForecastId;
    }

    public Calendar getTargetPeriod() {
        return targetPeriod;
    }

    public void setTargetPeriod(Calendar targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    public Double getSalesForecastQuantity() {
        return salesForecastQuantity;
    }

    public void setSalesForecastQuantity(Double salesForecastQuantity) {
        this.salesForecastQuantity = salesForecastQuantity;
    }

}
