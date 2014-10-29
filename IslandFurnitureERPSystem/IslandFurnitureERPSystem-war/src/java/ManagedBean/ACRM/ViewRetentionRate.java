/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.ACRM;

import SessionBean.ACRM.AnalyticalCRMSessionBeanLocal;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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

    @EJB
    private AnalyticalCRMSessionBeanLocal acrm;

    private Calendar time;
    private Long storeId;
    private Double monthlyRRR;
    private Double monthlyKRR;
    private Double monthlyFRR;

    private Double quarterlyRRR;
    private Double quarterlyKRR;
    private Double quarterlyFRR;

    private Double yearlyRRR;
    private Double yearlyKRR;
    private Double yearlyFRR;

    private Double monthlyRRForAll;
    private Double quarterlyRRForAll;
    private Double yearlyRRForAll;

    @PostConstruct
    public void init() {
        try {
            time = Calendar.getInstance(Locale.ENGLISH);
            System.out.println("ManagedBean: Time = " + time.getTime().toString());
            storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            
            System.out.println("Managed Bean: viewRetentionRate: ");            
            System.out.println("StoreId: " + storeId);

            monthlyFRR = acrm.getRetentionRate(storeId, time, 1, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
            monthlyRRR = acrm.getRetentionRate(storeId, time, 2, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
            monthlyKRR = acrm.getRetentionRate(storeId, time, 3, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);

            quarterlyFRR = acrm.getRetentionRate(storeId, time, 1, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
            quarterlyRRR = acrm.getRetentionRate(storeId, time, 2, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
            quarterlyKRR = acrm.getRetentionRate(storeId, time, 3, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);

            yearlyFRR = acrm.getRetentionRate(storeId, time, 1, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
            yearlyRRR = acrm.getRetentionRate(storeId, time, 2, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
            yearlyKRR = acrm.getRetentionRate(storeId, time, 3, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);

            monthlyRRForAll = acrm.getRetentionRate(storeId, time, 0, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
            quarterlyRRForAll = acrm.getRetentionRate(storeId, time, 0, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
            yearlyRRForAll = acrm.getRetentionRate(storeId, time, 0, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);

        } catch (Exception ex) {
            Logger.getLogger(ViewRetentionRate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ViewRetentionRate() {
    }

    public AnalyticalCRMSessionBeanLocal getAcrm() {
        return acrm;
    }

    public void setAcrm(AnalyticalCRMSessionBeanLocal acrm) {
        this.acrm = acrm;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Double getMonthlyRRR() {
        return monthlyRRR;
    }

    public void setMonthlyRRR(Double monthlyRRR) {
        this.monthlyRRR = monthlyRRR;
    }

    public Double getMonthlyKRR() {
        return monthlyKRR;
    }

    public void setMonthlyKRR(Double monthlyKRR) {
        this.monthlyKRR = monthlyKRR;
    }

    public Double getMonthlyFRR() {
        return monthlyFRR;
    }

    public void setMonthlyFRR(Double monthlyFRR) {
        this.monthlyFRR = monthlyFRR;
    }

    public Double getQuarterlyRRR() {
        return quarterlyRRR;
    }

    public void setQuarterlyRRR(Double quarterlyRRR) {
        this.quarterlyRRR = quarterlyRRR;
    }

    public Double getQuarterlyKRR() {
        return quarterlyKRR;
    }

    public void setQuarterlyKRR(Double quarterlyKRR) {
        this.quarterlyKRR = quarterlyKRR;
    }

    public Double getQuarterlyFRR() {
        return quarterlyFRR;
    }

    public void setQuarterlyFRR(Double quarterlyFRR) {
        this.quarterlyFRR = quarterlyFRR;
    }

    public Double getYearlyRRR() {
        return yearlyRRR;
    }

    public void setYearlyRRR(Double yearlyRRR) {
        this.yearlyRRR = yearlyRRR;
    }

    public Double getYearlyKRR() {
        return yearlyKRR;
    }

    public void setYearlyKRR(Double yearlyKRR) {
        this.yearlyKRR = yearlyKRR;
    }

    public Double getYearlyFRR() {
        return yearlyFRR;
    }

    public void setYearlyFRR(Double yearlyFRR) {
        this.yearlyFRR = yearlyFRR;
    }

    public Double getMonthlyRRForAll() {
        return monthlyRRForAll;
    }

    public void setMonthlyRRForAll(Double monthlyRRForAll) {
        this.monthlyRRForAll = monthlyRRForAll;
    }

    public Double getQuarterlyRRForAll() {
        return quarterlyRRForAll;
    }

    public void setQuarterlyRRForAll(Double quarterlyRRForAll) {
        this.quarterlyRRForAll = quarterlyRRForAll;
    }

    public Double getYearlyRRForAll() {
        return yearlyRRForAll;
    }

    public void setYearlyRRForAll(Double yearlyRRForAll) {
        this.yearlyRRForAll = yearlyRRForAll;
    }
}
