/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.IM;

import Entity.Factory.SCM.OutboundMovementEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author zhengyuan
 */
@Entity
public class StoreGoodReceiptEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Integer inventoryType; //0 Product 1 Retail Product
    
    @ManyToOne
    private StoreEntity se;
    
    @ManyToOne
    private StoreProductEntity spe;
    
    @ManyToOne
    private StoreRetailProductEntity srpe;
    
    private Double receivedAmount;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar creationTime;
    
    @OneToOne
    private OutboundMovementEntity ome = null;
    
    
    
    public StoreGoodReceiptEntity(){
        
        
    }
    
    public StoreGoodReceiptEntity (Integer inventoryType, Double receivedAmount, Calendar creationTime){
        
        this.inventoryType = inventoryType;
        this.receivedAmount = receivedAmount;
        this.creationTime = creationTime;
        
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OutboundMovementEntity getOme() {
        return ome;
    }

    public void setOme(OutboundMovementEntity ome) {
        this.ome = ome;
    }

    
     
    
    public Integer getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(Integer inventoryType) {
        this.inventoryType = inventoryType;
    }

    public StoreProductEntity getSpe() {
        return spe;
    }

    public void setSpe(StoreProductEntity spe) {
        this.spe = spe;
    }

    public StoreRetailProductEntity getSrpe() {
        return srpe;
    }

    public void setSrpe(StoreRetailProductEntity srpe) {
        this.srpe = srpe;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public StoreEntity getSe() {
        return se;
    }

    public void setSe(StoreEntity se) {
        this.se = se;
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
        if (!(object instanceof StoreGoodReceiptEntity)) {
            return false;
        }
        StoreGoodReceiptEntity other = (StoreGoodReceiptEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.StoreGoodReceiptEntity[ id=" + id + " ]";
    }
    
}
