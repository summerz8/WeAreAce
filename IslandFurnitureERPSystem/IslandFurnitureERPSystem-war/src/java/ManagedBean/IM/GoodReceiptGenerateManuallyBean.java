/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import SessionBean.IM.StoreBinControlLocal;
import SessionBean.IM.StoreInventoryControlLocal;
import SessionBean.IM.StoreMovementControlLocal;
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
@Named(value = "goodReceiptGenerateManually")
@ViewScoped
public class GoodReceiptGenerateManuallyBean implements Serializable{

    /**
     * Creates a new instance of GoodReceiptGenerateManually
     */
    public GoodReceiptGenerateManuallyBean() {
    }
    
    
    @EJB
    StoreInventoryControlLocal sicl;
    
    @EJB
    StoreBinControlLocal sbcl;
    
    @EJB
    StoreMovementControlLocal smcl;
    
    private Integer invtype;
    private StoreProductEntity selectedp;
    private StoreRetailProductEntity selectedrp;
    
    private List<StoreProductEntity> pList;
    private List<StoreRetailProductEntity> rpList;
    
    private List<StoreWarehouseBinEntity> binList;
    private StoreWarehouseBinEntity selectedBin;
    
    private Integer status;
    
    private Long storeId;
    
    private Boolean isSelectedType;
    
    
    private Integer selectedDestinationStatus;
    
    private Double quantity;
    
    
    @PostConstruct
    public void init(){
         storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
         pList = sicl.getListOfStoreProduct(storeId);
         rpList = sicl.getListOfStoreRetailProduct(storeId);
         binList = sbcl.getAllBackHouseBin(storeId);
         
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeProductEntities", pList);
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeRetailProductEntities", rpList);
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sBinEntities", binList);

        
    }
    
    
    public void confrimGoodReceipt(ActionEvent event){
        try{
            
       Integer result = -1;
       if(invtype == 0){
       result = smcl.generateGoodReceiptMannually(storeId,  0, selectedp.getStoreProductId(),  quantity);
       
       }
       if(invtype == 1){
           
           
       result = smcl.generateGoodReceiptMannually(storeId,  1, selectedrp.getStoreRetailProductId(),  quantity);
       
       }
       if(result == 0){
       
       String statusMsg = "Good Receipt has been generated!";
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  statusMsg, ""));
       String path = "/secured/restricted/Store/IM/ListIncomingInventories.xhtml?faces-redirect=true";
       String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
       FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);
       System.err.println("go to another page");
       }
       else{
           
       String statusMsg = "Exception Happend! Please try again or contact system admin";
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Result " + statusMsg, ""));
      
       }
      
     }
      catch (Exception e){
       String statusMsg = "Exception Happend! Please try again or contact system admin";
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Result " + statusMsg, ""));
          e.printStackTrace();
      }
        
        
    }

    public Boolean isIsSelectedType() {
        return isSelectedType;
    }

    public void setIsSelectedType(Boolean isSelectedType) {
        this.isSelectedType = isSelectedType;
    }
    
    
    public void putInvType(){
        
        isSelectedType = true;
        
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    
    
    

    public Integer getSelectedDestinationStatus() {
        return selectedDestinationStatus;
    }

    public void setSelectedDestinationStatus(Integer selectedDestinationStatus) {
        this.selectedDestinationStatus = selectedDestinationStatus;
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

    public Integer getInvtype() {
        return invtype;
    }

    public void setInvtype(Integer invtype) {
        this.invtype = invtype;
    }

    public StoreProductEntity getSelectedp() {
        return selectedp;
    }

    public void setSelectedp(StoreProductEntity selectedp) {
        this.selectedp = selectedp;
    }

    public StoreRetailProductEntity getSelectedrp() {
        return selectedrp;
    }

    public void setSelectedrp(StoreRetailProductEntity selectedrp) {
        this.selectedrp = selectedrp;
    }

    public List<StoreProductEntity> getpList() {
        return pList;
    }

    public void setpList(List<StoreProductEntity> pList) {
        this.pList = pList;
    }

    public List<StoreRetailProductEntity> getRpList() {
        return rpList;
    }

    public void setRpList(List<StoreRetailProductEntity> rpList) {
        this.rpList = rpList;
    }

    public List<StoreWarehouseBinEntity> getBinList() {
        return binList;
    }

    public void setBinList(List<StoreWarehouseBinEntity> binList) {
        this.binList = binList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    
    
    
}
