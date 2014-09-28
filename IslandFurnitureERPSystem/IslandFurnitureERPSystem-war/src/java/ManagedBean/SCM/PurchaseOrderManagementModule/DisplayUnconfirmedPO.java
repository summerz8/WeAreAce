/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule;

import Entity.Factory.SCM.PurchaseOrderEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.io.Serializable;
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
public class DisplayUnconfirmedPO implements Serializable{

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;
    private Long factoryId;
    private Collection<PurchaseOrderEntity> unconfirmedPOList;
    private PurchaseOrderEntity purchaseOrder;

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            System.out.println("factoryId = " + factoryId);
            unconfirmedPOList = pmb.viewUnconfirmedPurchaseOrder(factoryId);
            for(PurchaseOrderEntity upo : unconfirmedPOList){
                System.out.println("UPO: " + upo.toString());
            }
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
            String userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
            System.out.println("UserId = " + userId);
            String result = pmb.confirmPurchaseOrder(userId, purchaseOrder.getId());
        } catch (Exception ex) {
            Logger.getLogger(DisplayUnconfirmedPO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/DisplayUnconfirmedPO?faces-redirect=true";
    }

    public String cancelUPO() {
        try {
            purchaseOrder = (PurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedUPO");
            String userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
            
            String result = pmb.cancelPurchaseOrder(userId, purchaseOrder.getId());
        } catch (Exception ex) {
            Logger.getLogger(DisplayUnconfirmedPO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/DisplayUnconfirmedPO?faces-redirect=true";
    }
}
