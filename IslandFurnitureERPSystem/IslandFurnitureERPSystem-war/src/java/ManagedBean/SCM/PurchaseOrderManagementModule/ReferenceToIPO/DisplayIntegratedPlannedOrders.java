/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule.ReferenceToIPO;

import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
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
@ManagedBean(name = "displayIntegratedPlannedOrders")
@ViewScoped
public class DisplayIntegratedPlannedOrders implements Serializable {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;

    private Long factoryId;
    private Collection<IntegratedPlannedOrderEntity> integratedPlannedOrderList;
    private String userId;

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");

            integratedPlannedOrderList = pmb.viewWaitingIntegratedPlannedOrder(factoryId);
            for (IntegratedPlannedOrderEntity ipo : integratedPlannedOrderList) {
                System.out.println("IPO = " + ipo.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(DisplayIntegratedPlannedOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Collection<IntegratedPlannedOrderEntity> getIntegratedPlannedOrderList() {
        return integratedPlannedOrderList;
    }

    public void setIntegratedPlannedOrderList(Collection<IntegratedPlannedOrderEntity> integratedPlannedOrderList) {
        this.integratedPlannedOrderList = integratedPlannedOrderList;
    }

    public DisplayIntegratedPlannedOrders() {
    }

    public String displayIntegratedPlannedOrders() throws Exception {
        UserEntity user = pmb.getUser(userId);
        if (user.getUserLevel() == 1 || user.getUserLevel() == 4) {
            return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/ReferenceToIntegratedPlannedOrder/DisplayAvailIntegratedPlannedOrders";
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Permission Denied", null));

            return "/secured/restricted/Factory/SCM/PurchasedItemAndSupplierManagementModule/PurchaseOrderManagementPage?faces-redirect=true";
        }
    }

}
