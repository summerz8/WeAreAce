/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contractId;
<<<<<<< HEAD
<<<<<<< HEAD
    private Double contractPrice;
    private Integer leadTime;

=======
    private double contractPrice;   // in US$ per unit
    private String unit;
>>>>>>> e7f7d6f925185cf2a916a2f547520582e1d869d0
=======
    private double contractPrice;   // in US$ per unit
    private String unit;
>>>>>>> e7f7d6f925185cf2a916a2f547520582e1d869d0
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar contractStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar contractEndDate;

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
        //may be changed later
        setContractId(System.nanoTime());
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

<<<<<<< HEAD
<<<<<<< HEAD
    public Integer getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }
    
=======
=======
>>>>>>> e7f7d6f925185cf2a916a2f547520582e1d869d0
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

<<<<<<< HEAD
>>>>>>> e7f7d6f925185cf2a916a2f547520582e1d869d0
=======
>>>>>>> e7f7d6f925185cf2a916a2f547520582e1d869d0
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
    }
//
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

    //create a new contract entity with attributes
    public void create(Double contractPrice, Integer leadTime, Calendar contractStartDate, Calendar contractEndDate) {
        this.contractPrice = contractPrice;
        this.leadTime = leadTime;
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
