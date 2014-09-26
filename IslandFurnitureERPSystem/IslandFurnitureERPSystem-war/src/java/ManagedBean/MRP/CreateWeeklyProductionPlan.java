/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.MRP;

import Entity.Factory.MRP.WeeklyProductionPlanEntity;
import SessionBean.MRP.WeeklyProductionPlanLocal;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author apple
 */
@Named(value = "createWeeklyProductionPlan")
@ViewScoped
public class CreateWeeklyProductionPlan {

    @EJB 
    WeeklyProductionPlanLocal weekly;
    
    Calendar period;
    Long productionPlanId;
    Double monthlyDemand;
    
    List<WeeklyProductionPlanEntity> weeklyProductionPlanList;
    /**
     * Creates a new instance of CreateWeeklyProductionPlan
     */
    public CreateWeeklyProductionPlan() {
    }
    
    @PostConstruct
    public void create(){
          productionPlanId=(Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productionPlanId");
          weeklyProductionPlanList=weekly.generateWeeklyProductionPlan(productionPlanId);
          period=weeklyProductionPlanList.get(0).getProductionPlan().getTargetPeriod();
          monthlyDemand=weeklyProductionPlanList.get(0).getProductionPlan().getQuantity();
          
    }

    public WeeklyProductionPlanLocal getWeekly() {
        return weekly;
    }

    public void setWeekly(WeeklyProductionPlanLocal weekly) {
        this.weekly = weekly;
    }

    public Long getProductionPlanId() {
        return productionPlanId;
    }

    public void setProductionPlanId(Long productionPlanId) {
        this.productionPlanId = productionPlanId;
    }

    public List<WeeklyProductionPlanEntity> getWeeklyProductionPlanList() {
        return weeklyProductionPlanList;
    }

    public void setWeeklyProductionPlanList(List<WeeklyProductionPlanEntity> weeklyProductionPlanList) {
        this.weeklyProductionPlanList = weeklyProductionPlanList;
    }

    public Calendar getPeriod() {
        return period;
    }

    public void setPeriod(Calendar period) {
        this.period = period;
    }

    public Double getMonthlyDemand() {
        return monthlyDemand;
    }

    public void setMonthlyDemand(Double monthlyDemand) {
        this.monthlyDemand = monthlyDemand;
    }
 
    
}
