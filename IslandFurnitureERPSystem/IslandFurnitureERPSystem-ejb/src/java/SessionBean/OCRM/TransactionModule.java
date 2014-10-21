/*
 * To moneyChange this license header, choose License Headers in Project Properties.
 * To moneyChange this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.CommonInfrastructure.StoreUserEntity;
import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.MembershipLevelEntity;
import Entity.Store.OCRM.PickupListEntity;
import Entity.Store.OCRM.SalesRecordEntity;
import Entity.Store.OCRM.TransactionEntity;
import Entity.Store.OCRM.TransactionItemEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreItemMappingEntity;
import Entity.Store.StoreRetailProductEntity;
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
            @WebParam(name = "location") int location) {

        Calendar generatedTime = Calendar.getInstance();
        StoreUserEntity storeStaff = em.find(StoreUserEntity.class, staffId);
        StoreEntity store = em.find(StoreEntity.class,storeStaff.getDepartmentId());
        TransactionEntity transaction = new TransactionEntity();       

        transaction.setGenerateTime(generatedTime);
        transaction.setStore(store);
        transaction.setStoreStaff(storeStaff);
        if (memberId != null) {
            MemberEntity member = em.find(MemberEntity.class, memberId);
            transaction.setMember(member);
        }
        transaction.setLocation(location);

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
            @WebParam(name = "amount") Double amount,
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
                StoreProductEntity storeProduct = em.find(StoreProductEntity.class, item.getProductid());
                String itemName = storeProduct.getProduct().getName();
                Double unitPrice = storeProduct.getProduct().getPrice();
                Double totalPrice = unitPrice * amount;
                transactionItem.setItemName(itemName);
                transactionItem.setTotalPrice(totalPrice);
                transactionItem.setUnitPrice(unitPrice);
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

        for (TransactionItemEntity list : transactionItemList) {
            Double listTotalPrice = list.getTotalPrice();
            upDateSalesRecord(transaction.getGenerateTime(), list.getItemId(), list.getAmount(), list.getTotalPrice());
            totalPrice += listTotalPrice;
        }



//        if(transaction.getMember() !=null){
//           MemberEntity member = transaction.getMember();
//           MembershipLevel level = member.getMemberlvl();
//           Double discount = level.getDiscount();
//           totalPrice *= discount;
//        }


        transaction.setTotalPrice(totalPrice);

        em.persist(transaction);
        em.flush();

        return totalPrice;

    }

    //caculate the moneyChange amount and return
    @Override
    @WebMethod(operationName = "caculateChange")
    public Double caculateChange(
            @WebParam(name = "transactionId") Long transactionId,
            @WebParam(name = "tendered") Double tendered) {
        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);

        Double totalPrice = transaction.getTotalPrice();
        Double moneyChange = tendered - totalPrice;

        transaction.setTendered(tendered);
        transaction.setMoneyChange(moneyChange);

        em.persist(transaction);
        em.flush();

        createPickupList(transactionId);

        return moneyChange;
    }

    @WebMethod(exclude = true)
    public void createPickupList(Long transactionId) {

        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);


        List<TransactionItemEntity> transactionItemList = transaction.getTransactionItemList();
        PickupListEntity pickupList = new PickupListEntity();

        for (TransactionItemEntity transactionItem : transactionItemList) {
            Long UUID = transactionItem.getItemId();

            StoreItemMappingEntity mapping = em.find(StoreItemMappingEntity.class, UUID);

            if (mapping.getProductid() != null) {
                Long storeProductId = mapping.getProductid();
                StoreProductEntity storeProduct = em.find(StoreProductEntity.class, storeProductId);
                if (!storeProduct.getSelfPick()) {
                    pickupList.getTransactoinItems().add(transactionItem);
                    transactionItem.setPickupList(pickupList);
                }
            } else {
                pickupList.getTransactoinItems().add(transactionItem);
                transactionItem.setPickupList(pickupList);
            }

            em.persist(transactionItem);
            em.flush();

            em.persist(pickupList);
            em.flush();
        }//for

    }

    public void upDateSalesRecord(Calendar generateTime, Long itemId, Double amount, Double revenue) {
        Long productId;

        Query q = em.createQuery("SELECT t FROM StoreItemMappingEntity t");
        for (StoreItemMappingEntity s : (List<StoreItemMappingEntity>) q) {
            if (s.getProductid() == null) {
                productId = s.getRetailProductId();
                StoreRetailProductEntity storeRetailProduct = em.find(StoreRetailProductEntity.class, productId);
                storeRetailProduct.setQuantity(storeRetailProduct.getQuantity() - amount);
                SalesRecordEntity salesRecord = storeRetailProduct.getSalesRecordList().get(storeRetailProduct.getSalesRecordList().size() - 1);
                if (salesRecord.getPeriod().get(Calendar.MONTH) == generateTime.get(Calendar.MONTH)
                        && salesRecord.getPeriod().get(Calendar.YEAR) == generateTime.get(Calendar.YEAR)) {
                    salesRecord.setAmount(salesRecord.getAmount() + amount);
                    salesRecord.setAmount(salesRecord.getRevenue() + revenue);
                } else {
                    SalesRecordEntity newSalesRecord = new SalesRecordEntity();
                    newSalesRecord.setStore(storeRetailProduct.getStore());
                    newSalesRecord.setStoreRetailProduct(storeRetailProduct);
                    newSalesRecord.setPeriod(generateTime);
                    newSalesRecord.setAmount(amount);
                    newSalesRecord.setRevenue(revenue);
                    
                }
            
        }else {
                productId = s.getProductid();
                StoreProductEntity storeProduct = em.find(StoreProductEntity.class, productId);
                storeProduct.setQuantity(storeProduct.getQuantity() - amount);
                SalesRecordEntity salesRecord = storeProduct.getSalesRecordList().get(storeProduct.getSalesRecordList().size() - 1);
                if (salesRecord.getPeriod().get(Calendar.MONTH) == generateTime.get(Calendar.MONTH)
                        && salesRecord.getPeriod().get(Calendar.YEAR) == generateTime.get(Calendar.YEAR)) {
                    salesRecord.setAmount(salesRecord.getAmount() + amount);
                    salesRecord.setAmount(salesRecord.getRevenue() + revenue);
                } else {
                    SalesRecordEntity newSalesRecord = new SalesRecordEntity();
                    newSalesRecord.setStore(storeProduct.getStore());
                    newSalesRecord.setStoreProduct(storeProduct);
                    newSalesRecord.setPeriod(generateTime);
                    newSalesRecord.setAmount(amount);
                    newSalesRecord.setRevenue(revenue);
                    
                }
            }

    }

}

    @WebMethod(operationName = "checkItem")
    public Boolean checkItem(Long UUID) {
        StoreItemMappingEntity itemMapping = em.find(StoreItemMappingEntity.class, UUID);
        return itemMapping != null;
    }

    @WebMethod(operationName = "getTransactionItemList")
    public List<TransactionItemEntity> getTransactionItemList(Long transactionId) {

        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);
        if (transaction != null) {
            List<TransactionItemEntity> transactionList = transaction.getTransactionItemList();
            return transactionList;
        } else {
            return null;
        }

    }

    @WebMethod(operationName = "deleteUnfinishedTransaction")
    public void deleteUnfinishedTransaction(Long transactionId) {
        TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);

        if (transaction.getMoneyChange() == null) {
            em.remove(transaction);
            em.flush();
        }

    }


//    
//    public void upDateSalesRecord(Calendar generateTime,Long itemId, int amount, Double totalprice){
//        List<SalesRecord> salesRecordEntityList=new ArrayList<>();
//        Query q = em.createQuery("SELECT s FROM SalesRecord s");
//        salesRecordEntityList= (List<SalesRecord>) q.getResultList();
//        for(SalesRecord s:salesRecordEntityList){
//            if(s)
//        
//        }
//    }


}
