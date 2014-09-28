/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule.ReferenceToIPO;

import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.SupplierEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.util.Calendar;
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
@ManagedBean(name = "displayContractForSelectedSupplierIPO")
@ViewScoped
public class DisplayContractForSelectedSupplierIPO {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;
    private ContractEntity contract;
    private IntegratedPlannedOrderEntity integratedPlannedOrder;
    private SupplierEntity supplier;
    private String itemType;
    private Long itemId;
    private Long factoryId;
    private Calendar deliveryDate;
    private Double nextMonthBeginPlannedAmount;

    @PostConstruct
    public void init() {

        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");

            //be put @selectedIntegratedPlannedOrder
            integratedPlannedOrder = (IntegratedPlannedOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedIPO");
            //be put @selectedSupplierForIPO
            supplier = (SupplierEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedSupplierIPO");

            if (integratedPlannedOrder.getFactoryRawMaterialAmount() != null) {
                itemType = "RawMaterial";
                itemId = integratedPlannedOrder.getFactoryRawMaterialAmount().getFactoryRawMaterial().getFactoryRawMaterialId();
            } else {
                itemType = "RetailProduct";
                itemId = integratedPlannedOrder.getFactoryRetailProductAmount().getFactoryRetailProduct().getFactoryRetailProdctId();
            }

            contract = pmb.selectSupplier(itemType, itemId, supplier.getSupplierId());
        } catch (Exception ex) {
            Logger.getLogger(DisplayContractForSelectedSupplierIPO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public IntegratedPlannedOrderEntity getIntegratedPlannedOrder() {
        return integratedPlannedOrder;
    }

    public void setIntegratedPlannedOrder(IntegratedPlannedOrderEntity integratedPlannedOrder) {
        this.integratedPlannedOrder = integratedPlannedOrder;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
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

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Calendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getNextMonthBeginPlannedAmount() {
        return nextMonthBeginPlannedAmount;
    }

    public void setNextMonthBeginPlannedAmount(Double nextMonthBeginPlannedAmount) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextMonthBeginPlannedAmount", nextMonthBeginPlannedAmount);
        this.nextMonthBeginPlannedAmount = nextMonthBeginPlannedAmount;
    }

    public DisplayContractForSelectedSupplierIPO() {
    }

}
