/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.MemberEntity;
import ManagedBean.CommonInfrastructure.EnterpriseResourceControl.FactoryControlBean;
import SessionBean.OCRM.MemberRegistrationModuleLocal;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author dan
 */
@Named(value = "memberControl")
@ViewScoped
public class MemberControlBean {

    @EJB
    private MemberRegistrationModuleLocal MRMM;

    private List<MemberEntity> memberList;
    private List<MemberEntity> filteredMember;

    private String FirstName;
    private String MidName;
    private String LastName;
    private String Title;
    private String Gender;
    private String Nationality;
    private String Email;
    private String Address;
    private String Postal;

    private Calendar birthday;
    private String departmentId;

    private Date birDate;// used to convert birthday between string and calendar
    private String birString;
    private Long transactionId = -1L;

    /**
     * Creates a new instance of MemberControlBean
     */
    public MemberControlBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("MemberControlBean: init:");

        memberList = MRMM.ListMember();
        filteredMember = memberList;

    }

    public void onRowEdit(RowEditEvent event) {
        System.out.println("onRowEdit test:");
        MemberEntity entity = (MemberEntity) event.getObject();
        System.out.println("onRowEdit test: " + entity.getMemberId());

        MRMM.ModifyMember(entity.getMemberId(), entity.getLastName(), entity.getMidName(),
                entity.getFirstName(), entity.getBirthday(), entity.getGender(), entity.getTitle(),
                entity.getAddress(), entity.getPostalCode(), entity.getEmail());
        FacesMessage msg = new FacesMessage("Member Edited", String.valueOf(entity.getMemberId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((MemberEntity) event.getObject()).getMemberId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteMember(long id) {
        System.out.println("MemberControlBean: deleteMember: " + String.valueOf(id));
        MRMM.DeleteMember(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Member deleted successfully! ", ""));

        memberList = MRMM.ListMember();
        filteredMember = memberList;
    }

    public void addMember() {
        System.out.println("MemberControlBean: addMember: ");

        int result = MRMM.AddMember(LastName, MidName, FirstName, birthday, Nationality, Gender, Title, Address, Postal, Email, transactionId);
        if (result == 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Member added successfully! ", ""));

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("viewMember.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(FactoryControlBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (result == -1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Transaction does not exist! ", ""));
        } else if (result == -2) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Transactoin has already been rebated! ", ""));
        } else if (result == -3) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Transactoin is not created in today! ", ""));
        }

    }

//    public void changePassword(ActionEvent event) {
//        //System.out.println(FacesContext.getCurrentInstance().getAttributes().get("pwd"));
//        System.out.println("UserInfoManageBean: change password");
//        //System.out.println(FacesContext.getCurrentInstance().getMessages("messagesStatus"));
//        System.out.println("UserInfoManageBean: old password" + password + " and input password " + inputOldPass + " and new pass " + newPass);
//        if (cryptographicHelper.doMD5Hashing(inputOldPass).equals(password)) {
//            //System.out.println("\n\n\nIMPORTANT!!!: New password before hashing: "+ newPass +" Just for check!\n\n\n");
//            IUMA.changePass(newPass, userId);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
//                    "Password changed successfully!", ""));
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
//                    "Wrong Password, please enter again!", ""));
//        }
//
//        inputOldPass = null;
//        password = IUMA.getUser(userId).getPwd();
//
//    }
    public String BirString(Calendar bir) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        if (bir != null) {
            birString = format.format(bir.getTime());
        } else {
            birString = null;
        }
        System.out.println("UserControlBean: birstring:" + birString);
        return birString;
    }

    public MemberRegistrationModuleLocal getMRMM() {
        return MRMM;
    }

    public void setMRMM(MemberRegistrationModuleLocal MRMM) {
        this.MRMM = MRMM;
    }

    public List<MemberEntity> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberEntity> memberList) {
        this.memberList = memberList;
    }

    public List<MemberEntity> getFilteredMember() {
        return filteredMember;
    }

    public void setFilteredMember(List<MemberEntity> filteredMember) {
        this.filteredMember = filteredMember;
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

}
