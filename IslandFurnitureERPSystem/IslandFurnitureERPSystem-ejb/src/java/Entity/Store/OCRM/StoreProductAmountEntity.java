/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

import Entity.Store.StoreProductEntity;
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
public class StoreProductAmountEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long storeProductAmountId;
    private String unit;
    private Double amount;
    
    //factory product amount entity -- factory product entity: M <--> 1
    @ManyToOne
    private StoreProductEntity storeProduct;

    public StoreProductAmountEntity() {
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

    public StoreProductEntity getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(StoreProductEntity storeProduct) {
        this.storeProduct = storeProduct;
    }

    public Long getStoreProductAmountId() {
        return storeProductAmountId;
    }

    public void setStoreProductAmountId(Long storeProductAmountId) {
        this.storeProductAmountId = storeProductAmountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeProductAmountId != null ? storeProductAmountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the storeProductAmountId fields are not set
        if (!(object instanceof StoreProductAmountEntity)) {
            return false;
        }
        StoreProductAmountEntity other = (StoreProductAmountEntity) object;
        if ((this.storeProductAmountId == null && other.storeProductAmountId != null) || (this.storeProductAmountId != null && !this.storeProductAmountId.equals(other.storeProductAmountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.StoreProductAmountEntity[ id=" + storeProductAmountId + " ]";
    }
    
}
