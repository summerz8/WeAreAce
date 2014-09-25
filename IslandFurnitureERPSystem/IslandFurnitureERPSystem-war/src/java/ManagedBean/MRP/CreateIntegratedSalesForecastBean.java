/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.MRP;

import Entity.Factory.MRP.IntegratedSalesForecastEntity;
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
@Named(value = "createIntegratedSalesForecastBean")
@ViewScoped
public class CreateIntegratedSalesForecastBean {

    @EJB
    SalesForecastModuleLocal SFML;
    
    Long productId;
    IntegratedSalesForecastEntity integratedSalesForecast;
    List<SalesForecastEntity> salesForecastList;
    Calendar targetPeriod;
    
    public CreateIntegratedSalesForecastBean() {
    }
    
    @PostConstruct
    public void ListSalesForecast(){
        productId=(Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productId");
        integratedSalesForecast=SFML.IntegrateSalesForecast(productId, null);
        salesForecastList=integratedSalesForecast.getSalesForecastList();
        targetPeriod=integratedSalesForecast.getTargetPeriod();
        
    }

    public SalesForecastModuleLocal getSFML() {
        return SFML;
    }

    public void setSFML(SalesForecastModuleLocal SFML) {
        this.SFML = SFML;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public IntegratedSalesForecastEntity getIntegratedSalesForecast() {
        return integratedSalesForecast;
    }

    public void setIntegratedSalesForecast(IntegratedSalesForecastEntity integratedSalesForecast) {
        this.integratedSalesForecast = integratedSalesForecast;
    }

    public List<SalesForecastEntity> getSalesForecastList() {
        return salesForecastList;
    }

    public void setSalesForecastList(List<SalesForecastEntity> salesForecastList) {
        this.salesForecastList = salesForecastList;
    }

    public Calendar getTargetPeriod() {
        return targetPeriod;
    }

    public void setTargetPeriod(Calendar targetPeriod) {
        this.targetPeriod = targetPeriod;
    }
    
    
    
}
