/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.RawIngredientsManagementModule;

import Entity.Kitchen.IngredientSupplierEntity;
import Entity.Kitchen.KitchenEntity;
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
@ManagedBean(name = "ingredientSupplierBean")
@ViewScoped
public class IngredientSupplierBean implements Serializable {

    @EJB
    private RawIngredientsManagementModuleLocal rim;
    
    private KitchenEntity kitchen;
    private String name;
    private String address;
    private String contact;
    private String fax;
    private String remark;
    private IngredientSupplierEntity selectedSupplier;
    private List<IngredientSupplierEntity> filteredSuppliers;

    public IngredientSupplierBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public IngredientSupplierEntity getSelectedSupplier() {
        return selectedSupplier;
    }

    public void setSelectedSupplier(IngredientSupplierEntity selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }

    public List<IngredientSupplierEntity> getFilteredSuppliers() {
        return filteredSuppliers;
    }

    public void setFilteredSuppliers(List<IngredientSupplierEntity> filteredSuppliers) {
        this.filteredSuppliers = filteredSuppliers;
    }
    
    @PostConstruct
    public void init() {
        try {
            kitchen = kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM..RawIngredientsManagementModule.IngredientSupplierBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredSuppliers = rim.getSuppliers(kitchen.getId());
    }

    public void addSupplier(ActionEvent event) {
        Long supplierId = rim.addSupplier(kitchen.getId(), name, address, contact, fax, remark);
        if (supplierId == -1L) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "An unexpected exception occurred"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful", "New ingredient supplier " + supplierId + " is added"));
        }
        filteredSuppliers = rim.getSuppliers(kitchen.getId());
    }

    public void onRowEdit(RowEditEvent event) {
        IngredientSupplierEntity supplier = (IngredientSupplierEntity) event.getObject();
        try {
            Long temp = rim.editSupplier(supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getContact(), supplier.getFax(), supplier.getRemark());
            if (temp == -1L) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Ingredient Supplier " + supplier.getId() + "is Edited");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM.RawIngredientsManagementModule.IngredientSupplierBean: onRowEdit(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredSuppliers = rim.getSuppliers(kitchen.getId());
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        filteredSuppliers = rim.getSuppliers(kitchen.getId());
    }

    public void deleteSupplier(IngredientSupplierEntity supplier) {
        try {
            Long temp = rim.deleteSupplier(supplier.getId());
            if (temp == -1L) {
                FacesMessage msg = new FacesMessage("Faild", "Ingredient Supplier has raw ingredients to supply currently");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (temp == -2L) {
                FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Successful", "Ingredient Supplier Deleted");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.RawIngredientsManagementModule.IngredientSupplierBean: deleteSupplier(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
        filteredSuppliers = rim.getSuppliers(kitchen.getId());
    }

}
