/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.MenuManagementModule;

import Entity.Kitchen.ComboEntity;
import Entity.Kitchen.DishItemEntity;
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
@ManagedBean(name = "dishItemsBean")
@ViewScoped
public class DishItemsBean implements Serializable {

    @EJB
    MenuManagementModuleLocal mm;

    private ComboEntity selectedCombo;
    private List<DishItemEntity> filteredDishItems;

    public DishItemsBean() {
    }

    public ComboEntity getSelectedCombo() {
        return selectedCombo;
    }

    public void setSelectedCombo(ComboEntity selectedCombo) {
        this.selectedCombo = selectedCombo;
    }

    public List<DishItemEntity> getFilteredDishItems() {
        return filteredDishItems;
    }

    public void setFilteredDishItems(List<DishItemEntity> filteredDishItems) {
        this.filteredDishItems = filteredDishItems;
    }

    @PostConstruct
    public void init() {
        try {
            selectedCombo = (ComboEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedCombo");
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.DishItemsBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredDishItems = mm.getDishItems(selectedCombo.getId());
    }

    public void onRowEdit(RowEditEvent event) {
        DishItemEntity di = (DishItemEntity) event.getObject();
        try {
            Long diId = mm.editDishItem(di.getId(), di.getQuantity());
            if (diId == -1L) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Dish Item " + diId + " is Edited");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM.MenuManagementModule.DishItemsBean: onRowEdit(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredDishItems = mm.getDishItems(selectedCombo.getId());
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filteredDishItems = mm.getDishItems(selectedCombo.getId());
    }

    public void deleteDishItem(Long dishItemId) {
        Long temp = mm.deleteDishItem(selectedCombo.getId(), dishItemId);
        if (temp == -1L) {
            FacesMessage msg = new FacesMessage("Deletion Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Deletion Successful", "Dish Item " + dishItemId + " is deleted");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        filteredDishItems = mm.getDishItems(selectedCombo.getId());
    }
}
