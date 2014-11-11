/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Factory.SCM.PurchaseOrderEntity;
import SessionBean.IM.StoreMovementControlLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author zhengyuan
 */
@Named(value = "viewIncomingInventoryFromSupplier")
@ViewScoped
public class ViewIncomingInventoryFromSupplier  implements Serializable{

    /**
     * Creates a new instance of ViewIncomingInventoryFromSupplier
     */
    public ViewIncomingInventoryFromSupplier() {
    }
    
    
    @EJB
    StoreMovementControlLocal smcl;
    private List<PurchaseOrderEntity> incomingInventoryfromSupplier;
    private Long storeId;
    private PurchaseOrderEntity selectedPO;
    private PurchaseOrderEntity selectedpoe;
    
    
    @PostConstruct
    public void init(){
        storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        incomingInventoryfromSupplier = smcl.viewIncomingGoodsFromSupplier(storeId); 
    
    } 
    
    
    public Long convertRProduct (Long factoryRetailProductId){
        Long storeRetailProductId = smcl.convertRProductFToS(factoryRetailProductId, storeId);
        return storeRetailProductId;
        
    }
    
       public void ViewPODetail(PurchaseOrderEntity poe) throws IOException{
        selectedpoe = poe;
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedPOE", selectedpoe);
        String path = "/secured/restricted/Store/IM/inComingInventoryFromSupplierDetail.xhtml";
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);
        System.err.println("go to another page");
           
    }
    
    

    public StoreMovementControlLocal getSmcl() {
        return smcl;
    }

    public void setSmcl(StoreMovementControlLocal smcl) {
        this.smcl = smcl;
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

    public PurchaseOrderEntity getSelectedPO() {
        return selectedPO;
    }

    public void setSelectedPO(PurchaseOrderEntity selectedPO) {
        this.selectedPO = selectedPO;
    }

    public PurchaseOrderEntity getSelectedpoe() {
        return selectedpoe;
    }

    public void setSelectedpoe(PurchaseOrderEntity selectedpoe) {
        this.selectedpoe = selectedpoe;
    }
    
    
    
    
    
    
}
