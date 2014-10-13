/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

import SessionBean.SCM.FactoryInventoryManagementModuleLocal;
import java.util.List;
import javax.ejb.EJB;
<<<<<<< HEAD
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
=======
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
>>>>>>> 2b4be6cba12607486d6f2d0ee91e40619f339de7

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
<<<<<<< HEAD
    private Long factoryId;
=======
    private long factoryId;
>>>>>>> 2b4be6cba12607486d6f2d0ee91e40619f339de7
    private List storageBinInformation;

    public FactoryBinBean() {
    }

<<<<<<< HEAD
    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
=======
    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
>>>>>>> 2b4be6cba12607486d6f2d0ee91e40619f339de7
        this.factoryId = factoryId;
    }

    public List getStorageBinInformation() {
        storageBinInformation = fim.listStorageBinInformation(factoryId);
        return storageBinInformation;
    }

    public void setStorageBinInformation(List storageBinInformation) {
        this.storageBinInformation = storageBinInformation;
    }

<<<<<<< HEAD
=======
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

>>>>>>> 2b4be6cba12607486d6f2d0ee91e40619f339de7
}
