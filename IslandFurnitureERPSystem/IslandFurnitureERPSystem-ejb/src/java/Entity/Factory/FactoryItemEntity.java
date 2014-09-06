/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory;

import Entity.Factory.SCM.FactoryMovementEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zhengyuan
 */
@Entity
@Table(name = "FactoryItem")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FactoryItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryItemId;
    private Integer quantity;
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryItem")
    private Collection<FactoryMovementEntity> factoryMovement = new ArrayList<FactoryMovementEntity>();

    public Long getFactoryItemId() {
        return factoryItemId;
    }

    public void setFactoryItemId(Long factoryItemId) {
        this.factoryItemId = factoryItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Collection<FactoryMovementEntity> getFactoryMovement() {
        return factoryMovement;
    }

    public void setFactoryMovement(Collection<FactoryMovementEntity> factoryMovement) {
        this.factoryMovement = factoryMovement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factoryItemId != null ? factoryItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the factoryItemId fields are not set
        if (!(object instanceof FactoryItemEntity)) {
            return false;
        }
        FactoryItemEntity other = (FactoryItemEntity) object;
        if ((this.factoryItemId == null && other.factoryItemId != null) || (this.factoryItemId != null && !this.factoryItemId.equals(other.factoryItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.FactoryInventoryEntity[ id=" + factoryItemId + " ]";
    }

}
