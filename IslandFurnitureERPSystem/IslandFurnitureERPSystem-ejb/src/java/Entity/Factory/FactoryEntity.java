/*
 * FactoryEntity.java
 *
 * This is a real entity for the factory.
 * It's an entity of factory.
 */
package Entity.Factory;

import Entity.Factory.FactoryBin.FactoryBinEntity;
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
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "factory")
    private Collection<FactoryBinEntity> factoryBins = new ArrayList<>();

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

    @Override
    public String toString() {
        return "entity.UserEntity[ id=" + factoryId + " ]";
    }

}
