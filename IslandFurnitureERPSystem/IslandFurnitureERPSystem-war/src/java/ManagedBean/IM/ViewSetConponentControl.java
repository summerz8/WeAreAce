/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.IM;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.SetEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreSetEntity;
import ManagedBean.SCM.AddFactoryProduct;
import SessionBean.IM.StoreInventoryControlLocal;
import SessionBean.IM.StoreSetControlLocal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "viewSetConponentControl")
@ViewScoped
public class ViewSetConponentControl {

    @EJB
    StoreSetControlLocal sscl;

    @EJB
    StoreInventoryControlLocal sicl;

    private List<StoreProductEntity> currentStoreProductList = new ArrayList<>();
    private Long storeId;
    private List<ProductEntity> productNotInStoreList = new ArrayList<>();
    private Long setId;
    private StoreSetEntity set;
    private List<ProductEntity> ProductList;
    private ProductEntity selectedProduct;
    private Collection<FactoryProductEntity> availableFactory;
    private String msgprint1;
    private String msgprint2;
    private FactoryProductEntity selectedFactory;
    private Boolean isSelfPicked;
    private String remark;    
    
    
    public ViewSetConponentControl() {
    }

    @PostConstruct
    public void init() {
        try {
            if ((int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Userlvl") == 0) {
                storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("HFactoryId");

            } else {
                storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            }
            currentStoreProductList = sicl.getListOfStoreProduct(storeId);

            setId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("setId");
            set = sscl.getStoreSet(setId);
            ProductList = set.getSet().getProductList();

        } catch (Exception ex) {
            Logger.getLogger(AddFactoryProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selelectProductToAdd(ProductEntity pe) {
        selectedProduct = pe;
        selectAvailableFactory(pe);
    }

    public Collection<FactoryProductEntity> selectAvailableFactory(ProductEntity pe) {
        selectedProduct = pe;
        availableFactory = (Collection) sicl.listAvailableFactoryProduct(pe.getProductId());
        return availableFactory;
    }

    public void addStoreProduct(ActionEvent event) {

        System.out.println("Add the product function");

        // comment by zheng yuan 1112
  //      int result = sicl.addNewStoreProduct(storeId, selectedProduct.getProductId(), selectedFactory.getFactoryProductId(), isSelfPicked, remark);
//        if (result == 1) {
//            msgprint1 = "A new product added successfully!";
//            currentStoreProductList = sicl.getListOfStoreProduct(storeId);
//            productNotInStoreList = sicl.getListOfProductNotInStore(storeId);
//            System.out.println(msgprint1);
//
//        } else {
//            msgprint1 = "Exception occured. Please try again or raise a ticket.";
//            System.out.println(msgprint2);
//
//        }
    }
    
    public boolean exist(ProductEntity product){
        for(StoreProductEntity s: currentStoreProductList){
            if(s.getProduct().getProductId().equals(product.getProductId()))
                return false;
        }
        return true;
    
    }

    public List<ProductEntity> getProductNotInStoreList() {
        return productNotInStoreList;
    }

    public void setProductNotInStoreList(List<ProductEntity> productNotInStoreList) {
        this.productNotInStoreList = productNotInStoreList;
    }

    public ProductEntity getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Collection<FactoryProductEntity> getAvailableFactory() {
        return availableFactory;
    }

    public void setAvailableFactory(Collection<FactoryProductEntity> availableFactory) {
        this.availableFactory = availableFactory;
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

    public StoreSetControlLocal getSscl() {
        return sscl;
    }

    public void setSscl(StoreSetControlLocal sscl) {
        this.sscl = sscl;
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

    public void setCurrentStoreProductList(List<StoreProductEntity> currentStoreProductList) {
        this.currentStoreProductList = currentStoreProductList;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }

    public StoreSetEntity getSet() {
        return set;
    }

    public void setSet(StoreSetEntity set) {
        this.set = set;
    }

    public List<ProductEntity> getProductList() {
        return ProductList;
    }

    public void setProductList(List<ProductEntity> ProductList) {
        this.ProductList = ProductList;
    }

}
