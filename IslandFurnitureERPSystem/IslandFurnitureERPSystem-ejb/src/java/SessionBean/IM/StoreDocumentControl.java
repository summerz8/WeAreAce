/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.IM;

import Entity.Factory.SCM.DeliveryOrderEntity;
import Entity.Store.IM.StoreGoodReceiptEntity;
import Entity.Store.IM.StoreInStoreMovementRecordEntity;
import Entity.Store.IM.StoreInboundRecordEntity;
import Entity.Store.IM.StoreOutboundRecordEntity;
import Entity.Store.ReturnedItemMovementRecordEntity;
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
    
    
        @Override
    public List<ReturnedItemMovementRecordEntity> getToProcessReturnIMRE(Long storeId){
        String status = "processing";
        System.out.println("getToProcessReturnIMRE");
        Query q = em.createQuery("Select r From ReturnedItemMovementRecordEntity r Where r.status = :stt and r.storeProduct.store.storeId = :sId");
        q.setParameter("stt", status);
        q.setParameter("sId", storeId);
        List<ReturnedItemMovementRecordEntity> resultList = new ArrayList<>();
        for(Object o : q.getResultList()){
            ReturnedItemMovementRecordEntity r = (ReturnedItemMovementRecordEntity) o;
            resultList.add(r);
            
            
        }
        System.out.println("getToProcessReturnIMRE list size " + resultList.size());
        return resultList;
        
    }
    
    
    
   
        @Override
    public List<DeliveryOrderEntity> getDeliveryOrder(Long purchaseOrderId){
        Query q = em.createQuery("Select doe From DeliveryOrderEntity doe Where doe.purchaseOrder.id = :poId");
        q.setParameter("poId", purchaseOrderId);
        List<DeliveryOrderEntity> doList = new ArrayList<>();
        for(Object o : q.getResultList()){
            
            DeliveryOrderEntity doe = (DeliveryOrderEntity) o;
            doList.add(doe);
            
        }
        
        return doList;

    }
    
    
    
    
        
}
