/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.CustomerOrderFulfillmentModule;

import Entity.Kitchen.ComboItemEntity;
import Entity.Kitchen.DailySalesEntity;
import Entity.Kitchen.DishItemEntity;
import Entity.Kitchen.KitchenEntity;
import SessionBean.KM.CustomerOrderFulfillmentModuleLocal;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "dailySalesBean")
@ViewScoped
public class DailySalesBean implements Serializable {
    @EJB
    private CustomerOrderFulfillmentModuleLocal cof;

    private KitchenEntity kitchen;
    private DailySalesEntity selectedDS;
    private List<DishItemEntity> filteredDishItems;
    private List<ComboItemEntity> filteredComboItems;
    private Date selectedDate;
    private String message;
    private Calendar cal;
    
    public DailySalesBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public DailySalesEntity getSelectedDS() {
        return selectedDS;
    }

    public void setSelectedDS(DailySalesEntity selectedDS) {
        this.selectedDS = selectedDS;
    }

    public List<DishItemEntity> getFilteredDishItems() {
        return filteredDishItems;
    }

    public void setFilteredDishItems(List<DishItemEntity> filteredDishItems) {
        this.filteredDishItems = filteredDishItems;
    }

    public List<ComboItemEntity> getFilteredComboItems() {
        return filteredComboItems;
    }

    public void setFilteredComboItems(List<ComboItemEntity> filteredComboItems) {
        this.filteredComboItems = filteredComboItems;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
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
    
    
    
    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
            cal = Calendar.getInstance();
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.CustomerOrderFulfillmentModule.DailySalesBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }
    
    public void findRequiredDailyOrders(ActionEvent event) {
        try {
            selectedDS = cof.findDailySales(kitchen.getId(), selectedDate);
            if (selectedDS == null) {
                message = "The Daily Sales for the selected date is not generated yet";
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('message').show();");
            } else {
                filteredDishItems = cof.findDailySalesDishItems(selectedDS.getId());
                filteredComboItems = cof.findDailySalesComboItems(selectedDS.getId());
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.CustomerOrderFulfillmentModule.DailySalesBean: findRequiredDailyOrders(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }
}
