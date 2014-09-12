/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory.MRP;

import Entity.Factory.ProductEntity;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */
@Entity
public class SalsForecastEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long storeId;
    private List<ProductEntity> productList;
    private List<Integer> amount; 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetDateStart;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetDateEnd;
    private String status;
    
        
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    public List<Integer> getAmount() {
        return amount;
    }

    public void setAmount(List<Integer> amount) {
        this.amount = amount;
    }

    public Calendar getTargetDateStart() {
        return targetDateStart;
    }

    public void setTargetDateStart(Calendar targetDateStart) {
        this.targetDateStart = targetDateStart;
    }

    public Calendar getTargetDateEnd() {
        return targetDateEnd;
    }

    public void setTargetDateEnd(Calendar targetDateEnd) {
        this.targetDateEnd = targetDateEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof SalsForecastEntity)) {
            return false;
        }
        SalsForecastEntity other = (SalsForecastEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.MRP.SalsForecastEntity[ id=" + id + " ]";
    }
    
}
