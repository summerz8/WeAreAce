/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.MenuManagementModule;

import Entity.Kitchen.DishEntity;
import Entity.Kitchen.KitchenEntity;
import SessionBean.KM.MenuManagementModuleLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "dishListBean")
@ViewScoped
public class DishListBean implements Serializable {

    @EJB
    MenuManagementModuleLocal mm;

    private KitchenEntity kitchen;
    private DishEntity selectedDish;
    private List<DishEntity> filteredDishes;

    public DishListBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public DishEntity getSelectedDish() {
        return selectedDish;
    }

    public void setSelectedDish(DishEntity selectedDish) {
        this.selectedDish = selectedDish;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedDish", selectedDish);
    }

    public List<DishEntity> getFilteredDishes() {
        return filteredDishes;
    }

    public void setFilteredDishes(List<DishEntity> filteredDishes) {
        this.filteredDishes = filteredDishes;
    }

    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.DishListBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredDishes = mm.getDishes(kitchen.getId());
    }

    public void onRowEdit(RowEditEvent event) {
        DishEntity dish = (DishEntity) event.getObject();
        try {
            Long dishId = mm.editDish(dish.getId(), dish.getName(), dish.getPrice(), dish.getRemark(), dish.getRecipeQuantity());
            if (dishId == -1L) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Dish " + dish.getName() + " is Edited");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM.MenuManagementModule.DishListBean: onRowEdit(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredDishes = mm.getDishes(kitchen.getId());
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filteredDishes = mm.getDishes(kitchen.getId());
    }

    public void deleteDish(DishEntity dish) {
        Long temp = mm.deleteDish(dish.getId());
        if (temp == -1L) {
            FacesMessage msg = new FacesMessage("Deletion Faild", "Dish " + dish.getName() + " is in some Combo");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (temp == -2L) {
            FacesMessage msg = new FacesMessage("Deletion Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Deletion Successful", "Dish " + dish.getName() + " is deleted");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        filteredDishes = mm.getDishes(kitchen.getId());
    }

}
