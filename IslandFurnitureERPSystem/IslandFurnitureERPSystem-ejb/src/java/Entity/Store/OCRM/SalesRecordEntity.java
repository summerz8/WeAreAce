/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    
       //Sales record -- store M-->1
    @ManyToOne
    private StoreEntity store;
    
    //Sales record -- store product M <--> 1
    @ManyToOne
    private StoreProductEntity storeProduct;

    //Sales record -- store retail product M <--> 1
    @ManyToOne
    private StoreRetailProductEntity storeRetailProduct;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar recordPeriod;
    private Double amount;  //sold amount for this product in this month
    private Double revenue; //earned revenue for this product in this month
    
    public SalesRecordEntity() {
        amount=0D;
        revenue=0D;
        recordPeriod=Calendar.getInstance();
    }

    public Long getSalesRecordId() {
        return salesRecordId;
    }

    public void setSalesRecordId(Long salesRecordId) {
        this.salesRecordId = salesRecordId;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public StoreProductEntity getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(StoreProductEntity storeProduct) {
        this.storeProduct = storeProduct;
    }

    public StoreRetailProductEntity getStoreRetailProduct() {
        return storeRetailProduct;
    }

    public void setStoreRetailProduct(StoreRetailProductEntity storeRetailProduct) {
        this.storeRetailProduct = storeRetailProduct;
    }

    public Calendar getRecordPeriod() {
        return recordPeriod;
    }

    public void setRecordPeriod(Calendar recordPeriod) {
        this.recordPeriod = recordPeriod;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
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
