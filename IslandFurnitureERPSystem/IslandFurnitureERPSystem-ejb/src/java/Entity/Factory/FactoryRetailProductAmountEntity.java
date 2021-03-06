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
import javax.persistence.ManyToOne;

/**
 *
 * @author apple
 */
@Entity
public class FactoryRetailProductAmountEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long factoryRetailProductAmountId;
    private String unit;
    private Double amount;
    
    //retail product amount entity -- factory retail product amount entity: M <--> 1
    @ManyToOne
    private FactoryRetailProductEntity factoryRetailProduct;

    public FactoryRetailProductAmountEntity() {
    }

    public FactoryRetailProductAmountEntity(String unit, Double amount, FactoryRetailProductEntity factoryRetailProduct) {
        this.unit = unit;
        this.amount = amount;
        this.factoryRetailProduct = factoryRetailProduct;
    }
    
    
    public Long getFactoryRetailProductAmountId() {
        return factoryRetailProductAmountId;
    }

    public void setFactoryRetailProductAmountId(Long factoryRetailProductAmountId) {
        this.factoryRetailProductAmountId = factoryRetailProductAmountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factoryRetailProductAmountId != null ? factoryRetailProductAmountId.hashCode() : 0);
        return hash;
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

    public FactoryRetailProductEntity getFactoryRetailProduct() {
        return factoryRetailProduct;
    }

    public void setFactoryRetailProduct(FactoryRetailProductEntity factoryRetailProduct) {
        this.factoryRetailProduct = factoryRetailProduct;
    }

    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the factoryRetailProductAmountId fields are not set
        if (!(object instanceof FactoryRetailProductAmountEntity)) {
            return false;
        }
        FactoryRetailProductAmountEntity other = (FactoryRetailProductAmountEntity) object;
        if ((this.factoryRetailProductAmountId == null && other.factoryRetailProductAmountId != null) || (this.factoryRetailProductAmountId != null && !this.factoryRetailProductAmountId.equals(other.factoryRetailProductAmountId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Entity.Factory.FactoryRetailProductAmountEntity[ id=" + factoryRetailProductAmountId + " ]";
    }
    
}
