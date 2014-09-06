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
    private Long BomId;
    private Long ProductId;
    private List<RawMaterialAmountEntity> RawMaterialList;
    @OneToMany(cascade={CascadeType.PERSIST})
    private List<RawMaterialAmountEntity> rawmaterialList;
    @OneToOne(mappedBy="bom")
    private ProductEntity product;

    public BOMEntity() {
    }

    public Long getBomId() {
        return BomId;
    }

    public void setBomId(Long BomId) {
        this.BomId = BomId;
    }

       public List<RawMaterialAmountEntity> getRawMaterialList() {
        return RawMaterialList;
    }

    public void setRawMaterialList(List<RawMaterialAmountEntity> RawMaterialList) {
        this.RawMaterialList = RawMaterialList;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long ProductId) {
        this.ProductId = ProductId;
    }

    public List<RawMaterialAmountEntity> getRawmaterialList() {
        return rawmaterialList;
    }

    public void setRawmaterialList(List<RawMaterialAmountEntity> rawmaterialList) {
        this.rawmaterialList = rawmaterialList;
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
        hash += (BomId != null ? BomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the BomId fields are not set
        if (!(object instanceof BOMEntity)) {
            return false;
        }
        BOMEntity other = (BOMEntity) object;
        if ((this.BomId == null && other.BomId != null) || (this.BomId != null && !this.BomId.equals(other.BomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.BOMEntity[ id=" + BomId + " ]";
    }
    
}
