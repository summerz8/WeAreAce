/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure;

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
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author zhengyuan
 */
@Named(value = "receiveMessageManageBean")
@ViewScoped
public class receiveMessageManageBean {

    /**
     * Creates a new instance of receiveMessageManageBean
     */
    
    private String currentUserId;
    
    
    private List<InternalMessageReceive> receiveMessageList;
    
    private InternalMessageReceive selectedMessage;
    
    private List<InternalMessageReceive> receiveMessageCheckList;
    
    private InternalMessageReceive checkedMessage;
    
    private List<InternalMessageReceive> checkedMessageList;
    
    private InternalMessageReceive deleteMessage;
    
    private Calendar calTime;
    
    @EJB
    private InternalMessageModuleLocal im;
    
    public receiveMessageManageBean(){
    }
     
    
    @PostConstruct
    public void init() 
    {   
        
        System.err.println("hello");
        currentUserId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
        System.err.println("ID:" + currentUserId);
        receiveMessageList = (List<InternalMessageReceive>) im.viewReceiveMessage(currentUserId);  
        System.err.println("manageBean:receivemessage(): MessageSize:" + receiveMessageList.size());
       // System.err.println("time message 1: " +receiveMessageList.get(5).getCalendarTime().getTime());
     //   FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("receiveMessageEntities", receiveMessageList);
    }
    
    @PreDestroy
    public void destroy()
    {
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("receiveMessageEntities");
    }
    
    public String displayTime(Calendar calendarTime){ 
           System.err.println("Calendar Time:" + calendarTime.getTime());
           SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss");
           String time = sdf.format(calendarTime.getTime()).toString();
           return time;
    }
    
    public void readMessage (InternalMessageReceive message) throws Exception{
        selectedMessage = message;
        
        Boolean isOpened;
        isOpened = selectedMessage.isOpened();
        
        if(!isOpened){
            System.err.println("my first time read");
        im.readReceiveMessage(selectedMessage.getReceivedMessageid());
        }
        
        String path = "/secured/public/readMessageDetail.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedMessage", selectedMessage);

        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+path);
        System.err.println("go to another page");
        
        
    }
    
    
    public void deleteMessage(List<InternalMessageReceive> messageList) throws Exception{
        
        for( InternalMessageReceive msg : messageList){
            im.deleteReceiveMessage(msg.getReceivedMessageid());
        }
        
        receiveMessageList = (List<InternalMessageReceive>) im.viewReceiveMessage(currentUserId);  

        
    }
    
  
    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public List<InternalMessageReceive> getReceiveMessageList() {
        return receiveMessageList;
    }

    public void setReceiveMessageList(List<InternalMessageReceive> receiveMessageList) {
        this.receiveMessageList = receiveMessageList;
    }

    public InternalMessageModuleLocal getIm() {
        return im;
    }

    public void setIm(InternalMessageModuleLocal im) {
        this.im = im;
    }

    public InternalMessageReceive getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(InternalMessageReceive selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public List<InternalMessageReceive> getReceiveMessageCheckList() {
        return receiveMessageCheckList;
    }

    public void setReceiveMessageCheckList(List<InternalMessageReceive> receiveMessageCheckList) {
        this.receiveMessageCheckList = receiveMessageCheckList;
    }

    public InternalMessageReceive getCheckedMessage() {
        return checkedMessage;
    }

    public void setCheckedMessage(InternalMessageReceive checkedMessage) {
        this.checkedMessage = checkedMessage;
    }

    public List<InternalMessageReceive> getCheckedMessageList() {
        return checkedMessageList;
    }

    public void setCheckedMessageList(List<InternalMessageReceive> checkedMessageList) {
        this.checkedMessageList = checkedMessageList;
    }

    public InternalMessageReceive getDeleteMessage() {
        return deleteMessage;
    }

    public void setDeleteMessage(InternalMessageReceive deleteMessage) {
        this.deleteMessage = deleteMessage;
    }
    
    
    
     public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Message Selected", String.valueOf(((InternalMessageReceive) event.getObject()).getReceivedMessageid()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", String.valueOf(((InternalMessageReceive) event.getObject()).getReceivedMessageid()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
            
            
    
}


//  <p:commandLink title="View Detail" styleClass="ui-icon ui-icon-search" style="float:left;margin-right:10px">
//                                  <f:setPropertyActionListener value="#{message}" target="#{receiveMessageManagedBean.selectedReceiveMessage}" />
//                                  <h:outputText value="#{message.sendTime}, #{message.senderId} , #{message.title}"  />
//                            </p:commandLink>
//                            <h:outputText value="#{message.sendTime}, #{message.senderId} , #{message.title}" style="display:inline-block" />
//
//   

//<p:commandLink ajax="false"  title="View Detail" styleClass="ui-icon ui-icon-search" style="float:left;margin-right:2px">
//                                        <f:setPropertyActionListener value="#{message}" target="#{receiveMessageManageBean.selectedMessage}" />
//                                        
//                                 </p:commandLink>