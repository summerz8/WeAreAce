/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import SessionBean.MRP.PlannedOrderManagementModuleLocal;
import SessionBean.MRP.ProductionPlanManagementModuleLocal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hangsun
 */
@Named(value = "plannedOrderView")
@ViewScoped
public class PlannedOrderBean {

    /**
     * Creates a new instance of PlannedOrderList
     */
    public PlannedOrderBean() {
    }
    
    private List<PlannedOrderEntity> plannedOrder;
    private List<PlannedOrderEntity> plannedOrderUnconfirmed;
    private List<PlannedOrderEntity> plannedOrderConfirmed;
    private List<PlannedOrderEntity> plannedOrderCancelled;
//    private List<FactoryRawMaterialAmountEntity> selectedRawMaterial;
    
    @EJB
    private PlannedOrderManagementModuleLocal PO;
    @EJB
    private ProductionPlanManagementModuleLocal PP;
    
    @PostConstruct
    public void init(){
        System.out.println("2");

          plannedOrder = PO.getPlannedOrder();
          System.out.println("3");

          plannedOrderUnconfirmed = PO.getUnconfirmedPlannedOrder();
          System.out.println("4");

          plannedOrderConfirmed = PO.getConfirmedPlannedOrder();
          System.out.println("5");

          plannedOrderCancelled = PO.getCancelledPlannedOrder();
          System.out.println("6");

    }
    
    public List<PlannedOrderEntity> getPlannedOrder(){
        return plannedOrder;
    } 

    public List<PlannedOrderEntity> getPlannedOrderUnconfirmed() {
        return plannedOrderUnconfirmed;
    }

    public List<PlannedOrderEntity> getPlannedOrderConfirmed() {
        return plannedOrderConfirmed;
    }

    public List<PlannedOrderEntity> getPlannedOrderCancelled() {
        return plannedOrderCancelled;
    }

//    public List<FactoryRawMaterialAmountEntity> getSelectedRawMaterial() {
//        return selectedRawMaterial;
//    }

    public void setPlannedOrder(List<PlannedOrderEntity> plannedOrder) {
        this.plannedOrder = plannedOrder;
    }

    public void setPlannedOrderUnconfirmed(List<PlannedOrderEntity> plannedOrderUnconfirmed) {
        this.plannedOrderUnconfirmed = plannedOrderUnconfirmed;
    }

    public void setPlannedOrderConfirmed(List<PlannedOrderEntity> plannedOrderConfirmed) {
        this.plannedOrderConfirmed = plannedOrderConfirmed;
    }

    public void setPlannedOrderCancelled(List<PlannedOrderEntity> plannedOrderCancelled) {
        this.plannedOrderCancelled = plannedOrderCancelled;
    }

//    public void setSelectedRawMaterial(List<FactoryRawMaterialAmountEntity> selectedRawMaterial) {
//        this.selectedRawMaterial = selectedRawMaterial;
//    }
    
    public String confirm(Long id){
        Calendar confirmDate = Calendar.getInstance();
        PO.editPlannedOrder(id, "confirmDate", confirmDate);
        PO.editPlannedOrder(id, "status", "confirmed");
        return "/secured/restricted/Factory/MRP/PlannedOrder/MRPPlannedOrderUnconfirmed?faces-redirect=true";
    }   
    
     public String cancel(Long id){
        System.out.println("!@#$!@!@%!@" + id);
        PO.editPlannedOrder(id, "status", "cancelled");
        return "/secured/restricted/Factory/MRP/PlannedOrder/MRPPlannedOrderUnconfirmed?faces-redirect=true";
    }   
    
    public String viewRawMaterial(List<FactoryRawMaterialAmountEntity> selectedRawMaterial){
        System.out.println("0");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedRawMaterial", selectedRawMaterial);
        System.out.println("1");

        return "/secured/restricted/Factory/MRP/PlannedOrder/MRPViewRawMaterial?faces-redirect=true";

    }
    
    
    public Date convert(Calendar cal){
        Date result = cal.getTime();
        
        System.out.println("Calendar"+cal.getTime().toString());
        System.out.println("Date"+ result.toString());
        
        return result;
    }
    
}
