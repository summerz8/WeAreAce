/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author apple
 */
@Entity
public class StoredProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long StoredProductID;
    private Double amount;
    private RawMaterialEntity product;
    
    
    public Long getId() {
        return StoredProductID;
    }

    public void setId(Long id) {
        this.StoredProductID = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (StoredProductID != null ? StoredProductID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoredProductEntity)) {
            return false;
        }
        StoredProductEntity other = (StoredProductEntity) object;
        if ((this.StoredProductID == null && other.StoredProductID != null) || (this.StoredProductID != null && !this.StoredProductID.equals(other.StoredProductID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.StoredProduct[ id=" + StoredProductID + " ]";
    }
    
}
