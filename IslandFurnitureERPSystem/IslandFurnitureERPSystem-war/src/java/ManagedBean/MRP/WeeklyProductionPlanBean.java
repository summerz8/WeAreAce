/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.MRP;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "weeklyProductionPlanBean")
@ViewScoped
public class WeeklyProductionPlanBean {

    private Long productionPlanId;
    /**
     * Creates a new instance of WeeklyProductionPlanBean
     */
    public WeeklyProductionPlanBean() {
    }

    public Long getProductionPlanId() {
        return productionPlanId;
    }

    public void setProductionPlanId(Long productionPlanId) {
        this.productionPlanId = productionPlanId;
    }
    
    public String getProduction(Long productionPlanId){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productionPlanId", productionPlanId);
        return "CreateWeeklyProductionPlan";
    
    }
}
