/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import SessionBean.IM.StoreBinControlLocal;
import SessionBean.IM.StoreInventoryControlLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author zhengyuan
 */
@Named(value = "storeBinControl")
@ViewScoped
public class StoreBinControl {

    /**
     * Creates a new instance of StoreBinControl
     */
    public StoreBinControl() {
    
    }
    @EJB
    StoreBinControlLocal sbcl;
    
    
    private StoreEntity store;
    private List<StoreWarehouseBinEntity> backHouseList = new ArrayList<StoreWarehouseBinEntity>();
    private List<StoreWarehouseBinEntity> displayAreaList = new ArrayList<StoreWarehouseBinEntity>();
    private List<StoreWarehouseBinEntity> selfCollectList = new ArrayList<StoreWarehouseBinEntity>();
    
    private List<StoreWarehouseBinEntity> filteredbackHouseList = new ArrayList<StoreWarehouseBinEntity>();
    private List<StoreWarehouseBinEntity> filtereddisplayAreaList = new ArrayList<StoreWarehouseBinEntity>();
    private List<StoreWarehouseBinEntity> filteredselfCollectList = new ArrayList<StoreWarehouseBinEntity>();
    
    private String newBinName1;
    private String newRemark1;
    private String newBinName2;
    private String newRemark2;
    private String newBinName3;
    private String newRemark3;
    
    private Long storeId;
    
    private StoreWarehouseBinEntity toDeleteBin1;
    private StoreWarehouseBinEntity toDeleteBin2;
    private StoreWarehouseBinEntity toDeleteBin3;
    
    
    private String msgprint;
    private String msgprint2;
    
    @PostConstruct
    public void init(){
           
          storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
          System.out.println("testing init:" + storeId); 
          backHouseList = sbcl.getAllBackHouseBin(storeId);
          displayAreaList = sbcl.getAllDisplayAreaBin(storeId);
          selfCollectList = sbcl.getAllSelfCollectBin(storeId);
          
    }
    
    
    public void selectDelete1(StoreWarehouseBinEntity s){
        
        toDeleteBin1 = s;
        System.out.println("Delete Item:" + toDeleteBin1.getName());
    }
    
    public void selectDelete2(StoreWarehouseBinEntity s){
        
        toDeleteBin2 = s;
        System.out.println("Delete Item:" + toDeleteBin2.getName());
    }
    
    public void selectDelete3(StoreWarehouseBinEntity s){
        
        toDeleteBin3 = s;
        System.out.println("Delete Item:" + toDeleteBin3.getName());
    }
    
    
    
    public void addAbackHouseBin(){
        
        msgprint = null;
        System.out.println("newBinName"+newBinName1);
        System.out.println("newRemark"+newRemark1);
        
        int result  = sbcl.addAStorageBin(newBinName1, newRemark1, true, false, false, storeId);
        
        newBinName1 = null;
        newRemark1 = null;
        
        if(result == 1) {
            msgprint = "Added successfully!";
            backHouseList = sbcl.getAllBackHouseBin(storeId);
          displayAreaList = sbcl.getAllDisplayAreaBin(storeId);
          selfCollectList = sbcl.getAllSelfCollectBin(storeId);
        }
        
        else {
            msgprint = "Bin add failed. Exception happended. Please try again or raise a ticket!";
        }
        
        
        
    }
    
    public void addAdisplayAreaBin(){
        
        msgprint = null;
        
       int result  =  sbcl.addAStorageBin(newBinName2, newRemark2, false, true, false, storeId);
       newBinName2 = null;
        newRemark2 = null;
         if(result == 1) {
            msgprint = "Added successfully!";
            backHouseList = sbcl.getAllBackHouseBin(storeId);
          displayAreaList = sbcl.getAllDisplayAreaBin(storeId);
          selfCollectList = sbcl.getAllSelfCollectBin(storeId);
        }
        
        else {
            msgprint = "Bin add failed. Exception happended. Please try again or raise a ticket!";
        }
        
    }
    
    
    public void addASelfCollectBin(){
         msgprint = null;
         int result  = sbcl.addAStorageBin(newBinName3, newRemark3, false, false, true, storeId);
         newBinName3 = null;
        newRemark3 = null;
        
        System.out.println("AddSelfCollectionBin:" + result);
        
        if(result == 1) {
            msgprint = "Added successfully!";
            backHouseList = sbcl.getAllBackHouseBin(storeId);
          displayAreaList = sbcl.getAllDisplayAreaBin(storeId);
          selfCollectList = sbcl.getAllSelfCollectBin(storeId);
        }
        
        else {
            msgprint = "Bin add failed. Exception happended. Please try again or raise a ticket!";
        }
        
    }
    
    public void deleteAbackHouseBin(){
        msgprint2 =null;
        int result = sbcl.deleteAStorageBin(toDeleteBin1.getId());
        if(result == 1) {
            msgprint2 = "Delete successfully!";
            backHouseList = sbcl.getAllBackHouseBin(storeId);
          displayAreaList = sbcl.getAllDisplayAreaBin(storeId);
          selfCollectList = sbcl.getAllSelfCollectBin(storeId);
        }
        
        else {
            msgprint2 = "Bin delete failed. Exception happended. Please try again or raise a ticket!";
        }
        
    }
    
