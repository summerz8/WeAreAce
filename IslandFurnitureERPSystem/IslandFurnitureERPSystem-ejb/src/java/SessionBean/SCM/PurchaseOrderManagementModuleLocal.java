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

import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.InventoryRecordEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.DeliveryOrderEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
import Entity.Store.StoreEntity;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface PurchaseOrderManagementModuleLocal {

    public UserEntity getUser(String userId) throws Exception;

    public FactoryEntity getFactoryEntity(Long factoryiId) throws Exception;

    public StoreEntity getStoreEntity(Long storeId) throws Exception;

    public FactoryRawMaterialEntity getFactoryRM(Long itemId) throws Exception;

    public FactoryRetailProductEntity getFactoryRP(Long itemId) throws Exception;

    public ContractEntity getContract(Long contractId) throws Exception;

    public ContractEntity getContract2(Long supplierId, Long itemId, String itemType) throws Exception;
    
    public InventoryRecordEntity getIR(Calendar targetPeriod, String itemType, Long itemId) throws Exception;
    //1. View and Select item for purchase
    public Collection<FactoryRawMaterialEntity> viewRawMaterialWithSelectType(Long factoryId) throws Exception;

    public Collection<FactoryRetailProductEntity> viewRetailProductWithSelectType(Long factoryId) throws Exception;

    //2. Generate purchase amount
    //3. View and Select Supplier
    public Collection<SupplierEntity> viewSupplierForItem(String itemType, Long itemId) throws Exception;

    public Collection<SupplierEntity> viewSupplierForFactory(Long factoryId) throws Exception;

    //select a unexpired contract with given supplier and given raw material
    //this contract will later be passed to createPurchaseOrder() method
    public ContractEntity selectSupplier(String itemType, Long itemId, Long supplierId) throws Exception;

    //4. View and Select delivery address (for retail products)
    //display all available stores
    public List<StoreEntity> viewAvailStore(Long factoryId) throws Exception;

    //5. input amount for given date of goods receipt
    public Collection<DeliveryOrderEntity> getDeliveryAmountAndDate(Long integratedPlannedOrderId, Double purchaseAmount) throws Exception;

    //6. Generate purchase order
    //Method 1 : by manually input the purcahse item related information (with the above functions)
    public PurchaseOrderEntity createPurchaseOrder(Long factoryId, Long contractId,
            Double purchaseAmount, Long storeId, String destination, Calendar deliveryDate, Boolean isManual) throws Exception;

    //Method 2 : by reference to an integrated planned order
    //Step 1: system display a list of available integrated planned order for RM and RP 
    public List<IntegratedPlannedOrderEntity> viewWaitingIntegratedPlannedOrder(Long factoryId) throws Exception;

    public List<IntegratedPlannedOrderEntity> viewCancelledIntegratedPlannedOrder(Long factoryId) throws Exception;

    //Step 2: user choose one of the integrated planned order(either RM or RP)
    //input : integratedPlannedOrderId
    //output: display a list of available suppliers for the choose RM or RP   
    public List<SupplierEntity> viewAvailSupplier(Long factoryId, Long integratedPlannedOrderId, String itemType) throws Exception;

    //Step 3: user choose one of the suppliers displayed
    //input : supplierId, planned 1st of next month 's left inventory (means this month's left inventory)
    //output: display the generated amount for purchase
    public Double generatePurchaseAmount(Long integratedPlannedOrderId, Double nextMonthBeginPlannedAmount, String itemType) throws Exception;

    //Step 4: user confirm the displayed amount 
    //output: purchase order
    public PurchaseOrderEntity generatePurchaseOrder(Long factoryId, Long integratedPlannedOrderId,
            Double purchaseAmount, Double nextMonthBeginPlannedAmount,
            Long contractId, Long storeId, String destination, String itemType) throws Exception;

    public Collection<PurchaseOrderEntity> viewUnconfirmedPurchaseOrder(Long factoryId) throws Exception;

    public Collection<PurchaseOrderEntity> viewConfirmedPurchaseOrder(Long factoryId) throws Exception;

    //6. Edit unconfirmed purchase order
    public PurchaseOrderEntity editPurchaseOrder(Long purchaseOrderId, String status, Double totalAmount,
            String unit, Calendar createDate, String destination, Integer leadTime,
            Double totalPrice, FactoryEntity factory, IntegratedPlannedOrderEntity integratedPlannedOrder,
            ContractEntity contract) throws Exception;

    //8. Generate Goods Receipt
    public String confirmPurchaseOrder(String userId, Long purchaseOrderId) throws Exception;

    //7. Cancel purchase order
    public String cancelPurchaseOrder(String userId, Long purchaseOrderId) throws Exception;

    public String generateGoodsReciptForDeliveryOrders(Long purchaseOrderId, Long deliveryOrderId) throws Exception;

    //9. Generate Goods Receipt
    public String generateGoodsRecipt(Long purchaseOrderId) throws Exception;
}
