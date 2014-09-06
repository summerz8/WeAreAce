package Entity.Factory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entity.Factory.SCM.SupplierEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "RawMaterial")
public class RawMaterialEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long materialID;
    private String materialName;
    private String description;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "RawMaterial_Supplier")
    private List<SupplierEntity> supplier;

    public Long getMaterialID() {
        return materialID;
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

    public List<SupplierEntity> getSupplier() {
        return supplier;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSupplier(List<SupplierEntity> supplier) {
        this.supplier = supplier;
    }

    public void setMaterialID(Long MaterialID) {
        this.materialID = MaterialID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialID != null ? materialID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RawMaterialEntity)) {
            return false;
        }
        RawMaterialEntity other = (RawMaterialEntity) object;
        if ((this.materialID == null && other.materialID != null) || (this.materialID != null && !this.materialID.equals(other.materialID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.MaterialEntity[ id=" + materialID + " ]";
    }

}
