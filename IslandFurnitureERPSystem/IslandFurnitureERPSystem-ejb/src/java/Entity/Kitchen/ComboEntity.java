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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Yoky
 */
@Entity
@XmlAccessorType(value = XmlAccessType.FIELD)
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"KITCHEN_ID", "NAME"}))
public class ComboEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(unique = true, nullable = false)
    private String name;
    private Double price;
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "DishItem_ID"))
    private List<DishItemEntity> dishes = new ArrayList<>();
    private String remark;
    private boolean deleted;
    @ManyToOne
    @XmlTransient
    private KitchenEntity kitchen;
    @ManyToMany
    private List<MenuItemForecastEntity> forecasts = new ArrayList<>();

    
    @ManyToMany(mappedBy = "combos")
    private List<DishEntity> dishList = new ArrayList<>();

    @ManyToMany
    private List<DailySalesEntity> dailySales = new ArrayList<>();


    public ComboEntity() {
        deleted = false;
    }

    public ComboEntity(String name, Double price, String remark, KitchenEntity kitchen) {
        this.name = name;
        this.price = price;
        this.remark = remark;
        this.deleted = false;
        this.kitchen = kitchen;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<DishItemEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishItemEntity> dishes) {
        this.dishes = dishes;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public List<DailySalesEntity> getDailySales() {
        return dailySales;
    }

    public void setDailySales(List<DailySalesEntity> dailySales) {
        this.dailySales = dailySales;
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
        if (!(object instanceof ComboEntity)) {
            return false;
        }
        ComboEntity other = (ComboEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.ComboEntity[ id=" + id + " ]";
    }

}
