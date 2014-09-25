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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "editSupplier")
@ViewScoped
public class EditSupplier implements Serializable {

    @EJB
    private PurchasedItemAndSupplierManagementModuleLocal pmb;

    Long factoryId = 1L;
    Collection<SupplierEntity> supplierList;
    SupplierEntity selectedSupplier;
    String result = null;

    public EditSupplier() {
    }

    @PostConstruct
    public void init() {
        selectedSupplier = (SupplierEntity) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("selectedSupplier");

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

    public String save() throws Exception {

        System.out.println("save() ");

        selectedSupplier = (SupplierEntity) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().remove("selectedSupplier");

        System.out.println(this.selectedSupplier.toString());
        System.out.println(this.selectedSupplier.getSupplierName());

        result = pmb.editSupplier(factoryId, selectedSupplier.getSupplierName(),
                selectedSupplier.getSupplierAddress(), selectedSupplier.getSupplierContact(),
                selectedSupplier.getSupplierFax(), selectedSupplier.getRemark());

        FacesMessage msg = new FacesMessage("Information: " + result);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "/secured/WorkPlace?faces-redirect=true";

    }
}
