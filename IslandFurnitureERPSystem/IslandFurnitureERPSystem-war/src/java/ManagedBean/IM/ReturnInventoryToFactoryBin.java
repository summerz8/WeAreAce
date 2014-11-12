/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.IM.StoreBinRetailProductEntity;
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
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "returnInventoryToFactoryBin")
@ViewScoped
public class ReturnInventoryToFactoryBin implements Serializable{

    /**
     * Creates a new instance of ReturnInventoryToFactoryBin
     */
    public ReturnInventoryToFactoryBin() {
    }
    
    @EJB
    StoreBinControlLocal sbcl;
    @EJB
    StoreMovementControlLocal smcl;
    
    @EJB
    StoreInventoryControlLocal sicl;
    
    
    private List<StoreBinProductEntity> pbinlist;
    private List<StoreBinRetailProductEntity> rpbinlist;
    
    private StoreBinProductEntity selectedpb;
    private StoreBinRetailProductEntity selectedrp;
    
    private Long storeId;
    
    private List<StoreProductEntity> pdlist;
    private List<StoreRetailProductEntity> rpdlist;
    
    private StoreProductEntity selectedpd;
    private StoreRetailProductEntity selectedrpd;
    
    private Boolean selectedProductType;
    private Boolean selectedInventory;
    
    private Integer invtype;
    
    private Double moveoutQuantity;
    
