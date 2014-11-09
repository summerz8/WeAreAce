/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.IM;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.SCM.OutboundMovementEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.IM.StoreBinRetailProductEntity;
import Entity.Store.IM.StoreGoodReceiptEntity;
import Entity.Store.IM.StoreInStoreMovementRecordEntity;
import Entity.Store.IM.StoreInboundRecordEntity;
import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import static Entity.Store.StoreProductEntity_.storeProductId;
import Entity.Store.StoreRetailProductEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author zhengyuan
 */
@Stateless
public class StoreMovementControl implements StoreMovementControlLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
    
    
    
    @Override
    public List<OutboundMovementEntity> viewIncomingGoodsFromFactory(Long storeId){
         List<OutboundMovementEntity> result = new ArrayList<OutboundMovementEntity> ();
         StoreEntity store = em.find(StoreEntity.class, storeId);
         Query q = em.createQuery("Select om From OutboundMovementEntity om where om.receivedByStore = false and om.toStore = :storeCode");
         q.setParameter("storeCode", store);
         for (Object o : q.getResultList()){
             OutboundMovementEntity ome = (OutboundMovementEntity) o;
             result.add(ome);
         }
        return result;
    }
    
    @Override
    public List<PurchaseOrderEntity> viewIncomingGoodsFromSupplier(Long storeId){
        List<PurchaseOrderEntity> result = new ArrayList<PurchaseOrderEntity> ();
       StoreEntity store = em.find(StoreEntity.class, storeId);
       Query q = em.createQuery("Select poe From PurchaseOrderEntity poe where poe.isToStore = true and poe.status = :cStatus and  poe.destinationId = :storeCode");
       q.setParameter("storeCode", store.getStoreId());
       q.setParameter("cStatus","confirmed");
       for (Object o : q.getResultList()){
             PurchaseOrderEntity poe = (PurchaseOrderEntity) o;
             result.add(poe);
         }
  
        return result;
    }
    
    @Override
    public Long convertProductFToS(Long fproductId, Long storeId){
        Long sproductId = null;
        FactoryProductEntity fProduct = em.find(FactoryProductEntity.class, fproductId);
        StoreEntity store = em.find(StoreEntity.class,storeId);
        Query q = em.createQuery("Select sp From StoreProductEntity sp Where sp.store.storeId = :sId and sp.deleteFlag = false and sp.factoryProduct.factoryProductId = :fpId");
        q.setParameter("sId", storeId);
        q.setParameter("fpId", fProduct.getFactoryProductId());
        StoreProductEntity spe = (StoreProductEntity) q.getResultList();
        sproductId = spe.getStoreProductId();
        return sproductId;
    }
    
    @Override
    public Long convertRProductFToS(Long fRproductId, Long storeId){
        Long sRproductId = null;
        FactoryRetailProductEntity frp = em.find(FactoryRetailProductEntity.class, fRproductId);
        StoreEntity store = em.find(StoreEntity.class, storeId);
        Query q = em.createQuery("Select srp From StoreRetailProductEntity srp Where srp.store.storeId = :sId and srp.deleteFlag = false and srp.factoryRetailProduct.factoryRetailProdctId = :frpId");
        q.setParameter("sId", storeId);
        q.setParameter("frpId", frp.getFactoryRetailProdctId());
        StoreRetailProductEntity srpe = (StoreRetailProductEntity) q.getResultList();
        sRproductId = srpe.getStoreRetailProductId();
        return sRproductId;
        
    }
    
    @Override
    public void fromFactoryGoodReceipts(Long inventoryId, Integer invType, Double quantity, Long storeId){
        try {
        StoreProductEntity spe = null;
        StoreRetailProductEntity srpe = null;
        if(invType == 0){
        spe = em.find(StoreProductEntity.class,inventoryId);
        Double newQuantity = spe.getIntransitInventory() + quantity;
        spe.setIntransitInventory(newQuantity);
        
        }
        
        else if(invType == 1){   
            
        srpe = em.find(StoreRetailProductEntity.class, inventoryId);
        Double newQuantity = srpe.getIntransitInventory() + quantity;
        srpe.setIntransitInventory(newQuantity);
        
        }
        
        StoreEntity store = em.find(StoreEntity.class, storeId);
        
        Calendar creationTime = Calendar.getInstance();
        StoreGoodReceiptEntity goodreceipt = new StoreGoodReceiptEntity(invType,quantity,creationTime);
        em.persist(goodreceipt);
        
        goodreceipt.setSpe(spe);
        goodreceipt.setSrpe(srpe);
        goodreceipt.setSe(store);
        
        store.getGoodReceipts().add(goodreceipt);
        spe.getGoodReceipts().add(goodreceipt);
        srpe.getGoodReceipts().add(goodreceipt);
        
        
        em.flush();
        }
        catch (Exception e){
            
          System.err.println("SessionBean.IM.StoreMovementControl: fromFactoryGoodReceipts(): Failed. Caught an unexpected exception.");
          e.printStackTrace();
          
        }
       
    }
    
    @Override
    public int createInStoreRecord(Long storeId, Long frombinId, Long tobinId, Long productId, Long retailProductId, Double amount){
   
        return 0;
    }
    
    @Override
    public int createInboundRecord(){
        
        return 0;
    }
    
    @Override
    public int createOutBoundRecord(){
  
        return 0;
   
    }
    
    
    
    //-1 exception
    // -3 quantity smaller than required quantity
    // 0 add successfully
    
    @Override
    public int inboundMovement (Long storeId, Long storeBinId, Integer invType, Long storeProductId,Double quantity, Integer status){
        int result = -1;
        if(invType == 0){
        StoreProductEntity sp = em.find(StoreProductEntity.class, storeProductId);
            if(sp.getIntransitInventory() >= quantity){
            result = ProductmoveInABin(storeId, storeBinId, storeProductId, quantity , status);
            sp.setIntransitInventory(sp.getIntransitInventory()-quantity);
            em.flush();
            
            }
            else result = -3;
            }
        
        return result;
        
    }
    
    
    
    
    private int ProductmoveInABin(Long storeId, Long storeBinId, Long storeProductId, Double quantity, Integer status){
         try{
         StoreWarehouseBinEntity storeBin = em.find(StoreWarehouseBinEntity.class,storeBinId);
         StoreEntity store = em.find(StoreEntity.class,storeId);
         StoreProductEntity sp = em.find(StoreProductEntity.class, storeProductId);
        
         
         Long id = findABinProduct(storeBinId, storeProductId, status);
         if(id != null){
         StoreBinProductEntity sbp = em.find(StoreBinProductEntity.class, id);
         Double newquantity = sbp.getQuantity() + quantity;
         sbp.setQuantity(newquantity);
         sbp.setStatus(status);
         
         }
         else{
             StoreBinProductEntity sbp = new StoreBinProductEntity();
             em.persist(sbp);
             sbp.setProduct(sp);
             sbp.setQuantity(quantity);
             em.flush();
         }

         Calendar creationTime = Calendar.getInstance();
         StoreInboundRecordEntity record = new StoreInboundRecordEntity(quantity, creationTime);
         em.persist(quantity);
         record.setStoreProduct(sp);
         record.setToBin(storeBin);
         return 0;
         
         }
         catch (Exception e){
          System.err.println("SessionBean.IM.StoreMovementControl: ProductmoveInABin(): Failed. Caught an unexpected exception.");
          e.printStackTrace();
         return -1;
             
         }

    }
    
    
    private Long findABinProduct(Long storeBinId, Long storeProductId, Integer status){
         StoreProductEntity sp = em.find(StoreProductEntity.class, storeProductId);
         StoreWarehouseBinEntity storeBin = em.find(StoreWarehouseBinEntity.class,storeBinId);
         Collection<StoreBinProductEntity> stockList = storeBin.getStoreBinProducts();
         Boolean isFound = false;
         Long sbpId = null;
         for(StoreBinProductEntity sb: stockList){
             StoreBinProductEntity s = sb;
             if(s.isIsDeleted() == false && s.getStatus().equals(status) && s.getProduct().equals(sp)){
                isFound = true;
                sbpId = s.getId();
                break; 
             }
         }
        return sbpId;
    
    }
    
    private Long RProductmoveInABin(Long storeId, Long storeBinId, Long storeRProductId, Double quantity, Integer status){
     StoreRetailProductEntity srp = em.find(StoreRetailProductEntity.class, storeRProductId);
         StoreWarehouseBinEntity storeBin = em.find(StoreWarehouseBinEntity.class,storeBinId);
         Collection<StoreBinRetailProductEntity> stockList = storeBin.getStoreBinRetailProducts();
         Boolean isFound = false;
         Long sbpId = null;
         for(StoreBinRetailProductEntity sb: stockList){
             StoreBinRetailProductEntity s = sb;
             if(s.isIsDeleted() == false && s.getStatus().equals(status) && s.getRetailProduct().equals(srp)){
                isFound = true;
                sbpId = s.getId();
                break; 
             }
         }
        return sbpId;
    }
    
}
