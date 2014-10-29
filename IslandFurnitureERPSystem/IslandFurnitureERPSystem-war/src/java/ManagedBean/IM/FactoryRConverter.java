/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRetailProductEntity;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author zhengyuan
 */
@FacesConverter("factoryRConverter")
public class FactoryRConverter implements Converter {
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            List<FactoryRetailProductEntity> availFactoryEntities = (List<FactoryRetailProductEntity>)fc.getExternalContext().getSessionMap().get("factoryREntities");
            
            System.err.println("Converter: " + availFactoryEntities.size());
            
            for(FactoryRetailProductEntity factoryEntity:availFactoryEntities)
            {
                if(factoryEntity.getFactoryRetailProdctId().equals(value))
                {
                   return factoryEntity;
                }
            }
            
            return null;
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((FactoryRetailProductEntity) object).getFactoryRetailProdctId());
        }
        else {
            return null;
        }
    }   
    
}
