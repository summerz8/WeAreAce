/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.SalesForecastEntity;
import SessionBean.MRP.SalesForecastModuleLocal;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author apple
 */
@Named(value = "salesForecastBean")
@RequestScoped
public class SalesForecastBean {

    @EJB
    private SalesForecastModuleLocal salesForecastModule;

    private List<SalesForecastEntity> salesForecastList;
    private Long storeId;
    private Calendar targetPeriod;
    private Long factoryId;
    private String department;

    public SalesForecastBean() {
    }

    @PostConstruct
    public void getAllSalesForecast() {

        factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        department = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("department");

        try {
            if (department.equals("H")) {
                salesForecastList = salesForecastModule.ListSalesForecast(null, null, null, null);
            } else {
                salesForecastList = salesForecastModule.ListSalesForecast(factoryId, null, null, null);
            }
        } catch (Exception e) {
            System.err.println("Caught an unexpected exception managedbean");
            e.printStackTrace();
        }

    }

    public List<SalesForecastEntity> getSalesForecast() {
        try {
            salesForecastList = salesForecastModule.ListSalesForecast(factoryId, storeId, null, targetPeriod);
        } catch (Exception e) {
            System.err.println("Caught an unexpected exception managedbean");
            e.printStackTrace();
        }
        return salesForecastList;
    }

//    public boolean Department() {
//        department=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("department");
//        System.out.println(department);
//        return department.equals("F");
//        
//    }

    public SalesForecastModuleLocal getSalesForecastModule() {
        return salesForecastModule;
    }

    public void setSalesForecastModule(SalesForecastModuleLocal salesForecastModule) {
        this.salesForecastModule = salesForecastModule;
    }

    public List<SalesForecastEntity> getSalesForecastList() {
        return salesForecastList;
    }

    public void setSalesForecastList(List<SalesForecastEntity> salesForecastList) {
        this.salesForecastList = salesForecastList;
    }

    public String ViewSalesForecast(Long salesForecastId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesForecastId", salesForecastId);

        return "MRPViewSalesForecast?faces-redirect=true";
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Calendar getTargetPeriod() {
        return targetPeriod;
    }

    public void setTargetPeriod(Calendar targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
}
