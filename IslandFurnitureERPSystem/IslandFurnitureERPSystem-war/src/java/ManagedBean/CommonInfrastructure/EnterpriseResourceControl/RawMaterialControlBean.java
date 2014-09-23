/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialEntity;
import SessionBean.CommonInFrastructure.EnterpriseInventoryManagementModule_RawMaterialLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author dan
 */
@Named(value = "rawMaterialControl")
@ViewScoped
public class RawMaterialControlBean {

    @EJB
    private EnterpriseInventoryManagementModule_RawMaterialLocal EIMR;
    private List<RawMaterialEntity> rawMaterialList;
    private List<RawMaterialEntity> filteredRawMaterial;
    
    private String newRawMaterialName;
    private String newRawMaterialDescription;
    
    private String newRawMaterialUnit;
    /**
     * Creates a new instance of RawMaterialControlBean
     */
    public RawMaterialControlBean() {
    }
    
    @PostConstruct
    public void init() {
        System.out.println("RawMaterialControlBean: init:");

        rawMaterialList = EIMR.listRawMaterial();
        filteredRawMaterial = rawMaterialList;

    }

    public void onRowEdit(RowEditEvent event) throws Exception {
        System.out.println("onRowEdit test:");
        RawMaterialEntity entity = (RawMaterialEntity) event.getObject();
        System.out.println("onRowEdit test: " + entity.getMaterialId()+ entity.getMaterialName());

        EIMR.modifyRawMaterial(entity.getMaterialId(), entity.getMaterialName(), entity.getDescription(), entity.getUnit());
        FacesMessage msg = new FacesMessage("Raw Material Edited", String.valueOf(entity.getMaterialId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((RawMaterialEntity) event.getObject()).getMaterialId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteRawMaterial(Long id) throws Exception {
        System.out.println("RawMaterialControlBean: deleteRawMaterial: " + String.valueOf(id));      
        EIMR.deleteRawMaterial(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product deleted successfully! ", ""));
        
    }
    
    public void addRawMaterial() {
        System.out.println("RawMaterialControlBean: addRawMaterial: ");
        EIMR.addRawMaterial(newRawMaterialName, newRawMaterialDescription, newRawMaterialUnit);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product added successfully! ", ""));

    }

    public List<RawMaterialEntity> getRawMaterialList() {
        return rawMaterialList;
    }

    public void setRawMaterialList(List<RawMaterialEntity> rawMaterialList) {
        this.rawMaterialList = rawMaterialList;
    }

    public List<RawMaterialEntity> getFilteredRawMaterial() {
        return filteredRawMaterial;
    }

    public void setFilteredRawMaterial(List<RawMaterialEntity> filteredRawMaterial) {
        this.filteredRawMaterial = filteredRawMaterial;
    }

    public String getNewRawMaterialName() {
        return newRawMaterialName;
    }

    public void setNewRawMaterialName(String newRawMaterialName) {
        this.newRawMaterialName = newRawMaterialName;
    }

    public String getNewRawMaterialDescription() {
        return newRawMaterialDescription;
    }

    public void setNewRawMaterialDescription(String newRawMaterialDescription) {
        this.newRawMaterialDescription = newRawMaterialDescription;
    }

    public String getNewRawMaterialUnit() {
        return newRawMaterialUnit;
    }

    public void setNewRawMaterialUnit(String newRawMaterialUnit) {
        this.newRawMaterialUnit = newRawMaterialUnit;
    }
    
    
}
