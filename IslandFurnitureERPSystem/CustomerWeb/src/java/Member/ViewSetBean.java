/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import Entity.Store.OCRM.CustomerWebItemEntity;
import Entity.Store.OCRM.SetEntity;
import Entity.Store.OCRM.ShoppingCartItemEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "viewSetBean")
@ViewScoped
public class ViewSetBean {

    @EJB
    private CustomerWebModuleLocal cwml;

    private Long setId;
    private SetEntity set;
    private List<String> pictureList;
    private List<CustomerWebItemEntity> itemList;
    private String email;
    private CustomerWebItemEntity item;
    private Integer quantity;

    public ViewSetBean() {
    }

    @PostConstruct
    public void init() {

        setId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("setId");
        System.out.println(setId);
        set = cwml.getSet(setId);
        pictureList = new ArrayList<>();
        pictureList.add(set.getPicture());
        itemList = set.getUnitList();
        for (CustomerWebItemEntity c : itemList) {
            pictureList.add(c.getPicture());
        }

    }

    public String addToShoppingCart() {
        email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Email");
        if (email == null) {
            return "LoginPage?faces-redirect=true";
        } else {
            cwml.addToShoppingCart(email, item.getId(), quantity);
            return "set?faces-redirect=true";
        }
    }

    public void addAllToShoppingCart() throws IOException {
        email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Email");
        if (email == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("LoginPage.xhtml");

        }
        quantity = 1;
        for (CustomerWebItemEntity c : itemList) {
            item = c;
            addToShoppingCart();
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "The whole set of furniture has been added to your shopping cart", ""));
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
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

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public List<CustomerWebItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<CustomerWebItemEntity> itemList) {
        this.itemList = itemList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerWebItemEntity getItem() {
        return item;
    }

    public void setItem(CustomerWebItemEntity item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
