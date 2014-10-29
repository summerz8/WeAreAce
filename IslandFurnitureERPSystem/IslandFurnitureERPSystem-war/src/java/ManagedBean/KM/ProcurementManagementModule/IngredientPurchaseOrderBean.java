/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.ProcurementManagementModule;

import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.IngredientPurchaseOrderEntity;
import Entity.Kitchen.KitchenEntity;
import SessionBean.KM.ProcurementManagementModuleLocal;
import java.io.Serializable;
import java.util.Calendar;
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
@ManagedBean(name = "ingredientPurchaseOrderBean")
@ViewScoped
public class IngredientPurchaseOrderBean implements Serializable {

    @EJB
    private ProcurementManagementModuleLocal pm;

    private KitchenEntity kitchen;
    private IngredientPurchaseOrderEntity selectedIPO;
    private List<IngredientItemEntity> filteredIPOItems;
    private Date selectedTargetDate;
    private String message;
    private Calendar cal;
    private Double total;
    private Double actualTotal;

    public IngredientPurchaseOrderBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public IngredientPurchaseOrderEntity getSelectedIPO() {
        return selectedIPO;
    }

    public void setSelectedIPO(IngredientPurchaseOrderEntity selectedIPO) {
        this.selectedIPO = selectedIPO;
    }

    public List<IngredientItemEntity> getFilteredIPOItems() {
        return filteredIPOItems;
    }

    public void setFilteredIPOItems(List<IngredientItemEntity> filteredIPOItems) {
        this.filteredIPOItems = filteredIPOItems;
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

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(Double actualTotal) {
        this.actualTotal = actualTotal;
    }

    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
            cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 2);
            selectedIPO = (IngredientPurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ingredientPurchaseOrderFromIF");
            if (selectedIPO == null) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('selectTargetDate').show();");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ingredientPurchaseOrderFromIF", null);
                filteredIPOItems = pm.getPurchaseItems(selectedIPO.getId());
                total = pm.getIPOTotal(selectedIPO.getId());
                actualTotal = total;
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            IngredientItemEntity ii = (IngredientItemEntity) event.getObject();
            if (ii.getQuantity() < 0) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Quantity cannot be negative");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                Long iiId = pm.editPurchaseItem(selectedIPO.getId(), ii.getId(), ii.getQuantity());
                if (iiId == -1L) {
                    FacesMessage msg = new FacesMessage("Edition Faild", "The new quantity does not comply with lot size constaint");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else if (iiId == -2L) {
                    FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    FacesMessage msg = new FacesMessage("Successful", "Ingredient Purxhase Order Item of " + ii.getIngredient().getName() + " is Edited");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
            filteredIPOItems = pm.getPurchaseItems(selectedIPO.getId());
            total = pm.getIPOTotal(selectedIPO.getId());
            actualTotal = total;
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
        filteredIPOItems = pm.getPurchaseItems(selectedIPO.getId());
    }

    public void comfirmIPO(ActionEvent event) {
        Long ipoId = pm.confirmIngredientPurchaseOrder(selectedIPO.getId(), actualTotal);
        if (ipoId == -1L) {
            FacesMessage msg = new FacesMessage("Confirmation Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Successful", "Raw Ingredient Purchase Order " + ipoId + " is confirmed");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        filteredIPOItems = pm.getPurchaseItems(selectedIPO.getId());
    }

    public void cancelIPO(ActionEvent event) {
        Long ipoId = pm.cancelIngredientPurchaseOrder(selectedIPO.getId());
        if (ipoId == -1L) {
            FacesMessage msg = new FacesMessage("Confirmation Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Successful", "Raw Ingredient Purchase Order " + ipoId + " is cancelled");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        filteredIPOItems = pm.getPurchaseItems(selectedIPO.getId());
    }

    public void findRequiredIngredientPurchaseOrder(ActionEvent event) {
        try {
            if (selectedTargetDate == null) {
                message = "Please Select A Date";
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('message').show();");
            } else {
                selectedIPO = pm.findIngredientPurchaseOrder(kitchen.getId(), selectedTargetDate);
                if (selectedIPO == null) {
                    message = "The Raw Ingredient Purchase Order for the selected target date is not generated yet";
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('message').show();");
                } else {
                    filteredIPOItems = pm.getPurchaseItems(selectedIPO.getId());
                    total = pm.getIPOTotal(selectedIPO.getId());
                    actualTotal = total;
                }
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: findRequiredMenuItemForecast(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void generateIngredientReceipt(ActionEvent event) {
        Long irId = pm.generateIngredientReceipt(selectedIPO.getId());
        if (irId == -1L) {
            FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Successful", "New Raw Ingredient Receipt " + irId + " is generated");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
