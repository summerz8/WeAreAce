/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.MRP;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.FactoryRetailProductAmountEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author apple
 */
@Entity
public class IntegratedPlannedOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar  generatedDate;//generate generatedDate
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar targetPeriod;//target production generatedDate
    private String status;//waiting, processing, cancelled, and accomplished

    //raw material amount entity -- integrated planned order entity: 1 <-- 1
    @OneToOne
    private FactoryRawMaterialAmountEntity factoryRawMaterialAmount = null;

    //factory retail product amount entity -- integrated planned order entity: 1 <-- 1
    @OneToOne
    private FactoryRetailProductAmountEntity factoryRetailProductAmount = null;

    //planned order entity -- integrated planned order entity: M <-- M
    @ManyToMany(cascade={CascadeType.PERSIST})
    @JoinTable(name="INTEGRATEDPLANNEDORDER_PLANNEDORDER")
    private List<PlannedOrderEntity> plannedOrderList=new ArrayList<>();

    //integrated planned order entity -- purchase order entity: 1 <--> 1
    @OneToOne(cascade={CascadeType.PERSIST})
    private PurchaseOrderEntity purchaseOrder = null;

    //integrated planned order entity -- factory entity: M <--> 1
    @ManyToOne
    private FactoryEntity factory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Calendar generatedDate) {
        this.generatedDate = generatedDate;
    }

    public Calendar getTargetPeriod() {
        return targetPeriod;
    }

    public void setTargetPeriod(Calendar targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    public FactoryRawMaterialAmountEntity getFactoryRawMaterialAmount() {
        return factoryRawMaterialAmount;
    }

    public void setFactoryRawMaterialAmount(FactoryRawMaterialAmountEntity factoryRawMaterialAmount) {
        this.factoryRawMaterialAmount = factoryRawMaterialAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PlannedOrderEntity> getPlannedOrderList() {
        return plannedOrderList;
    }

    public void setPlannedOrderList(List<PlannedOrderEntity> plannedOrderList) {
        this.plannedOrderList = plannedOrderList;
    }

    public PurchaseOrderEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrderEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factory) {
        this.factory = factory;
    }

    public FactoryRetailProductAmountEntity getFactoryRetailProductAmount() {
        return factoryRetailProductAmount;
    }

    public void setFactoryRetailProductAmount(FactoryRetailProductAmountEntity factoryRetailProduct) {
        this.factoryRetailProductAmount = factoryRetailProduct;
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
        if (!(object instanceof IntegratedPlannedOrderEntity)) {
            return false;
        }
        IntegratedPlannedOrderEntity other = (IntegratedPlannedOrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.MRP.IntegratedPlannedOrderEntity[ id=" + id + " ]";
    }

}
