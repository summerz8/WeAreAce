/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import Entity.CommonInfrastructure.InternalMessageEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String userId;
    private String pwd;
    private String department;
    private Integer userLevel;
    private String lastName;
    private String midName;
    private String firstName;
    private String position;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;
    private String gender;
    private String title;
    private String address;
    private String postalCode;
    private String email;
    
    @ManyToMany(cascade={CascadeType.PERSIST})
    @JoinTable(name = "USER_INTERNALMESSAGE")
    private Set<InternalMessageEntity> inMessages = new HashSet<InternalMessageEntity>();

    public UserEntity(String department, String idNumber, Integer userLevel, String lastName, String firstName, String position, String gender) {
        this.setUserId(department + idNumber);
        String PWD;
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   
        Random random = new Random();   
        StringBuffer sb = new StringBuffer();   
        for (int i = 0; i < 8; i++) {   
            int number = random.nextInt(base.length());   
            sb.append(base.charAt(number));   
        }
        
        PWD = sb.toString();
        setPwd(PWD);
        
        this.userLevel = userLevel;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.gender = gender;
    }

    public UserEntity() {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public Set<InternalMessageEntity> getInMessages() {
        return inMessages;
    }

    public void setInMessages(Set<InternalMessageEntity> inMessages) {
        this.inMessages = inMessages;
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
