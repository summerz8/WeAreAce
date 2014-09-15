/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchasOrderId;
    private String status;
    private Integer amount = 0;
    private String unit;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar createDate;
    private String destination;
    private Integer leadTime;

    //purchase order entity -- factory entity: M <--> 1 
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private FactoryEntity factory;

    //goods receipt entity -- purchase order entity : 1 <--> 1
    @OneToOne(mappedBy = "purchaseOrder")
    private GoodsReceiptEntity goodsReceipt;

    //integrated planned order entity -- purchase order entity: 1 <--> M
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private IntegratedPlannedOrderEntity integratedPlannedOrder;

    //purchase order entity -- contract entity: M --> 1
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private ContractEntity contract;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factory) {
        this.factory = factory;
    }

    public GoodsReceiptEntity getGoodsReceipt() {
        return goodsReceipt;
    }

    public void setGoodsReceipt(GoodsReceiptEntity goodsReceipt) {
        this.goodsReceipt = goodsReceipt;
    }

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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }
  
    public void create(FactoryEntity factory, ContractEntity contract, String status,
            Integer amount, String unit, String destination, Double total_price, Integer leadTime) {
        this.factory = factory;
        this.contract = contract;
        this.status = status;
        this.amount = amount;
        this.unit = unit;
        this.createDate = Calendar.getInstance();
        this.destination = destination;
        this.total = total_price;
        this.leadTime = leadTime;
    }

    public IntegratedPlannedOrderEntity getIntegratedPlannedOrder() {
        return integratedPlannedOrder;
    }

    public void setIntegratedPlannedOrder(IntegratedPlannedOrderEntity integratedPlannedOrder) {
        this.integratedPlannedOrder = integratedPlannedOrder;
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
