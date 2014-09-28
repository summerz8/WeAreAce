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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "itemsForPurchase")
@ViewScoped
public class ItemsForPurchase implements Serializable {

    @EJB
    private PurchasedItemAndSupplierManagementModuleLocal pmb;

    Long factoryId;
    Collection<FactoryRawMaterialEntity> frmList;
    Collection<FactoryRetailProductEntity> frpList;

    @PostConstruct
    public void init() {
        factoryId = (Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");

        System.out.println("factoryId "  + factoryId);
        try {
            frmList = pmb.viewRawMaterialWithSelectType(factoryId);
            frpList = pmb.viewRetailProductWithSelectType(factoryId);
        } catch (Exception ex) {
            Logger.getLogger(ItemsForPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public ItemsForPurchase() {
    }

    public PurchasedItemAndSupplierManagementModuleLocal getPmb() {
        return pmb;
    }

    public void setPmb(PurchasedItemAndSupplierManagementModuleLocal pmb) {
        this.pmb = pmb;
    }

    public String displayAllFactoryItems() throws Exception {
        return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/DisplayItemsForPurchase?faces-redirect=true";
    }
}
