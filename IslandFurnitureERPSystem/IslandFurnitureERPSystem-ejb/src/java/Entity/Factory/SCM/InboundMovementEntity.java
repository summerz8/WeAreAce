/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory.SCM;

import Entity.Factory.FacotryBin.FactoryBinEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name="InboundMovement")
public class InboundMovementEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long InboundMovementId;
    @ManyToOne
    private PurchaseOrderEntity fromGoodsRecipt;
    @ManyToOne                                                                                                                                                             
    private FactoryBinEntity toBin;

    public Long getInboundMovementId() {
        return InboundMovementId;
    }

    public void setInboundMovementId(Long InboundMovementId) {
        this.InboundMovementId = InboundMovementId;
    }

    public PurchaseOrderEntity getFromGoodsRecipt() {
        return fromGoodsRecipt;
    }

    public void setFromGoodsRecipt(PurchaseOrderEntity fromGoodsRecipt) {
        this.fromGoodsRecipt = fromGoodsRecipt;
    }
 
    public FactoryBinEntity getToBin() {
        return toBin;
    }

    public void setToBin(FactoryBinEntity toBin) {
        this.toBin = toBin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (InboundMovementId != null ? InboundMovementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the InboundMovementId fields are not set
        if (!(object instanceof InboundMovementEntity)) {
            return false;
        }
        InboundMovementEntity other = (InboundMovementEntity) object;
        if ((this.InboundMovementId == null && other.InboundMovementId != null) || (this.InboundMovementId != null && !this.InboundMovementId.equals(other.InboundMovementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.SCM.InboundMovementEntity[ id=" + InboundMovementId + " ]";
    }
    
}
