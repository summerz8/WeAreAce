/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryBin.FactoryBinEntity;
import Entity.Factory.FactoryBin.FactoryBinStoredProductEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Store.StoreEntity;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Yoky
 */
@Entity
@Table(name = "ReturnedItemInboundMovement")
//cannot record the total quantity of returned products
public class ReturnedItemInboundMovementEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long returnedItemInboundMovementId;

    //goods receipt entity -- inbound movements: 1 <--> M (from which corresponding goods receipt)
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private StoreEntity fromStore;
    //factory bin stored product entity -- inbound movements: 1 <--> M (to which bin)
    @ManyToOne
    private FactoryBinEntity toBin;

    //factory product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private FactoryProductEntity factoryProduct = null;

    //factory retail product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryRetailProductEntity factoryRetailProduct = null;

    private Integer stockTypeIndicator = 0; // default is 0  //to indicate the type of stocks: 2 for factoryProduct, 3 for factoryRetailProduct
    private Double quantity;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar creationDate;

    public Long getReturnedItemInboundMovementId() {
        return returnedItemInboundMovementId;
    }

    public void setReturnedItemInboundMovementId(Long returnedItemInboundMovementId) {
        this.returnedItemInboundMovementId = returnedItemInboundMovementId;
    }

    public StoreEntity getFromStore() {
        return fromStore;
    }

    public void setFromStore(StoreEntity fromStore) {
        this.fromStore = fromStore;
    }

    public FactoryBinEntity getToBin() {
        return toBin;
    }

    public void setToBin(FactoryBinEntity toBin) {
        this.toBin = toBin;
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

    public Integer getStockTypeIndicator() {
        return stockTypeIndicator;
    }

    public void setStockTypeIndicator(Integer stockTypeIndicator) {
        this.stockTypeIndicator = stockTypeIndicator;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public void recordReturnedFactoryProductInboundMovement(FactoryBinStoredProductEntity factoryBinStoredProduct, StoreEntity fromStore, double quantity, Calendar creationDate) {
        this.setFactoryProduct(factoryBinStoredProduct.getFactoryProduct());
        this.setStockTypeIndicator(2);
        this.setFromStore(fromStore);
        this.setToBin(factoryBinStoredProduct.getFactoryBin());
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(factoryBinStoredProduct, quantity);
        updateFactoryProduct(factoryBinStoredProduct, quantity);
    }

    public void recordReturnedFactoryRetailProductInboundMovement(FactoryBinStoredProductEntity factoryBinStoredProduct, StoreEntity fromStore, double quantity, Calendar creationDate) {
        this.setFactoryRetailProduct(factoryBinStoredProduct.getFactoryRetailProduct());
        this.setStockTypeIndicator(3);
        this.setFromStore(fromStore);
        this.setToBin(factoryBinStoredProduct.getFactoryBin());
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(factoryBinStoredProduct, quantity);
        updateFactoryRetailProduct(factoryBinStoredProduct, quantity); // need to matian the total quantity of returned products
    }

    private void updateFactoryBinStoredProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity) {
        factoryBinStoredProduct.increaseQuantity(quantity);
    }
    
    private void updateFactoryProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity) {
        factoryBinStoredProduct.getFactoryProduct().setReturnedInventory(factoryBinStoredProduct.getFactoryProduct().getReturnedInventory() + quantity);
    }

    private void updateFactoryRetailProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity) {
        factoryBinStoredProduct.getFactoryRetailProduct().setReturnedInventory(factoryBinStoredProduct.getFactoryRetailProduct().getReturnedInventory() + quantity);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (returnedItemInboundMovementId != null ? returnedItemInboundMovementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the returnedItemInboundMovementId fields are not set
        if (!(object instanceof ReturnedItemInboundMovementEntity)) {
            return false;
        }
        ReturnedItemInboundMovementEntity other = (ReturnedItemInboundMovementEntity) object;
        if ((this.returnedItemInboundMovementId == null && other.returnedItemInboundMovementId != null) || (this.returnedItemInboundMovementId != null && !this.returnedItemInboundMovementId.equals(other.returnedItemInboundMovementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.SCM.ReturnedItemInboundMovementEntity[ id=" + returnedItemInboundMovementId + " ]";
    }

}
