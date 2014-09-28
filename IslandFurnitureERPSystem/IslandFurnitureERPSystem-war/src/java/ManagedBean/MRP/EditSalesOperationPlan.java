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
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "editSalesOperationPlan")
@ViewScoped
public class EditSalesOperationPlan {

    @EJB
    SalesOperationPlanLocal sopl;

    Long saleOperationPlanId;
    SalesOperationPlanEntity salesOperationPlan;
    Double plannedEndInventory;
    Double productionPlanQuantity;
    Integer workingDays;
    Double salesForecastQuantity;
    Calendar targetPeriod;

    public EditSalesOperationPlan() {
    }

    @PostConstruct
    public void Init() {
        saleOperationPlanId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("salesOperationPlanId");
        salesOperationPlan = sopl.getSalesOperationPlan(saleOperationPlanId);
        plannedEndInventory = salesOperationPlan.getPlannedEndMonthInventory();
        productionPlanQuantity = salesOperationPlan.getPlannedProductionPlanQuantity();
        workingDays = salesOperationPlan.getWorkingDay();
        salesForecastQuantity = salesOperationPlan.getIntegratedSalesForecast().getAmount();
        targetPeriod = salesOperationPlan.getTargetPeriod();

    }

    public void changeWorkingDays(ValueChangeEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        workingDays = (Integer) newValue;
    }

    public void changePlannedEndInventory(ValueChangeEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        plannedEndInventory = (Double) newValue;
        productionPlanQuantity = plannedEndInventory - (Double) oldValue + salesForecastQuantity;
        System.out.println(plannedEndInventory);
    }

    public void changeProductionPlanQuantity(ValueChangeEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        productionPlanQuantity = (Double) newValue;
        System.out.println(productionPlanQuantity);
    }

    public String Edit() {
        salesOperationPlan = sopl.EditSalesOperationPlanEntity(saleOperationPlanId, productionPlanQuantity, targetPeriod, plannedEndInventory, workingDays);

        return "MRPNewSalesOperationPlanConfirmed?faces-redirect=true";
    }

    public String Confirm() {
        int userLevel = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Userlvl");
        if (userLevel <= 1) {
            boolean bo = sopl.IsThereSalesOperation(salesOperationPlan.getFactoryProduct().getFactoryProductId());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("salesOperationPlanId");
            if (bo == false) {
                System.out.println("false");
                salesOperationPlan = sopl.confirmSalesOperationPlan(saleOperationPlanId);
                return "MRPViewSalesOperationPlan?faces-redirect=true";
            } else {
                System.out.println("true");
                return "MRPOperationPlanExist?faces-redirect=true";
            }
        }else return "MRPRequestDenied?faces-redirect=true";
    }

    public String Back() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("salesOperationPlanId");
        return "MRPViewSalesOperationPlan?faces-redirect=true";

    }

    public String Cancel() {
        salesOperationPlan = sopl.cancelSalesOperationPlan(saleOperationPlanId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("salesOperationPlanId");
        return "MRPViewSalesOperationPlan?faces-redirect=true";
    }

    public SalesOperationPlanLocal getSopl() {
        return sopl;
    }

    public void setSopl(SalesOperationPlanLocal sopl) {
        this.sopl = sopl;
    }

    public Long getSaleForecastPlanId() {
        return saleOperationPlanId;
    }

    public void setSaleForecastPlanId(Long saleForecastPlanId) {
        this.saleOperationPlanId = saleForecastPlanId;
    }

    public SalesOperationPlanEntity getSalesOperationPlan() {
        return salesOperationPlan;
    }

    public void setSalesOperationPlan(SalesOperationPlanEntity salesOperationPlan) {
        this.salesOperationPlan = salesOperationPlan;
    }

    public Double getPlannedEndInventory() {
        return plannedEndInventory;
    }

    public void setPlannedEndInventory(Double plannedEndInventory) {
        this.plannedEndInventory = plannedEndInventory;
    }

    public Double getProductionPlanQuantity() {
        return productionPlanQuantity;
    }

    public void setProductionPlanQuantity(Double productionPlanQuantity) {
        this.productionPlanQuantity = productionPlanQuantity;
    }

    public Integer getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Integer workingDays) {
        this.workingDays = workingDays;
    }

    public Double getSalesForecastQuantity() {
        return salesForecastQuantity;
    }

    public void setSalesForecastQuantity(Double salesForecastQuantity) {
        this.salesForecastQuantity = salesForecastQuantity;
    }

    public Calendar getTargetPeriod() {
        return targetPeriod;
    }

    public void setTargetPeriod(Calendar targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

}
