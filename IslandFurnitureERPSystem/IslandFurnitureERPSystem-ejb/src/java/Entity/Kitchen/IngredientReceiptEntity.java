/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Kitchen;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Yoky
 */
@Entity
public class IngredientReceiptEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar creationTime;
    @OneToOne
    @JoinColumn(unique = true, nullable = false)
    private IngredientPurchaseOrderToSupplierEntity purchaseOrder;
    

    
    public IngredientReceiptEntity() {
        creationTime = Calendar.getInstance();
    }

    public IngredientReceiptEntity(IngredientPurchaseOrderToSupplierEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
        creationTime = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public IngredientPurchaseOrderToSupplierEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(IngredientPurchaseOrderToSupplierEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
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
        if (!(object instanceof IngredientReceiptEntity)) {
            return false;
        }
        IngredientReceiptEntity other = (IngredientReceiptEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.RawIngredientReceiptEntity[ id=" + id + " ]";
    }

}
