/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;



/**
 *
 * @author dan
 */
@Entity
@Table(name ="PickupList")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PickupListEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PickupListId;
    
    private Boolean Picked;
    private Boolean Distributed;
    
    //PickupListEntity <--> TransactionItem 1<-->M
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "pickupList")
<<<<<<< HEAD
    @XmlTransient
    private List<TransactionItemEntity> transactoinItems = new ArrayList<>();
=======
    private List<TransactionItem> transactoinItems = new ArrayList<>();
>>>>>>> db5c01712158636793f41f6e6f3aa3cbd7fe2e5e
    
    public PickupListEntity(){
        this.Picked = Boolean.FALSE;
        this.Distributed = Boolean.FALSE;
    }


    public Long getPickupListId() {
        return PickupListId;
    }

    public void setPickupListId(Long PickupListId) {
        this.PickupListId = PickupListId;
    }

    public Boolean getPicked() {
        return Picked;
    }

    public void setPicked(Boolean Picked) {
        this.Picked = Picked;
    }

<<<<<<< HEAD
    public Boolean isDistributed() {
        return Distributed;
    }

    public void setDistributed(Boolean Distributed) {
        this.Distributed = Distributed;
    }

    
    public List<TransactionItemEntity> getTransactoinItems() {
=======
    public List<TransactionItem> getTransactoinItems() {
>>>>>>> db5c01712158636793f41f6e6f3aa3cbd7fe2e5e
        return transactoinItems;
    }

    public void setTransactoinItems(List<TransactionItem> transactoinItems) {
        this.transactoinItems = transactoinItems;
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
