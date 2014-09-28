/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.SCM;

import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.RetailProductEntity;
import SessionBean.SCM.PurchasedItemAndSupplierManagementModuleLocal;
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
@Named(value = "addFactoryRetailProduct")
@ViewScoped
public class AddFactoryRetailProduct {

    /**
     * Creates a new instance of AddFactoryRetailProduct
     */
    public AddFactoryRetailProduct() {
    }
    @EJB
    PurchasedItemAndSupplierManagementModuleLocal PASMM;

    private Collection<FactoryRetailProductEntity> addedFactoryRetailProductList;
    private Collection<RetailProductEntity> notAddRetailProductList;
    private Long factoryId;
    private String msgPrint;
    private String msgPrint2;
    private FactoryRetailProductEntity selectedFRP;
    private RetailProductEntity selectedRetailProuct;

    @PostConstruct
    public void init() {

        try {
            factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            addedFactoryRetailProductList = PASMM.viewRetailProductWithSelectType(factoryId);
            System.out.println("AddFactory RP ManageBean: addedListsize :" + addedFactoryRetailProductList.size());
            notAddRetailProductList = PASMM.viewRetailProductListNotInFactory(factoryId);
            System.out.println("AddFactory RP ManageBean: notAddRetailProductList Size: " + notAddRetailProductList.size());

        } catch (Exception ex) {
            Logger.getLogger(AddFactoryRawMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectFactoryRetailProduct(FactoryRetailProductEntity frp) {
        System.out.println("get into select factory retail product."+ frp.getName());
        selectedFRP = frp;
    }

    public void selectRetailProduct(RetailProductEntity rp) {
        System.out.println("get into select retail product.");
        selectedRetailProuct = rp;
    }

    public void deleteFactoryRetailProduct(FactoryRetailProductEntity frp) throws Exception {
        System.out.println("have a check:" + frp.getFactoryRetailProdctId());
        msgPrint = PASMM.deleteItem("RetailProduct", frp.getFactoryRetailProdctId());
        addedFactoryRetailProductList = PASMM.viewRetailProductWithSelectType(factoryId);
        notAddRetailProductList = PASMM.viewRetailProductListNotInFactory(factoryId);
    }

    public void addFactoryRetailProduct(RetailProductEntity rp) throws Exception {
        msgPrint2  = PASMM.addItem(factoryId, "RetailProduct", rp.getRetailProductId());
        addedFactoryRetailProductList = PASMM.viewRetailProductWithSelectType(factoryId);
        notAddRetailProductList = PASMM.viewRetailProductListNotInFactory(factoryId);
        
    }

    public PurchasedItemAndSupplierManagementModuleLocal getPASMM() {
        return PASMM;
    }

    public void setPASMM(PurchasedItemAndSupplierManagementModuleLocal PASMM) {
        this.PASMM = PASMM;
    }

    public Collection<FactoryRetailProductEntity> getAddedFactoryRetailProductList() {
        return addedFactoryRetailProductList;
    }

    public void setAddedFactoryRetailProductList(Collection<FactoryRetailProductEntity> addedFactoryRetailProductList) {
        this.addedFactoryRetailProductList = addedFactoryRetailProductList;
    }

    public Collection<RetailProductEntity> getNotAddRetailProductList() {
        return notAddRetailProductList;
    }

    public void setNotAddRetailProductList(Collection<RetailProductEntity> notAddRetailProductList) {
        this.notAddRetailProductList = notAddRetailProductList;
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

    public String getMsgPrint2() {
        return msgPrint2;
    }

    public void setMsgPrint2(String msgPrint2) {
        this.msgPrint2 = msgPrint2;
    }

    public FactoryRetailProductEntity getSelectedFRP() {
        return selectedFRP;
    }

    public void setSelectedFRP(FactoryRetailProductEntity selectedFRP) {
        this.selectedFRP = selectedFRP;
    }

    public RetailProductEntity getSelectedRetailProuct() {
        return selectedRetailProuct;
    }

    public void setSelectedRetailProuct(RetailProductEntity selectedRetailProuct) {
        this.selectedRetailProuct = selectedRetailProuct;
    }
    
    
}
