/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory;


import Entity.Factory.FactoryBin.FactoryBinStoredProductEntity;
import Entity.Factory.SCM.ContractEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zhengyuan
 */
@Entity
@Table(name = "FactoryRawMaterial")
public class FactoryRawMaterialEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long factoryRawMaterialId;

    private Double unrestrictedInventory = 0D;//start with 0
    private Double blockedInventory = 0D;
    private String unit;
    private String materialName;

    private String description;
    private Double minimumInventory = 50D;
    private Boolean deleteFlag = false;

    //factory entity -- factory raw material entity: 1 <--> M 
    @ManyToOne
    private FactoryEntity factory;

    //factory raw material entity -- factory bin stored product entity: 1 <--> M 
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryRawMaterial")
    private Collection<FactoryBinStoredProductEntity> factoryBinStoredProducts = new ArrayList<>();

    //factory raw material entity -- raw material entity: M <--> 1
    @ManyToOne
    private RawMaterialEntity rawMaterial;
    
    //contract entity -- factory raw material entity: M <--> 1
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryRawMaterial")
    private Collection<ContractEntity> contracts = new ArrayList<>();

    //inventory record entity -- factory raw material entity : M <--> 1
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryRawMaterial")
    private List<InventoryRecordEntity> inventoryRecord = new ArrayList<>();
    
    public FactoryRawMaterialEntity() {
    }

    public FactoryRawMaterialEntity(String unit, String materialName, String description, Boolean deleteFlag, FactoryEntity factory, RawMaterialEntity rawMaterial) {
        this.unit = unit;
        this.materialName = materialName;
        this.description = description;
        this.deleteFlag = deleteFlag;
        this.factory = factory;
        this.rawMaterial = rawMaterial;
    }
   
    public Long getFactoryRawMaterialId() {
        return factoryRawMaterialId;
    }

    public void setFactoryRawMaterialId(Long factoryRawMaterialId) {
        this.factoryRawMaterialId = factoryRawMaterialId;
    }

    public Double getUnrestrictedInventory() {
        return unrestrictedInventory;
    }

    public void setUnrestrictedInventory(Double unrestrictedInventory) {
        this.unrestrictedInventory = unrestrictedInventory;
    }

    public Double getBlockedInventory() {
        return blockedInventory;
    }

    public void setBlockedInventory(Double blockedInventory) {
        this.blockedInventory = blockedInventory;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factory) {
        this.factory = factory;
    }
//
    public Collection<FactoryBinStoredProductEntity> getFactoryBinStoredProducts() {
        return factoryBinStoredProducts;
    }

    public void setFactoryBinStoredProducts(Collection<FactoryBinStoredProductEntity> factoryBinStoredProducts) {
        this.factoryBinStoredProducts = factoryBinStoredProducts;
    }

    public RawMaterialEntity getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterialEntity rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public Collection<ContractEntity> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<ContractEntity> contracts) {
        this.contracts = contracts;
    }

    public Double getMinimumInventory() {
        return minimumInventory;
    }

    public void setMinimumInventory(Double minimumInventory) {
        this.minimumInventory = minimumInventory;
    }

    public List<InventoryRecordEntity> getInventoryRecord() {
        return inventoryRecord;
    }

    public void setInventoryRecord(List<InventoryRecordEntity> inventoryRecord) {
        this.inventoryRecord = inventoryRecord;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "FactoryRawMaterialEntity{" + "factoryRawMaterialId=" + factoryRawMaterialId + ", unit=" + unit + ", materialName=" + materialName + ", description=" + description + ", minimumInventory=" + minimumInventory + ", deleteFlag=" + deleteFlag + ", factory=" + factory + ", rawMaterial=" + rawMaterial + '}';
    }
    
}
