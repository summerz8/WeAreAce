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
@ManagedBean(name = "recordOutboundMovementBean")
@RequestScoped
public class RecordOutboundMovementBean {

    @EJB
    private FactoryInventoryManagementModuleLocal fim;

    @ManagedProperty(value = "#{loginBean.departmentId}")
    private long factoryId;
    private Long fromBinId;
    private Long toStoreId;
    private double quantity;
    private Calendar creationDate = Calendar.getInstance();
    private Calendar inputDate = Calendar.getInstance();
    private int itemTypeIndicator;
    private Long itemId;

    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
        this.factoryId = factoryId;
    }

    public Long getFromBinId() {
        return fromBinId;
    }

    public void setFromBinId(Long fromBinId) {
        this.fromBinId = fromBinId;
    }

    public Long getToStoreId() {
        return toStoreId;
    }

    public void setToStoreId(Long toStoreId) {
        this.toStoreId = toStoreId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
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

    public RecordOutboundMovementBean() {
    }

    public void recordOutboundMovement(ActionEvent event) {
        Long temp;
        if (itemTypeIndicator == 2) {
            temp = fim.recordFactoryProductOutboundMovement(factoryId, fromBinId, itemId, toStoreId, quantity, creationDate);
            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "toStoreId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "fromBinId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "factoryProduct is not found"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "specified storage bin does not contain this factoryProduct available for shipping"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "required quantity exceeds the total stock from this storage bin"));
            } else if (temp == -6L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "required quantity exceeds the available inventory stock in the factory"));
            } else if (temp == -7L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "unexpected exception occurred"));
            } else if (temp == -8L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "factory has no access to this factory bin"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Successful!",
                                ""));
            }
        } else {
            temp = fim.recordFactoryRetailProductOutboundMovement(factoryId, fromBinId, itemId, toStoreId, quantity, creationDate);
            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "toStoreId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "fromBinId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "factoryRetailProduct is not found"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "specified storage bin does not contain this factoryRetailProduct available for shipping"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "required quantity exceeds the total stock from this storage bin"));
            } else if (temp == -6L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "required quantity exceeds the available inventory stock in the factory"));
            } else if (temp == -7L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "unexpected exception occurred"));
            } else if (temp == -8L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "factory has no access to this factory bin"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Successful!",
                                ""));
            }
        }
    }

}
