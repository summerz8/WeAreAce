/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store;

import Entity.Factory.FactoryEntity;
import Entity.Factory.RetailProductEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author dan
 */
@Entity
@Table(name = "StoreRetailProduct")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StoreRetailProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeRetailProductId;

    //store retail product entity -- factory entity: M <--> 1 
    @ManyToOne
    private FactoryEntity factory;
    
    //store retail product entity -- stores entity: M <--> 1
    @ManyToOne
    private StoreEntity store;
    
    @ManyToOne
    private RetailProductEntity retailProduct;

    public StoreRetailProductEntity() {
    }

    public StoreRetailProductEntity(FactoryEntity factory, StoreEntity store) {
        this.factory = factory;
        this.store = store;
    }
    
    
    public Long getStoreRetailProductId() {
        return storeRetailProductId;
    }

    public void setStoreRetailProductId(Long storeRetailProductId) {
        this.storeRetailProductId = storeRetailProductId;
    }

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factory) {
        this.factory = factory;
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
