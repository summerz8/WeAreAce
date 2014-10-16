/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

<<<<<<< HEAD
<<<<<<< HEAD
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
=======
=======
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
import Entity.Store.StoreEntity;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
<<<<<<< HEAD
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
=======
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import javax.persistence.ManyToOne;
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
=======
import javax.persistence.ManyToOne;
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
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
    
<<<<<<< HEAD
<<<<<<< HEAD
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar generateTime;
    
    private long MemberId;
    private Double totalPrice;
    
    @OneToMany
    private List<TransactionItem> transactionItems;
=======
=======
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar generateTime;
    
    private Long storeStaffId;
    
    private Long memberId;
    private Double totalPrice;
    private Double tendered;
    private Double change;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "transaction")
    private List<TransactionItem> transactionItems;
    
    @ManyToOne
    private StoreEntity store;
<<<<<<< HEAD
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
=======
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
      

    public Long getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(Long TransactionId) {
        this.TransactionId = TransactionId;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
    public Calendar getGenerateTime() {
        return generateTime;
    }

    public Long getStoreStaffId() {
        return storeStaffId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Double getTendered() {
        return tendered;
    }

    public Double getChange() {
        return change;
    }

    public List<TransactionItem> getTransactionItems() {
        return transactionItems;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setGenerateTime(Calendar generateTime) {
        this.generateTime = generateTime;
    }

    public void setStoreStaffId(Long storeStaffId) {
        this.storeStaffId = storeStaffId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTendered(Double tendered) {
        this.tendered = tendered;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public void setTransactionItems(List<TransactionItem> transactionItems) {
        this.transactionItems = transactionItems;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }
    
    

<<<<<<< HEAD
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
=======
>>>>>>> 2b60006074cc5bb70f5b927ba60bc6b45a59a63b
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
