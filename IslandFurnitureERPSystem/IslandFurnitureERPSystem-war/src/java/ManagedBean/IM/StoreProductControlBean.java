/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.ProductEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import ManagedBean.SCM.AddFactoryProduct;
import SessionBean.IM.StoreInventoryControlLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author zhengyuan
 */
@Named(value = "storeProductControl")
@ViewScoped
public class StoreProductControlBean implements Serializable{

    /**
     * Creates a new instance of StoreProductControl
     */
    public StoreProductControlBean() {
    }
    
    @EJB
    StoreInventoryControlLocal sicl;
    
    private List<StoreProductEntity> currentStoreProductList = new ArrayList<>();
    private List<ProductEntity> productNotInStoreList = new ArrayList<>();
    private StoreProductEntity selectedStoreProduct;
    private ProductEntity selectedProduct;
    private Collection<FactoryProductEntity> availableFactory;
    private FactoryProductEntity selectedFactory;
    private StoreProductEntity storeProductToDelete;
    private StoreEntity store;
    private String msgprint1;
    private String msgprint2;
    private Long storeId;
    private Boolean isSelfPicked;
    private String remark;    
    @PostConstruct
    public void init(){
        try{
            if ((int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Userlvl")==0) {
                storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("HFactoryId");
                
            } else {
                storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            }
        currentStoreProductList = sicl.getListOfStoreProduct(storeId);
        System.out.println("ManagedBean : StoreProductControl : currentStoreProductList size()" + currentStoreProductList.size());
        productNotInStoreList = sicl.getListOfProductNotInStore(storeId);
        System.out.println("ManagedBean : StoreProductControl : productNotInStore size()" + productNotInStoreList.size());

         } catch (Exception ex) {
            Logger.getLogger(AddFactoryProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void selectProductToDetele(StoreProductEntity spe){
        storeProductToDelete = spe;
         
        
    }
    

    public void selelectProductToAdd(ProductEntity pe){
        selectedProduct = pe;
        selectAvailableFactory(pe);
        
        
        
        
    }
    public void deleteStoreProduct(StoreProductEntity spe){
        
        int result = sicl.deleteStoreProduct(storeId, spe.getStoreProductId(), spe.getFactoryProduct().getFactory().getFactoryId());
        if(result == 1){
            
            msgprint2 = "Product delete successfully!!";
            currentStoreProductList = sicl.getListOfStoreProduct(storeId);
            productNotInStoreList = sicl.getListOfProductNotInStore(storeId);
            
        }
        else {
            msgprint2 = "Exception occured. Please try again or raise a ticket.";
        }
    }
    
    public Collection<FactoryProductEntity> selectAvailableFactory(ProductEntity pe){
        selectedProduct = pe;
        availableFactory =(Collection) sicl.listAvailableFactoryProduct(pe.getProductId());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("factoryProductEntities", availableFactory);
        return availableFactory;
    }
    
    public void addStoreProduct(ActionEvent event){
        
        
        System.out.println("Add the product function");
        
        int result = sicl.addNewStoreProduct(storeId,  selectedProduct.getProductId(),selectedFactory.getFactoryProductId(), isSelfPicked, remark);
        if(result == 1) {
            msgprint1 = "A new product added successfully!";
            currentStoreProductList = sicl.getListOfStoreProduct(storeId);
            productNotInStoreList = sicl.getListOfProductNotInStore(storeId);
            System.out.println(msgprint1);
            
        }
        else{
            msgprint1 = "Exception occured. Please try again or raise a ticket.";
            System.out.println(msgprint1);
            
        }
        selectedProduct = null;
        selectedFactory = null;
        isSelfPicked = false;
        remark = null;
            
    }

 public void onRowEdit(RowEditEvent event) {
        System.out.println("onRowEdit test:");
        StoreProductEntity entity = (StoreProductEntity) event.getObject();
        Long oldFactoryProductId = entity.getFactoryProduct().getFactoryProductId();
        System.out.println("onRowEdit test: " + entity.getStoreProductId() + entity.getName());

        if (entity.getFactoryProduct()==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Store Product cannot be edited!", "Please at least select a factory"));
        } else {
           sicl.editStoreProduct(entity.getStore().getStoreId(), entity.getStoreProductId(), oldFactoryProductId, entity.getSelfPick(), entity.getFactoryProduct().getFactoryProductId(), entity.getMinimumInventory(),entity.getStoreRemark());

            FacesMessage msg = new FacesMessage("Store Product Edited", String.valueOf(entity.getStoreProductId()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((StoreProductEntity) event.getObject()).getStoreProductId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    
    
    public StoreInventoryControlLocal getSicl() {
        return sicl;
    }

    public void setSicl(StoreInventoryControlLocal sicl) {
        this.sicl = sicl;
    }

    public List<StoreProductEntity> getCurrentStoreProductList() {
        return currentStoreProductList;
    }

    public FactoryProductEntity getSelectedFactory() {
        return selectedFactory;
    }

    public void setSelectedFactory(FactoryProductEntity selectedFactory) {
        this.selectedFactory = selectedFactory;
    }

    public Boolean getIsSelfPicked() {
        return isSelfPicked;
    }

    public void setIsSelfPicked(Boolean isSelfPicked) {
        this.isSelfPicked = isSelfPicked;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    public void setCurrentStoreProductList(List<StoreProductEntity> currentStoreProductList) {
        this.currentStoreProductList = currentStoreProductList;
    }

    public List<ProductEntity> getProductNotInStoreList() {
        return productNotInStoreList;
    }

    public void setProductNotInStoreList(List<ProductEntity> productNotInStoreList) {
        this.productNotInStoreList = productNotInStoreList;
    }

    public StoreProductEntity getSelectedStoreProduct() {
        return selectedStoreProduct;
    }

    public void setSelectedStoreProduct(StoreProductEntity selectedStoreProduct) {
        this.selectedStoreProduct = selectedStoreProduct;
    }
 
    public ProductEntity getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getMsgprint1() {
        return msgprint1;
    }

    public void setMsgprint1(String msgprint1) {
        this.msgprint1 = msgprint1;
    }

    public String getMsgprint2() {
        return msgprint2;
    }

    public void setMsgprint2(String msgprint2) {
        this.msgprint2 = msgprint2;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }
    
    
    public StoreProductEntity getStoreProductToDelete() {
        return storeProductToDelete;
    }

    public void setStoreProductToDelete(StoreProductEntity storeProductToDelete) {
        this.storeProductToDelete = storeProductToDelete;
    }

    public Collection<FactoryProductEntity> getAvailableFactory() {
        return availableFactory;
    }

    public void setAvailableFactory(List<FactoryProductEntity> availableFactory) {
        this.availableFactory = availableFactory;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    
    
    
}
