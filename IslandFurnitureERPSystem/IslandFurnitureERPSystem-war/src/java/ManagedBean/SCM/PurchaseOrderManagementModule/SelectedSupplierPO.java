
package ManagedBean.SCM.PurchaseOrderManagementModule;


import Entity.Factory.SCM.SupplierEntity;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "selectedSupplierPO")
@ViewScoped
public class SelectedSupplierPO implements Serializable {

    private SupplierEntity supplier;

    public SelectedSupplierPO() {
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity selectedSupplier) {
        this.supplier = selectedSupplier;
    }

    public String passValue() {
        //put selected supplier to the session map
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedSupplier", supplier);
        
        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/DisplayContractForManuallyGeneratedPO?faces-redirect=true";
    }

}
