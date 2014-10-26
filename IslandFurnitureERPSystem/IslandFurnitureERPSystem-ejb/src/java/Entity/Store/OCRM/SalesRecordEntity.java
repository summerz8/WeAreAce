/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

import Entity.Store.OCRM.StoreProductAmountEntity;
import Entity.Store.OCRM.StoreRetailProductAmountEntity;
import Entity.Store.StoreEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author dan
 */
@Entity
public class SalesRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesRecordId;
    
    @ManyToOne
    private StoreEntity store;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetPeriod;
    private String status; // unconfirmed, confirmed
    //store product amount entity -- sales forecast entity M <-- 1
    @OneToMany(cascade={CascadeType.PERSIST})
    private List<StoreProductAmountEntity> storeProductList = new ArrayList<>();

    //store product amount entity -- sales forecast entity M <-- 1
    @OneToMany(cascade={CascadeType.PERSIST})
    private List<StoreRetailProductAmountEntity> storeRetailProductList = new ArrayList<>();

    public SalesRecordEntity() {
    }

    public SalesRecordEntity(StoreEntity store, Calendar targetPeriod) {
        this.store = store;
        this.targetPeriod = targetPeriod;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public Calendar getTargetPeriod() {
        return targetPeriod;
    }

    public void setTargetPeriod(Calendar targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<StoreProductAmountEntity> getStoreProductList() {
        return storeProductList;
    }

    public void setStoreProductList(List<StoreProductAmountEntity> storeProductList) {
        this.storeProductList = storeProductList;
    }

    public List<StoreRetailProductAmountEntity> getStoreRetailProductList() {
        return storeRetailProductList;
    }

    public void setStoreRetailProductList(List<StoreRetailProductAmountEntity> storeRetailProductList) {
        this.storeRetailProductList = storeRetailProductList;
    }
    
    
    public Long getSalesRecordId() {
        return salesRecordId;
    }

    public void setSalesRecordId(Long salesRecordId) {
        this.salesRecordId = salesRecordId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salesRecordId != null ? salesRecordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the salesRecordId fields are not set
        if (!(object instanceof SalesRecordEntity)) {
            return false;
        }
        SalesRecordEntity other = (SalesRecordEntity) object;
        if ((this.salesRecordId == null && other.salesRecordId != null) || (this.salesRecordId != null && !this.salesRecordId.equals(other.salesRecordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SessionBean.OCRM.SalesRecordEntity[ id=" + salesRecordId + " ]";
    }
    
}
