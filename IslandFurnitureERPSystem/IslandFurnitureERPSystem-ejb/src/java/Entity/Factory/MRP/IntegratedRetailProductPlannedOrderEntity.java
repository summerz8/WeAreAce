/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.MRP;

import Entity.Factory.FactoryEntity;
import Entity.Factory.RawMaterialAmountEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */
@Entity
public class IntegratedRetailProductPlannedOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar date;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetDate;
    private RawMaterialAmountEntity rawMaterial;
    private String status;//waiting, processing, cancelled, and accomplished

    @OneToMany
    private List<RetailProductPlannedOrderEntity> retailProductPlannedOrderList;

    //planned order entity -- purchase order entity: 1 <--> M
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "integratedPlannedOrder")
    private List<PurchaseOrderEntity> purchaseOrder = null;

    @ManyToOne
    private FactoryEntity factory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Calendar getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Calendar targetDate) {
        this.targetDate = targetDate;
    }

    public RawMaterialAmountEntity getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterialAmountEntity rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RetailProductPlannedOrderEntity> getRetailProductPlannedOrderList() {
        return retailProductPlannedOrderList;
    }

    public void setRetailProductPlannedOrderList(List<RetailProductPlannedOrderEntity> retailProductPlannedOrderList) {
        this.retailProductPlannedOrderList = retailProductPlannedOrderList;
    }

    public List<PurchaseOrderEntity> getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(List<PurchaseOrderEntity> purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factory) {
        this.factory = factory;
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
        if (!(object instanceof IntegratedRetailProductPlannedOrderEntity)) {
            return false;
        }
        IntegratedRetailProductPlannedOrderEntity other = (IntegratedRetailProductPlannedOrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.MRP.IntegratedRetailProductPlannedOrderEntity[ id=" + id + " ]";
    }

}
