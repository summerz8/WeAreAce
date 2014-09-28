/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory.MRP;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "IntegratedSalesForecast")
public class IntegratedSalesForecastEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    Calendar targetPeriod;
    
    //factory product entity -- integrated sales forecast entity   1 <-- M
    @ManyToOne
    private FactoryProductEntity factoryProduct;
    
 
    //factory  entity -- integrated sales forecast entity   1 <-- M
    @ManyToOne
    private FactoryEntity factory;
    
    //factory retail product entity -- integrated sales forecast entity   1 <-- M
    @ManyToOne
    private FactoryRetailProductEntity factoryRetailProduct;
    
    //sales forecast list -- integrated sales forcast   M <-- M
    @ManyToMany(cascade={CascadeType.PERSIST})
    @JoinTable(name="INTEGRATEDSALESFORECAST_SALESFORECAST")
    private List<SalesForecastEntity> salesForecastList = new ArrayList<>(); 
    
     // sales forecast entity --- sales operation plan entity  1 <---> 1
    @OneToOne(mappedBy="integratedSalesForecast")
    private SalesOperationPlanEntity salesOperationPlan;
    private Double amount;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getTargetPeriod() {
        return targetPeriod;
    }

    public void setTargetPeriod(Calendar targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    public FactoryProductEntity getFactoryProduct() {
        return factoryProduct;
    }

    public FactoryRetailProductEntity getFactoryRetailProduct() {
        return factoryRetailProduct;
    }

    public void setFactoryRetailProduct(FactoryRetailProductEntity factoryRetailProduct) {
        this.factoryRetailProduct = factoryRetailProduct;
    }
   

    public void setFactoryProduct(FactoryProductEntity factoryProduct) {
        this.factoryProduct = factoryProduct;
    }

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factory) {
        this.factory = factory;
    }

    public List<SalesForecastEntity> getSalesForecastList() {
        return salesForecastList;
    }

    public void setSalesForecastList(List<SalesForecastEntity> salesForecastList) {
        this.salesForecastList = salesForecastList;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public SalesOperationPlanEntity getSalesOperationPlan() {
        return salesOperationPlan;
    }

    public void setSalesOperationPlan(SalesOperationPlanEntity salesOperationPlan) {
        this.salesOperationPlan = salesOperationPlan;
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
        if (!(object instanceof IntegratedSalesForecastEntity)) {
            return false;
        }
        IntegratedSalesForecastEntity other = (IntegratedSalesForecastEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.MRP.IntegratedSalesForecastEntity[ id=" + id + " ]";
    }
    
}
