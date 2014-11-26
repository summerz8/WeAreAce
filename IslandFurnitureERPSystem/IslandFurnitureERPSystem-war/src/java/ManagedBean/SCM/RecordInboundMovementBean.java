/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

import Entity.Factory.SCM.GoodsReceiptEntity;
import SessionBean.SCM.FactoryInventoryManagementModuleLocal;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "recordInboundMovementBean")
@SessionScoped
public class RecordInboundMovementBean implements Serializable {

    @EJB
    private FactoryInventoryManagementModuleLocal fim;

    @ManagedProperty(value = "#{loginBean.departmentId}")
    private Long factoryId;
    private Long goodsReceiptId;
    private Long toBinId;
    private String status;
    private Double quantity;
    private Calendar creationDate = Calendar.getInstance();
    private Calendar inputDate = Calendar.getInstance();
    private List<GoodsReceiptEntity> unfulfilledGoodsReceipts;

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Long getGoodsReceiptId() {
        return goodsReceiptId;
    }

    public void setGoodsReceiptId(Long goodsReceiptId) {
        this.goodsReceiptId = goodsReceiptId;
        System.out.println("1. GR ID: " + goodsReceiptId);
        System.out.println(goodsReceiptId.getClass());
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
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

    public List<GoodsReceiptEntity> getUnfulfilledGoodsReceipts() {
        unfulfilledGoodsReceipts = fim.findUnfulfilledGoodsReceipts(factoryId);
        return unfulfilledGoodsReceipts;
    }

    public void setUnfulfilledGoodsReceipts(List<GoodsReceiptEntity> unfulfilledGoodsReceipts) {
        this.unfulfilledGoodsReceipts = unfulfilledGoodsReceipts;
    }

    public RecordInboundMovementBean() {
    }

    public void recordInboundMovement(ActionEvent event) {
        System.out.println("3. GR ID: " + goodsReceiptId);
        System.out.println(goodsReceiptId.getClass());
        Long temp = fim.recordInboundMovement(factoryId, goodsReceiptId, toBinId, status, quantity, creationDate);
        if (temp == -1L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create inbound movement record",
                            "goodsReceiptId is invalid"));
        } else if (temp == -2L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create inbound movement record",
                            "factory has no access to this goods receipt"));
        } else if (temp == -3L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create inbound movement record",
                            "toBinId is invalid"));
        } else if (temp == -4L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create inbound movement record",
                            "factory has no access to this factory bin"));
        } else if (temp == -5L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create inbound movement record",
                            "unexpected exception occurred"));
        } else if (temp == -6L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create inbound movement record",
                            "quantity exceed the goods receipt amount"));
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Successful!",
                            ""));
        }
    }

    public void setSelectedGoodsReceipt(ActionEvent event) {
        goodsReceiptId = (Long) event.getComponent().getAttributes().get("goodsReceiptId");
        System.out.println("2. GR ID: " + goodsReceiptId);
        System.out.println(goodsReceiptId.getClass());
    }

}
