/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Factory.SCM.OutboundMovementEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Store.StoreProductEntity;
import SessionBean.IM.StoreInventoryControl;
import SessionBean.IM.StoreMovementControlLocal;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "viewIncomingInventoryList")
@ViewScoped
public class ViewIncomingInventoryList {

    /**
     * Creates a new instance of ViewIncomingInventoryList
     */
    public ViewIncomingInventoryList() {
    }
    
    @EJB
    StoreMovementControlLocal smcl;
    
    
    private List<OutboundMovementEntity> incomingInventoryfromFactory;
    private List<PurchaseOrderEntity> incomingInventoryfromSupplier;
    private Long storeId;
    
    private OutboundMovementEntity selectedome;
    
    
    @PostConstruct
    public void init(){

        storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        incomingInventoryfromFactory = smcl.viewIncomingGoodsFromFactory(storeId);
       
        
    }

    
    
    public Long convertProduct(Long factoryProductId){
        Long storeProductId = smcl.convertProductFToS(factoryProductId, storeId);
        return storeProductId;
    }
    
    public Long convertRProduct (Long factoryRetailProductId){
        Long storeRetailProductId = smcl.convertRProductFToS(factoryRetailProductId, storeId);
        return storeRetailProductId;
        
    }
    
    public void viewOMRDetail (OutboundMovementEntity ome) throws IOException{
       selectedome = ome;
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedOME", selectedome);
        String path = "/secured/restricted/Store/IM/inComingInventoryFromFacoryDetail.xhtml";
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);
        System.err.println("go to another page");
 
    }

    public OutboundMovementEntity getSelectedome() {
        return selectedome;
    }

    public void setSelectedome(OutboundMovementEntity selectedome) {
        this.selectedome = selectedome;
    }
    

    

    
    public StoreMovementControlLocal getSmcl() {
        return smcl;
    }

    public void setSmcl(StoreMovementControlLocal smcl) {
        this.smcl = smcl;
    }

    public List<OutboundMovementEntity> getIncomingInventoryfromFactory() {
        return incomingInventoryfromFactory;
    }

    public void setIncomingInventoryfromFactory(List<OutboundMovementEntity> incomingInventoryfromFactory) {
        this.incomingInventoryfromFactory = incomingInventoryfromFactory;
    }

    public List<PurchaseOrderEntity> getIncomingInventoryfromSupplier() {
        return incomingInventoryfromSupplier;
    }

    public void setIncomingInventoryfromSupplier(List<PurchaseOrderEntity> incomingInventoryfromSupplier) {
        this.incomingInventoryfromSupplier = incomingInventoryfromSupplier;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    
    
    
    
    
    
}
