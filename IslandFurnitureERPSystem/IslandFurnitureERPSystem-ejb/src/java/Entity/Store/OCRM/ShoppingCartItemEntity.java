/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store.OCRM;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author apple
 */
@Entity
public class ShoppingCartItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantity;
    private Long storeId;
    private String type;
    
    @ManyToOne
    private CountryProductEntity customerWebItem;
    
    @ManyToOne
    private CountrySetEntity countrySet;


    public ShoppingCartItemEntity(){
    
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public CountryProductEntity getCustomerWebItem() {
        return customerWebItem;
    }

    public void setCustomerWebItem(CountryProductEntity customerWebItem) {
        this.customerWebItem = customerWebItem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CountrySetEntity getCountrySet() {
        return countrySet;
    }

    public void setCountrySet(CountrySetEntity countrySet) {
        this.countrySet = countrySet;
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
        if (!(object instanceof ShoppingCartItemEntity)) {
            return false;
        }
        ShoppingCartItemEntity other = (ShoppingCartItemEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.ShoppingCartItem[ id=" + id + " ]";
    }
    
}
