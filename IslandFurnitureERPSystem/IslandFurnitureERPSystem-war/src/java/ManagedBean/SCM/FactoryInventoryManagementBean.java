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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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

    public void recordInboundMovement(ActionEvent event) {
        Long temp = fim.recordInboundMovement(factoryId, goodsReceiptId, toBinId, status, quantity, creationDate);
        if (temp == -1L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create inbound movement record",
                            "goodsReceiptId is invalid"));
        } else if (temp == -2L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create inbound movement record",
                            "factory has no access to this goods receipt"));
        } else if (temp == -3L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create inbound movement record",
                            "toBinId is invalid"));
        } else if (temp == -4L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create inbound movement record",
                            "factory has no access to this factory bin"));
        } else if (temp == -5L) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create inbound movement record",
                            "unexpected exception occurred"));
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Create Successful!",
                            ""));
        }
    }

    public void recordOutboundMovement(ActionEvent event) {
        Long temp;
        if (itemTypeIndicator == 2) {
            temp = fim.recordFactoryProductOutboundMovement(factoryId, fromBinId, itemId, toStoreId, quantity, creationDate);
            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "toStoreId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "fromBinId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "factoryProduct is not found"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "specified storage bin does not contain this factoryProduct available for shipping"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "required quantity exceeds the total stock from this storage bin"));
            } else if (temp == -6L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "required quantity exceeds the minimum inventory level in the factory"));
            } else if (temp == -7L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "unexpected exception occurred"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Create Successful!",
                                ""));
            }
        } else {
            temp = fim.recordFactoryRetailProductOutboundMovement(factoryId, fromBinId, itemId, toStoreId, quantity, creationDate);
            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "toStoreId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "fromBinId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "factoryRetailProduct is not found"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "specified storage bin does not contain this factoryRetailProduct available for shipping"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "required quantity exceeds the total stock from this storage bin"));
            } else if (temp == -6L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "required quantity exceeds the minimum inventory level in the factory"));
            } else if (temp == -7L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create outbound movement record",
                                "unexpected exception occurred"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Create Successful!",
                                ""));
            }
        }
    }

    public void recordInFactoryMovement(ActionEvent event) {
        Long temp;
        if (itemTypeIndicator == 1) {
            temp = fim.recordInFactoryRawMaterialMovement(factoryId, fromBinId, toBinId, itemId, status, quantity, creationDate);

            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "fromBinId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "toBinId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "factoryRawMaterial is not found"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "specified storage bin does not contain this factoryRawMaterial with required status"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "required quantity exceeds the total stock from this storage bin"));
            } else if (temp == -6L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "unexpected exception occurredy"));
            }  else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Successful!",
                                ""));
            }
            
        } else if (itemTypeIndicator == 2) {
            temp = fim.recordInFactoryProductMovement(factoryId, fromBinId, toBinId, itemId, status, quantity, creationDate);
            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "fromBinId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "toBinId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "factoryProduct is not found"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "specified storage bin does not contain this factoryProduct with required status"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "required quantity exceeds the total stock from this storage bin"));
            } else if (temp == -6L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "unexpected exception occurred "));
            }  else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Create Successful!",
                                ""));
            }
        } else {
            temp = fim.recordInFactoryRetailProductMovement(factoryId, fromBinId, toBinId, itemId, status, quantity, creationDate);
            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "fromBinId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "toBinId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "factoryRetailProduct is not found"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "specified storage bin does not contain this factoryRetailProduct with required status"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "required quantity exceeds the total stock from this storage bin"));
            } else if (temp == -6L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record infactory movement",
                                "unexpected exception occurred "));
            }  else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Create Successful!",
                                ""));
            }
        }
    }

    public void recordRawMaterialInFactoryUseMovement(ActionEvent event) {
        Long temp = fim.recordRawMaterialInFactoryUseMovement(factoryId, fromBinId, factoryRawMaterialId, quantity, creationDate);
        if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record raw material infactory use movement",
                                "fromBinId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record raw material infactory use movement",
                                "factoryRawMaterial is not found"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record raw material infactory use movement",
                                "specified storage bin does not contain this factoryRawMaterial available for use"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record raw material infactory use movement",
                                "required quantity exceeds the total stock in this storage bin"));
            } else if (temp == -5L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record raw material infactory use movement",
                                "required quantity exceeds the minimum stock level in the factory"));
            } else if (temp == -6L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record raw material infactory use movement",
                                "unexpected exception occurred "));
            }  else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Create Successful!",
                                ""));
            }
    }

    public void changeFactoryBinStoredProductStatus(ActionEvent event) {
        fim.changeFactoryBinStoredProductStatus(factoryBinStoredProductId, toStatus);
    }

    public void recordReturnedItemInboundMovement(ActionEvent event) {
       Long temp;
        if (itemTypeIndicator == 2) {
            temp = fim.recordReturnedProductInboundMovement(factoryId, itemId, fromStoreId, toBinId, quantity, creationDate);
             if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "factoryProductId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "storeId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "toBinId is invalid"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "unexpected exception occurred"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Create Successful!",
                                ""));
            }
        } else {
            temp = fim.recordReturnedRetailProductInboundMovement(factoryId, itemId, fromStoreId, toBinId, quantity, creationDate);
            if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "factoryRetailProductId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "storeId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "toBinId is invalid"));
            } else if (temp == -4L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record returned item inbound movement",
                                "unexpected exception occurred"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Create Successful!",
                                ""));
            }
        }
    }

    public void recordProductToBinMovement(ActionEvent event) {
        Long temp = fim.recordProductToBinMovement(factoryProductId, toBinId, status, quantity, creationDate);
        if (temp == -1L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record product to bin movement",
                                "factoryProductId is invalid"));
            } else if (temp == -2L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record product to bin movement",
                                "toBinId is invalid"));
            } else if (temp == -3L) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record product to bin movement",
                                "unexpected exception occurred"));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Create Successful!",
                                ""));
            }
    }
 
//    public long findFactoryIdByUserId(String userId) {
//        long temp = fim.findFactoryIdByUserId(userId);
//        if (temp == -1L) {
//                FacesContext.getCurrentInstance().addMessage(
//                        null,
//                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to find factory id",
//                                "userId is not a factory userId"));
//            } else if (temp == -2L) {
//                FacesContext.getCurrentInstance().addMessage(
//                        null,
//                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to find factory id",
//                                "unexpected error occurred"));
//            } else {
//                FacesContext.getCurrentInstance().addMessage(
//                        null,
//                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Factory ID found!",
//                                ""));               
//            }
//        return temp;
//    }

}
