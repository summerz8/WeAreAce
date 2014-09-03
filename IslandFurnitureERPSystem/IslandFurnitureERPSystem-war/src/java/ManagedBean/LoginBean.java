/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import SessionBean.IFManagerBeanRemote;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "loginBean")
@ManagedBean
@RequestScoped
public class LoginBean {

    @EJB
    private IFManagerBeanRemote IFMB;

    private String userId;
    private String pwd;
    private String statusMsg;
    private String path;

    public LoginBean() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
    public void checkLogin(ActionEvent event) {
        
        System.out.println("LoginBean: checkLogin:()");
        
        String checkUserId = String.valueOf(userId);
        String checkPwd = String.valueOf(pwd);

        try {
            if (IFMB.checkAccount(checkUserId, checkPwd)) {
                statusMsg = "Login successfully...";
                path = "/WorkPlace";
            } else {
                statusMsg = "Incorrect userId or password, please enter again.";
                //path = "index";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login result " + statusMsg, ""));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
