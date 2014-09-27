/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.SCM.PurchaseOrderManagementModule.GoodsReceipt;

import Entity.Factory.SCM.DeliveryOrderEntity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean
@ViewScoped
public class SelectedDeliveryOrder {

    private DeliveryOrderEntity selectedDO;

    public DeliveryOrderEntity getSelectedDO() {
        return selectedDO;
    }

    public void setSelectedDO(DeliveryOrderEntity selectedDO) {
        this.selectedDO = selectedDO;
    }
    
    public SelectedDeliveryOrder() {
    }
    
}
