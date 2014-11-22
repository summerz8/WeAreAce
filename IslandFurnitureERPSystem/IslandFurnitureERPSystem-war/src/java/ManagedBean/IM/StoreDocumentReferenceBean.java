/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Store.IM.StoreGoodReceiptEntity;
import Entity.Store.IM.StoreInStoreMovementRecordEntity;
import Entity.Store.IM.StoreInboundRecordEntity;
import Entity.Store.IM.StoreOutboundRecordEntity;
import SessionBean.IM.StoreDocumentControlLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
@Named(value = "storeDocumentReferenceBean")
@ViewScoped
public class StoreDocumentReferenceBean implements Serializable {

    /**
     * Creates a new instance of DocumentReferenceBean
     */
    public StoreDocumentReferenceBean() {
    }
    
    @EJB
    StoreDocumentControlLocal sdcl;
    
    
    List<StoreGoodReceiptEntity> grList;
    List<StoreInboundRecordEntity> irList;
    List<StoreInStoreMovementRecordEntity> imrList;
    List<StoreOutboundRecordEntity> orList;
    Long storeId;
    
    
    @PostConstruct
    public void init(){
        storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
       
        grList = sdcl.getStoreGoodReceipt(storeId);
        irList = sdcl.getStoreInboundRecordEntity(storeId);
        imrList = sdcl.getStoreInStoreMovementRecord(storeId);
        System.out.println("imrList:size()" + imrList.size());
        orList = sdcl.getStoreOutboundRecord(storeId);
        
        
    }
         public String displayTime(Calendar calendarTime){ 
           System.err.println("Calendar Time:" + calendarTime.getTime());
           SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
           String time = sdf.format(calendarTime.getTime()).toString();
           return time;
    }
         
         
         public String displayInventoryType(Integer i){
             if( i == 0 ){
                 
                 return "Product";
             }
             if(i == 1){
             return "Retail Product";
             
             }
             return null;
             
             
         }
         
         public Long displayId(Integer i, StoreGoodReceiptEntity sgr){
             
             if( i == 0){
                 
                 return sgr.getSpe().getStoreProductId();
             }
             
             if( i == 1){
                 
                 
                 return sgr.getSrpe().getStoreRetailProductId();
             }
             return null;
         }
         
         

    public StoreDocumentControlLocal getSdcl() {
        return sdcl;
    }

    public void setSdcl(StoreDocumentControlLocal sdcl) {
        this.sdcl = sdcl;
    }

    public List<StoreGoodReceiptEntity> getGrList() {
        return grList;
    }

    public void setGrList(List<StoreGoodReceiptEntity> grList) {
        this.grList = grList;
    }

    public List<StoreInboundRecordEntity> getIrList() {
        return irList;
    }

    public void setIrList(List<StoreInboundRecordEntity> irList) {
        this.irList = irList;
    }

    public List<StoreInStoreMovementRecordEntity> getImrList() {
        return imrList;
    }

    public void setImrList(List<StoreInStoreMovementRecordEntity> imrList) {
        this.imrList = imrList;
    }

    public List<StoreOutboundRecordEntity> getOrList() {
        return orList;
    }

    public void setOrList(List<StoreOutboundRecordEntity> orList) {
        this.orList = orList;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    
    
    
    
    
    
    
            
}
