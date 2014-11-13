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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "updateStockBean")
@ViewScoped
public class UpdateStockBean implements Serializable {

    /**
     * Creates a new instance of UpdateStockBean
     */
    public UpdateStockBean() {
    }
    
    
    
    @EJB
    StoreInventoryControlLocal sicl;
    
    
    
    private List<StoreRetailProductEntity> rplist;
    private List<StoreProductEntity> plist;
    
    
    private StoreRetailProductEntity selectedrp;
    private StoreProductEntity selectedp;
    
    
    private List<StoreBinProductEntity> pbinlist;
    
    private List<StoreBinRetailProductEntity> rpbinlist;
    
    private Double onTheAirInventory;
    
    private Long storeId;
    
    private Long updatedInventory;
    
    private Boolean isSelected;
    
    
    @PostConstruct
    public void init(){
        storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        plist = sicl.getNonSelfCollectProduct(storeId);
        rplist = sicl.getRetailProduct(storeId);
        pbinlist = new ArrayList<>();
        rpbinlist = new ArrayList<>();
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeProductEntities", plist);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("storeRetailProductEntities", rplist);

        
        
    }
    
    public void setSelectedRP(){
        
        
        
        System.out.println("selected Retail Product");
        rpbinlist = sicl.listAllAvailBinRP(selectedrp.getStoreRetailProductId());
        
        
    }
    
    
    public void setselectedP(){
        
        
        System.out.println("Selected Product");
        pbinlist = sicl.listAllAvailBin(selectedp.getStoreProductId());
    
    
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
    
    
    public void updateValueCheck() throws IOException{
        
        if(selectedp != null ){
        
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedStoreProductToUpdate", selectedp);
        String path = "/secured/restricted/Store/IM/UpdateSelectedPDetail.xhtml";
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);
        
        }
        else{
           String statusMsg = "Please Select A Product!";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Result : " + statusMsg, ""));
    
        }
        
        
        
    }
    

    public StoreInventoryControlLocal getSicl() {
        return sicl;
    }

    public void setSicl(StoreInventoryControlLocal sicl) {
        this.sicl = sicl;
    }

    public List<StoreRetailProductEntity> getRplist() {
        return rplist;
    }

    public void setRplist(List<StoreRetailProductEntity> rplist) {
        this.rplist = rplist;
    }

    public List<StoreProductEntity> getPlist() {
        return plist;
    }

    public void setPlist(List<StoreProductEntity> plist) {
        this.plist = plist;
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

    public Double getOnTheAirInventory() {
        return onTheAirInventory;
    }

    public void setOnTheAirInventory(Double onTheAirInventory) {
        this.onTheAirInventory = onTheAirInventory;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getUpdatedInventory() {
        return updatedInventory;
    }

    public void setUpdatedInventory(Long updatedInventory) {
        this.updatedInventory = updatedInventory;
    }
    
    
    
    
    
    
    
}
