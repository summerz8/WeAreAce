/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Kitchen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yoky
 */
@Entity
@XmlAccessorType(value = XmlAccessType.FIELD)
public class IngredientPurchaseOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @XmlTransient
    private List<IngredientItemEntity> purchaseItems = new ArrayList<>();
    private Double total;
    private Double actualTotal;
    private String status; // Unconfirmed, Confirmed, Cancelled
    @OneToOne
    @JoinColumn(unique = true, nullable = false)
    private IngredientForecastEntity forecast;
    @OneToMany(mappedBy = "ingredientPurchaseOrder")
    @XmlTransient
    private List<IngredientPurchaseOrderToSupplierEntity> IPOSs = new ArrayList<>();
    private Integer unconfirmedIPOSQuantity;

    public IngredientPurchaseOrderEntity() {
        status = "Unconfirmed";
        total = 0.0;
        actualTotal = 0.0;
        unconfirmedIPOSQuantity = 0;
    }

    public IngredientPurchaseOrderEntity(IngredientForecastEntity forecast) {
        this.forecast = forecast;
        status = "Unconfirmed";
        total = 0.0;
        actualTotal = 0.0;
        unconfirmedIPOSQuantity = 0;
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

    public IngredientForecastEntity getForecast() {
        return forecast;
    }

    public void setForecast(IngredientForecastEntity forecast) {
        this.forecast = forecast;
    }

    public List<IngredientPurchaseOrderToSupplierEntity> getIPOSs() {
        return IPOSs;
    }

    public void setIPOSs(List<IngredientPurchaseOrderToSupplierEntity> IPOSs) {
        this.IPOSs = IPOSs;
    }

    public Integer getUnconfirmedIPOSQuantity() {
        return unconfirmedIPOSQuantity;
    }

    public void setUnconfirmedIPOSQuantity(Integer unconfirmedIPOSQuantity) {
        this.unconfirmedIPOSQuantity = unconfirmedIPOSQuantity;
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
