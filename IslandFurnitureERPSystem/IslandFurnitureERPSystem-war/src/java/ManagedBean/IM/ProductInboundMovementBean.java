package ManagedBean.IM;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.StoreProductEntity;
import SessionBean.IM.StoreBinControlLocal;
import SessionBean.IM.StoreInventoryControlLocal;
import SessionBean.IM.StoreMovementControlLocal;
import java.awt.ActiveEvent;
import java.io.Serializable;
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
@Named(value = "productInboundMovementBean")
@ViewScoped
public class ProductInboundMovementBean implements Serializable {

    /**
     * Creates a new instance of ProductInboundMovementBean
     */
    public ProductInboundMovementBean() {
    }
    @EJB
    StoreMovementControlLocal smcl; 
    @EJB
    StoreInventoryControlLocal sicl;
    @EJB
    StoreBinControlLocal sbcl;
    
    private StoreProductEntity selectedProduct;
    private List<StoreProductEntity> productList;
    private Long storeId;
    private StoreWarehouseBinEntity selectedBin;
    private List<StoreWarehouseBinEntity> binList;
    
    
    private Integer inventoryType;
    private Double quantity;
    
    private String msgPrint;
    
    
    private Boolean SetBin;
    
    @PostConstruct
    public void init(){
       storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
       productList =  sicl.getListOfStoreProduct(storeId);
       binList = sbcl.getAllBackHouseBin(storeId);
       SetBin = false;
       
         
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeProductEntities", null);
       
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeProductEntities", productList);
       
       
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sBinEntities", binList);

        
        
    }
    
    public void setData(StoreProductEntity sp, StoreWarehouseBinEntity sbe, Integer inventorytype, Double q){
        selectedProduct = sp;
        selectedBin = sbe;
        inventoryType = inventorytype;
        quantity = q;
   
        
    }    
    
    
    public void setSelectedPD(){
        SetBin = true;
        
        
    }
    
    public void confrimResult(ActionEvent event){
        System.out.println("ProductInBoundMovementBean:confirmResult:Found");
        
     if(selectedProduct != null && selectedBin != null){   
       Integer result =  smcl.inboundMovement(storeId, selectedBin.getId(), 0, selectedProduct.getStoreProductId(), quantity, inventoryType);
       if(result == 0){
           
           msgPrint = "An new record is created!";
           inventoryType = null;
           quantity = null;
           selectedProduct = null;
           selectedBin = null;
           SetBin = false;
       
        productList =  sicl.getListOfStoreProduct(storeId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeProductEntities", null);
       
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeProductEntities", productList);
       
           
           
       }
       else if(result == -1){
           
           msgPrint = "Exception. Please add again or contact system admin!";
       }
       else if (result == -3){
           
           msgPrint = "Please check again! The quantity is smaller than what you required.";
       }
       
     }else{
         
         msgPrint = "Please fill in all the field!";
         
     }
       FacesContext context = FacesContext.getCurrentInstance();
       context.addMessage(null, new FacesMessage("Message", msgPrint));
       
       
        
    }

    public String getMsgPrint() {
        return msgPrint;
    }

    public void setMsgPrint(String msgPrint) {
        this.msgPrint = msgPrint;
    }

    public Boolean isSetBin() {
        return SetBin;
    }

    public void setSetBin(Boolean SetBin) {
        this.SetBin = SetBin;
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

    public StoreProductEntity getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(StoreProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<StoreProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<StoreProductEntity> productList) {
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
    
    
    
    
   
}
