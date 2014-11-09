/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.SCM.OutboundMovementEntity;
import SessionBean.IM.StoreMovementControlLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zhengyuan
 */
@Named(value = "viewOmeDetail")
@ViewScoped
public class ViewOmeDetail {

    /**
     * Creates a new instance of ViewOmeDetail
     */
    public ViewOmeDetail() {
    }
    
    @EJB
    StoreMovementControlLocal smcl;
    private Long storeId;
    private OutboundMovementEntity ome;
    private Long omeId;
    private FactoryRetailProductEntity frp;
    private FactoryProductEntity fp;
    private Long factoryId;
    private Long storeProductId;
    private Long globalProductId;
    private Long storeRProductId;
    private Long globalRProductId;
    private Double quantity;
    private Double actualAmount;
    private Boolean isRetail = false;
    private Boolean isfulfilled = false;
    
    
    @PostConstruct
    public void init(){
       storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
       ome = (OutboundMovementEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedOME");
       ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("selectedOME");
       omeId = ome.getOutboundMovementId();
       if(ome.getStockTypeIndicator() == 2 ){
           fp = ome.getFactoryProduct();
           storeProductId = smcl.convertProductFToS(fp.getFactoryProductId(),storeId);
           globalProductId = fp.getProduct().getProductId();        
           
       }else {
           isRetail = true; 
           frp = ome.getFactoryRetailProduct();
           storeRProductId = smcl.convertRProductFToS(frp.getFactoryRetailProdctId(), storeId);
           globalProductId = frp.getRetailProduct().getRetailProductId();
       }
       factoryId = ome.getFromBin().getFactory().getFactoryId();
       quantity = ome.getQuantity();
       actualAmount = ome.getQuantity();
       
        
    }
    
    
    
    
    public void submitFulfillment(ActionEvent event){
      try{
        if(isRetail){
      smcl.fromFactoryGoodReceipts(storeRProductId, 1, actualAmount, storeId);
      
      }
      else{
      smcl.fromFactoryGoodReceipts(storeProductId, 0, actualAmount, storeId);
      }
       String statusMsg = "Good Receipt has been generated!";
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout result " + statusMsg, ""));
      }
      catch (Exception e){
       String statusMsg = "Exception Happend! Please try again or contact system admin";
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout result " + statusMsg, ""));
          
      }
 
    }
    
    public void makeFulfillment(ActionEvent event){
       isfulfilled = true;   
    } 
    

    public Boolean isIsfulfilled() {
        return isfulfilled;
    }

    public void setIsfulfilled(Boolean isfulfilled) {
        this.isfulfilled = isfulfilled;
    }

    
    
    
    public Boolean isIsRetail() {
        return isRetail;
    }

    public void setIsRetail(Boolean isRetail) {
        this.isRetail = isRetail;
    }
 
    
    public StoreMovementControlLocal getSmcl() {
        return smcl;
    }

    public void setSmcl(StoreMovementControlLocal smcl) {
        this.smcl = smcl;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public OutboundMovementEntity getOme() {
        return ome;
    }

    public void setOme(OutboundMovementEntity ome) {
        this.ome = ome;
    }

    public Long getOmeId() {
        return omeId;
    }

    public void setOmeId(Long omeId) {
        this.omeId = omeId;
    }

    public FactoryRetailProductEntity getFrp() {
        return frp;
    }

    public void setFrp(FactoryRetailProductEntity frp) {
        this.frp = frp;
    }

    public FactoryProductEntity getFp() {
        return fp;
    }

    public void setFp(FactoryProductEntity fp) {
        this.fp = fp;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Long getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(Long storeProductId) {
        this.storeProductId = storeProductId;
    }

    public Long getGlobalProductId() {
        return globalProductId;
    }

    public void setGlobalProductId(Long globalProductId) {
        this.globalProductId = globalProductId;
    }

    public Long getStoreRProductId() {
        return storeRProductId;
    }

    public void setStoreRProductId(Long storeRProductId) {
        this.storeRProductId = storeRProductId;
    }

    public Long getGlobalRProductId() {
        return globalRProductId;
    }

    public void setGlobalRProductId(Long globalRProductId) {
        this.globalRProductId = globalRProductId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    
    
}
