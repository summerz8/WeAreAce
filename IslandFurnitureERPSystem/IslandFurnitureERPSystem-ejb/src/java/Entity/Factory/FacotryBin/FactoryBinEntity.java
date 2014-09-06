/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.FacotryBin;

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
import javax.persistence.OneToMany;

/**
 *
 * @author zhangshiyu
 */
@Entity
public class FactoryBinEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryBinId;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "fromBin")
    private Collection<InFactoryMovementEntity> inFactoryMovements_from = new ArrayList<InFactoryMovementEntity>();
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "toBin")
    private Collection<InFactoryMovementEntity> inFactoryMovements_to = new ArrayList<InFactoryMovementEntity>();
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "fromBin")
    private Collection<OutboundMovementEntity> outboundMovements_from = new ArrayList<OutboundMovementEntity>();
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "toBin")
    private Collection<InboundMovementEntity> inboundMovements = new ArrayList<InboundMovementEntity>();

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryBin")
    private Collection<FactoryBinStoredProductEntity> factoryBinStoredProducts = new ArrayList<FactoryBinStoredProductEntity>();
    public Long getFactoryBinId() {
        return factoryBinId;
    }

    public void setFactoryBinId(Long factoryBinId) {
        this.factoryBinId = factoryBinId;
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

    public Collection<FactoryBinStoredProductEntity> getFactoryBinStoredProducts() {
        return factoryBinStoredProducts;
    }

    public void setFactoryBinStoredProducts(Collection<FactoryBinStoredProductEntity> factoryBinStoredProducts) {
        this.factoryBinStoredProducts = factoryBinStoredProducts;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factoryBinId != null ? factoryBinId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the factoryBinId fields are not set
        if (!(object instanceof FactoryBinEntity)) {
            return false;
        }
        FactoryBinEntity other = (FactoryBinEntity) object;
        if ((this.factoryBinId == null && other.factoryBinId != null) || (this.factoryBinId != null && !this.factoryBinId.equals(other.factoryBinId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.FacotryBin.FactoryBinEntity[ id=" + factoryBinId + " ]";
    }

}
