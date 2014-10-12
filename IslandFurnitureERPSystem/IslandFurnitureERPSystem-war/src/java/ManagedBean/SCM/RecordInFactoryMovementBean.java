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
@ManagedBean(name = "recordInFactoryMovementBean")
@RequestScoped
public class RecordInFactoryMovementBean {

    @EJB
    private FactoryInventoryManagementModuleLocal fim;

    @ManagedProperty(value = "#{loginBean.departmentId}")
    private long factoryId;
    private Calendar inputDate = Calendar.getInstance();
    private Long fromBinId;
    private Long toBinId;
    private String status;
    private double quantity;
    private Calendar creationDate = Calendar.getInstance();
    private int itemTypeIndicator;
    private Long itemId;

    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
        this.factoryId = factoryId;
    }

    public Date getInputDate() {
        return inputDate.getTime();
    }

    public void setInputDate(Date inputDate) {
        this.inputDate.setTime(inputDate);
        creationDate.setTime(inputDate);
    }

    public Long getFromBinId() {
        return fromBinId;
    }

    public void setFromBinId(Long fromBinId) {
        this.fromBinId = fromBinId;
    }

    public Long getToBinId() {
        return toBinId;
    }

    public void setToBinId(Long toBinId) {
        this.toBinId = toBinId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    
    public RecordInFactoryMovementBean() {
    }

    public void recordInFactoryMovement(ActionEvent event) {
        Long temp;
        if (itemTypeIndicator == 1) {
            temp = fim.recordInFactoryRawMaterialMovement(factoryId, fromBinId, toBinId, itemId, status, quantity, creationDate);

            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "fromBinId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "toBinId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "factoryRawMaterial is not found"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "specified storage bin does not contain this factoryRawMaterial with required status"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "required quantity exceeds the total stock from this storage bin"));
            } else if (temp == -6L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "unexpected exception occurredy"));
            } else if (temp == -7L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create infatcory movement",
                                "factory has no access to this factory bin"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Successful!",
                                ""));
            }

        } else if (itemTypeIndicator == 2) {
            temp = fim.recordInFactoryProductMovement(factoryId, fromBinId, toBinId, itemId, status, quantity, creationDate);
            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "fromBinId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "toBinId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "factoryProduct is not found"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "specified storage bin does not contain this factoryProduct with required status"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "required quantity exceeds the total stock from this storage bin"));
            } else if (temp == -6L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "unexpected exception occurred "));
            } else if (temp == -7L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create infactory movement",
                                "factory has no access to this factory bin"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Successful!",
                                ""));
            }
        } else {
            temp = fim.recordInFactoryRetailProductMovement(factoryId, fromBinId, toBinId, itemId, status, quantity, creationDate);
            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "fromBinId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "toBinId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "factoryRetailProduct is not found"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "specified storage bin does not contain this factoryRetailProduct with required status"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "required quantity exceeds the total stock from this storage bin"));
            } else if (temp == -6L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "unexpected exception occurred "));
            } else if (temp == -7L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create infactory movement record",
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
