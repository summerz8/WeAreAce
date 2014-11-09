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
    private List<StoreProductEntity> storeProduct = new ArrayList<>();

    //store entity -- store retail product entity: 1 <--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "store")
    private List<StoreRetailProductEntity> storeRetailProduct = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "store")
    private List<StoreItemMappingEntity> storeItemMapping = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "store")
    private List<TransactionEntity> transactions = new ArrayList<>();

    public StoreEntity() {
    }

    public StoreEntity(String address, String country, String contact, String manager, Boolean deleteFlag) {
        this.address = address;
        this.country = country;
        this.contact = contact;
        this.manager = manager;
        this.deleteFlag = deleteFlag;
        factoryList=new ArrayList<>();

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

    public List<StoreProductEntity> getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(List<StoreProductEntity> storeProduct) {
        this.storeProduct = storeProduct;
    }

    public List<StoreRetailProductEntity> getStoreRetailProduct() {
        return storeRetailProduct;
    }

    public void setStoreRetailProduct(List<StoreRetailProductEntity> storeRetailProduct) {
        this.storeRetailProduct = storeRetailProduct;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
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


    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public List<StoreItemMappingEntity> getStoreProductMapping() {
        return storeItemMapping;
    }

    public void setStoreProductMapping(List<StoreItemMappingEntity> storeProductMapping) {
        this.storeItemMapping = storeProductMapping;
    }
    
    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public List<StoreItemMappingEntity> getStoreItemMapping() {
        return storeItemMapping;
    }

//    public List<TransactionEntity> getTransacion() {
//        return transactions;
//    }

    public void setStoreItemMapping(List<StoreItemMappingEntity> storeItemMapping) {
        this.storeItemMapping = storeItemMapping;
    }

    public List<Long> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(List<Long> factoryList) {
        this.factoryList = factoryList;
    }

//    public void setTransacion(List<TransactionEntity> transactions) {
//        this.transactions = transactions;
//    }

    
    
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
