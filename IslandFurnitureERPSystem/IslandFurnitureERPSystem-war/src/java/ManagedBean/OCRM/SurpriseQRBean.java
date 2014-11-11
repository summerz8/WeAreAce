/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.OCRM;

import Entity.Store.OCRM.SurpriseQREntity;
import SessionBean.OCRM.SurpriseQRBeanLocal;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;


/**
 *
 * @author dan
 */
@Named(value = "surpriseQRBean")
@ViewScoped
public class SurpriseQRBean {
    @EJB
    SurpriseQRBeanLocal sqb;
    
    
    private List<SurpriseQREntity> surpriseQRList;
    private List<SurpriseQREntity> filteredFeedback;
    private String name;
    private Double percentage;
    private Double rewardPoints;
    private Date expireDate;
    private Calendar expireDay;
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
        sqb.createQR(percentage, rewardPoints, expireDay, name);
        
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
    
    
}
