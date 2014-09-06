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
 * @author Yoky
 */
@Entity
@Table(name = "Product")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productEntityID;
    private String name;
    private String description;
    private double price; // ???
    private String unit;

    public ProductEntity() {
    }

    public Long getProductEntity() {
        return productEntityID;
    }

    public void setProductEntity(Long productEntity) {
        this.productEntityID = productEntity;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productEntityID != null ? productEntityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the productEntity fields are not set
        if (!(object instanceof ProductEntity)) {
            return false;
        }
        ProductEntity other = (ProductEntity) object;
        if ((this.productEntityID == null && other.productEntityID != null) || (this.productEntityID != null && !this.productEntityID.equals(other.productEntityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProductEntity[ id=" + productEntityID + " ]";
    }

}
