/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

import SessionBean.SCM.FactoryInventoryManagementModuleLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "factoryBinBean")
@RequestScoped
public class FactoryBinBean {

    @EJB
    private FactoryInventoryManagementModuleLocal fim;

    @ManagedProperty(value = "#{loginBean.departmentId}")
    private long factoryId;
    private List storageBinInformation;

    public FactoryBinBean() {
    }

    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
        this.factoryId = factoryId;
    }

    public List getStorageBinInformation() {
        storageBinInformation = fim.listStorageBinInformation(factoryId);
        return storageBinInformation;
    }

    public void setStorageBinInformation(List storageBinInformation) {
        this.storageBinInformation = storageBinInformation;
    }

    public void recordCurrentInventoryLevel(ActionEvent event) {
        int temp = fim.recordCurrentInventoryLevel(factoryId);
        if (temp == -1) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to record current inventory level",
                            "unexpected exception occurred"));
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Record Successful!",
                            ""));
        }
    }

}
