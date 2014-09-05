/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author apple
 */
@Entity
public class RawMaterialEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long MaterialID;
    private String materialName;
    private String description;
   // private List<SupplierEntity> supplier;
    
    
    public Long getMaterialID() {
        return MaterialID;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getDescription() {
        return description;
    }

//    public List<SupplierEntity> getSupplier() {
//        return supplier;
//    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
//
//    public void setSupplier(List<SupplierEntity> supplier) {
////        this.supplier = supplier;
//    }
    
    

    public void setMaterialID(Long MaterialID) {
        this.MaterialID = MaterialID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (MaterialID != null ? MaterialID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RawMaterialEntity)) {
            return false;
        }
        RawMaterialEntity other = (RawMaterialEntity) object;
        if ((this.MaterialID == null && other.MaterialID != null) || (this.MaterialID != null && !this.MaterialID.equals(other.MaterialID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.MaterialEntity[ id=" + MaterialID + " ]";
    }
    
}
