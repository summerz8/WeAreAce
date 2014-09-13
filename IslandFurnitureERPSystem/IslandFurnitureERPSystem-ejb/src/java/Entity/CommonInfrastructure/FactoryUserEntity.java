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
@Table(name="FactoryUser")
public class FactoryUserEntity extends UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String factoryId; //factoryId is a string made up of one 'F' and 5 digit number eg.F000001
    
    
    public FactoryUserEntity() {
    }
    public FactoryUserEntity(String department, String idNumber, Integer userLevel, 
            String lastName, String firstName, String position, String gender, String departmentId) {
        super(department,idNumber, userLevel,lastName,firstName, position,gender);
        factoryId = departmentId;
        
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }
    
}
