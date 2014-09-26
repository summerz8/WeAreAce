/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchasedItemAndSupplierManagementModule;

import Entity.Factory.RawMaterialEntity;
import Entity.Factory.RetailProductEntity;
import SessionBean.SCM.PurchasedItemAndSupplierManagementModuleLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "addItem")
@ViewScoped
public class AddItem  implements Serializable {

    @EJB
    private PurchasedItemAndSupplierManagementModuleLocal pmb;

    Long factoryId =  3L;
    String itemType = "test";
    Long itemId = 1L;
    
    RawMaterialEntity selectedRM;
    RetailProductEntity selectedRP;
    String result = null;

    public AddItem() {
    }

    public PurchasedItemAndSupplierManagementModuleLocal getPmb() {
        return pmb;
    }

    public void setPmb(PurchasedItemAndSupplierManagementModuleLocal pmb) {
        this.pmb = pmb;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public RawMaterialEntity getSelectedRM() {
        return selectedRM;
    }

    public void setSelectedRM(RawMaterialEntity selectedRM) {
        this.selectedRM = selectedRM;
    }

    public RetailProductEntity getSelectedRP() {
        return selectedRP;
    }

    public void setSelectedRP(RetailProductEntity selectedRP) {
        this.selectedRP = selectedRP;
    }

    public String add(String itemType, Long itemId) throws Exception {

        System.out.println("add() ");

        this.itemType = itemType;
        this.itemId = itemId;
        System.out.println("itemType " + this.itemType);
        System.out.println("itemId " + this.itemId);

        result = pmb.addItem(factoryId, itemType, itemId);
        FacesMessage msg = new FacesMessage("Information: " + result);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "/secured/WorkPlace?faces-redirect=true";

    }
}
