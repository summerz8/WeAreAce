/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import util.security.CreateID;

/**
 *
 * @author hangsun
 */
@Entity
public class StoreItemMappingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    
    private Long retailProductId;
    private Long productid;
    
    @ManyToOne
    private StoreEntity store;
    
    public StoreItemMappingEntity(){
        this.id =   Long.parseLong(CreateID.generate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRetailProductId() {
        return retailProductId;
    }

    public Long getProductId() {
        return productid;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setRetailProductId(Long retailProductId) {
        this.retailProductId = retailProductId;
    }

    public void setProductId(Long productid) {
        this.productid = productid;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
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
        if (!(object instanceof StoreItemMappingEntity)) {
            return false;
        }
        StoreItemMappingEntity other = (StoreItemMappingEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.StoreProductMappingEntity[ id=" + id + " ]";
    }
    
}
