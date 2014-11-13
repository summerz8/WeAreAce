/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store;

import Entity.Factory.SetEntity;
import Entity.Store.OCRM.CountryProductEntity;
import Entity.Store.OCRM.CountrySetEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author apple
 */
@Entity
public class StoreSetEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Boolean deleteFlag;
    private String storeRemark;
    
    //store product entity -- stores entity: M <--> 1
    @ManyToOne
    private StoreEntity store;
    
    //store product entity -- stores entity: M <--> 1
    @OneToMany
    @XmlTransient
    private List<StoreProductEntity> storeProductList=new ArrayList<>();

    @ManyToOne
    private SetEntity set;

    @ManyToOne
    private CountrySetEntity webSet;

    public StoreSetEntity() {
        storeProductList = new ArrayList<>();
    }

    public StoreSetEntity(SetEntity set,CountrySetEntity webset, StoreEntity store) {
        this.set=set;
        this.webSet = webset;
        this.description = webSet.getDescription();
        this.name = webSet.getName();
        this.store = store;
        this.deleteFlag = false;
        storeProductList = new ArrayList<>();
        for (CountryProductEntity s : this.webSet.getUnitList()) {
            Collection<StoreProductEntity> list = s.getProduct().getStoreProducts();
            for (StoreProductEntity c : list) {
                if (c.getStore().getStoreId().equals(store.getStoreId())) {
                    storeProductList.add(c);
                    break;
                }
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getStoreRemark() {
        return storeRemark;
    }

    public void setStoreRemark(String storeRemark) {
        this.storeRemark = storeRemark;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public SetEntity getSet() {
        return set;
    }

    public void setSet(SetEntity set) {
        this.set = set;
    }

    public List<StoreProductEntity> getStoreProductList() {
        return storeProductList;
    }

    public void setStoreProductList(List<StoreProductEntity> storeProductList) {
        this.storeProductList = storeProductList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CountrySetEntity getWebSet() {
        return webSet;
    }

    public void setWebSet(CountrySetEntity webSet) {
        this.webSet = webSet;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreSetEntity)) {
            return false;
        }
        StoreSetEntity other = (StoreSetEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.StoreSetEntity[ id=" + id + " ]";
    }

}
