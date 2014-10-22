/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.ACRM;

import SessionBean.ACRM.AnalyticalCRMSessionBeanLocal;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author summer
 */
@Named(value = "viewRetentionRate")
@ViewScoped
public class ViewRetentionRate {
    private AnalyticalCRMSessionBeanLocal acrm;
    
    private Double monthlyRR;
    private Double yearlyRR;

    @PostConstruct
    public void init(){
        try {
//            Double getRetentionRate(Long storeId, Calendar time, Integer location,
//            Boolean isForAllPlace, Boolean isMonthly) 
//            
//            storeId = (Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
//
//            monthlyRR = acrm.getRetentionRate(storeId, time, , Boolean.FALSE, Boolean.TRUE);
//            yearlyRR = acrm.getRetentionRate(storeId, time, Integer.SIZE, Boolean.FALSE, Boolean.FALSE);
        } catch (Exception ex) {
            Logger.getLogger(ViewRetentionRate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public ViewRetentionRate() {
    }

    
}
