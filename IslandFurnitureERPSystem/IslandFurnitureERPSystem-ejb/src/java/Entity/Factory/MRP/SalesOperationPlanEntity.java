/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory.MRP;

import Entity.Factory.FactoryProductEntity;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "SalesOperationPlan")
public class SalesOperationPlanEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetPeriod;
    private Double plannedEndMonthInventory;
    private Integer workingDay;
    
    // factory product entity --- sales operation plan entity   1 <--- M
    @ManyToOne
    private FactoryProductEntity factoryProduct;
  
    // production plan entity --- sales operation plan entity 1 <--- 1
    @OneToOne(cascade={CascadeType.PERSIST})
    private ProductionPlanEntity productionPlan;
    
    // sales forecast entity --- sales operation plan entity  M <--- 1
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy="salesOperationPlan")
    private List<SalesForecastEntity> salesForecast = new ArrayList<>();
    

    public SalesOperationPlanEntity(){
    }
    
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

    public void setFactoryProduct(FactoryProductEntity factoryProduct) {
        this.factoryProduct = factoryProduct;
    }

    public ProductionPlanEntity getProductionPlan() {
        return productionPlan;
    }

    public void setProductionPlan(ProductionPlanEntity productionPlan) {
        this.productionPlan = productionPlan;
    }

    public List<SalesForecastEntity> getSalesForecast() {
        return salesForecast;
    }

    public void setSalesForecast(List<SalesForecastEntity> salesForecast) {
        this.salesForecast = salesForecast;
    }

    public Double getPlannedEndMonthInventory() {
        return plannedEndMonthInventory;
    }

    public void setPlannedEndMonthInventory(Double plannedEndMonthInventory) {
        this.plannedEndMonthInventory = plannedEndMonthInventory;
    }

    public Integer getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(Integer workingDay) {
        this.workingDay = workingDay;
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
        if (!(object instanceof SalesOperationPlanEntity)) {
            return false;
        }
        SalesOperationPlanEntity other = (SalesOperationPlanEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.MRP.DemandManagementEntity[ id=" + id + " ]";
    }
    
}
