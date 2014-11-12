/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Converter;

import Entity.Factory.FactoryProductEntity;
import Entity.Store.IM.StoreBinProductEntity;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author zhengyuan
 */
@FacesConverter("productBinConverter")
public class ProductBinConverter implements Converter {
    
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
     
        System.out.println("Product Bin Converter converter: value:" + value);
     
        if(value != null && value.trim().length() > 0) {
            List<StoreBinProductEntity> availBinEntities = (List<StoreBinProductEntity>)fc.getExternalContext().getSessionMap().get("storeBinProductEntities");
            
            System.err.println("Converter: availBinEntitie:" + availBinEntities.size());
            
            for(StoreBinProductEntity sbpEntity:availBinEntities)
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
            return String.valueOf(((StoreBinProductEntity) object).getId());
        }
        else {
            return null;
        }
    }   
}
