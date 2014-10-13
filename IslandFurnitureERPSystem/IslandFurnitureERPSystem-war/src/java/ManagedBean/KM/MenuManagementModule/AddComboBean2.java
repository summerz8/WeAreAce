/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.MenuManagementModule;

import Entity.Kitchen.ComboEntity;
import Entity.Kitchen.DishEntity;
import Entity.Kitchen.DishItemEntity;
import Entity.Kitchen.KitchenEntity;
import SessionBean.KM.MenuManagementModuleLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "addComboBean2")
@RequestScoped
public class AddComboBean2 implements Serializable {

    @EJB
    private MenuManagementModuleLocal mm;

    private KitchenEntity kitchen;
    private ComboEntity combo;
    private DishEntity dish;
    private Integer quantity;
    private List<DishItemEntity> filteredDishItems;
    private List<DishEntity> filteredDishs;

    public AddComboBean2() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public ComboEntity getCombo() {
        return combo;
    }

    public void setCombo(ComboEntity combo) {
        this.combo = combo;
    }

    public DishEntity getDish() {
        return dish;
    }

    public void setDish(DishEntity dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<DishItemEntity> getFilteredDishItems() {
        return filteredDishItems;
    }

    public void setFilteredDishItems(List<DishItemEntity> filteredDishItems) {
        this.filteredDishItems = filteredDishItems;
    }

    public List<DishEntity> getFilteredDishs() {
        return mm.getDishes(kitchen.getId());
    }

    public void setFilteredDishs(List<DishEntity> filteredDishs) {
        this.filteredDishs = filteredDishs;
    }
    
    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
            combo = (ComboEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("newCombo");
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.AddComboBean2: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredDishItems = mm.getDishItems(combo.getId());
    }
    
    public void addDishItem(ActionEvent event) {
        Long dishItemId = mm.addDishItem(combo.getId(), dish.getId(), quantity);
        if (dishItemId == -1L) {
            FacesMessage msg = new FacesMessage("Faild", "The Dish " + dish.getName() + " is already in the Combo");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (dishItemId == -2L) {
            FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Successful", "New Dish Item " + dishItemId + " is added");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        filteredDishItems = mm.getDishItems(combo.getId());
    }

}
