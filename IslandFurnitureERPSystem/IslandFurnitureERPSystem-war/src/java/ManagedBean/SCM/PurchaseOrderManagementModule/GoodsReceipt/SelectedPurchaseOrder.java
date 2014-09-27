/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule.GoodsReceipt;

import Entity.Factory.SCM.PurchaseOrderEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean(name = "selectedPurchaseOrder")
@ViewScoped
public class SelectedPurchaseOrder {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;

    private PurchaseOrderEntity selectedPO;

    public PurchaseOrderEntity getSelectedPO() {
        return selectedPO;
    }

    public void setSelectedPO(PurchaseOrderEntity selectedPO) {
        this.selectedPO = selectedPO;
    }

    public SelectedPurchaseOrder() {
    }

    public String passValue() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedPO", selectedPO);
        if (selectedPO.getDeliveryDate() != null) {
            pmb.generateGoodsRecipt(selectedPO.getId());
            return "/secured/restricted/Factory/SCM/WorkPlace?faces-redirect=true";
        } else {
            return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/GoodsReceipt/DisplayDeliveryOrdersForSelectedPO?faces-redirect=true";
        }
    }
}
