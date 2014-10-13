/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

import SessionBean.SCM.FactoryInventoryManagementModuleLocal;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "recordReturnedItemInboundMovementBean")
@RequestScoped
public class RecordReturnedItemInboundMovementBean {

    @EJB
    private FactoryInventoryManagementModuleLocal fim;

    @ManagedProperty(value = "#{loginBean.departmentId}")
<<<<<<< HEAD
    private Long factoryId;
    private Long toBinId;
    private Long fromStoreId;
    private Double quantity;
=======
    private long factoryId;
    private Long toBinId;
    private Long fromStoreId;
    private double quantity;
>>>>>>> 2b4be6cba12607486d6f2d0ee91e40619f339de7
    private Calendar creationDate = Calendar.getInstance();
    private Calendar inputDate = Calendar.getInstance();
    private int itemTypeIndicator;
    private Long itemId;

<<<<<<< HEAD
    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
=======
    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
>>>>>>> 2b4be6cba12607486d6f2d0ee91e40619f339de7
        this.factoryId = factoryId;
    }

    public Long getToBinId() {
        return toBinId;
    }

    public void setToBinId(Long toBinId) {
        this.toBinId = toBinId;
    }

    public Long getFromStoreId() {
        return fromStoreId;
    }

    public void setFromStoreId(Long fromStoreId) {
        this.fromStoreId = fromStoreId;
    }

<<<<<<< HEAD
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
=======
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
>>>>>>> 2b4be6cba12607486d6f2d0ee91e40619f339de7
        this.quantity = quantity;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Date getInputDate() {
        return inputDate.getTime();
    }

    public void setInputDate(Date inputDate) {
        this.inputDate.setTime(inputDate);
        creationDate.setTime(inputDate);
    }

    public int getItemTypeIndicator() {
        return itemTypeIndicator;
    }

    public void setItemTypeIndicator(int itemTypeIndicator) {
        this.itemTypeIndicator = itemTypeIndicator;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public RecordReturnedItemInboundMovementBean() {
    }

    public void recordReturnedItemInboundMovement(ActionEvent event) {
        Long temp;
        if (itemTypeIndicator == 2) {
            temp = fim.recordReturnedProductInboundMovement(factoryId, itemId, fromStoreId, toBinId, quantity, creationDate);
            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "factoryProductId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "storeId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "toBinId is invalid"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "unexpected exception occurred"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "factory has no access to this factory bin"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Successful!",
                                ""));
            }
<<<<<<< HEAD
        } else if (itemTypeIndicator == 3){
=======
        } else {
>>>>>>> 2b4be6cba12607486d6f2d0ee91e40619f339de7
            temp = fim.recordReturnedRetailProductInboundMovement(factoryId, itemId, fromStoreId, toBinId, quantity, creationDate);
            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "factoryRetailProductId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "storeId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "toBinId is invalid"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "unexpected exception occurred"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "factory has no access to this factory bin"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Successful!",
                                ""));
            }
<<<<<<< HEAD
        } else{
            FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "please select valid item type"));
=======
>>>>>>> 2b4be6cba12607486d6f2d0ee91e40619f339de7
        }
    }

}
