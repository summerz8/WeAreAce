/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.login;

import Entity.CommonInfrastructure.IdNumberEntity;
import Entity.CommonInfrastructure.UserEntity;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zhangshiyu
 */
@Stateless
@WebService
public class IFManagerBean implements IFManagerBeanRemote {

    @PersistenceContext
    private EntityManager em;

    public IFManagerBean() {
    }

//    @Override
//    @WebMethod(exclude = true)
//    public String createUser(String department, Integer userLevel, String lastName,
//            String firstName, String position, String gender, long departmentId, Calendar birthday) {
//        System.out.println("IFManagerBean: createUser():");
//        String msg = new String();
//        try {
//            Integer idNumber = 0;
//            UserEntity user;
//            IdNumberEntity idNum = em.find(IdNumberEntity.class, 0);
//
//            switch (department.charAt(0)) {
//                case 'H':
//                    idNumber = (int) idNum.getId_H() + 1;
//                    idNum.setId_H((long) idNumber);
//
//                    user = new HQUserEntity(department, idNumber.toString(), userLevel,
//                            lastName, null, firstName, position, birthday, gender, null, null, null, null, departmentId, "123", false);
//                    em.persist(user);
//                    msg = user.getUserId() + " " + user.getPwd();
//                    break;
//
//                case 'F':
//                    idNumber = (int) idNum.getId_F() + 1;
//                    idNum.setId_H((long) idNumber);
//                    user = new FactoryUserEntity(department, idNumber.toString(), userLevel,
//                            lastName, null, firstName, position, birthday, gender, null, null, null, null, departmentId, "123", false);
//                    em.persist(user);
//                    msg = user.getUserId() + " " + user.getPwd();
//                    break;
//                case 'S':
//                    idNumber = (int) idNum.getId_S() + 1;
//                    idNum.setId_H((long) idNumber);
//                    user = new StoreUserEntity(department, idNumber.toString(), userLevel,
//                            lastName, null, firstName, position, birthday, gender, null, null, null, null, departmentId, "123", false);
//                    em.persist(user);
//                    msg = user.getUserId() + " " + user.getPwd();
//                    break;
//            }
//            em.flush();
//
//            System.out.println("User created!");
//            return msg;
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        return "createUser() failed!";
//    }
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
    @WebMethod(exclude = true)
    public int checkAccount(String userId, String pwd) {

        UserEntity user;
        int check = 0;

        System.out.println("IFManagerBean: checkAccount()");
        //Query q = em.createQuery("SELECT t FROM UserEntity t WHERE t.userId=:userId");
        //q.setParameter("userId", userId);
        System.out.println("checkAccount:" + userId);
        user = em.find(UserEntity.class, userId);
        //user = (UserEntity)q.getResultList();
        //if the user exsit and password correct
        if (user == null) {
            System.out.println("User Not Found!");
            check = -1;
        } else if (user.isDeleteFlag()) {
            System.out.println("User is Deleted!");
            check = -1;//user not found
        } else if ((user.getPwd().equals(pwd))) {
            check = 1;
            System.out.println("User Found!");
        } else {
            System.out.println("User Found but password inccorect!");
            check = 0;
        }

        return check;
    }

    @WebMethod(operationName = "shopLogin")
    public int shopLogin(
            @WebParam(name = "id") String userId,
            @WebParam(name = "password") String pwd) {

        UserEntity user;
        int check = 0;
        String department = userId.substring(0, 1);

        System.out.println("IFManagerBean: shopLogin()");
        //Query q = em.createQuery("SELECT t FROM UserEntity t WHERE t.userId=:userId");
        //q.setParameter("userId", userId);
        System.out.println("login:" + userId);
        user = em.find(UserEntity.class, userId);
        //user = (UserEntity)q.getResultList();
        //if the user exsit and password correct
        if (user == null) {
            System.out.println("User Not Found!");
            check = -1;
        } else if (user.isDeleteFlag()) {
            System.out.println("User is Deleted!");
            check = -1;//user not found
        } else {
            if (!department.equals("S")) {
                check = -2;
                System.out.println("Only store staffs and managers are allowed to login");
            } else {
                if (user.getPwd().equals(pwd)) {
                    check = 1;
                    System.out.println("User Found!");
                } else {
                    System.out.println("User Found but password incorrect!");
                    check = 0;
                }
            }
        }

        return check;
    }

    @Override
    @WebMethod(operationName = "getFullNameById")
    public String getFullName(@WebParam(name = "userId") String userId) {
        String fullName = null;
        if (true) {
            UserEntity user = em.find(UserEntity.class, userId);
            fullName = user.getFirstName() + " " + user.getLastName() + " ";
        }
        return fullName;
    }

    @Override
    @WebMethod(exclude = true)
    public String getDepartment(String userId) {
        String department = null;
        if (true) {
            UserEntity user = em.find(UserEntity.class, userId);
            department = user.getDepartment();
        }
        return department;
    }

    @Override
    @WebMethod(exclude = true)
    public Long getDepartmentId(String userId) {
        Long departmentId = null;
        if (true) {
            UserEntity user = em.find(UserEntity.class, userId);
            departmentId = user.getDepartmentId();
        }
        return departmentId;
    }

    @Override
    @WebMethod(exclude = true)
    public int getUserLevel(String userId) {
        int userLevel;
        if (true) {
            UserEntity user = em.find(UserEntity.class, userId);
            userLevel = user.getUserLevel();
        }
        return userLevel;
    }
    
    @Override
    @WebMethod(exclude = true)
    public String getUserRole(String userId) {
        String userRole=" ";
        if (true) {
            UserEntity user = em.find(UserEntity.class, userId);
            int userLevel = user.getUserLevel();
            if(userLevel == 0) userRole = "HQ Manager";
            else if(userLevel == 1) userRole = "Factory Manager";
            else if(userLevel == 2) userRole = "Store Manager";
            else if(userLevel == 3) userRole = "Factory SCM Staff";
            else if(userLevel == 4) userRole = "Factory MRP Staff";
            else if(userLevel == 5) userRole = "Store Kitchen Staff";
            else if(userLevel == 6) userRole = "Store Staff";
            else if(userLevel == 7) userRole = "System Admin";

        }
        return userRole;
    }

    @Override
    @WebMethod(exclude = true)
    public void setUpIdNumber() {
        IdNumberEntity id = new IdNumberEntity();
        id.create();
        System.out.println("IdNumber initialized");
    }

    @WebMethod(operationName = "getUserInfo")
    @Override
    public String getUserInfo(@WebParam(name = "userId") String userId) {
        UserEntity ue = em.find(UserEntity.class, userId);
        System.out.println("getUserInfo: "+userId);
        return ue.getFirstName()+" "+ue.getLastName() + "&" + ue.getEmail();
    }
     
    @Override
    public String getUserEmail(String userId){
        UserEntity ue = em.find(UserEntity.class, userId);
        System.out.println("getUserEmail: "+userId);
        return ue.getEmail();
    }
}
