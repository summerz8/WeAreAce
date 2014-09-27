/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

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

    @ManagedProperty(value = "#{loginBean.userId}")
    private String userId;
    private long factoryId;
    private List weeklyRawMaterialInventoryInFlow;
    private List weeklyRawMaterialInventoryOutFlow;

    @EJB
    private RawMaterialInventoryMonitoringModuleLocal rmim;

    public RawMaterialInventoryMonitoringBean() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        this.setFactoryId(rmim.findFactoryIdByUserId(userId));
    }

    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
        this.factoryId = factoryId;
    }

    public List getWeeklyRawMaterialInventoryInFlow() {
        weeklyRawMaterialInventoryInFlow = rmim.ViewWeeklyRawMaterialInventoryInFlow(factoryId);
        return weeklyRawMaterialInventoryInFlow;
    }

    public void setWeeklyRawMaterialInventoryInFlow(List weeklyRawMaterialInventoryInFlow) {
        this.weeklyRawMaterialInventoryInFlow = weeklyRawMaterialInventoryInFlow;
    }

    public List getWeeklyRawMaterialInventoryOutFlow() {
        weeklyRawMaterialInventoryOutFlow = rmim.ViewWeeklyRawMaterialInventoryOutFlow(factoryId);
        return weeklyRawMaterialInventoryOutFlow;
    }

    public void setWeeklyRawMaterialInventoryOutFlow(List weeklyRawMaterialInventoryOutFlow) {
        this.weeklyRawMaterialInventoryOutFlow = weeklyRawMaterialInventoryOutFlow;
    }

}
