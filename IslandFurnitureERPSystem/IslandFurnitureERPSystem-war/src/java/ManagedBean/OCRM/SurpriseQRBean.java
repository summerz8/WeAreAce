/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.OCRM;

import Entity.Store.OCRM.SurpriseQREntity;
import SessionBean.OCRM.SurpriseQRBeanLocal;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author dan
 */
@Named(value = "surpriseQRBean")
@ViewScoped
public class SurpriseQRBean implements Serializable{
    @EJB
    SurpriseQRBeanLocal sqb;
    
    
    private List<SurpriseQREntity> surpriseQRList;
    private List<SurpriseQREntity> filteredFeedback;
    private String name;
    private Double percentage;
    private Double rewardPoints;
    private Date expireDate;
    private Calendar expireDay;
    private String path;
    private String fileName;
    /**
     * Creates a new instance of SurpriseQRBean
     */
    public SurpriseQRBean() {
    }
    
    @PostConstruct
    public void init(){
        surpriseQRList = sqb.getAllQR();
        filteredFeedback = surpriseQRList;
    }
    
    public String createSurpriseQR() throws Exception{
        expireDay = Calendar.getInstance();
        expireDay.setTime(expireDate);
        System.out.println("UserInfoManageBean: birString to Date to Calendar:" + expireDay.getTime().toString());

        System.out.println("createSurpriseQR(): ");
        path = sqb.createQR(percentage, rewardPoints, expireDay, name);
        System.out.println("path "+path);
        return "/secured/restricted/Store/OCRM/Surprise/viewSurpriseQR?faces-redirect=true";
    }

    public SurpriseQRBeanLocal getSqb() {
        return sqb;
    }

    public void setSqb(SurpriseQRBeanLocal sqb) {
        this.sqb = sqb;
    }

    public List<SurpriseQREntity> getSurpriseQRList() {
        return surpriseQRList;
    }

    public void setSurpriseQRList(List<SurpriseQREntity> surpriseQRList) {
        this.surpriseQRList = surpriseQRList;
    }

    public List<SurpriseQREntity> getFilteredFeedback() {
        return filteredFeedback;
    }

    public void setFilteredFeedback(List<SurpriseQREntity> filteredFeedback) {
        this.filteredFeedback = filteredFeedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Calendar getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Calendar expireDay) {
        this.expireDay = expireDay;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
}
