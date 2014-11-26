/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.ProductEntity;
import ManagedBean.SCM.PurchasedItemAndSupplierManagementModule.ItemsForPurchase;
import SessionBean.CommonInFrastructure.Factory_StoreManagementModuleLocal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "addFactoryProduct")
@ViewScoped
public class AddFactoryProduct {

    /**
     * Creates a new instance of AddFactoryProduct
     */
    public AddFactoryProduct() {
    }

    @EJB
    Factory_StoreManagementModuleLocal fsml;

    private Collection<FactoryProductEntity> addedFactoryProductList;
    private Collection<ProductEntity> notAddProductList;
    private Long factoryId;
    private String msgPrint;
    private String msgPrint2;
    private FactoryProductEntity selectedFP;
    private ProductEntity selectedProduct;

    @PostConstruct
    public void init() {

        try {
            if ((int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Userlvl")==0) {
                factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("HFactoryId");
                
            } else {
                factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            }
            addedFactoryProductList = fsml.listFactoryProduct(factoryId);
            System.out.println("AddFactoryProduct ManageBean: addedListsize :" + addedFactoryProductList.size());
            notAddProductList = fsml.viewProductListNotInFactory(factoryId);
            System.out.println("AddFactoryProduct ManageBean: notAddProductList Size: " + notAddProductList.size());

        } catch (Exception ex) {
            Logger.getLogger(AddFactoryProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectFactoryProduct(FactoryProductEntity fpe) {
        selectedFP = fpe;
    }

    public void selectProduct(ProductEntity pe) {
        selectedProduct = pe;
    }

    public void deleteFactoryProduct(FactoryProductEntity fpe) throws Exception {
        System.out.println("have a check:" + fpe.getProduct().getName());
        Integer message = fsml.deleteFatoryProduct(fpe.getFactoryProductId());

        if (message == 1) {
            msgPrint = "There are still inventory in the stock. You cannot delete it.";

        } else {
            msgPrint = "Delete it from the list successfully!";
            addedFactoryProductList = fsml.listFactoryProduct(factoryId);
            notAddProductList = fsml.viewProductListNotInFactory(factoryId);

        }

    }

    public void addFactoryProduct(ProductEntity pe) throws Exception {
        Integer message = fsml.addFactoryProduct(factoryId, pe.getProductId());
        if (message == 2) {
            msgPrint2 = "Product has been added into Factory Product List successfully!";
            addedFactoryProductList = fsml.listFactoryProduct(factoryId);
            notAddProductList = fsml.viewProductListNotInFactory(factoryId);            
        } else {
            msgPrint2 = "Sorry! Error happended in adding product";
        }
    }

    public Factory_StoreManagementModuleLocal getFsml() {
        return fsml;
    }

    public void setFsml(Factory_StoreManagementModuleLocal fsml) {
        this.fsml = fsml;
    }

    public Collection<FactoryProductEntity> getAddedFactoryProductList() {
        return addedFactoryProductList;
    }

    public void setAddedFactoryProductList(Collection<FactoryProductEntity> addedFactoryProductList) {
        this.addedFactoryProductList = addedFactoryProductList;
    }

    public Collection<ProductEntity> getNotAddProductList() {
        return notAddProductList;
    }

    public void setNotAddProductList(Collection<ProductEntity> notAddProductList) {
        this.notAddProductList = notAddProductList;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getMsgPrint() {
        return msgPrint;
    }

    public void setMsgPrint(String msgPrint) {
        this.msgPrint = msgPrint;
    }

    public FactoryProductEntity getSelectedFP() {
        return selectedFP;
    }

    public void setSelectedFP(FactoryProductEntity selectedFP) {
        this.selectedFP = selectedFP;
    }

    public String getMsgPrint2() {
        return msgPrint2;
    }

    public void setMsgPrint2(String msgPrint2) {
        this.msgPrint2 = msgPrint2;
    }

    public ProductEntity getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

}
