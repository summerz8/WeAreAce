/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

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
public class FactoryBinRawMaterialEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryID;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String factoryBinID;
    
    private List<Entity> items;
    
    private FactoryEntity factory = new FactoryEntity();

    public Long getFactoryID() {
        return factoryID;
    }

    public void setFactoryID(Long factoryID) {
        this.factoryID = factoryID;
    }
    
    public String getFactoryBinID() {
        return factoryBinID;
    }

    public void setFactoryBinID(String factoryBinID) {
        this.factoryBinID = factoryBinID;
    }
    
    public List<Entity> getItems() {
        return items;
    }

    public void setItems(List<Entity> items) {
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
        return "Entity.Factory.FactoryBinEntity[ factoryBinId=" + factoryBinID + " ]" + "[ factoryId=" + factoryID + " ]";
    }
    
}
