/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Store.OCRM;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dan
 */
@Entity
@Table(name = "member")
public class MemberEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    
    private String pwd; 
    private String lastName;
    private String midName;
    private String firstName;

    

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar birthday;
    private String gender;
    private String title;
    private String address;
    private String postalCode;
    private String email;
    
    private Boolean deleteFlag;
//    
//    @ManyToOne
//    private MembershipLevel memberlvl;

    public MemberEntity() {
    }

    public MemberEntity(String pwd, String lastName, String midName, String firstName, 
            Calendar birthday, String gender, String title, String address, String postalCode, 
            String email, Boolean deleteFlag) {
        this.pwd = pwd;
        this.lastName = lastName;
        this.midName = midName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.gender = gender;
        this.title = title;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
        this.deleteFlag = deleteFlag;
    }


    public Long getId() {
        return memberId;
    }

    public void setId(Long id) {
        this.memberId = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
//
//    public MembershipLevel getMemberlvl() {
//        return memberlvl;
//    }
//
//    public void setMemberlvl(MembershipLevel memberlvl) {
//        this.memberlvl = memberlvl;
//    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the memberId fields are not set
        if (!(object instanceof MemberEntity)) {
            return false;
        }
        MemberEntity other = (MemberEntity) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Store.OCRM.MemberEntity[ id=" + memberId + " ]";
    }
    
}
