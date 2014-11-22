/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Factory.MRP.SalesForecastEntity;
import SessionBean.OCRM.OCRMSalesForecastModuleLocal;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "createForecast")
@ViewScoped
public class CreateForecast {

    @EJB
    OCRMSalesForecastModuleLocal sfml;

    Long storeId;
    String department;
    Calendar targetPeriod = Calendar.getInstance();
    List<SalesForecastEntity> saleForecastList;

    public CreateForecast() {
    }

    @PostConstruct
    public void init() {
        storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        department = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("department");

        saleForecastList = sfml.createForecast(storeId);
        targetPeriod.add(Calendar.MONTH, 2);

    }

    public String viewProductList(Long SalesForecastId) {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("saleForecastId", SalesForecastId);
        return "ViewProductList?faces-redirect=true";
    }

    public OCRMSalesForecastModuleLocal getSfml() {
        return sfml;
    }

    public void setSfml(OCRMSalesForecastModuleLocal sfml) {
        this.sfml = sfml;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<SalesForecastEntity> getSaleForecastList() {
        return saleForecastList;
    }

    public void setSaleForecastList(List<SalesForecastEntity> saleForecastList) {
        this.saleForecastList = saleForecastList;
    }

    public Calendar getTargetPeriod() {
        return targetPeriod;
    }

    public void setTargetPeriod(Calendar targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    
}
