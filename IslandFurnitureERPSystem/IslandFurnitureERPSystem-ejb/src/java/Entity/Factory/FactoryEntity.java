/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

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
@Table(name = "factory")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FactoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long factoryID;
    private String country;
    private String address;
    private String contact;
    private String manager;
    private Collection<FactoryBinInventoryEntity> factoryBinFinishedGood = new ArrayList<FactoryBinInventoryEntity>();
    private Collection<FactoryBinRawMaterialEntity> factoryBinRawMaterial = new ArrayList<FactoryBinRawMaterialEntity>();
    private Collection<FactoryBinRetailProductEntity> factoryBinRetailProduct = new ArrayList<FactoryBinRetailProductEntity>();
    
    public FactoryEntity() {
    }
    
    public long getFactoryId() {
        return factoryID;
    }

    public void setUserId(long factoryID) {
        this.factoryID = factoryID;
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

     
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="factory")
    public Collection<FactoryBinInventoryEntity> getFactoryBinFinishedGood(){
        return factoryBinFinishedGood;
    }
    
    public void setFactoryBinFinishedGood(Collection<FactoryBinInventoryEntity> factoryBinFinishedGood){
        this.factoryBinFinishedGood = factoryBinFinishedGood; 
    }
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="factory")
    public Collection<FactoryBinRawMaterialEntity> getFactoryBinRawMaterial(){
        return factoryBinRawMaterial;
    }
    
    public void setFactoryBinRawMaterial(Collection<FactoryBinRawMaterialEntity> factoryBinRawMaterial){
        this.factoryBinRawMaterial = factoryBinRawMaterial; 
    }
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="factory")
    public Collection<FactoryBinRetailProductEntity> getFactoryBinRetailProduct(){
        return factoryBinRetailProduct;
    }
    
    public void setFactoryBinRetailProduct(Collection<FactoryBinRetailProductEntity> factoryBinRetailProduct){
        this.factoryBinRetailProduct = factoryBinRetailProduct; 
    }
    
    @Override
    public String toString() {
        return "entity.UserEntity[ id=" + factoryID + " ]";
    }
    
}
