/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long BOMId;
    private Long productId;
    
    @OneToMany(cascade={CascadeType.PERSIST})
    private List<FactoryRawMaterialAmountEntity> rawMaterialList;
    @OneToOne(mappedBy="bom")
    private ProductEntity product;

    public BOMEntity() {
    }

    public Long getBOMId() {
        return BOMId;
    }

    public void setBOMId(Long BOMId) {
        this.BOMId = BOMId;
    }

       public List<FactoryRawMaterialAmountEntity> getRawMaterialList() {
        return rawMaterialList;
    }

    public void setRawMaterialList(List<FactoryRawMaterialAmountEntity> rawMaterialList) {
        this.rawMaterialList = rawMaterialList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long ProductId) {
        this.productId = ProductId;
    }

    public List<FactoryRawMaterialAmountEntity> getRawmaterialList() {
        return rawMaterialList;
    }

    public void setRawmaterialList(List<FactoryRawMaterialAmountEntity> rawmaterialList) {
        this.rawMaterialList = rawmaterialList;
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
