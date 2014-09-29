/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.MRP.SalesOperationPlanEntity;
import SessionBean.MRP.SalesOperationPlanLocal;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author apple
 */
@Named(value = "salesOperationPlanBean")
@ViewScoped
public class SalesOperationPlanBean {

    /**
     * Creates a new instance of SalesOperationPlanBean
     */
    @EJB
    private SalesOperationPlanLocal salesOperationPlanLocal;
    private SalesOperationPlanEntity salesOperationPlan;
    private List<SalesOperationPlanEntity> salesOperationPlanList;
    private List<FactoryProductEntity> factoryProductList;
    private Long productId;
    private Calendar targetPeriod;
    private String department;
    private Integer workingDay = 0;
    private Double PlannedEndInventory = 0D;
    private Long factoryId;

    public SalesOperationPlanBean() {
    }

    @PostConstruct
    public void getAllFactoryProductList() {
        factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        department = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("department");
        System.out.println(department);
        if (department.equals("H")) {
            System.out.println("hhhhh");
            factoryProductList = salesOperationPlanLocal.getAllFacotryProduct(null);
        } else {
            factoryProductList = salesOperationPlanLocal.getAllFacotryProduct(factoryId);
        }

    }

    public String findSalesOperationPlanList(Long id) {
        productId = id;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesproductId", productId);
        salesOperationPlanList = salesOperationPlanLocal.ListSalesOperationPlan(id, null, null);
        if (salesOperationPlanList.isEmpty()) {
            System.out.println("4");
        }
        return "MRPViewSalesOperationPlan?faces-redirect=true";
    }

    public String GETProductId() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productId", productId);
        boolean bo = salesOperationPlanLocal.IsThereForecast(productId);
        if (bo == false) {
            return "MRPSalesForecastNotExist?faces-redirect=true";
        } else {
            return "MRPNewSalesOperationPlan?faces-redirect=true";
        }
    }

    public SalesOperationPlanLocal getSalesOperationPlanLocal() {
        return salesOperationPlanLocal;
    }

    public void setSalesOperationPlanLocal(SalesOperationPlanLocal salesOperationPlanLocal) {
        this.salesOperationPlanLocal = salesOperationPlanLocal;
    }

    public List<SalesOperationPlanEntity> getSalesOperationPlanList() {
        return salesOperationPlanList;
    }

    public void setSalesOperationPlanList(List<SalesOperationPlanEntity> salesOperationPlanList) {
        this.salesOperationPlanList = salesOperationPlanList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long storeId) {
        this.productId = storeId;
    }

    public Calendar getTargetPeriod() {
        return targetPeriod;
    }

    public void setTargetPeriod(Calendar targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    public List<FactoryProductEntity> getFactoryProductList() {
        return factoryProductList;
    }

    public void setFactoryProductList(List<FactoryProductEntity> factoryProductList) {
        this.factoryProductList = factoryProductList;
    }

    public SalesOperationPlanEntity getSalesOperationPlan() {
        return salesOperationPlan;
    }

    public void setSalesOperationPlan(SalesOperationPlanEntity salesOperationPlan) {
        this.salesOperationPlan = salesOperationPlan;
    }

    public Integer getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(Integer workingDay) {
        this.workingDay = workingDay;
    }

    public Double getPlannedEndInventory() {
        return PlannedEndInventory;
    }

    public void setPlannedEndInventory(Double PlannedEndInventory) {
        this.PlannedEndInventory = PlannedEndInventory;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
