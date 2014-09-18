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
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Temporal;

/**
 *
 * @author Yoky
 */
@Entity
public class ProductToBinMovementEntity implements Serializable {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productToBinMovementId;

    @ManyToOne
    private FactoryBinEntity toBin;

    @ManyToOne
    private FactoryProductEntity factoryProduct;
    private String status;  //unrestricted, blocked
    private double quantity;
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
    public void recordProductToBinMovement(FactoryProductEntity factoryProduct, FactoryBinEntity toBin, String status, double quantity, Calendar creationDate) {
        try {
            this.setFactoryProduct(factoryProduct);
            this.setToBin(toBin);
            this.setStatus(status);
            this.setQuantity(quantity);
            this.setCreationDate(creationDate);
            updateFactoryBinStoredProduct(factoryProduct, toBin, status, quantity);
//            updateFactoryProduct(factoryProduct, status, quantity);
        } catch (Exception ex) {
            System.err.println("Entity.Factory.SCM.ProductToBinMovementEntity: recordProductToBinMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    private void updateFactoryBinStoredProduct(FactoryProductEntity factoryProduct, FactoryBinEntity toBin, String status, double quantity) {
        try {
            Query q = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryProduct = :factoryProduct AND fbsp.status = :status");
            q.setParameter("toBin", toBin);
            q.setParameter("factoryProduct", factoryProduct);
            q.setParameter("status", status);

            if (q.getResultList() == null) {
                FactoryBinStoredProductEntity factoryBinStoredProduct = new FactoryBinStoredProductEntity();
                factoryBinStoredProduct.createFactoryBinStoredProduct(factoryProduct, toBin, status);
                factoryBinStoredProduct.setAmount(factoryBinStoredProduct.getAmount() + quantity);
                em.persist(factoryBinStoredProduct);
            } else {
                FactoryBinStoredProductEntity factoryBinStoredProduct = (FactoryBinStoredProductEntity) q.getSingleResult();
                factoryBinStoredProduct.setAmount(factoryBinStoredProduct.getAmount() + quantity);
                em.flush();
            }
        } catch (Exception ex) {
            System.err.println("Entity.Factory.SCM.InboundMovementEntity: updateFactoryBinStoredProduct(): Caught an unexpected exception in recordInboundMovement()");
            ex.printStackTrace();
        }
    }

//    private void updateFactoryProduct(FactoryProductEntity factoryProduct, String status, double quantity) {
//        factoryBinStoredProduct.getFactoryProduct().setInventory(factoryBinStoredProduct.getFactoryProduct().getInventory() - quantity);
//        em.flush();
//    }

}
