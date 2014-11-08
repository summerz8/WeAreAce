/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.SetEntity;
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
@Named(value = "customerWebSetBean")
@ViewScoped
public class CustomerWebSetBean {

    @EJB
    CustomerWebModuleLocal cwml;

    private List<SetEntity> setList;
    private String selectedWeb;
    private Long searchId;
    private SetEntity set;

    public CustomerWebSetBean() {
    }

    @PostConstruct
    public void init() {

        selectedWeb = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");
        setList = cwml.getSetList(selectedWeb);

    }

    public String edit(Long setId) {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("setId", setId);
        return "EditSet?faces-redirect=true";
    }

    public String delete(Long setId) {
        cwml.deleteSet(setId);

        return "CustomerWebSet?faces-redirect=true";

    }

    public String search() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("setId", searchId);
        set = cwml.getSet(searchId);
        if (set != null) {
            return "EditSet?faces-redirect=true";
        } else {
            return "CantFindSet?faces-redirect=true";
        }
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public List<SetEntity> getSetList() {
        return setList;
    }

    public void setSetList(List<SetEntity> setList) {
        this.setList = setList;
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
