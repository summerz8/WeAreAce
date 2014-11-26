/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.SCM.PurchaseOrderManagementModule.ReferenceToIPO;

import Entity.Factory.SCM.PurchaseOrderEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author dan
 */
@Named(value = "getGeneratedPO")
@ViewScoped
public class GetGeneratedPO {

     @EJB
    private PurchaseOrderManagementModuleLocal pmb;

    private PurchaseOrderEntity po;
    private String itemType;
    private Long itemId;
    private String itemName;

    @PostConstruct
    public void init() {
        try {
            
            po = (PurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("po");
            itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");
            itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");

            if (itemType.equals("RawMaterial")) {

                itemName = pmb.getFactoryRM(itemId).getMaterialName();

            } else {
                itemName = pmb.getFactoryRP(itemId).getName();
            }
        } catch (Exception ex) {
            Logger.getLogger(GetGeneratedPO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PurchaseOrderEntity getPo() {
        return po;
    }

    public void setPo(PurchaseOrderEntity po) {
        this.po = po;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String backToHome() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemType");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemId");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("po");
        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/PurchaseOrderManagementPage?faces-redirect=true";
    }
    public GetGeneratedPO() {
    }
    
}
