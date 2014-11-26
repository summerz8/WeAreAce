/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.Factory.ProductEntity;
import Entity.Factory.SetEntity;
import SessionBean.CommonInFrastructure.RetailProduct_ProductManagementModuleLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "modifySetBean")
@ViewScoped
public class ModifySetBean {

    @EJB
    private RetailProduct_ProductManagementModuleLocal RPMM;
    private Long setId;
    private SetEntity set;
    private String description;
    private String setName;
    private List<ProductEntity> itemList;
    private List<ProductEntity> allitems;
    private String selectedItem;
    private List<SelectItem> displayList;
    private String name;
    private String path;
    private String selectedWeb;
    private Double price;
    private Double memberPrice;
    private Double totalPrice;
    private Double totalMemberPrice;

    public ModifySetBean() {
    }

    @PostConstruct
    public void init() {
        setId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SetId");

        set = RPMM.getSet(setId);
        price = set.getPrice();
        memberPrice = set.getMemberPrice();
        description = set.getDescription();
        setName = set.getName();
        itemList = set.getProductList();
        totalPrice = 0D;
        totalMemberPrice = 0D;
        for (ProductEntity p : itemList) {
            totalPrice += p.getPrice();
            totalMemberPrice += p.getMemberPrice();
        }
        allitems = RPMM.ListProduct();
        displayList = new ArrayList<>();
        for (ProductEntity s : allitems) {
            String t = s.getProductId() + " " + s.getName();
            displayList.add(new SelectItem(s.getProductId(), t));
        }
    }

    public void upDate() throws IOException {
        if (price.compareTo(memberPrice) < 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Product cannot be edited!", "Member Price must be smaller than Original Price!"));
        } else if (price > totalPrice || memberPrice > totalMemberPrice) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Product cannot be edited!", "All Prices must be smaller than the sum of the price of all components!"));
        } else {
            RPMM.ModifySet(setId, setName, description, price, memberPrice);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/IslandFurnitureERPSystem-war/secured/restricted/CommonInfrastructure/EnterpriseResouces/SetControl.xhtml");
        }
    }

    public String addItem() {
        Long itemId = Long.valueOf(selectedItem);
        RPMM.addItem(setId, itemId);
        return "EditSet?faces-redirect=true";
    }

    public String deleteItem(Long itemId) {
        RPMM.deleteSetProduct(set.getId(), itemId);

        return "EditSet?faces-redirect=true";
    }

    public RetailProduct_ProductManagementModuleLocal getRPMM() {
        return RPMM;
    }

    public void setRPMM(RetailProduct_ProductManagementModuleLocal RPMM) {
        this.RPMM = RPMM;
    }

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }

    public SetEntity getSet() {
        return set;
    }

    public void setSet(SetEntity set) {
        this.set = set;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public List<ProductEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<ProductEntity> itemList) {
        this.itemList = itemList;
    }

    public List<ProductEntity> getAllitems() {
        return allitems;
    }

    public void setAllitems(List<ProductEntity> allitems) {
        this.allitems = allitems;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<SelectItem> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<SelectItem> displayList) {
        this.displayList = displayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSelectedWeb() {
        return selectedWeb;
    }

    public void setSelectedWeb(String selectedWeb) {
        this.selectedWeb = selectedWeb;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalMemberPrice() {
        return totalMemberPrice;
    }

    public void setTotalMemberPrice(Double totalMemberPrice) {
        this.totalMemberPrice = totalMemberPrice;
    }

}
