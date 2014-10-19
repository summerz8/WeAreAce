/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.ReturnedItemMovementRecordEntity;
import Entity.Store.StoreProductEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateless
public class PostSaleService implements PostSaleServiceLocal {

     @PersistenceContext
    private EntityManager em;
     
    
     @Override
    public List<ReturnedItemMovementRecordEntity> ListAllRecords(Long storeId){
        Query q = em.createQuery("SELECT po FROM ReturnedItemMovementRecordEntity po ORDER BY po.createdDate DESC");
        return (List<ReturnedItemMovementRecordEntity>) q.getResultList();
        
    }
    
     @Override
    public ReturnedItemMovementRecordEntity createRecord(String type,Long storeId,Long storeProductId,String description,Long MemberId){
        ReturnedItemMovementRecordEntity newRecord=new ReturnedItemMovementRecordEntity();
        newRecord.setType(type);
        newRecord.setStatus("Processing");
//        Calendar createdTime=Calendar.getInstance();
//        newRecord.setCreatedDate(createdTime);
        newRecord.setDescription(description);
        newRecord.setStoreProduct(em.find(StoreProductEntity.class, storeProductId));
        newRecord.setMemberId(MemberId);
        em.persist(newRecord);
        em.flush();
        
                System.out.println("66666");

        return newRecord;
        
    }


}
