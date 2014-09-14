package Entity.Factory.MRP;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entity.Factory.FactoryEntity;
import Entity.Factory.RawMaterialAmountEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "PlannedOrder")
public class PlannedOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long plannedOrderId;
    @Temporal(javax.persistence.TemporalType.DATE)

    private Calendar date;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetSalesStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetSalesEndDate;
    private String status;
    @OneToMany(cascade = {CascadeType.PERSIST})
    private List<RawMaterialAmountEntity> rawMaterialList;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private ProductionPlanEntity productionplan;
    @ManyToMany(cascade={CascadeType.PERSIST})
    @JoinTable(name="PlannedOrder_PurchaseOrder")
    private List<PurchaseOrderEntity> purchaseOrder;
    @ManyToOne
    private FactoryEntity factory;

    public PlannedOrderEntity() {
    }


    public void createPlannedOrder(Calendar date,
                                   Calendar targetStart,
                                   Calendar targetEnd,
                                   String status,
                                   ProductionPlanEntity productionPlan, 
                                   List<RawMaterialAmountEntity> rawMaterialList) {
        this.date=date;
        this.targetSalesStartDate=targetStart;
        this.targetSalesEndDate=targetEnd;
        this.status=status;
        this.productionplan=productionPlan;
        this.rawMaterialList= rawMaterialList;
            }

    public void setPlannedOrderId(Long plannedOrderId) {
        this.plannedOrderId = plannedOrderId;
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

    public List<RawMaterialAmountEntity> getRawMaterialAmount() { // why not use the same name?
        return rawMaterialList;
    }

    public void setRawMaterialAmount(List<RawMaterialAmountEntity> rawMaterialList) {
        this.rawMaterialList = rawMaterialList;
    }

    public Long getPlannedOrderId() {
        return plannedOrderId;
    }

    public Calendar getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public ProductionPlanEntity getProductionPlan() {
        return productionplan;
    }

    public void setProductionPlan(ProductionPlanEntity productionPlan) {
        this.productionplan = productionPlan;
    }

    public List<PurchaseOrderEntity> setPurchaseOrder(){
        return purchaseOrder;
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
