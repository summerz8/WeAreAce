/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "workPlaceManageBean")
@ViewScoped
public class WorkPlaceManageBean implements Serializable{

    /**
     * Creates a new instance of WorkPlaceManageBean
     */
    private String currentUserId;
    private int currentUserLevel;

    public WorkPlaceManageBean() {
    }

    @PostConstruct
    public void init() {
        currentUserLevel = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Userlvl");
    }

    public Boolean isValidMRP() {
        if (currentUserLevel == 0 || currentUserLevel == 1 || currentUserLevel == 4) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isValidSCM() {
        if (currentUserLevel == 0 || currentUserLevel == 1 || currentUserLevel == 3) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isValidHQ() {
        if (currentUserLevel == 0) {
            System.out.println("isValidHQ currentUserLevel == 0");
            return true;
        } else {
            return false;
        }
    }

    public Boolean isValidResource() {
        if (currentUserLevel == 0 || currentUserLevel == 1 || currentUserLevel == 2) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isValidFactoryManager() {
        if (currentUserLevel == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isValidFactorySCMOnly() {
        if (currentUserLevel == 1 || currentUserLevel == 3) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isValidStoreManager() {
        if (currentUserLevel == 2) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isValidKitchen() {
        if (currentUserLevel == 0 || currentUserLevel == 2 || currentUserLevel == 5) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean isValidKitchenOnly() {
        if (currentUserLevel == 2 || currentUserLevel == 5) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isValidCRM() {
        if (currentUserLevel == 0 ||currentUserLevel == 2 || currentUserLevel == 6) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isValidTicket() {
        if (currentUserLevel == 0 || currentUserLevel == 7) {
            return true;
        } else {
            return false;
        }
    }
    
    

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public int getCurrentUserLevel() {
        return currentUserLevel;
    }

    public void setCurrentUserLevel(int currentUserLevel) {
        this.currentUserLevel = currentUserLevel;
    }

}
