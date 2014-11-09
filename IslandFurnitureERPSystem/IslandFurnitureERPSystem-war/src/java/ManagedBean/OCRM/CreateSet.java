/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.SetEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "createSet")
@ViewScoped
public class CreateSet {

    @EJB
    CustomerWebModuleLocal cwml;
    
    private String picture;
    private String setName;
    private String description;
    private SetEntity set;
    
    public CreateSet() {
    }
    
    @PostConstruct
    public void init(){
        
    }
    
    public String create(){
<<<<<<< HEAD
        Long setId=cwml.createSet(setName, description, "set2.jpg");
=======
        Long setId=cwml.createSet(setName, description, "set2.png");
>>>>>>> parent of 6bf018e... no message
        set=cwml.getSet(setId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("setId",setId);
        
        return "EditSet?faces-redirect=true";
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SetEntity getSet() {
        return set;
    }

    public void setSet(SetEntity set) {
        this.set = set;
    }
    
    
}
