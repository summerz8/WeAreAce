/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchasedItemAndSupplierManagementModule;

import Entity.CommonInfrastructure.UserEntity;
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

    Long factoryId;
    Collection<SupplierEntity> supplierList;
    SupplierEntity selectedSupplier;
    String result = null;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DeleteSupplier() {
    }

//    public String displaySuppliers() throws Exception {
//            return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/DisplaySuppliersForDeleteSupplier?faces-redirect=true";
//    }

    public void delete() throws Exception {

        System.out.println("save() ");

        System.out.println(this.selectedSupplier.toString());
        System.out.println(this.selectedSupplier.getSupplierName());

        result = pmb.deleteSupplier(this.selectedSupplier.getSupplierId());
    }

}
