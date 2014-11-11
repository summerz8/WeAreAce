/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Factory.SCM.DeliveryOrderEntity;
import Entity.Factory.SCM.OutboundMovementEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import SessionBean.IM.StoreMovementControlLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@Named(value = "viewPoBean")
@ViewScoped
public class viewPoBean implements Serializable {

    /**
     * Creates a new instance of viewPoBean
     */
    public viewPoBean() {
    }
    
    @EJB 
    StoreMovementControlLocal smcl;
    
    
    private PurchaseOrderEntity po;
    private Long storeId;
    private List<DeliveryOrderEntity> dolist;
    private DeliveryOrderEntity selectedDO;
    private Double newQuantity;
     private Long storeRPId;
    
    
    @PostConstruct
    public void init(){
       storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
       po = (PurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedPOE");
       storeRPId = smcl.convertRProductFToS(po.getContract().getFactoryRetailProduct().getFactoryRetailProdctId(), storeId);
       
        dolist =  (List<DeliveryOrderEntity>) po.getDeliveryOrderList();
      
        
    }
    
    
    public Boolean checkIsFulfilled(DeliveryOrderEntity doee){
       
        return !doee.getStatus().equals("waiting");
    }
    
    
    public String displayTime(Calendar calendarTime){ 
           System.err.println("Calendar Time:" + calendarTime.getTime());
           SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
           String time = sdf.format(calendarTime.getTime()).toString();
           return time;
    }
    
    public void selectDOE(DeliveryOrderEntity po){
        
        selectedDO  = po;
        newQuantity = po.getAmount();
        
    }
    
    
    
    
    
    
    
    public void receiveGoodFromSupplier(ActionEvent event){
       try{ 
        if(newQuantity > selectedDO.getAmount()){
               String statusMsg = "Actual Amount cannot be larger than the amount indicated! Please eneter a smaller amount. If you received extra inventory, please generate it mannually!";
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Result : " + statusMsg, ""));
 
            
            
        }
        else{
            
           smcl.fromSupplierGoodReceipt(po.getId(), storeId, 1, storeRPId , selectedDO.getId(), newQuantity);
            
            
            
           String statusMsg = "A good receipt has been generated!";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Result : " + statusMsg, ""));
       
            
        }
        
       }
       catch (Exception e){
            String statusMsg = "Exception Happend! Please try again or contact system admin";
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Result : " + statusMsg, ""));
      
           
           
       }
        
    }

    public StoreMovementControlLocal getSmcl() {
        return smcl;
    }

    public void setSmcl(StoreMovementControlLocal smcl) {
        this.smcl = smcl;
    }

    public PurchaseOrderEntity getPo() {
        return po;
    }

    public void setPo(PurchaseOrderEntity po) {
        this.po = po;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<DeliveryOrderEntity> getDolist() {
        return dolist;
    }

    public Double getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(Double newQuantity) {
        this.newQuantity = newQuantity;
    }

    public Long getStoreRPId() {
        return storeRPId;
    }

    public void setStoreRPId(Long storeRPId) {
        this.storeRPId = storeRPId;
    }

    public void setDolist(List<DeliveryOrderEntity> dolist) {
        this.dolist = dolist;
    }

    public DeliveryOrderEntity getSelectedDO() {
        return selectedDO;
    }

    public void setSelectedDO(DeliveryOrderEntity selectedDO) {
        this.selectedDO = selectedDO;
    }
    
    
    
    
    
    
    
    
    
}
