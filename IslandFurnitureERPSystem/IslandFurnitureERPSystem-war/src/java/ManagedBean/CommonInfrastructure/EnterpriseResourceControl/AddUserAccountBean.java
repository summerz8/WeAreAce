/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.Factory.FactoryEntity;
import Entity.Store.StoreEntity;
import SessionBean.CommonInFrastructure.Factory_StoreManagementModuleLocal;
import SessionBean.CommonInFrastructure.InternalUserAccountManagementModuleLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author dan
 */
@Named(value = "newUser")
@ViewScoped
public class AddUserAccountBean implements Serializable {

    @EJB
    private InternalUserAccountManagementModuleLocal IUMA;
    @EJB
    private Factory_StoreManagementModuleLocal FSMM;

    private String userId;
    private String FirstName;
    private String MidName;
    private String LastName;
    private String Title;
    private String Gender;
    private String Position;
    private String Department;
    private String Email;
    private String Address;
    private String Postal;
    private String password;

    private String userLevel;
    private Calendar birthday;
    private String departmentId;

    private Date birDate;// used to convert birthday between string and calendar
    private String inputOldPass;
    private String newPass;

    private List<SelectItem> departmentList;
    private List<SelectItem> userLevelList;

    /**
     * Creates a new instance of UserInfoPageManageBean
     */
    public AddUserAccountBean() {
    }

    public InternalUserAccountManagementModuleLocal getIUMA() {
        return IUMA;
    }

    public void setIUMA(InternalUserAccountManagementModuleLocal IUMA) {
        this.IUMA = IUMA;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
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

    public String getInputOldPass() {
        return inputOldPass;
    }

    public void setInputOldPass(String inputOldPass) {
        this.inputOldPass = inputOldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public void AddNewUser(ActionEvent event) {
        System.out.println("UserInfoManageBean: save changes");

        birthday = Calendar.getInstance();
        birthday.setTime(birDate);
        System.out.println("UserInfoManageBean: birString to Date to Calendar:" + birthday.getTime().toString());

        IUMA.AddStaff(Department, Integer.valueOf(userLevel), LastName, MidName, FirstName, Position,
                birthday, Gender, Title, Address, Postal, Email, Long.valueOf(departmentId));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "New User Added Successfully!", ""));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("UserAccountControl.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AddUserAccountBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<SelectItem> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<SelectItem> departmentList) {
        this.departmentList = departmentList;
    }

    public List<SelectItem> getUserLevelList() {
        return userLevelList;
    }

    public void setUserLevelList(List<SelectItem> userLevelList) {
        this.userLevelList = userLevelList;
    }

    public void onDepartmentChange() {
        System.out.println("AddUserAccountBean: test1");
        departmentList = new ArrayList();
        userLevelList = new ArrayList();
        switch (Department) {
            case "H":
                departmentList.add(new SelectItem("1", "1 HQ"));
                userLevelList.add(new SelectItem("0", "0 HQ Manager"));
                userLevelList.add(new SelectItem("7", "7 System Admin"));
                break;
            case "F":
                List<FactoryEntity> factoryList = FSMM.ListFactory();
                for (FactoryEntity factory : factoryList) {
                    String t = factory.getFactoryId().toString() + " " + factory.getAddress();
                    departmentList.add(new SelectItem(factory.getFactoryId().toString(), t)); 
                    System.out.println("AddUserAccountBean " + t);
                }
                userLevelList.add(new SelectItem("1", "1 Factory Manager"));
                userLevelList.add(new SelectItem("3", "3 Factory SCM Staff"));
                userLevelList.add(new SelectItem("4", "4 Factory MRP Staff"));
                break;
            case "S":
                List<StoreEntity> storelist = FSMM.ListStore();
                for (StoreEntity store : storelist) {
                    departmentList.add(new SelectItem(store.getStoreId().toString(), 
                            store.getStoreId().toString() + " " + store.getAddress()));
                }
                userLevelList.add(new SelectItem("2", "2 Store Manager"));
                userLevelList.add(new SelectItem("5", "5 Store Kitchen Staff"));
                userLevelList.add(new SelectItem("6", "6 Store Market Staff"));
                break;
        }
    }

}
