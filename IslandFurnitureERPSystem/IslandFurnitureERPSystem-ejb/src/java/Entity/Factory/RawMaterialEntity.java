package Entity.Factory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
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
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long materialId;
    private String materialName;
    private String description;
    private Boolean deleteFlag;
    private String unit;
    
    //raw material entity -- factory raw material entity: 1<--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "rawMaterial")
    private Collection<FactoryRawMaterialEntity> factoryRawMaterials = new ArrayList<>();

    //raw material entity -- bom entity:    1 <--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "rawMaterial")
    private List<BOMEntity> bomList= new ArrayList<>();;
    
    public RawMaterialEntity() {
    }

    public RawMaterialEntity(String materialName, String description, Boolean deleteFlag, String unit) {
        this.materialName = materialName;
        this.description = description;
        this.deleteFlag = deleteFlag;
        this.unit = unit;
    }
    
    public Long getMaterialId() {
        return materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getDescription() {
        return description;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaterialId(Long MaterialID) {
        this.materialId = MaterialID;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public Collection<FactoryRawMaterialEntity> getFactoryRawMaterials() {
        return factoryRawMaterials;
    }

    public void setFactoryRawMaterials(Collection<FactoryRawMaterialEntity> factoryRawMaterials) {
        this.factoryRawMaterials = factoryRawMaterials;
    }

    public List<BOMEntity> getBomList() {
        return bomList;
    }

    public void setBomList(List<BOMEntity> bomList) {
        this.bomList = bomList;
    }

    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
   
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialId != null ? materialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RawMaterialEntity)) {
            return false;
        }
        RawMaterialEntity other = (RawMaterialEntity) object;
        if ((this.materialId == null && other.materialId != null) || (this.materialId != null && !this.materialId.equals(other.materialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.MaterialEntity[ id=" + materialId + " ]";
    }

}
