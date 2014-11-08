/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.SalesForecastEntity;
import SessionBean.MRP.SalesForecastModuleLocal;
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
@Named(value = "listStoreSalesForecast")
@ViewScoped
public class ListStoreSalesForecast {

    @EJB
    SalesForecastModuleLocal sfml;
    
    Long storeId;
    Long factoryId;
    List<SalesForecastEntity> salesForecastList;
    
    public ListStoreSalesForecast() {
    }

    @PostConstruct
    public void init() {

        storeId= (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("storeId");
        factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        System.out.println(storeId+"  "+factoryId);
        salesForecastList=sfml.ListSalesForecast(factoryId, storeId, null, null);
        
    }

    public String ViewSalesForecast(Long salesForecastId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesForecastId", salesForecastId);

        return "MRPViewSalesForecast?faces-redirect=true";
    }
       
    public SalesForecastModuleLocal getSfml() {
        return sfml;
    }

    public void setSfml(SalesForecastModuleLocal sfml) {
        this.sfml = sfml;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public List<SalesForecastEntity> getSalesForecastList() {
        return salesForecastList;
    }

    public void setSalesForecastList(List<SalesForecastEntity> salesForecastList) {
        this.salesForecastList = salesForecastList;
    }
    
    
}
