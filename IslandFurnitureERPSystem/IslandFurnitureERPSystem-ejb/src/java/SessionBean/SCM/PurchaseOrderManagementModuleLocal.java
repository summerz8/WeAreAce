/*
 * PurchaseOrderManagementModuleLocal.java
 * 
 * 
 * 1. Select Item for purcahse.
 * 2. Generate purcahse amount
 * 3. Select Supplier
 * 4. Select delivery address (for stores only)
 * 5. Generate purchase order
 * 6. Edit unconfirmed prucashe order
 * 7. Cancel purchase order
 * 8. Generate Goods Receipt
 */
package SessionBean.SCM;

import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
import Entity.Store.StoreEntity;
import java.awt.List;
import java.util.Collection;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface PurchaseOrderManagementModuleLocal {

    //1. View and Select item for purchase

    public Collection<Object> viewItemwithSelectType(Long factoryId, String itemType) throws Exception;
    public Object selectItem(String itemType, Long itemId) throws Exception;

    //2. Generate purchase amount
    
    //3. View and Select Available Supplier
    public Set<SupplierEntity> viewAvailSupplier(String itemType, Long itemId) throws Exception;
    //select a unexpired contract with given supplier and given raw material
    //this contract will later be passed to createPurchaseOrder() method
    public ContractEntity selectSupplier(String itemType, Long itemId, Long supplierId) throws Exception;

    //4. View and Select delivery address (for retail products)
    //display all available stores
    public Set<StoreEntity> viewAvailStore(Long factoryId) throws Exception;
    
    //5. Generate purchase order
    //by manually input the purcahse item related information
    public PurchaseOrderEntity createPurchaseOrder(Long factoryId, Long contractId, Integer amount, Long storeId, String destination) throws Exception;  
    //by reference to selected planned order
    public Set<PlannedOrderEntity> viewAvailPlannedOrder() throws Exception;

    
    //6. Edit unconfirmed purchase order
    //7. Cancel purchase order
    //8. Generate Goods Receipt
}
