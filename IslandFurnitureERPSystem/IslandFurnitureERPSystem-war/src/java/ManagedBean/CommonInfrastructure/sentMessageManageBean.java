/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import Entity.CommonInfrastructure.InternalMessageEntity;
import Entity.CommonInfrastructure.InternalMessageReceive;
import SessionBean.CommonInFrastructure.InternalMessageModuleLocal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author zhengyuan
 */
@Named(value = "sentMessageManageBean")
@ViewScoped
public class sentMessageManageBean {

    /**
     * Creates a new instance of sentMessageManageBean
     */
    public sentMessageManageBean() {
    }

    private String currentUserId;

    private List<InternalMessageEntity> sentMessageList;

    private InternalMessageEntity selectedMessage;

    private List<InternalMessageEntity> sentMessageCheckList;

    private InternalMessageEntity checkedMessage;

    private List<InternalMessageEntity> checkedMessageList;

    private InternalMessageEntity deleteMessage;

    @EJB
    private InternalMessageModuleLocal im;

    @PostConstruct
    public void init() {

        System.err.println("hello");
        currentUserId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
        System.err.println("ID:" + currentUserId);
        sentMessageList = (List<InternalMessageEntity>) im.viewSendMessage(currentUserId);
        System.err.println("manageBean:receivemessage(): MessageSize:" + sentMessageList.size());
        //   FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("receiveMessageEntities", sentMessageList);
    }

    @PreDestroy
    public void destroy() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("receiveMessageEntities");
    }

    public void readMessage(InternalMessageEntity message) throws Exception {
        selectedMessage = message;

        String path = "/secured/CommonInfrastructure/viewSendMessageDetail.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedSendMessage", selectedMessage);

        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);
        System.err.println("go to another page");

    }
    
        public String displayTime(Calendar calendarTime){ 
           System.err.println("Calendar Time:" + calendarTime.getTime());
           SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss");
           String time = sdf.format(calendarTime.getTime()).toString();
           return time;
    }

       public void deleteMessage(List<InternalMessageEntity> messageList) throws Exception{
        
        for( InternalMessageEntity msg : messageList){
            im.deleteSendMessage(msg.getInternalMessageId());
        }
        
        sentMessageList = (List<InternalMessageEntity>) im.viewSendMessage(currentUserId);
        System.err.println("why did not print out?");

    }    
    
        
    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public List<InternalMessageEntity> getSentMessageList() {
        return sentMessageList;
    }

    public void setSentMessageList(List<InternalMessageEntity> sentMessageList) {
        this.sentMessageList = sentMessageList;
    }

    public InternalMessageEntity getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(InternalMessageEntity selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public List<InternalMessageEntity> getSentMessageCheckList() {
        return sentMessageCheckList;
    }

    public void setSentMessageCheckList(List<InternalMessageEntity> sentMessageCheckList) {
        this.sentMessageCheckList = sentMessageCheckList;
    }

    public InternalMessageEntity getCheckedMessage() {
        return checkedMessage;
    }

    public void setCheckedMessage(InternalMessageEntity checkedMessage) {
        this.checkedMessage = checkedMessage;
    }

    public List<InternalMessageEntity> getCheckedMessageList() {
        return checkedMessageList;
    }

    public void setCheckedMessageList(List<InternalMessageEntity> checkedMessageList) {
        this.checkedMessageList = checkedMessageList;
    }

    public InternalMessageEntity getDeleteMessage() {
        return deleteMessage;
    }

    public void setDeleteMessage(InternalMessageEntity deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

    public InternalMessageModuleLocal getIm() {
        return im;
    }

    public void setIm(InternalMessageModuleLocal im) {
        this.im = im;
    }
    
         public void onRowSelect(SelectEvent event) {
        FacesMessage sentmsg = new FacesMessage("Message Selected", String.valueOf(((InternalMessageEntity) event.getObject()).getInternalMessageId()));
        FacesContext.getCurrentInstance().addMessage(null, sentmsg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage sentmsg = new FacesMessage("Car Unselected", String.valueOf(((InternalMessageEntity) event.getObject()).getInternalMessageId()));
        FacesContext.getCurrentInstance().addMessage(null, sentmsg);
    }
         
    

}
