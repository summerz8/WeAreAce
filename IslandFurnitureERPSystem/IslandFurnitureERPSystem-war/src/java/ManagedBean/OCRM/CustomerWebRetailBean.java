/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.CountryRetailProductEntity;
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
@Named(value = "customerWebRetailBean")
@ViewScoped
public class CustomerWebRetailBean {

    @EJB
    CustomerWebModuleLocal cwml;

    private List<CountryRetailProductEntity> retailItemList;
    private String selectedWeb;
    private Long searchId;
    private CountryRetailProductEntity retailItem;

    public CustomerWebRetailBean() {
    }

    @PostConstruct
    public void init() {

        selectedWeb = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");
        retailItemList = cwml.listRetailItems(selectedWeb);

    }

    public String edit(Long retailItemId) {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("retailItemId", retailItemId);
        return "EditRetailItem?faces-redirect=true";
    }

    public String delete(Long retailItemId) {
        cwml.deleteRetailItem(retailItemId);

        return "CustomerWebRetail?faces-redirect=true";

    }

    public String search() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("retailItemId", searchId);
        retailItem = cwml.getRetailItem(searchId);
        if (retailItem != null) {
            return "EditRetailItem?faces-redirect=true";
        } else {
            return "CantFindRetailItem?faces-redirect=true";
        }
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public List<CountryRetailProductEntity> getRetailItemList() {
        return retailItemList;
    }

    public void setRetailItemList(List<CountryRetailProductEntity> retailItemList) {
        this.retailItemList = retailItemList;
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

    public CountryRetailProductEntity getRetailItem() {
        return retailItem;
    }

    public void setRetailItem(CountryRetailProductEntity retailItem) {
        this.retailItem = retailItem;
    }

}
