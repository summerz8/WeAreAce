/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import SessionBean.IFManagerBeanRemote;
import com.sun.xml.ws.runtime.dev.Session;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
//    private Session Flag;

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

//    public void setLoginFlag(String loginFlag) {
//        this.loginFlag = loginFlag;
//    }
//    public String getLoginFlag() {
//        return loginFlag;
//    }

//    public Session getFlag() {
//        return Flag;
//    }
//
//    public void setFlag(Session Flag) {
//        this.Flag = Flag;
//    }
    
    public void checkLogin(ActionEvent event) {
        
        System.out.println("LoginBean: checkLogin:()");
        
        String checkUserId = String.valueOf(userId);
        String checkPwd = String.valueOf(pwd);

        try {
            if (IFMB.checkAccount(checkUserId, checkPwd)) {
                statusMsg = "Login successfully...";
                path = "/WorkPlace";
                HttpServletRequest request= (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
                request.getSession().setAttribute("flag", (Object) "userlogin");
                //System.out.println(FacesContext.getCurrentInstance().getMessages().toString());
                
            } else {
                statusMsg = "Incorrect userId or password, please enter again.";
                System.out.println("haah");
                //path = "index";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login result " + statusMsg, ""));
            
            //System.out.println();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void checkPermissions(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)(fc.getExternalContext().getSession(true)); 
        
      
            String cid = (String) httpSession.getAttribute("flag");
        
        
        //System.out.println(fc.getMessages().toString());
        
        if( cid == null){
            ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();
            handler.performNavigation("index");
            //System.out.println(fc.getMessages().toString());
            //System.out.println("LoginBean: "+ fc.getExternalContext().getRequestContextPath());
            return;
        
        }
    }
}
