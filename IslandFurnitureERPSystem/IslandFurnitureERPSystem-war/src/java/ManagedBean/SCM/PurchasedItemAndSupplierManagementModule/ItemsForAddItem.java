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
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "itemsForAddItem")
@ViewScoped
public class ItemsForAddItem implements Serializable {

    @EJB
    private PurchasedItemAndSupplierManagementModuleLocal pmb;
    Long factoryId = 3L;
    Collection<RawMaterialEntity> rmList;
    Collection<RetailProductEntity> rpList;

    @PostConstruct
    public void init() {
        try {
            
            rmList = pmb.viewRawMaterialListNotInFactory(factoryId);
            rpList = pmb.viewRetailProductListNotInFactory(factoryId);

            for (RawMaterialEntity rm : rmList) {
                System.out.println(rm.toString());
            }
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

    public Collection<RawMaterialEntity> getRmList() {
        return rmList;
    }

    public void setRmList(Collection<RawMaterialEntity> rmList) {
        this.rmList = rmList;
    }

    public Collection<RetailProductEntity> getRpList() {
        return rpList;
    }

    public void setRpList(Collection<RetailProductEntity> rpList) {
        this.rpList = rpList;
    }
   
    public ItemsForAddItem() {
    }

    public PurchasedItemAndSupplierManagementModuleLocal getPmb() {
        return pmb;
    }

    public void setPmb(PurchasedItemAndSupplierManagementModuleLocal pmb) {
        this.pmb = pmb;
    }

    public String displayAllFactoryItems() throws Exception {
        return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/DisplayItemsForAddItem?faces-redirect=true";
    }
}
