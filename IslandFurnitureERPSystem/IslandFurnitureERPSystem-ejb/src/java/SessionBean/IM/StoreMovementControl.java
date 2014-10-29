/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.IM;

import Entity.Factory.ProductEntity;
import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.IM.StoreInStoreMovementRecordEntity;
import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import java.util.Calendar;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public int createInStoreRecord(Long storeId, Long frombinId, Long tobinId, Long productId, Long retailProductId, Double amount){
        StoreEntity store = em.find(StoreEntity.class, storeId);
        StoreWarehouseBinEntity fromBin = em.find(StoreWarehouseBinEntity.class, frombinId);
        StoreWarehouseBinEntity toBin = em.find(StoreWarehouseBinEntity.class, tobinId);
        StoreInStoreMovementRecordEntity newRecord = new StoreInStoreMovementRecordEntity();
        Boolean isin = false;
        Calendar sendTime = Calendar.getInstance();
        if(retailProductId == null){
        
        StoreProductEntity storeproduct = em.find(StoreProductEntity.class, productId);
        
         newRecord.setStoreProduct(storeproduct);
         newRecord.setStoreRetailProduct(null);
         
        Collection<StoreBinProductEntity> productInfoList  = fromBin.getStoreBinProducts();
        for(StoreBinProductEntity sbpe: productInfoList){
            StoreBinProductEntity binProduct = sbpe;
            if(binProduct.getProduct().equals(storeproduct)){
                isin = true;
                if(binProduct.getQuantity() >= amount){
                    binProduct.setQuantity(binProduct.getQuantity()-amount);
                }
                
            }
            
        }
       
        
        }
        else if(productId == null){
            
            StoreRetailProductEntity storeRetailProduct = em.find(StoreRetailProductEntity.class, retailProductId);
            newRecord.setStoreRetailProduct(storeRetailProduct);
            newRecord.setStoreProduct(null);
        }
        
        newRecord.setAmount(amount);
        newRecord.setFromBin(fromBin);
        newRecord.setToBin(toBin);
        newRecord.setCreationTime(sendTime);
        
        em.persist(newRecord);
        
        
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
    
    
    
}
