/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Yoky
 */
@Entity
@Table(name="BOM")
public class BOMEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BOMId;
    private String unit;
    private Double amount;
   // Raw material entity ---- bom : 1 <--> M
    @ManyToOne
    private RawMaterialEntity rawMaterial;
    
    //product entity  ---- bom:   1 <------> M
    @ManyToOne
    private ProductEntity product;
    

    public BOMEntity() {
    }
    
    public BOMEntity(RawMaterialEntity rawMaterial, String unit, Double amount, ProductEntity product){
        this.amount=amount;
        this.rawMaterial=rawMaterial;
        this.unit=unit;
        this.product=product;
    }
    public Long getBOMId() {
        return BOMId;
    }

    public void setBOMId(Long BOMId) {
        this.BOMId = BOMId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public RawMaterialEntity getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterialEntity rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (BOMId != null ? BOMId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the BOMId fields are not set
        if (!(object instanceof BOMEntity)) {
            return false;
        }
        BOMEntity other = (BOMEntity) object;
        if ((this.BOMId == null && other.BOMId != null) || (this.BOMId != null && !this.BOMId.equals(other.BOMId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.BOMEntity[ id=" + BOMId + " ]";
    }
    
}
