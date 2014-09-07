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
@Table(name = "FactoryRetailProduct")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FactoryRetailProductEntity extends FactoryItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //factory retail product entity -- reatail product entity: M <--> 1
    @ManyToOne
    private RetailProductEntity retailProduct;
    
    //contract entity -- factory retail product entity: M <--> 1
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "rettailProduct")
    private Collection<ContractEntity> contracts;
    
    public FactoryRetailProductEntity() {

    }

    public RetailProductEntity getRetailProduct() {
        return retailProduct;
    }

    public void setRetailProduct(RetailProductEntity retailProduct) {
        this.retailProduct = retailProduct;
    }

    public Collection<ContractEntity> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<ContractEntity> contracts) {
        this.contracts = contracts;
    }
    
    
    

}
