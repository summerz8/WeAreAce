/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule.ReferenceToIPO;

import Entity.Store.StoreEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean(name = "displayDestinationForPurchaseItem")
@SessionScoped
public class DisplayDestinationForPuchaseItem {

    /**
     * Creates a new instance of DisplayDestinationForPuchaseItem
     */
    public DisplayDestinationForPuchaseItem() {
    }

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;
    private Long storeId;
    private Long factoryId;
    private Collection<StoreEntity> storeList;
    private String itemType;

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("factoryId");
            storeList = pmb.viewAvailStore(factoryId);
        } catch (Exception ex) {
            Logger.getLogger(DisplayDestinationForPuchaseItem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public PurchaseOrderManagementModuleLocal getPmb() {
        return pmb;
    }

    public void setPmb(PurchaseOrderManagementModuleLocal pmb) {
        this.pmb = pmb;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Collection<StoreEntity> getStoreList() {
        return storeList;
    }

    public void setStoreList(Collection<StoreEntity> storeList) {
        this.storeList = storeList;
    }

    public String displayDestination() {
        if (itemType.equals("RawMaterial")) {
            return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/ReferenceToIntegratedPlannedOrder/DisplayGeneratedPO?faces-redirect=true";
        } else {//itemType.equals("RetailProduct")

            return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/ReferenceToIntegratedPlannedOrder/DisplayDeliveryDestination?faces-redirect=true";
        }
    }
}
