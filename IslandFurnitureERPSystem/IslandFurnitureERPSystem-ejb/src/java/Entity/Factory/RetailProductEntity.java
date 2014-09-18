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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name = "RetailProduct")
public class RetailProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long retailProductId;
    private String name;
    private String description;
    private Boolean deleteFlag;
    private String unit;
    
    
    //retail product entity -- factory retail product entity: 1<--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "retailProduct")
    private Collection<FactoryRetailProductEntity> factoryRetailProducts = new ArrayList<>();

    public RetailProductEntity() {
    }

    public RetailProductEntity(String name, String description, Boolean deleteFlag) {
        this.name = name;
        this.description = description;
        this.deleteFlag = deleteFlag;
    }

    
    public Long getRetailProductId() {
        return retailProductId;
    }

    public void setRetailProductId(Long retailProductId) {
        this.retailProductId = retailProductId;
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

    public void setDescription(String decription) {
        this.description = decription;
    }

    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Collection<FactoryRetailProductEntity> getFactoryRetailProducts() {
        return factoryRetailProducts;
    }

    public void setFactoryRetailProducts(Collection<FactoryRetailProductEntity> factoryRetailProducts) {
        this.factoryRetailProducts = factoryRetailProducts;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retailProductId != null ? retailProductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the retailProductId fields are not set
        if (!(object instanceof RetailProductEntity)) {
            return false;
        }
        RetailProductEntity other = (RetailProductEntity) object;
        if ((this.retailProductId == null && other.retailProductId != null) || (this.retailProductId != null && !this.retailProductId.equals(other.retailProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.RetailProductEntity[ id=" + retailProductId + " ]";
    }

}
