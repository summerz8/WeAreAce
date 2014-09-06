/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name="IdNumber")
public class IdNumberEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private final int id = 0;
    private Long id_H;
    private Long id_F;
    private Long id_S;

    public IdNumberEntity() {
        this.id_H = new Long(1000000);
        this.id_F = new Long(1000000);
        this.id_S = new Long(1000000);
    }
    
    
    public Long getId_H() {
        return id_H;
    }

    public void setId_H(Long id_H) {
        this.id_H = id_H;
    }

    public Long getId_F() {
        return id_F;
    }

    public void setId_F(Long id_F) {
        this.id_F = id_F;
    }

    public Long getId_S() {
        return id_S;
    }

    public void setId_S(Long id_S) {
        this.id_S = id_S;
    }
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_H != null ? id_H.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdNumberEntity)) {
            return false;
        }
        IdNumberEntity other = (IdNumberEntity) object;
        if ((this.id_H == null && other.id_H != null) || (this.id_H != null && !this.id_H.equals(other.id_H))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.IdEntity[ id=" + id_H + " ]";
    }
    
}
