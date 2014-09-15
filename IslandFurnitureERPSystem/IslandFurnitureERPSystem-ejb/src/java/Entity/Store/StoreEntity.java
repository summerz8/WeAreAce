/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store;

import Entity.Factory.FactoryEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private String manager;
    private Boolean deleteFlag;

    //factory entity -- store entity: M <--> M 
    @ManyToMany(cascade = {CascadeType.PERSIST}, mappedBy = "stores")
    private Set<FactoryEntity> stores = new HashSet<FactoryEntity>();

    
    public StoreEntity() {
    }

    public StoreEntity(String address, String country, String contact, String manager, Boolean deleteFlag) {
        this.address = address;
        this.country = country;
        this.contact = contact;
        this.manager = manager;
        this.deleteFlag = deleteFlag;
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

    public Set<FactoryEntity> getStores() {
        return stores;
    }

    public void setStores(Set<FactoryEntity> stores) {
        this.stores = stores;
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
