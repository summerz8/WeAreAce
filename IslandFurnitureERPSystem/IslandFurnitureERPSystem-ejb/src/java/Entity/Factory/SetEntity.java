/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory;

import Entity.Store.OCRM.CountrySetEntity;
import Entity.Store.StoreSetEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author apple
 */
@Entity
public class SetEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Boolean deleteFlag;
    private Double price;
    private Double memberPrice;

    @OneToMany
    @XmlTransient
    private List<ProductEntity> productList;

    @OneToMany
    @XmlTransient
    private List<StoreSetEntity> storeSetList;

    @OneToMany
    @XmlTransient
    private List<CountrySetEntity> webSetList;
    
    
    public SetEntity(){
        productList=new ArrayList<>();
        storeSetList=new ArrayList<>();
        webSetList=new ArrayList<>();
        deleteFlag=false;
        price=0D;
        memberPrice=0D;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    public List<StoreSetEntity> getStoreSetList() {
        return storeSetList;
    }

    public void setStoreSetList(List<StoreSetEntity> storeSetList) {
        this.storeSetList = storeSetList;
    }

    public List<CountrySetEntity> getWebSetList() {
        return webSetList;
    }

    public void setWebSetList(List<CountrySetEntity> webSetList) {
        this.webSetList = webSetList;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
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
        if (!(object instanceof SetEntity)) {
            return false;
        }
        SetEntity other = (SetEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.SetEntity[ id=" + id + " ]";
    }

}
