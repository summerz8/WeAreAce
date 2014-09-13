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
@Entity(name = "WeeklyProductionPlan")
public class WeeklyProductionPlanEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String month;
    private Integer week;
    private Integer workingDayInWeek;
    private Integer workingDayInMonth;
    private Integer weeklyDemand;
    
    @OneToOne(cascade={CascadeType.PERSIST})
    private ProductionPlanEntity productionPlan;
    
    @OneToOne(mappedBy="weeklyProductionPlan")
    private WeeklyRawMaterialRecordEntity weeklyRawMaterialRecord;
    
    public WeeklyProductionPlanEntity(){
    }
    
    public void createWeeklyProductionPlan(Long id,String month,Integer week,Integer workingDayInWeek,Integer workingDayInMonth,Integer weeklyDemand){
        this.id = id;
        this.month = month;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public Integer getWeeklyDemand() {
        return weeklyDemand;
    }

    public void setWeeklyDemand(Integer weeklyDemand) {
        this.weeklyDemand = weeklyDemand;
    }

    public ProductionPlanEntity getProductionPlan() {
        return productionPlan;
    }

    public void setProductionPlan(ProductionPlanEntity productionPlan) {
        this.productionPlan = productionPlan;
    }

    public WeeklyRawMaterialRecordEntity getRawMaterialRecord(){
        return weeklyRawMaterialRecord;
    }
    
    public void setWeeklyRawMaterialRecord(WeeklyRawMaterialRecordEntity weeklyRawMaterialRecord) {
        this.weeklyRawMaterialRecord = weeklyRawMaterialRecord;
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
