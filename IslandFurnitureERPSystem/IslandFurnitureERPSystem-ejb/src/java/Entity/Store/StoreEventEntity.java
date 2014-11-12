/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author dan
 */
@Entity
public class StoreEventEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String eventName;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar endDate;
    @ManyToOne
    private StoreEntity store;
    private Double bonus;
    private Double increaseSale;
    @ManyToOne
    private EventEntity event;

    public StoreEventEntity(){
    }
    
    public StoreEventEntity(EventEntity event,String description, Calendar startDate, Calendar endDate, StoreEntity store,Double bonus){
        this.event=event;
        this.eventName=event.getEventName();
        this.description=description;
        this.startDate=startDate;
        this.endDate=endDate;
        this.bonus=bonus;
        this.endDate=endDate;
        this.store=store;
        this.increaseSale=1D;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public Double getIncreaseSale() {
        return increaseSale;
    }

    public void setIncreaseSale(Double increaseSale) {
        this.increaseSale = increaseSale;
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
        if (!(object instanceof StoreEventEntity)) {
            return false;
        }
        StoreEventEntity other = (StoreEventEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.StoreEvent[ id=" + id + " ]";
    }
    
}
