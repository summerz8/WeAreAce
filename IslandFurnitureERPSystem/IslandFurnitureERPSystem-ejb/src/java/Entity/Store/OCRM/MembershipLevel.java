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
import javax.persistence.Table;

/**
 *
 * @author dan
 */
@Entity
@Table(name = "memberlevel")
public class MembershipLevel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long level;
       
    private double discount;
//
//    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "member")
//    private Collection<MemberEntity> members;

    
    public MembershipLevel() {
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

//
//    public Collection<MemberEntity> getMembers() {
//        return members;
//    }
//
//    public void setMembers(Collection<MemberEntity> members) {
//        this.members = members;
//    }
//
//    
//    

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (level != null ? level.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the level fields are not set
        if (!(object instanceof MembershipLevel)) {
            return false;
        }
        MembershipLevel other = (MembershipLevel) object;
        if ((this.level == null && other.level != null) || (this.level != null && !this.level.equals(other.level))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.MembershipLevel[ id=" + level + " ]";
    }
    
}
