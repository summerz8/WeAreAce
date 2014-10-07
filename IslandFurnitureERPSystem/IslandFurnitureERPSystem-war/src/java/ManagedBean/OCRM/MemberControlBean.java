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
    private String Email;
    private String Address;
    private String Postal;

    private Calendar birthday;
    private String departmentId;

    private Date birDate;// used to convert birthday between string and calendar
    
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

        MRMM.ModifyMember(entity.getId(), entity.getLastName(), entity.getMidName(), entity.getFirstName(), entity.getBirthday(), entity.getGender(), entity.getTitle(), entity.getAddress(), entity.getPostalCode(), entity.getEmail());
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
        
        MRMM.AddMember(LastName, MidName, FirstName, birthday, Gender, Title, Address, Postal, Email);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Member added successfully! ", ""));       
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewMember.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FactoryControlBean.class.getName()).log(Level.SEVERE, null, ex);
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
}
