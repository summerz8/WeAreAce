/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule.GoodsReceipt;

import Entity.Factory.SCM.PurchaseOrderEntity;
import ManagedBean.SCM.PurchaseOrderManagementModule.DisplayDeliveryDestinationForManuallyGeneratedPO;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean
@ViewScoped
public class DisplayConfirmedPO {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;
    private Long factoryId;

    private Collection<PurchaseOrderEntity> purchaseOrderList;

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("factoryId");
            purchaseOrderList = pmb.viewConfirmedPurchaseOrder(factoryId);
        } catch (Exception ex) {
            Logger.getLogger(DisplayDeliveryDestinationForManuallyGeneratedPO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Collection<PurchaseOrderEntity> getPurchaseOrderList() {
        return purchaseOrderList;
    }

    public void setPurchaseOrderList(Collection<PurchaseOrderEntity> purchaseOrderList) {
        this.purchaseOrderList = purchaseOrderList;
    }

    public DisplayConfirmedPO() {
    }

    public String displayDestination() {

        return "/secured/restricted/Factory/SCM/DisplaySuppliersForManuallyGeneratedPO/DisplayConfirmedPO?faces-redirect=true";
    }

}
