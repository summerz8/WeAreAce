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
    private Collection<InFactoryMovementEntity> inFactoryMovement_from = new ArrayList<InFactoryMovementEntity>();
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "toBin")
    private Collection<InFactoryMovementEntity> inFactoryMovement_to = new ArrayList<InFactoryMovementEntity>();
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "fromBin")
    private Collection<OutboundMovementEntity> outboundMovement_from = new ArrayList<OutboundMovementEntity>();
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "toBin")
    private Collection<InboundMovementEntity> inboundMovement = new ArrayList<InboundMovementEntity>();

    public Long getFactoryBinId() {
        return factoryBinId;
    }

    public void setFactoryBinId(Long factoryBinId) {
        this.factoryBinId = factoryBinId;
    }

    public Collection<InFactoryMovementEntity> getInFactoryMovement_from() {
        return inFactoryMovement_from;
    }

    public void setInFactoryMovement_from(Collection<InFactoryMovementEntity> inFactoryMovement_from) {
        this.inFactoryMovement_from = inFactoryMovement_from;
    }

    public Collection<InFactoryMovementEntity> getInFactoryMovement_to() {
        return inFactoryMovement_to;
    }

    public void setInFactoryMovement_to(Collection<InFactoryMovementEntity> inFactoryMovement_to) {
        this.inFactoryMovement_to = inFactoryMovement_to;
    }

    public Collection<OutboundMovementEntity> getOutboundMovement_from() {
        return outboundMovement_from;
    }

    public void setOutboundMovement_from(Collection<OutboundMovementEntity> outboundMovement_from) {
        this.outboundMovement_from = outboundMovement_from;
    }

    public Collection<InboundMovementEntity> getInboundMovement() {
        return inboundMovement;
    }

    public void setInboundMovement(Collection<InboundMovementEntity> inboundMovement) {
        this.inboundMovement = inboundMovement;
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
