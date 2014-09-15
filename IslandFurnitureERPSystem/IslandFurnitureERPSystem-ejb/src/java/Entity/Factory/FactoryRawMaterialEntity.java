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

    private Integer inventory = 0;//start with 0
    private String materialName;

    private String description;
    private Integer minimumInventory = 50;

    //factory entity -- factory item entity: 1 <--> M 
    @ManyToOne
    private FactoryEntity factory;

    //factory raw material entity -- factory bin stored product entity: 1 <--> M 
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryRawMaterial")
    private Collection<FactoryBinStoredProductEntity> factoryBinStoredProducts = new ArrayList<>();

    //factory raw material entity -- raw material entity: M <--> 1
    @ManyToOne
    private RawMaterialEntity rawMaterial;
    
    //raw material amount entity -- factory raw material entity: M <--> 1
    @OneToMany
    private Collection<RawMaterialAmountEntity> rawMaterialAmounts = new ArrayList<>();

    //contract entity -- factory raw material entity: M <--> 1
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryRawMaterialProduct")
    private Collection<ContractEntity> contracts = new ArrayList<>();

    public FactoryRawMaterialEntity() {
        //may be changed later
        setFactoryRawMaterialId(System.nanoTime());
    }

    public Long getFactoryRawMaterialId() {
        return factoryRawMaterialId;
    }

    public void setFactoryRawMaterialId(Long factoryRawMaterialId) {
        this.factoryRawMaterialId = factoryRawMaterialId;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
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

    public Integer getMinimumInventory() {
        return minimumInventory;
    }

    public void setMinimumInventory(Integer minimumInventory) {
        this.minimumInventory = minimumInventory;
    }

    public Collection<RawMaterialAmountEntity> getRawMaterialAmounts() {
        return rawMaterialAmounts;
    }

    public void setRawMaterialAmounts(Collection<RawMaterialAmountEntity> rawMaterialAmounts) {
        this.rawMaterialAmounts = rawMaterialAmounts;
    }


    public void create(String materialName, String description) {
        setMaterialName(materialName);
        setDescription(description);
    }

}
