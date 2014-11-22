/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.Support;

import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.StoragePlaceEntity;
import SessionBean.KM.KitchenSupportLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Yoky
 */
@FacesConverter("storagePlaceConverter")
public class StoragePlaceConverter implements Converter {

    KitchenSupportLocal s = lookupKitchenSupportLocal();

    private KitchenEntity kitchen;

    public StoragePlaceConverter() {
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
            kitchen = (KitchenEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kitchen");
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.Support.StoragePlaceConverter: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }
    
    @Override
    public StoragePlaceEntity getAsObject(FacesContext context, UIComponent conponent, String location) {
        try {
            return s.findStoragePlace(kitchen.getId(), location);
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.Support.StoragePlaceConverter: getAsObject(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent conponent, Object sp) {
        try {
            if (sp == null) {
                return null;
            }

            if (sp instanceof StoragePlaceEntity) {
                StoragePlaceEntity storagePlace = (StoragePlaceEntity) sp;
                return storagePlace.getLocation();
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.Support.StoragePlaceConverter: getAsString(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    private KitchenSupportLocal lookupKitchenSupportLocal() {
        try {
            Context c = new InitialContext();
            return (KitchenSupportLocal) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/KitchenSupport!SessionBean.KM.KitchenSupportLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
