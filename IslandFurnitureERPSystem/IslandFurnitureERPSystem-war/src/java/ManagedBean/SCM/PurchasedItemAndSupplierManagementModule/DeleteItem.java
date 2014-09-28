/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchasedItemAndSupplierManagementModule;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import SessionBean.SCM.PurchasedItemAndSupplierManagementModuleLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "deleteItem")
@ViewScoped
public class DeleteItem implements Serializable {

    @EJB
    private PurchasedItemAndSupplierManagementModuleLocal pmb;

    Long factoryId;
    Collection<FactoryRawMaterialEntity> frmList;
    Collection<FactoryRetailProductEntity> frpList;
    private Long itemId;
    private String itemType;
    String result = null;

    public DeleteItem() {
    }

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");

            frmList = pmb.viewRawMaterialWithSelectType(factoryId);
            frpList = pmb.viewRetailProductWithSelectType(factoryId);

            for (FactoryRawMaterialEntity frm : frmList) {
                System.out.println(frm.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemsForPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public Collection<FactoryRawMaterialEntity> getFrmList() {
        return frmList;
    }

    public void setFrmList(Collection<FactoryRawMaterialEntity> frmList) {
        this.frmList = frmList;
    }

    public Collection<FactoryRetailProductEntity> getFrpList() {
        return frpList;
    }

    public void setFrpList(Collection<FactoryRetailProductEntity> frpList) {
        this.frpList = frpList;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String displayItems() {
        return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/DisplayItemsForDeleteItem?faces-redirect=true";

    }

    public String delete(String itemType, Long itemId) throws Exception {

        System.out.println("save() ");
        this.itemType = itemType;
        this.itemId = itemId;

        System.out.println("itemType " + this.itemType);
        System.out.println("itemId " + this.itemId);

        result = pmb.deleteItem(itemType, itemId);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Result: ", result);
        FacesContext.getCurrentInstance().addMessage(null,msg);

        return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/PurchasedItemAndSupplierManagementPage?faces-redirect=true";

    }

}
