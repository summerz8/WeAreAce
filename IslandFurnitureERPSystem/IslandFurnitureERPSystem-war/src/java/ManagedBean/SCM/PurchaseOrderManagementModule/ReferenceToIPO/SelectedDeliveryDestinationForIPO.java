/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.SCM.PurchaseOrderManagementModule.ReferenceToIPO;

import Entity.Factory.FactoryEntity;
import Entity.Store.StoreEntity;
import SessionBean.SCM.PurchaseOrderManagementModuleLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zhangshiyu
 */
@ManagedBean(name = "selectedDeliveryDestinationForIPO")
@ViewScoped
public class SelectedDeliveryDestinationForIPO {

       @EJB
    private PurchaseOrderManagementModuleLocal pmb;

    private String destination;
    private StoreEntity selectedStore = null;
    private FactoryEntity selectedFactory = null;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
        if (destination.equals("factory")) {
            try {
                Long factoryId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("factoryId");
                this.selectedFactory = pmb.getFactoryEntity(factoryId);
            } catch (Exception ex) {
                Logger.getLogger(SelectedDeliveryDestinationForIPO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public StoreEntity getSelectedStore() {
        return selectedStore;
    }

    public void setSelectedStore(StoreEntity selectedStore) {
        this.selectedStore = selectedStore;
    }

    public FactoryEntity getSelectedFactory() {
        return selectedFactory;
    }

    public void setSelectedFactory(FactoryEntity selectedFactory) {
        this.selectedFactory = selectedFactory;
    }


    public String passValue() {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("destination", destination);

        if (destination.equals("Store")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedStore", selectedStore);
        }
        return "/secured/restricted/Factory/SCM/PurchaseOrderManagementModule/ReferenceToIntegratedPlannedOrder/DisplayGeneratedPO?faces-redirect=true";
    }
    
    public SelectedDeliveryDestinationForIPO() {
    }
    
}
