/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory.MRP;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author apple
 */
@Entity(name = "SalesOperationPlan")
public class SalesOperationPlanEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade={CascadeType.PERSIST})
    private ProductionPlanEntity productionPlan;
    private Integer year;
    private String month;
    private Integer salesForecast;
    private Integer plannedEndMonthInventory;
    private Integer workingDay;
    
    public SalesOperationPlanEntity(){
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public ProductionPlanEntity getProductPlan() {
        return productionPlan;
    }

    public void setProductionPlan(ProductionPlanEntity productionPlan) {
        this.productionPlan = productionPlan;
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
