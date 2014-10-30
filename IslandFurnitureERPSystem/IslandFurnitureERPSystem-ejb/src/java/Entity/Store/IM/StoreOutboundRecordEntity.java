/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.IM;

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
public class StoreOutboundRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double amount;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar creationTime;

    
    @ManyToOne 
    private StoreWarehouseBinEntity fromBin;
    

       @ManyToOne
    private StoreRetailProductEntity storeRetailProduct = null;
    
    @ManyToOne
    private StoreProductEntity storeProduct = null;

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

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }


    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public StoreWarehouseBinEntity getFromBin() {
        return fromBin;
    }

    public void setFromBin(StoreWarehouseBinEntity fromBin) {
        this.fromBin = fromBin;
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
        if (!(object instanceof StoreOutboundRecordEntity)) {
            return false;
        }
        StoreOutboundRecordEntity other = (StoreOutboundRecordEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.IM.StoreOutbondRecord[ id=" + id + " ]";
    }
    
}
