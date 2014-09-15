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
import java.util.Vector;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
                idNumber = idNum.getId_H().intValue() + 1;
                idNum.setId_H((long) idNumber);
                HQuser = new HQUserEntity(department, idNumber.toString(), userLevel,
                        lastName, midName, firstName, position, birthday, gender,
                        title, address, postalCode, email, true);
                em.persist(HQuser);
                System.out.println("User H" + idNumber.toString() + "created!");
                break;
            case 'F':
                idNumber = idNum.getId_F().intValue() + 1;
                idNum.setId_H((long) idNumber);
                Fuser = new FactoryUserEntity(department, idNumber.toString(), userLevel,
                        lastName, midName, firstName, position, birthday, gender,
                        title, address, postalCode, email, departmentId, true);
                em.persist(Fuser);
                System.out.println("User F" + idNumber.toString() + "created!");
                break;
            case 'S':
                idNumber = idNum.getId_S().intValue() + 1;
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
        Query query;
        switch (userId.charAt(0)) {
            case 'H':
                query = em.createQuery("SELECT h FROM HQUserEntity WHERE h.userId=userId");
                HQUserEntity HQUser = (HQUserEntity) query.getSingleResult();
                HQUser.editHQUserEntity(department, userLevel, lastName, midName,
                        firstName, position, birthday, gender, title, address, postalCode, email, Boolean.TRUE);
                em.persist(HQUser);
            case 'F':
                query = em.createQuery("SELECT f FROM FactoryUserEntity WHERE f.userId=userId");
                FactoryUserEntity FactoryUser = em.find(FactoryUserEntity.class, userId);
                FactoryUser.editFactoryUserEntity(department, userLevel, lastName, midName,
                        firstName, position, birthday, gender, title, address, postalCode, email, departmentId, Boolean.TRUE);
                em.persist(FactoryUser);
            case 'S':
                query = em.createQuery("SELECT s FROM StoreUserEntity WHERE s.userId=userId");
                StoreUserEntity StoreUser = em.find(StoreUserEntity.class, userId);
                StoreUser.editStoreUserEntity(department, userLevel, lastName, midName,
                        firstName, position, birthday, gender, title, address, postalCode, email, departmentId, Boolean.TRUE);
                em.persist(StoreUser);
        }
        em.flush();

    }
    
    //don't know how to implement this

    public List<ArrayList> ListUser() {
        System.out.println("InternalUserAccountModule: ListUser():");
        Query q = em.createQuery("SELECT t FROM UserEntity t");
        List requiredUserList = new ArrayList();
        for(Object o:q.getResultList()){
            UserEntity u = (UserEntity) o;
            List Userinfo = new ArrayList();
            Userinfo.add(0, u.getUserId());
            Userinfo.add(1, u.getTitle());
            Userinfo.add(2, u.getFirstName());
            Userinfo.add(3, u.getMidName());
            Userinfo.add(4, u.getLastName());
            Userinfo.add(5, u.getGender());
            Userinfo.add(6, u.getDepartment());
            Userinfo.add(7, u.getPosition());
            Userinfo.add(8, u.getEmail());
            requiredUserList.add(Userinfo);         
        }       
        return requiredUserList;
    }

//    public List<Vector> searchUser(String userId, String department, String lastName, 
//            String firstName, String position){
//        System.out.println("InternalUserAccountModule: searchUser():");
//        Query query;
//        if (department != null) {
//            query = em.createQuery("SELECT c FROM UserEntity WHERE c.department = department");
//            List<Vector> userList = new ArrayList();
//            for(Object o: query.getResultList()){
//            UserEntity u = (UserEntity) o;
//            Vector user = new Vector();
//            user.add(u.getUserId());
//            user.add(u.getFirstName());
//            user.add(u.getMidName());
//            user.add(u.getLastName());
//            user.add(u.getPosition());
//            user.add(u.)
//            }
//            
//        }
//        if (departmentId != null) {
//
//        }
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
//        return;
//
//    }

}
