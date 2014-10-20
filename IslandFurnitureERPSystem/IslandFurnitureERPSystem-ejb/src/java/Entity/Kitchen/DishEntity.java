/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Kitchen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yoky
 */
@Entity
@XmlAccessorType(value = XmlAccessType.FIELD)
public class DishEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private Double price;
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "IngredientItem_ID"))
    private List<IngredientItemEntity> recipe = new ArrayList<>();
    private Integer recipeQuantity;
    private String remark;
    private boolean deleted;
    @ManyToMany
    @XmlTransient
    private List<ComboEntity> combos = new ArrayList<>();
    @ManyToOne
    private KitchenEntity kitchen;
    @ManyToMany
    private List<MenuItemForecastEntity> forecasts = new ArrayList<>();

    public DishEntity() {
        deleted = false;
    }

    public DishEntity(String name, Double price, String remark, Integer recipeQuantity, KitchenEntity kitchen) {
        this.name = name;
        this.price = price;
        this.remark = remark;
        this.recipeQuantity = recipeQuantity;
        this.kitchen = kitchen;
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

    public List<IngredientItemEntity> getRecipe() {
        return recipe;
    }

    public void setRecipe(List<IngredientItemEntity> recipe) {
        this.recipe = recipe;
    }

    public Integer getRecipeQuantity() {
        return recipeQuantity;
    }

    public void setRecipeQuantity(Integer recipeQuantity) {
        this.recipeQuantity = recipeQuantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<ComboEntity> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboEntity> combos) {
        this.combos = combos;
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public List<MenuItemForecastEntity> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<MenuItemForecastEntity> forecasts) {
        this.forecasts = forecasts;
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
        if (!(object instanceof DishEntity)) {
            return false;
        }
        DishEntity other = (DishEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.DishEntity[ id=" + id + " ]";
    }

}
