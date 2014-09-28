/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.SalesOperationPlanEntity;
import SessionBean.MRP.SalesOperationPlanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "viewSalesOperationPlan")
@ViewScoped
public class ViewSalesOperationPlan {

    @EJB
    SalesOperationPlanLocal salesOperationPlanLocal;

    Long productId;
    List<SalesOperationPlanEntity> salesOperationPlanList;
    List<SalesOperationPlanEntity> unconfirmedSalesOperationPlanList = new ArrayList<>();
    List<SalesOperationPlanEntity> confirmedSalesOperationPlanList = new ArrayList<>();

    ;
    public ViewSalesOperationPlan() {
    }

    @PostConstruct
    public void viewSalesOperationPlan() {
        productId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("salesproductId");
        salesOperationPlanList = salesOperationPlanLocal.ListSalesOperationPlan(productId, null, null);
//        if(!salesOperationPlanList.isEmpty()){
        for (SalesOperationPlanEntity sop : salesOperationPlanList) {
            if (sop.getStatus().equals("unconfirmed")) {
                unconfirmedSalesOperationPlanList.add(sop);
            } else if (sop.getStatus().equals("confirmed")) {
                confirmedSalesOperationPlanList.add(sop);
            }
        }
//        }

    }

    public String edit(Long salesOperationPlanId) {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesOperationPlanId", salesOperationPlanId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("salesproductId");
        return "MRPEditSalesOperationPlan?faces-redirect=true";
    }

    public String confirm(Long salesOperationPlanId) {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesOperationPlanId", salesOperationPlanId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("salesproductId");
        return "MRPConfirmSalesOperationPlan?faces-redirect=true";
    }

    public String cancel(Long salesOperationPlanId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("salesOperationPlanId", salesOperationPlanId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("salesproductId");
        return "MRPCancelSalesOperationPlan?faces-redirect=true";

    }

    public String Back() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("salesproductId");
        return "MRPSalesOperationPlan?faces-redirect=true";
    }

    public SalesOperationPlanLocal getSalesOperationPlanLocal() {
        return salesOperationPlanLocal;
    }

    public void setSalesOperationPlanLocal(SalesOperationPlanLocal salesOperationPlanLocal) {
        this.salesOperationPlanLocal = salesOperationPlanLocal;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<SalesOperationPlanEntity> getSalesOperationPlanList() {
        return salesOperationPlanList;
    }

    public void setSalesOperationPlanList(List<SalesOperationPlanEntity> salesOperationPlanList) {
        this.salesOperationPlanList = salesOperationPlanList;
    }

    public List<SalesOperationPlanEntity> getUnconfirmedSalesOperationPlanList() {
        return unconfirmedSalesOperationPlanList;
    }

    public void setUnconfirmedSalesOperationPlanList(List<SalesOperationPlanEntity> unconfirmedSalesOperationPlanList) {
        this.unconfirmedSalesOperationPlanList = unconfirmedSalesOperationPlanList;
    }

    public List<SalesOperationPlanEntity> getConfirmedSalesOperationPlanList() {
        return confirmedSalesOperationPlanList;
    }

    public void setConfirmedSalesOperationPlanList(List<SalesOperationPlanEntity> confirmedSalesOperationPlanList) {
        this.confirmedSalesOperationPlanList = confirmedSalesOperationPlanList;
    }

}
