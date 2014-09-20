/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure;

import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "workPlaceManageBean")
@Dependent
public class WorkPlaceManageBean {

    /**
     * Creates a new instance of WorkPlaceManageBean
     */
    
    private String currentUserId;
    private String currentUsserName;
    
    public WorkPlaceManageBean() {
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getCurrentUsserName() {
        return currentUsserName;
    }

    public void setCurrentUsserName(String currentUsserName) {
        this.currentUsserName = currentUsserName;
    }

    
    
    
    
    
    
    
    
}
