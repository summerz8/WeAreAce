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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zhengyuan
 */
@Entity
@Table(name = "FactoryRetailProduct")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FactoryRetailProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long factoryRetailProdctId;

    private Double unrestrictedInventory = 0D;//start with 0
    private Double blockedInventory = 0D;
    private Double returnedInventory = 0D;
    private String unit;
    private String name;

    private String description;

    private Double minimumInventory = 50D;
    private Boolean isDeleted = false;
    //factory entity -- factory item entity: 1 <--> M 
    @ManyToOne
    private FactoryEntity factory;

    //factory item entity -- factory bin stored product entity: 1 <--> M 
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryRetailProduct")
    private Collection<FactoryBinStoredProductEntity> factoryBinStoredProducts = new ArrayList<>();

    //factory retail product entity -- reatail product entity: M <--> 1
    @ManyToOne
    private RetailProductEntity retailProduct;

    //contract entity -- factory retail product entity: M <--> 1
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryRetailProduct")
    private Collection<ContractEntity> contracts = new ArrayList<>();

    //retail product amount entity -- factory retail product amount entity: M <--> 1
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryRetailProduct")
    private List<FactoryRetailProductAmountEntity> factoryRetailProductAmounts = new ArrayList<>();

    //inventory record entity -- factory product entity : M <--> 1
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryRetailProduct")
    private List<InventoryRecordEntity> inventoryRecords = new ArrayList<>();

    public FactoryRetailProductEntity() {
    }

    public FactoryRetailProductEntity(String unit, String name, String description, FactoryEntity factory, RetailProductEntity retailProduct) {
        this.unit = unit;
        this.name = name;
        this.description = description;
        this.factory = factory;
        this.retailProduct = retailProduct;
    }

    public Long getFactoryRetailProdctId() {
        return factoryRetailProdctId;
    }

    public void setFactoryRetailProdctId(Long factoryRetailProdctId) {
        this.factoryRetailProdctId = factoryRetailProdctId;
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

    public Double getReturnedInventory() {
        return returnedInventory;
    }

    public void setReturnedInventory(Double returnedInventory) {
        this.returnedInventory = returnedInventory;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
//

    public RetailProductEntity getRetailProduct() {
        return retailProduct;
    }

    public void setRetailProduct(RetailProductEntity retailProduct) {
        this.retailProduct = retailProduct;
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

    public List<FactoryRetailProductAmountEntity> getFactoryRetailProductAmounts() {
        return factoryRetailProductAmounts;
    }

    public void setFactoryRetailProductAmount(List<FactoryRetailProductAmountEntity> factoryRetailProductAmount) {
        this.factoryRetailProductAmounts = factoryRetailProductAmount;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<InventoryRecordEntity> getInventoryRecords() {
        return inventoryRecords;
    }

    public void setInventoryRecords(List<InventoryRecordEntity> inventoryRecords) {
        this.inventoryRecords = inventoryRecords;
    }

//    public void create(String name, String description) {
//        setName(name);
//        setDescription(description);
//}

    
}
