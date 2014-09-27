/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author dan
 */
@ManagedBean(name = "idleBean")

public class IdleMonitorBean {

    public void welcomeListener() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome Back",
                        "Continue your works."));

    }

    public void logoutListener() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "You Have Logged Out!",
                        "Thank you for using Island Furniture Internal Portal!"));

        // invalidate session, and redirect to other pages
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        try {
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/loginPage.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(IdleMonitorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
