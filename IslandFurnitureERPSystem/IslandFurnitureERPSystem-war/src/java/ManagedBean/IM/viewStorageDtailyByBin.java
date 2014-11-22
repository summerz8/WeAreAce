/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.IM.StoreBinRetailProductEntity;
import Entity.Store.IM.StoreWarehouseBinEntity;
import SessionBean.IM.StoreBinControlLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "viewStorageDtailyByBin")
@ViewScoped
public class viewStorageDtailyByBin {

    /**
     * Creates a new instance of viewStorageDtailyByBin
     */
    public viewStorageDtailyByBin() {
    }
    
    
    @EJB
    StoreBinControlLocal sbcl;
    
    private List<StoreWarehouseBinEntity> backhouseBinList;
    private List<StoreWarehouseBinEntity> selfcollectBinList;
    private List<StoreWarehouseBinEntity> displayareaBinList;
    
    private StoreWarehouseBinEntity selectedBin;
    
    private List<StoreBinProductEntity> selectedsbp;
    private List<StoreBinRetailProductEntity> selectedsbrp;
    
    private Long storeId;
    
    private Boolean binTypeSelected;
    private Boolean binSelected;
    
    private Integer selectedBinType;
    
    
    @PostConstruct
    public void init(){
      storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
       backhouseBinList = sbcl.getAllBackHouseBin(storeId);
       System.out.println("backhouseBinList " + backhouseBinList.size() );
       displayareaBinList = sbcl.getAllDisplayAreaBin(storeId);
       System.out.println("displayareaBinList " + displayareaBinList.size());
       selfcollectBinList = sbcl.getAllSelfCollectBin(storeId);
       System.out.println("selfcollectBinList " +  selfcollectBinList.size());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sBinEntities", null );
    
       
       selectedsbp = new ArrayList<>();
       selectedsbrp = new ArrayList<>();
       binTypeSelected = false;
       binSelected = false;

    }
    
    
  public void selectBinType(){
      
      binTypeSelected = true;
      binSelected = false;
      
     FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("sBinEntities");
  
      
      if(selectedBinType == 0){
          
          System.out.println("bin type 0");
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sBinEntities", backhouseBinList );
      }
      if(selectedBinType == 1 ){
         System.out.println("bin type 1"); 
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sBinEntities", displayareaBinList );
      
      }
      if(selectedBinType == 2){
          System.out.println("bin type 2");
         
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sBinEntities", selfcollectBinList);
         
          
      }
  }    
  
  
  
  public void selectBin(){
      if(selectedBin != null){
      binSelected = true;
      
      
      selectedsbp = (List<StoreBinProductEntity>) selectedBin.getStoreBinProducts();
      selectedsbrp = (List<StoreBinRetailProductEntity>) selectedBin.getStoreBinRetailProducts();
      
      System.out.println("viewStorageDtailyByBin() bpe size: " + selectedsbp.size());
      
      }
  }
  
  
      public String checkPStatus(StoreBinProductEntity sbp){
        if(sbp.getStatus() == 0){
            
            return "Unrestricted";
        }
        if(sbp.getStatus() == 1){
            
            return "Returned";
        }
        
        return null;
        
    }
    
      public String checkRPStatus(StoreBinRetailProductEntity sbrp){
        if(sbrp.getStatus() == 0){
            
            return "Unrestricted";
        }
        if(sbrp.getStatus() == 1){
            
            return "Returned";
        }
        
        return null;
        
        
    }

    public StoreBinControlLocal getSbcl() {
        return sbcl;
    }

    public void setSbcl(StoreBinControlLocal sbcl) {
        this.sbcl = sbcl;
    }

    public List<StoreWarehouseBinEntity> getBackhouseBinList() {
        return backhouseBinList;
    }

    public void setBackhouseBinList(List<StoreWarehouseBinEntity> backhouseBinList) {
        this.backhouseBinList = backhouseBinList;
    }

    public List<StoreWarehouseBinEntity> getSelfcollectBinList() {
        return selfcollectBinList;
    }

    public void setSelfcollectBinList(List<StoreWarehouseBinEntity> selfcollectBinList) {
        this.selfcollectBinList = selfcollectBinList;
    }

    public List<StoreWarehouseBinEntity> getDisplayareaBinList() {
        return displayareaBinList;
    }

    public void setDisplayareaBinList(List<StoreWarehouseBinEntity> displayareaBinList) {
        this.displayareaBinList = displayareaBinList;
    }

    public StoreWarehouseBinEntity getSelectedBin() {
        return selectedBin;
    }

    public void setSelectedBin(StoreWarehouseBinEntity selectedBin) {
        this.selectedBin = selectedBin;
    }

    public List<StoreBinProductEntity> getSelectedsbp() {
        return selectedsbp;
    }

    public void setSelectedsbp(List<StoreBinProductEntity> selectedsbp) {
        this.selectedsbp = selectedsbp;
    }

    public List<StoreBinRetailProductEntity> getSelectedsbrp() {
        return selectedsbrp;
    }

    public void setSelectedsbrp(List<StoreBinRetailProductEntity> selectedsbrp) {
        this.selectedsbrp = selectedsbrp;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Boolean isBinTypeSelected() {
        return binTypeSelected;
    }

    public void setBinTypeSelected(Boolean binTypeSelected) {
        this.binTypeSelected = binTypeSelected;
    }

    public Boolean isBinSelected() {
        return binSelected;
    }

    public void setBinSelected(Boolean binSelected) {
        this.binSelected = binSelected;
    }

    public Integer getSelectedBinType() {
        return selectedBinType;
    }

    public void setSelectedBinType(Integer selectedBinType) {
        this.selectedBinType = selectedBinType;
    }
  
 
  
  
    
    
}
