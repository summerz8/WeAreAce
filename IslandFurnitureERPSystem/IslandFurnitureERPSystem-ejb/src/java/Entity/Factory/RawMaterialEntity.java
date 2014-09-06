package Entity.Factory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private Long rawMaterialId;
    private String name;
    private String unit;
    private String description;
    @OneToMany(mappedBy = "rawMaterial")
    private List<FactoryRawMaterialEntity> factoryRawMaterials;

    public RawMaterialEntity() {
    }

    public Long getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(Long rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FactoryRawMaterialEntity> getFactoryRawMaterials() {
        return factoryRawMaterials;
    }

    public void setFactoryRawMaterials(List<FactoryRawMaterialEntity> factoryRawMaterials) {
        this.factoryRawMaterials = factoryRawMaterials;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rawMaterialId != null ? rawMaterialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RawMaterialEntity)) {
            return false;
        }
        RawMaterialEntity other = (RawMaterialEntity) object;
        if ((this.rawMaterialId == null && other.rawMaterialId != null) || (this.rawMaterialId != null && !this.rawMaterialId.equals(other.rawMaterialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.MaterialEntity[ id=" + rawMaterialId + " ]";
    }

}
