/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import Entity.Factory.SCM.ContractEntity;
import java.io.Serializable;
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
        @Table(name="FactoryRawMaterial")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FactoryRawMaterialEntity extends FactoryItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //factory raw material entity -- raw material entity: M <--> 1
    @ManyToOne
    private RawMaterialEntity rawMaterial;
    
    //contract entity -- factory raw material entity: M <--> 1
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "rawMaterial")
    private Collection<ContractEntity> contracts;
    
    public FactoryRawMaterialEntity() {
        
    }

    public RawMaterialEntity getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterialEntity rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public Collection<ContractEntity> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<ContractEntity> contracts) {
        this.contracts = contracts;
    }
   
    
}
