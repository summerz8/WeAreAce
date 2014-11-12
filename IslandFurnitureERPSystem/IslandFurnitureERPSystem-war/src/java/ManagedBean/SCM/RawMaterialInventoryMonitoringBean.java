/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

import Entity.Factory.SCM.InboundMovementEntity;
import SessionBean.SCM.RawMaterialInventoryMonitoringModuleLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "rawMaterialInventoryMonitoringBean")
@ViewScoped
public class RawMaterialInventoryMonitoringBean {

    @ManagedProperty(value = "#{loginBean.department}")
    private String department;
    @ManagedProperty(value = "#{loginBean.departmentId}")
    private Long factoryId;
    private List weeklyRawMaterialInventoryInFlow;
    private List weeklyRawMaterialInventoryOutFlow;

    @EJB
    private RawMaterialInventoryMonitoringModuleLocal rmim;

    public RawMaterialInventoryMonitoringBean() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public List<InboundMovementEntity> getWeeklyRawMaterialInventoryInFlow() {
        if (department.equals("H")) {
            try {
                weeklyRawMaterialInventoryInFlow = rmim.viewWeeklyRawMaterialInventoryInFlow(factoryId);
            } catch (Exception ex) {
                System.err.println("ManagedBean.SCM.RawMaterialInventoryMonitoringBean: getWeeklyRawMaterialInventoryInFlow(): Failed. Caught an unexpected exception.");
                ex.printStackTrace();
                return null;
            }
        } else {
            weeklyRawMaterialInventoryInFlow = rmim.viewAllWeeklyRawMaterialInventoryInFlow();
        }
        return weeklyRawMaterialInventoryInFlow;
    }

    public void setWeeklyRawMaterialInventoryInFlow(List weeklyRawMaterialInventoryInFlow) {
        this.weeklyRawMaterialInventoryInFlow = weeklyRawMaterialInventoryInFlow;
    }

    public List getWeeklyRawMaterialInventoryOutFlow() {
        if (department.equals("H")) {
            weeklyRawMaterialInventoryOutFlow = rmim.viewWeeklyRawMaterialInventoryOutFlow(factoryId);
        } else {
            weeklyRawMaterialInventoryOutFlow = rmim.viewAllWeeklyRawMaterialInventoryOutFlow();
        }
        return weeklyRawMaterialInventoryOutFlow;
    }

    public void setWeeklyRawMaterialInventoryOutFlow(List weeklyRawMaterialInventoryOutFlow) {
        this.weeklyRawMaterialInventoryOutFlow = weeklyRawMaterialInventoryOutFlow;
    }

}
