/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchasedItemAndSupplierManagementModule;

import Entity.Factory.SCM.SupplierEntity;
import SessionBean.SCM.PurchasedItemAndSupplierManagementModuleLocal;
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
@Named(value = "deleteSupplier")
@ViewScoped
public class DeleteSupplier {

    @EJB
    private PurchasedItemAndSupplierManagementModuleLocal pmb;

    Long factoryId = 1L;
    Collection<SupplierEntity> supplierList;
    SupplierEntity selectedSupplier;
    String result = null;

    @PostConstruct
    public void init() {
        try {
            System.out.println("displaySuppliers():");

            supplierList = pmb.viewAvailSupplier(factoryId);

            for (SupplierEntity supplier : supplierList) {
                System.out.println(supplier.toString());
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

    public Collection<SupplierEntity> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(Collection<SupplierEntity> supplierList) {
        this.supplierList = supplierList;
    }

    public SupplierEntity getSelectedSupplier() {
        return selectedSupplier;
    }

    public void setSelectedSupplier(SupplierEntity selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }

    public PurchasedItemAndSupplierManagementModuleLocal getPmb() {
        return pmb;
    }

    public void setPmb(PurchasedItemAndSupplierManagementModuleLocal pmb) {
        this.pmb = pmb;
    }

    public DeleteSupplier() {
    }

    public String displaySuppliers() throws Exception {
        return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/DisplaySuppliersForDeleteSupplier?faces-redirect=true";
    }

    public String delete() throws Exception {

        System.out.println("save() ");
        
        System.out.println(this.selectedSupplier.toString());
        System.out.println(this.selectedSupplier.getSupplierName());

        result = pmb.deleteSupplier(this.selectedSupplier.getSupplierId());

        FacesMessage msg = new FacesMessage("Information: " + result);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "/secured/WorkPlace?faces-redirect=true";

    }

}
