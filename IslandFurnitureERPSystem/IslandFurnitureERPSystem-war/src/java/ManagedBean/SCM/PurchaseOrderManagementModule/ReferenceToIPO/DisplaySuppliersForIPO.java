/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule.ReferenceToIPO;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean(name = "displaySuppliersForIPO")
@ViewScoped
public class DisplaySuppliersForIPO {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;

    private Long factoryId;
    private IntegratedPlannedOrderEntity integratedPlannedOrder;
    private Collection<SupplierEntity> supplierList;
    private String itemType;
    private Long itemId;

    private FactoryRawMaterialEntity frm;
    private FactoryRetailProductEntity frp;

    @PostConstruct
    public void init() {

        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");

            //be put @selectedIntegratedPlannedOrder
            integratedPlannedOrder = (IntegratedPlannedOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedIPO");

            if (integratedPlannedOrder.getFactoryRawMaterialAmount() != null) {
                itemType = "RawMaterial";
                frm = integratedPlannedOrder.getFactoryRawMaterialAmount().getFactoryRawMaterial();
                itemId = frm.getFactoryRawMaterialId();
            } else {
                itemType = "RetailProduct";
                frp = integratedPlannedOrder.getFactoryRetailProductAmount().getFactoryRetailProduct();
                itemId = frp.getFactoryRetailProdctId();
            }

            supplierList = pmb.viewAvailSupplier(factoryId, this.integratedPlannedOrder.getId(), itemType);

            for (SupplierEntity supplier : supplierList) {
                System.out.println(supplier.toString());
            }

        } catch (Exception ex) {
            Logger.getLogger(DisplaySuppliersForIPO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public IntegratedPlannedOrderEntity getIntegratedPlannedOrder() {
        return integratedPlannedOrder;
    }

    public void setIntegratedPlannedOrder(IntegratedPlannedOrderEntity integratedPlannedOrder) {
        this.integratedPlannedOrder = integratedPlannedOrder;
    }

    public Collection<SupplierEntity> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(Collection<SupplierEntity> supplierList) {
        this.supplierList = supplierList;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public FactoryRawMaterialEntity getFrm() {
        return frm;
    }

    public void setFrm(FactoryRawMaterialEntity frm) {
        this.frm = frm;
    }

    public FactoryRetailProductEntity getFrp() {
        return frp;
    }

    public void setFrp(FactoryRetailProductEntity frp) {
        this.frp = frp;
    }

    
    public DisplaySuppliersForIPO() {
    }

}
