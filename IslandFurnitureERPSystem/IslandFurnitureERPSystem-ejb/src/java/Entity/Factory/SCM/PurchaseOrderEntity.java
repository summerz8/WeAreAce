/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.CommonInfrastructure.FactoryUserEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import java.io.Serializable;
import java.util.ArrayList;
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
 * @author Yoky
 */
@Entity
@Table(name = "PurchaseOrder")
public class PurchaseOrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchasOrderId;
    private String status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    @OneToOne
    private GoodsReceiptEntity goodsReceipt;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<PlannedOrderEntity> plannedOrders = new ArrayList<PlannedOrderEntity>();
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private SupplierContractEntity supplierContract;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private FactoryUserEntity personInCharge;

    public PurchaseOrderEntity() {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public SupplierContractEntity getSupplierContract() {
        return supplierContract;
    }

    public void setSupplierContract(SupplierContractEntity supplierContract) {
        this.supplierContract = supplierContract;
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
