/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory.MRP;

import Entity.Factory.FactoryProductAmountEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Store.StoreEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */

@Entity
@Table(name = "SalesForecast")
public class SalesForecastEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private StoreEntity store;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetPeriod;
    private String status; // unconfirmed, confirmed
    //factory product amount entity -- sales forecast entity M <-- 1
    @OneToMany(cascade={CascadeType.PERSIST})
    private List<FactoryProductAmountEntity> factoryProductList = new ArrayList<>();

    //factory product amount entity -- sales forecast entity M <-- 1
    @OneToMany(cascade={CascadeType.PERSIST})
    private List<FactoryRetailProductAmountEntity> factoryRetailProductList = new ArrayList<>();
    
    @ManyToOne
    private SalesOperationPlanEntity salesOperationPlan;
    
    private Double tempProductAmount;
    private Double tempRetailAmount;
     
    public SalesForecastEntity(){
    }

    public SalesForecastEntity(StoreEntity store, Calendar targetPeriod) {
        this.store = store;
        this.targetPeriod = targetPeriod;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public List<FactoryProductAmountEntity> getFactoryProductList() {
        return factoryProductList;
    }

    public void setFactoryProductList(List<FactoryProductAmountEntity> productList) {
        this.factoryProductList = productList;
    }

    public Calendar getTargetPeriod() {
        return targetPeriod;
    }

    public void setTargetPeriod(Calendar targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public SalesOperationPlanEntity getSalesOperationPlan() {
        return salesOperationPlan;
    }

    public void setSalesOperationPlan(SalesOperationPlanEntity salesOperationPlan) {
        this.salesOperationPlan = salesOperationPlan;
    }

    public List<FactoryRetailProductAmountEntity> getFactoryRetailProductList() {
        return factoryRetailProductList;
    }

    public void setFactoryRetailProductList(List<FactoryRetailProductAmountEntity> factoryretailProductList) {
        this.factoryRetailProductList = factoryretailProductList;
    }

    public Double getTempProductAmount() {
        return tempProductAmount;
    }

    public void setTempProductAmount(Double tempProductAmount) {
        this.tempProductAmount = tempProductAmount;
    }

    public Double getTempRetailAmount() {
        return tempRetailAmount;
    }

    public void setTempRetailAmount(Double tempRetailAmount) {
        this.tempRetailAmount = tempRetailAmount;
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
        if (!(object instanceof SalesForecastEntity)) {
            return false;
        }
        SalesForecastEntity other = (SalesForecastEntity) object;
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
