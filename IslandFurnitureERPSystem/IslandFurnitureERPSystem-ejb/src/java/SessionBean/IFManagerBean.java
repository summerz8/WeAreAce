/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.CommonInfrastructure.IdNumberEntity;
import Entity.CommonInfrastructure.UserEntity;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zhangshiyu
 */
@Stateful
public class IFManagerBean implements IFManagerBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public IFManagerBean() {
    }

    @Override
    public String createUser(String department, Integer userLevel, String lastName, String firstName, String position, String gender) {
        System.out.println("IFManagerBean: createUser():");

        Integer idNumber = 0;
        UserEntity user;
        IdNumberEntity idNum = em.find(IdNumberEntity.class, 0);

        switch (department) {
            case "H":
                idNumber = idNum.getId_H().intValue() + 1;
                idNum.setId_H((long) idNumber);
                break;
            case "F":
                idNumber = idNum.getId_F().intValue() + 1;
                idNum.setId_H((long) idNumber);
                break;
            case "S":
                idNumber = idNum.getId_S().intValue() + 1;
                idNum.setId_H((long) idNumber);
                break;
        }
        em.flush();

        try {
            user = new UserEntity(department, idNumber.toString(), userLevel, lastName, firstName, position, gender);
            em.persist(user);
            System.out.println("User created!");
            return user.getUserId()+ " " +user.getPwd();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "createUser() failed!";
    }
//
//    @Override
//    public String getUserId(String userId) {
//        System.out.println("IFManagerBean: getUserId()");
//        UserEntity user;
//        try {                                                                                                                                      
//            user = em.find(UserEntity.class, userId);
//            return user.getUserId();
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        return "getUserId() failed!";
//    }

    @Override
    public boolean checkAccount(String userId, String pwd) {
        UserEntity user;
        boolean check = false;

        System.out.println("IFManagerBean: checkAccount()");
        user = em.find(UserEntity.class, userId);

        //if the user exsit and password correct
        if (user == null) {
            System.out.println("User Not Found!");
        } else if ((user.getPwd().equals(pwd))) {
            check = true;
            System.out.println("User Found!");
        } else {
            System.out.println("User Found but password inccorect!");
        }

        return check;
    }

}
