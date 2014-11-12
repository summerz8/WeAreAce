/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Converter;

import Entity.Store.StoreProductEntity;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author zhengyuan
 */
@FacesConverter("sProductConverter")
public class SProductConverter implements Converter {
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
     
        System.out.println("converter: value:" + value);
     
        if(value != null && value.trim().length() > 0) {
            List<StoreProductEntity> storeProductEntities = (List<StoreProductEntity>)fc.getExternalContext().getSessionMap().get("storeProductEntities");
            
           
            
            for(StoreProductEntity spEntity:storeProductEntities)
            {
                System.out.println("converter: spEntity:" + spEntity.getStoreProductId());
                
                if(spEntity.getStoreProductId().equals(Long.valueOf(value)))
                {
                    
                    System.out.println("Converter: found spEntity: " + spEntity.getStoreProductId());
                   return spEntity;
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
            return String.valueOf(((StoreProductEntity) object).getStoreProductId());
        }
        else {
            return null;
        }
    }   
}
