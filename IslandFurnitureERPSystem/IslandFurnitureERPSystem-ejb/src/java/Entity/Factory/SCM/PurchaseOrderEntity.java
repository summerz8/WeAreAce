/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.RawMaterialEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name = "PurchaseOrder")
public class PurchaseOrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchasOrderId;
    private String status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createDate;

    //goods receipt entity -- purchase order entity : 1 <--> 1
    @OneToOne(mappedBy = "purchaseOrder")
    private GoodsReceiptEntity goodsReceipt;
    
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "purchaseOrder")
    private List<PlannedOrderEntity> plannedOrder;

    private SupplierEntity supplierID;
    private List<RawMaterialEntity> purchaseItem;

    public PurchaseOrderEntity() {
    }

    public PurchaseOrderEntity(Long purchasOrderId, String status) {
        this.purchasOrderId = purchasOrderId;
        this.status = status;
    }

    public Long getPurchasOrderId() {
        return purchasOrderId;
    }

    public void setPurchasOrderId(Long purchasOrderId) {
        this.purchasOrderId = purchasOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public GoodsReceiptEntity getGoodsReceipt() {
        return goodsReceipt;
    }

    public void setGoodsReceipt(GoodsReceiptEntity goodsReceipt) {
        this.goodsReceipt = goodsReceipt;
    }

    public List<PlannedOrderEntity> getPlannedOrder() {
        return plannedOrder;
    }

    public void setPlannedOrder(List<PlannedOrderEntity> plannedOrder) {
        this.plannedOrder = plannedOrder;
    }

    public SupplierEntity getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(SupplierEntity supplierID) {
        this.supplierID = supplierID;
    }

    public List<RawMaterialEntity> getPurchaseItem() {
        return purchaseItem;
    }

    public void setPurchaseItem(List<RawMaterialEntity> purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchasOrderId != null ? purchasOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrderEntity)) {
            return false;
        }
        PurchaseOrderEntity other = (PurchaseOrderEntity) object;
        if ((this.purchasOrderId == null && other.purchasOrderId != null) || (this.purchasOrderId != null && !this.purchasOrderId.equals(other.purchasOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.PurchaseOrderEntity[ id=" + purchasOrderId + " ]";
    }

}
