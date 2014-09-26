/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.PlannedOrderEntity;
import SessionBean.MRP.IntegratedPlannedOrderManagementLocal;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hangsun
 */
@Named(value = "integratedPlannedOrderView")
@ViewScoped
public class IntegratedPlannedOrderBean {

    /**
     * Creates a new instance of IntegratedPlannedOrderBean
     */
    public IntegratedPlannedOrderBean() {
    }
    
    private List<PlannedOrderEntity> plannedOrderConfirmed;
    private Calendar targetMonth; 
    private Long factoryRawMaterialId;
    
    
    @EJB
    private IntegratedPlannedOrderManagementLocal IPO;

    public List<PlannedOrderEntity> getPlannedOrderConfirmed() {
        return plannedOrderConfirmed;
    }

    public Calendar getTargetMonth() {
        return targetMonth;
    }

    public void setPlannedOrderConfirmed(List<PlannedOrderEntity> plannedOrderConfirmed) {
        this.plannedOrderConfirmed = plannedOrderConfirmed;
    }

    public void setTargetMonth(Calendar targetMonth) {
        this.targetMonth = targetMonth;
    }

    public Long getFactoryRawMaterialId() {
        return factoryRawMaterialId;
    }

    public void setFactoryRawMaterialId(Long factoryRawMaterialId) {
        this.factoryRawMaterialId = factoryRawMaterialId;
    }
    
    
            
    @PostConstruct
    public void init(){
        targetMonth = Calendar.getInstance();
    }
    
    public String createIntegratedPlannedOrder(Long factoryRawMaterialId){
        
        
        IPO.createIntegratedPlannedOrder(targetMonth, factoryRawMaterialId);
        
        return "MRPIntegratedPlannedOrderView?faces-redirect=true";
        
    }
    
}
