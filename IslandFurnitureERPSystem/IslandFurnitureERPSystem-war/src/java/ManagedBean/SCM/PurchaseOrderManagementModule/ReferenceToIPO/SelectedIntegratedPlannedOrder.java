/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule.ReferenceToIPO;

import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean(name = "selectedIntegratedPlannedOrder")
@ViewScoped
public class SelectedIntegratedPlannedOrder implements Serializable{

    private IntegratedPlannedOrderEntity selectedIPO;

    public SelectedIntegratedPlannedOrder() {
    }

    public IntegratedPlannedOrderEntity getSelectedIPO() {
        return selectedIPO;
    }

    public void setSelectedIPO(IntegratedPlannedOrderEntity selectedIPO) {
        this.selectedIPO = selectedIPO;
    }

    public String passValue() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedIPO", this.selectedIPO);
        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/ReferenceToIntegratedPlannedOrder/DisplaySuppliersForIPO?faces-redirect=true";
    }
}
