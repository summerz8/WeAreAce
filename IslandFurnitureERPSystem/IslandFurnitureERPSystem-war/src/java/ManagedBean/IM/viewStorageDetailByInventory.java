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
import SessionBean.IM.StoreInventoryControlLocal;
import java.io.Serializable;
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
@Named(value = "viewStorageDetailByInventory")
@ViewScoped
public class viewStorageDetailByInventory implements Serializable{

    /**
     * Creates a new instance of viewStorageDetailByInventory
     */
    public viewStorageDetailByInventory() {
    }
    
    @EJB
    StoreInventoryControlLocal sicl;
    
    private Integer invtype;
    private Long storeId;
    private List<StoreProductEntity> splist;
    private List<StoreRetailProductEntity> srplist;
    private StoreProductEntity selectedsp;
    private StoreRetailProductEntity selectedsrp;
    
    private List<StoreBinProductEntity> pbinlist;
    private List<StoreBinRetailProductEntity> rpbinlist;
    
    private Boolean isSelectedType;
    
    private Boolean invIsSelected;
    
    @PostConstruct
    public void init(){
         storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
         splist = sicl.getListOfStoreProduct(storeId);
         srplist = sicl.getListOfStoreRetailProduct(storeId);
         isSelectedType = true;
         pbinlist = new ArrayList<>();
         rpbinlist = new ArrayList<>();
         invIsSelected = false;
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeProductEntities", splist);
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeRetailProductEntities", srplist);
       
        
    }
    
    
    public void setSelectedType (){
        invIsSelected = false;
        
        isSelectedType = true;
        System.out.println("setSelectedType");
        
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
    
    public String chekcBinStatus(StoreWarehouseBinEntity swe){
        
        if(swe.isIsBackHouse()){
            
            return "Back House Bin";
        }
        
        if(swe.isIsDisplayArea()){
            
            return "Display Area Bin";
        }
        
        if(swe.isIsSelfCollect()){
            
            
            return "Self Collection Bin";
        }
       return "N.A";   
    }
    
    
    public void setInvIsSelected(){
        
        
        invIsSelected = true;
        
        if(invtype == 0){
        setPBinList();
        
        }
        if(invtype == 1){
        setRPBinList();
        }
    }
    
    
    public void setPBinList(){
        
        
        pbinlist = sicl.getProductStorageInformation(selectedsp.getStoreProductId());
    
    }
    
    public void setRPBinList(){
        
        rpbinlist = sicl.getRProductStorageInformation(selectedsrp.getStoreRetailProductId());
        
    }

    public StoreInventoryControlLocal getSicl() {
        return sicl;
    }

    public void setSicl(StoreInventoryControlLocal sicl) {
        this.sicl = sicl;
    }

    public Integer getInvtype() {
        return invtype;
    }

    public void setInvtype(Integer invtype) {
        this.invtype = invtype;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<StoreProductEntity> getSplist() {
        return splist;
    }

    public void setSplist(List<StoreProductEntity> splist) {
        this.splist = splist;
    }

    public List<StoreRetailProductEntity> getSrplist() {
        return srplist;
    }

    public void setSrplist(List<StoreRetailProductEntity> srplist) {
        this.srplist = srplist;
    }

    public StoreProductEntity getSelectedsp() {
        return selectedsp;
    }

    public void setSelectedsp(StoreProductEntity selectedsp) {
        this.selectedsp = selectedsp;
    }

    public StoreRetailProductEntity getSelectedsrp() {
        return selectedsrp;
    }

    public void setSelectedsrp(StoreRetailProductEntity selectedsrp) {
        this.selectedsrp = selectedsrp;
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

    public Boolean isIsSelectedType() {
        return isSelectedType;
    }

    public void setIsSelectedType(Boolean isSelectedType) {
        this.isSelectedType = isSelectedType;
    }

    public Boolean isInvIsSelected() {
        return invIsSelected;
    }

    public void setInvIsSelected(Boolean invIsSelected) {
        this.invIsSelected = invIsSelected;
    }
    
    
    
    
}
