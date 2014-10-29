/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import util.login.IFManagerBeanRemote;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import util.security.CryptographicHelper;

/**
 *
 * @author zhangshiyu
 */
@Named(value = "loginBean")
@SessionScoped

public class LoginBean implements Serializable {

    @EJB
    private IFManagerBeanRemote IFMB;

    private String userId;
    private String pwd;
    private String statusMsg;
    private String path;
    private Boolean Flag = false;
    private String department;
    private Long departmentId;
    private int userLevel;
    private String userRole;

    private String fullName;

    private CryptographicHelper cryptographicHelper = CryptographicHelper.getInstanceOf();

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    

    public void checkLogin(ActionEvent event) {

        System.out.println("LoginBean: checkLogin:()");

        String checkUserId = String.valueOf(userId);
        String checkPwd = String.valueOf(cryptographicHelper.doMD5Hashing(pwd+userId));

        try {
            int check = IFMB.checkAccount(checkUserId, checkPwd);
            System.out.println("LoginBean: check: "+ check);
            if (check == 1) {
                Flag = true;
                statusMsg = "Login successfully...";
                path = "secured/public/WorkPlace.xhtml";
                fullName = IFMB.getFullName(userId);

                department = IFMB.getDepartment(userId);
                departmentId = IFMB.getDepartmentId(userId);
                userLevel = IFMB.getUserLevel(userId);
                userRole = IFMB.getUserRole(userId);

                ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("isLogin", true);
                ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("Userlvl", userLevel);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserId", checkUserId);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("departmentId", departmentId);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("department", department);
                FacesContext.getCurrentInstance().getExternalContext().redirect(path);

            } else if (check == 0) {
                statusMsg = "Incorrect password, please enter again.";
                //path = "index";
            } else if (check == -1) {
                statusMsg = "User Not Found!";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login result " + statusMsg, ""));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void performLogout(ActionEvent event) throws IOException {
        System.out.println("LogoutBean: performLogout:()");
        Flag = false;
        statusMsg = "Logout successfully...";
        path = "/loginPage.xhtml";
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("isLogin");
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("UserId");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout result " + statusMsg, ""));
    }
}
