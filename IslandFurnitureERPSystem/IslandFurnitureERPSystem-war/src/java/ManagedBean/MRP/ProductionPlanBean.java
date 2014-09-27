/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.ProductionPlanEntity;
import SessionBean.MRP.PlannedOrderManagementModuleLocal;
import SessionBean.MRP.ProductionPlanManagementModuleLocal;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author hangsun
 */
@Named(value = "productionPlanView")
@ViewScoped
public class ProductionPlanBean implements Serializable {

    /**
     * Creates a new instance of PlannedOrderList
     */
    private List<ProductionPlanEntity> productionPlan;
    private List<ProductionPlanEntity> productionPlanConfirmed;
    private List<ProductionPlanEntity> productionPlanUnconfirmed;
    private List<ProductionPlanEntity> productionPlanCancelled;

    @EJB
    private ProductionPlanManagementModuleLocal PP;
    @EJB
    private PlannedOrderManagementModuleLocal PO;
    

    private ProductionPlanEntity pp;
    private Long productionPlanId;
    private Object quantity;
//    private Calendar targetPeriod;
//    private Long productId;
    private String status;
    private String remark;
    private Long factoryId;

    public Long getProductionPlanId() {
        return productionPlanId;
    }

    public void setProductionPlanId(Long productionPlanId) {
        this.productionPlanId = productionPlanId;
    }

    public Object getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {

        this.quantity = quantity;
    }
//

    public Long getFactoryId() {
        return factoryId;
    }
    
    
//    public Calendar getTargetPeriod() {
//        
//        return targetPeriod;
//    }
//
//    public Long getProductId() {
//        return productId;
//    }
//
    public String getStatus() {
        return status;
    }
//
    public String getRemark() {
        return remark;
    }
//
//    public void setTargetPeriod(Calendar targetPeriod) {
//        this.targetPeriod = targetPeriod;
//    }
//
//    public void setProductId(Long productionId) {
//        this.productId = productionId;
//    }
//
    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @PostConstruct
    public void init() {
        factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        System.out.println("factoryId    " + factoryId);
        productionPlanUnconfirmed = PP.getProductionPlanUnconfirmed(factoryId);
        productionPlanConfirmed = PP.getProductionPlanConfirmed(factoryId);
        productionPlanCancelled = PP.getProductionPlanCancelled(factoryId);
    }

    public List<ProductionPlanEntity> getProductionPlanConfirmed() {
        return productionPlanConfirmed;
    }

    public List<ProductionPlanEntity> getProductionPlanUnconfirmed() {
        return productionPlanUnconfirmed;
    }

    public List<ProductionPlanEntity> getProductionPlanCancelled() {
        return productionPlanCancelled;
    }

    public List<ProductionPlanEntity> getProductionPlan() {
        return productionPlan;
    }

    
    
    public void saveId(Long id) {
        System.out.println("5");
        productionPlanId = id;
        System.out.println("6");
        pp = PP.searchProductionPlan(productionPlanId);
        System.out.println(productionPlanId + "!@#$%^&*&*");
        if (!pp.getQuantity().equals(quantity) && quantity != null) {
            save(productionPlanId, "quantity", quantity);
        } else if (!pp.getStatus().equals(status) && status != null) {
            save(productionPlanId, "status", status);
        } else if (!pp.getRemark().equals(remark) && remark != null) {
            save(productionPlanId, "remark", remark);
        }
//        else if(!pp.getTargetPeriod().equals(targetPeriod) && targetPeriod != null)
//            save(productionPlanId,"targetPeriod",targetPeriod);

    }

//    public void targetPeriodChanged(ValueChangeEvent event) {
//        System.out.println("period1");
//        Object newValue = event.getNewValue(); 
//        Date targetPeriodDate = (Date) newValue;
//        System.out.println("date"+targetPeriodDate.toString());
//        targetPeriod = Calendar.getInstance();
//        targetPeriod.setTime(targetPeriodDate);
//        System.out.println("period2" + targetPeriod.toString());
//}
    public void quantityChanged(ValueChangeEvent event) {
        Object newValue = event.getNewValue();
        quantity = (Double) newValue;
    }

    public void statusChanged(ValueChangeEvent event) {
        System.out.println("1");
        Object newValue = event.getNewValue();
        System.out.println("2");
        String statusId = (String) newValue;
        switch (statusId) {
            case "0":
                status = "unconfirmed";
                break;
            case "1":
                status = "confirmed";
                break;
            case "2":
                status = "cancelled";
                break;
        }
    }

    public void remarkChanged(ValueChangeEvent event) {
        System.out.println("remark1");
        Object newValue = event.getNewValue();
        remark = (String) newValue;
        System.out.println("remark2" + remark);
    }

    public void save(Long productionPlanId, String field, Object content) {
        System.out.println("9");
        PP.editProductionPlan(productionPlanId, field, content);
        System.out.println("10");
    }
    
    public String confirm(Long id){
        Calendar confirmDate = Calendar.getInstance();
        PP.editProductionPlan(id, "status", "confirmed");
        PP.editProductionPlan(id, "confirmDate", confirmDate);
        PO.createPlannedOrder(id);
        return "/secured/restricted/Factory/MRP/ProductionPlan/MRPProductionPlanUnconfirmed?faces-redirect=true";
    }
    
    public String cancel(Long id){
        PP.editProductionPlan(id, "status", "cancelled");
        return "/secured/restricted/Factory/MRP/ProductionPlan/MRPProductionPlanUnconfirmed?faces-redirect=true";
    }
    

}
