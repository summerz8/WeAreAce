/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;
//
//import SessionBean.OCRM.MemberRegistrationModuleLocal;

import SessionBean.OCRM.CustomerWebMemberModuleLocal;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
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

    private Date birDate;// used to convert birthday between string and calendar
    private String birString;

    public SignUpBean() {
    }

    public String addMember() {
        System.out.println("MemberControlBean: addMember: ");
        MRMM.AddMemberWithPassword(LastName, MidName, FirstName, birthday, Gender, Title, Address, Postal, Email, password);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("FirstName", FirstName);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Email", Email);
        return "/secured/MemberPage?faces-redirect=true";
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

    
}
