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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "WeeklyProductionPlan")
public class WeeklyProductionPlanEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar productionMonth;
    private Integer week;
    private Integer workingDayInWeek;
    private Integer workingDayInMonth;
    private Double weeklyDemand;
    
    @ManyToOne
    private ProductionPlanEntity productionPlan;
    
    
    public WeeklyProductionPlanEntity(){
    }
    
    public void createWeeklyProductionPlan(Calendar month,Integer week,Integer workingDayInWeek,Integer workingDayInMonth,Double weeklyDemand){
        this.productionMonth = month;
        this.week = week;
        this.workingDayInWeek = workingDayInWeek;
        this.workingDayInMonth = workingDayInMonth;
        this.weeklyDemand = weeklyDemand;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getProductionMonth() {
        return productionMonth;
    }

    public void setProductionMonth(Calendar productionMonth) {
        this.productionMonth = productionMonth;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getWorkingDayInWeek() {
        return workingDayInWeek;
    }

    public void setWorkingDayInWeek(Integer workingDayInWeek) {
        this.workingDayInWeek = workingDayInWeek;
    }

    public Integer getWorkingDayInMonth() {
        return workingDayInMonth;
    }

    public void setWorkingDayInMonth(Integer workingDayInMonth) {
        this.workingDayInMonth = workingDayInMonth;
    }

    public Double getWeeklyDemand() {
        return weeklyDemand;
    }

    public void setWeeklyDemand(Double weeklyDemand) {
        this.weeklyDemand = weeklyDemand;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WeeklyProductionPlanEntity)) {
            return false;
        }
        WeeklyProductionPlanEntity other = (WeeklyProductionPlanEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Entity.Factory.MRP.WeeklyProductionPlanEntity[ id=" + id + " ]";
    }
    
}
