/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.IM;

import Entity.Store.StoreEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author zhengyuan
 */
@Entity
public class StoreWarehouseBinEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isDeleted;
    private Boolean isBackHouse;
    private Boolean isDisplayArea;
    private Boolean isSelfCollect;
    private String name;
    private String remark;
    
   
    

    @OneToMany(cascade = {CascadeType.PERSIST})
    private Collection<StoreBinProductEntity> storeBinProducts;
    
    @OneToMany(cascade = {CascadeType.PERSIST})
    private Collection<StoreBinRetailProductEntity> storeBinRetailProducts;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "fromBin")
    private Collection<StoreInStoreMovementRecordEntity> frominstoreMovementRecords;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "toBin")
    private Collection<StoreInStoreMovementRecordEntity> toinstoreMovementRecords;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "toBin")
    private Collection<StoreInboundRecordEntity> inboundMovementRecords;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "fromBin")
    private Collection<StoreOutboundRecordEntity> outboundMovementRecords;
    
    
    @ManyToOne
    private StoreEntity store;
    

     
    public StoreWarehouseBinEntity(String name, String remark, Boolean isBackHouse, Boolean isDisplayArea, Boolean isSelfCollect) {
        this.isDeleted = false;
        this.isBackHouse = isBackHouse;
        this.isDisplayArea = isDisplayArea;
        this.isSelfCollect = isSelfCollect;
        this.name = name;
        this.remark = remark;
        
    }

    public StoreWarehouseBinEntity() {
       
    }
    
    
    

    public Long getId() {
       
        return id;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public Collection<StoreInStoreMovementRecordEntity> getFrominstoreMovementRecords() {
        return frominstoreMovementRecords;
    }

    public void setFrominstoreMovementRecords(Collection<StoreInStoreMovementRecordEntity> frominstoreMovementRecords) {
        this.frominstoreMovementRecords = frominstoreMovementRecords;
    }

    public Collection<StoreInStoreMovementRecordEntity> getToinstoreMovementRecords() {
        return toinstoreMovementRecords;
    }

    public void setToinstoreMovementRecords(Collection<StoreInStoreMovementRecordEntity> toinstoreMovementRecords) {
        this.toinstoreMovementRecords = toinstoreMovementRecords;
    }

    public Collection<StoreInboundRecordEntity> getInboundMovementRecords() {
        return inboundMovementRecords;
    }

    public void setInboundMovementRecords(Collection<StoreInboundRecordEntity> inboundMovementRecords) {
        this.inboundMovementRecords = inboundMovementRecords;
    }

    public Collection<StoreOutboundRecordEntity> getOutboundMovementRecords() {
        return outboundMovementRecords;
    }

    public void setOutboundMovementRecords(Collection<StoreOutboundRecordEntity> outboundMovementRecords) {
        this.outboundMovementRecords = outboundMovementRecords;
    }


    public Collection<StoreBinProductEntity> getStoreBinProducts() {
        return storeBinProducts;
    }

    public void setStoreBinProducts(Collection<StoreBinProductEntity> storeBinProducts) {
        this.storeBinProducts = storeBinProducts;
    }

    public Collection<StoreBinRetailProductEntity> getStoreBinRetailProducts() {
        return storeBinRetailProducts;
    }

    public void setStoreBinRetailProducts(Collection<StoreBinRetailProductEntity> storeBinRetailProducts) {
        this.storeBinRetailProducts = storeBinRetailProducts;
    }

    public Boolean isIsBackHouse() {
        return isBackHouse;
    }

    public void setIsBackHouse(Boolean isBackHouse) {
        this.isBackHouse = isBackHouse;
    }

    public Boolean isIsDisplayArea() {
        return isDisplayArea;
    }

    public void setIsDisplayArea(Boolean isDisplayArea) {
        this.isDisplayArea = isDisplayArea;
    }

    public Boolean isIsSelfCollect() {
        return isSelfCollect;
    }

    public void setIsSelfCollect(Boolean isSelfCollect) {
        this.isSelfCollect = isSelfCollect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
    
    
    

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreWarehouseBinEntity)) {
            return false;
        }
        StoreWarehouseBinEntity other = (StoreWarehouseBinEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.IM.StoreWarehouseBinEntity[ id=" + id + " ]";
    }
    
}
