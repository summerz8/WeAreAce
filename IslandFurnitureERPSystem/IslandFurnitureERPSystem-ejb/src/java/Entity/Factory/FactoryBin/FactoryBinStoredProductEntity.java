/*
 * FactoryBinStoredProductEntity.java
 *
 * This entity is a functional entity.
 * Used as a quantity record of a specific item in a specific bin.
 */
package Entity.Factory.FactoryBin;

import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Yoky
 */
@Entity
@Table(name = "FactoryStoredProduct")
public class FactoryBinStoredProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryBinStoredProductId;
    private double amount = 0;

    //factory rawMaterial entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private FactoryRawMaterialEntity factoryRawMaterial = null;

    //factory product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private FactoryProductEntity factoryProduct = null;

    //factory retail product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private FactoryRetailProductEntity factoryRetailProduct = null;

    private int stockTypeIndicator = 0; // default is 0    //to indicate the type of stocks: 1 for factoryRawMaterial, 2 for factoryProduct, 3 for factoryRetailProduct

//    //factory bin entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private FactoryBinEntity factoryBin;

    private String status;  // unrestricted, blocked, returned

    public FactoryBinStoredProductEntity() {
    }

    public Long getFactoryBinStoredProductId() {
        return factoryBinStoredProductId;
    }

    public void setFactoryBinStoredProductId(Long id) {
        this.factoryBinStoredProductId = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public FactoryRawMaterialEntity getFactoryRawMaterial() {
        return factoryRawMaterial;
    }

    public void setFactoryRawMaterial(FactoryRawMaterialEntity factoryRawMaterial) {
        this.factoryRawMaterial = factoryRawMaterial;
    }

    public FactoryProductEntity getFactoryProduct() {
        return factoryProduct;
    }

    public void setFactoryProduct(FactoryProductEntity factoryProduct) {
        this.factoryProduct = factoryProduct;
    }

    public FactoryRetailProductEntity getFactoryRetailProduct() {
        return factoryRetailProduct;
    }

    public void setFactoryRetailProduct(FactoryRetailProductEntity factoryRetailProduct) {
        this.factoryRetailProduct = factoryRetailProduct;
    }

    public FactoryBinEntity getFactoryBin() {
        return factoryBin;
    }

    public int getStockTypeIndicator() {
        return stockTypeIndicator;
    }

    public void setStockTypeIndicator(int stockTypeIndicator) {
        this.stockTypeIndicator = stockTypeIndicator;
    }

    public void setFactoryBin(FactoryBinEntity factoryBin) {
        this.factoryBin = factoryBin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void createFactoryBinStoredProduct(FactoryRawMaterialEntity factoryRawMaterial, FactoryBinEntity factoryBin, String status) {
        this.setFactoryRawMaterial(factoryRawMaterial);
        this.setStockTypeIndicator(1);
        this.setFactoryBin(factoryBin);
        this.setStatus(status);
    }

    public void createFactoryBinStoredProduct(FactoryProductEntity factoryProduct, FactoryBinEntity factoryBin, String status) {
        this.setFactoryProduct(factoryProduct);
        this.setStockTypeIndicator(2);
        this.setFactoryBin(factoryBin);
        this.setStatus(status);
    }

    public void createFactoryBinStoredProduct(FactoryRetailProductEntity factoryRetailProduct, FactoryBinEntity factoryBin, String status) {
        this.setFactoryRetailProduct(factoryRetailProduct);
        this.setStockTypeIndicator(3);
        this.setFactoryBin(factoryBin);
        this.setStatus(status);
    }

    public void increaseQuantity(double increaseBy) {
        this.amount += increaseBy;
    }
    
    //pre-cond: amount - decreaseBy >= 0
    public void decreaseQuantity(double decreaseBy) {
        this.amount -= decreaseBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factoryBinStoredProductId != null ? factoryBinStoredProductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactoryBinStoredProductEntity)) {
            return false;
        }
        FactoryBinStoredProductEntity other = (FactoryBinStoredProductEntity) object;
        if ((this.factoryBinStoredProductId == null && other.factoryBinStoredProductId != null) || (this.factoryBinStoredProductId != null && !this.factoryBinStoredProductId.equals(other.factoryBinStoredProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.StoredProduct[ id=" + factoryBinStoredProductId + " ]";
    }

}
