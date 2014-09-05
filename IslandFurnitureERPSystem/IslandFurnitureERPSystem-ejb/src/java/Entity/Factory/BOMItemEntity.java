/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import Entity.Factory.SCM.RawMaterialEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Yoky
 */
@Entity
public class BOMItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long BOMItemId;
    private RawMaterialEntity material;
    private double quantity;
    private String description;

    public BOMItemEntity() {
    }

    public Long getBOMItemId() {
        return BOMItemId;
    }

    public void setBOMItemId(Long BOMItemId) {
        this.BOMItemId = BOMItemId;
    }

    public RawMaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(RawMaterialEntity material) {
        this.material = material;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (BOMItemId != null ? BOMItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the BOMItemId fields are not set
        if (!(object instanceof BOMItemEntity)) {
            return false;
        }
        BOMItemEntity other = (BOMItemEntity) object;
        if ((this.BOMItemId == null && other.BOMItemId != null) || (this.BOMItemId != null && !this.BOMItemId.equals(other.BOMItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.BOMItemEntity[ id=" + BOMItemId + " ]";
    }
    
}
