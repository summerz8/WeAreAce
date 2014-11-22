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
    private Long frpId; 
    private Collection<StoreEntity> storeList;
    private String itemType;

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            frpId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");
            storeList = pmb.viewAvailStoreForRetailProduct(factoryId, frpId);
             
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

        System.out.println("displayDestination");
        itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");
        System.out.println("itemType : " + itemType);
        if (itemType.equals("RawMaterial")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("destination", "factory");
            return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/DisplayManuallyGeneratedPO?faces-redirect=true";
        } else {//itemType.equals("RetailProduct")
            return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/DisplayDeliveryDestinationForRetailProduct?faces-redirect=true";
        }

    }

}
