/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author dan
 */
@Entity
@Table(name="HeadQuarterUser")

public class HQUserEntity extends UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String HQId = "HQ0001"; //this is a final string because we only hava one HQ

    public HQUserEntity() {
    }

    
    public HQUserEntity(String department, String idNumber, Integer userLevel, String lastName, String midName,
            String firstName, String position,  Calendar birthday, String gender, 
            String title, String address, String postalCode, String email, Boolean deleteFlag) {
        super(department,idNumber, userLevel,lastName,midName, firstName, position, 
                birthday,gender,title, address, postalCode, email, deleteFlag);
    }

    public void editHQUserEntity(String department, Integer userLevel, String lastName, String midName,
            String firstName, String position,  Calendar birthday, String gender, 
            String title, String address, String postalCode, String email, Boolean deleteFlag) {
        
        super.editUserEntity(department, userLevel, lastName, midName, firstName, 
                position, birthday, gender, title, address, postalCode, email, deleteFlag);       
    }
    public String getHQId() {
        return HQId;
    }
       
   
}
