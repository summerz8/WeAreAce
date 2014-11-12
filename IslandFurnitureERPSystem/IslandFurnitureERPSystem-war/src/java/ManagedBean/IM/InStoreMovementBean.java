/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.IM.StoreBinRetailProductEntity;
import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import SessionBean.IM.StoreBinControlLocal;
import SessionBean.IM.StoreInventoryControlLocal;
import SessionBean.IM.StoreMovementControlLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


/**
 *
 * @author zhengyuan
 */
@Named(value = "inStoreMovementBean")
@ViewScoped
public class InStoreMovementBean implements Serializable  {

    /**
     * Creates a new instance of InStoreMovementBean
     */
    public InStoreMovementBean() {
    }
    
    @EJB
    StoreMovementControlLocal smcl;
    
    @EJB
    StoreInventoryControlLocal sicl;
    
    @EJB
    StoreBinControlLocal sbcl;
    
    private Integer invtype;
    private List<StoreRetailProductEntity> rpList;
    private List<StoreProductEntity> pList;
    private StoreRetailProductEntity selectedrp;
    private StoreProductEntity selectedp;
    
    private List<StoreWarehouseBinEntity> fromBins;
    
    
    
    private List<StoreBinProductEntity> fromBinP;
    private List<StoreBinRetailProductEntity> fromBinRP;
    
    private List<StoreWarehouseBinEntity> toBins;
    
    private StoreBinProductEntity selectedfb;
    private StoreBinRetailProductEntity sleectedtb;
    
    private StoreWarehouseBinEntity selectedTb;
    
    private Integer oldStatus;
    private Integer newStatus;
    
    private Double Quantity;
    private Boolean skip;
    
    private Long storeId;
    
    private Boolean finishedSelectFromBin;
    
    
    private List<Integer> destinationBinType;
    private List<Integer> destinationBinStatus;
    
    private Integer selectedDestinationBinType;
    
    private Integer selectedDestinationStatus;
    
    private String msgprint;

    
    @PostConstruct
    public void init(){
        storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        
        System.out.println("print out storeId" + storeId);
        
//        pList = sicl.getListOfStoreProduct(storeId);
//        rpList = sicl.getListOfStoreRetailProduct(storeId);
        
         
        pList = sicl.getHaveStockP(storeId);
        rpList = sicl.getHaveStockRP(storeId);
        finishedSelectFromBin = false;
        destinationBinType = new ArrayList<>();
        destinationBinStatus = new ArrayList<>();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeProductEntities", pList);
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeRetailProductEntities", rpList);
        
          
       
    }
    
    public void getAvailableBinList(){
        
        
        finishedSelectFromBin = false;
        
        System.out.println("InStoreMovementBean: getAvailableBinList");
        if(invtype == 0 & selectedp != null){
          fromBinP =  sicl.getProductStorageInformation(selectedp.getStoreProductId());
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeBinProductEntities", fromBinP);
          

           // display the bin information
        }
        else if (invtype ==1 & selectedrp != null){
           System.out.println("Testing");
           fromBinRP = sicl.getRProductStorageInformation(selectedrp.getStoreRetailProductId());
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeBinRetailProductEntities", fromBinRP);
          
        }
        
        
    }
    
    //0 warehouseBin
    //1 displayArea
    //2 selfservice
    public void selectFromBin(){
        
        
        if(fromBinP != null ||sleectedtb !=null ){
            finishedSelectFromBin = true;
        
        destinationBinType.clear();
        
        if(invtype == 0 && selectedp.getSelfPick()){
                destinationBinType.add(0);
              //  destinationBinType.add(1);
         }else if(invtype == 1){
             
             destinationBinType.add(0);
             destinationBinType.add(1);
             
         }
             else{
            destinationBinType.add(0);
            destinationBinType.add(1);
            destinationBinType.add(2);
    
        }
        
        }
        
       
        
        
    }
    
    
    
    
    
