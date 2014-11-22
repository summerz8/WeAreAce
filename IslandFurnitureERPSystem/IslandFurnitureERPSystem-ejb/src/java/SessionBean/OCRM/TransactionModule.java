/*
 * To moneyChange this license header, choose License Headers in Project Properties.
 * To moneyChange this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.CommonInfrastructure.StoreUserEntity;
import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.PickupListEntity;
import Entity.Store.OCRM.SalesRecordEntity;
import Entity.Store.OCRM.TransactionEntity;
import Entity.Store.OCRM.TransactionItemEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreItemMappingEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import Entity.Store.StoreSetEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hangsun
 */
@Stateless
@WebService
public class TransactionModule implements TransactionModuleLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;

    @Override
    @WebMethod(operationName = "createTransaction")
    public Long createNewTransaction(
            @WebParam(name = "staffId") String staffId,
            @WebParam(name = "memberId") Long memberId,
            @WebParam(name = "location") int location,
            @WebParam(name = "POSid") String POSid) {

        Calendar generatedTime = Calendar.getInstance();
        StoreUserEntity storeStaff = em.find(StoreUserEntity.class, staffId);

        StoreEntity store = em.find(StoreEntity.class, storeStaff.getDepartmentId());
        TransactionEntity transaction = new TransactionEntity();

        transaction.setGenerateTime(generatedTime);
        transaction.setStore(store);
        transaction.setStoreStaff(storeStaff);
        if (memberId != null) {
            MemberEntity member = em.find(MemberEntity.class, memberId);
            transaction.setMember(member);
        }
        transaction.setLocation(location);
        transaction.setPOSid(POSid);
        em.persist(transaction);
        em.flush();

        if (memberId != null) {
            MemberEntity member = em.find(MemberEntity.class, memberId);
            member.getTransactionList().add(transaction);
            em.persist(member);
            em.flush();
        }

        return transaction.getTransactionId();

    }

    @Override
    @WebMethod(operationName = "createTransactionItem")
    public void createTransactionItem(
            @WebParam(name = "itemId") Long itemId,
            @WebParam(name = "amount") int amount,
            @WebParam(name = "transactionId") Long transactionId) {
        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);
        List<TransactionItemEntity> transactionItemList = transaction.getTransactionItemList();
        Boolean flag = Boolean.TRUE;

        if (transactionItemList != null && transactionItemList.size() > 0) {
            for (TransactionItemEntity ti : transactionItemList) {
                if (ti.getItemId().equals(itemId)) {
                    ti.setAmount(ti.getAmount() + amount);
                    ti.setTotalPrice(ti.getTotalPrice() + ti.getUnitPrice() * amount);
                    em.persist(ti);
                    em.flush();
                    flag = Boolean.FALSE;
                    break;
                }
            }
        }

        if (flag) {
            TransactionItemEntity transactionItem = new TransactionItemEntity();
            StoreItemMappingEntity item = em.find(StoreItemMappingEntity.class, itemId);

            if (transaction.getLocation() == 1) {
//<<<<<<< HEAD
                if (item.getProductId() != null) {
                    StoreProductEntity storeProduct = em.find(StoreProductEntity.class, item.getProductId());
                    String itemName = storeProduct.getProduct().getName();
                    Double unitPrice = storeProduct.getProduct().getPrice();
                    Double totalPrice = unitPrice * amount;
                    transactionItem.setItemName(itemName);
                    transactionItem.setTotalPrice(totalPrice);
                    transactionItem.setUnitPrice(unitPrice);

                    Double unitMemberPrice = storeProduct.getProduct().getMemberPrice();
                    Double totalMemberPrice = unitMemberPrice * amount;
                    transactionItem.setUnitMemberPrice(unitMemberPrice);
                    transactionItem.setTotalMemberPrice(totalMemberPrice);
                } else if (item.getStoreSetId() != null) {
                    StoreSetEntity storeSet = em.find(StoreSetEntity.class, item.getStoreSetId());
                    String itemName = storeSet.getName();
                    Double unitPrice = storeSet.getSet().getPrice();
                    Double totalPrice = unitPrice * amount;
                    transactionItem.setItemName(itemName);
                    transactionItem.setItemName(itemName);
                    transactionItem.setTotalPrice(totalPrice);
                    transactionItem.setUnitPrice(unitPrice);

                    Double unitMemberPrice = storeSet.getSet().getMemberPrice();
                    Double totalMemberPrice = unitMemberPrice * amount;
                    transactionItem.setUnitMemberPrice(unitMemberPrice);
                    transactionItem.setTotalMemberPrice(totalMemberPrice);
                }
//=======
//                StoreProductEntity storeProduct = em.find(StoreProductEntity.class, item.getProductId());
//                String itemName = storeProduct.getProduct().getName();
//                Double unitPrice = storeProduct.getProduct().getPrice();
//                Double totalPrice = unitPrice * amount;
//                transactionItem.setItemName(itemName);
//                transactionItem.setTotalPrice(totalPrice);
//                transactionItem.setUnitPrice(unitPrice);
//
//                Double unitMemberPrice = storeProduct.getProduct().getMemberPrice();
//                Double totalMemberPrice = unitMemberPrice * amount;
//                transactionItem.setUnitMemberPrice(unitMemberPrice);
//                transactionItem.setTotalMemberPrice(totalMemberPrice);
//>>>>>>> 0427c1f918685d0ec7f6b47d5ad5c944f4c44f17

            } else {
                StoreRetailProductEntity storeRetailProduct = em.find(StoreRetailProductEntity.class, item.getRetailProductId());
                String itemName = storeRetailProduct.getRetailProduct().getName();
                Double unitPrice = storeRetailProduct.getRetailProduct().getPrice();
                Double totalPrice = unitPrice * amount;
                transactionItem.setItemName(itemName);
                transactionItem.setTotalPrice(totalPrice);
                transactionItem.setUnitPrice(unitPrice);

            }

            transactionItem.setAmount(amount);
            transactionItem.setItemId(itemId);
            transactionItem.setTransaction(transaction);

            em.persist(transactionItem);
            em.flush();

            transaction.getTransactionItemList().add(transactionItem);
            em.persist(transaction);
            em.flush();
        }

    }

    //caculate the total price and return 
    @Override
    @WebMethod(operationName = "caculateTotalPrice")
    public Double caculateTotalPrice(
            @WebParam(name = "transactionId") Long transactionId) {

        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);
        List<TransactionItemEntity> transactionItemList = transaction.getTransactionItemList();
        Double totalPrice = 0D;
        Double totalMemberPrice = 0D;

        for (TransactionItemEntity list : transactionItemList) {
            Double listTotalPrice = list.getTotalPrice();
            totalPrice += listTotalPrice;
        }

        if (transaction.getLocation() == 1) {
            for (TransactionItemEntity list : transactionItemList) {
                Double listTotalPrice = list.getTotalMemberPrice();
                totalMemberPrice += listTotalPrice;
            }
        }

        transaction.setTotalPrice(totalPrice);
        transaction.setTotalMemberPrice(totalMemberPrice);

        em.persist(transaction);
        em.flush();

        return totalPrice;

    }

    @WebMethod(operationName = "setTendered")
    public void setTendered(
            @WebParam(name = "transactionId") Long transactionId,
            @WebParam(name = "tendered") Double tendered) {
        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);

        transaction.setTendered(tendered);
        em.persist(transaction);
        em.flush();
    }

    //caculate the moneyChange amount and return
    @Override
    @WebMethod(operationName = "caculateChange")
    public void caculateChange(
            @WebParam(name = "transactionId") Long transactionId,
            @WebParam(name = "moneyChange") Double moneyChange) {
        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);
        transaction.setMoneyChange(moneyChange);

        em.persist(transaction);
        em.flush();
        System.out.println("Money Change: " + moneyChange);
        if (transaction.getLocation() == 1) {
            createPickupList(transactionId);
        }
    }

    @WebMethod(operationName = "caculateRedemption")
    public Double caculateRedemption(Double points, Long transactionId) {
        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);
        Double actualTotalPrice;
        if (transaction.getLocation() == 1 && transaction.getMember() != null) {
            actualTotalPrice = transaction.getTotalMemberPrice() - points / 100.0;
        } else {
            actualTotalPrice = transaction.getTotalPrice() - points / 100.0;
        }

        return actualTotalPrice;
    }

    @WebMethod(exclude = true)
    public void createPickupList(Long transactionId) {

        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);

        List<TransactionItemEntity> transactionItemList = transaction.getTransactionItemList();
        PickupListEntity pickupList = new PickupListEntity();
        List<TransactionItemEntity> temp = new ArrayList();

        for (TransactionItemEntity ti : transactionItemList) {
            Long itemId = ti.getItemId();
            StoreItemMappingEntity mapping = em.find(StoreItemMappingEntity.class, itemId);
//<<<<<<< HEAD
            if (mapping.getRetailProductId() == null && mapping.getStoreSetId() == null) {
                Long storeProductId = mapping.getProductId();
                StoreProductEntity storeProduct = em.find(StoreProductEntity.class, storeProductId);
                if (storeProduct.getSelfPick()) {
                    ti.setPickupList(pickupList);
                    em.persist(ti);
                    em.flush();
                    temp.add(ti);
                }
            } else if (mapping.getProductId() == null && mapping.getRetailProductId() == null) {
//                Long storeProductId = mapping.getProductId();
//                StoreProductEntity storeProduct = em.find(StoreProductEntity.class, storeProductId);
//                if (storeProduct.getSelfPick()) {
                ti.setPickupList(pickupList);
                em.persist(ti);
                em.flush();
                temp.add(ti);
            }
            pickupList.setTransactoinItems(temp);
            em.persist(pickupList);
            em.flush();

//            Long storeProductId = mapping.getProductId();
//            StoreProductEntity storeProduct = em.find(StoreProductEntity.class, storeProductId);
//            if (storeProduct.getSelfPick()) {
//                ti.setPickupList(pickupList);
//                em.persist(ti);
//                em.flush();
//                temp.add(ti);
//>>>>>>> 0427c1f918685d0ec7f6b47d5ad5c944f4c44f17
//            }
        }
    }

    @WebMethod(operationName = "checkItem")
    public Boolean checkItem(Long UUID
    ) {
        StoreItemMappingEntity itemMapping = em.find(StoreItemMappingEntity.class, UUID);
        return itemMapping != null;
    }

    @WebMethod(operationName = "getTransactionItemList")
    public List<TransactionItemEntity> getTransactionItemList(Long transactionId
    ) {

        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);
        if (transaction != null) {
            List<TransactionItemEntity> transactionList = transaction.getTransactionItemList();
            return transactionList;
        } else {
            return null;
        }

    }

    @WebMethod(operationName = "deleteUnfinishedTransaction")
    public void deleteUnfinishedTransaction(Long transactionId
    ) {
        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);

        if (transaction.getMoneyChange() == null) {
            em.remove(transaction);
            em.flush();
        }

    }

    @WebMethod(operationName = "getStoreByStaffId")
    public StoreEntity getStoreByStaffId(
            @WebParam(name = "storeStaffId") String storeStaffId
    ) {

        StoreUserEntity storeUser = em.find(StoreUserEntity.class, storeStaffId);
        StoreEntity store = em.find(StoreEntity.class, storeUser.getDepartmentId());
        return store;
    }

    @WebMethod(operationName = "getTransactionById")
    public TransactionEntity getTransactionById(Long transactionId
    ) {
        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);
        return transaction;
    }

    @WebMethod(operationName = "changeAmount")
    public void changeAmount(Long transactionItemId, int amount
    ) {
        TransactionItemEntity transactionItem = em.find(TransactionItemEntity.class, transactionItemId);
        transactionItem.setAmount(amount);
        Double totalPrice = transactionItem.getUnitPrice() * amount;
        transactionItem.setTotalPrice(totalPrice);

        if (transactionItem.getUnitMemberPrice() != null) {
            Double totalMemberPrice = transactionItem.getUnitMemberPrice() * amount;
            transactionItem.setTotalMemberPrice(totalMemberPrice);
        }

        em.persist(transactionItem);
        em.flush();
    }

    @WebMethod(operationName = "deleteTransactionItem")
    public void deleteTransactionItem(Long transactionItemId, Long transactionId
    ) {
        TransactionItemEntity transactionItem = em.find(TransactionItemEntity.class, transactionItemId);
        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);

        List<TransactionItemEntity> transactionItemList = transaction.getTransactionItemList();
        if (transactionItemList.remove(transactionItem)) {
            System.out.println("Remove from transaction successfully\n");
        }
        transaction.setTransactionItemList(transactionItemList);
        em.persist(transaction);
        em.flush();

        em.remove(transactionItem);
        em.flush();
    }

    //check the product type, return 1 if it is furniture, 2 if it is retail product
    @WebMethod(operationName = "checkItemType")
    public int checkItemType(Long itemId) {
        StoreItemMappingEntity mapping = em.find(StoreItemMappingEntity.class, itemId);
        if (mapping.getRetailProductId() == null && mapping.getStoreSetId() == null) {
            return 1;
        } else if (mapping.getStoreSetId() == null && mapping.getProductId() == null) {
            return 2;
        } else {
            return 3;
        }
    }

    @WebMethod(operationName = "inventoryMovement")
    public void inventoryMovement(Long transactionId) {
        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);
        List<TransactionItemEntity> transactionItemList = transaction.getTransactionItemList();
        for (TransactionItemEntity ti : transactionItemList) {
            Long itemId = ti.getItemId();
            Double amount = Double.parseDouble(String.valueOf(ti.getAmount()));
            StoreItemMappingEntity mapping = em.find(StoreItemMappingEntity.class, itemId);
            Calendar today = Calendar.getInstance();

            Query q = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecordList = (List<SalesRecordEntity>) q.getResultList();
            if (mapping.getProductId() == null && mapping.getStoreSetId() == null) {

                Long retailProductId = mapping.getRetailProductId();
                StoreRetailProductEntity retailProduct = em.find(StoreRetailProductEntity.class, retailProductId);
                retailProduct.setOnairInventory(retailProduct.getOnairInventory() - amount);
                retailProduct.setUnrestrictedInventory(retailProduct.getUnrestrictedInventory() - amount);
                em.persist(retailProduct);
                em.flush();

                boolean exist = false;
                for (SalesRecordEntity s : salesRecordList) {
                    if (s.getStoreRetailProduct() != null) {
                        if (s.getStoreRetailProduct().getStoreRetailProductId().equals(retailProductId) && s.getRecordPeriod().get(Calendar.YEAR) == today.get(Calendar.YEAR) && s.getRecordPeriod().get(Calendar.MONTH) == (today.get(Calendar.MONTH))) {
                            s.setAmount(s.getAmount() + amount);
                            s.setRevenue((s.getAmount() + amount * retailProduct.getRetailProduct().getPrice()));
                            exist = true;
                            break;
                        }

                    }
                    em.flush();
                }

                if (exist == false) {
                    SalesRecordEntity newSalesRecord = new SalesRecordEntity();
                    newSalesRecord.setStore(retailProduct.getStore());
                    newSalesRecord.setAmount(amount);
                    newSalesRecord.setRecordPeriod(today);
                    newSalesRecord.setRevenue(amount * retailProduct.getRetailProduct().getPrice());
                    newSalesRecord.setStoreRetailProduct(retailProduct);
                    em.persist(newSalesRecord);
                    em.flush();
                    retailProduct.getSalesRecordList().add(newSalesRecord);
                    em.flush();
                }

            } else if (mapping.getStoreSetId() == null && mapping.getRetailProductId() == null) {
                Long productId = mapping.getProductId();
                StoreProductEntity product = em.find(StoreProductEntity.class, productId);
                if (!product.getSelfPick()) {
                    product.setOnairInventory(product.getOnairInventory() - amount);
                    product.setUnrestrictedInventory(product.getUnrestrictedInventory() - amount);
                    em.persist(product);
                    em.flush();
                } else {
                    product.setUnrestrictedInventory(product.getUnrestrictedInventory() - amount);
                    em.persist(product);
                    em.flush();
                }
                boolean exist = false;
                for (SalesRecordEntity s : salesRecordList) {
                    if (s.getStoreProduct() != null) {
                        if (s.getStoreProduct().getStoreProductId().equals(productId) && s.getRecordPeriod().get(Calendar.YEAR) == today.get(Calendar.YEAR) && s.getRecordPeriod().get(Calendar.MONTH) == (today.get(Calendar.MONTH))) {
                            s.setAmount(s.getAmount() + amount);
                            s.setRevenue(s.getRevenue() + amount * product.getProduct().getPrice());
                            exist = true;
                        }

                    }
                    em.flush();
                }

                if (exist == false) {
                    SalesRecordEntity newSalesRecord = new SalesRecordEntity();
                    newSalesRecord.setStore(product.getStore());
                    newSalesRecord.setAmount(amount);
                    newSalesRecord.setRecordPeriod(today);
                    newSalesRecord.setRevenue(amount * product.getProduct().getPrice());
                    newSalesRecord.setStoreProduct(product);
                    em.persist(newSalesRecord);
                    em.flush();
                    product.getSalesRecordList().add(newSalesRecord);
                    em.flush();

                }
                // }
            } else {
                // StoreSetEntity storeSet = ;
                for (StoreProductEntity sp : (em.find(StoreSetEntity.class, mapping.getStoreSetId())).getStoreProductList()) {
                    //sp.setOnairInventory(sp.getOnairInventory() - amount);
                    sp.setUnrestrictedInventory(sp.getUnrestrictedInventory() - amount);
                    // em.persist(sp);
                    em.flush();

                    boolean exist = false;
                    for (SalesRecordEntity s : salesRecordList) {
                        if (s.getStoreProduct() != null) {
                            if (s.getStoreProduct().getStoreProductId().equals(sp.getStoreProductId()) && s.getRecordPeriod().get(Calendar.YEAR) == today.get(Calendar.YEAR) && s.getRecordPeriod().get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
                                s.setAmount(s.getAmount() + amount);
                                s.setRevenue(s.getRevenue() + sp.getProduct().getPrice() * amount);
                                exist = true;
                            }

                        }
                        em.flush();
                    }

                    if (exist == false) {
                        SalesRecordEntity newSalesRecord = new SalesRecordEntity();
                        newSalesRecord.setStore(sp.getStore());
                        newSalesRecord.setAmount(amount);
                        newSalesRecord.setRecordPeriod(today);
                        newSalesRecord.setRevenue(amount * sp.getProduct().getPrice());
                        newSalesRecord.setStoreProduct(sp);
                        em.persist(newSalesRecord);
                        em.flush();
                        sp.getSalesRecordList().add(newSalesRecord);
                        em.flush();

                    }

                }
            }
        }
    }

//    public void UpdateSalesRecord(Calendar generateTime,Long transactionId, int amount, Double totalprice){
//        List<SalesRecordEntity> salesRecordEntityList=new ArrayList<>();
//        Query q = em.createQuery("SELECT s FROM SalesRecordEntity s");
//        salesRecordEntityList= (List<SalesRecordEntity>) q.getResultList();
//        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);
//        List<TransactionItemEntity> transactionList = transaction.getTransactionItemList();
//        for(TransactionItemEntity tie: transactionList){
//        for(SalesRecordEntity s:salesRecordEntityList){
//            if(s.getStore().getStoreId().equals(transaction.getStore().getStoreId())&&){
//            
//            }
//        
//        }
//        }
//    }
}
