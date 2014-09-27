/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.FactoryEntity;
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
@Named(value = "factoryControl")
@ViewScoped
public class FactoryControlBean {

    @EJB
    private Factory_StoreManagementModuleLocal FSMM;
    @EJB
    private InternalUserAccountManagementModuleLocal IUMA;
    private List<FactoryEntity> factoryList;
    private List<FactoryEntity> filterdFactory;

    private String newFactoryCountry;
    private String newFactoryAddress;
    private String newFactoryContact;
    private String newFactoryManager;

    /**
     * Creates a new instance of FactoryStoreControlBean
     */
    public FactoryControlBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("UserControlBean: init:");

        factoryList = FSMM.ListFactory();
        filterdFactory = factoryList;

    }

    public void onRowEdit(RowEditEvent event) {
        System.out.println("onRowEdit test: ");
        FactoryEntity entity = (FactoryEntity) event.getObject();
        System.out.println("onRowEdit test: " + String.valueOf(entity.getFactoryId()) + entity.getManager());
        if (IUMA.getUser(entity.getManager()) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Factory edit failed! ", "Manager not found!"));
        } else {
            FSMM.ModifyFactory(entity.getFactoryId(), entity.getCountry(), entity.getAddress(), entity.getContact(), entity.getManager());

            FacesMessage msg = new FacesMessage("Factory Edited", String.valueOf(entity.getFactoryId()));
            FacesContext.getCurrentInstance().addMessage(null, msg);            
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(((FactoryEntity) event.getObject()).getFactoryId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteFactory(long id) {
        System.out.println("FactoryControlBean: deleteFactory: " + String.valueOf(id));
        if (IUMA.ListFactoryUser(id).isEmpty()) {
            FSMM.DeleteFactory(id);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Factory deleted successfully! ", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Factory cannot be deleted! ", "Factory user still exists!"));
            List<UserEntity> list = IUMA.ListFactoryUser(id);
            for (UserEntity u : list) {
                System.out.println("Factory associated user: " + u.getUserId());
            }
        }
        factoryList = FSMM.ListFactory();
        filterdFactory = factoryList;
    }

    public void addFactory() {
        System.out.println("FactoryControlBean: addFactory: ");
        if (IUMA.getUser(newFactoryManager) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Factory added failed! ", "Manager not found!"));
        } else {
            FSMM.AddFactory(newFactoryCountry, newFactoryAddress, newFactoryContact, newFactoryManager);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Factory added successfully! ", ""));

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("FactoryControl.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(FactoryControlBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<FactoryEntity> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(List<FactoryEntity> factoryList) {
        this.factoryList = factoryList;
    }

    public List<FactoryEntity> getFilterdFactory() {
        return filterdFactory;
    }

    public void setFilterdFactory(List<FactoryEntity> filterdFactory) {
        this.filterdFactory = filterdFactory;
    }

    public String getNewFactoryCountry() {
        return newFactoryCountry;
    }

    public void setNewFactoryCountry(String newFactoryCountry) {
        this.newFactoryCountry = newFactoryCountry;
    }

    public String getNewFactoryAddress() {
        return newFactoryAddress;
    }

    public void setNewFactoryAddress(String newFactoryAddress) {
        this.newFactoryAddress = newFactoryAddress;
    }

    public String getNewFactoryContact() {
        return newFactoryContact;
    }

    public void setNewFactoryContact(String newFactoryContact) {
        this.newFactoryContact = newFactoryContact;
    }

    public String getNewFactoryManager() {
        return newFactoryManager;
    }

    public void setNewFactoryManager(String newFactoryManager) {
        this.newFactoryManager = newFactoryManager;
    }

}
