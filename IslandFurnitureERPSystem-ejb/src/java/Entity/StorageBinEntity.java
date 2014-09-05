/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Yoky
 */
@Entity
public class StorageBinEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long storageBinId;
    private FactoryEntity factory;
    private List<MaterialEntity> materials;

    public StorageBinEntity() {
    }

    public Long getId() {
        return storageBinId;
    }

    public void setId(Long id) {
        this.storageBinId = id;
    }

    public Long getStorageBinId() {
        return storageBinId;
    }

    public void setStorageBinId(Long storageBinId) {
        this.storageBinId = storageBinId;
    }

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factory) {
        this.factory = factory;
    }

    public List<MaterialEntity> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialEntity> materials) {
        this.materials = materials;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storageBinId != null ? storageBinId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the storageBinId fields are not set
        if (!(object instanceof StorageBinEntity)) {
            return false;
        }
        StorageBinEntity other = (StorageBinEntity) object;
        if ((this.storageBinId == null && other.storageBinId != null) || (this.storageBinId != null && !this.storageBinId.equals(other.storageBinId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.StorageBinEntity[ id=" + storageBinId + " ]";
    }
    
}
