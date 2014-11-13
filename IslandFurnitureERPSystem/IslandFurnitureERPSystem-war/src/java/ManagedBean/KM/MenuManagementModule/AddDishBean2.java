/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.MenuManagementModule;

import Entity.Kitchen.DishEntity;
import Entity.Kitchen.IngredientEntity;
import Entity.Kitchen.IngredientItemEntity;
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
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "addDishBean2")
@ViewScoped
public class AddDishBean2 implements Serializable {

    @EJB
    private MenuManagementModuleLocal mm;

    private KitchenEntity kitchen;
    private DishEntity dish;
    private IngredientEntity ingredient;
    private Double quantity;
    private Long ingredientItemId;
    private List<IngredientItemEntity> filteredRecipeItems;
    private List<IngredientEntity> filteredIngredients;

    public AddDishBean2() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public DishEntity getDish() {
        return dish;
    }

    public void setDish(DishEntity dish) {
        this.dish = dish;
    }

    public IngredientEntity getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientEntity ingredient) {
        this.ingredient = ingredient;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getIngredientItemId() {
        return ingredientItemId;
    }

    public void setIngredientItemId(Long ingredientItemId) {
        this.ingredientItemId = ingredientItemId;
    }

    public List<IngredientItemEntity> getFilteredRecipeItems() {
        return filteredRecipeItems;
    }

    public void setFilteredRecipeItems(List<IngredientItemEntity> filteredRecipeItems) {
        this.filteredRecipeItems = filteredRecipeItems;
    }

    public List<IngredientEntity> getFilteredIngredients() {
        return mm.getIngredients(kitchen.getId());
    }

    public void setFilteredIngredients(List<IngredientEntity> filteredIngredients) {
        this.filteredIngredients = filteredIngredients;
    }

    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
            dish = (DishEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("newDish");
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.AddDishBean2: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredRecipeItems = mm.getRecipeItems(dish.getId());
    }

    public void addIngredientItem(ActionEvent event) {
        if (quantity == null) {
            FacesMessage msg = new FacesMessage("Faild", "Invalid Quantity Input");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (quantity < 0) {
            FacesMessage msg = new FacesMessage("Faild", "Quantity cannot be negative");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            ingredientItemId = mm.addIngredientItem(dish.getId(), ingredient.getId(), quantity);
            if (ingredientItemId == -1L) {
                FacesMessage msg = new FacesMessage("Faild", "The raw ingredient " + ingredient.getName() + " is already in the recipe");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (ingredientItemId == -2L) {
                FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "New Ingredient Item " + ingredientItemId + " is added");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('addRecipeItem').hide()");
            }
            filteredRecipeItems = mm.getRecipeItems(dish.getId());
        }
        quantity = null;
    }
}
