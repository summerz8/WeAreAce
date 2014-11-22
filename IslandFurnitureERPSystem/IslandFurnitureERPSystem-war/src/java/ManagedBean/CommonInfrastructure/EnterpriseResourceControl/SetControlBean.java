/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.Factory.SetEntity;
import SessionBean.CommonInFrastructure.RetailProduct_ProductManagementModuleLocal;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author apple
 */
@Named(value = "setControlBean")
@ViewScoped
public class SetControlBean {

    @EJB
    private RetailProduct_ProductManagementModuleLocal RPMM;
    private List<SetEntity> setList;
    private List<SetEntity> filteredSet;

    private String newSetName;
    private String newSetDescription;
    private Double newSetPrice;
    private String newSetUnit;
    private Double newSetMemberPrice;

    private SetEntity selectedSet;
    private Long selectedDeleteSetId;
    
    
    public SetControlBean() {
    }
    
    @PostConstruct
    public void init() {
        System.out.println("ProductControlBean: init:");

        setList = RPMM.listSetList();
        filteredSet = setList;

    }
    
     public void onRowEdit(RowEditEvent event) {
        System.out.println("onRowEdit test:");
        SetEntity entity = (SetEntity) event.getObject();
        System.out.println("onRowEdit test: " + entity.getId() + entity.getName());

        if (entity.getPrice()<= entity.getMemberPrice()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Product cannot be edited!", "Member Price must be smaller than Original Price!"));
        } else {
            RPMM.ModifySet(entity.getId(), entity.getName(), entity.getDescription(),
                    entity.getPrice(), entity.getMemberPrice());

            FacesMessage msg = new FacesMessage("Set Edited", String.valueOf(entity.getId()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((SetEntity) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteSet(Long id) {
        System.out.println("ProductControlBean: deleteProduct: " + String.valueOf(id));
        if(RPMM.DeleteSet(id)){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product deleted successfully! ", ""));

        setList = RPMM.listSetList();
        filteredSet = setList;
        }else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Set cannot be deleted! ", "Country set or store set still exists!"));
        }
    }
    
     public void viewSet(SetEntity set) throws IOException {
        selectedSet = set;
        String path = "/secured/restricted/CommonInfrastructure/EnterpriseResouces/EditSet.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SetId", selectedSet.getId());

        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);
        System.err.println("go to another page");

    }

    public RetailProduct_ProductManagementModuleLocal getRPMM() {
        return RPMM;
    }

    public void setRPMM(RetailProduct_ProductManagementModuleLocal RPMM) {
        this.RPMM = RPMM;
    }

    public List<SetEntity> getSetList() {
        return setList;
    }

    public void setSetList(List<SetEntity> setList) {
        this.setList = setList;
    }

    public List<SetEntity> getFilteredSet() {
        return filteredSet;
    }

    public void setFilteredSet(List<SetEntity> filteredSet) {
        this.filteredSet = filteredSet;
    }

    public String getNewSetName() {
        return newSetName;
    }

    public void setNewSetName(String newSetName) {
        this.newSetName = newSetName;
    }

    public String getNewSetDescription() {
        return newSetDescription;
    }

    public void setNewSetDescription(String newSetDescription) {
        this.newSetDescription = newSetDescription;
    }

    public Double getNewSetPrice() {
        return newSetPrice;
    }

    public void setNewSetPrice(Double newSetPrice) {
        this.newSetPrice = newSetPrice;
    }

    public String getNewSetUnit() {
        return newSetUnit;
    }

    public void setNewSetUnit(String newSetUnit) {
        this.newSetUnit = newSetUnit;
    }

    public Double getNewSetMemberPrice() {
        return newSetMemberPrice;
    }

    public void setNewSetMemberPrice(Double newSetMemberPrice) {
        this.newSetMemberPrice = newSetMemberPrice;
    }

    public SetEntity getSelectedSet() {
        return selectedSet;
    }

    public void setSelectedSet(SetEntity selectedSet) {
        this.selectedSet = selectedSet;
    }

    public Long getSelectedDeleteSetId() {
        return selectedDeleteSetId;
    }

    public void setSelectedDeleteSetId(Long selectedDeleteSetId) {
        this.selectedDeleteSetId = selectedDeleteSetId;
    }
    
}
