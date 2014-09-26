/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchasedItemAndSupplierManagementModule;

import SessionBean.SCM.PurchasedItemAndSupplierManagementModuleLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean(name = "addSupplier")
@ViewScoped
public class AddSupplierBean implements Serializable {

    @EJB
    private PurchasedItemAndSupplierManagementModuleLocal pmb;

    Long factoryId = 1L;
    String itemType = "test";
    Long itemId = 1L;
    String supplierName;
    String address;
    String telephone;
    String fax;
    String remark;
    Double contractPrice;
    Integer leadTime;
    Double lotSize;

    Calendar startDate = Calendar.getInstance();
    Calendar endDate = Calendar.getInstance();

    String result = null;

    public AddSupplierBean() {
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getSupplierName() {
        System.out.println("getSupplierName(): ");
        System.out.println("SupplierName1 = " + this.supplierName);
        return supplierName;

    }

    public void setSupplierName(String supplierName) {
        System.out.println("setSupplierName(): ");
        System.out.println("SupplierName2 = " + this.supplierName);
        this.supplierName = supplierName;
        System.out.println("SupplierName3 = " + this.supplierName);

    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getContractPrice() {
        System.out.println("getContactPrice():");
        System.out.println("Contract Price1 = " + this.contractPrice);

        return contractPrice;
    }

    public void setContractPrice(Double contractPrice) {
        System.out.println("setContractPrice(): ");

        System.out.println("Contract Price2 = " + this.contractPrice);

        this.contractPrice = contractPrice;

        System.out.println("Contract Price3 = " + this.contractPrice);
    }

    public Integer getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

    public Double getLotSize() {
        return lotSize;
    }

    public void setLotSize(Double lotSize) {
        this.lotSize = lotSize;
    }

    public Date getStartDate() {
        System.out.println("1. " + startDate.getTime().toString());
        return startDate.getTime();
    }

    public void setStartDate(Date startDate) {
        System.out.println("2." + startDate.toString());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        dateFormat.setTimeZone(this.startDate.getTimeZone());
        
        System.out.println("3." + dateFormat.format(this.startDate.getTime()));
        
        this.startDate.setTime(startDate);
        
        System.out.println("4." + dateFormat.format(this.startDate.getTime()));

    }

    public Date getEndDate() {
        return endDate.getTime();
    }

    public void setEndDate(Date endDate) {
        this.endDate.setTime(endDate);
    }

    public String setItemIdAndType(String itemType, Long itemId) {

        this.itemType = itemType;
        this.itemId = itemId;

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemType", itemType);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", itemId);
        return "/secured/SCM/PurchasedItemAndSupplierManagementModule/AddSupplier?faces-redirect=true";
    }

    public String save() throws Exception {

        System.out.println("save() ");
        System.out.println("ItemType = " + this.itemType);
        System.out.println("ItemId = " + this.itemId);

        itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");
        itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemType");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemId");

        System.out.println("SupplierName2 = " + this.supplierName);
        System.out.println("ItemType = " + this.itemType);
        System.out.println("ItemId = " + this.itemId);

        result = pmb.addSupplier(itemType, itemId, fax, address, telephone, fax, remark,
                contractPrice, leadTime, lotSize, startDate, endDate);

        System.out.println("Result = " + result);
        FacesMessage msg = new FacesMessage("Information: " + result);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "/secured/public/WorkPlace?faces-redirect=true";

    }

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }
}
