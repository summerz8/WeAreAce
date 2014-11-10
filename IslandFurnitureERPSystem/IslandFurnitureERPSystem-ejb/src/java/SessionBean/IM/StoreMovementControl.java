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
         System.out.println("Session Bean:StoremovementControl:viewIncomingGoodsFromFactory(): StoreId = " + storeId );
         Query q = em.createQuery("Select om From OutboundMovementEntity om where om.toStore.storeId = :storeCode AND om.receivedByStore = false");
         q.setParameter("storeCode", storeId);
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
        System.out.println("FactoryProductId" + fProduct.getFactoryProductId());
        StoreProductEntity spe = null;
        int n = 0;
        for(Object o: q.getResultList()){
              spe  = (StoreProductEntity) o;
              n = n + 1;
        }
        System.out.println("StoreProduct CONVERT N= " + n);
        System.out.println("StoreProduct convertProductFTOS " + spe.getStoreProductId());
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
        StoreRetailProductEntity srpe = null;
        for(Object o: q.getResultList()){
            
             srpe = (StoreRetailProductEntity)o;
        }
       
        sRproductId = srpe.getStoreRetailProductId();
        return sRproductId;
        
    }
    
    @Override
    public void fromFactoryGoodReceipts(Long outboundMovementId, Long inventoryId, Integer invType, Double quantity, Long storeId){
        try {
        StoreProductEntity spe = null;
        StoreRetailProductEntity srpe = null;
        StoreEntity store = em.find(StoreEntity.class, storeId);
        OutboundMovementEntity ome = em.find(OutboundMovementEntity.class, outboundMovementId);
        if(invType == 0){
        spe = em.find(StoreProductEntity.class,inventoryId);
        Double newQuantity = spe.getIntransitInventory() + quantity;
        spe.setIntransitInventory(newQuantity);
        System.out.println("StoreMovementControlBean:fromFactoryGoodReceipts(): type " + invType);
        
        }
        
        else if(invType == 1){   
            
        srpe = em.find(StoreRetailProductEntity.class, inventoryId);
        Double newQuantity = srpe.getIntransitInventory() + quantity;
        srpe.setIntransitInventory(newQuantity);
        System.out.println("StoreMovementControlBean:fromFactoryGoodReceipts(): type " + invType);

        }
        
        
        
        Calendar creationTime = Calendar.getInstance();
        StoreGoodReceiptEntity goodreceipt = new StoreGoodReceiptEntity(invType,quantity,creationTime);
        em.persist(goodreceipt);
        System.out.println("StoreMovementControlBean:fromFactoryGoodReceipts(): persist: GD: " + goodreceipt.getId());

        goodreceipt.setSpe(spe);
        System.out.println("StoreMovementControlBean:fromFactoryGoodReceipts(): persist: Set SPE");

        goodreceipt.setSrpe(srpe);
        System.out.println("StoreMovementControlBean:fromFactoryGoodReceipts(): persist: Set SRPE");

        goodreceipt.setSe(store);
        System.out.println("StoreMovementControlBean:fromFactoryGoodReceipts(): persist: Set S");

        store.getGoodReceipts().add(goodreceipt);
        
        if(invType == 0){
        spe.getGoodReceipts().add(goodreceipt);}
        if(invType ==1){
        srpe.getGoodReceipts().add(goodreceipt);}
        
        ome.setReceivedByStore(true);
        
        goodreceipt.setOme(ome);
        
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
        else if(invType == 1){
            
        StoreRetailProductEntity srp = em.find(StoreRetailProductEntity.class,storeProductId);
            if(srp.getIntransitInventory() >= quantity){
            result = RProductmoveInABin(storeId, storeBinId, storeProductId, quantity , status);
            srp.setIntransitInventory(srp.getIntransitInventory()-quantity);
            em.flush();
            
              }
            
            else result = -3;
        }
        
        return result;
        
    }
    
    
    // -1 bin is the same, nothing changed
    // -2 from Bin not enough stock 
    // -3 self-pick item cannot move to display area
    // -4 self-pick item cannot move to other area
    //-7 unknown exception
    
    public int inStoreMovement(Long storeId, Integer productType, Long inId, Integer oldStatus, Integer newStatus, Long oldBId, Long newBId, Double quantity){
        
        try{
        StoreWarehouseBinEntity oldBin = em.find(StoreWarehouseBinEntity.class, oldBId);
        
        StoreWarehouseBinEntity newBin = em.find(StoreWarehouseBinEntity.class, newBId);

        StoreProductEntity sp = null;
        StoreRetailProductEntity srp = null;
        if(oldBin.equals(newBin)){
            return -1;
        }
        
        
        if(productType == 0){
           sp =em.find(StoreProductEntity.class, inId); 
           int moveOut = ProductmoveOutABin(storeId, oldBin.getId(), inId, quantity, oldStatus);
            if(moveOut == 2){
                return moveOut;
            }
            int moveIn = ProductmoveInABin(storeId, newBin.getId(), inId, quantity, newStatus);
            if(moveIn == -1){
                
                return -7;
            }
        }
        else if(productType == 1){
            srp = em.find(StoreRetailProductEntity.class,inId);
        }
        
        
        Calendar creationTime = Calendar.getInstance();
        StoreInStoreMovementRecordEntity instoreRecord = new StoreInStoreMovementRecordEntity(quantity,creationTime);
        em.persist(instoreRecord);
        instoreRecord.setFromBin(oldBin);
        instoreRecord.setToBin(newBin);
        instoreRecord.setStoreProduct(sp);
        instoreRecord.setStoreRetailProduct(srp);
        return -0;
        }catch (Exception e){
          System.err.println("SessionBean.IM.StoreMovementControl: inStoreMovement(): Failed. Caught an unexpected exception.");
          e.printStackTrace();
         return -7;  
            
        }
        
    }
    
    
    
    private int ProductmoveOutABin(Long storeId, Long storeBinId, Long storeProductId, Double quantity, Integer status){
        StoreWarehouseBinEntity storeBin = em.find(StoreWarehouseBinEntity.class, storeBinId);
        Long id = findABinRProduct(storeBinId, storeProductId, status);
        StoreBinProductEntity sbpe = em.find(StoreBinProductEntity.class,id);
        Double currentQ = sbpe.getQuantity();
        if(currentQ < quantity){
            
            return -2;
        }
        currentQ = currentQ - quantity;
        if(status == 0){
            sbpe.getProduct().setUnrestrictedInventory(sbpe.getProduct().getUnrestrictedInventory() - quantity);   
        }
        else if (status == 1){
            sbpe.getProduct().setReturnedInventory(sbpe.getProduct().getReturnedInventory() - quantity);
        }
        sbpe.setQuantity(currentQ);
        em.flush();
        return -0;
        
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
         
         em.persist(record);
         if(status == 0){
             
             sp.setUnrestrictedInventory(sp.getUnrestrictedInventory()+ quantity);
         }
         else if(status == 1){
             
             sp.setReturnedInventory(sp.getReturnedInventory()+ quantity);
         }
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
    
        private int RProductmoveInABin(Long storeId, Long storeBinId, Long storeProductId, Double quantity, Integer status){
         try{
         StoreWarehouseBinEntity storeBin = em.find(StoreWarehouseBinEntity.class,storeBinId);
         StoreEntity store = em.find(StoreEntity.class,storeId);
         StoreRetailProductEntity sp = em.find(StoreRetailProductEntity.class, storeProductId);
        
         
         Long id = findABinRProduct(storeBinId, storeProductId, status);
         if(id != null){
         StoreBinRetailProductEntity sbp = em.find(StoreBinRetailProductEntity.class, id);
         Double newquantity = sbp.getQuantity() + quantity;
         sbp.setQuantity(newquantity);
         sbp.setStatus(status);
         
         }
         else{
             StoreBinRetailProductEntity sbp = new StoreBinRetailProductEntity();
             em.persist(sbp);
             sbp.setRetailProduct(sp);
             sbp.setQuantity(quantity);
             em.flush();
         }

         Calendar creationTime = Calendar.getInstance();
         StoreInboundRecordEntity record = new StoreInboundRecordEntity(quantity, creationTime);
         
         em.persist(record);
         if(status == 0){
             
             sp.setUnrestrictedInventory(sp.getUnrestrictedInventory()+ quantity);
         }
         else if(status == 1){
             
             sp.setReturnedInventory(sp.getReturnedInventory()+ quantity);
         }
         record.setStoreRetailProduct(sp);
         record.setToBin(storeBin);
         return 0;
         
         }
         catch (Exception e){
          System.err.println("SessionBean.IM.StoreMovementControl: RProductmoveInABin(): Failed. Caught an unexpected exception.");
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
        private Long findABinRProduct(Long storeBinId, Long storeProductId, Integer status){
         StoreRetailProductEntity sp = em.find(StoreRetailProductEntity.class, storeProductId);
         StoreWarehouseBinEntity storeBin = em.find(StoreWarehouseBinEntity.class,storeBinId);
         Collection<StoreBinRetailProductEntity> stockList = storeBin.getStoreBinRetailProducts();
         Boolean isFound = false;
         Long sbpId = null;
         for(StoreBinRetailProductEntity sb: stockList){
             StoreBinRetailProductEntity s = sb;
             if(s.isIsDeleted() == false && s.getStatus().equals(status) && s.getRetailProduct().equals(sp)){
                isFound = true;
                sbpId = s.getId();
                break; 
             }
         }
        return sbpId;
    
    }
    
   
}
