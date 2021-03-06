/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author zhengyuan
 */
@Entity
@Table (name = "InternalMessageReceive")
public class InternalMessageReceive implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receivedMessageid;
    private Boolean deleted;
    private Boolean opened;
    private String senderId;
    private String content;
    private String title;
   
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar calendarTime;
    
    private Boolean replied;
    
    @ManyToOne
    private InternalMessageEntity message;
    
    @ManyToOne 
    private UserEntity receiver;
    
    
    public InternalMessageReceive() {
    }

    
    
    public InternalMessageReceive(InternalMessageEntity message) {
     
        this.deleted = false;
        this.opened = false;
        this.message = message;
        this.senderId = message.getSender().getUserId();
        this.content = message.getContent();
        this.title = message.getTitle();
        this.calendarTime = message.getCalendarTime();
        
    }

    
    
    
    public Long getReceivedMessageid() {
        return receivedMessageid;
    }

    public void setReceivedMessageid(Long receivedMessageid) {
        this.receivedMessageid = receivedMessageid;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }


    public InternalMessageEntity getSendMessage() {
        return message;
    }

    public void setSendMessage(InternalMessageEntity sendMessage) {
        this.message = sendMessage;
    }

    public InternalMessageEntity getMessage() {
        return message;
    }

    public void setMessage(InternalMessageEntity message) {
        this.message = message;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Calendar getCalendarTime() {
        return calendarTime;
    }

    public void setCalendarTime(Calendar calendarTime) {
        this.calendarTime = calendarTime;
    }


    public Boolean isReplied() {
        return replied;
    }

    public void setReplied(Boolean replied) {
        this.replied = replied;
    }
    
    
    
    

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receivedMessageid != null ? receivedMessageid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InternalMessageReceive)) {
            return false;
        }
        InternalMessageReceive other = (InternalMessageReceive) object;
        if ((this.receivedMessageid == null && other.receivedMessageid != null) || (this.receivedMessageid != null && !this.receivedMessageid.equals(other.receivedMessageid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CommonInfrastructure.InternalMessageReceive[ ReceivedMessageid=" + receivedMessageid + " ]";
    }
    
}
