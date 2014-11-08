/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import SessionBean.OCRM.CustomerWebMemberModuleLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "feedBackBean")
@ViewScoped
public class FeedBackBean {

    @EJB
    private CustomerWebMemberModuleLocal MRMM;

    private String name;
    private String title;
    private String email;
    private String content;

    public FeedBackBean() {
    }

    @PostConstruct
    public void init() {
        name = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("FirstName");
        if (name != null) {

            email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Email");

        }

    }

    public String CreateFeedBack() {

        MRMM.createFeedBack( title,  content,  email, name);

            return "ThanksForFeedBack?faces-redirect=true";

    }

    public CustomerWebMemberModuleLocal getMRMM() {
        return MRMM;
    }

    public void setMRMM(CustomerWebMemberModuleLocal MRMM) {
        this.MRMM = MRMM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
