/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
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
    
    private List<IntegratedPlannedOrderEntity> integratedPlannedOrder;
    private List<PlannedOrderEntity> plannedOrderConfirmed;
    private Calendar targetMonth; 
    private Long factoryRawMaterialId;
    
    
    @EJB
    private IntegratedPlannedOrderManagementLocal IPO;

    public List<IntegratedPlannedOrderEntity> getIntegratedPlannedOrder() {
        return integratedPlannedOrder;
    }

    public void setIntegratedPlannedOrder(List<IntegratedPlannedOrderEntity> integratedPlannedOrder) {
        this.integratedPlannedOrder = integratedPlannedOrder;
    }
   
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
        targetMonth.set(Calendar.DATE,1);
        targetMonth.set(Calendar.MONTH,targetMonth.get(Calendar.MONTH)+1);        
        integratedPlannedOrder = IPO.getIntegratedPlannedOrder();
    }
    
    public String createIntegratedPlannedOrder(Long factoryRawMaterialId){
    
        IPO.createIntegratedPlannedOrder(targetMonth, factoryRawMaterialId);
        
        return "/secured/restricted/Factory/MRP/PlannedOrder/MRPIntegratedPlannedOrderView?faces-redirect=true";
        
    }
    
    
}
