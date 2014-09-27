/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule;

import Entity.Factory.SCM.PurchaseOrderEntity;
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
public class DisplayUnconfirmedPO {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;
    private Long factoryId;
    private Collection<PurchaseOrderEntity> unconfirmedPOList;
    private PurchaseOrderEntity purchaseOrder;

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            unconfirmedPOList = pmb.viewUnconfirmedPurchaseOrder(factoryId);
        } catch (Exception ex) {
            Logger.getLogger(DisplayUnconfirmedPO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Collection<PurchaseOrderEntity> getUnconfirmedPOList() {
        return unconfirmedPOList;
    }

    public void setUnconfirmedPOList(Collection<PurchaseOrderEntity> unconfirmedPOList) {
        this.unconfirmedPOList = unconfirmedPOList;
    }

    public DisplayUnconfirmedPO() {
    }

    public String displayDisplayUnconfirmedPurchaseOrder() {
        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/DisplayUnconfirmedPO?faces-redirect=true";
    }

    public String confirmPurchaseOrder() {
        try {
            purchaseOrder = (PurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedUPO");
            Long userId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId");
            String result = pmb.confirmPurchaseOrder(userId, purchaseOrder.getId());
        } catch (Exception ex) {
            Logger.getLogger(DisplayUnconfirmedPO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/secured/public/WorkPlace.xhtml";
    }

    public String cancellUPO() {
        try {
            purchaseOrder = (PurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedUPO");
            Long userId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId");
            
            String result = pmb.cancelPurchaseOrder(userId, purchaseOrder.getId());
        } catch (Exception ex) {
            Logger.getLogger(DisplayUnconfirmedPO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "/secured/public/WorkPlace.xhtml";
    }
}
