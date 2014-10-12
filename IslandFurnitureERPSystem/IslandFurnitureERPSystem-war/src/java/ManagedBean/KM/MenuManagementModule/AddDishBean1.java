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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "addDishBean1")
@ViewScoped
public class AddDishBean1 implements Serializable {

    @EJB
    private MenuManagementModuleLocal mm;

    private KitchenEntity kitchen;
    private String name;
    private Double price;
    private String remark;
    private Integer recipeQuantity;
    private DishEntity dish;

    public AddDishBean1() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
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

    public Integer getRecipeQuantity() {
        return recipeQuantity;
    }

    public void setRecipeQuantity(Integer recipeQuantity) {
        this.recipeQuantity = recipeQuantity;
    }

    public DishEntity getDish() {
        return dish;
    }

    public void setDish(DishEntity dish) {
        this.dish = dish;
    }

    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.AddDishBean1: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void addDish(ActionEvent event) {
        dish = mm.addDish(kitchen.getId(), name, price, remark, recipeQuantity);
        if (dish == null) {
            FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Successful", "New Dish " + dish.getName() + " is Added");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("newDish", dish);
        }
    }

}
