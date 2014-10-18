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
    private String Name;
    
    private Double pointsToUpgrade;// lvl1 1000, lvl2 2000, lvl3 5000 lvl4 10000 lvl5 20000
       
    private Double discount;
//
//    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "member")
//    private Collection<MemberEntity> members;

    
    public MembershipLevel() {
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
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

    public Double getPointsToUpgrade() {
        return pointsToUpgrade;
    }

    public void setPointsToUpgrade(Double pointsToUpgrade) {
        this.pointsToUpgrade = pointsToUpgrade;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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
