package Member;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Named(value = "gallery")
@ViewScoped
public class GalleryBean {

    @EJB
    private CustomerWebModuleLocal cwml;

    private List<SetEntity> setList;
    private String web;

    public GalleryBean() {
    }

    @PostConstruct
    public void init() {
        web = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");

        setList = cwml.getSetList(web);
    }

    public String viewMore(Long setId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("setId", setId);

        return "set?faces-redirect=true";
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

}
