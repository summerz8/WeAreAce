/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.CommonInfrastructure.FactoryUserEntity;
import Entity.CommonInfrastructure.HQUserEntity;
import Entity.CommonInfrastructure.IdNumberEntity;
import Entity.CommonInfrastructure.StoreUserEntity;
import Entity.CommonInfrastructure.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zhangshiyu
 */
@Stateless
public class IFManagerBean implements IFManagerBeanLocal{

    @PersistenceContext
    private EntityManager em;

    public IFManagerBean() {
    }

    @Override
    public String createUser(String department, Integer userLevel, String lastName,
            String firstName, String position, String gender, long departmentId) {
        System.out.println("IFManagerBean: createUser():");
        String msg = new String();
        try {
            Integer idNumber = 0;
            UserEntity user;
            IdNumberEntity idNum = em.find(IdNumberEntity.class, 0);

            switch (department.charAt(0)) {
                case 'H':
                    idNumber = (int)idNum.getId_H() + 1;
                    idNum.setId_H((long) idNumber);
                    user = new HQUserEntity(department, idNumber.toString(), userLevel,
                            lastName, null, firstName, position, null, gender, null, null, null, null, true, departmentId);
                    em.persist(user);
                    msg = user.getUserId() + " " + user.getPwd();
                    break;

                case 'F':
                    idNumber = (int)idNum.getId_F() + 1;
                    idNum.setId_H((long) idNumber);
                    user = new FactoryUserEntity(department, idNumber.toString(), userLevel,
                            lastName, null, firstName, position, null, gender, null, null, null, null, departmentId, true);
                    em.persist(user);
                    msg = user.getUserId() + " " + user.getPwd();
                    break;
                case 'S':
                    idNumber = (int)idNum.getId_S() + 1;
                    idNum.setId_H((long) idNumber);
                    user = new StoreUserEntity(department, idNumber.toString(), userLevel,
                            lastName, null, firstName, position, null, gender, null, null, null, null, departmentId, true);
                    em.persist(user);
                    msg = user.getUserId() + " " + user.getPwd();
                    break;
            }
            em.flush();

            System.out.println("User created!");
            return msg;

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
        //Query q = em.createQuery("SELECT t FROM UserEntity t WHERE t.userId=:userId");
        //q.setParameter("userId", userId);
        user = em.find(UserEntity.class, userId);
        //user = (UserEntity)q.getResultList();
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

    @Override
    public String getFullName(String userId) {
        String fullName = null;
        if (true) {
            UserEntity user = em.find(UserEntity.class, userId);
            fullName = user.getFirstName() + " " + user.getLastName() + " ";
        }
        return fullName;
    }

    @Override
    public void setUpIdNumber() {
        IdNumberEntity id = new IdNumberEntity();
        id.create();
        System.out.println("IdNumber initialized");
    }

}
