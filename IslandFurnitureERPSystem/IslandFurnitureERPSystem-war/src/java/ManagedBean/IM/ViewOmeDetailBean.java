/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.SCM.OutboundMovementEntity;
import SessionBean.IM.StoreMovementControlLocal;
import java.io.Serializable;
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
public class ViewOmeDetailBean  implements Serializable{

    /**
     * Creates a new instance of ViewOmeDetail
     */
    public ViewOmeDetailBean() {
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
    private Boolean isRetail;
    private Boolean isfulfilled;
    private FactoryEntity factory;
    
    
    
    @PostConstruct
    public void init(){
       storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
       ome = (OutboundMovementEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedOME");
    //   ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("selectedOME");
       omeId = ome.getOutboundMovementId();
       isfulfilled = false;
       System.out.println("IsFulfilled : " + isfulfilled);
       if(ome.getStockTypeIndicator() == 2 ){
            isRetail = false;
           fp = ome.getFactoryProduct();
           storeProductId = smcl.convertProductFToS(fp.getFactoryProductId(),storeId);
           globalProductId = fp.getProduct().getProductId();  
           factory = fp.getFactory();
           
       }else {
           isRetail = true; 
           frp = ome.getFactoryRetailProduct();
           storeRProductId = smcl.convertRProductFToS(frp.getFactoryRetailProdctId(), storeId);
           globalRProductId = frp.getRetailProduct().getRetailProductId();
           factory = frp.getFactory();
           
           System.out.println("ViewOMEDetail:" + globalRProductId);
       }
       factoryId = ome.getFromBin().getFactory().getFactoryId();
       quantity = ome.getQuantity();
       actualAmount = ome.getQuantity();
       
        
    }
    
    
    
    
   public void submitFulfillment(ActionEvent event){
     try{
         
      if(actualAmount <= quantity){
           if(isRetail){
          smcl.fromFactoryGoodReceipts(omeId, storeRProductId, 1, actualAmount, storeId);

             }
          else{
          smcl.fromFactoryGoodReceipts(omeId, storeProductId, 0, actualAmount, storeId);
          }
       
           String statusMsg = "Good Receipt has been generated!";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Result : " + statusMsg, ""));
           String path = "/secured/restricted/Store/IM/ListIncomingInventories.xhtml?faces-redirect=true";
           String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
           FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);
           System.err.println("go to another page");
      
     }
      
     
     else {
               String statusMsg = "Actual Amount cannot be larger than the amount indicated! Please eneter a smaller amount. If you received extra inventory, please generate it mannually!";
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Result : " + statusMsg, ""));
 
       }    
     }       
      
      catch (Exception e){
       String statusMsg = "Exception Happend! Please try again or contact system admin";
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Result : " + statusMsg, ""));
          
      }
 
    }
    
    public void makeFulfillment(ActionEvent event){
        System.out.println("IsFulfilled : " + isfulfilled);
       isfulfilled = true; 
    } 
    

    public Boolean isIsfulfilled() {
        return isfulfilled;
    }

    public void setIsfulfilled(Boolean isfulfilled) {
        System.out.println("IsFulfilled : " + isfulfilled);
        this.isfulfilled = isfulfilled;
        System.out.println("IsFulfilled : " + isfulfilled);
    }

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factory) {
        this.factory = factory;
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