    public void deleteAdisplayAreaBin(){
        msgprint2 =null;
        int result = sbcl.deleteAStorageBin(toDeleteBin2.getId());
                if(result == 1) {
            msgprint2 = "Delete successfully!";
            backHouseList = sbcl.getAllBackHouseBin(storeId);
          displayAreaList = sbcl.getAllDisplayAreaBin(storeId);
          selfCollectList = sbcl.getAllSelfCollectBin(storeId);
        }
        
        else {
            msgprint2 = "Bin delete failed. Exception happended. Please try again or raise a ticket!";
        }
        
       
    }
    
    
    public void deleteASelfCollectBin(){
        msgprint2 =null;
        int result = sbcl.deleteAStorageBin(toDeleteBin3.getId());
                if(result == 1) {
            msgprint2 = "Delete successfully!";
            backHouseList = sbcl.getAllBackHouseBin(storeId);
          displayAreaList = sbcl.getAllDisplayAreaBin(storeId);
          selfCollectList = sbcl.getAllSelfCollectBin(storeId);
        }
        
        else {
            msgprint2 = "Bin delete failed. Exception happended. Please try again or raise a ticket!";
        }
        
        
    }

    public String getMsgprint2() {
        return msgprint2;
    }

    public void setMsgprint2(String msgprint2) {
        this.msgprint2 = msgprint2;
    }
    

public void onRowEdit(RowEditEvent event) {

        StoreWarehouseBinEntity entity = (StoreWarehouseBinEntity) event.getObject();
        System.out.println("onRowEdit test: " + String.valueOf(entity.getId()) + entity.getStore().getStoreId());
        
      

            FacesMessage msg = new FacesMessage("Store Bin Edited", String.valueOf(entity.getId()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((StoreWarehouseBinEntity) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }



    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public String getNewBinName1() {
        return newBinName1;
    }

    public void setNewBinName1(String newBinName1) {
        this.newBinName1 = newBinName1;
    }

    public String getNewRemark1() {
        return newRemark1;
    }

    public void setNewRemark1(String newRemark1) {
        this.newRemark1 = newRemark1;
    }

    public String getNewBinName2() {
        return newBinName2;
    }

    public void setNewBinName2(String newBinName2) {
        this.newBinName2 = newBinName2;
    }

    public String getNewRemark2() {
        return newRemark2;
    }

    public void setNewRemark2(String newRemark2) {
        this.newRemark2 = newRemark2;
    }

    public String getNewBinName3() {
        return newBinName3;
    }

    public void setNewBinName3(String newBinName3) {
        this.newBinName3 = newBinName3;
    }

    public String getNewRemark3() {
        return newRemark3;
    }

    public void setNewRemark3(String newRemark3) {
        this.newRemark3 = newRemark3;
    }



    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public StoreWarehouseBinEntity getToDeleteBin1() {
        return toDeleteBin1;
    }

    public void setToDeleteBin1(StoreWarehouseBinEntity toDeleteBin1) {
        this.toDeleteBin1 = toDeleteBin1;
    }

    public StoreWarehouseBinEntity getToDeleteBin2() {
        return toDeleteBin2;
    }

    public void setToDeleteBin2(StoreWarehouseBinEntity toDeleteBin2) {
        this.toDeleteBin2 = toDeleteBin2;
    }

    public StoreWarehouseBinEntity getToDeleteBin3() {
        return toDeleteBin3;
    }

    public void setToDeleteBin3(StoreWarehouseBinEntity toDeleteBin3) {
        this.toDeleteBin3 = toDeleteBin3;
    }

    public String getMsgprint() {
        return msgprint;
    }

    public void setMsgprint(String msgprint) {
        this.msgprint = msgprint;
    }
    
    

    public StoreBinControlLocal getSbcl() {
        return sbcl;
    }

    public void setSbcl(StoreBinControlLocal sbcl) {
        this.sbcl = sbcl;
    }

    public List<StoreWarehouseBinEntity> getBackHouseList() {
        return backHouseList;
    }

    public void setBackHouseList(List<StoreWarehouseBinEntity> backHouseList) {
        this.backHouseList = backHouseList;
    }

    public List<StoreWarehouseBinEntity> getDisplayAreaList() {
        return displayAreaList;
    }

    public void setDisplayAreaList(List<StoreWarehouseBinEntity> displayAreaList) {
        this.displayAreaList = displayAreaList;
    }

    public List<StoreWarehouseBinEntity> getSelfCollectList() {
        return selfCollectList;
    }

    public void setSelfCollectList(List<StoreWarehouseBinEntity> selfCollectList) {
        this.selfCollectList = selfCollectList;
    }

    public List<StoreWarehouseBinEntity> getFilteredbackHouseList() {
        return filteredbackHouseList;
    }

    public void setFilteredbackHouseList(List<StoreWarehouseBinEntity> filteredbackHouseList) {
        this.filteredbackHouseList = filteredbackHouseList;
    }

    public List<StoreWarehouseBinEntity> getFiltereddisplayAreaList() {
        return filtereddisplayAreaList;
    }

    public void setFiltereddisplayAreaList(List<StoreWarehouseBinEntity> filtereddisplayAreaList) {
        this.filtereddisplayAreaList = filtereddisplayAreaList;
    }

    public List<StoreWarehouseBinEntity> getFilteredselfCollectList() {
        return filteredselfCollectList;
    }

    public void setFilteredselfCollectList(List<StoreWarehouseBinEntity> filteredselfCollectList) {
        this.filteredselfCollectList = filteredselfCollectList;
    }
    
 
    
}
