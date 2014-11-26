/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Store;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */
@Entity
public class EventEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar endDate;
    private String description;

    @OneToMany
    private List<StoreEventEntity> storeEvent;
    
    public EventEntity(){
    }
    
    public EventEntity(String eventName,String description, Calendar startDate, Calendar endDate){
        this.description=description;
        this.endDate=endDate;
        this.eventName=eventName;
        this.startDate=startDate;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStoreEvent(List<StoreEventEntity> storeEvent) {
        this.storeEvent = storeEvent;
    }

 
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<StoreEventEntity> getStoreEvent() {
        return storeEvent;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventEntity)) {
            return false;
        }
        EventEntity other = (EventEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.EventCategoryEntity[ id=" + id + " ]";
    }

}
