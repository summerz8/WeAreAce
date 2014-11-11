/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;
//
//import SessionBean.OCRM.MemberRegistrationModuleLocal;

import SessionBean.OCRM.CustomerWebMemberModuleLocal;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "signUp")
@ViewScoped
public class SignUpBean {
//

    @EJB
    private CustomerWebMemberModuleLocal MRMM;

    private String FirstName;
    private String MidName;
    private String LastName;
    private String Title;
    private String Gender;
    private String Email;
    private String Address = "";
    private String Postal = "";
    private String password;
    private Calendar birthday;
    private String departmentId;
    private String country;

    private Date birDate;// used to convert birthday between string and calendar
    private String birString;
    private String userId;
    private String subject;
    private String text;
    private boolean checkBox;

    private Boolean isSuccess = true;

    public SignUpBean() {
    }

    public void addMember() throws IOException {
        System.out.println("MemberControlBean: addMember: ");
        String web = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");
        if (checkBox == true) {
            birthday=Calendar.getInstance();
            birthday.setTime(birDate);
            boolean check = MRMM.AddMemberWithPassword(LastName, MidName, FirstName, birthday, Gender, Title, Address, Postal, Email, password,country);
            if (check == true) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("FirstName", FirstName);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Email", Email);
                sendEmail();

                if (web.equals("Singapore")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("../secured/Singapore/MemberPage.xhtml");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("../secured/China/MemberPage.xhtml");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "This email address has been used", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "You did not agree with the terms and conditions", ""));
        }
    }

    public void sendEmail() {
        userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Email");

        SendMailSSLWeb se = new SendMailSSLWeb();

        if (!se.sendMessage(userId)) {
            isSuccess = false;
        }

        if (isSuccess) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Email Send Successfully!", ""));
        }

    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getMidName() {
        return MidName;
    }

    public void setMidName(String MidName) {
        this.MidName = MidName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPostal() {
        return Postal;
    }

    public void setPostal(String Postal) {
        this.Postal = Postal;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Date getBirDate() {
        return birDate;
    }

    public void setBirDate(Date birDate) {
        this.birDate = birDate;
    }

    public String getBirString() {
        return birString;
    }

    public void setBirString(String birString) {
        this.birString = birString;
    }
//
//    public MemberRegistrationModuleLocal getMRMM() {
//        return MRMM;
//    }
//
//    public void setMRMM(MemberRegistrationModuleLocal MRMM) {
//        this.MRMM = MRMM;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerWebMemberModuleLocal getMRMM() {
        return MRMM;
    }

    public void setMRMM(CustomerWebMemberModuleLocal MRMM) {
        this.MRMM = MRMM;
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

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
