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
import javax.persistence.OneToOne;

/**
 *
 * @author apple
 */
@Entity
public class SalesOperationPlanEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private String FactoryId;
    private Integer productionPlan;
    private Calendar period;
    private Integer salesForecast;
    private Integer plannedEndMonthInventory;
    private Integer workingDay;

    public SalesOperationPlanEntity(Long productId, String FactoryId, Integer productionPlan, Calendar period, Integer salesForecast, Integer plannedEndMonthInventory, Integer workingDay) {
  
        this.productId = productId;
        this.FactoryId = FactoryId;
        this.productionPlan = productionPlan;
        this.period = period;
        this.salesForecast = salesForecast;
        this.plannedEndMonthInventory = plannedEndMonthInventory;
        this.workingDay = workingDay;
    }
    
    public void EditOperationPlanEntity(Long productId, String FactoryId, Integer productionPlan, Calendar period, Integer salesForecast, Integer plannedEndMonthInventory, Integer workingDay) {
  
        this.productId = productId;
        this.FactoryId = FactoryId;
        this.productionPlan = productionPlan;
        this.period = period;
        this.salesForecast = salesForecast;
        this.plannedEndMonthInventory = plannedEndMonthInventory;
        this.workingDay = workingDay;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSalesForecast() {
        return salesForecast;
    }

    public void setSalesForecast(Integer salesForecast) {
        this.salesForecast = salesForecast;
    }

    public Integer getPlannedEndMonthInventory() {
        return plannedEndMonthInventory;
    }

    public void setPlannedEndMonthInventory(Integer plannedEndMonthInventory) {
        this.plannedEndMonthInventory = plannedEndMonthInventory;
    }

    public Integer getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(Integer workingDay) {
        this.workingDay = workingDay;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getFactoryId() {
        return FactoryId;
    }

    public void setFactoryId(String FactoryId) {
        this.FactoryId = FactoryId;
    }

    public Integer getProductionPlan() {
        return productionPlan;
    }

    public void setProductionPlan(Integer productionPlan) {
        this.productionPlan = productionPlan;
    }

    public Calendar getPeriod() {
        return period;
    }

    public void setPeriod(Calendar period) {
        this.period = period;
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
        if (!(object instanceof SalesOperationPlanEntity)) {
            return false;
        }
        SalesOperationPlanEntity other = (SalesOperationPlanEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.MRP.DemandManagementEntity[ id=" + id + " ]";
    }
    
}
