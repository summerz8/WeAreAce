/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

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
@Named(value = "viewSalesRecordBean")
@ViewScoped
public class ViewSalesRecordBean {

    @EJB
    OCRMSalesForecastModuleLocal sfml;
    
    private Long productId;
    private String productType;
    private StoreProductEntity storeProduct;
    private StoreRetailProductEntity storeRetailProductEntity;
    private List<SalesRecordEntity> salesRecordList;
    
    public ViewSalesRecordBean() {
    }

    @PostConstruct
    public void init() {
        productId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ProductId" );
        productType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productType");
        
        salesRecordList=sfml.listSalesRecord(productId, productType);
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

    public StoreProductEntity getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(StoreProductEntity storeProduct) {
        this.storeProduct = storeProduct;
    }

    public StoreRetailProductEntity getStoreRetailProductEntity() {
        return storeRetailProductEntity;
    }

    public void setStoreRetailProductEntity(StoreRetailProductEntity storeRetailProductEntity) {
        this.storeRetailProductEntity = storeRetailProductEntity;
    }

    public List<SalesRecordEntity> getSalesRecordList() {
        return salesRecordList;
    }

    public void setSalesRecordList(List<SalesRecordEntity> salesRecordList) {
        this.salesRecordList = salesRecordList;
    }

    
    
}
