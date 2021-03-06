/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Converter;


import Entity.Factory.FactoryProductEntity;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
 

 
@FacesConverter("factoryConverter")
public class FactoryConverter implements Converter {
 
 public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
     
        System.out.println("converter: value:" + value);
     
        if(value != null && value.trim().length() > 0 && !value.equals("[Select One]")) {
            List<FactoryProductEntity> availFactoryEntities = (List<FactoryProductEntity>)fc.getExternalContext().getSessionMap().get("factoryProductEntities");
            
            System.err.println("Converter: " + availFactoryEntities.size());
            
            for(FactoryProductEntity factoryEntity:availFactoryEntities)
            {
                System.out.println("converter: factoryEntity:" + factoryEntity.getFactoryProductId());
                
                if(factoryEntity.getFactoryProductId().equals(Long.valueOf(value)))
                {
                    
                    System.out.println("Converter: found factoryEntity: " + factoryEntity.getFactoryProductId());
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
            
            if(object instanceof String)
            {
                return null;
            }
            else
            {
                return String.valueOf(((FactoryProductEntity) object).getFactoryProductId());
            }
        }
        else {
            return null;
        }
    }   
}         
    