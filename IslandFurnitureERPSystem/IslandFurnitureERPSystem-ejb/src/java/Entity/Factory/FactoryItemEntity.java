/*
 * FactoryItemEntity.java
 * 
 * This entity is a functional entity.
 * Used as a quntity record of a specific item (RM, retail products, or products) that a specific factory has,
 * which means it either can produce or purchase.
 */
package Entity.Factory;

import Entity.Factory.FacotryBin.FactoryBinStoredProductEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zhengyuan
 */
@Entity
@Table(name = "FactoryItem")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FactoryItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryItemId;
    private Integer quantity;
    
    //factory entity -- factory item entity: 1 <--> M 
    @ManyToOne
    private FactoryEntity factory;
    
    //factory item entity -- factory bin stored product entity: 1 <--> M 
    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy = "factoryItem")
    private Collection<FactoryBinStoredProductEntity> factoryBinStoredProducts = new ArrayList<FactoryBinStoredProductEntity>();
   

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return factoryItemId;
    }

    public void setId(Long factoryItemId) {
        this.factoryItemId = factoryItemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factoryItemId != null ? factoryItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the factoryItemId fields are not set
        if (!(object instanceof FactoryItemEntity)) {
            return false;
        }
        FactoryItemEntity other = (FactoryItemEntity) object;
        if ((this.factoryItemId == null && other.factoryItemId != null) || (this.factoryItemId != null && !this.factoryItemId.equals(other.factoryItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.FactoryInventoryEntity[ factoryItemId=" + factoryItemId + " ]";
    }

}
