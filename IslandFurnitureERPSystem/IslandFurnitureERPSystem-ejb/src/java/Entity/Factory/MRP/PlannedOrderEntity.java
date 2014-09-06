/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.MRP;

import Entity.Factory.BOMEntity;
import Entity.Factory.RawMaterialAmountEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "PlannedOrder")
public class PlannedOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long plannedOrderId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date targetSalesStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date targetSalesEndDate;
    private String status;

    private List<RawMaterialAmountEntity> RawMaterialList;
    private List<Long> purchaseOrderID;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private ProductionPlanEntity productionPlan;

    public PlannedOrderEntity() {
    }

    public void createPlannedOrder(Date date,Date targetStart,Date targetEnd,String status,Long product,Integer Amount) {
    
    }

    public void setPlannedOrderId(Long plannedOrderId) {
        this.plannedOrderId = plannedOrderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTargetSalesStartDate() {
        return targetSalesStartDate;
    }

    public void setTargetSalesStartDate(Date targetSalesStartDate) {
        this.targetSalesStartDate = targetSalesStartDate;
    }

    public Date getTargetSalesEndDate() {
        return targetSalesEndDate;
    }

    public void setTargetSalesEndDate(Date targetSalesEndDate) {
        this.targetSalesEndDate = targetSalesEndDate;
    }

//    public List<RawMaterialEntity> getRawMaterialEntityList() {
//        return RawMaterialEntityList;
//    }
//
//    public void setRawMaterialEntityList(List<RawMaterialEntity> RawMaterialEntityList) {
//        this.RawMaterialEntityList = RawMaterialEntityList;
//    }

    public Long getPlannedOrderId() {
        return plannedOrderId;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public List<Long> getPurchaseOrders() {
        return purchaseOrderID;
    }

    public void setPurchaseOrderID(List<Long> purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
    }

    public ProductionPlanEntity getProductionPlan() {
        return productionPlan;
    }

    public void setProductionPlan(ProductionPlanEntity productionPlan) {
        this.productionPlan = productionPlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plannedOrderId != null ? plannedOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlannedOrderEntity)) {
            return false;
        }
        PlannedOrderEntity other = (PlannedOrderEntity) object;
        if ((this.plannedOrderId == null && other.plannedOrderId != null) || (this.plannedOrderId != null && !this.plannedOrderId.equals(other.plannedOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.PlannedOrderEntity[ id=" + plannedOrderId + " ]";
    }

}
