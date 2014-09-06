/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.FacotryBin;

import Entity.Factory.FactoryItemEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "FactoryStoredProduct")
public class FactoryBinStoredProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long FactoryBinStoredProductId;
    private Double amount;
    private String unit;
    @ManyToOne
    private FactoryBinEntity factoryBin;
    @ManyToOne
    private FactoryItemEntity factoryItem;

    public FactoryBinStoredProductEntity() {
        setFactoryBinStoredProductId(System.nanoTime());
    }

    public Long getFactoryBinStoredProductId() {
        return FactoryBinStoredProductId;
    }

    public void setFactoryBinStoredProductId(Long id) {
        this.FactoryBinStoredProductId = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public FactoryBinEntity getFactoryBin() {
        return factoryBin;
    }

    public void setFactoryBin(FactoryBinEntity factoryBin) {
        this.factoryBin = factoryBin;
    }

    public FactoryItemEntity getFactoryItem() {
        return factoryItem;
    }

    public void setFactoryItem(FactoryItemEntity factoryItem) {
        this.factoryItem = factoryItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (FactoryBinStoredProductId != null ? FactoryBinStoredProductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactoryBinStoredProductEntity)) {
            return false;
        }
        FactoryBinStoredProductEntity other = (FactoryBinStoredProductEntity) object;
        if ((this.FactoryBinStoredProductId == null && other.FactoryBinStoredProductId != null) || (this.FactoryBinStoredProductId != null && !this.FactoryBinStoredProductId.equals(other.FactoryBinStoredProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.StoredProduct[ id=" + FactoryBinStoredProductId + " ]";
    }

}
