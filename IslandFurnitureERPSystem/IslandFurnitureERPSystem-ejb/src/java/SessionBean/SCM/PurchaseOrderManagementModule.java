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
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
import Entity.Store.StoreEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
    //3. View and Select Available Supplier(whose contract has not expired)
    @Override
    public Set<SupplierEntity> viewAvailSupplier(String itemType, Long itemId) throws Exception {
        Set<SupplierEntity> supplierList = new HashSet<>();

        try {
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity item = em.find(FactoryRawMaterialEntity.class, itemId);
                Collection<ContractEntity> contractList = item.getContracts();
                Iterator iterator = contractList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;

                    Calendar contractEndDate = contract.getContractEndDate();
                    Calendar today = Calendar.getInstance();

                    if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {//check unexpired contract
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

                    Calendar contractEndDate = contract.getContractEndDate();
                    Calendar today = Calendar.getInstance();

                    if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {//check unexpired contract
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
    public ContractEntity selectSupplier(String itemType, Long itemId, Long supplierId) throws Exception {
        try {
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity item = em.find(FactoryRawMaterialEntity.class, itemId);
                Collection<ContractEntity> contractList = item.getContracts();

                Iterator iterator = contractList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;

                    Calendar contractEndDate = contract.getContractEndDate();
                    Calendar today = Calendar.getInstance();

                    if ((removeTime(today).compareTo(removeTime(contractEndDate)) <= 0)
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

                    Calendar contractEndDate = contract.getContractEndDate();
                    Calendar today = Calendar.getInstance();

                    if ((removeTime(today).compareTo(removeTime(contractEndDate)) <= 0)
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
    public Set<StoreEntity> viewAvailStore(Long factoryId) throws Exception {
        Set<StoreEntity> storeList = new HashSet<>();
        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            storeList = factory.getStores();
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return storeList;
    }

    //5. Generate purchase order
    //by manually input the purcahse item related information
    @Override
    public PurchaseOrderEntity createPurchaseOrder(Long factoryId, Long contractId, Integer amount, Long storeId, String destination)
            throws Exception {
        PurchaseOrderEntity purchaseOrder = new PurchaseOrderEntity();

        try {
            em.flush();
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            ContractEntity contract = em.find(ContractEntity.class, contractId);
            String status = "Unconfirmed";
            String unit = contract.getUnit();
            if (destination.equals("store")) {
                StoreEntity store = em.find(StoreEntity.class, storeId);
                destination = store.getAddress();
            } else {//destination is the factory that sends out the purchase order
                destination = factory.getAddress();
            }
            Double total_price = amount * contract.getContractPrice();
            Integer leadTime = contract.getLeadTime();

            purchaseOrder.create(factory, contract, status, amount, unit, destination, total_price, leadTime);
            em.persist(purchaseOrder);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        em.flush();
        return purchaseOrder;

    }
    //by reference to selected planned order
    @Override
    public Set<RetailProductPlannedOrderEntity> viewAvailIntegratedPlannedOrder(Long factoryId) throws Exception {
        Set<PlannedOrderEntity> plannedOrderList = new HashSet<>();
        Set<PlannedOrderEntity> availPlannedOrderList = new HashSet<>();
        
        try{
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            plannedOrderList = factory.getPlannedOrder();
            Iterator iterator = plannedOrderList.iterator();
            
            while(iterator.hasNext()){
                Object obj = iterator.next();
                PlannedOrderEntity plannedOrder = (PlannedOrderEntity) obj;
                if(!plannedOrder.getPurchaseOrder().isEmpty())
                    availPlannedOrderList.add(plannedOrder);
            }
            
        }catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }       
        return plannedOrderList;
    }
    @Override
    public PurchaseOrderEntity genratePurchaseOrder(Set<PlannedOrderEntity> plannedOrderList) throws Exception {
        PurchaseOrderEntity purchaseOrder = new PurchaseOrderEntity();
        try{
            // check whether the raw materials/retail products in the planned orders are the same
            boolean isSameItem = checkPlannedOrders(plannedOrderList);
            
            
            FactoryEntity factory = p
            purchaseOrder.create(null, null, null, Integer.SIZE, null, null, Double.NaN, Integer.SIZE);
            
        }catch(Exception ex){
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

    private boolean checkPlannedOrders(Set<PlannedOrderEntity> plannedOrderList) {
        boolean isSameItem = true;
        Iterator iterator = plannedOrderList.iterator();
        
        if(iterator.hasNext()){
            Object obj = iterator.next();
            PlannedOrderEntity plannedOrder = (PlannedOrderEntity) obj;
            RawMaterialAmountEntity rawMaterial = plannedOrder.getRawMaterialList()
            
            
            
        }

        
        return isSameItem;
    }

    

    
}
