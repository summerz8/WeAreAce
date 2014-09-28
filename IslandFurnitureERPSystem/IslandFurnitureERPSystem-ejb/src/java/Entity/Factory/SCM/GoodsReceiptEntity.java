/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name = "GoodsReceipt")
public class GoodsReceiptEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodsReceiptId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar createDate;

    //goods receipt entity -- purchase order entity : M <--> 1
    @ManyToOne
    private PurchaseOrderEntity purchaseOrder;
    
    //goods receipt entity -- inbound movement enitty: 1 <--> M (from which corresponding goods receipt)
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "fromGoodsRecipt")
    private Collection<InboundMovementEntity> inboundMovements = new ArrayList<>();

    public GoodsReceiptEntity() {
    }

    public GoodsReceiptEntity(Long goodsReceiptId, Calendar createDate) {
        this.goodsReceiptId = goodsReceiptId;
        this.createDate = createDate;
    }

    public Long getGoodsReceiptId() {
        return goodsReceiptId;
    }

    public void setGoodsReceiptId(Long goodsReceiptId) {
        this.goodsReceiptId = goodsReceiptId;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public PurchaseOrderEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOder(PurchaseOrderEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
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
