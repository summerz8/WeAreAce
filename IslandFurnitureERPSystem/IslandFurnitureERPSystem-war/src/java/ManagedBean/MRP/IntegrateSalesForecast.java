/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
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
@Named(value = "integrateSalesForecast")
@ViewScoped
public class IntegrateSalesForecast {

    @EJB
    SalesForecastModuleLocal sfml;
    /**
     * Creates a new instance of IntegrateSalesForecast
     */
    private List<IntegratedSalesForecastEntity> integratedSalesForecastList;
    private Calendar time;
    private List<FactoryProductEntity> factoryProductList;
  
    public IntegrateSalesForecast() {
    }
    
    @PostConstruct
    public void getAllIntegratedSalesForecastList(){
    
        integratedSalesForecastList=sfml.getIntegrateSalesForecastList(null,null);
        time=integratedSalesForecastList.get(0).getTargetPeriod();
        factoryProductList=sfml.productListNeededTobeIntegrated(1L);
        
    }

    public SalesForecastModuleLocal getSfml() {
        return sfml;
    }

    public void setSfml(SalesForecastModuleLocal sfml) {
        this.sfml = sfml;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }


    public List<FactoryProductEntity> getFactoryProductList() {
        return factoryProductList;
    }

    public void setFactoryProductList(List<FactoryProductEntity> factoryProductList) {
        this.factoryProductList = factoryProductList;
    }

    public List<IntegratedSalesForecastEntity> getIntegratedSalesForecastList() {
        return integratedSalesForecastList;
    }

    public void setIntegratedSalesForecastList(List<IntegratedSalesForecastEntity> integratedSalesForecastList) {
        this.integratedSalesForecastList = integratedSalesForecastList;
    }

    public String integrate(Long factoryProductId){
        
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productId",factoryProductId);
       return "MRPListSalesForecast?faces-redirect=true";
    
    }
    
    
}
