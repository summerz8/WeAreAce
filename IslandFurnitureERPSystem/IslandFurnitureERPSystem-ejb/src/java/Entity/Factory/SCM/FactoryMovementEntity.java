/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "FactoryMovement")
public class FactoryMovementEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryMovementId;

    public Long getId() {
        return factoryMovementId;
    }

    public void setId(Long id) {
        this.factoryMovementId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factoryMovementId != null ? factoryMovementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactoryMovementEntity)) {
            return false;
        }
        FactoryMovementEntity other = (FactoryMovementEntity) object;
        if ((this.factoryMovementId == null && other.factoryMovementId != null) || (this.factoryMovementId != null && !this.factoryMovementId.equals(other.factoryMovementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.FactoryMovementEntity[ id=" + factoryMovementId + " ]";
    }

}
