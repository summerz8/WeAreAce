/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.FactoryRawMaterialAmountEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hangsun
 */
@Named(value = "viewRawMaterial")
@ViewScoped
public class ViewRawMaterial { 
    
    /**
     * Creates a new instance of ViewRawMaterial
     */
    public ViewRawMaterial() {
    }
    
    private List<FactoryRawMaterialAmountEntity> selectedRawMaterial;
    
    @PostConstruct
    public void init(){
        selectedRawMaterial = (List<FactoryRawMaterialAmountEntity>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedRawMaterial");
    }

    public List<FactoryRawMaterialAmountEntity> getSelectedRawMaterial() {
        return selectedRawMaterial;
    }
    
    
    
}
