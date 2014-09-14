/*
 * FactoryEntity.java
 *
 * This is a real entity for the factory.
 * It's an entity of factory.
 */
package Entity.Factory;

import Entity.Factory.FactoryBin.FactoryBinEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import static Entity.Factory.MRP.ProductionPlanEntity_.plannedOrder;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Store.StoreEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name = "Factory")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FactoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long factoryId;
    private String country;
    private String address;
    private String contact;
    private String manager; // managerEntity
    
    //purchase order entity -- factory entity: M <--> 1 
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "factory")
    private Collection<PurchaseOrderEntity> purchaseOrders = new ArrayList<>();

    //factory entity -- factory raw material entity: 1 <--> M 
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "factory")
    private Collection<FactoryRawMaterialEntity> factoryRawMaterials = new ArrayList<>();

    //factory entity -- factory product entity: 1 <--> M 
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "factory")
    private Collection<FactoryProductEntity> factoryProducts = new ArrayList<>();

    //factory entity -- factory retail product entity: 1 <--> M 
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "factory")
    private Collection<FactoryRetailProductEntity> factoryRetailProducts = new ArrayList<>();

    //facotry entity -- factory bin entity: 1 <--> M
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "factory")
    private Collection<FactoryBinEntity> factoryBins = new ArrayList<>();
    
    //factory entity -- store entity: M <--> M 
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "FACTORY_STORE")
    private Set<StoreEntity> stores = new HashSet<>();
    
    @OneToMany(mappedBy="factory")
    private List<PlannedOrderEntity> plannedOrder;
    
    public FactoryEntity() {
    }

    public long getFactoryId() {
        return factoryId;
    }

    public void setUserId(long factoryID) {
        this.factoryId = factoryID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Collection<PurchaseOrderEntity> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(Collection<PurchaseOrderEntity> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    
    public Collection<FactoryRawMaterialEntity> getFactoryRawMaterials() {
        return factoryRawMaterials;
    }

    public void setFactoryRawMaterials(Collection<FactoryRawMaterialEntity> factoryRawMaterials) {
        this.factoryRawMaterials = factoryRawMaterials;
    }

    public Collection<FactoryProductEntity> getFactoryProducts() {
        return factoryProducts;
    }

    public void setFactoryProducts(Collection<FactoryProductEntity> factoryProducts) {
        this.factoryProducts = factoryProducts;
    }

    public Collection<FactoryRetailProductEntity> getFactoryRetailProducts() {
        return factoryRetailProducts;
    }

    public void setFactoryRetailProducts(Collection<FactoryRetailProductEntity> factoryRetailProducts) {
        this.factoryRetailProducts = factoryRetailProducts;
    }


    public Collection<FactoryBinEntity> getFactoryBins() {
        return factoryBins;
    }

    public void setFactoryBins(Collection<FactoryBinEntity> factoryBins) {
        this.factoryBins = factoryBins;
    }

    public Set<StoreEntity> getStores() {
        return stores;
    }

    public void setStores(Set<StoreEntity> stores) {
        this.stores = stores;
    }

    public List<PlannedOrderEntity> getPlannedOrder() {
        return plannedOrder;
    }

    public void setPlannedOrder(List<PlannedOrderEntity> plannedOrder) {
        this.plannedOrder = plannedOrder;
    }
    
    @Override
    public String toString() {
        return "entity.UserEntity[ id=" + factoryId + " ]";
    }

}
