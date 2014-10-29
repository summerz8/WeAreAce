package Entity.Factory.MRP;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import java.io.Serializable;
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
@Table(name = "PlannedOrder")
public class PlannedOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plannedOrderId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar generatedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetPeriod;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar confirmDate;
    private String status;//unconfirmed, confirmed
    
    //raw mterial amount entity -- planned order entity : M <-- M
    @OneToMany(cascade = {CascadeType.PERSIST})
    private List<FactoryRawMaterialAmountEntity> factoryRawMaterialAmountList=null;
    
  
    
    //planned order entity -- production plan entity 1 <--> 1
    @OneToOne(cascade = {CascadeType.PERSIST})
    private ProductionPlanEntity productionPlan;
    
    //planned order entity -- factory 
    @ManyToOne
    private FactoryEntity factory;

    public PlannedOrderEntity() {
    }


    public void createPlannedOrder(Calendar date,
                                   Calendar targetDate,
                                   String status,
                                   ProductionPlanEntity productionPlan, 
                                   List<FactoryRawMaterialAmountEntity> rawMaterialList,
                                   FactoryEntity factory) {
        this.generatedDate=date;
        this.targetPeriod=targetDate;
        this.status=status;
        this.productionPlan=productionPlan;
        this.factoryRawMaterialAmountList= rawMaterialList;
        this.factory=factory;
            }

    public Long getPlannedOrderId() {
        return plannedOrderId;
    }

    public void setPlannedOrderId(Long plannedOrderId) {
        this.plannedOrderId = plannedOrderId;
    }

    public Calendar getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Calendar generatedDate) {
        this.generatedDate = generatedDate;
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

    public Calendar getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Calendar confirmDate) {
        this.confirmDate = confirmDate;
    }
    
    

    public List<FactoryRawMaterialAmountEntity> getFactoryRawMaterialAmountList() {
        return factoryRawMaterialAmountList;
    }

    public void setFactoryRawMaterialAmountList(List<FactoryRawMaterialAmountEntity> factoryRawMaterialAmountList) {
        this.factoryRawMaterialAmountList = factoryRawMaterialAmountList;
    }

    public ProductionPlanEntity getProductionPlan() {
        return productionPlan;
    }

    public void setProductionPlan(ProductionPlanEntity productionPlan) {
        this.productionPlan = productionPlan;
    }

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factory) {
        this.factory = factory;
    }

  
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plannedOrderId != null ? plannedOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlannedOrderEntity)) {
            return false;
        }
        PlannedOrderEntity other = (PlannedOrderEntity) object;
        if ((this.plannedOrderId == null && other.plannedOrderId != null) || (this.plannedOrderId != null && !this.plannedOrderId.equals(other.plannedOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.PlannedOrderEntity[ id=" + plannedOrderId + " ]";
    }

}
