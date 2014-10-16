/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.RawIngredientsManagementModule;

import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.StoragePlaceEntity;
import SessionBean.KM.RawIngredientsManagementModuleLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "storagePlaceBean")
@ViewScoped
public class StoragePlaceBean implements Serializable {
    
    @EJB
    private RawIngredientsManagementModuleLocal rim;

    private KitchenEntity kitchen;
    private String location;
    private StoragePlaceEntity selectedStoragePlace;
    private List<StoragePlaceEntity> filteredStoragePlaces;
    
    public StoragePlaceBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public StoragePlaceEntity getSelectedStoragePlace() {
        return selectedStoragePlace;
    }

    public void setSelectedStoragePlace(StoragePlaceEntity selectedStoragePlace) {
        this.selectedStoragePlace = selectedStoragePlace;
    }

    public List<StoragePlaceEntity> getFilteredStoragePlaces() {
        return filteredStoragePlaces;
    }

    public void setFilteredStoragePlaces(List<StoragePlaceEntity> filteredStoragePlaces) {
        this.filteredStoragePlaces = filteredStoragePlaces;
    }
    
    @PostConstruct
    public void init() {
        try {
            kitchen = kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM..RawIngredientsManagementModule.storagePlaceBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredStoragePlaces = rim.getStoragePlaces(kitchen.getId());
    }

    public void addStoragePlace(ActionEvent event) {
        Long storagePlaceId = rim.addStoragePlace(kitchen.getId(), location);
        if (storagePlaceId == -1L) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "An unexpected exception occurred"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful", "New storage place " + storagePlaceId + " is added"));
        }
        filteredStoragePlaces = rim.getStoragePlaces(kitchen.getId());
    }

    public void onRowEdit(RowEditEvent event) {
        StoragePlaceEntity storagePlace = (StoragePlaceEntity) event.getObject();
        try {
            Long temp = rim.editStoragePlace(storagePlace.getId(), storagePlace.getLocation());
            if (temp == -1L) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Storage Place " + storagePlace.getId() + "is Edited");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM..RawIngredientsManagementModule.storagePlaceBean: onRowEdit(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredStoragePlaces = rim.getStoragePlaces(kitchen.getId());
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filteredStoragePlaces = rim.getStoragePlaces(kitchen.getId());
    }

    public void deleteStoragePlace(StoragePlaceEntity storagePlace) {
        try {
            Long temp = rim.deleteStoragePlace(storagePlace.getId());
            if (temp == -1L) {
                FacesMessage msg = new FacesMessage("Faild", "Storage Place has raw ingredients in it");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (temp == -2L) {
                FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Storage Placeˆ Deleted");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM..RawIngredientsManagementModule.storagePlaceBean: deleteStoragePlace(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredStoragePlaces = rim.getStoragePlaces(kitchen.getId());
    }

    
    
}
