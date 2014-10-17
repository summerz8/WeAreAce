/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author dan
 */
@Entity
public class TransactionItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TransactionItemId;
    
    private Long itemId;//refer to storeproductId or storeRetailProductId
    private int amount;
    private Double unitPrice;
    private Double totalPrice;
    private String itemName;

//    //TransactionItem -- TransactionEntity M<-->1
    @ManyToOne
    private TransactionEntity transaction;
    
    //TransactionItem -- PickupListEntity M<-->1
    @ManyToOne
    private PickupListEntity pickupList;
    

    public Long getTransactionItemId() {
        return TransactionItemId;
    }

    public void setTransactionItemId(Long TransactionItemId) {
        this.TransactionItemId = TransactionItemId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public PickupListEntity getPickupList() {
        return pickupList;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public void setPickupList(PickupListEntity pickupList) {
        this.pickupList = pickupList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (TransactionItemId != null ? TransactionItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the TransactionItemId fields are not set
        if (!(object instanceof TransactionItem)) {
            return false;
        }
        TransactionItem other = (TransactionItem) object;
        if ((this.TransactionItemId == null && other.TransactionItemId != null) || (this.TransactionItemId != null && !this.TransactionItemId.equals(other.TransactionItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.OrderItem[ id=" + TransactionItemId + " ]";
    }
    
}
