 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import util.security.CryptographicHelper;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String userId;
    private String pwd;
    private String department; //includes HQ, FSCM, FMRP, S
    private Integer userLevel;
    private String lastName;
    private String midName;
    private String firstName;
    private String position;
    private long departmentId;
    

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar birthday;
    private String gender;
    private String title;
    private String address;
    private String postalCode;
    private String email;
    
        
    private Boolean deleteFlag;//used to identify whether this user has been deleted  
                               
    //private CryptographicHelper cryptographicHelper = CryptographicHelper.getInstanceOf();
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "sender")
    private Collection<InternalMessageEntity> sendMessage;
    
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "receiver")
    private Collection<InternalMessageReceive> receiveMessage;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "systemUser")
    private Collection<TicketEntity> tickets;
    
    public UserEntity() {
    }

    
    public UserEntity(String department, String idNumber, Integer userLevel, String lastName, String midName,
            String firstName, String position,  Calendar birthday, String gender, 
            String title, String address, String postalCode, String email, Boolean deleteFlag, long departmentId, String PWD) {
        this.setUserId(department + idNumber);
//        String PWD;
//        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   
//        Random random = new Random();   
//        StringBuffer sb = new StringBuffer();   
//        for (int i = 0; i < 8; i++) {   
//            int number = random.nextInt(base.length());   
//            sb.append(base.charAt(number));   
//        }
//        
//        PWD = sb.toString();        
        //setPwd(cryptographicHelper.doMD5Hashing(PWD));
        
        this.userLevel = userLevel;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.gender = gender;
        this.address= address;
        this.birthday = birthday;
        this.department = department;
        this.email = email;
        this.midName = midName;
        this.postalCode = postalCode;
        this.title = title;
        this.deleteFlag = deleteFlag;
        this.departmentId = departmentId;
        this.pwd = PWD;
//        this.sendMessage = new ArrayList<InternalMessageEntity>();
//        this.receiveMessage = new ArrayList<InternalMessageReceive>();
//        this.tickets = new ArrayList<TicketEntity>();
        

    }

    public void editUserEntity(String department, Integer userLevel, String lastName, String midName,
            String firstName, String position,  Calendar birthday, String gender, 
            String title, String address, String postalCode, String email, Boolean deleteFlag, long departmentId) {
        
        this.userLevel = userLevel;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.gender = gender;
        this.address= address;
        this.birthday = birthday;
        //setBirthday(birthday);
        System.out.println("UserEntity: editUserEntity: birthday: "+ getBirthday().getTime().toString());
        this.department = department;
        this.email = email;
        this.midName = midName;
        this.postalCode = postalCode;
        this.title = title;
        this.deleteFlag = deleteFlag;
        this.departmentId = departmentId;
        
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

        

    public Collection<InternalMessageEntity> getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(Collection<InternalMessageEntity> sendMessage) {
        this.sendMessage = sendMessage;
    }

    public Collection<InternalMessageReceive> getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(Collection<InternalMessageReceive> receiveMessage) {
        this.receiveMessage = receiveMessage;
    }

    public Collection<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<TicketEntity> tickets) {
        this.tickets = tickets;
    }
    
   
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserEntity[ id=" + userId + " ]";
    }
    
}
