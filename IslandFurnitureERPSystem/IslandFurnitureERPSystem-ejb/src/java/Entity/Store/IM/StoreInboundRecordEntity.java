/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.IM;

import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author zhengyuan
 */
@Entity
public class StoreInboundRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Double amount;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar creationTime;
    
    
    
    @ManyToOne
    private StoreWarehouseBinEntity toBin;
    
    @ManyToOne
    private StoreRetailProductEntity storeRetailProduct = null;
    
    @ManyToOne
    private StoreProductEntity storeProduct = null;
    
    
    private Integer destinationStatus;
    
    
    @ManyToOne
    private StoreEntity store;

    public StoreInboundRecordEntity() {
    }
    
    
    
    public StoreInboundRecordEntity(Double amount, Calendar creationTime){
        this.amount = amount;
        this.creationTime = creationTime;
        
    }
    
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public StoreWarehouseBinEntity getToBin() {
        return toBin;
    }

    public void setToBin(StoreWarehouseBinEntity toBin) {
        this.toBin = toBin;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public StoreRetailProductEntity getStoreRetailProduct() {
        return storeRetailProduct;
    }

    public void setStoreRetailProduct(StoreRetailProductEntity storeRetailProduct) {
        this.storeRetailProduct = storeRetailProduct;
    }

    public StoreProductEntity getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(StoreProductEntity storeProduct) {
        this.storeProduct = storeProduct;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public Integer getDestinationStatus() {
        return destinationStatus;
    }

    public void setDestinationStatus(Integer destinationStatus) {
        this.destinationStatus = destinationStatus;
    }

    

    public Long getId() {
        return id;
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
        if (!(object instanceof StoreInboundRecordEntity)) {
            return false;
        }
        StoreInboundRecordEntity other = (StoreInboundRecordEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.IM.StoreInboundRecord[ id=" + id + " ]";
    }
    
}
