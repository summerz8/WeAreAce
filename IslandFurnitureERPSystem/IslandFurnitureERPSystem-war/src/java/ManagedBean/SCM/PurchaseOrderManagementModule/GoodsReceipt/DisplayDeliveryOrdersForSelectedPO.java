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

    private PurchaseOrderEntity po;
    private Collection<DeliveryOrderEntity> deliveryOrderList;
    private DeliveryOrderEntity selectedDO;
    private String result;

    @PostConstruct
    public void init() {
        po = (PurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedPO");
        deliveryOrderList = po.getDeliveryOrderList();
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
            result = pmb.generateGoodsReciptForDeliveryOrders(po.getDestinationId(), this.selectedDO.getId());

        } catch (Exception ex) {
            Logger.getLogger(DisplayDeliveryOrdersForSelectedPO.class.getName()).log(Level.SEVERE, null, ex);
        }

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedPO");

        return "/secured/public/WorkPlace.xhtml";
    }

}
