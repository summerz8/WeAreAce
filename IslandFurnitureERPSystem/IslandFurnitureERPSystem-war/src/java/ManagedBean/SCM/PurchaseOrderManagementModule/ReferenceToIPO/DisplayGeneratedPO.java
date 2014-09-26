/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule.ReferenceToIPO;

import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.DeliveryOrderEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
@ManagedBean(name = "displayGeneratedPO")
@ViewScoped
public class DisplayGeneratedPO {

    @EJB
    private PurchaseOrderManagementModuleLocal pmb;

    private PurchaseOrderEntity purchaseOrder;
    private Long factoryId;
    private Long contractId;
    private Double purchaseAmount;
    private Long storeId;
    private String destination;
    private Calendar deliveryDate;

    private Long itemId;
    private String itemType;

    private String itemName;
    private String unit;
    private ContractEntity contract;
    private Double totalPrice;
    private String destinationAddress;
    private IntegratedPlannedOrderEntity integratedPlannedOrder;
    private Double nextMonthBeginPlannedAmount;
    private Collection<DeliveryOrderEntity> deliveryOrderList;

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("factoryId");

            integratedPlannedOrder = (IntegratedPlannedOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedIPO");
            nextMonthBeginPlannedAmount = (Double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nextMonthBeginPlannedAmount");
            deliveryOrderList = (Collection<DeliveryOrderEntity>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("deliveryOrderList");

            contractId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("contractId");
            storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("storeId");
            destination = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("destination");

            if (integratedPlannedOrder.getFactoryRawMaterialAmount() != null) {
                itemType = "RawMaterial";
                itemId = integratedPlannedOrder.getFactoryRawMaterialAmount().getFactoryRawMaterial().getFactoryRawMaterialId();

            } else {
                itemType = "RetailProduct";
                itemId = integratedPlannedOrder.getFactoryRetailProductAmount().getFactoryRetailProduct().getFactoryRetailProdctId();
            }

            purchaseAmount = pmb.generatePurchaseAmount(integratedPlannedOrder.getId(), nextMonthBeginPlannedAmount, itemType);

            if (itemType.equals("RawMaterial")) {

                itemName = pmb.getFactoryRM(itemId).getMaterialName();
                unit = pmb.getFactoryRM(itemId).getUnit();

            } else {
                itemName = pmb.getFactoryRP(itemId).getName();
                unit = pmb.getFactoryRM(itemId).getUnit();

            }
            contract = pmb.getContract(contractId);
            totalPrice = purchaseAmount * contract.getContractPrice();
            if (destination.equals("store")) {
                destinationAddress = pmb.getStoreEntity(storeId).getAddress();

            } else {
                destinationAddress = pmb.getFactoryEntity(factoryId).getAddress();
            }
        } catch (Exception ex) {
            Logger.getLogger(DisplayGeneratedPO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public PurchaseOrderEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrderEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDeliveryDate() {
        return deliveryDate.getTime();
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate.setTime(null);
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public IntegratedPlannedOrderEntity getIntegratedPlannedOrder() {
        return integratedPlannedOrder;
    }

    public void setIntegratedPlannedOrder(IntegratedPlannedOrderEntity integratedPlannedOrder) {
        this.integratedPlannedOrder = integratedPlannedOrder;
    }

    public Double getNextMonthBeginPlannedAmount() {
        return nextMonthBeginPlannedAmount;
    }

    public void setNextMonthBeginPlannedAmount(Double nextMonthBeginPlannedAmount) {
        this.nextMonthBeginPlannedAmount = nextMonthBeginPlannedAmount;
    }

    public Collection<DeliveryOrderEntity> getDeliveryOrderList() {
        return deliveryOrderList;
    }

    public void setDeliveryOrderList(Collection<DeliveryOrderEntity> deliveryOrderList) {
        this.deliveryOrderList = deliveryOrderList;
    }

    public String generatePO() throws Exception {

        purchaseOrder = pmb.generatePurchaseOrder(factoryId, integratedPlannedOrder.getId(), purchaseAmount, nextMonthBeginPlannedAmount, contractId, storeId, destination, itemType);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedIPO");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("nextMonthBeginPlannedAmount");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("contractId");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("storeId");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("destination");

        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/ReferenceToIntegratedPlannedOrder/GetGeneratedPO?faces-redirect=true";

    }

}
