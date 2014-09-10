/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

/**
 *
 * @author Yoky
 */
@Embeddable
public class FactoryRawMaterialPKEntity implements Serializable {
//    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "FACTORYRAWMATERIAL_FACTORY")
    private FactoryEntity factory;
    @JoinColumn(name = "FACTORYRAWMATERIAL_RAWMATERIAL")
    private RawMaterialEntity rawMaterial;

    public FactoryRawMaterialPKEntity() {
    }

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factory) {
        this.factory = factory;
    }

    public RawMaterialEntity getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterialEntity rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (object == this)
            return true;
        if (!(object instanceof FactoryRawMaterialPKEntity)) {
            return false;
        }
        FactoryRawMaterialPKEntity other = (FactoryRawMaterialPKEntity) object;
        if (!(this.factory.equals(other.factory))) {
            return false;
        }
        if (!(this.rawMaterial.equals(other.rawMaterial))) {
            return false;
        }  
        return true;
    }

    @Override
    public int hashCode() {
        return this.factory.hashCode() + this.rawMaterial.hashCode();
    }
    
}
