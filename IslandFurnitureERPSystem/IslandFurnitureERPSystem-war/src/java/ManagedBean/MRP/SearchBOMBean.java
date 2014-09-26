/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "searchBOM")
@ViewScoped
public class SearchBOMBean {

    /**
     * Creates a new instance of SearchBOM
     */
    Long factoryProductId;
    
    public SearchBOMBean() {
    }

    public String goNext() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("factoryProductId", factoryProductId);
        return "ViewBOM?faces-redirect=true";

    }

    public Long getFactoryProductId() {
        return factoryProductId;
    }

    public void setFactoryProductId(Long factoryProductId) {
        this.factoryProductId = factoryProductId;
    }

    
}

