/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Kitchen;

import Entity.Store.StoreEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class KitchenEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "kitchen")
    private StoreEntity store;
    private boolean deleted;
    @OneToMany(mappedBy = "kitchen")
    @XmlTransient
    private List<DishEntity> dishes = new ArrayList<>();
    @OneToMany(mappedBy = "kitchen")
    @XmlTransient
    private List<ComboEntity> combos = new ArrayList<>();
    @OneToMany(mappedBy = "kitchen")
    @XmlTransient
    private List<IngredientEntity> ingredients = new ArrayList<>();
    @OneToMany(mappedBy = "kitchen")
    @XmlTransient
    private List<KitchenOrderEntity> orders = new ArrayList<>();
    @OneToMany(mappedBy = "kitchen")
    @XmlTransient
    private List<MenuItemForecastEntity> menuItemForecasts = new ArrayList<>();
    @OneToMany(mappedBy = "kitchen")
    @XmlTransient
    private List<StoragePlaceEntity> storagePlaces = new ArrayList<>();
    @OneToMany(mappedBy = "kitchen")
    @XmlTransient
    private List<IngredientSupplierEntity> ingredientSuppliers = new ArrayList<>();
    @OneToMany(mappedBy = "kitchen")
    @XmlTransient
    private List<DailySalesEntity> dailySales = new ArrayList<>();
    @OneToMany(mappedBy = "kitchen")
    @XmlTransient
    private List<IngredientPurchaseOrderToSupplierEntity> ingredientPurchaseOrders = new ArrayList<>();

    public KitchenEntity() {
        this.deleted = false;
    }

    public KitchenEntity(StoreEntity store) {
        this.store = store;
        this.deleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<DishEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishEntity> dishes) {
        this.dishes = dishes;
    }

    public List<ComboEntity> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboEntity> combos) {
        this.combos = combos;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public List<KitchenOrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<KitchenOrderEntity> orders) {
        this.orders = orders;
    }

    public List<MenuItemForecastEntity> getMenuItemForecasts() {
        return menuItemForecasts;
    }

    public void setMenuItemForecasts(List<MenuItemForecastEntity> menuItemForecasts) {
        this.menuItemForecasts = menuItemForecasts;
    }

    public List<StoragePlaceEntity> getStoragePlaces() {
        return storagePlaces;
    }

    public void setStoragePlaces(List<StoragePlaceEntity> storagePlaces) {
        this.storagePlaces = storagePlaces;
    }

    public List<IngredientSupplierEntity> getIngredientSuppliers() {
        return ingredientSuppliers;
    }

    public void setIngredientSuppliers(List<IngredientSupplierEntity> ingredientSuppliers) {
        this.ingredientSuppliers = ingredientSuppliers;
    }

    public List<DailySalesEntity> getDailySales() {
        return dailySales;
    }

    public void setDailySales(List<DailySalesEntity> dailySales) {
        this.dailySales = dailySales;
    }

    public List<IngredientPurchaseOrderToSupplierEntity> getIngredientPurchaseOrders() {
        return ingredientPurchaseOrders;
    }

    public void setIngredientPurchaseOrders(List<IngredientPurchaseOrderToSupplierEntity> ingredientPurchaseOrders) {
        this.ingredientPurchaseOrders = ingredientPurchaseOrders;
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
        if (!(object instanceof KitchenEntity)) {
            return false;
        }
        KitchenEntity other = (KitchenEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.KitchenEntity[ id=" + id + " ]";
    }

}
