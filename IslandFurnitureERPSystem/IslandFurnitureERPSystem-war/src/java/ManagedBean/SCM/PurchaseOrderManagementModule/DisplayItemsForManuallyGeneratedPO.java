/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule;

import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "displayItemsForManuallyGeneratedPO")
@ViewScoped
public class DisplayItemsForManuallyGeneratedPO implements Serializable {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;

    private Long factoryId;
    private Collection<FactoryRawMaterialEntity> frmList;
    private Collection<FactoryRetailProductEntity> frpList;
    private String userId;

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");

            frmList = pmb.viewRawMaterialWithSelectType(factoryId);
            frpList = pmb.viewRetailProductWithSelectType(factoryId);

            for (FactoryRawMaterialEntity frm : frmList) {
                System.out.println(frm.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(DisplayItemsForManuallyGeneratedPO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DisplayItemsForManuallyGeneratedPO() {
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Collection<FactoryRawMaterialEntity> getFrmList() {
        return frmList;
    }

    public void setFrmList(Collection<FactoryRawMaterialEntity> frmList) {
        this.frmList = frmList;
    }

    public Collection<FactoryRetailProductEntity> getFrpList() {
        return frpList;
    }

    public void setFrpList(Collection<FactoryRetailProductEntity> frpList) {
        this.frpList = frpList;
    }

    public PurchaseOrderManagementModuleLocal getPmb() {
        return pmb;
    }

    public void setPmb(PurchaseOrderManagementModuleLocal pmb) {
        this.pmb = pmb;
    }

    public String displayAllFactoryItems() throws Exception {
        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/DisplayItemsForManuallyGeneratedPO.xhtml";

    }
}
