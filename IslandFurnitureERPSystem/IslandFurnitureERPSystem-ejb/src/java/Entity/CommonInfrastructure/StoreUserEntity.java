/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author dan
 */
@Entity
@Table(name="StoreUser")
public class StoreUserEntity extends UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public StoreUserEntity() {
    }
    public StoreUserEntity(String department, String idNumber, Integer userLevel, String lastName, String firstName, String position, String gender) {
        super(department,idNumber, userLevel,lastName,firstName, position,gender);
    }
    
}
