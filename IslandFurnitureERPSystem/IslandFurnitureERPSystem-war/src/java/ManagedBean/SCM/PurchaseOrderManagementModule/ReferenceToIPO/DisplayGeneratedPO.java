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
import Entity.Factory.SCM.SupplierEntity;
import Entity.Store.StoreEntity;
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
    private SupplierEntity supplier;
    private Double originalAmount;

    private Boolean isToStore;

    @PostConstruct
    public void init() {
        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");

            integratedPlannedOrder = (IntegratedPlannedOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedIPO");
            nextMonthBeginPlannedAmount = (Double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nextMonthBeginPlannedAmount");
            deliveryOrderList = (Collection<DeliveryOrderEntity>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("deliveryOrderList");

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

            StoreEntity se = (StoreEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedStore");
            storeId = se.getStoreId();
            destination = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("destination");

            if (integratedPlannedOrder.getFactoryRawMaterialAmount() != null) {
                itemType = "RawMaterial";
                itemId = integratedPlannedOrder.getFactoryRawMaterialAmount().getFactoryRawMaterial().getFactoryRawMaterialId();

            } else {
                itemType = "RetailProduct";
                itemId = integratedPlannedOrder.getFactoryRetailProductAmount().getFactoryRetailProduct().getFactoryRetailProdctId();
            }

            originalAmount = pmb.generateOriginalAmount(integratedPlannedOrder.getId(), nextMonthBeginPlannedAmount, itemType);

            purchaseAmount = pmb.generatePurchaseAmount(integratedPlannedOrder.getId(), nextMonthBeginPlannedAmount, itemType, contract.getLotSize());
            System.out.println("originalAmount = " + originalAmount);

            if (itemType.equals("RawMaterial")) {

                itemName = pmb.getFactoryRM(itemId).getMaterialName();
                unit = pmb.getFactoryRM(itemId).getUnit();

            } else {
                itemName = pmb.getFactoryRP(itemId).getName();
                unit = pmb.getFactoryRM(itemId).getUnit();

            }
            totalPrice = (purchaseAmount / contract.getLotSize()) * contract.getContractPrice();

            if (destination != null && destination.equals("store")) {
                destinationAddress = pmb.getStoreEntity(storeId).getAddress();
            } else {
                destination = "factory";
                storeId = 0L;
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

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
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
        this.deliveryDate.setTime(deliveryDate);
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

    public PurchaseOrderManagementModuleLocal getPmb() {
        return pmb;
    }

    public void setPmb(PurchaseOrderManagementModuleLocal pmb) {
        this.pmb = pmb;
    }
    
    public Boolean getIsToStore() {
        return isToStore;
    }

    public void setIsToStore(Boolean isToStore) {
        this.isToStore = isToStore;
    }
    

    public String generatePO() throws Exception {
        System.out.println("factoryId = " + factoryId);
        System.out.println("IPO id = " + integratedPlannedOrder.getId());
        System.out.println("purchaseAmount = " + purchaseAmount);
        System.out.println("nextMonthBeginPlannedAmount = " + nextMonthBeginPlannedAmount);
        System.out.println("contractId = " + contract.getContractId());
        System.out.println("storeId = " + storeId);
        System.out.println("destination = " + destination);
        System.out.println("itemType = " + itemType);

        if (destination != null && destination.equals("store")) {
            destinationAddress = pmb.getStoreEntity(storeId).getAddress();
            isToStore = true;
        } else {
            destination = "factory";
            storeId = 0L;
            destinationAddress = pmb.getFactoryEntity(factoryId).getAddress();
            isToStore = false;
        }

        purchaseOrder = pmb.generatePurchaseOrder(factoryId, integratedPlannedOrder.getId(), purchaseAmount, nextMonthBeginPlannedAmount, contract.getContractId(), storeId, destination, itemType, isToStore);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedIPO");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedSupplierIPO");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("nextMonthBeginPlannedAmount");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedStore");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("destination");

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("po", purchaseOrder);

        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/GetManuallyGeneratedPO?faces-redirect=true";

    }
}
