/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.MRP;

import Entity.Factory.ProductEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private Date generateDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date confirmDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date targetSalesStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date targetSalesEndDate;
    private Integer output;
    private String remark;
    @ManyToOne(cascade={CascadeType.ALL})
    private ProductEntity product;
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="productionplan")
    private List<PlannedOrderEntity> plannedOrder=new ArrayList<PlannedOrderEntity>();
    
    
    public ProductionPlanEntity(String status, Date generateDate, Date targetSalesStartDate, Date targetSalesEndDate, Integer output, ProductEntity product, String remark){
        this.status = status;
        this.generateDate = generateDate;
        this.targetSalesStartDate = targetSalesStartDate;
        this.targetSalesEndDate = targetSalesEndDate;
        this.output = output;
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

    public Date getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(Date generateDate) {
        this.generateDate = generateDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Date getTargetSalesStartDate() {
        return targetSalesStartDate;
    }

    public void setTargetSalesStartDate(Date targetSalesStartDate) {
        this.targetSalesStartDate = targetSalesStartDate;
    }

    public Date getTargetSalesEndDate() {
        return targetSalesEndDate;
    }

    public void setTargetSalesEndDate(Date targetSalesEndDate) {
        this.targetSalesEndDate = targetSalesEndDate;
    }

    public Integer getOutput() {
        return output;
    }

    public void setOutput(Integer output) {
        this.output = output;
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
