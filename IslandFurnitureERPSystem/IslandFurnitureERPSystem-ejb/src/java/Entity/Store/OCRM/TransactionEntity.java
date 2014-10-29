/*
 * To moneyChange this license header, choose License Headers in Project Properties.
 * To moneyChange this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store.OCRM;

import Entity.CommonInfrastructure.StoreUserEntity;
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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author hangsun
 */
@Entity
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar generateTime;
    
    private Double totalPrice;
    private Double tendered;
    private Double moneyChange;
    private int location;//1 for furniture 2 for retial product 3 for kitchen

    @ManyToOne
    private StoreEntity store;
    
    @ManyToOne
    private MemberEntity member;
    
    @OneToOne
    private StoreUserEntity storeStaff;

    //Transaction -- TransactionItem 1<-->M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "transaction")
    private List<TransactionItem> transactionItemList;

    public TransactionEntity() {
    }

    public TransactionEntity(Calendar generateTime, Double totalPrice, int location, StoreEntity store, MemberEntity member) {
        this.generateTime = generateTime;
        this.totalPrice = totalPrice;
        this.location = location;
        this.store = store;
        this.member = member;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Calendar getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Calendar generateTime) {
        this.generateTime = generateTime;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public StoreUserEntity getStoreStaff() {
        return storeStaff;
    }

    public void setStoreStaff(StoreUserEntity storeStaff) {
        this.storeStaff = storeStaff;
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

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;

        hash += (transactionId != null ? transactionId.hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object object) {

        // TODO: Warning - this method won't work in the case the transactionId fields are not set
        if (!(object instanceof TransactionEntity)) {
            return false;
        }
        TransactionEntity other = (TransactionEntity) object;

        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {

            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "Entity.Store.OCRM.TransactionEntity[ id=" + transactionId + " ]";

    }

}
