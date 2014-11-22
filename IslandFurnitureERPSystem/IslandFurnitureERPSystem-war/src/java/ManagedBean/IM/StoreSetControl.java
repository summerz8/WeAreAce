/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.IM;

import Entity.Factory.SetEntity;
import Entity.Store.OCRM.CountrySetEntity;
import Entity.Store.StoreSetEntity;
import SessionBean.IM.StoreSetControlLocal;
import java.util.ArrayList;
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
@Named(value = "storeSetControl")
@ViewScoped
public class StoreSetControl {

    @EJB
    StoreSetControlLocal sscl;

    private List<StoreSetEntity> currentStoreSetList = new ArrayList<>();
    private List<CountrySetEntity> setNotInStoreList = new ArrayList<>();
    private StoreSetEntity selectedStoreSet;
    private Long storeId;


    public StoreSetControl() {
    }

    @PostConstruct
    public void init() {
        try {
            if ((int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Userlvl") == 0) {
                storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("HFactoryId");

            } else {
                storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            }
            currentStoreSetList = sscl.getListOfStoreSet(storeId);
            System.out.println("ManagedBean : StoreSetControl : currentStoreSetList size()" + currentStoreSetList.size());
            setNotInStoreList = sscl.getListOfSetNotInStore(storeId);
            System.out.println("ManagedBean : StoreSetControl : setNotInStore size()" + setNotInStoreList.size());

        } catch (Exception ex) {
           
        }
    }

    public void onRowEdit(RowEditEvent event) {
        System.out.println("onRowEdit test:");
        StoreSetEntity entity = (StoreSetEntity) event.getObject();

        FacesMessage msg = new FacesMessage("Store Set Edited", String.valueOf(entity.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((StoreSetEntity) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String add(Long countrySetId){
        sscl.addStoreSet(storeId, countrySetId);
        return "ListCurrentStoreSet?faces-redirect=true";
    
    }
    
    
    public String delete(Long StoreSetId){
        sscl.deleteStoreSet(StoreSetId);
        return "ListCurrentStoreSet?faces-redirect=true";
    
    }
    
    public String viewConponents(Long setId){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ViewSetId",setId);
        return "ViewSetConponents?faces-redirect=true";
    }

    public StoreSetControlLocal getSscl() {
        return sscl;
    }

    public void setSscl(StoreSetControlLocal sscl) {
        this.sscl = sscl;
    }

    public List<StoreSetEntity> getCurrentStoreSetList() {
        return currentStoreSetList;
    }

    public void setCurrentStoreSetList(List<StoreSetEntity> currentStoreSetList) {
        this.currentStoreSetList = currentStoreSetList;
    }

    public List<CountrySetEntity> getSetNotInStoreList() {
        return setNotInStoreList;
    }

    public void setSetNotInStoreList(List<CountrySetEntity> setNotInStoreList) {
        this.setNotInStoreList = setNotInStoreList;
    }

    public StoreSetEntity getSelectedStoreSet() {
        return selectedStoreSet;
    }

    public void setSelectedStoreSet(StoreSetEntity selectedStoreSet) {
        this.selectedStoreSet = selectedStoreSet;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

}
