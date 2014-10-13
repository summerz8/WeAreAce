/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

import SessionBean.SCM.FactoryInventoryManagementModuleLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

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
    private Long factoryId;
    private List storageBinInformation;

    public FactoryBinBean() {
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public List getStorageBinInformation() {
        storageBinInformation = fim.listStorageBinInformation(factoryId);
        return storageBinInformation;
    }

    public void setStorageBinInformation(List storageBinInformation) {
        this.storageBinInformation = storageBinInformation;
    }

}
