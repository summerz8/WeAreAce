/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM.PurchaseOrderManagementModule;

import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "displayManuallyGeneratedPO")
@ViewScoped
public class DisplayManuallyGeneratedPO {

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

    @PostConstruct
    public void init() {
        factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("factoryId");
        itemType = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemType");
        itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");

        //be put @displayContractForManuallyGeneratedPO
        contractId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("contractId");
        //be put @displayContractForManuallyGeneratedPO
        purchaseAmount = (Double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("purchaseAmount");
        //be put @selectedDeliveryDestination, could be null if destination is factory
        storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("storeId");
        //be put @selectedDeliveryDestination
        destination = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("destination");
        //be put @displayContractForManuallyGeneratedPO
        deliveryDate = (Calendar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("deliveryDate");
        try {
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
            Logger.getLogger(DisplayManuallyGeneratedPO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Creates a new instance of DisplayManuallyGeneratedPO
     */
    public DisplayManuallyGeneratedPO() {
    }

    public String generatePO() throws Exception {

        purchaseOrder = pmb.createPurchaseOrder(factoryId, contractId, purchaseAmount, storeId, destination, deliveryDate);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemType");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("itemId");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("contractId");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("purchaseAmount");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("storeId");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("destination");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("deliveryDate");

        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/GetManuallyGeneratedPO?faces-redirect=true";

    }

}
