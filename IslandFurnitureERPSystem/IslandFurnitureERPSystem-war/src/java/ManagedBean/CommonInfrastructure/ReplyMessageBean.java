/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure;

import Entity.CommonInfrastructure.InternalMessageEntity;
import Entity.CommonInfrastructure.InternalMessageReceive;
import Entity.CommonInfrastructure.UserEntity;
import SessionBean.CommonInFrastructure.InternalMessageModuleLocal;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zhengyuan
 */
@Named(value = "replyMessageBean")
@ViewScoped
public class ReplyMessageBean {

    
    private InternalMessageReceive replyMessage;
    private InternalMessageEntity sendMyReplyMessage;
    private String receiverId;
    private String senderId;
    private String title;
    private String content;
    private String sendTime;
    private ArrayList<String> receiverIdList;
    /**
     * Creates a new instance of ReplyMessageBean
     */
    public ReplyMessageBean() {
    }
    
    @EJB
    private InternalMessageModuleLocal im;
    
    
    @PostConstruct
    public void init(){
        
        
        System.err.println("ReplyMessageBean(): init():" );
        replyMessage = (InternalMessageReceive) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("repliedMessage");
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("repliedMessage");
        receiverId = replyMessage.getSenderId();
        System.err.println("ReplyMessageBean(): receiveId:" + receiverId );
        senderId = replyMessage.getReceiver().getUserId();
        System.err.println("ReplyMessageBean(): senderId:" + senderId );
        title = "Re: " + replyMessage.getTitle();
        content = "Reply:" + replyMessage.getContent();
        
       
        }
    
    
    public void sendMessage(ActionEvent event) throws Exception
    {   
        receiverIdList = new ArrayList<String> ();
        
        receiverIdList.add(receiverId);
        
    
       im.sendMessage(senderId, title, content, receiverIdList);
       im.replyMessage(replyMessage.getReceivedMessageid());
       
       System.err.println("sendMessage(): Message Sent ");
       FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Message", "Message is sent successfully."));
        
    }
    
    

    public InternalMessageReceive getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(InternalMessageReceive replyMessage) {
        this.replyMessage = replyMessage;
    }

    public InternalMessageEntity getSendMyReplyMessage() {
        return sendMyReplyMessage;
    }

    public void setSendMyReplyMessage(InternalMessageEntity sendMyReplyMessage) {
        this.sendMyReplyMessage = sendMyReplyMessage;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
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

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
    
    
    
}
