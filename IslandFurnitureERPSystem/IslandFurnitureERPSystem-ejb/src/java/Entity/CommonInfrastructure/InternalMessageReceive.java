/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private boolean deleted;
    private boolean opened;
    
    
    @ManyToOne
    private InternalMessageEntity message = new InternalMessageEntity();
    
    @ManyToOne 
    private UserEntity receiver = new UserEntity();
    
    
    public InternalMessageReceive() {
    }

    
    
    public InternalMessageReceive(InternalMessageEntity message) {
     
        this.deleted = false;
        this.opened = false;
    
        this.message = message;
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
