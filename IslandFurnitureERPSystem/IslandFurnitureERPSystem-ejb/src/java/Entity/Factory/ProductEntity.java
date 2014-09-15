/*
 * ProductEntity.java
 * 
 * This is a real entity.
 * Used for product entity, which is the factory self produced prodcuct.
 */
package Entity.Factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
@Table(name = "Product")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String name;
    private String description;
    private double price; // ???
    private String unit;
    private Boolean deleteFlag;
    
    //product entity -- bom entity: 1 <--> 1
    @OneToOne(cascade={CascadeType.PERSIST})
    public BOMEntity bom;

    //product entity -- factory product entity: 1<--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "product")
    private Collection<FactoryProductEntity> factoryProduct = new ArrayList<FactoryProductEntity>();
  
    public ProductEntity() {
    }

    public ProductEntity(String name, String description, double price, String unit, BOMEntity bom, Boolean deleteFlag) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit = unit;
        this.bom = bom;
        this.deleteFlag = deleteFlag;
    }

    
    public Long getProductId() {
        return productId;
    }

    public void setProductEntity(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BOMEntity getBom() {
        return bom;
    }

    public void setBom(BOMEntity bom) {
        this.bom = bom;
    }

    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    
    public Collection<FactoryProductEntity> getFactoryProduct(){
        return factoryProduct;
    }
    
    public void setFactoryProduct(Collection<FactoryProductEntity> factoryProduct){
        this.factoryProduct = factoryProduct;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the productEntity fields are not set
        if (!(object instanceof ProductEntity)) {
            return false;
        }
        ProductEntity other = (ProductEntity) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProductEntity[ id=" + productId + " ]";
    }

}
