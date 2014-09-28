/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.SalesOperationPlanEntity;
import SessionBean.MRP.SalesOperationPlanLocal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "listEditSalesOperationPlan")
@ViewScoped
public class ListEditSalesOperationPlan {

    @EJB
    SalesOperationPlanLocal sopl;
    
    List<SalesOperationPlanEntity> salesOperationPlanList;
    List<SalesOperationPlanEntity> unconfirmedSalesOperationPlanList;   
    List<SalesOperationPlanEntity> confirmedSalesOperationPlanList;
    
    public ListEditSalesOperationPlan() {
    }
    
    @PostConstruct
    public void listEditSalesOperationPlan(){
        Calendar thisMonth=Calendar.getInstance();
        thisMonth.add(Calendar.MONTH, 1);    
        salesOperationPlanList=sopl.ListSalesOperationPlan(null,thisMonth,thisMonth); 
        unconfirmedSalesOperationPlanList=new ArrayList<>();
        confirmedSalesOperationPlanList=new ArrayList<>();
        while(!salesOperationPlanList.isEmpty()){
            if(salesOperationPlanList.get(0).getStatus().equals("Confirmed")){
                confirmedSalesOperationPlanList.add(salesOperationPlanList.get(0));
            }
            else unconfirmedSalesOperationPlanList.add(salesOperationPlanList.get(0));
            
            salesOperationPlanList.remove(0);
        }
        
    }

    public SalesOperationPlanLocal getSopl() {
        return sopl;
    }

    public void setSopl(SalesOperationPlanLocal sopl) {
        this.sopl = sopl;
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
