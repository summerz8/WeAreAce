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
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface EventModuleLocal {
    
    public List<EventEntity> getEventList();
    
    public List<StoreEventEntity> getStoreEventList(Long storeId);
    
    public void createStoreEvent(String eventName,String description, Calendar startDate, Calendar endDate, StoreEntity store,Double bonus);
    
    public void deleteEvent(Long eventId);
    
    public void editEvent(String eventName,String description, Calendar startDate, Calendar endDate, StoreEntity store,Double bonus);
    
    
}
