/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.ACRM;

import Entity.Store.OCRM.MemberEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.email.SendMailSSL;

/**
 *
 * @author summer
 */
@Named(value = "sendCustomerEmail")
@ViewScoped
public class SendCustomerEmail implements Serializable{

    private Collection<MemberEntity> members;
    private String userId;
    private String subject;
    private String text;

    private Boolean isSuccess = true;

    public SendCustomerEmail() {
    }

    public void sendEmail(ActionEvent event) {
        members = (Collection<MemberEntity>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("customers");

        for (MemberEntity m : members) {
            userId = m.getEmail();
            SendMailSSL se = new SendMailSSL();

            if (!se.sendCustomerEmail(userId, subject, text)) {
                isSuccess = false;
            }
        }
        if (isSuccess) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Email Send Successfully!", ""));
        }

    }

    public Collection<MemberEntity> getMembers() {
        return members;
    }

    public void setMembers(Collection<MemberEntity> members) {
        this.members = members;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
