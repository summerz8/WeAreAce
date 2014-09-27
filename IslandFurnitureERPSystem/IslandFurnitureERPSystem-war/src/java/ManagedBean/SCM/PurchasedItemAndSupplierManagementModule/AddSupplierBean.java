/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchasedItemAndSupplierManagementModule;

import SessionBean.SCM.PurchasedItemAndSupplierManagementModuleLocal;
import java.io.Serializable;
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

    Long factoryId;
    String itemType;
    Long itemId;
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

    String result;

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
        return supplierName;

    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
        
        return contractPrice;
    }

    public void setContractPrice(Double contractPrice) {
         this.contractPrice = contractPrice;
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
        return startDate.getTime();
    }

    public void setStartDate(Date startDate) {
        this.startDate.setTime(startDate);
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
        
        return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/AddSupplier?faces-redirect=true";
    }

    public String save() throws Exception {

     
        itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");
        itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemType");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemId");

        result = pmb.addSupplier(itemType, itemId, supplierName, address, telephone, fax, remark,
                contractPrice, leadTime, lotSize, startDate, endDate);

        FacesMessage msg = new FacesMessage("Information: " + result);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "/secured/public/WorkPlace?faces-redirect=true";

    }

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }
}
