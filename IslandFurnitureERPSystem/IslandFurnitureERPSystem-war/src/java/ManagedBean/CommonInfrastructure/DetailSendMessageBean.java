/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import Entity.CommonInfrastructure.InternalMessageEntity;
import Entity.CommonInfrastructure.InternalMessageReceive;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zhengyuan
 */
@Named(value = "detailSendMessageBean")
@ViewScoped
public class DetailSendMessageBean {

    /**
     * Creates a new instance of DetailSendMessageBean
     */
    public DetailSendMessageBean() {
    }
    private InternalMessageEntity detailMessage;
    private Boolean isReplyMessage;

        @PostConstruct
    public void init(){
        isReplyMessage = false;
        detailMessage = (InternalMessageEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedSendMessage");
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("selectedSendMessage");
        System.out.println("DetailMessageBean: init(): " + detailMessage.getInternalMessageId());
    }
    public String displayTime(Calendar calendarTime) {
        System.err.println("Calendar Time:" + calendarTime.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss");
        String time = sdf.format(calendarTime.getTime()).toString();
        return time;
    }

    public InternalMessageEntity getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(InternalMessageEntity detailMessage) {
        this.detailMessage = detailMessage;
    }

    public Boolean isIsReplyMessage() {
        return isReplyMessage;
    }

    public void setIsReplyMessage(Boolean isReplyMessage) {
        this.isReplyMessage = isReplyMessage;
    }
    
    

}
