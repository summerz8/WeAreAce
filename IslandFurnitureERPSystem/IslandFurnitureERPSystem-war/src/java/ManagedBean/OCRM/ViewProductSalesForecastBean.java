/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.ProductSalesForecastEntity;
import Entity.Store.OCRM.SalesRecordEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import SessionBean.OCRM.OCRMSalesForecastModuleLocal;
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
@Named(value = "viewProductSalesForecastBean")
@ViewScoped
public class ViewProductSalesForecastBean {

    @EJB
    OCRMSalesForecastModuleLocal sfml;

    private Long productId;
    private String productType;
    private List<ProductSalesForecastEntity> productSalesForecastList;

    public ViewProductSalesForecastBean() {
    }

    @PostConstruct
    public void init() {
        productId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ProductId");
        productType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productType");

        productSalesForecastList = sfml.listProductSalesForecast(productId, productType);
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

    public List<ProductSalesForecastEntity> getProductSalesForecastList() {
        return productSalesForecastList;
    }

    public void setProductSalesForecastList(List<ProductSalesForecastEntity> productSalesForecastList) {
        this.productSalesForecastList = productSalesForecastList;
    }

    
}
