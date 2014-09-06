/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FacotryBin.FactoryBinEntity;
import Entity.Store.StoreEntity;
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
@Table(name="OutboundMovement")
public class OutboundMovementEntity extends FactoryMovementEntity implements Serializable {

//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @ManyToOne
    private FactoryBinEntity fromBin;
    @ManyToOne
    private StoreEntity toStore;

    public FactoryBinEntity getFromBin() {
        return fromBin;
    }

    public void setFromBin(FactoryBinEntity fromBin) {
        this.fromBin = fromBin;
    }

    public StoreEntity getToStore() {
        return toStore;
    }

    public void setToStore(StoreEntity toStore) {
        this.toStore = toStore;
    }
    
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof OutboundMovementEntity)) {
//            return false;
//        }
//        OutboundMovementEntity other = (OutboundMovementEntity) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "Entity.Factory.SCM.OutboundMovementEntity[ id=" + id + " ]";
//    }

}
