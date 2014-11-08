/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import Entity.Store.OCRM.CustomerWebRetailItemEntity;
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
@Named(value = "retailBean")
@ViewScoped
public class RetailBean {

    @EJB
    private CustomerWebModuleLocal cwml;

    private List<CustomerWebRetailItemEntity> retailItemList;
    private String web;

    public RetailBean() {
    }

    @PostConstruct
    public void init() {
        web=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");

        retailItemList = cwml.listRetailItems(web);
    }

    public String view(Long itemId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("retailItemId", itemId);
        return "ViewRetailItem?faces-redirect=true";
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public List<CustomerWebRetailItemEntity> getRetailItemList() {
        return retailItemList;
    }

    public void setRetailItemList(List<CustomerWebRetailItemEntity> retailItemList) {
        this.retailItemList = retailItemList;
    }
    
    

}
