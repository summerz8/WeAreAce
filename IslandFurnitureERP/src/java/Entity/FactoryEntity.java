/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Yoky
 */
@Entity
public class FactoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryId;
    private String country;
    private String address;
    private String tel;
    private String email;
    private List<StorageBinEntity> storageBins;
    private List<ProductEntity> products;    

    public FactoryEntity() {
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<StorageBinEntity> getStorageBins() {
        return storageBins;
    }

    public void setStorageBins(List<StorageBinEntity> storageBins) {
        this.storageBins = storageBins;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factoryId != null ? factoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the factoryId fields are not set
        if (!(object instanceof FactoryEntity)) {
            return false;
        }
        FactoryEntity other = (FactoryEntity) object;
        if ((this.factoryId == null && other.factoryId != null) || (this.factoryId != null && !this.factoryId.equals(other.factoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.FactoryEntity[ id=" + factoryId + " ]";
    }
    
}
