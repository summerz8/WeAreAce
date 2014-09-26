/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchasedItemAndSupplierManagementModule;

import Entity.Factory.SCM.SupplierEntity;
import SessionBean.SCM.PurchasedItemAndSupplierManagementModuleLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean(name = "displaySuppliersForAddContract")
@ViewScoped
public class DisplaySuppliersForAddContract implements Serializable {

    @EJB
    private PurchasedItemAndSupplierManagementModuleLocal pmb;

    @PostConstruct
    public void init() {

        try {

            itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");
            itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");

            System.out.println("displaySuppliers(): ItemType = " + this.itemType);
            System.out.println("displaySuppliers(): Itemid = " + this.itemId);
            
            supplierList = pmb.viewSupplierCouldBeAddedForItem(itemType, itemId);

            for (SupplierEntity supplier : supplierList) {
                System.out.println(supplier.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(DisplaySuppliersForAddContract.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    Long factoryId = 1L;
    Collection<SupplierEntity> supplierList;
    String itemType;
    Long itemId;

    public DisplaySuppliersForAddContract() {
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

    public Collection<SupplierEntity> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(Collection<SupplierEntity> supplierList) {
        this.supplierList = supplierList;
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

    public String displaySuppliers(String itemType, Long itemId) throws Exception {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", itemId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemType", itemType);

        return "/secured/SCM/PurchasedItemAndSupplierManagementModule/DisplaySuppliersForAddContract?faces-redirect=true";
    }

}
