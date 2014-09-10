/*
 * FactoryBinStoredProductEntity.java
 *
 * This entity is a functional entity.
 * Used as a quantity record of a specific item in a specific bin.
 */
package Entity.Factory.FacotryBin;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.SCM.InFactoryMovementEntity;
import Entity.Factory.SCM.InboundMovementEntity;
import Entity.Factory.SCM.OutboundMovementEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author shiyu
 */
@Entity
@Table(name = "FactoryStoredProduct")
public class FactoryBinStoredProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryBinStoredProductId;
    private double amount = 0;

    //factory rawMaterial entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryRawMaterialEntity factoryRawMaterial;

    //factory product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryProductEntity factoryProduct;

    //factory retail product entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryRetailProductEntity factoryRetailProduct;

    //factory bin entity -- factory bin stored products entity: 1 <--> M 
    @ManyToOne
    private FactoryBinEntity factoryBin;

    //factory bin stored product entity -- in factory movements: 1 <--> M (from which bin)
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "fromBin")
    private Collection<InFactoryMovementEntity> inFactoryMovements_from = new ArrayList<InFactoryMovementEntity>();

    //factory bin stored product entity -- in factory movements: 1 <--> M (to which bin)
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "toBin")
    private Collection<InFactoryMovementEntity> inFactoryMovements_to = new ArrayList<InFactoryMovementEntity>();

    //factory bin stored product entity -- outbound movements: 1 <--> M (from which bin)
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "fromBin")
    private Collection<OutboundMovementEntity> outboundMovements_from = new ArrayList<OutboundMovementEntity>();

    //factory bin stored product entity -- inbound movements: 1 <--> M (to which bin)
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "toBin")
    private Collection<InboundMovementEntity> inboundMovements = new ArrayList<InboundMovementEntity>();

    public FactoryBinStoredProductEntity() {
    }

    public Long getFactoryBinStoredProductId() {
        return factoryBinStoredProductId;
    }

    public void setFactoryBinStoredProductId(Long id) {
        this.factoryBinStoredProductId = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public FactoryBinEntity getFactoryBin() {
        return factoryBin;
    }

    public void setFactoryBin(FactoryBinEntity factoryBin) {
        this.factoryBin = factoryBin;
    }

    public Collection<InFactoryMovementEntity> getInFactoryMovements_from() {
        return inFactoryMovements_from;
    }

    public void setInFactoryMovements_from(Collection<InFactoryMovementEntity> inFactoryMovements_from) {
        this.inFactoryMovements_from = inFactoryMovements_from;
    }

    public Collection<InFactoryMovementEntity> getInFactoryMovements_to() {
        return inFactoryMovements_to;
    }

    public void setInFactoryMovements_to(Collection<InFactoryMovementEntity> inFactoryMovements_to) {
        this.inFactoryMovements_to = inFactoryMovements_to;
    }

    public Collection<OutboundMovementEntity> getOutboundMovements_from() {
        return outboundMovements_from;
    }

    public void setOutboundMovements_from(Collection<OutboundMovementEntity> outboundMovements_from) {
        this.outboundMovements_from = outboundMovements_from;
    }

    public Collection<InboundMovementEntity> getInboundMovements() {
        return inboundMovements;
    }

    public void setInboundMovements(Collection<InboundMovementEntity> inboundMovements) {
        this.inboundMovements = inboundMovements;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factoryBinStoredProductId != null ? factoryBinStoredProductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactoryBinStoredProductEntity)) {
            return false;
        }
        FactoryBinStoredProductEntity other = (FactoryBinStoredProductEntity) object;
        if ((this.factoryBinStoredProductId == null && other.factoryBinStoredProductId != null) || (this.factoryBinStoredProductId != null && !this.factoryBinStoredProductId.equals(other.factoryBinStoredProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.StoredProduct[ id=" + factoryBinStoredProductId + " ]";
    }

}
