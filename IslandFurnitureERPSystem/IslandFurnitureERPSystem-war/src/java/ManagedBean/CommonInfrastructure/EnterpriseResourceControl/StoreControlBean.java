/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.CommonInfrastructure.UserEntity;
import Entity.Store.StoreEntity;
import SessionBean.CommonInFrastructure.Factory_StoreManagementModuleLocal;
import SessionBean.CommonInFrastructure.InternalUserAccountManagementModuleLocal;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author dan
 */
@Named(value = "storeControl")
@ViewScoped
public class StoreControlBean {

    @EJB
    private Factory_StoreManagementModuleLocal FSMM;
    @EJB
    private InternalUserAccountManagementModuleLocal IUMA;
    private List<StoreEntity> storeList;
    private List<StoreEntity> filterdStore;

    private String newStoreCountry;
    private String newStoreAddress;
    private String newStoreContact;
    private String newStoreManager;

    /**
     * Creates a new instance of StoreControl
     */
    public StoreControlBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("UserControlBean: init:");

        storeList = FSMM.ListStore();
        filterdStore = storeList;

    }

    public void onRowEdit(RowEditEvent event) {

        StoreEntity entity = (StoreEntity) event.getObject();
        System.out.println("onRowEdit test: " + String.valueOf(entity.getStoreId()) + entity.getManager());
        if (IUMA.getUser(entity.getManager()) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Store edit failed! ", "Manager not found!"));
        } else {
            try {
                FSMM.ModifyStore(entity.getStoreId(), entity.getCountry(), entity.getAddress(), entity.getContact(), entity.getManager());
            } catch (Exception ex) {
            }
            FacesMessage msg = new FacesMessage("Store Edited", String.valueOf(entity.getStoreId()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((StoreEntity) event.getObject()).getStoreId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteStore(long id) {
        System.out.println("StoreControlBean: deleteStore: " + String.valueOf(id));
        if (IUMA.ListStoreUser(id).isEmpty()) {
            try{FSMM.DeleteStore(id);}catch(Exception ex){}
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Store deleted successfully! ", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Store cannot be deleted! ", "Store user still exists!"));
            List<UserEntity> list = IUMA.ListStoreUser(id);
            for (UserEntity u : list) {
                System.out.println("Store associated user: " + u.getUserId());
            }
        }

        storeList = FSMM.ListStore();
        filterdStore = storeList;
    }

    public void addStore() {
        System.out.println("StoreControlBean: addStore: " + newStoreCountry + newStoreAddress + newStoreContact + newStoreManager);
//        if (IUMA.getUser(newStoreManager) == null) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Store added failed! ", "Manager not found!"));
//        } else {
        FSMM.AddStore(newStoreCountry, newStoreAddress, newStoreContact, newStoreManager);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Store added successfully! ", ""));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("StoreControl.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FactoryControlBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        //}
    }

    public List<StoreEntity> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<StoreEntity> storeList) {
        this.storeList = storeList;
    }

    public List<StoreEntity> getFilterdStore() {
        return filterdStore;
    }

    public void setFilterdStore(List<StoreEntity> filterdStore) {
        this.filterdStore = filterdStore;
    }

    public String getNewStoreCountry() {
        return newStoreCountry;
    }

    public void setNewStoreCountry(String newStoreCountry) {
        this.newStoreCountry = newStoreCountry;
    }

    public String getNewStoreAddress() {
        return newStoreAddress;
    }

    public void setNewStoreAddress(String newStoreAddress) {
        this.newStoreAddress = newStoreAddress;
    }

    public String getNewStoreContact() {
        return newStoreContact;
    }

    public void setNewStoreContact(String newStoreContact) {
        this.newStoreContact = newStoreContact;
    }

    public String getNewStoreManager() {
        return newStoreManager;
    }

    public void setNewStoreManager(String newStoreManager) {
        this.newStoreManager = newStoreManager;
    }

}
