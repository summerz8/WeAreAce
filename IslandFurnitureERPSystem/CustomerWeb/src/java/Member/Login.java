package Member;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entity.Store.OCRM.MemberEntity;
import SessionBean.OCRM.CustomerWebMemberModuleLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.CryptographicHelper.CryptographicHelper;

/**
 *
 * @author apple
 */
@Named(value = "login")
@ViewScoped
public class Login {

    @EJB
    CustomerWebMemberModuleLocal cwml;

    CryptographicHelper cp = new CryptographicHelper();
    private String memberEmail;
    private String pwd;
    private MemberEntity member;
    private String web;

    public Login() {
    }

    public void checkLogin() throws IOException {
        member = cwml.memberLogin(memberEmail, cp.doMD5Hashing(pwd+memberEmail));
        if (member == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Failed, Please enter correct email or password.", ""));
        } else {
            web = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("FirstName", member.getFirstName());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Email", member.getEmail());

            if (web.equals("Singapore")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../secured/Singapore/MemberPage.xhtml");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../secured/China/MemberPage.xhtml");
            }

        }
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
