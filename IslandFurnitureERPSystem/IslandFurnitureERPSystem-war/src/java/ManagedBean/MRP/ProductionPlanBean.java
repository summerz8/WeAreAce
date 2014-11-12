/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.MRP.WeeklyProductionPlanEntity;
import SessionBean.MRP.PlannedOrderManagementModuleLocal;
import SessionBean.MRP.ProductionPlanManagementModuleLocal;
import SessionBean.MRP.WeeklyProductionPlanLocal;
import java.io.Serializable;
import java.util.Calendar;
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
    @EJB
    private WeeklyProductionPlanLocal WPO;

    private ProductionPlanEntity pp;
    private Long productionPlanId;
    private Object quantity;
//    private Calendar targetPeriod;
//    private Long productId;
    private String status;
    private String remark;
    private Long id;
    private String department;

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

    public Long getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public void setProductionPlan(List<ProductionPlanEntity> ProductionPlan) {
        this.productionPlan = ProductionPlan;
    }

    public void setProductionPlanConfirmed(List<ProductionPlanEntity> productionPlanConfirmed) {
        this.productionPlanConfirmed = productionPlanConfirmed;
    }

    public void setProductionPlanUnconfirmed(List<ProductionPlanEntity> productionPlanUnconfirmed) {
        this.productionPlanUnconfirmed = productionPlanUnconfirmed;
    }

    public void setProductionPlanCancelled(List<ProductionPlanEntity> productionPlanCancelled) {
        this.productionPlanCancelled = productionPlanCancelled;
    }

    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }
//

    public String getRemark() {
        return remark;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @PostConstruct
    public void init() {
        try {
            id = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            department = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("department");
            productionPlanUnconfirmed = PP.getProductionPlanUnconfirmed(id, department);
            productionPlanConfirmed = PP.getProductionPlanConfirmed(id, department);
            productionPlanCancelled = PP.getProductionPlanCancelled(id, department);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception.");
            ex.printStackTrace();
        }
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
        try {
            productionPlanId = id;
            pp = PP.searchProductionPlan(productionPlanId);
            if (!pp.getQuantity().equals(quantity) && quantity != null) {
                save(productionPlanId, "quantity", quantity);
            } else if (!pp.getStatus().equals(status) && status != null) {
                save(productionPlanId, "status", status);
            } else if (!pp.getRemark().equals(remark) && remark != null) {
                save(productionPlanId, "remark", remark);
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

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
        try {
            System.out.println("9");
            PP.editProductionPlan(productionPlanId, field, content);
            System.out.println("10");
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    public String confirm(Long id) {
        try {
            Calendar confirmDate = Calendar.getInstance();
            PP.editProductionPlan(id, "status", "confirmed");
            PP.editProductionPlan(id, "confirmDate", confirmDate);
            PO.createPlannedOrder(id);
            WPO.generateWeeklyProductionPlan(id);

            return "/secured/restricted/Factory/MRP/ProductionPlan/MRPProductionPlanUnconfirmed?faces-redirect=true";
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    public String cancel(Long id) {
        try {
            PP.editProductionPlan(id, "status", "cancelled");
            return "/secured/restricted/Factory/MRP/ProductionPlan/MRPProductionPlanUnconfirmed?faces-redirect=true";
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    public String viewWeeklyProductionPlan(List<WeeklyProductionPlanEntity> selectedWeeklyProductionPlan) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedWeeklyProductionPlan", selectedWeeklyProductionPlan);
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedProductionPlan", selectedProductionPlan);
        return "/secured/restricted/Factory/MRP/ProductionPlan/MRPWeeklyProductionPlanView?faces-redirect=true";

    }

}
