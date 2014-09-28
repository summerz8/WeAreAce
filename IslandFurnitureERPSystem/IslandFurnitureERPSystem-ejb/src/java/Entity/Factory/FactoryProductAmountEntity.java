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
public class FactoryProductAmountEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long factoryProductAmountId;
    private String unit;
    private Double amount;
    
    //factory product amount entity -- factory product entity: M <--> 1
    @ManyToOne
    private FactoryProductEntity factoryProduct;

    public FactoryProductAmountEntity() {
    }

    public FactoryProductAmountEntity(String unit, Double amount, FactoryProductEntity factoryProduct) {
        this.unit = unit;
        this.amount = amount;
        this.factoryProduct = factoryProduct;
    }

    public Long getFactoryProductAmountId() {
        return factoryProductAmountId;
    }

    public void setFactoryProductAmountId(Long factoryProductAmountId) {
        this.factoryProductAmountId = factoryProductAmountId;
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

    public FactoryProductEntity getFactoryProduct() {
        return factoryProduct;
    }

    public void setFactoryProduct(FactoryProductEntity factoryProduct) {
        this.factoryProduct = factoryProduct;
    }


    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factoryProductAmountId != null ? factoryProductAmountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactoryProductAmountEntity)) {
            return false;
        }
        FactoryProductAmountEntity other = (FactoryProductAmountEntity) object;
        if ((this.factoryProductAmountId == null && other.factoryProductAmountId != null) || (this.factoryProductAmountId != null && !this.factoryProductAmountId.equals(other.factoryProductAmountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.FactoryProductAmountEntity[ id=" + factoryProductAmountId + " ]";
    }
    
}
