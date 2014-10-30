/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import SessionBean.CommonInFrastructure.InternalUserAccountManagementModuleLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.email.SendMailSSL;
import util.login.IFManagerBeanRemote;

/**
 *
 * @author dan
 */
@Named(value = "passwordReset")
@ViewScoped
public class PasswordResetBean {

    @EJB
    private IFManagerBeanRemote IFMB;
    @EJB
    private InternalUserAccountManagementModuleLocal IUAM;

    private String email;
    private String userId;

    /**
     * Creates a new instance of PasswordResetBean
     */
    public PasswordResetBean() {
    }

    public void sendEmail(ActionEvent event) {
        Integer result = IFMB.validateUser(userId, email);
        if (result == 1) {
            String newPass = IUAM.resetPass(userId);
            if (!newPass.equals("error")) {
                SendMailSSL se = new SendMailSSL();

                if (se.sendPasswordResetMessage(email, newPass)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Password-Reset Email Send Successfully!", ""));
                }
            }
        } else if (result == -1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Fail!", "User doesn't exist!"));
        } else if (result == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Fail!", "Email doen't match!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Fail!", ""));
        }
    }

    public IFManagerBeanRemote getIFMB() {
        return IFMB;
    }

    public void setIFMB(IFManagerBeanRemote IFMB) {
        this.IFMB = IFMB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
