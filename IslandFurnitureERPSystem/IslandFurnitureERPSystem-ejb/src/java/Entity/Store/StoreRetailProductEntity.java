/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store;

import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Store.IM.StoreGoodReceiptEntity;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dan
 */
@Entity
@Table(name = "StoreRetailProduct")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class StoreRetailProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeRetailProductId;
//    private Double quantity;
    private String Name;
    private Double unrestrictedInventory = 0D;//start with 0
    private Double minimumInventory = 50D;
    private Double returnedInventory = 0D;
    private String storeRemark;
    private String unit;
    private Boolean deleteFlag;
    
    private Double intransitInventory;
    
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
    @XmlTransient
    private List<SalesRecordEntity> salesRecordList;
    
    @OneToMany(cascade= {CascadeType.PERSIST},mappedBy="storeRetailProduct")
    @XmlTransient
    private List<ProductSalesForecastEntity> productSalesForecastList;
    
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "srpe")
    private List<StoreGoodReceiptEntity> goodReceipts;
    

    public StoreRetailProductEntity() {
    }

//    public StoreRetailProductEntity(FactoryRetailProductEntity factoryretail, StoreEntity store) {
//        this.factoryRetailProduct = factoryretail;
//        this.store = store;
//        salesRecordList=new ArrayList<>();
//        productSalesForecastList=new ArrayList<>();
//    }
    
    public StoreRetailProductEntity(FactoryRetailProductEntity factoryretail, StoreEntity store, String storeRemark) {
        this.factoryRetailProduct = factoryretail;
        this.store = store;
        salesRecordList=new ArrayList<>();
        productSalesForecastList=new ArrayList<>();
        unrestrictedInventory = 0D;
        minimumInventory = 50D;
        returnedInventory = 0D;
        this.Name = factoryretail.getRetailProduct().getName();
        this.unit = factoryretail.getRetailProduct().getUnit();
        this.storeRemark = storeRemark;
        this.deleteFlag = false;
        goodReceipts = new ArrayList<>();
        intransitInventory = 0D;
    }
    

    public List<StoreGoodReceiptEntity> getGoodReceipts() {
        return goodReceipts;
    }

    public void setGoodReceipts(List<StoreGoodReceiptEntity> goodReceipts) {
        this.goodReceipts = goodReceipts;
    }

    public Double getIntransitInventory() {
        return intransitInventory;
    }

    public void setIntransitInventory(Double intransitInventory) {
        this.intransitInventory = intransitInventory;
    }

    
    
    public Long getStoreRetailProductId() {
        return storeRetailProductId;
    }

    public void setStoreRetailProductId(Long storeRetailProductId) {
        this.storeRetailProductId = storeRetailProductId;
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

    public Double getUnrestrictedInventory() {
        return unrestrictedInventory;
    }

    public void setUnrestrictedInventory(Double unrestrictedInventory) {
        this.unrestrictedInventory = unrestrictedInventory;
    }

    public Double getMinimumInventory() {
        return minimumInventory;
    }

    public void setMinimumInventory(Double minimumInventory) {
        this.minimumInventory = minimumInventory;
    }

    public Double getReturnedInventory() {
        return returnedInventory;
    }

    public void setReturnedInventory(Double returnedInventory) {
        this.returnedInventory = returnedInventory;
    }

    public String getStoreRemark() {
        return storeRemark;
    }

    public void setStoreRemark(String storeRemark) {
        this.storeRemark = storeRemark;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
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
