/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import Entity.Store.OCRM.CustomerWebItemEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "furnitureBean")
@ViewScoped
public class FurnitureBean {

    @EJB
    private CustomerWebModuleLocal cwml;

    private List<CustomerWebItemEntity> itemList;
    private String web;

    public FurnitureBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("FurenitureBean:init()");       
        web=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");

        itemList = cwml.listItems(web);

    }

    public String view(Long itemId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", itemId);
        return "ViewItem?faces-redirect=true";
    }

    public String searchBed() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchType", "Bed");
        return "FurnitureSearch?faces-redirect=true";
    }

    public String searchAll() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchType", "All");
        return "FurnitureSearch?faces-redirect=true";
    }

    public String searchDesk() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchType", "Desk");
        return "FurnitureSearch?faces-redirect=true";
    }

    public String searchChair() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchType", "Chair");
        return "FurnitureSearch?faces-redirect=true";
    }

    public String searchCloset() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchType", "Closet");
        return "FurnitureSearch?faces-redirect=true";
    }

    public String searchLight() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchType", "Light");
        return "FurnitureSearch?faces-redirect=true";
    }
    
    public String searchSofa() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchType", "Light");
        return "FurnitureSearch?faces-redirect=true";
    }

    public String searchOthers() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchType", "Others");
        return "FurnitureSearch?faces-redirect=true";
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public List<CustomerWebItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<CustomerWebItemEntity> itemList) {
        this.itemList = itemList;
    }
}
