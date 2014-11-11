/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Kitchen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Yoky
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"KITCHEN_ID", "SUPPLIER_ID", "TARGETDATE"}))
public class IngredientPurchaseOrderToSupplierEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private IngredientSupplierEntity supplier;
    @ManyToOne
    private IngredientPurchaseOrderEntity ingredientPurchaseOrder;
    @OneToMany
    private List<IngredientItemEntity> purchaseItems = new ArrayList<>();
    private Double total;
    private Double actualTotal;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar creationTime;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetDate;
    private String status;  //Unconfirmed, Confirmed, Received
    @OneToOne(mappedBy = "purchaseOrder")
    private IngredientReceiptEntity receipt;
    @ManyToOne
    private KitchenEntity kitchen;

    public IngredientPurchaseOrderToSupplierEntity() {
        creationTime = Calendar.getInstance();
        receipt = null;
        total = 0.0;
        actualTotal = 0.0;
        status = "Unconfirmed";
    }

    public IngredientPurchaseOrderToSupplierEntity(IngredientSupplierEntity supplier, IngredientPurchaseOrderEntity ingredientPurchaseOrder) {
        this.supplier = supplier;
        this.ingredientPurchaseOrder = ingredientPurchaseOrder;
        kitchen = ingredientPurchaseOrder.getForecast().getMenuItemForecast().getKitchen();
        targetDate = ingredientPurchaseOrder.getForecast().getMenuItemForecast().getTargetDate();
        creationTime = Calendar.getInstance();
        receipt = null;
        total = 0.0;
        actualTotal = 0.0;
        status = "Unconfirmed";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IngredientSupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(IngredientSupplierEntity supplier) {
        this.supplier = supplier;
    }

    public IngredientPurchaseOrderEntity getIngredientPurchaseOrder() {
        return ingredientPurchaseOrder;
    }

    public void setIngredientPurchaseOrder(IngredientPurchaseOrderEntity ingredientPurchaseOrder) {
        this.ingredientPurchaseOrder = ingredientPurchaseOrder;
    }

    public List<IngredientItemEntity> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<IngredientItemEntity> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public Calendar getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Calendar targetDate) {
        this.targetDate = targetDate;
    }

    public IngredientReceiptEntity getReceipt() {
        return receipt;
    }

    public void setReceipt(IngredientReceiptEntity receipt) {
        this.receipt = receipt;
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(Double actualTotal) {
        this.actualTotal = actualTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof IngredientPurchaseOrderToSupplierEntity)) {
            return false;
        }
        IngredientPurchaseOrderToSupplierEntity other = (IngredientPurchaseOrderToSupplierEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.IngredientPurchaseOrderToSupplier[ id=" + id + " ]";
    }

}
