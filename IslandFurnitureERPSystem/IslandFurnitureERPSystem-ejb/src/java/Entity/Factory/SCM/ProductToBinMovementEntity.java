/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryBin.FactoryBinEntity;
import Entity.Factory.FactoryBin.FactoryBinStoredProductEntity;
import Entity.Factory.FactoryProductEntity;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Yoky
 */
@Entity
public class ProductToBinMovementEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productToBinMovementId;

    @ManyToOne
    private FactoryBinEntity toBin;

    @ManyToOne
    private FactoryProductEntity factoryProduct;
    private String status;  //unrestricted, blocked
    private Double quantity;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar creationDate;

    public ProductToBinMovementEntity() {
    }

    public Long getProductToBinMovementId() {
        return productToBinMovementId;
    }

    public void setProductToBinMovementId(Long productToBinMovementId) {
        this.productToBinMovementId = productToBinMovementId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    public void recordProductToBinMovement(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity, Calendar creationDate) {
        try {
            this.setFactoryProduct(factoryBinStoredProduct.getFactoryProduct());
            this.setToBin(factoryBinStoredProduct.getFactoryBin());
            this.setStatus(factoryBinStoredProduct.getStatus());
            this.setQuantity(quantity);
            this.setCreationDate(creationDate);
            updateFactoryBinStoredProduct(factoryBinStoredProduct, quantity);
            updateFactoryProduct(factoryBinStoredProduct, quantity);
        } catch (Exception ex) {
            System.err.println("Entity.Factory.SCM.ProductToBinMovementEntity: recordProductToBinMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    private void updateFactoryBinStoredProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity) {
        try {
            factoryBinStoredProduct.increaseQuantity(quantity);
        } catch (Exception ex) {
            System.err.println("Entity.Factory.SCM.InboundMovementEntity: updateFactoryBinStoredProduct(): Caught an unexpected exception in recordInboundMovement()");
            ex.printStackTrace();
        }
    }

    private void updateFactoryProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity) {
        if (factoryBinStoredProduct.getStatus().equals("unrestricted")) {
            factoryBinStoredProduct.getFactoryProduct().setUnrestrictedInventory(factoryBinStoredProduct.getFactoryProduct().getUnrestrictedInventory() + quantity);
        } else {
            factoryBinStoredProduct.getFactoryProduct().setBlockedInventory(factoryBinStoredProduct.getFactoryProduct().getBlockedInventory()+ quantity);
        }
    }

}
