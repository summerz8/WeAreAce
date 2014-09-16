/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure;


import SessionBean.CommonInFrastructure.InternalMessageModuleLocal;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "InternalMessageBean")
@ManagedBean
@RequestScoped
public class InternalMessageManageBean {

    /**
     * Creates a new instance of InternalMessageManageBean
     */
    
    private String receiverIds;
    private String title;
    private String content;
    private String currentUserId;
    private String sendMessageStatus;
    private String sendMessageType;
    private String receiveMessageStatus;
    private String receiveMessageIsDelete;
    private String receiverIdString;
    private String statusMessage;
    
    @EJB
    private InternalMessageModuleLocal im;
        
        
    public InternalMessageManageBean() {
    }
    
    public InternalMessageModuleLocal getIm() {
        return im;
    }

    public void setIm(InternalMessageModuleLocal im) {
        this.im = im;
    }


    public String getReceiverIds() {
        return receiverIds;
    }

    public void setReceiverIds(String receiverIds) {
        this.receiverIds = receiverIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUser(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getSendMessageStatus() {
        return sendMessageStatus;
    }

    public void setSendMessageStatus(String sendMessageStatus) {
        this.sendMessageStatus = sendMessageStatus;
    }

    public String getSendMessageType() {
        return sendMessageType;
    }

    public void setSendMessageType(String sendMessageType) {
        this.sendMessageType = sendMessageType;
    }

    public String getReceiveMessageStatus() {
        return receiveMessageStatus;
    }

    public void setReceiveMessageStatus(String receiveMessageStatus) {
        this.receiveMessageStatus = receiveMessageStatus;
    }

    public String getReceiveMessageIsDelete() {
        return receiveMessageIsDelete;
    }

    public void setReceiveMessageIsDelete(String receiveMessageIsDelete) {
        this.receiveMessageIsDelete = receiveMessageIsDelete;
    }

    public String getReceiverIdString() {
        return receiverIdString;
    }

    public void setReceiverIdString(String receiverIdString) {
        this.receiverIdString = receiverIdString;
    }

    

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    
    
    public void sendNewMessage(ActionEvent event) throws Exception{
        
        
        try{
//          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userId", "123");
          currentUserId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
            im.sendMessage(currentUserId, title, content, null, null,  receiverIdString);
            statusMessage = "New Message Sent Successfully!";
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_INFO,"Send New Message Result: " + statusMessage + " (New Message is sent from  " + currentUserId + ")", "" ));
            
        }
        catch(Exception e){
             
        }
          
    }
    
}
