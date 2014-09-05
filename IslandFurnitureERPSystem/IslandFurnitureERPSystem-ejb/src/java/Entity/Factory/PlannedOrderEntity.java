/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */
@Entity(name="Planned Order")
public class PlannedOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long plannedOrderId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private String targetPeriod;
    private String status;
    private List<Long> purchaseOrderID;
    @OneToOne(cascade={CascadeType.PERSIST})
    private ProductionPlanEntity productionPlan; 

    
    PlannedOrderEntity() {
    }
     
    public PlannedOrderEntity(Long plannedOrderId, Date date,String targetPeriod, String status, BOMEntity bom) {
        this.plannedOrderId = plannedOrderId;
        this.targetPeriod = targetPeriod;
        this.status = status;
        this.date = date;
    }
    
    public void setPlannedOrderId(Long plannedOrderId) {
        this.plannedOrderId = plannedOrderId;
    }

    public void setTargetPeriod(String targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPlannedOrderId() {
        return plannedOrderId;
    }

    public Date getDate() {
        return date;
    }
  
    public String getTargetPeriod() {
        return targetPeriod;
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
