/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author zhengyuan
 */
@Entity
@Table(name = "FactoryRawMaterial")
public class FactoryRawMaterialEntity extends FactoryItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FactoryRawMaterialPKEntity factoryRawMaterialPK;
    
    public FactoryRawMaterialEntity() {

    }

    public FactoryRawMaterialPKEntity getFactoryRawMaterialPK() {
        return factoryRawMaterialPK;
    }

    public void setFactoryRawMaterialPK(FactoryRawMaterialPKEntity factoryRawMaterialPK) {
        this.factoryRawMaterialPK = factoryRawMaterialPK;
    }

}
