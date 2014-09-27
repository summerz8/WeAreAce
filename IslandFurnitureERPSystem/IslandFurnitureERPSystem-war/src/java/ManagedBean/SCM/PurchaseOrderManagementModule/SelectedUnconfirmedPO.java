/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.SCM.PurchaseOrderManagementModule;

import Entity.Factory.SCM.PurchaseOrderEntity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean(name = "selectedUnconfirmedPO")
@ViewScoped
public class SelectedUnconfirmedPO {

    PurchaseOrderEntity upo;

    public PurchaseOrderEntity getUpo() {
        return upo;
    }

    public void setUpo(PurchaseOrderEntity upo) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedUPO", upo);
        this.upo = upo;
    }
    
    public SelectedUnconfirmedPO() {
    }
    
    
    
}
