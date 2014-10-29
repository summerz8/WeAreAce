/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Factory.ProductEntity;
import Entity.Store.OCRM.CustomerWebItemEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "editItem")
@ViewScoped
public class EditItem {

    @EJB
    CustomerWebModuleLocal cwml;
    Long itemId;
    CustomerWebItemEntity item;
    String description;
    Double memberPrice;
    Double price;
    ProductEntity product;
    Long productId;
    String type;
    String productName;

    List<SelectItem> displayList;
    List<ProductEntity> productList;
    List<SelectItem> typeList;
    private String selectedProduct;
    private String selectedType;

    public EditItem() {
    }

    @PostConstruct
    public void init() {
        itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");
        item = cwml.getItem(itemId);
        description = item.getDescription();
        memberPrice = item.getMemberPrice();
        price = item.getPrice();
        product = item.getProduct();
        type = item.getType();
        productName = item.getProductName();
        productId = product.getProductId();

        displayList = new ArrayList<>();
        typeList = new ArrayList<>();
        productList = cwml.getProductList();
        for (ProductEntity s : productList) {
            String t = s.getProductId() + " " + s.getName();
            displayList.add(new SelectItem(s.getProductId(), t));
        }

        typeList.add(new SelectItem("Desk"));
        typeList.add(new SelectItem("Bed"));
        typeList.add(new SelectItem("Closet"));
        typeList.add(new SelectItem("Sofa"));
        typeList.add(new SelectItem("Chair"));
        typeList.add(new SelectItem("Other"));
    }
    
    
    public String upDate(){
        
        productId=Long.valueOf(selectedProduct);
        cwml.editItem(itemId, productId, productName, description, selectedType);
        
        return "CustomerWebSingapore?faces-redirect=true";
    
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public CustomerWebItemEntity getItem() {
        return item;
    }

    public void setItem(CustomerWebItemEntity item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<SelectItem> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<SelectItem> displayList) {
        this.displayList = displayList;
    }

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    public List<SelectItem> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<SelectItem> typeList) {
        this.typeList = typeList;
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(String selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }
    
    

}
