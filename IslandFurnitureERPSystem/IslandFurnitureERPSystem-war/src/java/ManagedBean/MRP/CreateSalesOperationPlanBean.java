/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
import Entity.Factory.MRP.SalesOperationPlanEntity;
import SessionBean.MRP.SalesOperationPlanLocal;
import java.io.Serializable;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
/**
 *
 * @author apple
 */
@Named(value = "createSalesOperationPlanBean")
@RequestScoped
public class CreateSalesOperationPlanBean implements Serializable {

    @EJB
    private SalesOperationPlanLocal salesOperationPlanLocal;

    private Long productId;

    private SalesOperationPlanEntity salesOperationPlan;
    private Integer workingDays;
    private Calendar targetPeriod;
    private Double salesForecastQuantity;
    private Double productionPlanQuantity=0D;
    private Double plannedEndInventory;
    FactoryProductEntity product;
    Long integratedSalesForecastId;
    
    public CreateSalesOperationPlanBean() {
    }
    
    
    @PostConstruct
    public void createSalesOperationPlan() {
       
        productId=(Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productId");
//        
//        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("productId");
        salesOperationPlan = salesOperationPlanLocal.createSalesOperationPlan(productId);
        product=salesOperationPlan.getFactoryProduct();
        System.out.println(salesOperationPlan.getTargetPeriod().getTime() + "hahahhaha");
        
        workingDays=salesOperationPlan.getWorkingDay();
        targetPeriod=salesOperationPlan.getTargetPeriod();    
        
        salesForecastQuantity=salesOperationPlan.getIntegratedSalesForecast().getAmount();
        integratedSalesForecastId=salesOperationPlan.getIntegratedSalesForecast().getId();
        plannedEndInventory=salesOperationPlan.getPlannedEndMonthInventory();
        
        productionPlanQuantity =plannedEndInventory- plannedEndInventory +salesForecastQuantity;
        System.out.println(productionPlanQuantity+"    "+salesForecastQuantity+"   "+plannedEndInventory);
    }
    
      public void changeWorkingDays(ValueChangeEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();      
        workingDays=(Integer) newValue;
    }

    public void changePlannedEndInventory(ValueChangeEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        plannedEndInventory = (Double) newValue;
        productionPlanQuantity =plannedEndInventory- (Double)oldValue +salesForecastQuantity;
        System.out.println(plannedEndInventory);
    }
    
       public void changeProductionPlanQuantity(ValueChangeEvent event){
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        productionPlanQuantity = (Double) newValue;
        System.out.println(productionPlanQuantity);
    }
//    public void changeTargetPeriod(ValueChangeEvent event){
//        Object oldValue = event.getOldValue();
//        Object newValue = event.getNewValue();
//    
//    }
       
    public String change(){
        System.out.println("change();");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("WorkingDay", workingDays);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("PlannedOrderInventory", plannedEndInventory);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ProductionPlanQuantity", productionPlanQuantity);     
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ProductId", productId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("IntegratedSalesForecastId", integratedSalesForecastId);      
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("TargetPeriod", targetPeriod);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SalesForecast", salesForecastQuantity);
        return "MRPViewNewSalesOperationPlan?faces-redirect=true";
    }
    
    

    public SalesOperationPlanLocal getSalesOperationPlanLocal() {
        return salesOperationPlanLocal;
    }

    public void setSalesOperationPlanLocal(SalesOperationPlanLocal salesOperationPlanLocal) {
        this.salesOperationPlanLocal = salesOperationPlanLocal;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long ProductId) {
        this.productId = ProductId;
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

    public Integer getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Integer workingDays) {
        this.workingDays = workingDays;
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

    public Double getProductionPlanQuantity() {
        return productionPlanQuantity;
    }

    public void setProductionPlanQuantity(Double productionPlanQuantity) {
        this.productionPlanQuantity = productionPlanQuantity;
    }
    
    
}
