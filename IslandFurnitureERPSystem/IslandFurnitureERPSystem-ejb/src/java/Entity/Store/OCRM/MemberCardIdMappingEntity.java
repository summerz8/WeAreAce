/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store.OCRM;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author hangsun
 */
@Entity
public class MemberCardIdMappingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String cardId;
    
    @OneToOne
    private MemberEntity member;
    
    public MemberCardIdMappingEntity(){
    }
            
    public MemberCardIdMappingEntity(String cardId){
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardId != null ? cardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the cardId fields are not set
        if (!(object instanceof MemberCardIdMappingEntity)) {
            return false;
        }
        MemberCardIdMappingEntity other = (MemberCardIdMappingEntity) object;
        if ((this.cardId == null && other.cardId != null) || (this.cardId != null && !this.cardId.equals(other.cardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.MemberCardIdMappingEntity[ id=" + cardId + " ]";
    }
    
}
