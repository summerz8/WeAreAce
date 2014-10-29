/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store;

import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Store.OCRM.ProductSalesForecastEntity;
import Entity.Store.OCRM.SalesRecordEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dan
 */
@Entity
@Table(name = "StoreRetailProduct")
public class StoreRetailProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeRetailProductId;
    private Double quantity;
    private String Name;

    //store retail product entity -- factory retail productentity: M <--> 1 
    @ManyToOne
    private FactoryRetailProductEntity factoryRetailProduct;

    //store retail product entity -- stores entity: M <--> 1
    @ManyToOne
    private StoreEntity store;

    //store retail product entity -- retail product: M<-->1
    @ManyToOne
    private RetailProductEntity retailProduct;

    @OneToMany(cascade= {CascadeType.PERSIST},mappedBy="storeRetailProduct")
    private List<SalesRecordEntity> salesRecordList;
    
    @OneToMany(cascade= {CascadeType.PERSIST},mappedBy="storeRetailProduct")
    private List<ProductSalesForecastEntity> productSalesForecastList;

    public StoreRetailProductEntity() {
    }

    public StoreRetailProductEntity(FactoryRetailProductEntity factoryretail, StoreEntity store) {
        this.factoryRetailProduct = factoryretail;
        this.store = store;
        salesRecordList=new ArrayList<>();
        productSalesForecastList=new ArrayList<>();
    }

    public Long getStoreRetailProductId() {
        return storeRetailProductId;
    }

    public void setStoreRetailProductId(Long storeRetailProductId) {
        this.storeRetailProductId = storeRetailProductId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public FactoryRetailProductEntity getFactoryRetailProduct() {
        return factoryRetailProduct;
    }

    public void setFactoryRetailProduct(FactoryRetailProductEntity factoryRetailProduct) {
        this.factoryRetailProduct = factoryRetailProduct;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public RetailProductEntity getRetailProduct() {
        return retailProduct;
    }

    public void setRetailProduct(RetailProductEntity retailProduct) {
        this.retailProduct = retailProduct;
    }

    public List<SalesRecordEntity> getSalesRecordList() {
        return salesRecordList;
    }

    public void setSalesRecordList(List<SalesRecordEntity> salesRecordList) {
        this.salesRecordList = salesRecordList;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public List<ProductSalesForecastEntity> getProductSalesForecastList() {
        return productSalesForecastList;
    }

    public void setProductSalesForecastList(List<ProductSalesForecastEntity> productSalesForecast) {
        this.productSalesForecastList = productSalesForecast;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeRetailProductId != null ? storeRetailProductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the storeRetailProductId fields are not set
        if (!(object instanceof StoreRetailProductEntity)) {
            return false;
        }
        StoreRetailProductEntity other = (StoreRetailProductEntity) object;
        if ((this.storeRetailProductId == null && other.storeRetailProductId != null) || (this.storeRetailProductId != null && !this.storeRetailProductId.equals(other.storeRetailProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.StoreRetailProduct[ id=" + storeRetailProductId + " ]";
    }

}
