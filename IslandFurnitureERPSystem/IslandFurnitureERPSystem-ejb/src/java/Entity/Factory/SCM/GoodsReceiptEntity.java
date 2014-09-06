/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.CommonInfrastructure.FactoryUserEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Yoky
 */
@Entity
@Table(name = "GoodsReceipt")
public class GoodsReceiptEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long goodsReceiptId;    // the same as the corresponding purchaseOrderId. Assumption: All the raw materials ordered by the same purchase order are received together.
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    @OneToOne(mappedBy = "goodsReceipt", cascade = {CascadeType.PERSIST})
    private PurchaseOrderEntity purchaseOder;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private FactoryUserEntity personInCharge;
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "goodsReceipt")
    private Collection<InboundMovementEntity> inboundMovement = new ArrayList<InboundMovementEntity>(); 

    public GoodsReceiptEntity() {
    }

    public GoodsReceiptEntity(PurchaseOrderEntity purchaseOder) {
        this.purchaseOder = purchaseOder;
        this.goodsReceiptId = purchaseOder.getPurchasOrderId();
    }

    public Long getGoodsReceiptId() {
        return goodsReceiptId;
    }

    public void setGoodsReceiptId(Long goodsReceiptId) {
        this.goodsReceiptId = goodsReceiptId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public PurchaseOrderEntity getPurchaseOder() {
        return purchaseOder;
    }

    public void setPurchaseOder(PurchaseOrderEntity purchaseOder) {
        this.purchaseOder = purchaseOder;
    }

    public FactoryUserEntity getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(FactoryUserEntity personInCharge) {
        this.personInCharge = personInCharge;
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
        hash += (goodsReceiptId != null ? goodsReceiptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GoodsReceiptEntity)) {
            return false;
        }
        GoodsReceiptEntity other = (GoodsReceiptEntity) object;
        if ((this.goodsReceiptId == null && other.goodsReceiptId != null) || (this.goodsReceiptId != null && !this.goodsReceiptId.equals(other.goodsReceiptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.GoodsReceiptEntity[ id=" + goodsReceiptId + " ]";
    }

}
