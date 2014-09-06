/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory.FacotryBin;

import Entity.Factory.RawMaterialEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author apple
 */
@Entity
        @Table(name="FactoryStoredProduct")
public class FactoryBinStoredProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long storedProductId;
    private double amount;
    private RawMaterialEntity product;
    
    
    public Long getStoredProductId() {
        return storedProductId;
    }

    public void setStoredProductId(Long id) {
        this.storedProductId = id;
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storedProductId != null ? storedProductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactoryBinStoredProductEntity)) {
            return false;
        }
        FactoryBinStoredProductEntity other = (FactoryBinStoredProductEntity) object;
        if ((this.storedProductId == null && other.storedProductId != null) || (this.storedProductId != null && !this.storedProductId.equals(other.storedProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.StoredProduct[ id=" + storedProductId + " ]";
    }
    
}
