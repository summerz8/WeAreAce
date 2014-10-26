/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.CustomerOrderFulfillmentModule;

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
@ManagedBean(name = "currentDateUnservedOrdersBean")
@ViewScoped
public class CurrentDateUnservedOrdersBean implements Serializable {
    
    @EJB
    private CustomerOrderFulfillmentModuleLocal cof;

    private KitchenEntity kitchen;
    private List<KitchenOrderEntity> orders;
    private List<KitchenOrderEntity> filteredOrders;
    private KitchenOrderEntity selectedOdr;
    
    public CurrentDateUnservedOrdersBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public List<KitchenOrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<KitchenOrderEntity> orders) {
        this.orders = orders;
    }

    public List<KitchenOrderEntity> getFilteredOrders() {
        return filteredOrders;
    }

    public void setFilteredOrders(List<KitchenOrderEntity> filteredOrders) {
        this.filteredOrders = filteredOrders;
    }

    public KitchenOrderEntity getSelectedOdr() {
        return selectedOdr;
    }

    public void setSelectedOdr(KitchenOrderEntity selectedOdr) {
        this.selectedOdr = selectedOdr;
    }

    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
            orders = cof.getCurrentDateUnservedOrder(kitchen.getId());
            filteredOrders = orders;
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.CustomerOrderFulfillmentModule.CurrentDateUnservedOrdersBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }
    public void serveOrder(Long orderId) {
        Long temp = cof.serveOrder(orderId);
        if (temp == -1L) {
                FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "The Order  " + orderId + " is fulfilled");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                filteredOrders = cof.getCurrentDateUnservedOrder(kitchen.getId());
            }
    }
}
