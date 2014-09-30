/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchasedItemAndSupplierManagementModule;

import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.SCM.SupplierEntity;
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
@Named(value = "displaySuppliersForEditSupplier")
@ViewScoped
public class DisplaySuppliersForEditSupplier implements Serializable {

    @EJB
    private PurchasedItemAndSupplierManagementModuleLocal pmb;

    Long factoryId;
    Collection<SupplierEntity> supplierList;
    private String userId;

    @PostConstruct
    public void init() {
        try {
            System.out.println("displaySuppliers():");
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");

            supplierList = pmb.viewAvailSupplier(factoryId);

            for (SupplierEntity supplier : supplierList) {
                System.out.println(supplier.toString());
            }

        } catch (Exception ex) {
            Logger.getLogger(ItemsForPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DisplaySuppliersForEditSupplier() {
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

    public String displaySuppliers() throws Exception {
        UserEntity user = pmb.getUser(userId);
        if (user.getUserLevel() == 1 || user.getUserLevel() == 4) {
            return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/DisplaySuppliersForEditSupplier?faces-redirect=true";
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Permission Denied", null));

            return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/PurchasedItemAndSupplierManagementPage?faces-redirect=true";
        }

    }
}
