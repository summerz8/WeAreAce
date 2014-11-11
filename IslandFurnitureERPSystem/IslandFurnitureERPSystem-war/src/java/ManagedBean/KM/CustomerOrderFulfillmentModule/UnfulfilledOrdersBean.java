/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.CustomerOrderFulfillmentModule;

import Entity.Kitchen.DetailedDishItemEntity;
import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.KitchenOrderEntity;
import SessionBean.KM.CustomerOrderFulfillmentModuleLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "unfulfilledOrdersBean")
@ViewScoped
public class UnfulfilledOrdersBean implements Serializable {

    @EJB
    private CustomerOrderFulfillmentModuleLocal cof;

    private KitchenEntity kitchen;
    private List<KitchenOrderEntity> orders;
    private List<KitchenOrderEntity> filteredOrders;
    private KitchenOrderEntity selectedOdr;
    private DetailedDishItemEntity selectedDdi;
    private List<IngredientItemEntity> selectedRecipe;

    public UnfulfilledOrdersBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public KitchenOrderEntity getSelectedOdr() {
        return selectedOdr;
    }

    public void setSelectedOdr(KitchenOrderEntity selectedOdr) {
        this.selectedOdr = selectedOdr;
    }

    public DetailedDishItemEntity getSelectedDdi() {
        return selectedDdi;
    }

    public void setSelectedDdi(DetailedDishItemEntity selectedDdi) {
        this.selectedDdi = selectedDdi;
    }

    public List<KitchenOrderEntity> getOrders() {
        return orders;
    }

    public List<KitchenOrderEntity> getFilteredOrders() {
        return filteredOrders;
    }

    public void setFilteredOrders(List<KitchenOrderEntity> filteredOrders) {
        this.filteredOrders = filteredOrders;
    }

    public void setOrders(List<KitchenOrderEntity> orders) {
        this.orders = orders;
    }

    public List<IngredientItemEntity> getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(List<IngredientItemEntity> selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
    }

    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
            orders = cof.getUnfulfilledOrders(kitchen.getId());
            filteredOrders = orders;
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.CustomerOrderFulfillmentModule.CurrentDateUnfulfilledOrdersBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void fulfillDishItem(Long orderId, Long detailedDishItemId) {
        try {
            Long temp = cof.fulfillDishItem(orderId, detailedDishItemId);
            if (temp == -1L) {
                FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "The detailed dish item " + detailedDishItemId + " is fulfilled");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                orders = cof.getUnfulfilledOrders(kitchen.getId());
                filteredOrders = orders;
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM.CustomerOrderFulfillmentModule.CurrentDateUnfulfilledOrdersBean: fulfillDishItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void findRecipe(DetailedDishItemEntity ddi) {
        selectedDdi = ddi;
        selectedRecipe = cof.findRecipe(ddi.getDish().getId());
    }

    public void update() {
        orders = cof.getUnfulfilledOrders(kitchen.getId());
        filteredOrders = orders;
    }
}
