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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productionPlanId;

    private String status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar generateDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar confirmDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetPeriod;
    private Integer quantity;
    private String remark;
    
    //factory product entity -- production plan entity: 1 <-- M
    @ManyToOne(cascade = {CascadeType.ALL})
    private FactoryProductEntity factoryProduct;  
    
    @OneToMany(cascade={CascadeType.ALL},mappedBy="productionPlan")
    private List<WeeklyProductionPlanEntity> weeklyProductionPlanEntity;

    //planned order entity -- production plan entity 1 <--> 1
    @OneToOne(cascade = {CascadeType.PERSIST})
    private PlannedOrderEntity plannedOrder;
    
    
    public ProductionPlanEntity() {
    }

    public ProductionPlanEntity(String status, Calendar generateDate, Calendar targetPeriod, Integer output, FactoryProductEntity product, String remark) {
        this.status = status;
        this.generateDate = generateDate;
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

    public Calendar getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(Calendar generateDate) {
        this.generateDate = generateDate;
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


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer output) {
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
