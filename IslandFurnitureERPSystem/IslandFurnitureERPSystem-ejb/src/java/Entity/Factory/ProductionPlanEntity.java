/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author zhangshiyu
 */
@Entity
public class ProductionPlanEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productionPlanId;

    private String status;
    private Date generateDate;
    private Date confirmDate;
    private Date targetSalesStartDate;
    private Date targetSalesEndDate;
    private Integer output;

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
