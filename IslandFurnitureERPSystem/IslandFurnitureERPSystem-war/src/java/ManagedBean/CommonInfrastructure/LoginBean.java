/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import SessionBean.IFManagerBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "loginBean")
@SessionScoped

public class LoginBean implements Serializable {

    @EJB
    private IFManagerBeanLocal IFMB;

    private String userId;
    private String pwd;
    private String statusMsg;
    private String path;
    private Boolean Flag=false;
    
    private String fullName;
    
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

    public Boolean getLoggedIn() {
        return Flag;
    }

    public void setLoggedIn(Boolean Flag) {
        this.Flag = Flag;
    }

    public Boolean getFlag() {
        return Flag;
    }

    public void setFlag(Boolean Flag) {
        this.Flag = Flag;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    
    public void checkLogin(ActionEvent event) {
        
        System.out.println("LoginBean: checkLogin:()");
        
        String checkUserId = String.valueOf(userId);
        String checkPwd = String.valueOf(pwd);

        try {
            
            if (IFMB.checkAccount(checkUserId, checkPwd)) {
                Flag=true;
                statusMsg = "Login successfully...";
                path = "secured/WorkPlace.xhtml";
                fullName = IFMB.getFullName(userId);
                ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("isLogin", true);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserId", checkUserId);
                FacesContext.getCurrentInstance().getExternalContext().redirect(path);
                
            } else {
                statusMsg = "Incorrect userId or password, please enter again.";
                //path = "index";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login result " + statusMsg, ""));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
     public void performLogout(ActionEvent event) throws IOException{
        System.out.println("LogoutBean: performLogout:()");
        Flag=false;
        statusMsg="Logout successfully...";
        path="/loginPage.xhtml";
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("isLogin");
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+path);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout result " + statusMsg, ""));
     }
}
