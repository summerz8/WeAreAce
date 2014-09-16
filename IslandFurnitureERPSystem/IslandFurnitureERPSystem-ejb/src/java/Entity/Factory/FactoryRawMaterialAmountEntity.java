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
import javax.persistence.Table;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "RawMaterialamount")
public class FactoryRawMaterialAmountEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rawMaterialAmountId;
    private String unit;
    private Double amount;
    
    //raw material amount entity -- factory raw material entity: M --> 1
    @ManyToOne
    private FactoryRawMaterialEntity factoryRawMaterial;
    
  
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

    public Long getRawMaterialAmountId() {
        return rawMaterialAmountId;
    }

    public void setRawMaterialAmountId(Long rawMaterialAmountId) {
        this.rawMaterialAmountId = rawMaterialAmountId;
    }

    public FactoryRawMaterialEntity getFactoryRawMaterial() {
        return factoryRawMaterial;
    }

    public void setFactoryRawMaterial(FactoryRawMaterialEntity factoryRawMaterial) {
        this.factoryRawMaterial = factoryRawMaterial;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rawMaterialAmountId != null ? rawMaterialAmountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the rawMaterialAmountId fields are not set
        if (!(object instanceof FactoryRawMaterialAmountEntity)) {
            return false;
        }
        FactoryRawMaterialAmountEntity other = (FactoryRawMaterialAmountEntity) object;
        if ((this.rawMaterialAmountId == null && other.rawMaterialAmountId != null) || (this.rawMaterialAmountId != null && !this.rawMaterialAmountId.equals(other.rawMaterialAmountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.RawMaterialAmountEntity[ id=" + rawMaterialAmountId + " ]";
    }

}
