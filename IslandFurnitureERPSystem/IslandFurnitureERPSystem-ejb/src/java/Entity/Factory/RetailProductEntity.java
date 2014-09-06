/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author zhangshiyu
 */
@Entity
        @Table(name="RetailProduct")
public class RetailProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long retailProductId;
    private String name;

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
