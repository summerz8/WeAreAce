/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import Entity.Store.OCRM.CustomerWebItemEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "viewItemBean")
@ViewScoped
public class ViewItemBean {

    @EJB
    private CustomerWebModuleLocal cwml;
    private Long itemId;
    private CustomerWebItemEntity item;
    private String email;
    private int quantity;

    public ViewItemBean() {
    }

    @PostConstruct
    public void init() {

        itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");
        item = cwml.getItem(itemId);

    }

    public String addToShoppingCart() {
        email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Email");
        if (email == null) {
            return "LoginPage?faces-redirect=true";
        } else {
            cwml.addToShoppingCart(email, item.getId(), quantity);
            return "ViewItem?faces-redirect=true";
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
