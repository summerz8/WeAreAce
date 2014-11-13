/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Converter;

import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.IM.StoreBinRetailProductEntity;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author zhengyuan
 */
@FacesConverter("rproductBinConverter")
public class RproductConverter implements Converter{
     public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
     
        System.out.println("Retail Product Bin Converter converter: value:" + value);
     
        if(value != null && value.trim().length() > 0 && !value.equals("[Select One]")) {
            List<StoreBinRetailProductEntity> availBinEntities = (List<StoreBinRetailProductEntity>)fc.getExternalContext().getSessionMap().get("storeBinRetailProductEntities");
            
         //   System.err.println("Converter: " + availFactoryEntities.size());
            
            for(StoreBinRetailProductEntity sbpEntity:availBinEntities)
            {
             //   System.out.println("converter: factoryEntity:" + factoryEntity.getFactoryProductId());
                
                if(sbpEntity.getId().equals(Long.valueOf(value)))
                {
                    
               //     System.out.println("Converter: found factoryEntity: " + factoryEntity.getFactoryProductId());
                   return sbpEntity;
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
                return String.valueOf(((StoreBinRetailProductEntity) object).getId());
            }
        }
        else {
            return null;
        }
    }   
}
