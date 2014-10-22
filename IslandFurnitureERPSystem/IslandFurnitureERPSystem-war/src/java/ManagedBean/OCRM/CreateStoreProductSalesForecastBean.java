/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.ProductSalesForecastEntity;
import SessionBean.OCRM.OCRMSalesForecastModuleLocal;
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
@Named(value = "createStoreProductSalesForecastBean")
@ViewScoped
public class CreateStoreProductSalesForecastBean {

    @EJB
    OCRMSalesForecastModuleLocal sfml;

    private Long productId;
    private String productType;
    private ProductSalesForecastEntity productSalesForecast;
    private String productName;
    private Double NewForecastAmount;

    public CreateStoreProductSalesForecastBean() {
    }

    @PostConstruct
    public void init() {
        productId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ProductId");
        productType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productType");
        productSalesForecast = sfml.createProductSalesForecast(productId, productType);
        if (productType.equals("StoreProduct")) {
            productName = productSalesForecast.getStoreProduct().getName();
        } else {
            productName = productSalesForecast.getStoreRetailProduct().getName();
        }

    }

    public String Confirm() {
        if (productType.equals("StoreProduct")) {
            productSalesForecast = sfml.confirmProductSalesForecast(productSalesForecast.getPeriod(), productSalesForecast.getAmount(), productSalesForecast.getStore().getStoreId(), productType, productSalesForecast.getStoreProduct().getStoreProductId());
        } else {
            productSalesForecast = sfml.confirmProductSalesForecast(productSalesForecast.getPeriod(), productSalesForecast.getAmount(), productSalesForecast.getStore().getStoreId(), productType, productSalesForecast.getStoreRetailProduct().getStoreRetailProductId());
        }

        return "SalesForecastIndex?face-redirect=true";
    }

    public void changeAmount(ValueChangeEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        NewForecastAmount = (Double) newValue;
    }

    public OCRMSalesForecastModuleLocal getSfml() {
        return sfml;
    }

    public void setSfml(OCRMSalesForecastModuleLocal sfml) {
        this.sfml = sfml;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public ProductSalesForecastEntity getProductSalesForecast() {
        return productSalesForecast;
    }

    public void setProductSalesForecast(ProductSalesForecastEntity productSalesForecast) {
        this.productSalesForecast = productSalesForecast;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getNewForecastAmount() {
        return NewForecastAmount;
    }

    public void setNewForecastAmount(Double NewForecastAmount) {
        this.NewForecastAmount = NewForecastAmount;
    }

}
