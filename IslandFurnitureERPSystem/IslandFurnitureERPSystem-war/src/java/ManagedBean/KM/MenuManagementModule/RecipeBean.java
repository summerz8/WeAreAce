/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.MenuManagementModule;

import Entity.Kitchen.DishEntity;
import Entity.Kitchen.IngredientEntity;
import Entity.Kitchen.IngredientItemEntity;
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "recipeBean")
@ViewScoped
public class RecipeBean implements Serializable {

    @EJB
    MenuManagementModuleLocal mm;

    private DishEntity selectedDish;
    private IngredientEntity ingredient;
    private Double quantity;
    private Long ingredientItemId;
    private List<IngredientItemEntity> filteredRecipeItems;
    private List<IngredientEntity> filteredIngredients;

    public RecipeBean() {
    }

    public DishEntity getSelectedDish() {
        return selectedDish;
    }

    public void setSelectedDish(DishEntity selectedDish) {
        this.selectedDish = selectedDish;
    }

    public List<IngredientItemEntity> getFilteredRecipeItems() {
        return filteredRecipeItems;
    }

    public void setFilteredRecipeItems(List<IngredientItemEntity> filteredRecipeItems) {
        this.filteredRecipeItems = filteredRecipeItems;
    }

    public List<IngredientEntity> getFilteredIngredients() {
        return mm.getIngredients(selectedDish.getKitchen().getId());
    }

    public void setFilteredIngredients(List<IngredientEntity> filteredIngredients) {
        this.filteredIngredients = filteredIngredients;
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

    @PostConstruct
    public void init() {
        try {
            selectedDish = (DishEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedDish");
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.RecipeBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredRecipeItems = mm.getRecipeItems(selectedDish.getId());
    }

    public void onRowEdit(RowEditEvent event) {
        IngredientItemEntity ii = (IngredientItemEntity) event.getObject();
        try {
            if (ii.getQuantity() < 0) {
                FacesMessage msg = new FacesMessage("Faild", "Quantity cannot be negative");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                Long iiId = mm.editRecipe(ii.getId(), ii.getQuantity());
                if (iiId == -1L) {
                    FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    FacesMessage msg = new FacesMessage("Successful", "Recipe Item " + iiId + " is Edited");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM.MenuManagementModule.RecipeBean: onRowEdit(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredRecipeItems = mm.getRecipeItems(selectedDish.getId());
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filteredRecipeItems = mm.getRecipeItems(selectedDish.getId());
    }

    public void deleteRecipeItem(Long ingredientItemId) {
        Long temp = mm.deleteRecipeItem(selectedDish.getId(), ingredientItemId);
        if (temp == -1L) {
            FacesMessage msg = new FacesMessage("Deletion Faild", "Recipe Item " + ingredientItemId + " is in some Combo");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (temp == -2L) {
            FacesMessage msg = new FacesMessage("Deletion Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Deletion Successful", "Recipe Item " + ingredientItemId + " is deleted");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        filteredRecipeItems = mm.getRecipeItems(selectedDish.getId());
    }

    public void addIngredientItem(ActionEvent event) {
        if (quantity == null) {
            FacesMessage msg = new FacesMessage("Faild", "Invalid Quantity Input");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (quantity < 0) {
            FacesMessage msg = new FacesMessage("Faild", "Quantity cannot be negative");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            ingredientItemId = mm.addIngredientItem(selectedDish.getId(), ingredient.getId(), quantity);
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
            filteredRecipeItems = mm.getRecipeItems(selectedDish.getId());
        }
        quantity = null;
    }
}
