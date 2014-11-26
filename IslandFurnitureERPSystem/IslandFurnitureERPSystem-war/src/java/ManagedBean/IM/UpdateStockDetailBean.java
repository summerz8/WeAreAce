/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.StoreProductEntity;
import SessionBean.IM.StoreBinControlLocal;
import SessionBean.IM.StoreInventoryControlLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

/**
 *
 * @author zhengyuan
 */
@Named(value = "updateStockDetailBean")
@ViewScoped
public class UpdateStockDetailBean implements Serializable {

    /**
     * Creates a new instance of UpdateStockDetailBean
     */
    public UpdateStockDetailBean() {
    }
    
    @EJB
    StoreInventoryControlLocal sicl;
    
    @EJB
    StoreBinControlLocal sbcl;
    
    private Long storeId;
    private StoreProductEntity storePd;
    private List<StoreBinProductEntity> binlist;
    
    
    @PostConstruct
    public void init(){
        
        
          storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
           storePd = (StoreProductEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedStoreProductToUpdate");
           binlist = sicl.listAllAvailBin(storePd.getStoreProductId());
        
      
      
    }

    public StoreInventoryControlLocal getSicl() {
        return sicl;
    }

    public void setSicl(StoreInventoryControlLocal sicl) {
        this.sicl = sicl;
    }

    public StoreBinControlLocal getSbcl() {
        return sbcl;
    }

    public void setSbcl(StoreBinControlLocal sbcl) {
        this.sbcl = sbcl;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public StoreProductEntity getStorePd() {
        return storePd;
    }

    public void setStorePd(StoreProductEntity storePd) {
        this.storePd = storePd;
    }

    public List<StoreBinProductEntity> getBinlist() {
        return binlist;
    }

    public void setBinlist(List<StoreBinProductEntity> binlist) {
        this.binlist = binlist;
    }
    
    
    
    
    
    
    
    
}
