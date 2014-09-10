/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.FactoryUserEntity;
import Entity.CommonInfrastructure.HQUserEntity;
import Entity.CommonInfrastructure.IdNumberEntity;
import Entity.CommonInfrastructure.StoreUserEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

/**
 *
 * @author dan
 */
@Stateful
public class InternalUserAccountManagementModule implements InternalUserAccountManagementModuleLocal {

    private EntityManager em;

    public InternalUserAccountManagementModule() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void AddStaff(String department, Integer userLevel, String lastName, String midName,
            String firstName, String position,  Calendar birthday, String gender, 
            String title, String address, String postalCode, String email, String departmentID) {
        //departmentID refers to the respective Factory, Store or HQ id
        System.out.println("EnterpriseManagementModule: addStaff():");

        Integer idNumber;
        FactoryUserEntity Fuser;
        StoreUserEntity Suser;
        HQUserEntity HQuser;

        IdNumberEntity idNum = em.find(IdNumberEntity.class, 0);

        switch (departmentID.charAt(0)) {
            case 'H':
                idNumber = idNum.getId_H().intValue() + 1;
                idNum.setId_H((long) idNumber);
                HQuser = new HQUserEntity(department, idNumber.toString(), userLevel,
                        lastName, firstName, position, gender);
                em.persist(HQuser);
                System.out.println("User H" + idNumber.toString() + "created!");
                break;
            case 'F':
                idNumber = idNum.getId_F().intValue() + 1;
                idNum.setId_H((long) idNumber);
                Fuser = new FactoryUserEntity(department, idNumber.toString(), userLevel,
                        lastName, firstName, position, gender, departmentID);
                em.persist(Fuser);
                System.out.println("User F" + idNumber.toString() + "created!");
                break;
            case 'S':
                idNumber = idNum.getId_S().intValue() + 1;
                idNum.setId_H((long) idNumber);
                Suser = new StoreUserEntity(department, idNumber.toString(), userLevel,
                        lastName, firstName, position, gender, departmentID);
                em.persist(Suser);
                System.out.println("User S" + idNumber.toString() + "created!");
                break;
        }

        em.flush();
    }

    @Override
    public void DeleteStaff() {
    }

    @Override
    public void ModifyStaff() {
    }

    public List<String> ListUser() {
        return null;
    }
    
    public void searchUser(){
    
    }

}
