/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "cantDeleteItemBean")
@ViewScoped
public class CantDeleteItemBean {

    private Long setId;
    public CantDeleteItemBean() {
    }
    
    @PostConstruct
    public void init(){
        setId=(Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("setId");
    
    }

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }
    
}
