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
import Entity.CommonInfrastructure.UserEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.security.CryptographicHelper;

/**
 *
 * @author dan
 */
@Stateless
public class InternalUserAccountManagementModule implements InternalUserAccountManagementModuleLocal {

    @PersistenceContext
    private EntityManager em;

    private CryptographicHelper cryptographicHelper = CryptographicHelper.getInstanceOf();

    public InternalUserAccountManagementModule() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void AddStaff(String department, Integer userLevel, String lastName, String midName,
            String firstName, String position, Calendar birthday, String gender,
            String title, String address, String postalCode, String email, long departmentId) {
        //departmentID refers to the respective Factory, Store or HQ id
        System.out.println("InternalUserAccountModule: addStaff():");

        Integer idNumber;
        FactoryUserEntity Fuser;
        StoreUserEntity Suser;
        HQUserEntity HQuser;

        String PWD;
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        PWD = sb.toString();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("IMPORTANT!!!: password before hashing: " + PWD + " Please remember this!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
       
        String hashedpwd;
        IdNumberEntity idNum = em.find(IdNumberEntity.class, 0);

        switch (department.charAt(0)) {
            case 'H':
                idNumber = (int) idNum.getId_H() + 1;
                idNum.setId_H((long) idNumber);
                hashedpwd = cryptographicHelper.doMD5Hashing(PWD+"H"+idNumber.toString());
                HQuser = new HQUserEntity(department, idNumber.toString(), userLevel,
                        lastName, midName, firstName, position, birthday, gender,
                        title, address, postalCode, email, 1L, hashedpwd, false);
                em.persist(HQuser);
                System.out.println("User H" + idNumber.toString() + "created!");
                break;
            case 'F':
                idNumber = (int) idNum.getId_F() + 1;
                idNum.setId_F((long) idNumber);
                hashedpwd = cryptographicHelper.doMD5Hashing(PWD+"F"+idNumber.toString());
                Fuser = new FactoryUserEntity(department, idNumber.toString(), userLevel,
                        lastName, midName, firstName, position, birthday, gender,
                        title, address, postalCode, email, departmentId, hashedpwd, false);
                em.persist(Fuser);
                System.out.println("User F" + idNumber.toString() + "created!");
                break;
            case 'S':
                idNumber = (int) idNum.getId_S() + 1;
                idNum.setId_S((long) idNumber);
                hashedpwd = cryptographicHelper.doMD5Hashing(PWD+"S"+idNumber.toString());
                Suser = new StoreUserEntity(department, idNumber.toString(), userLevel,
                        lastName, midName, firstName, position, birthday, gender,
                        title, address, postalCode, email, departmentId, hashedpwd, false);
                em.persist(Suser);
                System.out.println("User S" + idNumber.toString() + "created!");
                break;
        }

        em.flush();
    }

    @Override
    public void DeleteStaff(String userId) {
        String id = userId;
        System.out.println("InternalUserAccountModule: deletStaff():" + userId);
        UserEntity user = em.find(UserEntity.class, userId);
        user.setDeleteFlag(Boolean.TRUE);
        em.persist(user);
        em.flush();

    }

    @Override
    public void ModifyStaff(String userId, String department, Integer userLevel, String lastName, String midName,
            String firstName, String position, Calendar birthday, String gender,
            String title, String address, String postalCode, String email, long departmentId) {

        System.out.println("InternalUserAccountModule: ModifyStaff():" + userId);
        System.out.println("InternalUserAccountModule: ModifyStaff(): birthday" + birthday.getTime().toString());
        Query query;
        switch (userId.charAt(0)) {
            case 'H':
                //query = em.createQuery("SELECT h FROM HQUserEntity WHERE h.userId=:userId");\
                System.out.println("IUAM: modify HQ User");
                HQUserEntity HQUser = em.find(HQUserEntity.class, userId);
                HQUser.editHQUserEntity(department, userLevel, lastName, midName,
                        firstName, position, birthday, gender, title, address, postalCode, email, Boolean.FALSE, 1L);
                System.out.println("IUAM: ModifyStaff: HQUser: birthday " + HQUser.getBirthday().getTime().toString());
                em.persist(HQUser);
                em.flush();
                em.refresh(HQUser);

                System.out.println("IUAM: ModifyStaff: HQUser: birthday " + HQUser.getBirthday().getTime().toString());
                break;
            case 'F':
                //query = em.createQuery("SELECT f FROM FactoryUserEntity WHERE f.userId=:userId");
                FactoryUserEntity FactoryUser = em.find(FactoryUserEntity.class, userId);
                FactoryUser.editFactoryUserEntity(department, userLevel, lastName, midName,
                        firstName, position, birthday, gender, title, address, postalCode, email, departmentId, Boolean.FALSE);

                em.persist(FactoryUser);
                em.flush();
                break;
            case 'S':
                //query = em.createQuery("SELECT s FROM StoreUserEntity WHERE s.userId=userId");
                StoreUserEntity StoreUser = em.find(StoreUserEntity.class, userId);
                StoreUser.editStoreUserEntity(department, userLevel, lastName, midName,
                        firstName, position, birthday, gender, title, address, postalCode, email, departmentId, Boolean.FALSE);
                em.persist(StoreUser);
                em.flush();
                break;
        }
        UserEntity user = em.find(UserEntity.class, userId);
        System.out.println("IUAM: ModifyStaff: User: birthday " + user.getBirthday().getTime().toString());

    }

    //don't know how to implement this
    @Override
    public List<UserEntity> ListUser() {
        System.out.println("InternalUserAccountModule: ListUser():");
        Query q = em.createQuery("SELECT t FROM UserEntity t");
        List requiredUserList = new ArrayList();
        for (Object o : q.getResultList()) {
            UserEntity u = (UserEntity) o;
            if (!u.isDeleteFlag()) {
                requiredUserList.add(u);
            } else {
                System.out.println("deleted user: " + u.getUserId());
            }
        }
        return requiredUserList;
    }

    @Override
    public List<UserEntity> ListFactoryUser(Long id) {
        System.out.println("InternalUserAccountModule: ListUser(): for factory " + id);
        Query q = em.createQuery("SELECT t FROM UserEntity t");
        List requiredUserList = new ArrayList();
        for (Object o : q.getResultList()) {
            UserEntity u = (UserEntity) o;
            if ((!u.isDeleteFlag()) && (u.getDepartment().equals("F") && u.getDepartmentId() == id)) {
                requiredUserList.add(u);
                System.out.println("added user: " + u.getUserId());
            } else {
                System.out.println("deleted user: " + u.getUserId());
            }
        }
        if (requiredUserList.isEmpty()) {
            System.out.println("factoryuserlist is null~~~");
        }
        return requiredUserList;
    }

    @Override
    public List<UserEntity> ListStoreUser(Long id) {
        System.out.println("InternalUserAccountModule: ListUser(): for store " + id);
        Query q = em.createQuery("SELECT t FROM UserEntity t");
        List requiredUserList = new ArrayList();
        for (Object o : q.getResultList()) {
            UserEntity u = (UserEntity) o;
            if ((!u.isDeleteFlag()) && (u.getDepartment().equals("S") && u.getDepartmentId() == id)) {
                requiredUserList.add(u);
                System.out.println("added user: " + u.getUserId());
            } else {
                System.out.println("deleted user: " + u.getUserId());
            }
        }
        if (requiredUserList.isEmpty()) {
            System.out.println("storeuserlist is null~~~");
        }
        return requiredUserList;
    }

    @Override
    public UserEntity getUser(String userId) {
        try {
            System.out.println("InternalUserAccountModule: listUserInfo(): userID " + userId);

            UserEntity user = new UserEntity();
            //boolean check = false;
            System.out.println("error 1");
            user = em.find(UserEntity.class, userId);
            System.out.println("error 2");
            //user = (UserEntity)q.getResultList();
            //if the user exsit 
            if (user == null) {
                System.out.println("IUMA:getUser(): User Not Found!");
            } else {
                System.out.println("IUMA:getUser(): User Found !");
            }
            return user;
        } catch (Exception e) {
            System.out.println("unexpected error");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void changePass(String newPass, String userId) {
        System.out.println("InternalUserAccountModule: change password: ");
        System.out.println("IMPORTANT!!!: IUAM: New password before hashing: " + newPass + " Just for check!");
        UserEntity user = em.find(UserEntity.class, userId);
        user.setPwd(newPass);
        em.persist(user);
        em.flush();

    }

    @Override
    public String resetPass(String userId) {
        System.out.println("InternalUserAccountModule: change password: ");
        String newPass;
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        newPass = sb.toString();
        System.out.println("IMPORTANT!!!: IUAM: New password before hashing: " + newPass + " Just for check!");
        UserEntity user = em.find(UserEntity.class, userId);
        if (user != null) {
            user.setPwd(cryptographicHelper.doMD5Hashing(newPass+userId));
            em.persist(user);
            em.flush();
            return newPass;
        } else return "error";
    }
//    
//    @Override
//    public List<UserEntity> ListCasher(){
//        System.out.println("InternalUserAccountModule: ListCasher(): for store ");
//        Query q = em.createQuery("SELECT t FROM UserEntity t");
//        List requiredUserList = new ArrayList();
//        for (Object o : q.getResultList()) {
//            UserEntity u = (UserEntity) o;
//            if ((!u.isDeleteFlag()) && (u.getDepartment().equals("S") && u.getUserLevel()==8)) {
//                requiredUserList.add(u);
//                System.out.println("added user: " + u.getUserId());
//            } else {
//                System.out.println("deleted user: " + u.getUserId());
//            }
//        }
//        if (requiredUserList.isEmpty()) {
//            System.out.println("storeuserlist is null~~~");
//        }
//        return requiredUserList;
//    };
////
////    @Override
////    public void resetCash(Double newCash, Double d, Long userId){
////        UserEntity ue = em.find(UserEntity.class, userId);
////        ue.setStartCash(newCash);
////        ue.setEndCash(d);
////    };
}
