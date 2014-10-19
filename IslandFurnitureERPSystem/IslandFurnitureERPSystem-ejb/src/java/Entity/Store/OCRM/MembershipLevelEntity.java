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
public class MembershipLevelEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer levelId;

    private Double pointsToUpgrade;

    private String levelName;
    private double discount;
//
//    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "member")
//    private Collection<MemberEntity> members;

    
    public MembershipLevelEntity() {
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

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }


//
//
    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer level) {
        this.levelId = level;
    }


    public Double getPointsToUpgrade() {
        return pointsToUpgrade;
    }

    public void setPointsToUpgrade(Double pointsToUpgrade) {
        this.pointsToUpgrade = pointsToUpgrade;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (levelId != null ? levelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the levelId fields are not set
        if (!(object instanceof MembershipLevelEntity)) {
            return false;
        }
        MembershipLevelEntity other = (MembershipLevelEntity) object;
        if ((this.levelId == null && other.levelId != null) || (this.levelId != null && !this.levelId.equals(other.levelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.MembershipLevel[ id=" + levelId + " ]";
    }
    
}
