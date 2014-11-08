/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

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
@Named(value = "indexBean")
@ViewScoped
public class IndexBean {

    @EJB
    private CustomerWebModuleLocal cwml;

    private List<SetEntity> setList;
    private SetEntity set;

    public IndexBean() {
    }

    @PostConstruct
    public void init() {
        setList = cwml.getSetList();
    }

    public String viewMore(Long setId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("setId", setId);

        return "set?faces-redirect=true";
    }

    public String view() {
        set = setList.get(0);
        System.out.println(set.getId());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("setId", set.getId());
        return "/public/set?faces-redirect=true";

    }

}
