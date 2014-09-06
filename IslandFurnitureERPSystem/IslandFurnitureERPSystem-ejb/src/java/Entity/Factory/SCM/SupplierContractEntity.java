/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import Entity.Factory.FactoryRawMaterialEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Yoky
 */
@Entity
@Table(name = "SupplierContract")
public class SupplierContractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierContractId;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private FactoryRawMaterialEntity rawMaterial;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private SupplierEntity supplier;
    private double price;   // the price per lotsize
    private double lotsize;
    private String unit;    // lot size unit
    private Integer leadTime;   // unit: days
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date contractStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date contractEndDate;
    private String remark;
    @OneToMany(mappedBy = "supplierContract")
    private List<PurchaseOrderEntity> purchaseOrders = new ArrayList<PurchaseOrderEntity>();

    public SupplierContractEntity() {
    }

    public Long getSupplierContractId() {
        return supplierContractId;
    }

    public void setSupplierContractId(Long supplierContractId) {
        this.supplierContractId = supplierContractId;
    }

    public FactoryRawMaterialEntity getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(FactoryRawMaterialEntity rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLotsize() {
        return lotsize;
    }

    public void setLotsize(double lotsize) {
        this.lotsize = lotsize;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PurchaseOrderEntity> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrderEntity> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierContractId != null ? supplierContractId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the supplierContractId fields are not set
        if (!(object instanceof SupplierContractEntity)) {
            return false;
        }
        SupplierContractEntity other = (SupplierContractEntity) object;
        if ((this.supplierContractId == null && other.supplierContractId != null) || (this.supplierContractId != null && !this.supplierContractId.equals(other.supplierContractId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.SupplierContractEntity[ id=" + supplierContractId + " ]";
    }

}
