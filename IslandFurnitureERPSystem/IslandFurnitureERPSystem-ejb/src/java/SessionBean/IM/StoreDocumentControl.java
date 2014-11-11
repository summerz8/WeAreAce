/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.IM;

import Entity.Store.IM.StoreGoodReceiptEntity;
import Entity.Store.IM.StoreInStoreMovementRecordEntity;
import Entity.Store.IM.StoreInboundRecordEntity;
import Entity.Store.IM.StoreOutboundRecordEntity;
import java.util.ArrayList;
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
public class StoreDocumentControl implements StoreDocumentControlLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
        @PersistenceContext
    private EntityManager em;

    public StoreDocumentControl() {
    }
        
   @Override
   public List<StoreGoodReceiptEntity> getStoreGoodReceipt(Long storeId){
        List<StoreGoodReceiptEntity> grList = new ArrayList<>();
        Query q = em.createQuery("Select gr From StoreGoodReceiptEntity gr Where gr.se.storeId = :sId ORDER BY gr.creationTime DESC");
        q.setParameter("sId", storeId);
        for(Object o: q.getResultList()){
            StoreGoodReceiptEntity gre = (StoreGoodReceiptEntity) o;
            grList.add(gre);
            
        }
        
        return grList;
    }
    
        @Override
    public List<StoreInboundRecordEntity> getStoreInboundRecordEntity(Long storeId){
        List<StoreInboundRecordEntity> grList = new ArrayList<>();
        Query q = em.createQuery("Select ir From StoreInboundRecordEntity ir Where ir.store.storeId = :sId ORDER BY ir.creationTime DESC");
        q.setParameter("sId", storeId);
        for(Object o: q.getResultList()){
            StoreInboundRecordEntity ire = (StoreInboundRecordEntity) o;
            grList.add(ire);  
        }
        
        return grList;
 
    }
    
    
   @Override
    public List<StoreInStoreMovementRecordEntity> getStoreInStoreMovementRecord(Long storeId){
        
        List<StoreInStoreMovementRecordEntity> list = new ArrayList<>();
        Query q = em.createQuery("Select ir From StoreInStoreMovementRecordEntity ir Where ir.store.storeId = :sId ORDER BY ir.creationTime DESC");
        q.setParameter("sId", storeId);
        for(Object o: q.getResultList()){
            StoreInStoreMovementRecordEntity ire = (StoreInStoreMovementRecordEntity) o;
            list.add(ire);  
        }
        
        return list;
        
        
    }
    
    @Override
    public List<StoreOutboundRecordEntity> getStoreOutboundRecord(Long storeId){
         List<StoreOutboundRecordEntity> list = new ArrayList<>();
        Query q = em.createQuery("Select ir From StoreOutboundRecordEntity ir Where ir.store.storeId = :sId ORDER BY ir.creationTime DESC");
        q.setParameter("sId", storeId);
        for(Object o: q.getResultList()){
            StoreOutboundRecordEntity ire = (StoreOutboundRecordEntity) o;
            list.add(ire);  
        }
        
        return list;
   
        
    }
    
    
    
    
    
        
}
