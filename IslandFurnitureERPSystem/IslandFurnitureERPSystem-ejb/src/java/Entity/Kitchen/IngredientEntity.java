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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Yoky
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"KITCHEN_ID", "NAME"}))
public class IngredientEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(unique = true, nullable = false)
    private String name;
    private Double price;   // per lot size
    private String unit;
    private String remark;  // eg: storage conditions
    private Double stock;
    private Double lotSize;
    private boolean deleted;
    @ManyToMany
    private List<DishEntity> dishes;
    @ManyToMany
    private List<IngredientForecastEntity> forecasts = new ArrayList<>();
    @ManyToMany
    private List<IngredientPurchaseOrderEntity> purchaseOrders = new ArrayList<>(); // does not cantain cancelled purchase orders
    @ManyToMany
    private List<IngredientIssueEntity> issues = new ArrayList<>();
    @ManyToMany(mappedBy = "ingredients")
    private List<StoragePlaceEntity> storagePlaces = new ArrayList<>();
    @ManyToOne
    private KitchenEntity kitchen;
    @ManyToOne
    private IngredientSupplierEntity supplier;

    public IngredientEntity() {
        deleted = false;
        stock = 0.0;
    }

//    public IngredientEntity(String name, Double price, String unit, String remark, Double lotSize, KitchenEntity kitchen, IngredientSupplierEntity supplier) {
//        this.name = name;
//        this.price = price;
//        this.unit = unit;
//        this.remark = remark;
//        this.stock = 0.0;
//        this.lotSize = lotSize;
//        this.kitchen = kitchen;
//        this.supplier = supplier;
//        this.deleted = false;
//    }
    public IngredientEntity(String name, Double price, String unit, String remark, Double lotSize, KitchenEntity kitchen, IngredientSupplierEntity supplier) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.remark = remark;
        this.stock = 0.0;
        this.lotSize = lotSize;
        this.kitchen = kitchen;
        this.supplier = supplier;
        this.deleted = false;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getLotSize() {
        return lotSize;
    }

    public void setLotSize(Double lotSize) {
        this.lotSize = lotSize;
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

    public List<IngredientForecastEntity> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<IngredientForecastEntity> forecasts) {
        this.forecasts = forecasts;
    }

    public List<IngredientPurchaseOrderEntity> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<IngredientPurchaseOrderEntity> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public List<IngredientIssueEntity> getIssues() {
        return issues;
    }

    public void setIssues(List<IngredientIssueEntity> issues) {
        this.issues = issues;
    }

    public List<StoragePlaceEntity> getStoragePlaces() {
        return storagePlaces;
    }

    public void setStoragePlaces(List<StoragePlaceEntity> storagePlaces) {
        this.storagePlaces = storagePlaces;
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public IngredientSupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(IngredientSupplierEntity supplier) {
        this.supplier = supplier;
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
        if (!(object instanceof IngredientEntity)) {
            return false;
        }
        IngredientEntity other = (IngredientEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.RawIngredientEntity[ id=" + id + " ]";
    }

}
