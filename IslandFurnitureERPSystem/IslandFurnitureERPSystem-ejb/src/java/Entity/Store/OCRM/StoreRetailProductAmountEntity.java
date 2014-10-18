/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

import Entity.Store.StoreRetailProductEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author dan
 */
@Entity
public class StoreRetailProductAmountEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeRetailProductAmountId;
    
    private String unit;
    private Double amount;
    
    //retail product amount entity -- factory retail product amount entity: M <--> 1
    @ManyToOne
    private StoreRetailProductEntity storeRetailProduct;

    public StoreRetailProductAmountEntity() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public StoreRetailProductEntity getStoreRetailProduct() {
        return storeRetailProduct;
    }

    public void setStoreRetailProduct(StoreRetailProductEntity storeRetailProduct) {
        this.storeRetailProduct = storeRetailProduct;
    }

    public Long getStoreRetailProductAmountId() {
        return storeRetailProductAmountId;
    }

    public void setStoreRetailProductAmountId(Long storeRetailProductAmountId) {
        this.storeRetailProductAmountId = storeRetailProductAmountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeRetailProductAmountId != null ? storeRetailProductAmountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the storeRetailProductAmountId fields are not set
        if (!(object instanceof StoreRetailProductAmountEntity)) {
            return false;
        }
        StoreRetailProductAmountEntity other = (StoreRetailProductAmountEntity) object;
        if ((this.storeRetailProductAmountId == null && other.storeRetailProductAmountId != null) || (this.storeRetailProductAmountId != null && !this.storeRetailProductAmountId.equals(other.storeRetailProductAmountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.StoreRetailProductAmountEntity[ id=" + storeRetailProductAmountId + " ]";
    }
    
}
