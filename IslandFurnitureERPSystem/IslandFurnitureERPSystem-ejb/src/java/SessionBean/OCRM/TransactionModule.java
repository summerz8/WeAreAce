/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hangsun
 */
@Stateless
public class TransactionModule implements TransactionModuleLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void createNewTransaction(Long staffId,Long memberId){
        
        Calendar generatedTime = Calendar.getInstance();
        StoreUserEntity storeStaff = em.find(StoreUserEntity.class, staffId);
        StoreEntity store = em.find(StoreEntity.class,storeStaff.getDepartmentId());
        
        TransactionEntity transaction = new TransactionEntity();
        
        transaction.setGenerateTime(generatedTime);
        transaction.setStore(store);
        transaction.setStoreStaffId(staffId);
        if (memberId != null) transaction.setMemberId(memberId);
        
        em.persist(transaction);
        em.flush();
    
    }
    
    @Override
    public void createTransactionItem(Long itemId,int amount,Double unitPrice,Long transactionId){
        Double totalPrice = unitPrice * amount;
        String itemName;
        
        StoreItemMappingEntity item = em.find(StoreItemMappingEntity.class,itemId);
        TransactionEntity transaction = em.find(TransactionEntity.class,transactionId);
        
        if(item.getProductid() != null){
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
        
        transaction.getTransactionItems().add(transactionItem);
        em.persist(transaction);
        em.flush();
               
    }
    
    //caculate the total price and return 
    @Override
    public Double caculateTotalPrice(Long transactionId){
        TransactionEntity transaction = em.find(TransactionEntity.class,transactionId);
        List<TransactionItem> transactionItemList = transaction.getTransactionItems();
        Double totalPrice = 0D;
        
        for (TransactionItem list : transactionItemList) {
            Double listTotalPrice = list.getTotalPrice();
            totalPrice += listTotalPrice;
        }
        
        if(transaction.getMemberId()!=null){
           MemberEntity member = em.find(MemberEntity.class,transaction.getMemberId());
           MembershipLevel level = member.getMemberlvl();
           Double discount = level.getDiscount();
           totalPrice *= discount;
        }
               
        transaction.setTotalPrice(totalPrice);
       
        em.persist(transaction);
        em.flush();
        
        return totalPrice;
        
    }
    //caculate the change amount and return
    @Override
    public Double caculateChange(Long transactionId,Double tendered){
        TransactionEntity transaction = em.find(TransactionEntity.class,transactionId);
        
        Double totalPrice = transaction.getTotalPrice();
        Double change = tendered - totalPrice;
        
        transaction.setTendered(tendered);
        transaction.setChange(change);
        em.persist(transaction);
        em.flush();
        

        createPickupList(transactionId);
        
        
        return change;
    }
    
    public void createPickupList(Long transactionId){
        
        TransactionEntity transaction = em.find(TransactionEntity.class,transactionId);
        
        List<TransactionItem> transactionItemList = transaction.getTransactionItems();
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
