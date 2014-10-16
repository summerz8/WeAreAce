/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dan
 */
@Entity
@Table(name ="PickupList")
public class PickupListEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long PickupListId;
    
    private Boolean Picked;
    
    //PickupListEntity <--> TransactionItem 1<-->M
    @OneToMany
    private List<TransactionItem> transactoinItems;
    

    public Long getPickupListId() {
        return PickupListId;
    }

    public void setPickupListId(Long PickupListId) {
        this.PickupListId = PickupListId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (PickupListId != null ? PickupListId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the PickupListId fields are not set
        if (!(object instanceof PickupListEntity)) {
            return false;
        }
        PickupListEntity other = (PickupListEntity) object;
        if ((this.PickupListId == null && other.PickupListId != null) || (this.PickupListId != null && !this.PickupListId.equals(other.PickupListId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.PickupListEntity[ id=" + PickupListId + " ]";
    }
    
}
