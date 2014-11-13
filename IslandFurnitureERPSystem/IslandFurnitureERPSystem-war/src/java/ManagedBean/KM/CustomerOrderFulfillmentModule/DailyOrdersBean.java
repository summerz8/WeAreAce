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
@ManagedBean(name = "dailyOrdersBean")
@ViewScoped
public class DailyOrdersBean implements Serializable {

    @EJB
    private CustomerOrderFulfillmentModuleLocal cof;

    private KitchenEntity kitchen;
    private KitchenOrderEntity selectedOdr;
    private List<KitchenOrderEntity> orders;
    private Date selectedDate;
    private String message;
    private Calendar cal;

    public DailyOrdersBean() {
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
        this.selectedOdr = cof.findOrderById(selectedOdr.getId());
    }

    public List<KitchenOrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<KitchenOrderEntity> orders) {
        this.orders = orders;
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
            System.err.println("ManagedBean.KM.CustomerOrderFulfillmentModule.DailyOrdersBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void findRequiredDailyOrders(ActionEvent event) {
        try {
            if (selectedDate == null) {
                message = "Please Select A Date";
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('message').show();");
            } else {
                orders = cof.getDailyOrders(kitchen.getId(), selectedDate);
                if (orders.isEmpty()) {
                    message = "The Daily Orders for the selected date is not available";
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('message').show();");
                } else if (orders == null) {
                    message = "Unexpected Error Occurred";
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('message').show();");
                }
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.CustomerOrderFulfillmentModule.DailyOrdersBean: findRequiredIngredientForecast(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

}
