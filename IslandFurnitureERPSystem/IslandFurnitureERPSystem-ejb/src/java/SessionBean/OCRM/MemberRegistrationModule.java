/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.MembershipLevel;
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
public class MemberRegistrationModule implements MemberRegistrationModuleLocal {

    @PersistenceContext
    private EntityManager em;

    private CryptographicHelper cryptographicHelper = CryptographicHelper.getInstanceOf();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public MemberRegistrationModule() {
    }

    @Override
    public void AddMember(String lastName, String midName,
            String firstName, Calendar birthday, String gender,
            String title, String address, String postalCode, String email) {
        //departmentID refers to the respective Factory, Store or HQ id
        System.out.println("MemberRegistrationModule: addMember():");

        MemberEntity member;

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

        String hashedpwd = cryptographicHelper.doMD5Hashing(PWD);

        member = new MemberEntity(PWD, lastName, midName, firstName,
                birthday, gender, title, address, postalCode,
                email, Boolean.FALSE); 
        member.setMemberlvl(em.find(MembershipLevel.class, 0L));
        em.persist(member);
        System.out.println("New Member created!");
        em.flush();
    }

    @Override
    public void DeleteMember(Long userId) {        
        System.out.println("MemberRegistrationModule: deletMember():" + userId);
        MemberEntity member = em.find(MemberEntity.class, userId);
        member.setDeleteFlag(Boolean.TRUE);
        em.persist(member);
        em.flush();

    }

    @Override
    public void ModifyMember(Long userId, String lastName, String midName,
            String firstName, Calendar birthday, String gender,
            String title, String address, String postalCode, String email) {

        System.out.println("MemberRegistrationModule: ModifyStaff():" + userId);
        //System.out.println("MemberRegistrationModule: ModifyStaff(): birthday" + birthday.getTime().toString());

        MemberEntity member = em.find(MemberEntity.class, userId);
        member.setAddress(address);
        member.setBirthday(birthday);
        member.setEmail(email);
        member.setFirstName(firstName);
        member.setGender(gender);
        member.setLastName(lastName);
        member.setMidName(midName);     
        member.setPostalCode(postalCode);       
       
        em.persist(member);
        em.flush();
        
        
    }

    //don't know how to implement this
    @Override
    public List<MemberEntity> ListMember() {
        System.out.println("InternalUserAccountModule: ListUser():");
        Query q = em.createQuery("SELECT t FROM MemberEntity t");
        List requiredUserList = new ArrayList();
        for (Object o : q.getResultList()) {
            MemberEntity u = (MemberEntity) o;
            if (!u.isDeleteFlag()) {
                requiredUserList.add(u);
            } else {
                System.out.println("deleted user: " + u.getMemberId());
            }
        }
        return requiredUserList;
    }

}
