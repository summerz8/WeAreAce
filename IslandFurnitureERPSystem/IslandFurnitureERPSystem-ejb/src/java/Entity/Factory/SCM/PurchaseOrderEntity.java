/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.RawMaterialEntity;
import java.io.Serializable;
import java.util.Calendar;
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
// one purchase order for one raw material from the same supplier during a specified time period

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchasOrderId;
    private String status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar createDate;

    //goods receipt entity -- purchase order entity : 1 <--> 1
    @OneToOne(mappedBy = "purchaseOrder")
    private GoodsReceiptEntity goodsReceipt;
    //purchase order entity -- planned order entity : M <--> M 
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "purchaseOrders")
    private List<PlannedOrderEntity> plannedOrders;

    //purchase order entity -- contract entity: M --> 1
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private ContractEntity contract;

//    private SupplierEntity supplierID;  //no Id
//    private List<RawMaterialEntity> purchaseItems;   //only one item
    private double total; // the total price

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

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public GoodsReceiptEntity getGoodsReceipt() {
        return goodsReceipt;
    }

    public void setGoodsReceipt(GoodsReceiptEntity goodsReceipt) {
        this.goodsReceipt = goodsReceipt;
    }

    public List<PlannedOrderEntity> getPlannedOrders() {
        return plannedOrders;
    }

    public void setPlannedOrders(List<PlannedOrderEntity> plannedOrders) {
        this.plannedOrders = plannedOrders;
    }
//
//    public SupplierEntity getSupplierID() {
//        return supplierID;
//    }
//
//    public void setSupplierID(SupplierEntity supplierID) {
//        this.supplierID = supplierID;
//    }

//    public List<RawMaterialEntity> getPurchaseItems() {
//        return purchaseItems;
//    }
//
//    public void setPurchaseItems(List<RawMaterialEntity> purchaseItems) {
//        this.purchaseItems = purchaseItems;
//    }
    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
