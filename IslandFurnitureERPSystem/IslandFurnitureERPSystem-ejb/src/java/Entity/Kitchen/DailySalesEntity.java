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
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Yoky
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"KITCHEN_ID", "SALESDATE"}))
public class DailySalesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<DishItemEntity> dishes = new ArrayList<>();
    @ManyToMany
    private List<ComboItemEntity> combos = new ArrayList<>();
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar salesDate = Calendar.getInstance();
    @ManyToOne
    private KitchenEntity kitchen;

    public DailySalesEntity() {
    }

    public DailySalesEntity(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DishItemEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishItemEntity> dishes) {
        this.dishes = dishes;
    }

    public List<ComboItemEntity> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboItemEntity> combos) {
        this.combos = combos;
    }

    public Calendar getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Calendar salesDate) {
        this.salesDate = salesDate;
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
        if (!(object instanceof DailySalesEntity)) {
            return false;
        }
        DailySalesEntity other = (DailySalesEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.DailySalesEntity[ id=" + id + " ]";
    }

}
