/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.RawIngredientsManagementModule;

import Entity.Kitchen.IngredientForecastEntity;
import Entity.Kitchen.IngredientIssueEntity;
import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.KitchenEntity;
import SessionBean.KM.RawIngredientsManagementModuleLocal;
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
@ManagedBean(name = "ingredientIssueBean")
@ViewScoped
public class IngredientIssueBean implements Serializable {

    @EJB
    private RawIngredientsManagementModuleLocal rim;

    private KitchenEntity kitchen;
    private IngredientIssueEntity selectedII;
    private List<IngredientItemEntity> filteredIIItems;
    private Date selectedTargetDate;

    private String message;

    public IngredientIssueBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public IngredientIssueEntity getSelectedII() {
        return selectedII;
    }

    public void setSelectedII(IngredientIssueEntity selectedII) {
        this.selectedII = selectedII;
    }

    public List<IngredientItemEntity> getFilteredIIItems() {
        return filteredIIItems;
    }

    public void setFilteredIIItems(List<IngredientItemEntity> filteredIIItems) {
        this.filteredIIItems = filteredIIItems;
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
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void confirmIngredientIssue(ActionEvent event) {
        try {
            Long iiId = rim.confirmIngredientIssue(selectedII.getId());
            if (iiId == -1L) {
                FacesMessage msg = new FacesMessage("Confirmation Faild", "Some request issue quantty is larger than the stock");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (iiId == -2L) {
                FacesMessage msg = new FacesMessage("Confirmation Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Ingredient Issue " + iiId + " is confirmed");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            filteredIIItems = rim.getIngredientIssueItems(selectedII.getId());
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.IngredientForecastFromMIFBean: confirmIngredientIssue(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void findRequiredIngredientIssue(ActionEvent event) {
        try {
            selectedII = rim.findIngredientIssue(kitchen.getId(), selectedTargetDate);
            if (selectedII == null) {
                IngredientForecastEntity selectedIF = rim.findIngredientForecast(kitchen.getId(), selectedTargetDate);
                if (selectedIF == null) {
                    message = "The Raw Ingredient Forecast referred to for the selected date is not generated yet";
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('message').show();");
                } else {
                    selectedII = rim.generateIngredientIssue(selectedIF.getId());
                    filteredIIItems = rim.getIngredientIssueItems(selectedII.getId());
                }
            } else {
                filteredIIItems = rim.getIngredientIssueItems(selectedII.getId());
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: findRequiredMenuItemForecast(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            IngredientItemEntity ii = (IngredientItemEntity) event.getObject();
            Long iiId = rim.editIngredientIssueItem(ii.getId(), ii.getQuantity());
            if (iiId == -1L) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Request issue quantity is larger than the stock");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (iiId == -2L) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Ingredient Issue Item of " + ii.getIngredient().getName() + " is Edited");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            filteredIIItems = rim.getIngredientIssueItems(selectedII.getId());
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
        filteredIIItems = rim.getIngredientIssueItems(selectedII.getId());
    }
}
