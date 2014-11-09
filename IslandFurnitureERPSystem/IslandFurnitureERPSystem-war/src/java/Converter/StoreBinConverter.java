/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Converter;

import Entity.Store.IM.StoreWarehouseBinEntity;
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

@FacesConverter("sBinConverter")
public class StoreBinConverter implements Converter {
     public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
     
        System.out.println("converter: value:" + value);
     
        if(value != null && value.trim().length() > 0) {
            List<StoreWarehouseBinEntity> binEntities = (List<StoreWarehouseBinEntity>)fc.getExternalContext().getSessionMap().get("sBinEntities");
            
           
            
            for(StoreWarehouseBinEntity spEntity:binEntities)
            {
                
                
                if(spEntity.getId().equals(Long.valueOf(value)))
                {
                    
                   
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
            return String.valueOf(((StoreWarehouseBinEntity) object).getId());
        }
        else {
            return null;
        }
    }   
}