    public void setDestinationType(){
        destinationBinStatus.clear();
        
         if(selectedDestinationBinType == 0){
           toBins =  sbcl.getAllBackHouseBin(storeId); 
           
           destinationBinStatus.add(0);
           destinationBinStatus.add(1);
           
            
        }
         if (selectedDestinationBinType == 1){
            toBins = sbcl.getAllDisplayAreaBin(storeId);
           destinationBinStatus.add(0);
          
        }
        
         if (selectedDestinationBinType == 2){
            toBins = sbcl.getAllSelfCollectBin(storeId);
            destinationBinStatus.add(0);
           
           
        }
         
         
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sBinEntities", toBins);
           
        
        
    }
    
    public String convertStatus(Integer status){
        
        if(status == 0 ){
            
            return "Unrestricted";
        }
        if(status == 1){
            
            return "Returned";
        }
        
        return null;
    }
    
    public String convertDestinationType(Integer type){
        
        if(type == 0){
            
            return "Back House Bin";
        }
        else if (type == 1){
            
            return "Display Area Bin";
        }
        
        else if (type == 2){
            
            return "Self-Collection Bin";
        }
        return null;
    }

    public void setInvType(){
        
        System.out.println("inStoreMovementBean inv type is set Value :" + invtype);
        finishedSelectFromBin = false;
    }
    
    
    public void setSelectedDestinationBin(StoreWarehouseBinEntity swb){
        System.out.println("setSelectedDestinationBin is set");
        selectedTb = swb;
        System.out.println("setSelectedDestinationBin is set: Bin ID " + swb.getId());
        
    }
    
    public void confrimMovement(ActionEvent event){
        System.out.println("InStoreMovementBean: confrimMovement:start");
        Integer result;
        if(selectedTb != null){
            
            System.out.println("InStoreMovementBean: confrimMovement:add");
            if(invtype == 0){
         result =  smcl.inStoreMovement(storeId, 0,selectedp.getStoreProductId() , selectedfb.getStatus(), selectedDestinationStatus, selectedfb.getSwe().getId(),selectedTb.getId(), Quantity);
            }
            else{
          result =   smcl.inStoreMovement(storeId, 1, selectedrp.getStoreRetailProductId(), sleectedtb.getStatus(), selectedDestinationStatus, sleectedtb.getSwe().getId(),selectedTb.getId(), Quantity);
          
                
            }
            if(result == 0){
                
                msgprint = "An Instore Movement record is created succefully!";
                invtype = null;
               selectedrp = null;
               selectedp = null;
               selectedTb = null;
               Quantity = null;
               finishedSelectFromBin = false;
               
            }
            else if(result == -1){
                msgprint = "You move within the same bin. Nothing Changed.";
            }
            else if (result == -2){
                
                msgprint = "Your transferred amount is more than available quantity.";
            }
            else if(result == -3 ){
                
                msgprint = "Self-Collection item cannot move to display area.";
            }
            else{
                
                msgprint = "Exception! Please try again or contact system admin!";
            }
            
            
        }
        else{
       msgprint = "Information incomplete!";
       
            
        }
        
       
        
        
       FacesContext context = FacesContext.getCurrentInstance();
       context.addMessage(null, new FacesMessage("Message", msgprint)); 
        
        
        
    }
    

    

    public List<Integer> getDestinationBinType() {
        return destinationBinType;
    }

    public void setDestinationBinType(List<Integer> destinationBinType) {
        this.destinationBinType = destinationBinType;
    }
    
    
    
    

    public StoreWarehouseBinEntity getSelectedTb() {
        return selectedTb;
    }

    public void setSelectedTb(StoreWarehouseBinEntity selectedTb) {
        this.selectedTb = selectedTb;
    }
   
    
    
    public StoreMovementControlLocal getSmcl() {
        return smcl;
    }

    public void setSmcl(StoreMovementControlLocal smcl) {
        this.smcl = smcl;
    }

    public Integer getInvtype() {
        return invtype;
    }

