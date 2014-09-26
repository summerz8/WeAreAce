/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule.ReferenceToIPO;

import Entity.Factory.SCM.SupplierEntity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean(name = "selectedSupplierForIPO")
@ViewScoped
public class SelectedSupplierForIPO {

    private SupplierEntity selectedSupplier;

    public SelectedSupplierForIPO() {
    }

    public SupplierEntity getSelectedSupplier() {
        return selectedSupplier;
    }

    public void setSelectedSupplier(SupplierEntity selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }
    
    
    public String passValue() {
        //put selected supplier to the session map
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedSupplierIPO", selectedSupplier);

        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/ReferenceToIntegratedPlannedOrder/DisplayContractForIPO?faces-redirect=true";
    }

}
