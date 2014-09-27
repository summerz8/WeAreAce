/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

import SessionBean.SCM.FactoryInventoryManagementModuleLocal;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "factoryInventoryManagementBean")
@SessionScoped
public class FactoryInventoryManagementBean implements Serializable {

    @EJB
    private FactoryInventoryManagementModuleLocal fim;

    @ManagedProperty(value = "#{loginBean.userId}")
    private String userId;
    private long factoryId;
    private Long factoryRawMaterialId;
    private Long factoryProductId;
    private Long factoryRetailProductId;
    private Long factoryBinStoredProductId;
    private Long goodsReceiptId;
    private Long fromBinId;
    private Long toBinId;
    private Long fromStoreId;
    private Long toStoreId;
    private String status;
    private String toStatus;
    private double quantity;
    private Calendar creationDate = Calendar.getInstance();
    private Date inputDate;
    private List storageBinInformation;
    private Long inboundMovementId;
    private int itemTypeIndicator;
    private Long itemId;

    public FactoryInventoryManagementBean() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        this.setFactoryId(fim.findFactoryIdByUserId(userId));
    }

    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
        this.factoryId = factoryId;
    }

    public Long getFactoryRawMaterialId() {
        return factoryRawMaterialId;
    }

    public void setFactoryRawMaterialId(Long factoryRawMaterialId) {
        this.factoryRawMaterialId = factoryRawMaterialId;
    }

    public Long getFactoryProductId() {
        return factoryProductId;
    }

    public void setFactoryProductId(Long factoryProductId) {
        this.factoryProductId = factoryProductId;
    }

    public Long getFactoryRetailProductId() {
        return factoryRetailProductId;
    }

    public void setFactoryRetailProductId(Long factoryRetailProductId) {
        this.factoryRetailProductId = factoryRetailProductId;
    }

    public Long getGoodsReceiptId() {
        return goodsReceiptId;
    }

    public void setGoodsReceiptId(Long goodsReceiptId) {
        this.goodsReceiptId = goodsReceiptId;
    }

    public Long getFromBinId() {
        return fromBinId;
    }

    public void setFromBinId(Long fromBinId) {
        this.fromBinId = fromBinId;
    }

    public Long getToBinId() {
        return toBinId;
    }

    public void setToBinId(Long toBinId) {
        this.toBinId = toBinId;
    }

    public Long getToStoreId() {
        return toStoreId;
    }

    public void setToStoreId(Long toStoreId) {
        this.toStoreId = toStoreId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
        creationDate.setTime(inputDate);
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Long getInboundMovementId() {
        return inboundMovementId;
    }

    public void setInboundMovementId(Long inboundMovementId) {
        this.inboundMovementId = inboundMovementId;
    }

    public List getStorageBinInformation() {
        storageBinInformation = this.listStorageBinInformation(factoryId);
        return storageBinInformation;
    }

    public void setStorageBinInformation(List storageBinInformation) {
        this.storageBinInformation = storageBinInformation;
    }

    public Long getFactoryBinStoredProductId() {
        return factoryBinStoredProductId;
    }

    public void setFactoryBinStoredProductId(Long factoryBinStoredProductId) {
        this.factoryBinStoredProductId = factoryBinStoredProductId;
    }

    public Long getFromStoreId() {
        return fromStoreId;
    }

    public void setFromStoreId(Long fromStoreId) {
        this.fromStoreId = fromStoreId;
    }

    public String getToStatus() {
        return toStatus;
    }

    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
    }

    public int getItemTypeIndicator() {
        return itemTypeIndicator;
    }

    public void setItemTypeIndicator(int itemTypeIndicator) {
        this.itemTypeIndicator = itemTypeIndicator;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public List listStorageBinInformation(long factoryId) {
        return fim.listStorageBinInformation(factoryId);
    }

    public Long recordInboundMovement(ActionEvent event) {
        return fim.recordInboundMovement(factoryId, goodsReceiptId, toBinId, status, quantity, creationDate);
    }

    public Long recordOutboundMovement(ActionEvent event) {
        if (itemTypeIndicator == 2) {
            return fim.recordFactoryProductOutboundMovement(factoryId, fromBinId, itemId, toStoreId, quantity, creationDate);
        } else {
            return fim.recordFactoryRetailProductOutboundMovement(factoryId, fromBinId, itemId, toStoreId, quantity, creationDate);
        }
    }

    public Long recordInFactoryMovement(ActionEvent event) {
        if (itemTypeIndicator == 1) {
            return fim.recordInFactoryRawMaterialMovement(factoryId, fromBinId, toBinId, itemId, status, quantity, creationDate);
        } else if (itemTypeIndicator == 2) {
            return fim.recordInFactoryProductMovement(factoryId, fromBinId, toBinId, itemId, status, quantity, creationDate);
        } else {
            return fim.recordInFactoryRetailProductMovement(factoryId, fromBinId, toBinId, itemId, status, quantity, creationDate);
        }
    }

    public Long recordRawMaterialInFactoryUseMovement(ActionEvent event) {
        return fim.recordRawMaterialInFactoryUseMovement(factoryId, fromBinId, factoryRawMaterialId, quantity, creationDate);
    }

    public void changeFactoryBinStoredProductStatus(ActionEvent event) {
        fim.changeFactoryBinStoredProductStatus(factoryBinStoredProductId, toStatus);
    }

    public Long recordReturnedItemInboundMovement(ActionEvent event) {
        if (itemTypeIndicator == 2) {
            return fim.recordReturnedProductInboundMovement(factoryId, itemId, fromStoreId, toBinId, quantity, creationDate);
        } else {
            return fim.recordReturnedRetailProductInboundMovement(factoryId, itemId, fromStoreId, toBinId, quantity, creationDate);
        }
    }
    
    public Long recordProductToBinMovement(ActionEvent event) {
        return fim.recordProductToBinMovement(factoryProductId, toBinId, status, quantity, creationDate);
    }

    public long findFactoryIdByUserId(String userId) {
        return fim.findFactoryIdByUserId(userId);
    }

}
