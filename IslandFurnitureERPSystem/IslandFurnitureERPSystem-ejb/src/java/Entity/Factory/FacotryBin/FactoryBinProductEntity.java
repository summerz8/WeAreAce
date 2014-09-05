/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory.FacotryBin;

import Entity.Factory.FactoryEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author hangsun
 */
@Entity
public class FactoryBinProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryId;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String factoryBinId;
 
    private List<StoredProductEntity> items;
    //@ManyToOne?
    private FactoryEntity factory = new FactoryEntity();

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }
    
    public String getFactoryBinId() {
        return factoryBinId;
    }

    public void setFactoryBinId(String factoryBinId) {
        this.factoryBinId = factoryBinId;
    }
    
    public List<StoredProductEntity> getItems() {
        return items;
    }

    public void setItems(List<StoredProductEntity> items) {
        this.items = items;
    }
    
    @ManyToOne
    public FactoryEntity getFactory(){
         return factory;
    }
    
    public void setFactory(FactoryEntity factory){
         this.factory = factory;
    }
    
    @Override
    public String toString() {
        return "Entity.Factory.FactoryBinEntity[ factoryBinId=" + factoryBinId + " ]" + "[ factoryId=" + factoryId + " ]";
    }
    
}
