/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.ProcurementManagementModule;

import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.IngredientPurchaseOrderEntity;
import Entity.Kitchen.IngredientPurchaseOrderToSupplierEntity;
import Entity.Kitchen.KitchenEntity;
import SessionBean.KM.ProcurementManagementModuleLocal;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "ingredientPurchaseOrderToSuppliersBean")
@ViewScoped
public class IngredientPurchaseOrderToSuppliersBean implements Serializable {

    @EJB
    private ProcurementManagementModuleLocal pm;

    private KitchenEntity kitchen;
    private IngredientPurchaseOrderEntity fromIPO;
    private List<IngredientPurchaseOrderToSupplierEntity> IPOSs;
    private List<IngredientPurchaseOrderToSupplierEntity> filteredIPOSs;
    private String message;
    private Calendar cal;
    private IngredientPurchaseOrderToSupplierEntity selectedIPOS;
    private Date selectedTargetDate;

    public IngredientPurchaseOrderToSuppliersBean() {
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public IngredientPurchaseOrderEntity getFromIPO() {
        return fromIPO;
    }

    public void setFromIPO(IngredientPurchaseOrderEntity fromIPO) {
        this.fromIPO = fromIPO;
    }

    public List<IngredientPurchaseOrderToSupplierEntity> getIPOSs() {
        return IPOSs;
    }

    public void setIPOSs(List<IngredientPurchaseOrderToSupplierEntity> IPOSs) {
        this.IPOSs = IPOSs;
    }

    public List<IngredientPurchaseOrderToSupplierEntity> getFilteredIPOSs() {
        return filteredIPOSs;
    }

    public void setFilteredIPOSs(List<IngredientPurchaseOrderToSupplierEntity> filteredIPOSs) {
        this.filteredIPOSs = filteredIPOSs;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    public Date getSelectedTargetDate() {
        return selectedTargetDate;
    }

    public void setSelectedTargetDate(Date selectedTargetDate) {
        this.selectedTargetDate = selectedTargetDate;
    }

    public IngredientPurchaseOrderToSupplierEntity getSelectedIPOS() {
        return selectedIPOS;
    }

    public void setSelectedIPOS(IngredientPurchaseOrderToSupplierEntity selectedIPOS) {
        this.selectedIPOS = selectedIPOS;
    }

    @PostConstruct
    public void init() {
        try {
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
            cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 2);
            fromIPO = (IngredientPurchaseOrderEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("IPOForIPOSs");
            if (fromIPO == null) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('selectTargetDate').show();");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("IPOForIPOSs", null);
                IPOSs = fromIPO.getIPOSs();
                filteredIPOSs = IPOSs;
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.IngredientPurchaseOrderToSuppliersBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void findRequiredIngredientPurchaseOrderToSuppliers(ActionEvent event) {
        try {
            if (selectedTargetDate == null) {
                message = "Please Select A Date";
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('message').show();");
            } else {
                fromIPO = pm.findIngredientPurchaseOrder(kitchen.getId(), selectedTargetDate);
                if (fromIPO == null) {
                    System.out.println("1");
                    message = "The Integrated Raw Ingredient Purchase Order for the selected target date is not generated yet";
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('message').show();");
                } else {
                    System.out.println("2");
                    if (fromIPO.getStatus().equals("Unconfirmed")) {
                        System.out.println("3");
                        message = "Please confirm the Integrated Raw Ingredient Purchase Order for the selected target date first";
                        RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('message').show();");
                    } else {
                        System.out.println("4");
                        IPOSs = fromIPO.getIPOSs();
                        filteredIPOSs = IPOSs;
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: findRequiredIngredientPurchaseOrderToSuppliers(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            IngredientPurchaseOrderToSupplierEntity ipos = (IngredientPurchaseOrderToSupplierEntity) event.getObject();
            if (ipos.getActualTotal() < 0) {
                FacesMessage msg = new FacesMessage("Edition Faild", "Actual Total cannot be negative");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                Long iposId = pm.editIPOSActualTotal(ipos.getId(), ipos.getActualTotal());
                if (iposId == -1L) {
                    FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    FacesMessage msg = new FacesMessage("Successful", " Raw Ingredient Purchase Order to Supplier " + ipos.getId() + " is Edited");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
            fromIPO = pm.findIngredientPurchaseOrderById(fromIPO.getId());
            IPOSs = pm.findIngredientPurchaseOrdersToSuppliersByIPO(fromIPO.getId());
            filteredIPOSs = IPOSs;
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Edition Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("ManagedBean.KM.MenuManagementModule.MenuItemForecastBean: onRowEditDish(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        IPOSs = pm.findIngredientPurchaseOrdersToSuppliersByIPO(fromIPO.getId());
        filteredIPOSs = IPOSs;
    }

    public void confirmAllIPOSs() {
        Long temp = pm.confirmAllIPOSs(fromIPO.getId());
        if (temp == -1L) {
            FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Successful", "All Ingredient Purchase Orders to Suppliers are confirmed");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        fromIPO = pm.findIngredientPurchaseOrderById(fromIPO.getId());
        IPOSs = pm.findIngredientPurchaseOrdersToSuppliersByIPO(fromIPO.getId());
        filteredIPOSs = IPOSs;
    }

    public void confirmedIPOS(IngredientPurchaseOrderToSupplierEntity ipos) {
        Long temp = pm.confirmIPOS(ipos.getId());
        if (temp == -1L) {
            FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Successful", "Ingredient Purchase Order to Supplier " + ipos.getId() + " is confirmed");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        fromIPO = pm.findIngredientPurchaseOrderById(fromIPO.getId());
        IPOSs = pm.findIngredientPurchaseOrdersToSuppliersByIPO(fromIPO.getId());
        filteredIPOSs = IPOSs;
    }

    public void generateIngredientReceipt(IngredientPurchaseOrderToSupplierEntity selectedIPOS) {
        Long irId = pm.generateIngredientReceipt(selectedIPOS.getId());
        if (irId == -1L) {
            FacesMessage msg = new FacesMessage("Faild", "Unexpected Exception Occurred");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Successful", "New Raw Ingredient Receipt " + irId + " is generated");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        fromIPO = pm.findIngredientPurchaseOrderById(fromIPO.getId());
        IPOSs = pm.findIngredientPurchaseOrdersToSuppliersByIPO(fromIPO.getId());
        filteredIPOSs = IPOSs;
    }

}
