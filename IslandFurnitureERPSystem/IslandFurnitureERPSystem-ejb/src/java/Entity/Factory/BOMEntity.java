/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author apple
 */
@Entity
public class BOMEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long BOMID;
    private List<RawMaterialEntity> RawMaterial;
    @OneToOne(mappedBy="bom")
    private PlannedOrderEntity plannedOrder;
    
    
    public Long getId() {
        return BOMID;
    }

    public void setId(Long id) {
        this.BOMID = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (BOMID != null ? BOMID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BOMEntity)) {
            return false;
        }
        BOMEntity other = (BOMEntity) object;
        if ((this.BOMID == null && other.BOMID != null) || (this.BOMID != null && !this.BOMID.equals(other.BOMID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.BOMEntity[ id=" + BOMID + " ]";
    }
    
}
