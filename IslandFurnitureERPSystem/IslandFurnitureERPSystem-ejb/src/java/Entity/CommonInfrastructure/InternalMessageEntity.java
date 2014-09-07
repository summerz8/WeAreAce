/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import Entity.CommonInfrastructure.UserEntity;
import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zhengyuan
 */
@Entity
@Table(name="InternalMessage")

//send side message 
public class InternalMessageEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long internalMessageId;

   
    private String tittle;
    private String content;
    private String sendTime;
    private String senderName;
    private String status;
    private String type;
    @OneToMany (cascade = {CascadeType.ALL},mappedBy = "message")
    private Collection<InternalMessageReceive> receiverInfo = new ArrayList<InternalMessageReceive>();
    
    


    public Long getInternalMessageId() {
        return internalMessageId;
    }

    public void setInternalMessageId(Long internalMessageId) {
        this.internalMessageId = internalMessageId;
    }

     public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<InternalMessageReceive> getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(Collection<InternalMessageReceive> receiverInfo) {
        this.receiverInfo = receiverInfo;
    }
    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (internalMessageId != null ? internalMessageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the internalMessageId fields are not set
        if (!(object instanceof InternalMessageEntity)) {
            return false;
        }
        InternalMessageEntity other = (InternalMessageEntity) object;
        if ((this.internalMessageId == null && other.internalMessageId != null) || (this.internalMessageId != null && !this.internalMessageId.equals(other.internalMessageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CommonInfrastructure.InternalMessageEntity[ id=" + internalMessageId + " ]";
    }
    
}
