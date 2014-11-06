/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.IM;

import Entity.Factory.ProductEntity;
import Entity.Factory.SCM.OutboundMovementEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.IM.StoreInStoreMovementRecordEntity;
import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
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
    
    public List<PurchaseOrderEntity> viewIncomingGoodsFromSupplier(Long storeId){
        List<PurchaseOrderEntity> result = new ArrayList<PurchaseOrderEntity> ();
         
        
        
        return result;
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
    
//        public int createInStoreRecord(Long storeId, Long frombinId, Long tobinId, Long productId, Long retailProductId, Double amount){
//        StoreEntity store = em.find(StoreEntity.class, storeId);
//        StoreWarehouseBinEntity fromBin = em.find(StoreWarehouseBinEntity.class, frombinId);
//        StoreWarehouseBinEntity toBin = em.find(StoreWarehouseBinEntity.class, tobinId);
//        StoreInStoreMovementRecordEntity newRecord = new StoreInStoreMovementRecordEntity();
//        Boolean isin = false;
//        Calendar sendTime = Calendar.getInstance();
//        if(retailProductId == null){
//        
//        StoreProductEntity storeproduct = em.find(StoreProductEntity.class, productId);
//        
//         newRecord.setStoreProduct(storeproduct);
//         newRecord.setStoreRetailProduct(null);
//         
//        Collection<StoreBinProductEntity> productInfoList  = fromBin.getStoreBinProducts();
//        for(StoreBinProductEntity sbpe: productInfoList){
//            StoreBinProductEntity binProduct = sbpe;
//            if(binProduct.getProduct().equals(storeproduct)){
//                isin = true;
//                if(binProduct.getQuantity() >= amount){
//                    binProduct.setQuantity(binProduct.getQuantity()-amount);
//                }
//                
//            }
//            
//        }
//       
//        
//        }
//        else if(productId == null){
//            
//            StoreRetailProductEntity storeRetailProduct = em.find(StoreRetailProductEntity.class, retailProductId);
//            newRecord.setStoreRetailProduct(storeRetailProduct);
//            newRecord.setStoreProduct(null);
//        }
//        
//        newRecord.setAmount(amount);
//        newRecord.setFromBin(fromBin);
//        newRecord.setToBin(toBin);
//        newRecord.setCreationTime(sendTime);
//        
//        em.persist(newRecord);
//        
//        
//         return 0;
//        
//        
//    }
//    
    
    
}
