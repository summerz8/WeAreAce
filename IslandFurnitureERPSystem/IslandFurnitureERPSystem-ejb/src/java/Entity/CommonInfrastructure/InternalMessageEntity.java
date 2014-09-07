/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import Entity.CommonInfrastructure.UserEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author zhengyuan
 */
@Entity
        @Table(name="InternalMessage")
public class InternalMessageEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long internalMessageId;
    
    @ManyToOne(cascade={CascadeType.ALL})
    private UserEntity sender = new UserEntity();
    
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "inMessages")
    private Set<UserEntity> receivers = new HashSet<UserEntity>();


    public Long getInternalMessageId() {
        return internalMessageId;
    }

    public void setInternalMessageId(Long internalMessageId) {
        this.internalMessageId = internalMessageId;
    }

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public Set<UserEntity> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<UserEntity> receivers) {
        this.receivers = receivers;
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
