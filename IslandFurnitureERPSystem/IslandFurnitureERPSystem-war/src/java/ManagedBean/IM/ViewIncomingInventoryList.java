/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Factory.SCM.OutboundMovementEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import SessionBean.IM.StoreInventoryControl;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

/**
 *
 * @author zhengyuan
 */
@Named(value = "viewIncomingInventoryList")
@ViewScoped
public class ViewIncomingInventoryList {

    /**
     * Creates a new instance of ViewIncomingInventoryList
     */
    public ViewIncomingInventoryList() {
    }
    
    @EJB
    StoreInventoryControl stc;
    
    
    private List<OutboundMovementEntity> incomingInventoryfromFactory;
    private List<PurchaseOrderEntity> incomingInventoryfromSupplier;
    private Long storeId;
    
    
}
