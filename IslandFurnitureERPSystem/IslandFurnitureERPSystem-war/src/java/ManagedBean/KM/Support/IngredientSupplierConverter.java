/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.Support;

import Entity.Kitchen.IngredientSupplierEntity;
import Entity.Kitchen.KitchenEntity;
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
@FacesConverter("ingredientSupplierConverter")
public class IngredientSupplierConverter implements Converter {

    KitchenSupportLocal s = lookupKitchenSupportLocal();

    private KitchenEntity kitchen;

    public IngredientSupplierConverter() {
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
            System.err.println("ManagedBean.KM.Support.IngredientSupplierConverter: init(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
        }
    }

    @Override
    public IngredientSupplierEntity getAsObject(FacesContext context, UIComponent conponent, String name) {
        try {
            return s.findIngredientSupplier(kitchen.getId(), name);
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.Support.IngredientSupplierConverter: getAsObject(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent conponent, Object is) {
        try {
            if (is == null) {
                return null;
            }

            if (is instanceof IngredientSupplierEntity) {
                IngredientSupplierEntity supplier = (IngredientSupplierEntity) is;
                return supplier.getName();
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.Support.IngredientSupplierConverter: getAsString(): Failed. Caught an unexpected exception.");
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
