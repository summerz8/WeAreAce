/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import SessionBean.IM.StoreInventoryControlLocal;
import SessionBean.IM.StoreMovementControlLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author zhengyuan
 */
@Named(value = "inStoreMovementBean")
@ViewScoped
public class InStoreMovementBean {

    /**
     * Creates a new instance of InStoreMovementBean
     */
    public InStoreMovementBean() {
    }
    
    @EJB
    StoreMovementControlLocal smcl;
    StoreInventoryControlLocal sicl;
    private Integer invtype;
    private List<StoreRetailProductEntity> rpList;
    private List<StoreProductEntity> pList;
    private StoreRetailProductEntity selectedrp;
    private StoreProductEntity selectedp;
    
    private List<StoreWarehouseBinEntity> fromBins;
    private List<StoreWarehouseBinEntity> toBins;
    
    private StoreWarehouseBinEntity selectedfb;
    private StoreWarehouseBinEntity sleectedtb;
    
    private Integer oldStatus;
    private Integer newStatus;
    
    private Double Quantity;
    private Boolean skip;
    
    private Long storeId;

    
    @PostConstruct
    public void init(){
        storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");

        
        pList = sicl.getListOfStoreProduct(storeId);
        rpList = sicl.getListOfStoreRetailProduct(storeId);
//        rpList = sicl.getHaveStockRP(storeId);
//        pList = sicl.getHaveStockP(storeId);
//        
    }
    
    
    public void setInvType(){
        
        System.out.println("inStoreMovementBean inv type is set");
    }
    
    

    public void displayDetail(){
        
        
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

    public StoreWarehouseBinEntity getSelectedfb() {
        return selectedfb;
    }

    public void setSelectedfb(StoreWarehouseBinEntity selectedfb) {
        this.selectedfb = selectedfb;
    }

    public StoreWarehouseBinEntity getSleectedtb() {
        return sleectedtb;
    }

    public void setSleectedtb(StoreWarehouseBinEntity sleectedtb) {
        this.sleectedtb = sleectedtb;
    }

    public Boolean isSkip() {
        return skip;
    }

    public void setSkip(Boolean skip) {
        this.skip = skip;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
