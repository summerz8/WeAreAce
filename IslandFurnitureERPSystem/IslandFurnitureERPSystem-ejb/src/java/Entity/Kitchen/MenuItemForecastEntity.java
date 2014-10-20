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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Yoky
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"KITCHEN_ID", "TARGETDATE"}))
public class MenuItemForecastEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<DishItemEntity> dishForecastItems = new ArrayList<>();
    @ManyToMany
    private List<ComboItemEntity> comboForecastItems = new ArrayList<>();
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar creationTime = Calendar.getInstance();
    @OneToOne(mappedBy="menuItemForecast")
    private IngredientForecastEntity ingredientForecast = null;
    @ManyToOne
    private KitchenEntity kitchen;

    public MenuItemForecastEntity() {
    }

    public MenuItemForecastEntity(Calendar targetDate, KitchenEntity kitchen) {
        this.targetDate = targetDate;
        this.kitchen = kitchen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DishItemEntity> getDishForecastItems() {
        return dishForecastItems;
    }

    public void setDishForecastItems(List<DishItemEntity> dishForecastItems) {
        this.dishForecastItems = dishForecastItems;
    }

    public List<ComboItemEntity> getComboForecastItems() {
        return comboForecastItems;
    }

    public void setComboForecastItems(List<ComboItemEntity> comboForecastItems) {
        this.comboForecastItems = comboForecastItems;
    }

    public Calendar getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Calendar targetDate) {
        this.targetDate = targetDate;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public IngredientForecastEntity getIngredientForecast() {
        return ingredientForecast;
    }

    public void setIngredientForecast(IngredientForecastEntity ingredientForecast) {
        this.ingredientForecast = ingredientForecast;
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
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
        if (!(object instanceof MenuItemForecastEntity)) {
            return false;
        }
        MenuItemForecastEntity other = (MenuItemForecastEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.MenuItemForecastEntity[ id=" + id + " ]";
    }

}
