/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
import SessionBean.MRP.SalesForecastModuleLocal;
import java.util.ArrayList;
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
    private List<FactoryRetailProductEntity> factoryRetailProductList = new ArrayList<>();
    private List<IntegratedSalesForecastEntity> factoryProduct = new ArrayList<>();
    private List<IntegratedSalesForecastEntity> factoryRetailProduct = new ArrayList<>();
    private Long factoryId;

    public IntegrateSalesForecast() {
    }

    @PostConstruct
    public void getAllIntegratedSalesForecastList() {
        factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");

        integratedSalesForecastList = sfml.getIntegrateSalesForecastList(factoryId,null, null);
        while (!integratedSalesForecastList.isEmpty()) {
            if (integratedSalesForecastList.get(0).getFactoryProduct() == null) {
                factoryRetailProduct.add(integratedSalesForecastList.get(0));
            } else {
                factoryProduct.add(integratedSalesForecastList.get(0));
            }
            integratedSalesForecastList.remove(0);
        }
        time = Calendar.getInstance();
        time.set(time.get(Calendar.YEAR), time.get(Calendar.MONTH) + 2, 2, 0, 0, 0);

        System.out.println(time.getTime());
        factoryProductList = sfml.productListNeededTobeIntegrated(factoryId);
        factoryRetailProductList = sfml.retailProductListNeedToBeIntegrated(factoryId);
        
        

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

    public String integrateFactoryProduct(Long ProductId) {
      
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productId", ProductId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("type", "factoryProduct");
        
        return "MRPListSalesForecast?faces-redirect=true";

    }

    public String integrateFactoryRetailProduct(Long ProductId) {
     
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productId", ProductId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("type", "factoryRetailProduct");
       
        return "MRPListSalesForecast?faces-redirect=true";

    }

    public List<IntegratedSalesForecastEntity> getFactoryProduct() {
        return factoryProduct;
    }

    public void setFactoryProduct(List<IntegratedSalesForecastEntity> factoryProduct) {
        this.factoryProduct = factoryProduct;
    }

    public List<FactoryRetailProductEntity> getFactoryRetailProductList() {
        return factoryRetailProductList;
    }

    public void setFactoryRetailProductList(List<FactoryRetailProductEntity> factoryRetailProduct) {
        this.factoryRetailProductList = factoryRetailProduct;
    }

    public List<IntegratedSalesForecastEntity> getFactoryRetailProduct() {
        return factoryRetailProduct;
    }

    public void setFactoryRetailProduct(List<IntegratedSalesForecastEntity> factoryRetailProductList) {
        this.factoryRetailProduct = factoryRetailProductList;
    }

}
