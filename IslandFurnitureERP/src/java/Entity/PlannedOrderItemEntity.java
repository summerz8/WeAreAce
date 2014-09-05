/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

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
public class PlannedOrderItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long PlannedOrderItemId;
    private MaterialEntity material;
    private double quantity;
    private String remark;

    public PlannedOrderItemEntity() {
    }

    public Long getPlannedOrderItemId() {
        return PlannedOrderItemId;
    }

    public void setPlannedOrderItemId(Long PlannedOrderItemId) {
        this.PlannedOrderItemId = PlannedOrderItemId;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (PlannedOrderItemId != null ? PlannedOrderItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the PlannedOrderItemId fields are not set
        if (!(object instanceof PlannedOrderItemEntity)) {
            return false;
        }
        PlannedOrderItemEntity other = (PlannedOrderItemEntity) object;
        if ((this.PlannedOrderItemId == null && other.PlannedOrderItemId != null) || (this.PlannedOrderItemId != null && !this.PlannedOrderItemId.equals(other.PlannedOrderItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PlannedOrderItemEntity[ id=" + PlannedOrderItemId + " ]";
    }
    
}
