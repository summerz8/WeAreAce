/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule;

import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean
@ViewScoped
public class DisplayUnconfirmedPO implements Serializable {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;
    private Long factoryId;
    private Collection<PurchaseOrderEntity> unconfirmedPOList;
    private PurchaseOrderEntity purchaseOrder;
    private String result;
    private String userId;

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");

            System.out.println("factoryId = " + factoryId);
            unconfirmedPOList = pmb.viewUnconfirmedPurchaseOrder(factoryId);
            for (PurchaseOrderEntity upo : unconfirmedPOList) {
                System.out.println("UPO: " + upo.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(DisplayUnconfirmedPO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String edit(PurchaseOrderEntity upo) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("upo", upo);
        if (upo.getContract().getFactoryRawMaterial() == null) {
            Long itemId = upo.getContract().getFactoryRetailProduct().getFactoryRetailProdctId();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemType", "RetailProduct");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", itemId);

        } else {
            Long itemId = upo.getContract().getFactoryRawMaterial().getFactoryRawMaterialId();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemType", "RawMaterial");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", itemId);
        }
        return "EditUnconfirmedPO?faces-redirect=true";
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

    public PurchaseOrderEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrderEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DisplayUnconfirmedPO() {
    }

    public String displayDisplayUnconfirmedPurchaseOrder() throws Exception {
        UserEntity user = pmb.getUser(userId);
        if (user.getUserLevel() == 1 || user.getUserLevel() == 4) {
            return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/DisplayUnconfirmedPO?faces-redirect=true";
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Permission Denied", null));

            return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/PurchaseOrderManagementPage?faces-redirect=true";
        }
    }

    public void confirmPurchaseOrder() {
        try {
            purchaseOrder = (PurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedUPO");
            String userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
            System.out.println("UserId = " + userId);
            result = pmb.confirmPurchaseOrder(userId, purchaseOrder.getId());
            System.out.println("Result = " + result);

        } catch (Exception ex) {
            Logger.getLogger(DisplayUnconfirmedPO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelUPO() {
        try {
            purchaseOrder = (PurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedUPO");
            String userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");

            result = pmb.cancelPurchaseOrder(userId, purchaseOrder.getId());

            System.out.println("Result = " + result);

        } catch (Exception ex) {
            Logger.getLogger(DisplayUnconfirmedPO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
