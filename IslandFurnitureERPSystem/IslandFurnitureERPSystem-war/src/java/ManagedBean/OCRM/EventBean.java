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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
    private Date startDate;
    private Date endDate;
    private Date startDate1;
    private Date endDate1;
    private Double bonus;
    private String description;
    private String newEventName;
    private Long eventId;
    private EventEntity event;
    private StoreEventEntity storeEvent;
    private Date startDate2;
    private Date endDate2;
    private String description2;
    private Double bonus2;
    private Double increaseSale;
    private String description1;

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

        eventList = eml.getEventList();
        storeEventList = eml.getStoreEventList(storeId);

        displayList = new ArrayList<>();
        for (EventEntity s : eventList) {
            String t = s.getId() + " " + s.getEventName();
            displayList.add(new SelectItem(s.getId(), t));
        }

    }

    public void createNewStoreEvent() throws IOException {
        Calendar today = Calendar.getInstance();
        System.out.println("startdate :" + startDate1 + "       enddate" + endDate1);
        if (today.getTime().before(startDate1) && startDate1.before(endDate1) && selectedEvent != null) {
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            start.setTime(startDate1);
            end.setTime(endDate1);
            eventId = Long.valueOf(selectedEvent);
            eml.createStoreEvent(eventId, description1, start, end, storeId, bonus);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../Event/Event.xhtml");
//            FacesContext.getCurrentInstance().getExternalContext().redirect();
        } else if ((today.getTime().after(startDate1) || startDate1.after(endDate1)) && selectedEvent != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please enter correct date information!", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please choose a event!", ""));

        }
    }

    public void createNewEvent() throws IOException {
        if (startDate.before(endDate) && selectedEvent != null) {
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            start.setTime(startDate);
            end.setTime(endDate);
            eml.createEvent(newEventName, description, start, end);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../Event/Event.xhtml");
        } else if ((startDate.after(endDate)) && selectedEvent != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please enter correct date information!", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please choose a event!", ""));

        }
    }

    public void theEvent(String selectedEvent) {
        eventId = Long.valueOf(selectedEvent);
        event = eml.getEvent(eventId);
        startDate1 = event.getStartDate().getTime();
        endDate1 = event.getEndDate().getTime();

    }

    public void theStoreEvent(Long storeEventId) {

        storeEvent = eml.getStoreEvent(storeEventId);
        startDate2 = storeEvent.getStartDate().getTime();
        endDate2 = storeEvent.getEndDate().getTime();
        bonus2 = storeEvent.getBonus();
        description2 = storeEvent.getDescription();

    }

    public void editStoreEvent() throws IOException {
        Calendar today = Calendar.getInstance();
        if (today.getTime().before(startDate2) && startDate2.before(endDate2)) {
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            start.setTime(startDate2);
            end.setTime(endDate2);
            eml.editStoreEvent(storeEvent.getId(), description2, start, end, bonus2);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../Event/Event.xhtml");
//            FacesContext.getCurrentInstance().getExternalContext().redirect();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please enter correct date information!", ""));
        } 

    }
    
    public String updateEvent() {
  
            eml.updateStoreEvent(storeEvent.getId(), increaseSale);
            return "Event?faces-redirect=true";
    }

    public String delete(Long eventId) {
        eml.deleteStoreEvent(eventId);
        return "Event?faces-redirect=true";
    }

    public boolean checkbefore(Calendar startDate) {
        Calendar thisDay = Calendar.getInstance();
        return !thisDay.after(startDate);
    }
    
     public boolean checkafter(Calendar endDate) {
        Calendar thisDay = Calendar.getInstance();
        return thisDay.after(endDate);
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getNewEventName() {
        return newEventName;
    }

    public void setNewEventName(String newEventName) {
        this.newEventName = newEventName;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public Date getStartDate1() {
        return startDate1;
    }

    public void setStartDate1(Date startDate1) {
        this.startDate1 = startDate1;
    }

    public Date getEndDate1() {
        return endDate1;
    }

    public void setEndDate1(Date endDate1) {
        this.endDate1 = endDate1;
    }

    public StoreEventEntity getStoreEvent() {
        return storeEvent;
    }

    public void setStoreEvent(StoreEventEntity storeEvent) {
        this.storeEvent = storeEvent;
    }

    public Date getStartDate2() {
        return startDate2;
    }

    public void setStartDate2(Date startDate2) {
        this.startDate2 = startDate2;
    }

    public Date getEndDate2() {
        return endDate2;
    }

    public void setEndDate2(Date endDate2) {
        this.endDate2 = endDate2;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public Double getBonus2() {
        return bonus2;
    }

    public void setBonus2(Double bonus2) {
        this.bonus2 = bonus2;
    }

    public Double getIncreaseSale() {
        return increaseSale;
    }

    public void setIncreaseSale(Double increaseSale) {
        this.increaseSale = increaseSale;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

}
