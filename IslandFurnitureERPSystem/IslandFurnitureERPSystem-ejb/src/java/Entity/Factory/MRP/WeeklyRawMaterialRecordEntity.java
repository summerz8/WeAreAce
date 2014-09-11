/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory.MRP;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author apple   这个record产生于每周最后一天
 */
@Entity
public class WeeklyRawMaterialRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Calendar date;
    private Double grossRequirement;
    private Double plannedReceipts;
    private Double scheduledReceipts;
    private Double onHand;
    private Double plannedOrder;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGrossRequirement() {
        return grossRequirement;
    }

    public void setGrossRequirement(Double grossRequirement) {
        this.grossRequirement = grossRequirement;
    }

    public Double getPlannedReceipts() {
        return plannedReceipts;
    }

    public void setPlannedReceipts(Double plannedReceipts) {
        this.plannedReceipts = plannedReceipts;
    }

    public Double getScheduledReceipts() {
        return scheduledReceipts;
    }

    public void setScheduledReceipts(Double scheduledReceipts) {
        this.scheduledReceipts = scheduledReceipts;
    }

    public Double getOnHand() {
        return onHand;
    }

    public void setOnHand(Double onHand) {
        this.onHand = onHand;
    }

    public Double getPlannedOrder() {
        return plannedOrder;
    }

    public void setPlannedOrder(Double plannedOrder) {
        this.plannedOrder = plannedOrder;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
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
        if (!(object instanceof WeeklyRawMaterialRecordEntity)) {
            return false;
        }
        WeeklyRawMaterialRecordEntity other = (WeeklyRawMaterialRecordEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.MRP.WeeklyRawMaterialRecord[ id=" + id + " ]";
    }
    
}
