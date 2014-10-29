/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.Support;

import Entity.Kitchen.KitchenEntity;
import SessionBean.KM.KitchenSupportLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yoky
 */
@ManagedBean(name = "findKitchenBean")
@ViewScoped
public class KitchenSupport implements Serializable {
    
    @EJB
    private KitchenSupportLocal s;

    private Long storeId;
    private KitchenEntity kitchen;
    
    public KitchenSupport() {
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }
    
    @PostConstruct
    public void init() {
        try {
            storeId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
            kitchen = s.findKitchenByStoreId(storeId);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("kitchen", kitchen);
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.Support.findKitchenBean: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }
    
}
