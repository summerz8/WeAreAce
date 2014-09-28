/*
 * FactoryBinEntity.java
 * This is a real entity for storage bin in the factory.
 * It's unqiue in each factory and can store more than 1 item in it.
 */
package Entity.Factory.FactoryBin;

import Entity.Factory.FactoryEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author zhangshiyu
 */
@Entity(name="FactoryBin")
public class FactoryBinEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long factoryBinId; 

    //factory bin entity -- factory bin stroed products entity: 1 <--> M 
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "factoryBin")
    private Collection<FactoryBinStoredProductEntity> factoryBinStoredProducts = new ArrayList<>();
    
    @ManyToOne
    FactoryEntity factory = new FactoryEntity();
    
    public FactoryBinEntity(){
    }
    
    public Long getFactoryBinId() {
        return factoryBinId;
    }

    public void setFactoryBinId(Long factoryBinId) {
        this.factoryBinId = factoryBinId;
    }
    
    
    public FactoryEntity getFactory(){
        return factory;
    }
    
    public void setFactory(FactoryEntity factory){
        this.factory = factory;
    }

    public Collection<FactoryBinStoredProductEntity> getFactoryBinStoredProducts() {
        return factoryBinStoredProducts;
    }

    public void setFactoryBinStoredProducts(Collection<FactoryBinStoredProductEntity> factoryBinStoredProducts) {
        this.factoryBinStoredProducts = factoryBinStoredProducts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factoryBinId != null ? factoryBinId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the factoryBinId fields are not set
        if (!(object instanceof FactoryBinEntity)) {
            return false;
        }
        FactoryBinEntity other = (FactoryBinEntity) object;
        if ((this.factoryBinId == null && other.factoryBinId != null) || (this.factoryBinId != null && !this.factoryBinId.equals(other.factoryBinId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.FacotryBin.FactoryBinEntity[ id=" + factoryBinId + " ]";
    }

}
