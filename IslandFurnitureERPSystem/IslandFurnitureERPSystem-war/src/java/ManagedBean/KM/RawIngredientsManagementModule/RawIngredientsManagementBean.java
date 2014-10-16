/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.RawIngredientsManagementModule;

import Entity.Kitchen.IngredientEntity;
import Entity.Kitchen.IngredientSupplierEntity;
import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.StoragePlaceEntity;
import SessionBean.KM.RawIngredientsManagementModuleLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "rawIngredientsManagementBean")
@ViewScoped
public class RawIngredientsManagementBean implements Serializable {

    @EJB
    private RawIngredientsManagementModuleLocal rim;

    private KitchenEntity kitchen;
    private String name;
    private Double price;
    private String unit;
    private String remark;
    private Double lotSize;
    private IngredientSupplierEntity supplier;
    private List<IngredientSupplierEntity> filteredSuppliers;
    private Long ingredientId;
    private List<StoragePlaceEntity> selectedStoragePlaces;
    private List<StoragePlaceEntity> filteredStoragePlaces;
    private IngredientEntity selectedIngredient;
    private List<IngredientEntity> filteredIngredients;
    

    public RawIngredientsManagementBean() {
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

    public Double getLotSize() {
        return lotSize;
    }

    public void setLotSize(Double lotSize) {
        this.lotSize = lotSize;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public IngredientSupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(IngredientSupplierEntity supplier) {
        this.supplier = supplier;
    }

    public List<StoragePlaceEntity> getSelectedStoragePlaces() {
        return selectedStoragePlaces;
    }

    public void setSelectedStoragePlaces(List<StoragePlaceEntity> selectedStoragePlaces) {
        this.selectedStoragePlaces = selectedStoragePlaces;
    }

    public IngredientEntity getSelectedIngredient() {
        return selectedIngredient;
    }

    public void setSelectedIngredient(IngredientEntity selectedIngredient) {
        this.selectedIngredient = selectedIngredient;
    }

    public List<IngredientEntity> getFilteredIngredients() {
        return filteredIngredients;
    }

    public void setFilteredIngredients(List<IngredientEntity> filteredIngredients) {
        this.filteredIngredients = filteredIngredients;
    }

    public List<IngredientSupplierEntity> getFilteredSuppliers() {
        return rim.getSuppliers(kitchen.getId());
    }

    public void setFilteredSuppliers(List<IngredientSupplierEntity> filteredSuppliers) {
        this.filteredSuppliers = filteredSuppliers;
    }

    public List<StoragePlaceEntity> getFilteredStoragePlaces() {
        return rim.getStoragePlaces(kitchen.getId());
    }

    public void setFilteredStoragePlaces(List<StoragePlaceEntity> filteredStoragePlaces) {
        this.filteredStoragePlaces = filteredStoragePlaces;
    }

    @PostConstruct
    public void init() {
        try {
            kitchen = kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM..RawIngredientsManagementModule.RawIngredientsManagementBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredIngredients = rim.getIngredients(kitchen.getId());
    }

    public void addIngredient(ActionEvent event) {
        ArrayList<Long> selectedStoragePlaceIds = new ArrayList<>();
        for (StoragePlaceEntity sp : selectedStoragePlaces) {
            selectedStoragePlaceIds.add(sp.getId());
        }
        ingredientId = rim.addIngredient(kitchen.getId(), name, price, unit, remark, lotSize, selectedStoragePlaceIds, supplier.getId());
        if (ingredientId == -1L) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "An unexpected exception occurred"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful", "New ingredient " + ingredientId + " is added"));
        }
        filteredIngredients = rim.getIngredients(kitchen.getId());
    }

    public void onRowEdit(RowEditEvent event) {
        IngredientEntity ingredient = (IngredientEntity) event.getObject();
        try {
            ArrayList<Long> selectedStoragePlaceIds = new ArrayList<>();
            for (StoragePlaceEntity sp : ingredient.getStoragePlaces()) {
                selectedStoragePlaceIds.add(sp.getId());
            }
            Long temp = rim.editIngredient(ingredient.getId(), ingredient.getName(), ingredient.getPrice(), ingredient.getUnit(), ingredient.getRemark(), ingredient.getLotSize(), selectedStoragePlaceIds, ingredient.getSupplier().getId());
            if (temp == -1L) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Ingredient " + ingredient.getId() + "is Edited");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM..RawIngredientsManagementModule.RawIngredientsManagementBean: onRowEdit(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredIngredients = rim.getIngredients(kitchen.getId());
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filteredIngredients = rim.getIngredients(kitchen.getId());
    }

    public void deleteIngredient(IngredientEntity ingredient) {
        try {
            Long temp = rim.deleteIngredient(ingredient.getId());
            if (temp == -1L) {
                FacesMessage msg = new FacesMessage("Faild", "Stock Level > 0");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (temp == -2L) {
                FacesMessage msg = new FacesMessage("Faild", "This Raw Ingredient is in Dishs' recipe");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (temp == -3L) {
                FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Raw Ingredient Deleted");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM..RawIngredientsManagementModule.RawIngredientsManagementBean: deleteIngredient(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredIngredients = rim.getIngredients(kitchen.getId());
    }

}
