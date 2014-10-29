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
@Named(value = "editProductSalesForecast")
@ViewScoped
public class EditProductSalesForecast {

    @EJB
    OCRMSalesForecastModuleLocal sfml;

    private Long productId;
    private String productType;
    private Long productSalesForecastId;
    private ProductSalesForecastEntity productSalesForecast;
    private String productName;
    private Double newForecastAmount;

    public EditProductSalesForecast() {
    }

    @PostConstruct
    public void init() {
        productId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ProductId");
        productType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productType");
        productSalesForecastId=(Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productSalesForecastId");
        productSalesForecast = sfml.getProductSalesForecast(productSalesForecastId);
        
        if (productType.equals("StoreProduct")) {
            productName = productSalesForecast.getStoreProduct().getName();
        } else {
            productName = productSalesForecast.getStoreRetailProduct().getName();
        }

    }

    public void changeAmount(ValueChangeEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        newForecastAmount = (Double) newValue;
    }

    public String Confirm() {
      
            sfml.editProductSalesForecast(productSalesForecast.getId(),newForecastAmount);
   
        return "SalesForecastIndex?face-redirect=true";
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

    public Long getProductSalesForecastId() {
        return productSalesForecastId;
    }

    public void setProductSalesForecastId(Long productSalesForecastId) {
        this.productSalesForecastId = productSalesForecastId;
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
        return newForecastAmount;
    }

    public void setNewForecastAmount(Double newForecastAmount) {
        this.newForecastAmount = newForecastAmount;
    }
    
}
