/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author dan
 */
@Entity
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long TransactionId;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar generateTime;
    
    private long MemberId;
    private Double totalPrice;
    
    @OneToMany
    private List<TransactionItem> transactionItems;
      

    public Long getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(Long TransactionId) {
        this.TransactionId = TransactionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (TransactionId != null ? TransactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the TransactionId fields are not set
        if (!(object instanceof TransactionEntity)) {
            return false;
        }
        TransactionEntity other = (TransactionEntity) object;
        if ((this.TransactionId == null && other.TransactionId != null) || (this.TransactionId != null && !this.TransactionId.equals(other.TransactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.OrderEntity[ id=" + TransactionId + " ]";
    }
    
}
