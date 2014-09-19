/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure;

import Entity.CommonInfrastructure.InternalMessageReceive;
import SessionBean.CommonInFrastructure.InternalMessageModuleLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

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
    
    @EJB
    private InternalMessageModuleLocal im;
    
    public receiveMessageManageBean(){
    }
     
    
    @PostConstruct
    public void init() 
    {
        System.err.println("manageBean:receivemessage():not view list yet.");
        currentUserId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
        receiveMessageList = (List<InternalMessageReceive>) im.viewReceiveMessage(currentUserId);  
        System.err.println("manageBean:receivemessage(): MessageSize:" + receiveMessageList.size());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("receiveMessageEntities", receiveMessageList);
    }
    
    @PreDestroy
    public void destroy()
    {
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("receiveMessageEntities");
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
            
            
    
}
//  <p:commandLink title="View Detail" styleClass="ui-icon ui-icon-search" style="float:left;margin-right:10px">
//                                  <f:setPropertyActionListener value="#{message}" target="#{receiveMessageManagedBean.selectedReceiveMessage}" />
//                                  <h:outputText value="#{message.sendTime}, #{message.senderId} , #{message.title}"  />
//                            </p:commandLink>
//                            <h:outputText value="#{message.sendTime}, #{message.senderId} , #{message.title}" style="display:inline-block" />
//
//                              