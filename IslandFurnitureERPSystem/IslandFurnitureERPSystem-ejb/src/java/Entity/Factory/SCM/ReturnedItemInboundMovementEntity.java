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
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

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

    private int stockTypeIndicator = 0; // default is 0  //to indicate the type of stocks: 2 for factoryProduct, 3 for factoryRetailProduct
    private double quantity;
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

    public void recordReturnedItemInboundMovement(FactoryProductEntity factoryProduct, StoreEntity fromStore, FactoryBinEntity toBin, double quantity, Calendar creationDate) {
        this.setFactoryProduct(factoryProduct);
        this.setStockTypeIndicator(2);
        this.setFromStore(fromStore);
        this.setToBin(toBin);
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(toBin, factoryProduct, quantity);
 //       updateFactoryRetailProduct(factoryProduct, quantity); // need to matian the total quantity of returned products
    }
    
    public void recordReturnedItemInboundMovement(FactoryRetailProductEntity factoryRetailProduct, StoreEntity fromStore, FactoryBinEntity toBin, double quantity, Calendar creationDate) {
        this.setFactoryRetailProduct(factoryRetailProduct);
        this.setStockTypeIndicator(3);
        this.setFromStore(fromStore);
        this.setToBin(toBin);
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(toBin, factoryRetailProduct, quantity);
 //       updateFactoryRetailProduct(factoryRetailProduct, quantity); // need to matian the total quantity of returned products
    }

    private void updateFactoryBinStoredProduct(FactoryBinEntity toBin, FactoryProductEntity factoryProduct, double quantity) {
        try {
            Query q = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryProduct = :factoryProduct AND fbsp.status = 'returned'");
            q.setParameter("toBin", toBin);
            q.setParameter("factoryProduct", factoryProduct);

            if (q.getResultList() == null) {
                FactoryBinStoredProductEntity factoryBinStoredProduct = new FactoryBinStoredProductEntity();
                factoryBinStoredProduct.createFactoryBinStoredProduct(factoryProduct, toBin, "returned");
                factoryBinStoredProduct.increaseQuantity(quantity);
                em.persist(factoryBinStoredProduct);
            } else {
                FactoryBinStoredProductEntity factoryBinStoredProduct = (FactoryBinStoredProductEntity) q.getSingleResult();
                factoryBinStoredProduct.increaseQuantity(quantity);
                em.flush();
            }
        } catch (Exception ex) {
            System.err.println("Entity.Factory.SCM.InboundMovementEntity: updateFactoryBinStoredProduct(): Caught an unexpected exception in recordInboundMovement()");
            ex.printStackTrace();
        }
    }
    
    private void updateFactoryBinStoredProduct(FactoryBinEntity toBin, FactoryRetailProductEntity factoryRetailProduct, double quantity) {
        try {
            Query q = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryRetailProduct = :factoryRetailProduct AND fbsp.status = 'returned'");
            q.setParameter("toBin", toBin);
            q.setParameter("factoryRetailProduct", factoryRetailProduct);

            if (q.getResultList() == null) {
                FactoryBinStoredProductEntity factoryBinStoredProduct = new FactoryBinStoredProductEntity();
                factoryBinStoredProduct.createFactoryBinStoredProduct(factoryRetailProduct, toBin, "returned");
                factoryBinStoredProduct.increaseQuantity(quantity);
                em.persist(factoryBinStoredProduct);
            } else {
                FactoryBinStoredProductEntity factoryBinStoredProduct = (FactoryBinStoredProductEntity) q.getSingleResult();
                factoryBinStoredProduct.increaseQuantity(quantity);
                em.flush();
            }
        } catch (Exception ex) {
            System.err.println("Entity.Factory.SCM.InboundMovementEntity: updateFactoryBinStoredProduct(): Caught an unexpected exception in recordInboundMovement()");
            ex.printStackTrace();
        }
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
