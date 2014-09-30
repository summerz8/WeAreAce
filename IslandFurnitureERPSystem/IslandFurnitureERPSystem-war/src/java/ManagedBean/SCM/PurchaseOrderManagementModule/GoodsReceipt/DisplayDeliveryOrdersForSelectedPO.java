/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule.GoodsReceipt;

import Entity.Factory.SCM.DeliveryOrderEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
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
@ManagedBean(name = "displayDeliveryOrdersForSelectedPO")
@ViewScoped
public class DisplayDeliveryOrdersForSelectedPO {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;

    private PurchaseOrderEntity po = new PurchaseOrderEntity();
    private Collection<DeliveryOrderEntity> deliveryOrderList;
    private DeliveryOrderEntity selectedDO = new DeliveryOrderEntity();
    private String result;

    @PostConstruct
    public void init() {
        try {
            po = (PurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedPO");
            System.out.println("init: selectedPO" + po.getId());
            po = pmb.getPO(po.getId());
            deliveryOrderList = po.getDeliveryOrderList();
        } catch (Exception ex) {
            Logger.getLogger(DisplayDeliveryOrdersForSelectedPO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PurchaseOrderEntity getPo() {
        return po;
    }

    public void setPo(PurchaseOrderEntity po) {
        this.po = po;
    }

    public Collection<DeliveryOrderEntity> getDeliveryOrderList() {
        return deliveryOrderList;
    }

    public void setDeliveryOrderList(Collection<DeliveryOrderEntity> deliveryOrderList) {
        this.deliveryOrderList = deliveryOrderList;
    }

    public DeliveryOrderEntity getSelectedDO() {
        return selectedDO;
    }

    public void setSelectedDO(DeliveryOrderEntity selectedDO) {
        this.selectedDO = selectedDO;
    }

    public DisplayDeliveryOrdersForSelectedPO() {
    }

    public String fulfillDO(DeliveryOrderEntity selectedDO) {
        try {
            this.selectedDO = selectedDO;
            System.out.println("po.getDestinationId()" + po.getDestinationId());
            System.out.println("this.selectedDO.getId()" + this.selectedDO.getId());
            result = pmb.generateGoodsReciptForDeliveryOrders(po.getDestinationId(), this.selectedDO.getId());

        } catch (Exception ex) {
            Logger.getLogger(DisplayDeliveryOrdersForSelectedPO.class.getName()).log(Level.SEVERE, null, ex);
        }

//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedPO");
        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/GoodsReceipt/DisplayDeliveryOrdersForSelectedPO?faces-redirect=true";
    }

}
