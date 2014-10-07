/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dan
 */
@Entity
@Table(name="VoucherEntity")
public class VoucherEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VoucherId;
    private String name;
    private String description;
    private Double value; 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar expireDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar usageDate;
    private Boolean deleteFlag;
    private Boolean fulfillFlag;

    public VoucherEntity() {
    }

    public VoucherEntity(String name, String description, Double value, Calendar expireDate, 
            Calendar usageDate, Boolean deleteFlag, Boolean fulfillFlag) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.expireDate = expireDate;
        this.usageDate = usageDate;
        this.deleteFlag = deleteFlag;
        this.fulfillFlag = fulfillFlag;
    }

    
    
    

    public Long getVoucherId() {
        return VoucherId;
    }

    public void setVoucherId(Long VoucherId) {
        this.VoucherId = VoucherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Calendar getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Calendar expireDate) {
        this.expireDate = expireDate;
    }

    public Calendar getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(Calendar usageDate) {
        this.usageDate = usageDate;
    }
    
    

    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Boolean isFulfillFlag() {
        return fulfillFlag;
    }

    public void setFulfillFlag(Boolean fulfillFlag) {
        this.fulfillFlag = fulfillFlag;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (VoucherId != null ? VoucherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoucherEntity)) {
            return false;
        }
        VoucherEntity other = (VoucherEntity) object;
        if ((this.VoucherId == null && other.VoucherId != null) || (this.VoucherId != null && !this.VoucherId.equals(other.VoucherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.VoucherEntity[ id=" + VoucherId + " ]";
    }
    
}
