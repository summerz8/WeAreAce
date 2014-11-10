/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.StoreRetailProductEntity;
import SessionBean.IM.StoreBinControlLocal;
import SessionBean.IM.StoreInventoryControlLocal;
import SessionBean.IM.StoreMovementControlLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "rproductInboundMovementBean")
@ViewScoped
public class RProductInboundMovementBean {

    /**
     * Creates a new instance of RProductInboundMovementBean
     */
    public RProductInboundMovementBean() {
    }
    @EJB
    StoreMovementControlLocal smcl; 
    @EJB
    StoreInventoryControlLocal sicl;
    @EJB
    StoreBinControlLocal sbcl;
    
    StoreRetailProductEntity selectedProduct;
    List<StoreRetailProductEntity> productList;
    Long storeId;
    StoreWarehouseBinEntity selectedBin;
    List<StoreWarehouseBinEntity> binList;
    
    
    Integer inventoryType;
    Double quantity;
    
    String msgPrint;
    
    @PostConstruct
    public void init(){
       storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
       productList =  sicl.getListOfStoreRetailProduct(storeId);
       binList = sbcl.getAllBackHouseBin(storeId);
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeRetailProductEntities", productList);
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sBinEntities", binList);

        
        
    }
    

    public void confrimResult(ActionEvent event){
        System.out.println("RetailProductInBoundMovementBean:confirmResult:Found");
       Integer result =  smcl.inboundMovement(storeId, selectedBin.getId(), 1, selectedProduct.getStoreRetailProductId(), quantity, inventoryType);
       if(result == 0){
           
           msgPrint = "An new record is created!";
       }
       else if(result == -1){
           
           msgPrint = "Exception. Please add again or contact system admin!";
       }
       else if (result == -3){
           
           msgPrint = "Please check again! The quantity is smaller than what you required.";
       }
       FacesContext context = FacesContext.getCurrentInstance();
       context.addMessage(null, new FacesMessage("Message", msgPrint));
       inventoryType = null;
       quantity = null;
       
        
    }

    public StoreMovementControlLocal getSmcl() {
        return smcl;
    }

    public void setSmcl(StoreMovementControlLocal smcl) {
        this.smcl = smcl;
    }

    public StoreInventoryControlLocal getSicl() {
        return sicl;
    }

    public void setSicl(StoreInventoryControlLocal sicl) {
        this.sicl = sicl;
    }

    public StoreBinControlLocal getSbcl() {
        return sbcl;
    }

    public void setSbcl(StoreBinControlLocal sbcl) {
        this.sbcl = sbcl;
    }

    public StoreRetailProductEntity getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(StoreRetailProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<StoreRetailProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<StoreRetailProductEntity> productList) {
        this.productList = productList;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public StoreWarehouseBinEntity getSelectedBin() {
        return selectedBin;
    }

    public void setSelectedBin(StoreWarehouseBinEntity selectedBin) {
        this.selectedBin = selectedBin;
    }

    public List<StoreWarehouseBinEntity> getBinList() {
        return binList;
    }

    public void setBinList(List<StoreWarehouseBinEntity> binList) {
        this.binList = binList;
    }

    public Integer getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(Integer inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMsgPrint() {
        return msgPrint;
    }

    public void setMsgPrint(String msgPrint) {
        this.msgPrint = msgPrint;
    }
    
    
    
}