    @PostConstruct
    public void init(){
       storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
     
       pbinlist = new ArrayList<>();
       rpbinlist = new ArrayList<>();
      selectedProductType = false;
      selectedInventory = false;
//       pdlist = new ArrayList<>();
//       rpdlist = new ArrayList<>();
      
       pdlist = sicl.getListOfStoreProduct(storeId);
        rpdlist = sicl.getListOfStoreRetailProduct(storeId);
     
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeProductEntities", pdlist);
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeRetailProductEntities", rpdlist);

        
    }
    
    
    public void setselectedpt(){
        
       selectedInventory = false;
       pbinlist = null;
       rpbinlist= null;
          
       selectedProductType = true;
       if(invtype == 0){     
           pdlist = sicl.getListOfStoreProduct(storeId);
       
       System.out.println("ManagedBean Retun Inv Selected invtype 0000");
       
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeProductEntities", pdlist);
       }
        
       if(invtype == 1){
        rpdlist = sicl.getListOfStoreRetailProduct(storeId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeRetailProductEntities", rpdlist);
  System.out.println("ManagedBean Retun Inv Selected invtype 1111");
       }  
        
    }
    
    
    public void setselectinv1(){
        
        
            
            pbinlist = sbcl.ListProductReturnedBin(selectedpd.getStoreProductId());
            System.out.println("ManagedBean Retun Inv Selected invtype 0");
            
      
        
    }
    
        
    public void setselectinv2(){
        
      
            
            rpbinlist = sbcl.ListRProductReturnedBin(selectedrpd.getStoreRetailProductId());
            System.out.println("ManagedBean Retun Inv Selected invtype 1");
        
        
    }
    
    
    
    public void setTargetMovement(StoreBinProductEntity b){
        
        selectedpb = b;
         
        System.out.println("setTargetMovementRP is set. b : " + selectedpb.getId());
        
    }
    
    public void setTargetMovementRP(StoreBinRetailProductEntity b){
        
        selectedrp = b;
        
        System.out.println("setTargetMovementRP is set. b : " + selectedrp.getId());
    }
    
    public void createOutboundMovementRecord(ActionEvent event){
        
        int result = -1;
      
        if(selectedpb != null){
          System.out.println("Print movementQuantity " + moveoutQuantity  + " orginal quantity " + selectedpb.getQuantity());  
        if(moveoutQuantity == null || moveoutQuantity > selectedpb.getQuantity() ){
            result = 1;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Message", "The amount must be between ( 0," + selectedpb.getQuantity() + " )"));   
            
                 }
        
            else{
            
          result = smcl.sendOutboundMovement(storeId, 0, selectedpb.getSwe().getId(), selectedpd.getStoreProductId(), moveoutQuantity);
             pbinlist = sbcl.ListProductReturnedBin(selectedpd.getStoreProductId());
          
              }
        
        
         }
        if(selectedrp != null){
            
            if(moveoutQuantity == null || moveoutQuantity > selectedrp.getQuantity()){
                
                 result = 1;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Message", "The amount must be between ( 0," + selectedrp.getQuantity() + " )"));   
            
                
                
            }
            
            else {
                
                result = smcl.sendOutboundMovement(storeId, 1, selectedrp.getSwe().getId(), selectedrpd.getStoreRetailProductId(), moveoutQuantity);
                
                rpbinlist = sbcl.ListRProductReturnedBin(selectedrpd.getStoreRetailProductId());
                
            }
            
            if(result == 0){
                FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Message", "An outbound movement record is created successfully!"));   
            
            
                moveoutQuantity = null;
                
                
               
                
                
            }
            if(result == -1 ){
                
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Message", "Exception! Please try again or contact system admin."));   

                
            }
            
        }
    
        
        
    }

    public StoreBinControlLocal getSbcl() {
        return sbcl;
    }

    public void setSbcl(StoreBinControlLocal sbcl) {
        this.sbcl = sbcl;
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

    public List<StoreBinProductEntity> getPbinlist() {
        return pbinlist;
    }

    public void setPbinlist(List<StoreBinProductEntity> pbinlist) {
        this.pbinlist = pbinlist;
    }

    public List<StoreBinRetailProductEntity> getRpbinlist() {
        return rpbinlist;
    }

    public void setRpbinlist(List<StoreBinRetailProductEntity> rpbinlist) {
        this.rpbinlist = rpbinlist;
    }

    public StoreBinProductEntity getSelectedpb() {
        return selectedpb;
    }

    public void setSelectedpb(StoreBinProductEntity selectedpb) {
        this.selectedpb = selectedpb;
    }

    public StoreBinRetailProductEntity getSelectedrp() {
        return selectedrp;
    }

    public void setSelectedrp(StoreBinRetailProductEntity selectedrp) {
        this.selectedrp = selectedrp;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<StoreProductEntity> getPdlist() {
        return pdlist;
    }

    public void setPdlist(List<StoreProductEntity> pdlist) {
        this.pdlist = pdlist;
    }

    public List<StoreRetailProductEntity> getRpdlist() {
        return rpdlist;
    }

    public void setRpdlist(List<StoreRetailProductEntity> rpdlist) {
        this.rpdlist = rpdlist;
    }

    public StoreProductEntity getSelectedpd() {
        return selectedpd;
    }

    public void setSelectedpd(StoreProductEntity selectedpd) {
        this.selectedpd = selectedpd;
    }

    public StoreRetailProductEntity getSelectedrpd() {
        return selectedrpd;
    }

    public void setSelectedrpd(StoreRetailProductEntity selectedrpd) {
        this.selectedrpd = selectedrpd;
    }

    public Boolean isSelectedProductType() {
        return selectedProductType;
    }

    public void setSelectedProductType(Boolean selectedProductType) {
        this.selectedProductType = selectedProductType;
    }

    public Boolean isSelectedInventory() {
        return selectedInventory;
    }

    public void setSelectedInventory(Boolean selectedInventory) {
        this.selectedInventory = selectedInventory;
    }

    public Integer getInvtype() {
        return invtype;
    }

    public void setInvtype(Integer invtype) {
        this.invtype = invtype;
    }

    public Double getMoveoutQuantity() {
        return moveoutQuantity;
    }

    public void setMoveoutQuantity(Double moveoutQuantity) {
        this.moveoutQuantity = moveoutQuantity;
    }
    
    
}
