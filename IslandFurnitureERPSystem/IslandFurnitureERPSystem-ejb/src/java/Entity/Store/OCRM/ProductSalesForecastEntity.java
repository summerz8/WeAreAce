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
 * @author apple
 */
@Entity
public class ProductSalesForecastEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //product sales forecast -- store M --> 1
    @ManyToOne
    private StoreEntity store;
    
    //product sales forecast -- store product M <--> 1
    @ManyToOne
    private StoreProductEntity storeProduct;

    //product sales forecast  -- store retail product M <--> 1
    @ManyToOne
    private StoreRetailProductEntity storeRetailProduct;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar period;
    private Double amount;  //amount planned to be sold in the target month
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Calendar getPeriod() {
        return period;
    }

    public void setPeriod(Calendar period) {
        this.period = period;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
        if (!(object instanceof ProductSalesForecastEntity)) {
            return false;
        }
        ProductSalesForecastEntity other = (ProductSalesForecastEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.ProductSalesForecast[ id=" + id + " ]";
    }
    
}
