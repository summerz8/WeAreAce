/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryBin.FactoryBinEntity;
import Entity.Factory.FactoryBin.FactoryBinStoredProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Yoky
 */
@Entity
@Table(name = "RawMaterialInFactoryUseMovement")
public class RawMaterialInFactoryUseMovementEntity implements Serializable {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rawMaterialInFactoryUseMovementId;

    //factory bin stored product entity -- outbound movements: 1 <--> M (from which bin)
    @ManyToOne
    private FactoryBinEntity fromBin;

    //factory product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryRawMaterialEntity factoryRawMaterial;

    private double quantity;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar creationDate;
    // We do not relate it to ProductionPlan since RawMaterialInFactoryUseMovementEntity records only the usage of raw material in the reality

    public RawMaterialInFactoryUseMovementEntity() {
    }

    public Long getRawMaterialInFactoryUseMovementId() {
        return rawMaterialInFactoryUseMovementId;
    }

    public void setRawMaterialInFactoryUseMovementId(Long rawMaterialInFactoryUseMovementId) {
        this.rawMaterialInFactoryUseMovementId = rawMaterialInFactoryUseMovementId;
    }

    public FactoryBinEntity getFromBin() {
        return fromBin;
    }

    public void setFromBin(FactoryBinEntity fromBin) {
        this.fromBin = fromBin;
    }

    public FactoryRawMaterialEntity getFactoryRawMaterial() {
        return factoryRawMaterial;
    }

    public void setFactoryRawMaterial(FactoryRawMaterialEntity factoryRawMaterial) {
        this.factoryRawMaterial = factoryRawMaterial;
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
    public void recordRawMaterialInFactoryUseMovement(FactoryBinStoredProductEntity factoryBinStoredProduct, double quantity, Calendar creationDate) {
        this.setFromBin(factoryBinStoredProduct.getFactoryBin());
        this.setFactoryRawMaterial(factoryBinStoredProduct.getFactoryRawMaterial());
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(factoryBinStoredProduct, quantity);
        updateFactoryRawMaterial(factoryBinStoredProduct, quantity);
    }
    
    private void updateFactoryBinStoredProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, double quantity) {
        factoryBinStoredProduct.decreaseQuantity(quantity);
        if (factoryBinStoredProduct.getAmount() == 0) { //not sure about the comparation of double
            em.remove(factoryBinStoredProduct);
        }
        em.flush();
    }
    
    private void updateFactoryRawMaterial(FactoryBinStoredProductEntity factoryBinStoredProduct, double quantity) {
        factoryBinStoredProduct.getFactoryProduct().setInventory(factoryBinStoredProduct.getFactoryProduct().getInventory() - quantity);
        em.flush();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rawMaterialInFactoryUseMovementId != null ? rawMaterialInFactoryUseMovementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the rawMaterialInFactoryUseMovementId fields are not set
        if (!(object instanceof RawMaterialInFactoryUseMovementEntity)) {
            return false;
        }
        RawMaterialInFactoryUseMovementEntity other = (RawMaterialInFactoryUseMovementEntity) object;
        if ((this.rawMaterialInFactoryUseMovementId == null && other.rawMaterialInFactoryUseMovementId != null) || (this.rawMaterialInFactoryUseMovementId != null && !this.rawMaterialInFactoryUseMovementId.equals(other.rawMaterialInFactoryUseMovementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.SCM.RawMaterialInFactoryUseMovementEntity[ id=" + rawMaterialInFactoryUseMovementId + " ]";
    }

}
