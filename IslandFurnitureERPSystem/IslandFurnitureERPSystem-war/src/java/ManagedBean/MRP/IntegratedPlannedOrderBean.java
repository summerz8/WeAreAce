/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.MRP;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import SessionBean.MRP.IntegratedPlannedOrderManagementLocal;
import java.util.Calendar;
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
    private Long id;
    private String department;
    private List<FactoryRawMaterialEntity> factoryRawMaterialList;
    
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

    public Long getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public List<FactoryRawMaterialEntity> getFactoryRawMaterialList() {
        return factoryRawMaterialList;
    }

    public void setFactoryRawMaterialList(List<FactoryRawMaterialEntity> factoryRawMaterialList) {
        this.factoryRawMaterialList = factoryRawMaterialList;
    }
    
    
    
    @PostConstruct
    public void init(){
        id = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        department = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("department");
        
        targetMonth = Calendar.getInstance();
        targetMonth.set(Calendar.DATE,1);
        targetMonth.set(Calendar.MONTH,targetMonth.get(Calendar.MONTH)+1);        
        integratedPlannedOrder = IPO.getIntegratedPlannedOrder(id,department);
        factoryRawMaterialList = IPO.getFactoryRawMaterial(id, department);
    }
    
    public String createIntegratedPlannedOrder(Long factoryRawMaterialId){
        int month = targetMonth.get(Calendar.MONTH)+1;
        int year = targetMonth.get(Calendar.YEAR);
        
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = IPO.getIntegratedPlannedOrder(id,department);
        for(IntegratedPlannedOrderEntity ipo: integratedPlannedOrderList){
            int m = ipo.getTargetPeriod().get(Calendar.MONTH)+1;
            int y = ipo.getTargetPeriod().get(Calendar.YEAR);
            
            if(ipo.getFactoryRawMaterialAmount().getFactoryRawMaterial().getFactoryRawMaterialId().equals(factoryRawMaterialId)
                    && month == m && year == y)
                return "/secured/restricted/Factory/MRP/PlannedOrder/MRPIntegratedPlannedOrderFalse?faces-redirect=true";
            
        }
        
        if(!IPO.findFactoryRawMaterialIdList(id, department, factoryRawMaterialId))
                
            return "/secured/restricted/Factory/MRP/PlannedOrder/MRPIntegratedPlannedOrderFalse?faces-redirect=true";
            
        
        IPO.createIntegratedPlannedOrder(targetMonth, factoryRawMaterialId,id,department);
        
        return "/secured/restricted/Factory/MRP/PlannedOrder/MRPIntegratedPlannedOrderView?faces-redirect=true";
        
    }
    
    
}
