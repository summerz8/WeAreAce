/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.MenuManagementModule;

import Entity.Kitchen.ComboEntity;
import Entity.Kitchen.KitchenEntity;
import SessionBean.KM.MenuManagementModuleLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "comboListBean")
@ViewScoped
public class ComboListBean implements Serializable {

    @EJB
    MenuManagementModuleLocal mm;

    private KitchenEntity kitchen;
    private ComboEntity selectedCombo;
    private List<ComboEntity> filteredCombos;

    public ComboListBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public ComboEntity getSelectedCombo() {
        return selectedCombo;
    }

    public void setSelectedCombo(ComboEntity selectedCombo) {
        this.selectedCombo = selectedCombo;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedCombo", selectedCombo);
    }

    public List<ComboEntity> getFilteredCombos() {
        return filteredCombos;
    }

    public void setFilteredCombos(List<ComboEntity> filteredCombos) {
        this.filteredCombos = filteredCombos;
    }

    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.ComboListBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredCombos = mm.getCombos(kitchen.getId());
    }

    public void onRowEdit(RowEditEvent event) {
        ComboEntity combo = (ComboEntity) event.getObject();
        try {
            if (combo.getPrice() < 0) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Price cannot be negative");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                Long comboId = mm.editCombo(combo.getId(), combo.getName(), combo.getPrice(), combo.getRemark());
                if (comboId == -1L) {
                    FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    FacesMessage msg = new FacesMessage("Successful", "Combo " + combo.getName() + " is Edited");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM.ComboListBean: onRowEdit(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredCombos = mm.getCombos(kitchen.getId());
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filteredCombos = mm.getCombos(kitchen.getId());
    }

    public void deleteCombo(ComboEntity combo) {
        Long temp = mm.deleteCombo(combo.getId());
        if (temp == -1L) {
            FacesMessage msg = new FacesMessage("Deletion Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Deletion Successful", "Combo " + combo.getName() + " is deleted");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        filteredCombos = mm.getCombos(kitchen.getId());
    }
}
