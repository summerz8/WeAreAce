/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure.EnterpriseResourceControl;

import Entity.CommonInfrastructure.UserEntity;
import SessionBean.CommonInFrastructure.InternalUserAccountManagementModuleLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import util.security.CryptographicHelper;

/**
 *
 * @author dan
 */
@Named(value = "userControl")
@ViewScoped
public class UserControlBean {

    @EJB
    private InternalUserAccountManagementModuleLocal IUMA;

    private List<UserEntity> listedUser;
    private List<UserEntity> filterdUser;
    private UserEntity selectedUser;

    private String outcome = "UserAccountDetail.xhtml";

    private String birString;
    private String deletedUserId;

    private CryptographicHelper cryptographicHelper = CryptographicHelper.getInstanceOf();
    /**
     * Creates a new instance of UserControlBean
     */

    public UserControlBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("UserControlBean: init:");

        listedUser = IUMA.ListUser();
        filterdUser = listedUser;

    }

    public void onRowEdit(RowEditEvent event) {
//        FacesContext context = FacesContext.getCurrentInstance();
        UserEntity entity = (UserEntity) event.getObject();
        System.out.println("onRowEdit test: " + entity.getUserId() + entity.getFirstName());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("UserControlBean:birString:" + birString);
        try {
            Date temp = df.parse(birString);

            System.out.println("UserControlBean: birString to Date:" + temp.toString());
            Calendar cal = Calendar.getInstance();
            cal.setTime(temp);
            System.out.println("UserContorlBean:birString to Date to Calendar:" + cal.getTime().toString());
            IUMA.ModifyStaff(entity.getUserId(), entity.getDepartment(), entity.getUserLevel(), entity.getLastName(), entity.getMidName(),
                    entity.getFirstName(), entity.getPosition(), cal, entity.getGender(), entity.getTitle(), entity.getAddress(),
                    entity.getPostalCode(), entity.getEmail(), entity.getDepartmentId());
//            IUMA.changePass(entity.getPwd(), entity.getUserId());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        FacesMessage msg = new FacesMessage("User Edited", ((UserEntity) event.getObject()).getUserId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        listedUser = IUMA.ListUser();
        filterdUser = listedUser;
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((UserEntity) event.getObject()).getUserId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteUser(String id) {
        System.out.println("UserControlBean: deleteUser: " + id);
        IUMA.DeleteStaff(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User deleted successfully! ", ""));
        listedUser = IUMA.ListUser();
        filterdUser = listedUser;

    }
    
    public String getDeletedUserId() {
        return deletedUserId;
    }

    public void setDeletedUserId(String deletedUserId) {
        this.deletedUserId = deletedUserId;
    }

    public List<UserEntity> getListedUser() {
        return listedUser;
    }

    public void setListedUser(List<UserEntity> listedUser) {
        this.listedUser = listedUser;
    }

    public List<UserEntity> getFilterdUser() {
        return filterdUser;
    }

    public void setFilterdUser(List<UserEntity> filterdUser) {
        this.filterdUser = filterdUser;
    }

    public UserEntity getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserEntity selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

//    public void save(String id) {
//        System.out.println(id);
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

    public String getBirString() {
        return birString;
    }

    public void setBirString(String birString) {
        this.birString = birString;
    }
   
}
