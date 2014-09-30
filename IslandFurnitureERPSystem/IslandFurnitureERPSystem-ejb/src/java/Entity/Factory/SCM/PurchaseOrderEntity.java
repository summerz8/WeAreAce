/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
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
    private Long id;
    private String status;//unconfirmed,confirmed, cancelled, accomplished
    private Double totalAmount;
    private String unit;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar createDate;
    private String destination;
    private Long destinationId;
    private Integer leadTime;
    private Double totalPrice; // the totalPrice price
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar deliveryDate;
    private Boolean isManual;
    private String itemName;
    //purchase order entity -- factory entity: M <--> 1 
    @ManyToOne
    private FactoryEntity factory;

    //goods receipt entity -- purchase order entity : M <--> 1
    @OneToMany(mappedBy = "purchaseOrder")
    private Collection<GoodsReceiptEntity> goodsReceiptList = new ArrayList<>();

    //integrated planned order entity -- purchase order entity: 1 <--> 1
    @OneToOne(mappedBy = "purchaseOrder")
    private IntegratedPlannedOrderEntity integratedPlannedOrder = null;

    //purchase order entity -- contract entity: M --> 1
    @ManyToOne
    private ContractEntity contract;

    //purchase order entity -- delivery order entity : 1 <--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "purchaseOrder")
    private Collection<DeliveryOrderEntity> deliveryOrderList = new ArrayList<>();

    public PurchaseOrderEntity() {
    }

    public PurchaseOrderEntity(String itemName, String status, Double totalAmount,
            String unit, Calendar createDate, String destination, Long destinationId,
            Integer leadTime, Double totalPrice, FactoryEntity factory,
            ContractEntity contract, Calendar deliveryDate, Boolean isManual) {
        this.itemName = itemName;
        this.status = status;
        this.totalAmount = totalAmount;
        this.unit = unit;
        this.createDate = createDate;
        this.destination = destination;
        this.destinationId = destinationId;
        this.leadTime = leadTime;
        this.totalPrice = totalPrice;
        this.factory = factory;
        this.contract = contract;
        this.deliveryDate = deliveryDate;
        this.isManual = isManual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
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

    public Collection<GoodsReceiptEntity> getGoodsReceiptList() {
        return goodsReceiptList;
    }

    public void setGoodsReceiptList(Collection<GoodsReceiptEntity> goodsReceiptList) {
        this.goodsReceiptList = goodsReceiptList;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Calendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public IntegratedPlannedOrderEntity getIntegratedPlannedOrder() {
        return integratedPlannedOrder;
    }

    public void setIntegratedPlannedOrder(IntegratedPlannedOrderEntity integratedPlannedOrder) {
        this.integratedPlannedOrder = integratedPlannedOrder;
    }

    public Boolean getIsManual() {
        return isManual;
    }

    public void setIsManual(Boolean isManual) {
        this.isManual = isManual;
    }
    

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Collection<DeliveryOrderEntity> getDeliveryOrderList() {
        return deliveryOrderList;
    }

    public void setDeliveryOrderList(Collection<DeliveryOrderEntity> deliveryOrderList) {
        this.deliveryOrderList = deliveryOrderList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrderEntity)) {
            return false;
        }
        PurchaseOrderEntity other = (PurchaseOrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.PurchaseOrderEntity[ id=" + id + " ]";
    }

}
