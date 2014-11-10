/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Factory.FactoryProductEntity;
import Entity.Store.EventEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreEventEntity;
import java.util.ArrayList;
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
public class EventModule implements EventModuleLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<EventEntity> getEventList() {
        Query q = em.createQuery("SELECT q FROM EventEntity q");
        return (List<EventEntity>) q.getResultList();
    }

    @Override
    public List<StoreEventEntity> getStoreEventList(Long storeId) {
        Query q = em.createQuery("Select s From StoreEventEntity s where s.store.storeId = :sId ");
        q.setParameter("sId", storeId);
        return (List<StoreEventEntity>) q.getResultList();
    }

    @Override
    public void createStoreEvent(String eventName, String description, Calendar startDate, Calendar endDate, StoreEntity store, Double bonus) {
        StoreEventEntity storeEvent=new StoreEventEntity(eventName,description,startDate,endDate,store,bonus);
        em.persist(storeEvent);
        em.flush();
    }

    @Override
    public void deleteEvent(Long eventId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editEvent(String eventName, String description, Calendar startDate, Calendar endDate, StoreEntity store, Double bonus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
