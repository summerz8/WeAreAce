/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.CountryProductEntity;
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
@Named(value = "customerWebIndexBean")
@ViewScoped
public class CustomerWebIndexBean {

    @EJB
    CustomerWebModuleLocal cwml;

    private List<CountryProductEntity> itemList;
    private String selectedWeb;
    private Long searchId;
    private CountryProductEntity item;

    public CustomerWebIndexBean() {
    }

    @PostConstruct
    public void init() {

        selectedWeb = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");
        itemList = cwml.listItems(selectedWeb);

    }

    public String edit(Long itemId) {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", itemId);
        return "EditItem?faces-redirect=true";
    }

    public String delete(Long itemId) {
        Long respond = cwml.deleteItem(itemId);

        if (respond.equals(0L)) {
            return "CustomerWebFurniture?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("setId", respond);
            return "CantDeleteItem?faces-redirect=true";
        }
    }

    public String search() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", searchId);
        item = cwml.getItem(searchId);
        if (item != null) {
            return "EditItem?faces-redirect=true";
        } else {
            return "CantFindItem?faces-redirect=true";
        }

    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public List<CountryProductEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<CountryProductEntity> itemList) {
        this.itemList = itemList;
    }

    public String getSelectedWeb() {
        return selectedWeb;
    }

    public void setSelectedWeb(String selectedWeb) {
        this.selectedWeb = selectedWeb;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

}
