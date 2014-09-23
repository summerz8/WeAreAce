/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure;

import Entity.CommonInfrastructure.InternalMessageReceive;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zhengyuan
 */
@Named(value = "deatilMessageBean")
@ViewScoped
public class DeatilMessageBean {

    /**
     * Creates a new instance of DeatilMessageBean
     */
    public DeatilMessageBean() {
    }
    
    private InternalMessageReceive detailMessage;
    private Boolean isReplyMessage;
    
    
    @PostConstruct
    public void init(){
        isReplyMessage = false;
        detailMessage = (InternalMessageReceive) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedMessage");
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("selectedMessage");
        System.out.println("DetailMessageBean: init(): " + detailMessage.getSenderId());
    }

    public void replyAMessage(ActionEvent event) throws IOException{
        String path = "/secured/CommonInfrastructure/replyAMessage.xhtml";
        isReplyMessage = true;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("repliedMessage", detailMessage );
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isReply",  isReplyMessage);
       
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);   
        
    }
    
    
    public String displayTime(Calendar calendarTime){ 
           System.err.println("Calendar Time:" + calendarTime.getTime());
           SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss");
           String time = sdf.format(calendarTime.getTime()).toString();
           return time;
    }
    
    
    public InternalMessageReceive getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(InternalMessageReceive detailMessage) {
        this.detailMessage = detailMessage;
    }
  
    
}
