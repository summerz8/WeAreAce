/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory.MRP;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.RawMaterialAmountEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import java.util.Calendar;
import java.util.List;
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
 * @author apple
 */
@Entity
@Table(name = "RetaiProductPlannedOrder")
public class RetailProductPlannedOrderEntity {
        private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long retailPlannedOrderId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar date;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetSalesStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetSalesEndDate;
    private String status;//waiting, processing, cancelled, and accomplished
    
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private FactoryRetailProductEntity retailProduct;
    
    //planned order entity -- purchase order entity: 1 <--> M
    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy = "plannedOrder")
    private List<PurchaseOrderEntity> purchaseOrder = null;
    
    @ManyToOne
    private FactoryEntity factory;

    public RetailProductPlannedOrderEntity() {
    }


    public void createRetailProductPlannedOrder(Calendar date,
                                   Calendar targetStart,
                                   Calendar targetEnd,
                                   String status,
                                   FactoryRetailProductEntity retailproduct,
                                   FactoryEntity factory
                                   ){
        this.date=date;
        this.targetSalesStartDate=targetStart;
        this.targetSalesEndDate=targetEnd;
        this.status=status;
        this.retailProduct=retailproduct;
        this.factory=factory;
            }

    public void setPlannedOrderId(Long plannedOrderId) {
        this.retailPlannedOrderId = plannedOrderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Calendar getTargetSalesStartDate() {
        return targetSalesStartDate;
    }

    public void setTargetSalesStartDate(Calendar targetSalesStartDate) {
        this.targetSalesStartDate = targetSalesStartDate;
    }

    public Calendar getTargetSalesEndDate() {
        return targetSalesEndDate;
    }

    public void setTargetSalesEndDate(Calendar targetSalesEndDate) {
        this.targetSalesEndDate = targetSalesEndDate;
    }


    public Long getPlannedOrderId() {
        return retailPlannedOrderId;
    }

    public Calendar getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public List<PurchaseOrderEntity> setPurchaseOrder(){
        return purchaseOrder;
    }


    public List<PurchaseOrderEntity> getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(List<PurchaseOrderEntity> purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factory) {
        this.factory = factory;
    }

    public FactoryRetailProductEntity getRetailProduct() {
        return retailProduct;
    }

    public void setRetailProduct(FactoryRetailProductEntity retailProduct) {
        this.retailProduct = retailProduct;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retailPlannedOrderId != null ? retailPlannedOrderId.hashCode() : 0);
        return hash;
    }

//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof RetailProductPlannedOrderEntity)) {
//            return false;
//        }
//        PlannedOrderEntity other = (PlannedOrderEntity) object;
//        if ((this.retailPlannedOrderId == null && other.retailPlannedOrderId != null) || (this.retailPlannedOrderId != null && !this.retailPlannedOrderId.equals(other.retailPlannedOrderId))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "Entity.Factory.PlannedOrderEntity[ id=" + retailPlannedOrderId + " ]";
    }
}
