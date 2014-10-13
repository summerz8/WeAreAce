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
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.InventoryRecordEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.DeliveryOrderEntity;
import Entity.Factory.SCM.GoodsReceiptEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreRetailProductEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

    @Override
    public PurchaseOrderEntity getPO(Long poId) throws Exception {
        PurchaseOrderEntity po = em.find(PurchaseOrderEntity.class, poId);
        return po;
    }

    @Override
    public InventoryRecordEntity getIR(Calendar targetPeriod, String itemType, Long itemId) throws Exception {
        InventoryRecordEntity ir = null;

        if (itemType.equals("RawMaterial")) {
            FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, itemId);
            List<InventoryRecordEntity> inventoryRecordList = factoryRawMaterial.getInventoryRecord();
            Iterator iterator = inventoryRecordList.iterator();

            //check the inventory level of the material in the beginning of the month
            while (iterator.hasNext() && (ir == null)) {
                InventoryRecordEntity inventoryRecord = (InventoryRecordEntity) iterator.next();
                Calendar firstDateOfMonth = Calendar.getInstance();   // this takes current date
                firstDateOfMonth.set(Calendar.DAY_OF_MONTH, 1);
                if (removeTime(inventoryRecord.getRecordDate()).equals(removeTime(firstDateOfMonth))) {
                    ir = inventoryRecord;
                }
            }
        } else {//itemType.equals("RetailProduct")
            FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, itemId);
            List<InventoryRecordEntity> inventoryRecordList = factoryRetailProduct.getInventoryRecords();
            Iterator iterator = inventoryRecordList.iterator();

            //check the inventory level of the material in the beginning of the month
            while (iterator.hasNext() && (ir == null)) {
                InventoryRecordEntity inventoryRecord = (InventoryRecordEntity) iterator.next();
                Calendar firstDateOfMonth = Calendar.getInstance();   // this takes current date
                firstDateOfMonth.set(Calendar.DAY_OF_MONTH, 1);
                if (removeTime(inventoryRecord.getRecordDate()).equals(removeTime(firstDateOfMonth))) {
                    ir = inventoryRecord;
                }
            }
            System.out.println("Amount = " + ir.getAmount());

        }
        return ir;
    }

    @Override
    public UserEntity getUser(String userId) throws Exception {
        UserEntity user = em.find(UserEntity.class, userId);
        return user;
    }

    @Override
    public FactoryEntity getFactoryEntity(Long factoryId) throws Exception {
        FactoryEntity factory = null;

        try {
            factory = em.find(FactoryEntity.class, factoryId);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return factory;
    }

    @Override
    public StoreEntity getStoreEntity(Long storeId) throws Exception {
        StoreEntity store = null;

        try {
            store = em.find(StoreEntity.class, storeId);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return store;
    }

    //select an item for purchase
    @Override
    public FactoryRawMaterialEntity getFactoryRM(Long itemId) throws Exception {
        System.out.println("getFactoryRM():");
        FactoryRawMaterialEntity frm = null;
        try {
            frm = em.find(FactoryRawMaterialEntity.class, itemId);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return frm;
    }

    @Override
    public FactoryRetailProductEntity getFactoryRP(Long itemId) throws Exception {
        System.out.println("getFactoryRP():");
        FactoryRetailProductEntity frp = null;
        try {
            frp = em.find(FactoryRetailProductEntity.class, itemId);

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return frp;
    }

    @Override
    public ContractEntity getContract(Long contractId) throws Exception {
        System.out.println("getContract():");
        ContractEntity contract = null;
        try {
            contract = em.find(ContractEntity.class, contractId);

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return contract;

    }

    @Override
    public ContractEntity getContract2(Long supplierId, Long itemId, String itemType) throws Exception {
        ContractEntity contract = null;
        if (itemType.equals("RawMaterial")) {
            FactoryRawMaterialEntity frm = em.find(FactoryRawMaterialEntity.class, itemId);
            Collection<ContractEntity> contractList = frm.getContracts();
            for (ContractEntity c : contractList) {
                if ((c.getSupplier() != null)
                        && c.getSupplier().getSupplierId().equals(supplierId)) {
                    contract = c;
                }
            }
        } else {
            FactoryRetailProductEntity frp = em.find(FactoryRetailProductEntity.class, itemId);
            Collection<ContractEntity> contractList = frp.getContracts();
            for(ContractEntity c : contractList){
                SupplierEntity supplier = c.getSupplier();
                if (supplier.getSupplierId().equals(supplierId)) {
                    contract = c;
                }
            }
        }
        return contract;
    }

    //1. View an item for purcahse 
    //RawMaterials
    @Override
    public Collection<FactoryRawMaterialEntity> viewRawMaterialWithSelectType(Long factoryId) throws Exception {//test works!!
        System.out.println("Session Bean: viewRawMaterialWithSelectType():");
        Collection<FactoryRawMaterialEntity> frmList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            Collection<FactoryRawMaterialEntity> factoryRawMaterialList = factory.getFactoryRawMaterials();
            for (FactoryRawMaterialEntity frm : factoryRawMaterialList) {
                if (!frm.getIsDeleted()) {
                    frmList.add(frm);
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return frmList;
    }

    //RetailProduct
    @Override
    public Collection<FactoryRetailProductEntity> viewRetailProductWithSelectType(Long factoryId) throws Exception {//test works!!
        System.out.println("viewRetailProductWithSelectType():");
        Collection<FactoryRetailProductEntity> frpList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            Collection<FactoryRetailProductEntity> factoryRetailProductList = factory.getFactoryRetailProducts();
            for (FactoryRetailProductEntity frp : factoryRetailProductList) {
                if (!frp.getIsDeleted()) {
                    frpList.add(frp);
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return frpList;
    }

    //2. Generate purchase amount
    //See 5, Method 2, Step 3 * 
    //3. View and Select Available Supplier(whose contract has not expired)
    //input factoryItemId and itemType
    @Override
    public Collection<SupplierEntity> viewSupplierForItem(String itemType, Long itemId) throws Exception {
        System.out.println("SessionBean: viewSupplierForItem():");

        Collection<SupplierEntity> supplierList = new HashSet<>();

        try {
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity frm = em.find(FactoryRawMaterialEntity.class, itemId);
                Collection<ContractEntity> contractList = frm.getContracts();
                Iterator iterator = contractList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;
                    SupplierEntity supplier = contract.getSupplier();

                    //check unexpired contract and the supplier has not bean added previous
                    if (!isExpired(contract) && !supplierList.contains(supplier)) {
                        supplierList.add(supplier);
                    }
                }
            } else {// itemType.equals("RetailProduct")
                FactoryRetailProductEntity frp = em.find(FactoryRetailProductEntity.class, itemId);
                Collection<ContractEntity> contractList = frp.getContracts();
                Iterator iterator = contractList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;
                    SupplierEntity supplier = contract.getSupplier();

                    //check unexpired contract and the supplier has not bean added previous
                    if (!isExpired(contract) && !supplierList.contains(supplier)) {
                        supplierList.add(supplier);
                    }
                }
            }

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return supplierList;
    }

    @Override
    public Collection<SupplierEntity> viewSupplierForFactory(Long factoryId) throws Exception {
        System.out.println("viewAvailSupplier():");
        Collection<SupplierEntity> availSupplierList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            // add suppleirs for raw materials
            Collection<FactoryRawMaterialEntity> factoryRawMaterialList = factory.getFactoryRawMaterials();
            for (FactoryRawMaterialEntity frm : factoryRawMaterialList) {
                Collection<ContractEntity> contractList = frm.getContracts();
                for (ContractEntity contract : contractList) {
                    SupplierEntity supplier = contract.getSupplier();

                    //exclude the duplicated and the deleted suppleirs
                    if (!availSupplierList.contains(supplier) && !supplier.isDeleted()) {
                        availSupplierList.add(supplier);
                    }
                }
            }
            //add the suppliers for retail product, some maybe the same as the raw materials
            Collection<FactoryRetailProductEntity> factoryRetailProductList = factory.getFactoryRetailProducts();
            for (FactoryRetailProductEntity frp : factoryRetailProductList) {
                Collection<ContractEntity> contractList = frp.getContracts();
                for (ContractEntity contract : contractList) {
                    SupplierEntity supplier = contract.getSupplier();
                    //exclude the duplicated and the deleted supplier 
                    if (!availSupplierList.contains(supplier) && !supplier.isDeleted()) {
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

    //select a unexpired contract with given supplier and given raw material
    @Override
    public ContractEntity selectSupplier(String itemType, Long itemId, Long supplierId) throws Exception {
        System.out.println("selectSupplier():");

        try {
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity frm = em.find(FactoryRawMaterialEntity.class, itemId);
                Collection<ContractEntity> contractList = frm.getContracts();

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
                FactoryRetailProductEntity frp = em.find(FactoryRetailProductEntity.class, itemId);
                Collection<ContractEntity> contractList = frp.getContracts();

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
            List<StoreRetailProductEntity> storeRetailProductList = factory.getStoreRetailProduct();
            for (Object o : storeRetailProductList) {
                StoreRetailProductEntity storeRetailProduct = (StoreRetailProductEntity) o;
                StoreEntity store = storeRetailProduct.getStore();
                if (!storeList.contains(store)) {
                    storeList.add(store);
                }
            }

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return storeList;
    }

    @Override
    public Collection<StoreEntity> viewAvailStoreForRetailProduct(Long factoryId, Long frpId) throws Exception {
        List<StoreEntity> storeList = new ArrayList<>();
        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, frpId);
            List<StoreRetailProductEntity> storeRetailProductList = factory.getStoreRetailProduct();
            for (Object o : storeRetailProductList) {
                StoreRetailProductEntity storeRetailProduct = (StoreRetailProductEntity) o;
                if (storeRetailProduct.getRetailProduct().getRetailProductId()
                        .equals(factoryRetailProduct.getRetailProduct().getRetailProductId())) {
                    StoreEntity store = storeRetailProduct.getStore();
                    if (!storeList.contains(store)) {
                        storeList.add(store);
                    }
                }
            }

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return storeList;
    }

    //5. Select date and amount for goods receipt
    @Override
    public Collection<DeliveryOrderEntity> getDeliveryAmountAndDate(Long integratedPlannedOrderId, Double purchaseAmount) throws Exception {
        System.out.println("SessionBean: getDeliveryAmount():");

        Collection<DeliveryOrderEntity> deliveryOrderList = new ArrayList<>();
        try {
            System.out.println("Session Bean: getDeliveryAmount:()  1");
            IntegratedPlannedOrderEntity integratedPlannedOrder = em.find(IntegratedPlannedOrderEntity.class, integratedPlannedOrderId);
            Calendar period = integratedPlannedOrder.getTargetPeriod();
            Double amount = purchaseAmount;

            Calendar cal1 = Calendar.getInstance();
            cal1.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH), 1, 0, 0, 0);
            Calendar cal2 = Calendar.getInstance();
            cal2.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
            Integer daysInMonth = 0;
            System.out.println("getDeliveryAmount:()  2");
            do {
                if (cal1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    ++daysInMonth;
                }
                cal1.add(Calendar.DAY_OF_MONTH, 1);

                System.out.println("getDeliveryAmount:()  3");
            } while (cal1.getTimeInMillis() < cal2.getTimeInMillis());

            Calendar cal3 = Calendar.getInstance();
            cal3.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH), 1, 0, 0, 0);
            Calendar cal4 = Calendar.getInstance();
            cal4.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
            Integer week = 0;
            if (cal3.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                week = -1;
            }
            System.out.println("getDeliveryAmount:()  4");
            do {
                if (cal3.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || (cal3.getTimeInMillis() == cal4.getTimeInMillis() && cal3.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
                    week++;
                }
                cal3.add(Calendar.DAY_OF_MONTH, 1);
            } while (cal3.getTimeInMillis() <= cal4.getTimeInMillis());
            int workingDayInWeek = 1;
            Double weeklyDemand;
            Calendar cal5 = Calendar.getInstance();
            cal5.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH), 1, 0, 0, 0);
            Calendar cal6 = Calendar.getInstance();
            cal6.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
            cal6.add(Calendar.DAY_OF_MONTH, -1);
            System.out.println("getDeliveryAmount:()  5");
            for (int a = 0; a < week; a++) {
                if (a == 0) {
                    if (cal5.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                        workingDayInWeek = 5;
                    } else if (cal5.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                        workingDayInWeek = 4;
                    } else if (cal5.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                        workingDayInWeek = 3;
                    } else if (cal5.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                        workingDayInWeek = 2;
                    } else if (cal5.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                        workingDayInWeek = 1;
                    }
                    weeklyDemand = amount / daysInMonth * workingDayInWeek;
                    weeklyDemand = (double) Math.round(weeklyDemand);
                    DeliveryOrderEntity deliveryOrder = new DeliveryOrderEntity();

                    deliveryOrder.setDeliveryDate(cal5.getTime());

                    deliveryOrder.setAmount(weeklyDemand);
                    em.persist(deliveryOrder);
                    em.flush();
                    deliveryOrderList.add(deliveryOrder);

                    cal5.add(Calendar.DAY_OF_MONTH, workingDayInWeek + 2);
                } else if (a == 1) {
                    workingDayInWeek = 5;
                    weeklyDemand = amount / daysInMonth * workingDayInWeek;
                    weeklyDemand = (double) Math.round(weeklyDemand);
                    DeliveryOrderEntity deliveryOrder = new DeliveryOrderEntity();
                    deliveryOrder.setDeliveryDate(cal5.getTime());
                    deliveryOrder.setAmount(weeklyDemand);
                    em.persist(deliveryOrder);
                    em.flush();
                    deliveryOrderList.add(deliveryOrder);
                    cal5.add(Calendar.DAY_OF_MONTH, workingDayInWeek + 2);

                } else if (a == 2) {
                    workingDayInWeek = 5;
                    DeliveryOrderEntity deliveryOrder = new DeliveryOrderEntity();
                    deliveryOrder.setDeliveryDate(cal5.getTime());
                    weeklyDemand = amount / daysInMonth * workingDayInWeek;
                    weeklyDemand = (double) Math.round(weeklyDemand);
                    deliveryOrder.setAmount(weeklyDemand);
                    em.persist(deliveryOrder);
                    em.flush();
                    deliveryOrderList.add(deliveryOrder);

                    cal5.add(Calendar.DAY_OF_MONTH, workingDayInWeek + 2);

                } else if (a == 3 && week == 4) {
                    if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                        workingDayInWeek = 1;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                        workingDayInWeek = 2;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                        workingDayInWeek = 3;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                        workingDayInWeek = 4;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                        workingDayInWeek = 5;
                    }
                    weeklyDemand = amount / daysInMonth * workingDayInWeek;
                    weeklyDemand = (double) Math.round(weeklyDemand);
                    DeliveryOrderEntity deliveryOrder = new DeliveryOrderEntity();
                    deliveryOrder.setDeliveryDate(cal5.getTime());

                    deliveryOrder.setAmount(weeklyDemand);
                    em.persist(deliveryOrder);
                    em.flush();
                    deliveryOrderList.add(deliveryOrder);

                    cal5.add(Calendar.DAY_OF_MONTH, workingDayInWeek + 2);

                } else if (a == 3 && week == 5) {
                    workingDayInWeek = 5;
                    weeklyDemand = amount / daysInMonth * workingDayInWeek;
                    weeklyDemand = (double) Math.round(weeklyDemand);
                    DeliveryOrderEntity deliveryOrder = new DeliveryOrderEntity();
                    deliveryOrder.setDeliveryDate(cal5.getTime());

                    deliveryOrder.setAmount(weeklyDemand);
                    em.persist(deliveryOrder);
                    em.flush();
                    deliveryOrderList.add(deliveryOrder);

                    cal5.add(Calendar.DAY_OF_MONTH, workingDayInWeek + 2);

                } else if (a == 4 && week == 5) {
                    if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                        workingDayInWeek = 1;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                        workingDayInWeek = 2;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                        workingDayInWeek = 3;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                        workingDayInWeek = 4;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                        workingDayInWeek = 5;
                    }
                    weeklyDemand = amount / daysInMonth * workingDayInWeek;
                    weeklyDemand = (double) Math.round(weeklyDemand);
                    DeliveryOrderEntity deliveryOrder = new DeliveryOrderEntity();
                    deliveryOrder.setDeliveryDate(cal5.getTime());

                    deliveryOrder.setAmount(weeklyDemand);
                    em.persist(deliveryOrder);
                    em.flush();
                    deliveryOrderList.add(deliveryOrder);

                    cal5.add(Calendar.DAY_OF_MONTH, workingDayInWeek + 2);

                }
            }
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
            Double purchaseAmount, Long storeId, String destination, Calendar deliveryDate, Boolean isManual)
            throws Exception {
        System.out.println("createPurchaseOrder():");

        PurchaseOrderEntity purchaseOrder = null;

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            ContractEntity contract = em.find(ContractEntity.class, contractId);
            //status
            String status = "unconfirmed";
            //unit
            String unit = contract.getUnit();

            //destination and destinationId
            Long destinationId = null;
            String itemName = null;

            if (destination.equals("store")) {
                StoreEntity store = em.find(StoreEntity.class, storeId);
                destination = store.getAddress();
                destinationId = store.getStoreId();
            } else {//destination is the factory that sends out the purchase order
                destination = factory.getAddress();
                destinationId = factory.getFactoryId();
            }
            //createDate
            Calendar createDate = Calendar.getInstance();

            //totalPrice
            Double totalPrice = (purchaseAmount / contract.getLotSize()) * contract.getContractPrice();
            //leadTime
            Integer leadTime = contract.getLeadTime();
            if (contract.getFactoryRawMaterial() != null) {
                itemName = contract.getFactoryRawMaterial().getMaterialName();
            } else {
                itemName = contract.getFactoryRetailProduct().getName();
            }
            purchaseOrder = new PurchaseOrderEntity(itemName, status, purchaseAmount, unit, createDate, destination,
                    destinationId, leadTime, totalPrice, factory, contract, deliveryDate, isManual);

            em.persist(purchaseOrder);

            factory.getPurchaseOrders().add(purchaseOrder);

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
    public List<IntegratedPlannedOrderEntity> viewWaitingIntegratedPlannedOrder(Long factoryId) throws Exception {
        System.out.println("viewWaitingIntegratedPlannedOrder():");

        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList<>();
        List<IntegratedPlannedOrderEntity> waitingIntegratedPlannedOrderList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            System.out.println("Factory = " + factory.toString());
            integratedPlannedOrderList = factory.getIntegratedPlannedOrders();
            for (IntegratedPlannedOrderEntity ipo : integratedPlannedOrderList) {
                //check whether this intergrated planned order is in waiting status
                if (ipo.getStatus().equals("waiting") && (ipo.getPurchaseOrder() == null)) {
                    System.out.println(ipo.toString());
                    waitingIntegratedPlannedOrderList.add(ipo);
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return waitingIntegratedPlannedOrderList;
    }

    @Override
    public List<IntegratedPlannedOrderEntity> viewCancelledIntegratedPlannedOrder(Long factoryId) throws Exception {
        System.out.println("viewCancelledIntegratedPlannedOrder():");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList<>();
        List<IntegratedPlannedOrderEntity> waitingIntegratedPlannedOrderList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            integratedPlannedOrderList = factory.getIntegratedPlannedOrders();
            Iterator iterator = integratedPlannedOrderList.iterator();

            while (iterator.hasNext()) {
                Object obj = iterator.next();
                IntegratedPlannedOrderEntity integratedPlannedOrder = (IntegratedPlannedOrderEntity) obj;

                //check whether this intergrated planned order is in waiting status
                if (integratedPlannedOrder.getStatus().equals("cancelled")) {
                    waitingIntegratedPlannedOrderList.add(integratedPlannedOrder);
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return waitingIntegratedPlannedOrderList;
    }

    //Step 2: user choose one of the integrated planned order(either RM or RP)
    //input : integratedPlannedOrderId
    //output: display a list of available suppliers for the choose RM or RP   
    @Override
    public List<SupplierEntity> viewAvailSupplier(Long factoryId, Long integratedPlannedOrderId,
            String itemType) throws Exception {
        System.out.println("viewAvailSupplier():");

        List<SupplierEntity> availSupplierList = new ArrayList<>();

        try {
            IntegratedPlannedOrderEntity integratedPlannedOrder = em.find(IntegratedPlannedOrderEntity.class, integratedPlannedOrderId);
            if (itemType.equals(
                    "RawMaterial")) {
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
                for (ContractEntity contract : contractList) {
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
    public Double generatePurchaseAmount(Long integratedPlannedOrderId,
            Double nextMonthBeginPlannedAmount, String itemType, Double lotSize) throws Exception {
        System.out.println("generatePurchaseAmount():");
        Double originalAmount = 0D;
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

                System.out.println("monthBeginInventory = " + monthBeginInventory);
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
                System.out.println("monthBeginInventory = " + monthBeginInventory);

                plannedOrderAmount = integratedPlannedOrder.getFactoryRetailProductAmount().getAmount();
            }
            originalAmount = plannedOrderAmount + (nextMonthBeginPlannedAmount - monthBeginInventory);
            if (originalAmount < 0) {
                purchaseAmount = 0.0;
            } else if ((originalAmount % lotSize) == 0) {
                purchaseAmount = originalAmount;
            } else {
                Double temp = originalAmount / lotSize;
                System.out.println("SB: Temp = " + temp);
                System.out.println("Math.ceil(temp) = " + Math.ceil(temp));
                purchaseAmount = Math.ceil(temp) * lotSize;
            }

            System.out.println(
                    "Purchase Amount = " + purchaseAmount);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }

        return purchaseAmount;
    }

    @Override
    public Double generateOriginalAmount(Long integratedPlannedOrderId, Double nextMonthBeginPlannedAmount, String itemType) throws Exception {
        System.out.println("generatePurchaseAmount():");
        Double originalAmount = 0D;
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

                    //to record the user input planned amount 
                    firstDateOfMonth.add(Calendar.MONTH, 1); //represent the beginning of next month
                    if (removeTime(inventoryRecord.getRecordDate()).equals(removeTime(firstDateOfMonth))) {//there is no record for next month planned begin amount
                        inventoryRecord.setAmount(nextMonthBeginPlannedAmount);
                        em.flush();
                    } else if (!iterator.hasNext()) {//there is a record for next month planned begin amount
                        InventoryRecordEntity ir = new InventoryRecordEntity();
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.DAY_OF_MONTH, 1);
                        cal.add(Calendar.MONTH, 1);
                        ir.setRecordDate(cal);
                        ir.setAmount(nextMonthBeginPlannedAmount);
                        ir.setFactoryRawMaterial(factoryRawMaterial);
                        factoryRawMaterial.getInventoryRecord().add(ir);
                        em.persist(ir);
                        em.flush();
                    }
                }

                System.out.println("monthBeginInventory = " + monthBeginInventory);
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

                    //to record the user input planned amount 
                    firstDateOfMonth.add(Calendar.MONTH, 1); //represent the beginning of next month
                    if (removeTime(inventoryRecord.getRecordDate()).equals(removeTime(firstDateOfMonth))) {//there is no record for next month planned begin amount
                        inventoryRecord.setAmount(nextMonthBeginPlannedAmount);
                        em.flush();
                    } else if (!iterator.hasNext()) {//there is a record for next month planned begin amount
                        InventoryRecordEntity ir = new InventoryRecordEntity();
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.DAY_OF_MONTH, 1);
                        cal.add(Calendar.MONTH, 1);
                        ir.setRecordDate(cal);
                        ir.setAmount(nextMonthBeginPlannedAmount);
                        ir.setFactoryRetailProduct(factoryRetailProduct);
                        factoryRetailProduct.getInventoryRecords().add(ir);
                        em.persist(ir);
                        em.flush();
                    }

                }
                System.out.println("monthBeginInventory = " + monthBeginInventory);

                plannedOrderAmount = integratedPlannedOrder.getFactoryRetailProductAmount().getAmount();
            }
            originalAmount = plannedOrderAmount + (nextMonthBeginPlannedAmount - monthBeginInventory);

            System.out.println(
                    "Original Amount = " + originalAmount);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }

        return originalAmount;

    }

    //Step 4: user confirm the displayed amount 
    //output: a list of available address for delivery(only for RP), goto 4
    //Step 5: user select a address for delivery
    //output: purchase order
    @Override
    public PurchaseOrderEntity generatePurchaseOrder(Long factoryId, Long integratedPlannedOrderId,
            Double plannedAmount, Double nextMonthBeginPlannedAmount, Long contractId, Long storeId,
            String destination, String itemType) throws Exception {
        System.out.println("generatePurchaseOrder():");
        PurchaseOrderEntity purchaseOrder = new PurchaseOrderEntity();
        Long itemId;

        try {
            IntegratedPlannedOrderEntity integratedPlannedOrder = em.find(IntegratedPlannedOrderEntity.class, integratedPlannedOrderId);
            ContractEntity contract = em.find(ContractEntity.class, contractId);
            if (itemType.equals("RawMaterial")) {
                itemId = integratedPlannedOrder.getFactoryRawMaterialAmount().getFactoryRawMaterial().getFactoryRawMaterialId();
            } else {
                itemId = integratedPlannedOrder.getFactoryRetailProductAmount().getFactoryRetailProduct().getFactoryRetailProdctId();
            }

            Double originalAmount = generatePurchaseAmount(integratedPlannedOrderId,
                    nextMonthBeginPlannedAmount, itemType, contract.getLotSize());
            Double purchaseAmount;

            if ((originalAmount % contract.getLotSize()) == 0) {
                purchaseAmount = originalAmount;
            } else {
                Double temp = originalAmount / contract.getLotSize();
                System.out.println("SB: Temp = " + temp);
                System.out.println("Math.ceil(temp) = " + Math.ceil(temp));
                purchaseAmount = Math.ceil(temp) * contract.getLotSize();
            }

            Collection<DeliveryOrderEntity> deliveryOrderList = getDeliveryAmountAndDate(integratedPlannedOrderId, purchaseAmount);

            purchaseOrder = createPurchaseOrder(factoryId, contractId, purchaseAmount, storeId, destination, null, false);

            //set relationship between delivery orders and purchase order
            for (DeliveryOrderEntity deliveryOrder : deliveryOrderList) {
                deliveryOrder.setPurchaseOrder(purchaseOrder);
            }

            purchaseOrder.setIntegratedPlannedOrder(integratedPlannedOrder);
            integratedPlannedOrder.setPurchaseOrder(purchaseOrder);
            purchaseOrder.setDeliveryOrderList(deliveryOrderList);

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        em.flush();
        return purchaseOrder;

    }

    @Override
    public Collection<PurchaseOrderEntity> viewUnconfirmedPurchaseOrder(Long factoryId) throws Exception {
        System.out.println("viewUnconfirmedPurchaseOrder():");
        Collection<PurchaseOrderEntity> purchaseOrderList = new ArrayList<>();
        Collection<PurchaseOrderEntity> unconfirmedPurchaseOrderList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            System.out.println("factoryId = " + factoryId);
            purchaseOrderList = factory.getPurchaseOrders();
            for (PurchaseOrderEntity po : purchaseOrderList) {
                //get the unconfirmed purchase order
                if (po.getStatus().equals("unconfirmed")) {
                    System.out.println("SB: Purchase Order = " + po.toString());
                    unconfirmedPurchaseOrderList.add(po);
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return unconfirmedPurchaseOrderList;
    }

    @Override
    public Collection<PurchaseOrderEntity> viewConfirmedPurchaseOrder(Long factoryId) throws Exception {

        System.out.println("viewConfirmedPurchaseOrder():");
        Collection<PurchaseOrderEntity> purchaseOrderList = new ArrayList<>();
        Collection<PurchaseOrderEntity> confirmedPurchaseOrderList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            purchaseOrderList = factory.getPurchaseOrders();
            for (PurchaseOrderEntity po : purchaseOrderList) {
                //check whether this intergrated planned order is in waiting status
                if (po.getStatus().equals("confirmed")) {
                    confirmedPurchaseOrderList.add(po);
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return confirmedPurchaseOrderList;
    }

    //6. Edit unconfirmed purchase order
    @Override
    public PurchaseOrderEntity editPurchaseOrder(Long purchaseOrderId, String status, Double totalAmount,
            String unit, Calendar createDate, String destination, Long destinationId, Integer leadTime,
            Double totalPrice, FactoryEntity factory, IntegratedPlannedOrderEntity integratedPlannedOrder,
            ContractEntity contract) throws Exception {
        System.out.println("editPurchaseOrder():");
        PurchaseOrderEntity purchaseOrder = null;

        try {
            purchaseOrder = em.find(PurchaseOrderEntity.class, purchaseOrderId);

            purchaseOrder.setStatus(status);

            purchaseOrder.setTotalAmount(totalAmount);

            purchaseOrder.setUnit(unit);

            purchaseOrder.setCreateDate(createDate);

            purchaseOrder.setDestination(destination);

            purchaseOrder.setDestinationId(destinationId);

            purchaseOrder.setLeadTime(leadTime);

            purchaseOrder.setTotalPrice(totalPrice);

            purchaseOrder.setIntegratedPlannedOrder(integratedPlannedOrder);

            purchaseOrder.setContract(contract);

            em.flush();
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return purchaseOrder;
    }

    //8. Confirm purchase order
    //only be factory manager
    @Override
    public String confirmPurchaseOrder(String userId, Long purchaseOrderId) throws Exception {
        System.out.println("confirmPurchaseOrder");
        String result = null;
        try {
            UserEntity user = em.find(UserEntity.class, userId);
            if (user.getUserLevel() != 1) {
                result = "\"Confirm Purchase Order\" Permission Denied.";
                System.out.println(result);
                return result;
            }

            PurchaseOrderEntity purchaseOrder = em.find(PurchaseOrderEntity.class, purchaseOrderId);

            purchaseOrder.setStatus("confirmed");

            if (purchaseOrder.getIntegratedPlannedOrder() != null) {
                purchaseOrder.getIntegratedPlannedOrder().setStatus("processing");
            }

            em.flush();

            result = "Purchase Order Confirmed!";

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        System.out.println("Result = " + result);
        return result;
    }

    @Override
    public String cancelPurchaseOrder(String userId, Long purchaseOrderId) throws Exception {
        System.out.println("cancelPurchaseOrder");
        String result = null;

        try {
            UserEntity user = em.find(UserEntity.class, userId);
            if (user.getUserLevel() != 1) {
                result = "\"Confirm Purchase Order\" Permission Denied.";
                return result;
            }

            PurchaseOrderEntity purchaseOrder = em.find(PurchaseOrderEntity.class, purchaseOrderId);

            purchaseOrder.setStatus("cancelled");
            if (purchaseOrder.getIntegratedPlannedOrder() != null) {
                purchaseOrder.getIntegratedPlannedOrder().setStatus("waiting");
                purchaseOrder.getIntegratedPlannedOrder().setPurchaseOrder(null);
            }
            em.flush();

            result = "Purchase Order Cancelled!";

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return result;
    }

    //9. Generate Goods Receipt
    //for manually generated po
    @Override
    public String generateGoodsRecipt(Long purchaseOrderId) throws Exception {
        System.out.println("generateGoodsRecipt():");
        String result = null;
        PurchaseOrderEntity po = null;
        GoodsReceiptEntity gr = null;

        gr = new GoodsReceiptEntity();
        po = em.find(PurchaseOrderEntity.class, purchaseOrderId);
        gr.setCreateDate(Calendar.getInstance());
        gr.setPurchaseOrder(po);
        gr.setOriginalAmount(po.getTotalAmount());
        gr.setAmount(po.getTotalAmount());

        em.persist(gr);
        po.getGoodsReceiptList().add(gr);

        po.setStatus("accomplished");
        em.flush();

        result = "Purchase Order [id = " + po.getId()
                + "] is fulfilled with goods receipt [id = " + gr.getGoodsReceiptId() + " ] ";
        return result;
    }

    @Override
    public String generateGoodsReciptForDeliveryOrders(Long purchaseOrderId, Long deliveryOrderId) throws Exception {
        System.out.println("generateGoodsRecipt():");
        String result = null;

        GoodsReceiptEntity gr = new GoodsReceiptEntity();
        PurchaseOrderEntity po = em.find(PurchaseOrderEntity.class, purchaseOrderId);
        gr.setCreateDate(Calendar.getInstance());
        gr.setPurchaseOrder(po);
        System.out.println("Check 1");
        em.persist(gr);
        DeliveryOrderEntity deliveryOrder = em.find(DeliveryOrderEntity.class, deliveryOrderId);
        deliveryOrder.setStatus("fulfilled");
        po.getGoodsReceiptList().add(gr);
        gr.setOriginalAmount(deliveryOrder.getAmount());
        gr.setAmount(deliveryOrder.getAmount());
        em.flush();
        
        System.out.println("check 2");
        for (DeliveryOrderEntity delivery : po.getDeliveryOrderList()) {
            System.out.println("CHECK 3 = " + delivery.toString());
            if (!delivery.getStatus().equals("fulfilled")) {
               System.out.println("CHECK 4 = " + delivery.toString());
                result = "Delivery order [id = " + deliveryOrder.getId()
                        + "] is fullfilled with goods receipt [id = " + gr.getGoodsReceiptId() + " ] ";
                System.out.println(result);
                return result;
            }
        }
        po.setStatus("accomplished");
        po.getIntegratedPlannedOrder().setStatus("accomplished");
        em.flush();
        result = "Purchase Order [id = " + po.getId() + "] is fulfilled with goods receipt [id = " + gr.getGoodsReceiptId() + " ] ";
        System.out.println(result);

        return result;
    }
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
