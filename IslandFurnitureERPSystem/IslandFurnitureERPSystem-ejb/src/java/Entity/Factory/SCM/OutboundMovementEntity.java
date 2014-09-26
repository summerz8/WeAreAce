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
@Table(name = "OutboundMovement")
public class OutboundMovementEntity /*extends FactoryMovementEntity*/ implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long OutboundMovementId;

    //factory bin stored product entity -- outbound movements: 1 <--> M (from which bin)
    @ManyToOne
    private FactoryBinEntity fromBin;

    //store entity -- outbound movements: 1 <--> M (to which store)
    @ManyToOne
    private StoreEntity toStore;

    //factory product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryProductEntity factoryProduct = null;

    //factory retail product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryRetailProductEntity factoryRetailProduct = null;

    private int stockTypeIndicator = 0; // default is 0    //to indicate the type of stocks: 2 for factoryProduct, 3 for factoryRetailProduct
    private double quantity;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar creationDate;

    public OutboundMovementEntity() {
    }

    public Long getOutboundMovementId() {
        return OutboundMovementId;
    }

    public void setOutboundMovementId(Long OutboundMovementId) {
        this.OutboundMovementId = OutboundMovementId;
    }

    public FactoryBinEntity getFromBin() {
        return fromBin;
    }

    public void setFromBin(FactoryBinEntity fromBin) {
        this.fromBin = fromBin;
    }

    public StoreEntity getToStore() {
        return toStore;
    }

    public void setToStore(StoreEntity toStore) {
        this.toStore = toStore;
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

    public int getStockTypeIndicator() {
        return stockTypeIndicator;
    }

    public void setStockTypeIndicator(int stockTypeIndicator) {
        this.stockTypeIndicator = stockTypeIndicator;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    //pre-cond: availability check
    public void recordFactoryProductOutboundMovement(FactoryBinStoredProductEntity factoryBinStoredProduct, StoreEntity toStore, double quantity, Calendar creationDate) {
        this.setFromBin(factoryBinStoredProduct.getFactoryBin());
        this.setToStore(toStore);
        this.setFactoryProduct(factoryBinStoredProduct.getFactoryProduct());
        this.setStockTypeIndicator(2);
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(factoryBinStoredProduct, quantity);
        updateFactoryProduct(factoryBinStoredProduct, quantity);

    }

    //pre-cond: availability check
    public void recordFactoryRetailProductOutboundMovement(FactoryBinStoredProductEntity factoryBinStoredProduct, StoreEntity toStore, double quantity, Calendar creationDate) {
        this.setFromBin(factoryBinStoredProduct.getFactoryBin());
        this.setToStore(toStore);
        this.setFactoryRetailProduct(factoryBinStoredProduct.getFactoryRetailProduct());
        this.setStockTypeIndicator(3);
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(factoryBinStoredProduct, quantity);
        updateFactoryRetailProduct(factoryBinStoredProduct, quantity);

    }

    private void updateFactoryBinStoredProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, double quantity) {
        factoryBinStoredProduct.decreaseQuantity(quantity);
    }

    private void updateFactoryProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, double quantity) {
        factoryBinStoredProduct.getFactoryProduct().setUnrestrictedInventory(factoryBinStoredProduct.getFactoryProduct().getUnrestrictedInventory() - quantity);
    }

    private void updateFactoryRetailProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, double quantity) {
        factoryBinStoredProduct.getFactoryRetailProduct().setUnrestrictedInventory(factoryBinStoredProduct.getFactoryRetailProduct().getUnrestrictedInventory() - quantity);
    }
}
