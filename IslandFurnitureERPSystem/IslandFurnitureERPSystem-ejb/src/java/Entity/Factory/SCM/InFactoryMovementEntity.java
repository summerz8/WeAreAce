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
@Table(name="InFactoryMovement")
public class InFactoryMovementEntity implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inFactoryMovementId;
    @ManyToOne
    private FactoryBinEntity fromBin;
    @ManyToOne                                                                                                                                                             
    private FactoryBinEntity toBin;

    public Long getInFactoryMovementId() {
        return inFactoryMovementId;
    }

    public void setInFactoryMovementId(Long inFactoryMovementId) {
        this.inFactoryMovementId = inFactoryMovementId;
    }

    public FactoryBinEntity getFromBin() {
        return fromBin;
    }

    public void setFromBin(FactoryBinEntity fromBin) {
        this.fromBin = fromBin;
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
        hash += (inFactoryMovementId != null ? inFactoryMovementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the inFactoryMovementId fields are not set
        if (!(object instanceof InFactoryMovementEntity)) {
            return false;
        }
        InFactoryMovementEntity other = (InFactoryMovementEntity) object;
        if ((this.inFactoryMovementId == null && other.inFactoryMovementId != null) || (this.inFactoryMovementId != null && !this.inFactoryMovementId.equals(other.inFactoryMovementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.SCM.InFactoryMovementEntity[ id=" + inFactoryMovementId + " ]";
    }
    
}
