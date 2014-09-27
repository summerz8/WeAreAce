/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule;

import Entity.Store.StoreEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "displayDeliveryDestinationForManuallyGeneratedPO")
@ViewScoped
public class DisplayDeliveryDestinationForManuallyGeneratedPO {

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
//        itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");
//        itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");
//        supplierId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("supplierId");

            storeList = pmb.viewAvailStore(factoryId);
        } catch (Exception ex) {
            Logger.getLogger(DisplayDeliveryDestinationForManuallyGeneratedPO.class.getName()).log(Level.SEVERE, null, ex);
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

    public DisplayDeliveryDestinationForManuallyGeneratedPO() {
    }

    public String displayDestination() {
        itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");
        if(itemType.equals("RawMaterial")){
            return "/secured/restricted/Factory/SCM/DisplaySuppliersForManuallyGeneratedPO/DisplayManuallyGeneratedPO?faces-redirect=true";
        }else{//itemType.equals("RetailProduct")
            
            return "/secured/restricted/Factory/SCM/DisplaySuppliersForManuallyGeneratedPO/DisplayDeliveryDestinationForRetailProduct?faces-redirect=true";
            
        }

    }

}
