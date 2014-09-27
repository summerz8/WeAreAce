package org.primefaces.showcase.convert;
 
import Entity.CommonInfrastructure.UserEntity;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
 
 
@FacesConverter("userEntityConverter")
public class UserEntityConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            List<UserEntity> userEntities = (List<UserEntity>)fc.getExternalContext().getSessionMap().get("userEntities");
            
            System.err.println("Converter: " + userEntities.size());
            
            for(UserEntity userEntity:userEntities)
            {
                if(userEntity.getUserId().equals(value))
                {
                   return userEntity;
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
            return String.valueOf(((UserEntity) object).getUserId());
        }
        else {
            return null;
        }
    }   
}     