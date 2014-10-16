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
@Table(name = "RawMaterialInFactoryUseMovement")
public class RawMaterialInFactoryUseMovementEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rawMaterialInFactoryUseMovementId;

    @ManyToOne
    private FactoryBinEntity fromBin;

    @ManyToOne
    private FactoryRawMaterialEntity factoryRawMaterial;

    private Double quantity;
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
    public void recordRawMaterialInFactoryUseMovement(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity, Calendar creationDate) {
        this.setFromBin(factoryBinStoredProduct.getFactoryBin());
        this.setFactoryRawMaterial(factoryBinStoredProduct.getFactoryRawMaterial());
        this.setQuantity(quantity);
        this.setCreationDate(creationDate);
        updateFactoryBinStoredProduct(factoryBinStoredProduct, quantity);
        updateFactoryRawMaterial(factoryBinStoredProduct, quantity);
    }
    
    private void updateFactoryBinStoredProduct(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity) {
        factoryBinStoredProduct.decreaseQuantity(quantity);
    }
    
    private void updateFactoryRawMaterial(FactoryBinStoredProductEntity factoryBinStoredProduct, Double quantity) {
        factoryBinStoredProduct.getFactoryRawMaterial().setUnrestrictedInventory(factoryBinStoredProduct.getFactoryRawMaterial().getUnrestrictedInventory() - quantity);
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
