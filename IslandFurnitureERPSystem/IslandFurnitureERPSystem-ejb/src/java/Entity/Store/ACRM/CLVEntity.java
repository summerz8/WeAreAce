/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store.ACRM;

import Entity.Store.OCRM.MemberEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author summer
 */
@Entity
public class CLVEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer visitThisMonth;
    private Double aveExp;
    private Double clv;
    
    @OneToOne
    private MemberEntity member;

    public CLVEntity() {
    }

    public CLVEntity(Integer visitThisMonh, Double aveExp, Double clv, MemberEntity member) {
        this.visitThisMonth = visitThisMonh;
        this.aveExp = aveExp;
        this.clv = clv;
        this.member = member;
    }
    
    public Integer getVisitThisMonth() {
        return visitThisMonth;
    }

    public void setVisitThisMonth(Integer visitThisMonth) {
        this.visitThisMonth = visitThisMonth;
    }

    public Double getAveExp() {
        return aveExp;
    }

    public void setAveExp(Double aveExp) {
        this.aveExp = aveExp;
    }

    public Double getClv() {
        return clv;
    }

    public void setClv(Double clv) {
        this.clv = clv;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CLVEntity)) {
            return false;
        }
        CLVEntity other = (CLVEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.ACRM.CLVEntity[ id=" + id + " ]";
    }
    
}
