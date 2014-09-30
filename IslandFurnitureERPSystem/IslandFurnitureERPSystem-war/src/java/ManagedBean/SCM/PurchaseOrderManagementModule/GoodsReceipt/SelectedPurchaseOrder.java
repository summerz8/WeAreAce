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
    private String result;

    public PurchaseOrderEntity getSelectedPO() {
        return selectedPO;
    }

    public void setSelectedPO(PurchaseOrderEntity selectedPO) {
        this.selectedPO = selectedPO;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    

    public SelectedPurchaseOrder() {
    }

    public void passValue() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedPO", selectedPO);
        
        if (selectedPO.getDeliveryOrderList().isEmpty()) {
            result = pmb.generateGoodsRecipt(selectedPO.getId());
        } else {
            result = "Go to next";
        }
    }
}
