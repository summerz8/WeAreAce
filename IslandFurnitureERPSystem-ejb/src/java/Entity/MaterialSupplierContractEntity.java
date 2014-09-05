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
public class MaterialSupplierContractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long materialSupplierContractId;
    private MaterialEntity material;
    private SupplierEntity supplier;
    private double price;
    private int leadtime;

    public Long getMaterialSupplierContractId() {
        return materialSupplierContractId;
    }

    public void setMaterialSupplierContractId(Long materialSupplierContractId) {
        this.materialSupplierContractId = materialSupplierContractId;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLeadtime() {
        return leadtime;
    }

    public void setLeadtime(int leadtime) {
        this.leadtime = leadtime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialSupplierContractId != null ? materialSupplierContractId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the materialSupplierContractId fields are not set
        if (!(object instanceof MaterialSupplierContractEntity)) {
            return false;
        }
        MaterialSupplierContractEntity other = (MaterialSupplierContractEntity) object;
        if ((this.materialSupplierContractId == null && other.materialSupplierContractId != null) || (this.materialSupplierContractId != null && !this.materialSupplierContractId.equals(other.materialSupplierContractId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.MaterialSupplierContractEntity[ id=" + materialSupplierContractId + " ]";
    }
    
}
