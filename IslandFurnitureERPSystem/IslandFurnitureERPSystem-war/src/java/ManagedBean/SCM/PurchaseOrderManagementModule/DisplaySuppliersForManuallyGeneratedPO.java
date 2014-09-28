/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule;

import Entity.Factory.SCM.SupplierEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "displaySuppliersForManuallyGeneratedPO")
@ViewScoped
public class DisplaySuppliersForManuallyGeneratedPO implements Serializable{

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;

    private Long factoryId;
    private Collection<SupplierEntity> supplierList;
    private String itemType;
    private Long itemId;
    
    @PostConstruct
    public void init() {

        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");
            itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");

            supplierList = pmb.viewSupplierForItem(itemType, itemId);

            for (SupplierEntity supplier : supplierList) {
                System.out.println(supplier.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(DisplaySuppliersForManuallyGeneratedPO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public DisplaySuppliersForManuallyGeneratedPO() {
    }
    
    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Collection<SupplierEntity> getSupplierList() {

        return supplierList;
    }

    public void setSupplierList(Collection<SupplierEntity> supplierList) {
        this.supplierList = supplierList;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String displaySuppliers(String itemType, Long itemId){
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", itemId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemType", itemType);

        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/DisplaySuppliersForManuallyGeneratedPO?faces-redirect=true";
    }
}
