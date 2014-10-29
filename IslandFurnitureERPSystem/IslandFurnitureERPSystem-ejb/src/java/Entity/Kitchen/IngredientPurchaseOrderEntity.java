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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Yoky
 */
@Entity
public class IngredientPurchaseOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<IngredientItemEntity> purchaseItems = new ArrayList<>();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar creationTime;
    private Double total;
    private Double acturalTotal;
    private String status; // Unconfirmed, Confirmed, Cancelled, Received
    @OneToOne
    private IngredientForecastEntity forecast;
    @OneToOne(mappedBy = "purchaseOrder")
    private IngredientReceiptEntity receipt;

    public IngredientPurchaseOrderEntity() {
        status = "Unconfirmed";
        creationTime = Calendar.getInstance();
        receipt = null;
    }

    public IngredientPurchaseOrderEntity(IngredientForecastEntity forecast) {
        this.forecast = forecast;
        status = "Unconfirmed";
        creationTime = Calendar.getInstance();
        receipt = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getActuralTotal() {
        return acturalTotal;
    }

    public void setActuralTotal(Double acturalTotal) {
        this.acturalTotal = acturalTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public IngredientForecastEntity getForecast() {
        return forecast;
    }

    public void setForecast(IngredientForecastEntity forecast) {
        this.forecast = forecast;
    }

    public IngredientReceiptEntity getReceipt() {
        return receipt;
    }

    public void setReceipt(IngredientReceiptEntity receipt) {
        this.receipt = receipt;
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
        if (!(object instanceof IngredientPurchaseOrderEntity)) {
            return false;
        }
        IngredientPurchaseOrderEntity other = (IngredientPurchaseOrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.RawIngredientPurchaseOrderEntity[ id=" + id + " ]";
    }

}
