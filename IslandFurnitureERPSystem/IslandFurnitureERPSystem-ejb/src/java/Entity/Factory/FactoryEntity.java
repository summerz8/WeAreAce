/*
 * FactoryEntity.java
 *
 * This is a real entity for the factory.
 * It's an entity of factory.
 */
package Entity.Factory;

import Entity.Factory.FactoryBin.FactoryBinEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long factoryId;
  
    private String country;
    private String address;
    private String contact;
    private String manager; // manager id
    private Boolean deleteFlag;//a flag used to mark as deleted or not
    
    //purchase order entity -- factory entity: M <--> 1 
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factory")
    private Collection<PurchaseOrderEntity> purchaseOrders = new ArrayList<>();

    //factory entity -- factory raw material entity: 1 <--> M 
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factory")
    private Collection<FactoryRawMaterialEntity> factoryRawMaterials = new ArrayList<>();

    //factory entity -- factory product entity: 1 <--> M 
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factory")
    private Collection<FactoryProductEntity> factoryProducts = new ArrayList<>();

    //factory entity -- factory retail product entity: 1 <--> M 
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factory")
    private Collection<FactoryRetailProductEntity> factoryRetailProducts = new ArrayList<>();

    //facotry entity -- factory bin entity: 1 <--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factory")
    private Collection<FactoryBinEntity> factoryBins = new ArrayList<>();
//    
//    //factory entity -- store entity: M <--> M 
//    @ManyToMany(cascade = {CascadeType.PERSIST})
//    @JoinTable(name = "FACTORY_STORE")
//    private List<StoreEntity> stores = new ArrayList<>();
    
    
    
    //facotry entity -- planned order entity: 1 <--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factory")
    private List<PlannedOrderEntity> plannedOrders = new ArrayList<>();
    
    //facotry entity -- integrated planned order entity: 1 <--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy="factory")
    private List<IntegratedPlannedOrderEntity> integratedPlannedOrders = new ArrayList<>();
    
    public FactoryEntity() {
    }

    public FactoryEntity(String country, String address, String contact, String manager, Boolean deleteFlag) {
        this.country = country;
        this.address = address;
        this.contact = contact;
        this.manager = manager;
        this.deleteFlag = deleteFlag;
    }

    
    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
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

    public String getManagerId() {
        return manager;
    }

    public void setManagerId(String manager) {
        this.manager = manager;
    }

    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    
    public List<PlannedOrderEntity> getPlannedOrder() {
        return plannedOrders;
    }

    public void setPlannedOrder(List<PlannedOrderEntity> plannedOrder) {
        this.plannedOrders = plannedOrder;
    }

    public List<PlannedOrderEntity> getPlannedOrders() {
        return plannedOrders;
    }

    public void setPlannedOrders(List<PlannedOrderEntity> plannedOrders) {
        this.plannedOrders = plannedOrders;
    }

    public List<IntegratedPlannedOrderEntity> getIntegratedPlannedOrders() {
        return integratedPlannedOrders;
    }

    public void setIntegratedPlannedOrders(List<IntegratedPlannedOrderEntity> integratedPlannedOrders) {
        this.integratedPlannedOrders = integratedPlannedOrders;
    }

    
    @Override
    public String toString() {
        return "entity.FactoryEntity[ id=" + factoryId + ", " + "FactoryAddress = " + address + "]";
    }

}
