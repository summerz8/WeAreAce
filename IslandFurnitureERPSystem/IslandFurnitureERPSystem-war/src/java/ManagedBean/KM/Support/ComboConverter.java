/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.KM.Support;

import Entity.Kitchen.ComboEntity;
import SessionBean.KM.KitchenSupportLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@FacesConverter("comboConverter")
public class ComboConverter implements Converter {

    KitchenSupportLocal s = lookupKitchenSupportLocal();

    @Override
    public ComboEntity getAsObject(FacesContext context, UIComponent conponent, String name) {
        try {
            return s.findCombo(name);
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.Support.ComboConverter: getAsObject(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent conponent, Object c) {
        try {
            if (c == null) {
                return null;
            }

            if (c instanceof ComboEntity) {
                ComboEntity combo = (ComboEntity) c;
                return combo.getName();
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("ManagedBean.KM.Support.ComboConverter: getAsString(): Failed. Caught an unexpected exception.");
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
