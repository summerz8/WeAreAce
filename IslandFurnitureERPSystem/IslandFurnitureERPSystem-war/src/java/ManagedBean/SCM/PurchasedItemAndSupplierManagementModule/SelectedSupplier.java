/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchasedItemAndSupplierManagementModule;

import Entity.Factory.SCM.SupplierEntity;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "selectedSupplier")
@ViewScoped
public class SelectedSupplier implements Serializable {

    SupplierEntity supplier;

    public SelectedSupplier() {
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity selectedSupplier) {
        this.supplier = selectedSupplier;
    }

    public String passValue() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedSupplier", supplier);
        return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/EditSupplier?faces-redirect=true";
    }
    
    public String passValue2(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedSupplier", supplier);
        return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/AddContract?faces-redirect=true";
    }
}
