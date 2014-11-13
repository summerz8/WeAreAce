/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.ReturnedItemMovementRecordEntity;
import SessionBean.IM.StoreBinControlLocal;
import SessionBean.IM.StoreDocumentControlLocal;
import SessionBean.IM.StoreMovementControlLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
@Named(value = "viewReturnedItem")
@ViewScoped
public class ViewReturnedItem implements Serializable{

    /**
     * Creates a new instance of ViewReturnedItem
     */
    public ViewReturnedItem() {
    }
    
    
    @EJB 
    StoreMovementControlLocal smcl;
    
    @EJB
    StoreDocumentControlLocal sdcl;
    @EJB 
    StoreBinControlLocal sbcl;
    
    
    private List<ReturnedItemMovementRecordEntity> rimrlist;
    
    private ReturnedItemMovementRecordEntity selectedRecord;
    
    private List<StoreBinProductEntity> binList;
    
    private StoreBinProductEntity selectedBin;
    
    private Long storeId;
    
    
    @PostConstruct
    public void init(){
        storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        rimrlist = sdcl.getToProcessReturnIMRE(storeId);
        binList = new ArrayList<>();

  
    }
    
        public String displayTime(Calendar calendarTime){ 
           System.err.println("Calendar Time:" + calendarTime.getTime());
           SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
           String time = sdf.format(calendarTime.getTime()).toString();
           return time;
    }
    
    public void setSelectedBin1(){
        
        binList = sbcl.ListProductReturnedBin(selectedRecord.getStoreProduct().getStoreProductId());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeBinProductEntities", binList);
        
    }
    
    public void setToBin(ActionEvent event){
        
        
       Integer result  =  smcl.handleReturnedProductFromStore(selectedRecord.getId(), selectedBin.getSwe().getId());
       if(result == 0 ){
           
           
       FacesContext context = FacesContext.getCurrentInstance();
       context.addMessage(null, new FacesMessage(null, "Updated Successfully!"));
       }
       else {
          FacesContext context = FacesContext.getCurrentInstance();
          context.addMessage(null, new FacesMessage(null, "Please try again or contact system admin!"));
      
           
       }
        
    }

    public StoreMovementControlLocal getSmcl() {
        return smcl;
    }

    public void setSmcl(StoreMovementControlLocal smcl) {
        this.smcl = smcl;
    }

    public StoreDocumentControlLocal getSdcl() {
        return sdcl;
    }

    public void setSdcl(StoreDocumentControlLocal sdcl) {
        this.sdcl = sdcl;
    }

    public StoreBinControlLocal getSbcl() {
        return sbcl;
    }

    public void setSbcl(StoreBinControlLocal sbcl) {
        this.sbcl = sbcl;
    }

    public List<ReturnedItemMovementRecordEntity> getRimrlist() {
        return rimrlist;
    }

    public void setRimrlist(List<ReturnedItemMovementRecordEntity> rimrlist) {
        this.rimrlist = rimrlist;
    }

    public ReturnedItemMovementRecordEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(ReturnedItemMovementRecordEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<StoreBinProductEntity> getBinList() {
        return binList;
    }

    public void setBinList(List<StoreBinProductEntity> binList) {
        this.binList = binList;
    }

    public StoreBinProductEntity getSelectedBin() {
        return selectedBin;
    }

    public void setSelectedBin(StoreBinProductEntity selectedBin) {
        this.selectedBin = selectedBin;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    
    
   
    
}
