/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.EventEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreEventEntity;
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
        Query q = em.createQuery("Select s From StoreEventEntity s where s.store.storeId = :sId ORDER BY s.startDate DESC ");
        q.setParameter("sId", storeId);
        return (List<StoreEventEntity>) q.getResultList();
    }

    @Override
    public void createStoreEvent(Long eventId, String description, Calendar startDate, Calendar endDate, Long storeId, Double bonus) {
        StoreEntity store = em.find(StoreEntity.class, storeId);
        EventEntity event = em.find(EventEntity.class, eventId);
        StoreEventEntity storeEvent = new StoreEventEntity(event, description, startDate, endDate, store, bonus);
        em.persist(storeEvent);
        em.flush();
    }

    @Override
    public void createEvent(String eventName, String description, Calendar startDate, Calendar endDate) {
        EventEntity event = new EventEntity(eventName, description, startDate, endDate);
        em.persist(event);
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

    @Override
    public EventEntity getEvent(Long eventId) {
        return em.find(EventEntity.class, eventId);
    }

    @Override
    public StoreEventEntity getStoreEvent(Long storeEventId) {
        return em.find(StoreEventEntity.class, storeEventId);
    }

    @Override
    public void deleteStoreEvent(Long storeEventId) {
        StoreEventEntity storeEvent = em.find(StoreEventEntity.class, storeEventId);
        EventEntity event = storeEvent.getEvent();
        for (StoreEventEntity s : event.getStoreEvent()) {
            if (s.getId().equals(storeEvent.getId())) {
                event.getStoreEvent().remove(s);
                break;
            }
        }
        em.remove(storeEvent);
        em.flush();
    }

    @Override
    public void editStoreEvent(Long storeEventId, String description, Calendar startDate, Calendar endDate, Double bonus) {
        StoreEventEntity s = em.find(StoreEventEntity.class, storeEventId);
        s.setBonus(bonus);
        s.setDescription(description);
        s.setEndDate(endDate);
        s.setStartDate(startDate);
        em.flush();
    }

    @Override
    public void updateStoreEvent(Long storeEventId, Double increaseSale) {
        StoreEventEntity s = em.find(StoreEventEntity.class, storeEventId);
        s.setIncreaseSale(increaseSale);

    }
}
