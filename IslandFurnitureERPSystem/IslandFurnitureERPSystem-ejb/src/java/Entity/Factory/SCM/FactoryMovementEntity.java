/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.CommonInfrastructure.FactoryUserEntity;
import Entity.Factory.FactoryItemEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "FactoryMovement")
public abstract class FactoryMovementEntity implements Serializable {

    public FactoryMovementEntity() {
       setFactoryMovementId(System.nanoTime());
    }
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryMovementId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date movementDate;
    @ManyToOne
    private FactoryItemEntity factoryItem;
    private Integer quantity = 0;
    private String remark;
    @ManyToOne
    private FactoryUserEntity personInCharge;

    public Long getFactoryMovementId() {
        return factoryMovementId;
    }

    public void setFactoryMovementId(Long factoryMovementId) {
        this.factoryMovementId = factoryMovementId;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public FactoryItemEntity getFactoryItem() {
        return factoryItem;
    }

    public void setFactoryItem(FactoryItemEntity factoryItem) {
        this.factoryItem = factoryItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public FactoryUserEntity getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(FactoryUserEntity personInCharge) {
        this.personInCharge = personInCharge;
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
