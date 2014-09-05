/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Yoky
 */
@Entity
public class MaterialEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long materialId;
    private String name;
    private char type;
    private String description;
    private double lotSize;
    private String unit;
    private double inventoryBalance;
    private BOMEntity bom;
    private List<SupplierEntity> suppliers;
    private List<StorageBinEntity> storageBins;
    private String remark;

    public MaterialEntity() {
    }
      
    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLotSize() {
        return lotSize;
    }

    public void setLotSize(double lotSize) {
        this.lotSize = lotSize;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getInventoryBalance() {
        return inventoryBalance;
    }

    public void setInventoryBalance(double inventoryBalance) {
        this.inventoryBalance = inventoryBalance;
    }
    
    public BOMEntity getBom() {
        return bom;
    }

    public void setBom(BOMEntity bom) {
        this.bom = bom;
    }

    public List<SupplierEntity> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierEntity> suppliers) {
        this.suppliers = suppliers;
    }
 
    public List<StorageBinEntity> getStorageBins() {
        return storageBins;
    }

    public void setStorageBins(List<StorageBinEntity> storageBins) {
        this.storageBins = storageBins;
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
        hash += (materialId != null ? materialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the materialId fields are not set
        if (!(object instanceof MaterialEntity)) {
            return false;
        }
        MaterialEntity other = (MaterialEntity) object;
        if ((this.materialId == null && other.materialId != null) || (this.materialId != null && !this.materialId.equals(other.materialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.MaterialEntity[ id=" + materialId + " ]";
    }
    
}
