/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Factory.SetEntity;
import Entity.Store.EventEntity;
import Entity.Store.StoreEventEntity;
import SessionBean.OCRM.EventModuleLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "eventBean")
@ViewScoped
public class EventBean {

    private List<StoreEventEntity> storeEventList;
    private List<EventEntity> eventList;
    private Long storeId;
    private List<SelectItem> displayList;
    private String selectedEvent;

    @EJB
    EventModuleLocal eml;

    public EventBean() {
    }

    @PostConstruct
    public void init() {
        if ((int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Userlvl") == 0) {
            storeId = -1L;

        } else {
            storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        }
        
        eventList=eml.getEventList();
        storeEventList = eml.getStoreEventList(storeId);

        displayList = new ArrayList<>();
        for (EventEntity s : eventList) {
            String t = s.getId() + " " + s.getEventName();
            displayList.add(new SelectItem(s.getId(), t));
        }

    }
    
    public String createNewStoreEvent(){
        Long eventId=Long.valueOf(selectedEvent);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("eventId",eventId);

        return "createNewStoreEvent?faces-redirect=true";
    }

    public List<StoreEventEntity> getStoreEventList() {
        return storeEventList;
    }

    public void setStoreEventList(List<StoreEventEntity> storeEventList) {
        this.storeEventList = storeEventList;
    }

    public List<EventEntity> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventEntity> eventList) {
        this.eventList = eventList;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<SelectItem> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<SelectItem> displayList) {
        this.displayList = displayList;
    }

    public String getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(String selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public EventModuleLocal getEml() {
        return eml;
    }

    public void setEml(EventModuleLocal eml) {
        this.eml = eml;
    }
    
    

}
