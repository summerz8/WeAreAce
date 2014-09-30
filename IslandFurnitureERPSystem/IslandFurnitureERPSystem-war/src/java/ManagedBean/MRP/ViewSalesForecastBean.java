/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.FactoryProductAmountEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import SessionBean.MRP.SalesForecastModuleLocal;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author apple
 */
@Named(value = "viewSalesForecastBean")
@ViewScoped
public class ViewSalesForecastBean {

    @EJB
    private SalesForecastModuleLocal salesForecastModule;

    private SalesForecastEntity salesForecast;
    private List<SalesForecastEntity> salesForecastList;
    private List<FactoryProductAmountEntity> factoryProductList;
    private List<FactoryRetailProductAmountEntity> factoryRetailProductList;
    Long salesForecastId;
    Long storeId;

    public ViewSalesForecastBean() {
    }

    @PostConstruct
    public void ViewSalesForecast() {
        salesForecastId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("salesForecastId");

        salesForecast = salesForecastModule.GetSalesForecast(salesForecastId);
        storeId = salesForecast.getStore().getStoreId();
        factoryProductList = salesForecast.getFactoryProductList();
        System.out.println(factoryProductList.get(0).getFactoryProductAmountId());
        factoryRetailProductList = salesForecast.getFactoryRetailProductList();

    }

    public void getSalesForecastList(Long storeId, Object product, Calendar targetPeriod) {
        Long factoryId=(Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        salesForecastList = salesForecastModule.ListSalesForecast(factoryId,storeId, null, targetPeriod);
        if (salesForecastList.get(0).getFactoryProductList().isEmpty()) {
            factoryRetailProductList = salesForecastList.get(0).getFactoryRetailProductList();
        } else {
            factoryProductList = salesForecastList.get(0).getFactoryProductList();
        }

    }

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

    public List<FactoryProductAmountEntity> getFactoryProductList() {
        return factoryProductList;
    }

    public void setFactoryProductList(List<FactoryProductAmountEntity> factoryProductList) {
        this.factoryProductList = factoryProductList;
    }

    public List<FactoryRetailProductAmountEntity> getFactoryRetailProductList() {
        return factoryRetailProductList;
    }

    public void setFactoryRetailProductList(List<FactoryRetailProductAmountEntity> factoryRetailProductList) {
        this.factoryRetailProductList = factoryRetailProductList;
    }

    public SalesForecastEntity getSalesForecast() {
        return salesForecast;
    }

    public void setSalesForecast(SalesForecastEntity salesForecast) {
        this.salesForecast = salesForecast;
    }

    public Long getSalesForecastId() {
        return salesForecastId;
    }

    public void setSalesForecastId(Long salesForecastId) {
        this.salesForecastId = salesForecastId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

}
