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
 * @author zhangshiyu
 */
@Entity
@Table(name = "ProductionPlan")
public class ProductionPlanEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productionPlanId;

    private String status;// unconfirmed, confirmed, cancelled
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar generatedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar confirmDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetPeriod;
    private Double quantity;
    private String remark;
    
    //factory product entity -- production plan entity: 1 <-- M
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private FactoryProductEntity factoryProduct;  
    
    //weekly production plan entity  --  production plan: M <--> 1
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy="productionPlan")
    private List<WeeklyProductionPlanEntity> weeklyProductionPlanEntity = new ArrayList<>() ;

    //planned order entity -- production plan entity 1 <--> 1
    @OneToOne(cascade = {CascadeType.PERSIST},mappedBy="productionPlan")
    private PlannedOrderEntity plannedOrder;
    
    
    public ProductionPlanEntity() {
    }

    public ProductionPlanEntity(String status, Calendar generatedDate, Calendar targetPeriod, Double output, FactoryProductEntity product, String remark) {
        this.status = status;
        this.generatedDate = generatedDate;
        this.targetPeriod = targetPeriod;
        this.quantity = output;
        this.factoryProduct = product;
        this.remark = remark;
    }

    public Long getProductionPlanId() {
        return productionPlanId;
    }

    public void setProductionPlanId(Long productionPlanId) {
        this.productionPlanId = productionPlanId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Calendar getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Calendar generatedDate) {
        this.generatedDate = generatedDate;
    }

    public Calendar getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Calendar confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Calendar getTargetPeriod(){
        return targetPeriod;
    }

    public void setTargetPeriod(Calendar targetPeriod) {
        this.targetPeriod=targetPeriod;
    }


    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double output) {
        this.quantity = output;
    }

    public FactoryProductEntity getProduct() {
        return factoryProduct;
    }

    public void setProduct(FactoryProductEntity product) {
        this.factoryProduct = product;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<WeeklyProductionPlanEntity> getWeeklyProductionPlanEntity() {
        return weeklyProductionPlanEntity;
    }

    public void setWeeklyProductionPlanEntity(List<WeeklyProductionPlanEntity> weeklyProductionPlanEntity) {
        this.weeklyProductionPlanEntity = weeklyProductionPlanEntity;
    }

    public FactoryProductEntity getFactoryProduct() {
        return factoryProduct;
    }

    public void setFactoryProduct(FactoryProductEntity factoryProduct) {
        this.factoryProduct = factoryProduct;
    }

    public PlannedOrderEntity getPlannedOrder() {
        return plannedOrder;
    }

    public void setPlannedOrder(PlannedOrderEntity plannedOrder) {
        this.plannedOrder = plannedOrder;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productionPlanId != null ? productionPlanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductionPlanEntity)) {
            return false;
        }
        ProductionPlanEntity other = (ProductionPlanEntity) object;
        if ((this.productionPlanId == null && other.productionPlanId != null) || (this.productionPlanId != null && !this.productionPlanId.equals(other.productionPlanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.ProductionPlanEntity[ id=" + productionPlanId + " ]";
    }

}
