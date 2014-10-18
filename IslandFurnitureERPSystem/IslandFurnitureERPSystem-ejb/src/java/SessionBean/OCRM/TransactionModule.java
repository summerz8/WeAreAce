/*
 * To moneyChange this license header, choose License Headers in Project Properties.
 * To moneyChange this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.CommonInfrastructure.StoreUserEntity;
import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.MembershipLevel;
import Entity.Store.OCRM.PickupListEntity;
import Entity.Store.OCRM.TransactionEntity;
import Entity.Store.OCRM.TransactionItem;
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
    public void createNewTransaction(
            @WebParam(name = "staffId") String staffId,
            @WebParam(name = "memberId") Long memberId,
            @WebParam(name = "location") int location){
        
        Calendar generatedTime = Calendar.getInstance();
        StoreUserEntity storeStaff = em.find(StoreUserEntity.class, staffId);
        StoreEntity store = em.find(StoreEntity.class,storeStaff.getDepartmentId());
        
        
        TransactionEntity transaction = new TransactionEntity();
        
        transaction.setGenerateTime(generatedTime);
        transaction.setStore(store);
        transaction.setStoreStaff(storeStaff);
        if (memberId != null){
            MemberEntity member = em.find(MemberEntity.class,memberId);
            transaction.setMember(member);
        }
        transaction.setLocation(location);
        
        em.persist(transaction);
        em.flush();
        
        if(memberId != null){
            MemberEntity member = em.find(MemberEntity.class,memberId);
            member.getTransactionList().add(transaction);
            em.persist(member);
            em.flush();
        }
    
    }
    
    @Override
    @WebMethod(operationName = "createTranactionItem")
    public void createTransactionItem(
            @WebParam(name = "itemId") Long itemId,
            @WebParam(name = "amount") int amount,
            @WebParam(name = "unitPrice") Double unitPrice,
            @WebParam(name = "transactionId") Long transactionId){
        
        Double totalPrice = unitPrice * amount;
        String itemName;
        
        StoreItemMappingEntity item = em.find(StoreItemMappingEntity.class,itemId);
        TransactionEntity transaction = em.find(TransactionEntity.class,transactionId);
        
        if(transaction.getLocation() == 1 ){
            StoreProductEntity storeProduct = em.find(StoreProductEntity.class,item.getProductid());
            itemName = storeProduct.getProduct().getName();
        }
        else{
            StoreRetailProductEntity storeRetailProduct = em.find(StoreRetailProductEntity.class,item.getRetailProductId());
            itemName = storeRetailProduct.getRetailProduct().getName();
        }
        
        TransactionItem transactionItem = new TransactionItem();
        transactionItem.setAmount(amount);
        transactionItem.setItemId(itemId);
        transactionItem.setItemName(itemName);
        transactionItem.setTotalPrice(totalPrice);
        transactionItem.setUnitPrice(unitPrice);
        transactionItem.setTransaction(transaction);
         
        em.persist(transactionItem);
        em.flush();
        
        transaction.getTransactionItemList().add(transactionItem);
        em.persist(transaction);
        em.flush();
               
    }
    
    //caculate the total price and return 
    @Override
    @WebMethod(operationName = "caculateTotalPrice")
    public Double caculateTotalPrice(
            @WebParam(name = "transactionId") Long transactionId){
        
        TransactionEntity transaction = em.find(TransactionEntity.class,transactionId);
        List<TransactionItem> transactionItemList = transaction.getTransactionItemList();
        Double totalPrice = 0D;
        
        for (TransactionItem list : transactionItemList) {
            Double listTotalPrice = list.getTotalPrice();
            totalPrice += listTotalPrice;
        }
        
        if(transaction.getMember() !=null){
           MemberEntity member = transaction.getMember();
           MembershipLevel level = member.getMemberlvl();
           Double discount = level.getDiscount();
           totalPrice *= discount;
        }
               
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
            @WebParam(name = "tendered")Double tendered){
        TransactionEntity transaction = em.find(TransactionEntity.class,transactionId);
        
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
    public void createPickupList(Long transactionId){
        
        TransactionEntity transaction = em.find(TransactionEntity.class,transactionId);
        
        List<TransactionItem> transactionItemList = transaction.getTransactionItemList();
        PickupListEntity pickupList = new PickupListEntity();    
        
        for(TransactionItem transactionItem : transactionItemList){
            Long UUID = transactionItem.getItemId();
            
            StoreItemMappingEntity mapping = em.find(StoreItemMappingEntity.class, UUID);
            
            if(mapping.getProductid()!=null){
               Long storeProductId = mapping.getProductid();
               StoreProductEntity storeProduct = em.find(StoreProductEntity.class,storeProductId);
               if(!storeProduct.getSelfPick()){
                   pickupList.getTransactoinItems().add(transactionItem);
                   transactionItem.setPickupList(pickupList);
               }
            }
            
            else{
                pickupList.getTransactoinItems().add(transactionItem);
                transactionItem.setPickupList(pickupList);
            }
            
            em.persist(transactionItem);
            em.flush();
            
            em.persist(pickupList);
            em.flush();
        }//for
        
        
    }
   
}
