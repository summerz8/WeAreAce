/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */
@Entity
public class InventoryRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar recordDate;
    private Double amount;
    
    //inventory record entity -- factory raw material entity : M <--> 1
    @ManyToOne
    private FactoryRawMaterialEntity factoryRawMaterial = null;
    
    //inventory record entity -- factory product entity : M <--> 1
    @ManyToOne
    private FactoryProductEntity factoryProduct = null;
    
    //inventory record entity -- factory retail product entity : M <--> 1
    @ManyToOne
<<<<<<< HEAD

    private FactoryRetailProductEntity factoryRetailProduct = null;

=======
    private FactoryRetailProductEntity factoryRetailProduct;
>>>>>>> b8eb2397ec83f52290202fff9465a7b317d3a164

    public InventoryRecordEntity() {
    }

<<<<<<< HEAD
    public InventoryRecordEntity(FactoryRawMaterialEntity factoryRawMaterial, Calendar recordDate, Double amount) {
        this.factoryRawMaterial = factoryRawMaterial;
        this.recordDate = recordDate;
        this.amount = amount;
    }
    
    public InventoryRecordEntity(FactoryProductEntity factoryProduct, Calendar recordDate, Double amount) {
        this.factoryProduct = factoryProduct;
        this.recordDate = recordDate;
        this.amount = amount;
    }
    
    public InventoryRecordEntity(FactoryRetailProductEntity factoryRetailProduct, Calendar recordDate, Double amount) {
        this.factoryRetailProduct = factoryRetailProduct;
        this.recordDate = recordDate;
        this.amount = amount;

=======
    
    public InventoryRecordEntity(Calendar recordDate, Double amount, FactoryRawMaterialEntity factoryRawMaterial, FactoryProductEntity factoryProduct, FactoryRetailProductEntity factoryRetailProduct) {
        this.recordDate = recordDate;
        this.amount = amount;
        this.factoryRawMaterial = factoryRawMaterial;
        this.factoryProduct = factoryProduct;
        this.factoryRetailProduct = factoryRetailProduct;
>>>>>>> b8eb2397ec83f52290202fff9465a7b317d3a164
    }
            
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Calendar recordDate) {
        this.recordDate = recordDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

<<<<<<< HEAD

=======
   
>>>>>>> b8eb2397ec83f52290202fff9465a7b317d3a164
    public FactoryRawMaterialEntity getFactoryRawMaterial() {
        return factoryRawMaterial;
    }

    public void setFactoryRawMaterial(FactoryRawMaterialEntity factoryRawMaterial) {
        this.factoryRawMaterial = factoryRawMaterial;
    }

    public FactoryProductEntity getFactoryProduct() {
        return factoryProduct;
    }

    public void setFactoryProduct(FactoryProductEntity factoryProduct) {
        this.factoryProduct = factoryProduct;
    }

    public FactoryRetailProductEntity getFactoryRetailProduct() {
        return factoryRetailProduct;
    }

    public void setFactoryRetailProduct(FactoryRetailProductEntity factoryRetailProduct) {
        this.factoryRetailProduct = factoryRetailProduct;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventoryRecordEntity)) {
            return false;
        }
        InventoryRecordEntity other = (InventoryRecordEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.InventoryRecordEntity[ id=" + id + " ]";
    }
    
}
