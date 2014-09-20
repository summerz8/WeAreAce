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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dan
 */
@Stateless
public class InternalUserAccountManagementModule implements InternalUserAccountManagementModuleLocal {

    @PersistenceContext
    private EntityManager em;

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

        IdNumberEntity idNum = em.find(IdNumberEntity.class, 0);

        switch (department.charAt(0)) {
            case 'H':
                idNumber = (int) idNum.getId_H() + 1;
                idNum.setId_H((long) idNumber);
                HQuser = new HQUserEntity(department, idNumber.toString(), userLevel,
                        lastName, midName, firstName, position, birthday, gender,
                        title, address, postalCode, email, true, 1000001);
                em.persist(HQuser);
                System.out.println("User H" + idNumber.toString() + "created!");
                break;
            case 'F':
                idNumber = (int) idNum.getId_F() + 1;
                idNum.setId_H((long) idNumber);
                Fuser = new FactoryUserEntity(department, idNumber.toString(), userLevel,
                        lastName, midName, firstName, position, birthday, gender,
                        title, address, postalCode, email, departmentId, true);
                em.persist(Fuser);
                System.out.println("User F" + idNumber.toString() + "created!");
                break;
            case 'S':
                idNumber = (int) idNum.getId_S() + 1;
                idNum.setId_H((long) idNumber);
                Suser = new StoreUserEntity(department, idNumber.toString(), userLevel,
                        lastName, midName, firstName, position, birthday, gender,
                        title, address, postalCode, email, departmentId, true);
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
        Query query;
        switch (userId.charAt(0)) {
            case 'H':
                query = em.createQuery("SELECT h FROM HQUserEntity WHERE h.userId=userId");
                HQUserEntity HQUser = (HQUserEntity) query.getSingleResult();
                HQUser.setDeleteFlag(false);
                em.persist(HQUser);
            case 'F':
                query = em.createQuery("SELECT f FROM FactoryUserEntity WHERE f.userId=userId");
                FactoryUserEntity FactoryUser = em.find(FactoryUserEntity.class, userId);
                FactoryUser.setDeleteFlag(false);
                em.persist(FactoryUser);
            case 'S':
                query = em.createQuery("SELECT s FROM StoreUserEntity WHERE s.userId=userId");
                StoreUserEntity StoreUser = em.find(StoreUserEntity.class, userId);
                StoreUser.setDeleteFlag(false);
                em.persist(StoreUser);
        }
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
                        firstName, position, birthday, gender, title, address, postalCode, email, Boolean.TRUE, 1000001);
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
                        firstName, position, birthday, gender, title, address, postalCode, email, departmentId, Boolean.TRUE);

                em.persist(FactoryUser);
                em.flush();
                break;
            case 'S':
                //query = em.createQuery("SELECT s FROM StoreUserEntity WHERE s.userId=userId");
                StoreUserEntity StoreUser = em.find(StoreUserEntity.class, userId);
                StoreUser.editStoreUserEntity(department, userLevel, lastName, midName,
                        firstName, position, birthday, gender, title, address, postalCode, email, departmentId, Boolean.TRUE);
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
            requiredUserList.add(u);
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
        UserEntity user = em.find(UserEntity.class, userId);
        user.setPwd(newPass);
        em.persist(user);
        em.flush();

    }
//    public List<UserEntity> searchUser(String userId, String department, Long departmentId, String lastName, 
//            String firstName, String position){
//        System.out.println("InternalUserAccountModule: searchUser():");
//        Query query;
//        if (department != null) {
//            query = em.createQuery("SELECT c FROM UserEntity c WHERE c.department = department");
//            List<UserEntity> userList = new ArrayList();
//            for(Object o: query.getResultList()){
//            UserEntity u = (UserEntity) o;
//            userList.add(0,u);
//            }
//            
//        }
//        
//        if (userId != null) {
//            switch (userId.charAt(0)) {
//                case 'H':
//                    HQUserEntity HQUser = em.find(HQUserEntity.class, userId);
//                    HQUser.setDeleteFlag(false);
//                    em.persist(HQUser);
//                case 'F':
//                    FactoryUserEntity FactoryUser = em.find(FactoryUserEntity.class, userId);
//                    FactoryUser.setDeleteFlag(false);
//                    em.persist(FactoryUser);
//                case 'S':
//                    StoreUserEntity StoreUser = em.find(StoreUserEntity.class, userId);
//                    StoreUser.setDeleteFlag(false);
//                    em.persist(StoreUser);
//            }
//        }
//        
//        return;
//
//    }
}
