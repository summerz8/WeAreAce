/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Yoky
 */
@Entity
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productEntity;
    private String name;
    private String description;
    private double price; // ???
    private String unit;
    private BOMEntity bom;
    private int monthltSalesForecast;
    private int weeklyProduction;
    private int monthlyProduction;
    private int inventoryBalance;

    public ProductEntity() {
    }

    public Long getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(Long productEntity) {
        this.productEntity = productEntity;
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

    public BOMEntity getBom() {
        return bom;
    }

    public void setBom(BOMEntity bom) {
        this.bom = bom;
    }

    public int getMonthltSalesForecast() {
        return monthltSalesForecast;
    }

    public void setMonthltSalesForecast(int monthltSalesForecast) {
        this.monthltSalesForecast = monthltSalesForecast;
    }

    public int getWeeklyProduction() {
        return weeklyProduction;
    }

    public void setWeeklyProduction(int weeklyProduction) {
        this.weeklyProduction = weeklyProduction;
    }

    public int getMonthlyProduction() {
        return monthlyProduction;
    }

    public void setMonthlyProduction(int monthlyProduction) {
        this.monthlyProduction = monthlyProduction;
    }

    public int getInventoryBalance() {
        return inventoryBalance;
    }

    public void setInventoryBalance(int inventoryBalance) {
        this.inventoryBalance = inventoryBalance;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productEntity != null ? productEntity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the productEntity fields are not set
        if (!(object instanceof ProductEntity)) {
            return false;
        }
        ProductEntity other = (ProductEntity) object;
        if ((this.productEntity == null && other.productEntity != null) || (this.productEntity != null && !this.productEntity.equals(other.productEntity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProductEntity[ id=" + productEntity + " ]";
    }
    
}
