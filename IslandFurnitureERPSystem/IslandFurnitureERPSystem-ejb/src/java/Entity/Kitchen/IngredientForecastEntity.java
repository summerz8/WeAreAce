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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yoky
 */
@Entity
@XmlAccessorType(value = XmlAccessType.FIELD)
public class IngredientForecastEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @XmlTransient
    private List<IngredientItemEntity> forecastItems = new ArrayList<>();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar creationTime;
    @OneToOne
    @JoinColumn(unique = true, nullable = false)
    private MenuItemForecastEntity menuItemForecast;
    @OneToOne(mappedBy = "forecast")
    private IngredientPurchaseOrderEntity purchaseOrder;
    @OneToOne(mappedBy = "forecast")
    private IngredientIssueEntity issue;

    public IngredientForecastEntity() {
        purchaseOrder = null;
        issue = null;
        creationTime = Calendar.getInstance();
    }

    public IngredientForecastEntity(MenuItemForecastEntity menuItemForecast) {
        this.menuItemForecast = menuItemForecast;
        purchaseOrder = null;
        issue = null;
        creationTime = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<IngredientItemEntity> getForecastItems() {
        return forecastItems;
    }

    public void setForecastItems(List<IngredientItemEntity> forecastItems) {
        this.forecastItems = forecastItems;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public MenuItemForecastEntity getMenuItemForecast() {
        return menuItemForecast;
    }

    public void setMenuItemForecast(MenuItemForecastEntity menuItemForecast) {
        this.menuItemForecast = menuItemForecast;
    }

    public IngredientPurchaseOrderEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(IngredientPurchaseOrderEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public IngredientIssueEntity getIssue() {
        return issue;
    }

    public void setIssue(IngredientIssueEntity issue) {
        this.issue = issue;
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
        if (!(object instanceof IngredientForecastEntity)) {
            return false;
        }
        IngredientForecastEntity other = (IngredientForecastEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.RawIngredientForecastEntity[ id=" + id + " ]";
    }

}
