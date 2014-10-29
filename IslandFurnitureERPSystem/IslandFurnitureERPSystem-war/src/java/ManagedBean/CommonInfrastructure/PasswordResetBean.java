/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure;

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
    
    private String email;
    private String userId;
    
    /**
     * Creates a new instance of PasswordResetBean
     */
    public PasswordResetBean() {
    }
    
    public void sendEmail(ActionEvent event){
        email = IFMB.getUserEmail(userId);
        if(email!=null){
            SendMailSSL se = new SendMailSSL();
            if(se.sendMessage(email)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Password-Reset Email Send Successfully!", ""));
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
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
