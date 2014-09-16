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

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.InventoryRecordEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.DeliveryOrderEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Shiyu
 */
@Stateful
public class PurchaseOrderManagementModule implements PurchaseOrderManagementModuleLocal {

    @PersistenceContext
    private EntityManager em;

    public PurchaseOrderManagementModule() {
    }

    //1. View an item for purcahse 
    //pre-con: user select a type of items(RM/RP)
    //post-con: a list of item of the select type(RM / RP)
    @Override
    public Collection<Object> viewItemwithSelectType(Long factoryId, String itemType) throws Exception {
        System.out.println("viewItemwithSelectType():");

        Collection<Object> itemList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);

            if (itemType.equals("RawMaterial")) {
                Collection<FactoryRawMaterialEntity> rawMaterialList = factory.getFactoryRawMaterials();
                Iterator iterator = rawMaterialList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    itemList.add(obj);
                }
            } else {// itemType.equals("RetailProduct")
                Collection<FactoryRetailProductEntity> retailProductList = factory.getFactoryRetailProducts();
                Iterator iterator = retailProductList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    itemList.add(obj);
                }
                return itemList;
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return itemList;
    }

    //select an item for purchase
    @Override
    public Object selectItem(String itemType, Long itemId) throws Exception {
        System.out.println("selectItem():");

        try {
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity rawMaterial = em.find(FactoryRawMaterialEntity.class, itemId);
                return (Object) rawMaterial;
            } else {// itemType.equals("RetailProduct")
                FactoryRetailProductEntity retailProduct = em.find(FactoryRetailProductEntity.class, itemId);
                return (Object) retailProduct;
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return null;
    }

    //2. Generate purchase amount
    //See 5, Method 2, Step 3 * 
    //3. View and Select Available Supplier(whose contract has not expired)
    @Override
    public Set<SupplierEntity> viewAvailSupplier(String itemType, Long itemId) throws Exception {
        System.out.println("viewAvailSupplier():");

        Set<SupplierEntity> supplierList = new HashSet<>();

        try {
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity item = em.find(FactoryRawMaterialEntity.class, itemId);
                Collection<ContractEntity> contractList = item.getContracts();
                Iterator iterator = contractList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;

                    if (!isExpired(contract)) {//check unexpired contract
                        SupplierEntity supplier = contract.getSupplier();
                        supplierList.add(supplier);
                    }
                }
                return supplierList;

            } else {// itemType.equals("RetailProduct")
                FactoryRetailProductEntity item = em.find(FactoryRetailProductEntity.class, itemId);
                Collection<ContractEntity> contractList = item.getContracts();
                Iterator iterator = contractList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;

                    if (!isExpired(contract)) {//check unexpired contract
                        SupplierEntity supplier = contract.getSupplier();
                        supplierList.add(supplier);
                    }
                }
                return supplierList;
            }

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return supplierList;
    }

    //select a unexpired contract with given supplier and given raw material
    @Override
    public ContractEntity selectSupplier(String itemType, Long itemId, Long supplierId) throws Exception {
        System.out.println("selectSupplier():");

        try {
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity item = em.find(FactoryRawMaterialEntity.class, itemId);
                Collection<ContractEntity> contractList = item.getContracts();

                Iterator iterator = contractList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;

                    if (!isExpired(contract)
                            && (contract.getSupplier().getSupplierId().equals(supplierId))) {//check unexpired contract
                        return contract;
                    }
                }

            } else {// itemType.equals("RetailProduct")
                FactoryRetailProductEntity item = em.find(FactoryRetailProductEntity.class, itemId);
                Collection<ContractEntity> contractList = item.getContracts();

                Iterator iterator = contractList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;

                    if (!isExpired(contract)
                            && (contract.getSupplier().getSupplierId().equals(supplierId))) {//check unexpired contract
                        return contract;
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return null;

    }

    //4. Select delivery address (for retail products)
    //display all the available store
    @Override
    public List<StoreEntity> viewAvailStore(Long factoryId) throws Exception {
        System.out.println("viewAvailStore():");

        List<StoreEntity> storeList = new ArrayList<>();
        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            List<StoreProductEntity> storeProductList = factory.getStoreProduct();
            for(Object o : storeProductList){
                StoreProductEntity storeProduct = (StoreProductEntity) o;
                storeList.add(storeProduct.getStore());
            }

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return storeList;
    }

    //5. Select date and amount for goods receipt
    public List<DeliveryOrderEntity> getDeliveryAmount(Double purchaseAmount) throws Exception {
        System.out.println("getDeliveryAmount():");

        List<DeliveryOrderEntity> deliveryOrderList = new ArrayList<>();
        try {
            Calendar beginOfNextMonth = Calendar.getInstance();
            beginOfNextMonth.add(Calendar.MONTH, 1);
            beginOfNextMonth.set(Calendar.DAY_OF_MONTH, 1);

            //
            //
            //
            //
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return deliveryOrderList;
    }

    //6. Generate purchase order
    //Method 1 : by manually input the purcahse item related information (with the above functions)
    @Override
    public PurchaseOrderEntity createPurchaseOrder(Long factoryId, Long contractId, 
            Double purchaseAmount, Long storeId, String destination, 
            List<DeliveryOrderEntity> deliveryOrderList)
            throws Exception {
        System.out.println("createPurchaseOrder():");

        PurchaseOrderEntity purchaseOrder = null;

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            ContractEntity contract = em.find(ContractEntity.class, contractId);
            //status
            String status = "Unconfirmed";
            //unit
            String unit = contract.getUnit();
            //destination
            if (destination.equals("store")) {
                StoreEntity store = em.find(StoreEntity.class, storeId);
                destination = store.getAddress();
            } else {//destination is the factory that sends out the purchase order
                destination = factory.getAddress();
            }
            //createDate
            Calendar createDate = Calendar.getInstance();

            //totalPrice
            Double totalPrice = purchaseAmount * contract.getContractPrice();
            //leadTime
            Integer leadTime = contract.getLeadTime();

            purchaseOrder = new PurchaseOrderEntity(status, purchaseAmount, unit, createDate, destination, leadTime, totalPrice, factory, contract, deliveryOrderList);
            em.persist(purchaseOrder);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        em.flush();
        return purchaseOrder;

    }

    //Method 2 : by reference to an integrated planned order
    //Step 1: system display a list of available integrated planned order for RM and RP 
    @Override
    public List<IntegratedPlannedOrderEntity> viewAvailIntegratedPlannedOrder(Long factoryId) throws Exception {
        System.out.println("viewAvailIntegratedPlannedOrder():");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList<>();
        List<IntegratedPlannedOrderEntity> availIntegratedPlannedOrderList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            integratedPlannedOrderList = factory.getIntegratedPlannedOrders();
            Iterator iterator = integratedPlannedOrderList.iterator();

            while (iterator.hasNext()) {
                Object obj = iterator.next();
                IntegratedPlannedOrderEntity integratedPlannedOrder = (IntegratedPlannedOrderEntity) obj;
                if (!integratedPlannedOrder.getPurchaseOrder().isEmpty()) {
                    availIntegratedPlannedOrderList.add(integratedPlannedOrder);
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return availIntegratedPlannedOrderList;
    }

    //Step 2: user choose one of the integrated planned order(either RM or RP)
    //input : integratedPlannedOrderId
    //output: display a list of available suppliers for the choose RM or RP   
    @Override
    public List<SupplierEntity> viewAvailSupplier(Long factoryId, Long integratedPlannedOrderId, String itemType) throws Exception {
        System.out.println("viewAvailSupplier():");

        List<SupplierEntity> availSupplierList = new ArrayList<>();

        try {
            IntegratedPlannedOrderEntity integratedPlannedOrder = em.find(IntegratedPlannedOrderEntity.class, integratedPlannedOrderId);
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialAmountEntity factoryRawMaterialAmount = integratedPlannedOrder.getFactoryRawMaterialAmount();
                FactoryRawMaterialEntity factoryRawMaterial = factoryRawMaterialAmount.getFactoryRawMaterial();
                Collection<ContractEntity> contractList = factoryRawMaterial.getContracts();

                for (ContractEntity contract : contractList) {
                    if (!isExpired(contract)) {//check unexpired contract
                        SupplierEntity supplier = contract.getSupplier();
                        availSupplierList.add(supplier);
                    }
                }
            } else {//itemType.equals("RetailProduct")
                FactoryRetailProductAmountEntity factoryRetailProductAmount = integratedPlannedOrder.getFactoryRetailProductAmount();
                FactoryRetailProductEntity factoryRetailProduct = factoryRetailProductAmount.getFactoryRetailProduct();
                Collection<ContractEntity> contractList = factoryRetailProduct.getContracts();
                Iterator iterator = contractList.iterator();

                while (iterator.hasNext()) {
                    ContractEntity contract = (ContractEntity) iterator.next();

                    if (!isExpired(contract)) {//check unexpired contract
                        SupplierEntity supplier = contract.getSupplier();
                        availSupplierList.add(supplier);
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return availSupplierList;
    }

    //Step 3: user choose one of the suppliers displayed
    //input : supplierId, planned 1st of next month 's left inventory (means this month's left inventory)
    //output: display the generated amount for purchase
    @Override
    public Double generatePurchaseAmount(Long factoryId, Long integratedPlannedOrderId, 
            Long supplierId, Double nextMonthBeginPlannedAmount, String itemType) throws Exception {
        System.out.println("generatePurchaseAmount():");
        Double purchaseAmount = 0D;
        Double monthBeginInventory = -1D;
        Double plannedOrderAmount = 0D;
        try {
            IntegratedPlannedOrderEntity integratedPlannedOrder = em.find(IntegratedPlannedOrderEntity.class, integratedPlannedOrderId);
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialAmountEntity factoryRawMaterialAmount = integratedPlannedOrder.getFactoryRawMaterialAmount();
                FactoryRawMaterialEntity factoryRawMaterial = factoryRawMaterialAmount.getFactoryRawMaterial();
                List<InventoryRecordEntity> inventoryRecordList = factoryRawMaterial.getInventoryRecord();
                Iterator iterator = inventoryRecordList.iterator();

                //check the inventory level of the material in the beginning of the month
                while (iterator.hasNext() && (monthBeginInventory < 0D)) {
                    InventoryRecordEntity inventoryRecord = (InventoryRecordEntity) iterator.next();
                    Calendar firstDateOfMonth = Calendar.getInstance();   // this takes current date
                    firstDateOfMonth.set(Calendar.DAY_OF_MONTH, 1);
                    if (removeTime(inventoryRecord.getRecordDate()).equals(removeTime(firstDateOfMonth))) {
                        monthBeginInventory = inventoryRecord.getAmount();
                    }
                }

                plannedOrderAmount = integratedPlannedOrder.getFactoryRawMaterialAmount().getAmount();

            } else {//itemType.equals("RetailProduct")
                FactoryRetailProductAmountEntity factoryRetailProductAmount = integratedPlannedOrder.getFactoryRetailProductAmount();
                FactoryRetailProductEntity factoryRetailProduct = factoryRetailProductAmount.getFactoryRetailProduct();
                List<InventoryRecordEntity> inventoryRecordList = factoryRetailProduct.getInventoryRecords();
                Iterator iterator = inventoryRecordList.iterator();

                //check the inventory level of the material in the beginning of the month
                while (iterator.hasNext() && (monthBeginInventory < 0D)) {
                    InventoryRecordEntity inventoryRecord = (InventoryRecordEntity) iterator.next();
                    Calendar firstDateOfMonth = Calendar.getInstance();   // this takes current date
                    firstDateOfMonth.set(Calendar.DAY_OF_MONTH, 1);
                    if (removeTime(inventoryRecord.getRecordDate()).equals(removeTime(firstDateOfMonth))) {
                        monthBeginInventory = inventoryRecord.getAmount();
                    }
                }
                plannedOrderAmount = integratedPlannedOrder.getFactoryRetailProductAmount().getAmount();
            }

            purchaseAmount = plannedOrderAmount + (nextMonthBeginPlannedAmount - monthBeginInventory);
            System.out.println("Purchase Amount = " + purchaseAmount);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }

        return purchaseAmount;
    }

    //Step 4: user confirm the displayed amount 
    //output: a list of available address for delivery(only for RP), goto 4
    //Step 5: user select a address for delivery
    //output: purchase order
    @Override
    public PurchaseOrderEntity generatePurchaseOrder(Long factoryId, Long integratedPlannedOrderId, 
            Double purchaseAmount, Long supplierId, Long storeId, 
            String destination, String itemType) throws Exception {
        System.out.println("generatePurchaseOrder():");
        PurchaseOrderEntity purchaseOrder = new PurchaseOrderEntity();
        Long itemId;
        try {
            IntegratedPlannedOrderEntity integratedPlannedOrder = em.find(IntegratedPlannedOrderEntity.class, integratedPlannedOrderId);

            if (itemType.equals("RawMaterial")) {
                itemId = integratedPlannedOrder.getFactoryRawMaterialAmount().getFactoryRawMaterial().getFactoryRawMaterialId();
            } else {
                itemId = integratedPlannedOrder.getFactoryRetailProductAmount().getFactoryRetailProduct().getFactoryRetailProdctId();
            }
            
            ContractEntity contract = selectSupplier(itemType, itemId, supplierId);
            List<DeliveryOrderEntity> deliveryOrderList = getDeliveryAmount(purchaseAmount);
            purchaseOrder = createPurchaseOrder(factoryId, contract.getContractId(), purchaseAmount, storeId, destination, deliveryOrderList);

            purchaseOrder.setIntegratedPlannedOrder(integratedPlannedOrder);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }

        return purchaseOrder;

    }

    //6. Edit unconfirmed purchase order
    
    
    //7. Cancel purchase order
    //8. Generate Goods Receipt
    // for comparing two dates
    //function to set all the other attributes to be 0
    public Calendar removeTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    //check whether a contract has expired
    private boolean isExpired(ContractEntity contract) {
        boolean isExpired = true;

        Calendar contractEndDate = contract.getContractEndDate();
        Calendar today = Calendar.getInstance();

        if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {//if not expired
            isExpired = false;
        }
        return isExpired;
    }
}
