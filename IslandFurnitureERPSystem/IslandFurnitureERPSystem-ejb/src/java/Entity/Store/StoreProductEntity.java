/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.ProductEntity;
import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.OCRM.ProductSalesForecastEntity;
import Entity.Store.OCRM.SalesRecordEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zhengyuan
 */
@Entity
@Table(name = "StoreProduct")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(value = XmlAccessType.FIELD)
public class StoreProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeProductId;

    private String name;
//    private Double quantity;
    private String unit;
    private Double minimumInventory;
    private Double unrestrictedInventory;
    private Double returnedInventory;

    private Boolean selfPick;
    private Boolean deleteFlag;
    private String storeRemark;
    //store product entity -- factory product entity: M <--> 1 
    @ManyToOne
    private FactoryProductEntity factoryProduct;

    //store product entity -- stores entity: M <--> 1
    @ManyToOne
    private StoreEntity store;

    @ManyToOne
    private ProductEntity product;

    @OneToMany
    private Collection<StoreBinProductEntity> binProducts;

    @OneToMany
    @XmlTransient
    private Collection<ReturnedItemMovementRecordEntity> returnedItemMovementRecords = null;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "storeProduct")
    @XmlTransient
    private List<SalesRecordEntity> salesRecordList;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "storeProduct")
    @XmlTransient
    private List<ProductSalesForecastEntity> productSalesForecastList;

    public StoreProductEntity() {
    }

//    public StoreProductEntity(FactoryProductEntity factoryproduct, StoreEntity store, Boolean selfPick) {
//        this.factoryProduct = factoryproduct;
//        this.store = store;
//        this.selfPick = selfPick;
//        salesRecordList = new ArrayList<>();
//        productSalesForecastList = new ArrayList<>();
//    }
    public StoreProductEntity(FactoryProductEntity factoryproduct, StoreEntity store, Boolean selfPick, String storeRemark) {
        System.out.println("Testing!" + factoryproduct.getFactoryProductId());
        this.name = factoryproduct.getName();
        this.unit = factoryproduct.getUnit();
        this.factoryProduct = factoryproduct;
        this.store = store;

        this.deleteFlag = false;
        this.selfPick = selfPick;
        salesRecordList = new ArrayList<>();
        productSalesForecastList = new ArrayList<>();
        minimumInventory = 50D;
        unrestrictedInventory = 0D;
        returnedInventory = 0D;
        this.storeRemark = storeRemark;
    }

    public Long getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(Long storeProductId) {
        this.storeProductId = storeProductId;
    }

    public FactoryProductEntity getFactoryProduct() {
        return factoryProduct;
    }

    public void setFactoryProduct(FactoryProductEntity factoryProduct) {
        this.factoryProduct = factoryProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Collection<ReturnedItemMovementRecordEntity> getReturnedItemMovementRecords() {
        return returnedItemMovementRecords;
    }

    public void setReturnedItemMovementRecords(Collection<ReturnedItemMovementRecordEntity> returnedItemMovementRecords) {
        this.returnedItemMovementRecords = returnedItemMovementRecords;
    }

    public Boolean getSelfPick() {
        return selfPick;
    }

    public void setSelfPick(Boolean selfPick) {
        this.selfPick = selfPick;
    }

    public List<SalesRecordEntity> getSalesRecordList() {
        return salesRecordList;
    }

    public void setSalesRecordList(List<SalesRecordEntity> salesRecordList) {
        this.salesRecordList = salesRecordList;
    }

    public List<ProductSalesForecastEntity> getProductSalesForecastList() {
        return productSalesForecastList;
    }

    public void setProductSalesForecastList(List<ProductSalesForecastEntity> productSalesForecastList) {
        this.productSalesForecastList = productSalesForecastList;
    }

    public Double getMinimumInventory() {
        return minimumInventory;
    }

    public void setMinimumInventory(Double minimumInventory) {
        this.minimumInventory = minimumInventory;
    }

    public Double getUnrestrictedInventory() {
        return unrestrictedInventory;
    }

    public void setUnrestrictedInventory(Double unrestrictedInventory) {
        this.unrestrictedInventory = unrestrictedInventory;
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

    public Collection<StoreBinProductEntity> getBinProducts() {
        return binProducts;
    }

    public void setBinProducts(Collection<StoreBinProductEntity> binProducts) {
        this.binProducts = binProducts;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeProductId != null ? storeProductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the storeProductId fields are not set
        if (!(object instanceof StoreProductEntity)) {
            return false;
        }
        StoreProductEntity other = (StoreProductEntity) object;
        if ((this.storeProductId == null && other.storeProductId != null) || (this.storeProductId != null && !this.storeProductId.equals(other.storeProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.StoreProductEntity[ id=" + storeProductId + " ]";
    }

}
