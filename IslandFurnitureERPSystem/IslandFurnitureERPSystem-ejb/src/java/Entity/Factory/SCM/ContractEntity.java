/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name = "Contract")
public class ContractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contractId;
    private double price;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date contractStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date contractEndDate;

    //contract entity -- factory retail product entity: M <--> 1
    @ManyToOne
    private FactoryRetailProductEntity factoryRetailProduct;
    
    //contract entity -- factory raw material entity: M <--> 1
    @ManyToOne
    private FactoryRawMaterialEntity factoryRawMaterialProduct;
    
    //contract entity -- supplier entity: M<-->1
    @ManyToOne
    private SupplierEntity supplier;
    
    public ContractEntity() {
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public FactoryRetailProductEntity getFactoryRetailProduct() {
        return factoryRetailProduct;
    }

    public void setFactoryRetailProduct(FactoryRetailProductEntity factoryRetailProduct) {
        this.factoryRetailProduct = factoryRetailProduct;
    }

    public FactoryRawMaterialEntity getFactoryRawMaterialProduct() {
        return factoryRawMaterialProduct;
    }

    public void setFactoryRawMaterialProduct(FactoryRawMaterialEntity factoryRawMaterialProduct) {
        this.factoryRawMaterialProduct = factoryRawMaterialProduct;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contractId != null ? contractId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the contractId fields are not set
        if (!(object instanceof ContractEntity)) {
            return false;
        }
        ContractEntity other = (ContractEntity) object;
        if ((this.contractId == null && other.contractId != null) || (this.contractId != null && !this.contractId.equals(other.contractId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.ContractEntity[ id=" + contractId + " ]";
    }

}
