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
@ManagedBean(name = "recordProductToBinMovementBean")
@RequestScoped
public class RecordProductToBinMovementBean {

    @EJB
    private FactoryInventoryManagementModuleLocal fim;

    @ManagedProperty(value = "#{loginBean.departmentId}")
    private long factoryId;
    private Long factoryProductId;
    private Long toBinId;
    private String status;
    private double quantity;
    private Calendar creationDate = Calendar.getInstance();
    private Calendar inputDate = Calendar.getInstance();

    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
        this.factoryId = factoryId;
    }

    public Long getFactoryProductId() {
        return factoryProductId;
    }

    public void setFactoryProductId(Long factoryProductId) {
        this.factoryProductId = factoryProductId;
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

    public Date getInputDate() {
        return inputDate.getTime();
    }

    public void setInputDate(Date inputDate) {
        this.inputDate.setTime(inputDate);
        creationDate.setTime(inputDate);
    }

    public RecordProductToBinMovementBean() {
    }

    public void recordProductToBinMovement(ActionEvent event) {
        Long temp = fim.recordProductToBinMovement(factoryId, factoryProductId, toBinId, status, quantity, creationDate);
        if (temp == -1L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record product to bin movement",
                            "factoryProductId is invalid"));
        } else if (temp == -2L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record product to bin movement",
                            "toBinId is invalid"));
        } else if (temp == -3L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record product to bin movement",
                            "unexpected exception occurred"));
        } else if (temp == -4L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record product to bin movement",
                            "factory has no access to this factory bin"));
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Successful!",
                            ""));
        }
    }

}
