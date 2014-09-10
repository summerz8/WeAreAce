/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.MRP;

import Entity.Factory.ProductEntity;
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
    private Calendar targetSalesStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetSalesEndDate;
    private Integer quantity;
    private String remark;
    @ManyToOne(cascade={CascadeType.ALL})
    private ProductEntity product;
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="productionplan")
    private List<PlannedOrderEntity> plannedOrder=new ArrayList();

    public ProductionPlanEntity() {
    }

    
    public ProductionPlanEntity(String status, Calendar generateDate, Calendar targetSalesStartDate, Calendar targetSalesEndDate, Integer quantity, ProductEntity product, String remark){
        this.status = status;
        this.generateDate = generateDate;
        this.targetSalesStartDate = targetSalesStartDate;
        this.targetSalesEndDate = targetSalesEndDate;
        this.quantity = quantity;
        this.product = product;
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

    public Calendar getTargetSalesStartDate() {
        return targetSalesStartDate;
    }

    public void setTargetSalesStartDate(Calendar targetSalesStartDate) {
        this.targetSalesStartDate = targetSalesStartDate;
    }

    public Calendar getTargetSalesEndDate() {
        return targetSalesEndDate;
    }

    public void setTargetSalesEndDate(Calendar targetSalesEndDate) {
        this.targetSalesEndDate = targetSalesEndDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer output) {
        this.quantity = output;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
    
    public String getRemark(){
        return remark;
    }
    
    public void setRemark(String remark){
        this.remark = remark;
    }

    public List<PlannedOrderEntity> getPlannedOrder() {
        return plannedOrder;
    }

    public void setPlannedOrder(List<PlannedOrderEntity> plannedOrder) {
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
