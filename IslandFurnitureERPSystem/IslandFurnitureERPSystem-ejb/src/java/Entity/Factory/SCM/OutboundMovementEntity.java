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
    private Long outboundMovementId;

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

    private Integer stockTypeIndicator = 0; // default is 0    //to indicate the type of stocks: 2 for factoryProduct, 3 for factoryRetailProduct
    private Double quantity;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar creationDate;

    public OutboundMovementEntity() {
    }

    public OutboundMovementEntity(FactoryBinEntity fromBin, StoreEntity toStore, Double quantity, Calendar creationDate) {
        this.fromBin = fromBin;
        this.toStore = toStore;
        this.quantity = quantity;
        this.creationDate = creationDate;
    }

    public Long getOutboundMovementId() {
        return outboundMovementId;
    }

    public void setOutboundMovementId(Long OutboundMovementId) {
        this.outboundMovementId = OutboundMovementId;
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
        this.stockTypeIndicator = 2;
    }

    public FactoryRetailProductEntity getFactoryRetailProduct() {
        return factoryRetailProduct;
    }

    public void setFactoryRetailProduct(FactoryRetailProductEntity factoryRetailProduct) {
        this.factoryRetailProduct = factoryRetailProduct;
        this.stockTypeIndicator = 3;
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

    //pre-cond: availability check
    public void recordFactoryProductOutboundMovement(FactoryBinStoredProductEntity factoryBinStoredProduct, StoreEntity toStore, Double quantity, Calendar creationDate) {
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
    public void recordFactoryRetailProductOutboundMovement(FactoryBinStoredProductEntity factoryBinStoredProduct, StoreEntity toStore, Double quantity, Calendar creationDate) {
        this.setFromBin(factoryBinStoredProduct.getFactoryBin());
        this.setToStore(toStore);
        this.setFactoryRetailProduct(factoryBinStoredProduct.getFactoryRetailProduct());
        this.setStockTypeIndicator(3);
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(factoryBinStoredProduct, quantity);
        updateFactoryRetailProduct(factoryBinStoredProduct, quantity);

    }

    private void updateFactoryBinStoredProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity) {
            factoryBinStoredProduct.setAmount(factoryBinStoredProduct.getAmount() - quantity);
    }
    private void updateFactoryProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity) {
        factoryBinStoredProduct.getFactoryProduct().setUnrestrictedInventory(factoryBinStoredProduct.getFactoryProduct().getUnrestrictedInventory() - quantity);
    }

    private void updateFactoryRetailProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity) {
        factoryBinStoredProduct.getFactoryRetailProduct().setUnrestrictedInventory(factoryBinStoredProduct.getFactoryRetailProduct().getUnrestrictedInventory() - quantity);
    }
}
