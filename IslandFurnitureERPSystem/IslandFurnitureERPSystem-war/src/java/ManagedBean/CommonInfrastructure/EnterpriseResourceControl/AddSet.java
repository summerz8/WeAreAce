/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.Factory.SetEntity;
import SessionBean.CommonInFrastructure.RetailProduct_ProductManagementModuleLocal;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "addSet")
@ViewScoped
public class AddSet {

     @EJB
    RetailProduct_ProductManagementModuleLocal rpmm;
    
    private String setName;
    private String description;
    private SetEntity set;

    
    
    public AddSet() {
    }
    
    public String create(){
        set=rpmm.createSet(setName, description);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("setId",set.getId());
        
        return "EditSet?faces-redirect=true";
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


    
    
}
