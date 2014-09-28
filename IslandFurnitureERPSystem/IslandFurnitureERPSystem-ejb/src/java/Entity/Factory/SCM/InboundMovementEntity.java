/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryBin.FactoryBinEntity;
import Entity.Factory.FactoryBin.FactoryBinStoredProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
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
@Table(name = "InboundMovement")
// only for unrestrictd stock
public class InboundMovementEntity /*extends FactoryMovementEntity*/ implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inboundMovementId;

    //goods receipt entity -- inbound movements: 1 <--> M (from which corresponding goods receipt)
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private GoodsReceiptEntity fromGoodsRecipt;
    //factory bin stored product entity -- inbound movements: 1 <--> M (to which bin)
    @ManyToOne
    private FactoryBinEntity toBin;

    //factory rawMaterial entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryRawMaterialEntity factoryRawMaterial = null;

    //factory retail product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryRetailProductEntity factoryRetailProduct = null;

    private int stockTypeIndicator = 0; // default is 0    //to indicate the type of stocks: 1 for factoryRawMaterial, 3 for factoryRetailProduct
    private String status;
    private double quantity;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar creationDate;

    public InboundMovementEntity() {
    }

    public Long getInboundMovementId() {
        return inboundMovementId;
    }

    public void setInboundMovementId(Long InboundMovementId) {
        this.inboundMovementId = InboundMovementId;
    }

    public GoodsReceiptEntity getFromGoodsRecipt() {
        return fromGoodsRecipt;
    }

    public void setFromGoodsRecipt(GoodsReceiptEntity fromGoodsRecipt) {
        this.fromGoodsRecipt = fromGoodsRecipt;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    // assumption: the inbound goods is unrestricted
    // changes need further modification
    public void recordInboundMovement(GoodsReceiptEntity goodsRecipt, FactoryBinStoredProductEntity factoryBinStoredProduct, double quantity, Calendar creationDate) {
        this.fromGoodsRecipt = goodsRecipt;
        this.toBin = factoryBinStoredProduct.getFactoryBin();
        this.status = factoryBinStoredProduct.getStatus();
        if (factoryBinStoredProduct.getFactoryRawMaterial() != null) {
            this.factoryRawMaterial = factoryBinStoredProduct.getFactoryRawMaterial();
            this.stockTypeIndicator = 1;
        } else {
            this.factoryRetailProduct = factoryBinStoredProduct.getFactoryRetailProduct();
            this.stockTypeIndicator = 3;
        }
        this.quantity = quantity;
        this.creationDate = creationDate;
        updateFactoryBinStoredProduct(factoryBinStoredProduct, quantity);
        updateFactoryStock(factoryBinStoredProduct, quantity);
    }

    private void updateFactoryBinStoredProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, double quantity) {
        try {
            factoryBinStoredProduct.increaseQuantity(quantity);
        } catch (Exception ex) {
            System.err.println("Entity.Factory.SCM.InboundMovementEntity: updateFactoryBinStoredProduct(): Caught an unexpected exception in recordInboundMovement()");
            ex.printStackTrace();
        }
    }

    private void updateFactoryStock(FactoryBinStoredProductEntity factoryBinStoredProduct, double quantity) {
        try {
            if (factoryBinStoredProduct.getFactoryRawMaterial() != null) {
                FactoryRawMaterialEntity factoryRawMaterial = factoryBinStoredProduct.getFactoryRawMaterial();
                if(factoryBinStoredProduct.getStatus().equals("unrestricted")) {
                    factoryRawMaterial.setUnrestrictedInventory(factoryRawMaterial.getUnrestrictedInventory() + quantity);
                } else {
                    factoryRawMaterial.setBlockedInventory(factoryRawMaterial.getBlockedInventory() + quantity);
                }
            } else {
                FactoryRetailProductEntity factoryRetailProduct = factoryBinStoredProduct.getFactoryRetailProduct();
                if(factoryBinStoredProduct.getStatus().equals("unrestricted")) {
                    factoryRetailProduct.setUnrestrictedInventory(factoryRetailProduct.getUnrestrictedInventory() + quantity);
                } else {
                    factoryRetailProduct.setBlockedInventory(factoryRetailProduct.getBlockedInventory() + quantity);
                }
            }
        } catch (Exception ex) {
            System.err.println("Entity.Factory.SCM.InboundMovementEntity: updateFactoryBinStoredProduct(): Caught an unexpected exception in recordInboundMovement()");
            ex.printStackTrace();
        }
    }

}
