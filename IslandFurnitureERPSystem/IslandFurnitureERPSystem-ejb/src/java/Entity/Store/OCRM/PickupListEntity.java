/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

import java.io.Serializable;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.List;
=======
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
=======
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
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
<<<<<<< HEAD
<<<<<<< HEAD
    @OneToMany
    private List<TransactionItem> transactoinItems;
    
=======
=======
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "pickupList")
    private List<TransactionItem> transactoinItems = new ArrayList<>();
    
    public PickupListEntity(){
        this.Picked = Boolean.FALSE;
    }
<<<<<<< HEAD
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
=======
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b

    public Long getPickupListId() {
        return PickupListId;
    }

    public void setPickupListId(Long PickupListId) {
        this.PickupListId = PickupListId;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
    public Boolean getPicked() {
        return Picked;
    }

    public void setPicked(Boolean Picked) {
        this.Picked = Picked;
    }

    public List<TransactionItem> getTransactoinItems() {
        return transactoinItems;
    }

    public void setTransactoinItems(List<TransactionItem> transactoinItems) {
        this.transactoinItems = transactoinItems;
    }

<<<<<<< HEAD
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
=======
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
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