    public void setInvtype(Integer invtype) {
        this.invtype = invtype;
    }

    public List<StoreRetailProductEntity> getRpList() {
        return rpList;
    }

    public void setRpList(List<StoreRetailProductEntity> rpList) {
        this.rpList = rpList;
    }

    public List<StoreProductEntity> getpList() {
        return pList;
    }

    public void setpList(List<StoreProductEntity> pList) {
        this.pList = pList;
    }

    public StoreRetailProductEntity getSelectedrp() {
        return selectedrp;
    }

    public void setSelectedrp(StoreRetailProductEntity selectedrp) {
        this.selectedrp = selectedrp;
    }

    public StoreProductEntity getSelectedp() {
        return selectedp;
    }

    public void setSelectedp(StoreProductEntity selectedp) {
        this.selectedp = selectedp;
    }

    public List<StoreWarehouseBinEntity> getFromBins() {
        return fromBins;
    }

    public void setFromBins(List<StoreWarehouseBinEntity> fromBins) {
        this.fromBins = fromBins;
    }

    public List<StoreWarehouseBinEntity> getToBins() {
        return toBins;
    }

    public void setToBins(List<StoreWarehouseBinEntity> toBins) {
        this.toBins = toBins;
    }

    public Integer getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    public Double getQuantity() {
        return Quantity;
    }

    public void setQuantity(Double Quantity) {
        this.Quantity = Quantity;
    }

    public StoreBinProductEntity getSelectedfb() {
        return selectedfb;
    }

    public void setSelectedfb(StoreBinProductEntity selectedfb) {
        this.selectedfb = selectedfb;
    }

    public StoreBinRetailProductEntity getSleectedtb() {
        return sleectedtb;
    }

    public void setSleectedtb(StoreBinRetailProductEntity sleectedtb) {
        this.sleectedtb = sleectedtb;
    }

    public Boolean isFinishedSelectFromBin() {
        return finishedSelectFromBin;
    }

    public void setFinishedSelectFromBin(Boolean finishedSelectFromBin) {
        this.finishedSelectFromBin = finishedSelectFromBin;
    }

    
    
    


    public Boolean isSkip() {
        return skip;
    }

    public void setSkip(Boolean skip) {
        this.skip = skip;
    }

    public StoreInventoryControlLocal getSicl() {
        return sicl;
    }

    public void setSicl(StoreInventoryControlLocal sicl) {
        this.sicl = sicl;
    }

    public List<StoreBinProductEntity> getFromBinP() {
        return fromBinP;
    }

    public void setFromBinP(List<StoreBinProductEntity> fromBinP) {
        this.fromBinP = fromBinP;
    }

    public List<StoreBinRetailProductEntity> getFromBinRP() {
        return fromBinRP;
    }

    public void setFromBinRP(List<StoreBinRetailProductEntity> fromBinRP) {
        this.fromBinRP = fromBinRP;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Integer getSelectedDestinationBinType() {
        return selectedDestinationBinType;
    }

    public void setSelectedDestinationBinType(Integer selectedDestinationBinType) {
        this.selectedDestinationBinType = selectedDestinationBinType;
    }

    public Integer getSelectedDestinationStatus() {
        return selectedDestinationStatus;
    }

    public void setSelectedDestinationStatus(Integer selectedDestinationStatus) {
        this.selectedDestinationStatus = selectedDestinationStatus;
    }

    public StoreBinControlLocal getSbcl() {
        return sbcl;
    }

    public void setSbcl(StoreBinControlLocal sbcl) {
        this.sbcl = sbcl;
    }

    public String getMsgprint() {
        return msgprint;
    }

    public void setMsgprint(String msgprint) {
        this.msgprint = msgprint;
    }

    public List<Integer> getDestinationBinStatus() {
        return destinationBinStatus;
    }

    public void setDestinationBinStatus(List<Integer> destinationBinStatus) {
        this.destinationBinStatus = destinationBinStatus;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
