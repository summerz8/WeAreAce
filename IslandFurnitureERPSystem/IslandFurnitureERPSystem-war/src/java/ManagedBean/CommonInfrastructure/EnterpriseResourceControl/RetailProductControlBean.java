/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.Factory.ProductEntity;
import Entity.Factory.RetailProductEntity;
import SessionBean.CommonInFrastructure.RetailProduct_ProductManagementModuleLocal;
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
@Named(value = "retailControl")
@ViewScoped
public class RetailProductControlBean {

     @EJB
    private RetailProduct_ProductManagementModuleLocal RPMM;
    private List<RetailProductEntity> retailList;
    private List<RetailProductEntity> filteredRetail;
    
    private String newRetailProductName;
    private String newRetailProductDescription;   
    private String newRetailProductUnit;
    /**
     * Creates a new instance of RetailProductControlBean
     */
    public RetailProductControlBean() {
    }
    
    @PostConstruct
    public void init() {
        System.out.println("ProductControlBean: init:");

        retailList = RPMM.ListRetailProduct();
        filteredRetail = retailList;

    }

    public void onRowEdit(RowEditEvent event) {
        System.out.println("onRowEdit test:");
        RetailProductEntity entity = (RetailProductEntity) event.getObject();
        System.out.println("onRowEdit test: " + entity.getRetailProductId() + entity.getName());

        RPMM.ModifyRetailProduct(entity.getRetailProductId(), entity.getName(), entity.getUnit(), entity.getDescription());

        FacesMessage msg = new FacesMessage("Retail Product Edited", String.valueOf(entity.getRetailProductId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((ProductEntity) event.getObject()).getProductId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteRetailProduct(long id) {
        System.out.println("RetailProductControlBean: deleteRetailProduct: " + String.valueOf(id));      
        RPMM.DeleteRetailProduct(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Retail Product deleted successfully! ", ""));
        
        retailList = RPMM.ListRetailProduct();
        filteredRetail = retailList;
    }
    
    public void addRetailProduct() {
        System.out.println("RetailProductControlBean: addRetailProduct: ");
        RPMM.AddRetailProduct(newRetailProductName, newRetailProductDescription, newRetailProductUnit);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Retail Product added successfully! ", ""));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("RetailProductControl.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FactoryControlBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<RetailProductEntity> getRetailList() {
        return retailList;
    }

    public void setRetailList(List<RetailProductEntity> retailList) {
        this.retailList = retailList;
    }

    public List<RetailProductEntity> getFilteredRetail() {
        return filteredRetail;
    }

    public void setFilteredRetail(List<RetailProductEntity> filteredRetail) {
        this.filteredRetail = filteredRetail;
    }

    public String getNewRetailProductName() {
        return newRetailProductName;
    }

    public void setNewRetailProductName(String newRetailProductName) {
        this.newRetailProductName = newRetailProductName;
    }

    public String getNewRetailProductDescription() {
        return newRetailProductDescription;
    }

    public void setNewRetailProductDescription(String newRetailProductDescription) {
        this.newRetailProductDescription = newRetailProductDescription;
    }

    public String getNewRetailProductUnit() {
        return newRetailProductUnit;
    }

    public void setNewRetailProductUnit(String newRetailProductUnit) {
        this.newRetailProductUnit = newRetailProductUnit;
    }
    
    
}
