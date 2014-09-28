/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryBin.FactoryBinEntity;
import Entity.Factory.FactoryBin.FactoryBinStoredProductEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
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
@Table(name = "InFactoryMovement")
public class InFactoryMovementEntity /*extends FactoryMovementEntity*/ implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inFactoryMovementId;

    //factory bin stored product entity -- in factory movements: 1 <--> M (from which bin)
    @ManyToOne
    private FactoryBinEntity fromBin;
    //factory bin stored product entity -- in factory movements: 1 <--> M (to which bin)
    @ManyToOne
    private FactoryBinEntity toBin;

    //factory rawMaterial entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryRawMaterialEntity factoryRawMaterial = null;

    //factory product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryProductEntity factoryProduct = null;

    //factory retail product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryRetailProductEntity factoryRetailProduct = null;

    private int stockTypeIndicator = 0; // default is 0    //to indicate the type of stocks: 1 for factoryRawMaterial, 2 for factoryProduct, 3 for factoryRetailProduct
    private double quantity;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar creationDate;

    public InFactoryMovementEntity() {
    }

    public Long getInFactoryMovementId() {
        return inFactoryMovementId;
    }

    public void setInFactoryMovementId(Long inFactoryMovementId) {
        this.inFactoryMovementId = inFactoryMovementId;
    }

    public FactoryBinEntity getFromBin() {
        return fromBin;
    }

    public void setFromBin(FactoryBinEntity fromBin) {
        this.fromBin = fromBin;
    }

    public FactoryBinEntity getToBin() {
        return toBin;
    }

    public void setToBin(FactoryBinEntity toBin) {
        this.toBin = toBin;
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
    public void recordInFactoryRawMaterialMovement(FactoryBinStoredProductEntity factoryFromBinStoredProduct, FactoryBinStoredProductEntity factoryToBinStoredProduct, double quantity, Calendar creationDate) {
        this.setFromBin(factoryFromBinStoredProduct.getFactoryBin());
        this.setToBin(factoryToBinStoredProduct.getFactoryBin());
        this.setFactoryRawMaterial(factoryFromBinStoredProduct.getFactoryRawMaterial());
        this.setStockTypeIndicator(1);
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(factoryFromBinStoredProduct, factoryToBinStoredProduct, quantity);
    }

    //pre-cond: availability check
    public void recordInFactoryProductMovement(FactoryBinStoredProductEntity factoryFromBinStoredProduct, FactoryBinStoredProductEntity factoryToBinStoredProduct, double quantity, Calendar creationDate) {
        this.setFromBin(factoryFromBinStoredProduct.getFactoryBin());
        this.setToBin(factoryToBinStoredProduct.getFactoryBin());
        this.setFactoryProduct(factoryFromBinStoredProduct.getFactoryProduct());
        this.setStockTypeIndicator(2);
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(factoryFromBinStoredProduct, factoryToBinStoredProduct, quantity);
    }

    //pre-cond: availability check
    public void recordInFactoryRetailProductMovement(FactoryBinStoredProductEntity factoryFromBinStoredProduct, FactoryBinStoredProductEntity factoryToBinStoredProduct, double quantity, Calendar creationDate) {
        this.setFromBin(factoryFromBinStoredProduct.getFactoryBin());
        this.setToBin(factoryToBinStoredProduct.getFactoryBin());
        this.setFactoryRetailProduct(factoryFromBinStoredProduct.getFactoryRetailProduct());
        this.setStockTypeIndicator(3);
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(factoryFromBinStoredProduct, factoryToBinStoredProduct, quantity);
    }

    private void updateFactoryBinStoredProduct(FactoryBinStoredProductEntity factoryFromBinStoredProduct, FactoryBinStoredProductEntity factoryToBinStoredProduct, double quantity) {
        try {
            factoryFromBinStoredProduct.decreaseQuantity(quantity);
            factoryToBinStoredProduct.increaseQuantity(quantity);
        } catch (Exception ex) {
            System.err.println("Entity.Factory.SCM.InFactoryMovementEntity: updateFactoryBinStoredFactoryRawMaterial(): Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }
}
