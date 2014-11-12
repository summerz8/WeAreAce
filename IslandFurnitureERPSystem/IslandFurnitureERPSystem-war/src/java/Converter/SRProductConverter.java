/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Converter;

import Entity.Store.StoreRetailProductEntity;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author zhengyuan
 */

@FacesConverter("sRProductConverter")
public class SRProductConverter implements Converter {
     public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
     
        System.out.println("converter: value:" + value);
     
        if(value != null && value.trim().length() > 0 && !value.equals("[Select One]")) {
            List<StoreRetailProductEntity> storeRetailProductEntities = (List<StoreRetailProductEntity>)fc.getExternalContext().getSessionMap().get("storeRetailProductEntities");
            
           
            
            for(StoreRetailProductEntity srpEntity:storeRetailProductEntities)
            {
                System.out.println("converter: srpEntity:" + srpEntity.getStoreRetailProductId());
                
                if(srpEntity.getStoreRetailProductId().equals(Long.valueOf(value)))
                {
                    
                    System.out.println("Converter: found srpEntity: " + srpEntity.getStoreRetailProductId());
                   return srpEntity;
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
            
            if(object instanceof String)
            {
                return null;
            }
            else
            {
                return String.valueOf(((StoreRetailProductEntity) object).getStoreRetailProductId());
            }
        }
        else {
            return null;
        }
    }   
    
}
