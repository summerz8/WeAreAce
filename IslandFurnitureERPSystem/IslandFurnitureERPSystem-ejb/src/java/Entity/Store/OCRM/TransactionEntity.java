/*
 * To moneyChange this license header, choose License Headers in Project Properties.
 * To moneyChange this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store.OCRM;

import Entity.Store.StoreEntity;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author hangsun
 */
@Entity
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar generateTime;
    private Double totalPrice;
    private Double tendered;
    private Double moneyChange;
    private Long storeStaffId;
    private Long memberId;
    
    @ManyToOne
    private StoreEntity store;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "transaction")
    private List<TransactionItem> transactionItemList;
    
    
    public TransactionEntity(){
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Calendar generateTime) {
        this.generateTime = generateTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTendered() {
        return tendered;
    }

    public void setTendered(Double tendered) {
        this.tendered = tendered;
    }

    public Double getMoneyChange() {
        return moneyChange;
    }

    public void setMoneyChange(Double moneyChange) {
        this.moneyChange = moneyChange;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public List<TransactionItem> getTransactionItemList() {
        return transactionItemList;
    }

    public void setTransactionItemList(List<TransactionItem> transactionItemList) {
        this.transactionItemList = transactionItemList;
    }

    public Long getStoreStaffId() {
        return storeStaffId;
    }

    public void setStoreStaffId(Long staffId) {
        this.storeStaffId = staffId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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
        if (!(object instanceof TransactionEntity)) {
            return false;
        }
        TransactionEntity other = (TransactionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.TransactionEntity[ id=" + id + " ]";
    }
    
}
