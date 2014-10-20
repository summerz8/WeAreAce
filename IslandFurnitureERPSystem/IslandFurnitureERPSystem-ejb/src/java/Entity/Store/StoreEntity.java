/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store;

import Entity.Kitchen.KitchenEntity;
import Entity.Store.OCRM.TransactionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name = "Store")
public class StoreEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;
    private String address;
    private String country;
    private String contact;
    private String manager;//manager id
    private Boolean deleteFlag;
    private List<Long> factoryList;

    @OneToOne
    private KitchenEntity kitchen;

//    //factory entity -- store entity: M <--> M 
//    @ManyToMany(cascade = {CascadeType.PERSIST}, mappedBy = "stores")
//    private List<FactoryEntity> factorys = new ArrayList<>();
    //store entity -- store product entity: 1 <--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "store")
    private List<StoreProductEntity> storeProducts = new ArrayList<>();

    //store entity -- store retail product entity: 1 <--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "store")
    private List<StoreRetailProductEntity> storeRetailProducts = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "store")
    private List<StoreItemMappingEntity> storeItemMappings = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "store")
    private List<TransactionEntity> transacions = new ArrayList<>();

    public StoreEntity() {
    }

    public StoreEntity(String address, String country, String contact, String manager, Boolean deleteFlag) {
        this.address = address;
        this.country = country;
        this.contact = contact;
        this.manager = manager;
        this.deleteFlag = deleteFlag;
        factoryList = new ArrayList<>();

    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<Long> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(List<Long> factoryList) {
        this.factoryList = factoryList;
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public List<StoreProductEntity> getStoreProducts() {
        return storeProducts;
    }

    public void setStoreProducts(List<StoreProductEntity> storeProducts) {
        this.storeProducts = storeProducts;
    }

    public List<StoreRetailProductEntity> getStoreRetailProducts() {
        return storeRetailProducts;
    }

    public void setStoreRetailProducts(List<StoreRetailProductEntity> storeRetailProducts) {
        this.storeRetailProducts = storeRetailProducts;
    }

    public List<StoreItemMappingEntity> getStoreItemMappings() {
        return storeItemMappings;
    }

    public void setStoreItemMappings(List<StoreItemMappingEntity> storeItemMappings) {
        this.storeItemMappings = storeItemMappings;
    }

    public List<TransactionEntity> getTransacions() {
        return transacions;
    }

    public void setTransacions(List<TransactionEntity> transacions) {
        this.transacions = transacions;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeId != null ? storeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the storeId fields are not set
        if (!(object instanceof StoreEntity)) {
            return false;
        }
        StoreEntity other = (StoreEntity) object;
        if ((this.storeId == null && other.storeId != null) || (this.storeId != null && !this.storeId.equals(other.storeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.StoreEntity[ id=" + storeId + " ]";
    }

}
