/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.DailyDemandForecastingModule;

import Entity.Kitchen.IngredientForecastEntity;
import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.IngredientPurchaseOrderEntity;
import Entity.Kitchen.KitchenEntity;
import SessionBean.KM.DailyDemandForecastingModuleLocal;
import SessionBean.KM.ProcurementManagementModuleLocal;
import java.io.Serializable;
import java.util.Date;
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
@ManagedBean(name = "ingredientForecastBean")
@ViewScoped
public class IngredientForecastBean implements Serializable {

    @EJB
    private DailyDemandForecastingModuleLocal ddf;
    @EJB
    private ProcurementManagementModuleLocal pm;

    private KitchenEntity kitchen;
    private IngredientForecastEntity selectedIF;
    private List<IngredientItemEntity> filteredIfItems;
    private Date selectedTargetDate;
    private String message;

    public IngredientForecastBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public IngredientForecastEntity getSelectedIF() {
        return selectedIF;
    }

    public void setSelectedIF(IngredientForecastEntity selectedIF) {
        this.selectedIF = selectedIF;
    }

    public List<IngredientItemEntity> getFilteredIfItems() {
        return filteredIfItems;
    }

    public void setFilteredIfItems(List<IngredientItemEntity> filteredIfItems) {
        this.filteredIfItems = filteredIfItems;
    }

    public Date getSelectedTargetDate() {
        return selectedTargetDate;
    }

    public void setSelectedTargetDate(Date selectedTargetDate) {
        this.selectedTargetDate = selectedTargetDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
            selectedIF = (IngredientForecastEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ingredientForecastFromMIF");
            if (selectedIF == null) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('selectTargetDate').show();");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ingredientForecastFromMIF", null);
                filteredIfItems = ddf.getIngredientForecastItems(selectedIF.getId());
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void generateIngredientPurchaseOrder(ActionEvent event) {
        try {
            IngredientPurchaseOrderEntity ipo = pm.generateIngredientPurchaseOrder(selectedIF.getId());
            if (ipo == null) {
                FacesMessage msg = new FacesMessage("Generation Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Ingredient Purchase Order " + ipo.getId() + " is genereted");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ingredientPurchaseOrderFromIF", ipo);
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.IngredientForecastFromMIFBean: generateIngredientPurchaseOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            IngredientItemEntity ii = (IngredientItemEntity) event.getObject();
            Long iiId = ddf.editIngredientForecastItem(ii.getId(), ii.getQuantity());
            if (iiId == -1L) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Ingredient Forecast Item of " + ii.getIngredient().getName() + " is Edited");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            filteredIfItems = ddf.getIngredientForecastItems(selectedIF.getId());
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: onRowEditDish(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filteredIfItems = ddf.getIngredientForecastItems(selectedIF.getId());
    }

    public void findRequiredIngredientForecast(ActionEvent event) {
        try {
            selectedIF = ddf.findIngredientForecast(kitchen.getId(), selectedTargetDate);
            if (selectedIF == null) {
                message = "The Raw Ingredient Forecast for the selected target date is not generated yet";
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('message').show();");
            } else {
                filteredIfItems = ddf.getIngredientForecastItems(selectedIF.getId());
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: findRequiredMenuItemForecast(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void viewIngredientPurchaseOrder(ActionEvent event) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ingredientPurchaseOrderFromIF", selectedIF.getPurchaseOrder());
    }
}
