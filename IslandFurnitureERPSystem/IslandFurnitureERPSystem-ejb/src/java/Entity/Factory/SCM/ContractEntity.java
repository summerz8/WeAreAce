/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import java.io.Serializable;
import java.util.Calendar;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    private Double contractPrice;// in US$ per unit
    private Integer leadTime;
    private String unit;
    private Double lotSize;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar contractStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar contractEndDate;

    //contract entity -- factory retail product entity: M <--> 1
    @ManyToOne
    private FactoryRetailProductEntity factoryRetailProduct = null;

    //contract entity -- factory raw material entity: M <--> 1
    @ManyToOne
    private FactoryRawMaterialEntity factoryRawMaterial = null;

    private int typeIndicator = 0; // 1 for factoryRawMaterial, 3 for factoryRetailProduct

    //contract entity -- supplier entity: M<-->1
    @ManyToOne
    private SupplierEntity supplier;

    public ContractEntity() {
    }

    public ContractEntity(Double contractPrice, Integer leadTime, String unit, Double lotSize, Calendar contractStartDate, Calendar contractEndDate, FactoryRawMaterialEntity factoryRawMaterial, SupplierEntity supplier) {
        this.contractPrice = contractPrice;
        this.leadTime = leadTime;
        this.unit = unit;
        this.lotSize = lotSize;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.factoryRawMaterial = factoryRawMaterial;
        this.typeIndicator = 1;
        this.supplier = supplier;
    }
    
    public ContractEntity(Double contractPrice, Integer leadTime, String unit, Double lotSize, Calendar contractStartDate, Calendar contractEndDate, FactoryRetailProductEntity factoryRetailProduct, SupplierEntity supplier) {
        this.contractPrice = contractPrice;
        this.leadTime = leadTime;
        this.unit = unit;
        this.lotSize = lotSize;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.factoryRetailProduct = factoryRetailProduct;
        this.typeIndicator = 3;
        this.supplier = supplier;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Double getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(Double contractPrice) {
        this.contractPrice = contractPrice;
    }

    public Integer getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Calendar getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Calendar contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Calendar getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Calendar contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public FactoryRetailProductEntity getFactoryRetailProduct() {
        return factoryRetailProduct;
    }

    public void setFactoryRetailProduct(FactoryRetailProductEntity factoryRetailProduct) {
        this.factoryRetailProduct = factoryRetailProduct;
        this.typeIndicator = 3;
    }

    public FactoryRawMaterialEntity getFactoryRawMaterial() {
        return factoryRawMaterial;
    }

    public void setFactoryRawMaterial(FactoryRawMaterialEntity factoryRawMaterial) {
        this.factoryRawMaterial = factoryRawMaterial;
        this.typeIndicator = 3;
    }

    public int getTypeIndicator() {
        return typeIndicator;
    }

    public void setTypeIndicator(int typeIndicator) {
        this.typeIndicator = typeIndicator;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public Double getLotSize() {
        return lotSize;
    }

    public void setLotSize(Double lotSize) {
        this.lotSize = lotSize;
    }

    //create a new contract entity with attributes
    public void create(Double contractPrice, Integer leadTime, String unit, Double lotSize, Calendar contractStartDate, Calendar contractEndDate) {
        this.contractPrice = contractPrice;
        this.leadTime = leadTime;
        this.unit = unit;
        this.lotSize = lotSize;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
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
