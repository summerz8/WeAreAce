/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.MRP;

import Entity.Factory.MRP.WeeklyProductionPlanEntity;
import SessionBean.MRP.WeeklyProductionPlanLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "weeklyProductionPlanBean")
@ViewScoped
public class WeeklyProductionPlanBean {

    @EJB
    private WeeklyProductionPlanLocal WPO;
    
//    private Long productionPlanId;
    private List<WeeklyProductionPlanEntity> selectedWeeklyProductionPlanList;
    private Double demand;
    /**
     * Creates a new instance of WeeklyProductionPlanBean
     */
    public WeeklyProductionPlanBean() {
    }

//    public Long getProductionPlanId() {
//        return productionPlanId;
//    }
//
//    public void setProductionPlanId(Long productionPlanId) {
//        this.productionPlanId = productionPlanId;
//    }

    public Double getDemand() {
        return demand;
    }

    public void setDemand(Double demand) {
        this.demand = demand;
    }   

    public List<WeeklyProductionPlanEntity> getSelectedWeeklyProductionPlanList() {
        return selectedWeeklyProductionPlanList;
    }

    public void setSelectedWeeklyProductionPlanList(List<WeeklyProductionPlanEntity> selectedWeeklyProductionPlanList) {
        this.selectedWeeklyProductionPlanList = selectedWeeklyProductionPlanList;
    }  
       
//    public String getProduction(Long productionPlanId){
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productionPlanId", productionPlanId);
//        return "CreateWeeklyProductionPlan";
//    
//    }
    
    @PostConstruct
    public void init(){
       selectedWeeklyProductionPlanList =(List<WeeklyProductionPlanEntity>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedWeeklyProductionPlan");
    }
    
    public void saveId(Long id){
        WPO.Edit(id,"demand",demand);
    }
    
    public void demandChanged(ValueChangeEvent event) {
        Object newValue = event.getNewValue();
        demand = (Double) newValue;
    }
    
}