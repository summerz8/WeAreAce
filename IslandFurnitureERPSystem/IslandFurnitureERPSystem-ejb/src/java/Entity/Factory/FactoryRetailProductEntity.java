/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory;

import Entity.Factory.FacotryBin.FactoryBinStoredProductEntity;
import Entity.Factory.SCM.ContractEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryRetailProdctId;
    private Integer quantity = 0;//start with 0
    private String name;    // can get from retailProduct
    private String description;
    
    private Integer minimumInventory = 50;

    //factory entity -- factory item entity: 1 <--> M 
    @ManyToOne
    private FactoryEntity factory;

    //factory item entity -- factory bin stored product entity: 1 <--> M 
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryRetailProduct")
    private Collection<FactoryBinStoredProductEntity> factoryBinStoredProducts = new ArrayList<FactoryBinStoredProductEntity>();
    
    //factory retail product entity -- reatail product entity: M <--> 1
    @ManyToOne
    private RetailProductEntity retailProduct;

    //contract entity -- factory retail product entity: M <--> 1
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "retailProduct")
    private Collection<ContractEntity> contracts = new ArrayList<>();

    public FactoryRetailProductEntity(){
        //may be changed later
        setFactoryRetailProdctId(System.nanoTime());
    }

    public Long getFactoryRetailProdctId() {
        return factoryRetailProdctId;
    }

    public void setFactoryRetailProdctId(Long factoryRetailProdctId) {
        this.factoryRetailProdctId = factoryRetailProdctId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Collection<FactoryBinStoredProductEntity> getFactoryBinStoredProducts() {
        return factoryBinStoredProducts;
    }

    public void setFactoryBinStoredProducts(Collection<FactoryBinStoredProductEntity> factoryBinStoredProducts) {
        this.factoryBinStoredProducts = factoryBinStoredProducts;
    }

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

    public Integer getMinimumInventory() {
        return minimumInventory;
    }

    public void setMinimumInventory(Integer minimumInventory) {
        this.minimumInventory = minimumInventory;
    }
    
    public void create(String name, String description){
        setName(name);
        setDescription(description);
    }

}
