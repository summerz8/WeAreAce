/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.OCRM.MemberCardIdMappingEntity;
import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.MembershipLevelEntity;
import Entity.Store.OCRM.TransactionEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.security.CryptographicHelper;

/**
 *
 * @author dan
 */
@Stateless
@WebService
public class MemberRegistrationModule implements MemberRegistrationModuleLocal {

    @PersistenceContext
    private EntityManager em;

    private CryptographicHelper cryptographicHelper = CryptographicHelper.getInstanceOf();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public MemberRegistrationModule() {
    }

    @Override
    @WebMethod(exclude = true)
    public int AddMember(String lastName, String midName,
            String firstName, Calendar birthday, String gender,
            String title, String address, String postalCode, String email, Long transactionId) {
        //departmentID refers to the respective Factory, Store or HQ id
        System.out.println("MemberRegistrationModule: addMember():");

        int check = CheckFirstTransaction(transactionId);
        int check2 = checkEmail(email);
        if (check == 1 && check2==1) {

            TransactionEntity transaction = em.find(TransactionEntity.class, transactionId);
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

            member = new MemberEntity(hashedpwd, lastName, midName, firstName,
                    birthday, gender, title, address, postalCode,
                    email, Boolean.FALSE);
            member.setMemberlvl(em.find(MembershipLevelEntity.class, upgradeMember(transaction.getTotalPrice())));
            em.persist(member);
            System.out.println("New Member created!");
            em.flush();
            transaction.setMember(member);
            em.persist(transaction);
            em.flush();
        }else if(check2!=1){
            System.out.println("email incorrect! existed!");
         return check2;
        }        
        return check;
    }

    @Override
    @WebMethod(exclude = true)
    public void DeleteMember(Long userId) {
        System.out.println("MemberRegistrationModule: deletMember():" + userId);
        MemberEntity member = em.find(MemberEntity.class, userId);
        member.setDeleteFlag(Boolean.TRUE);
        em.persist(member);
        em.flush();

    }

    @Override
    @WebMethod(exclude = true)
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
    @WebMethod(exclude = true)
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

    @WebMethod(operationName = "checkMember")
    public Boolean checkMember(
            @WebParam(name = "memberId") Long memberId) {
        MemberEntity member = em.find(MemberEntity.class, memberId);

        return member != null;
    }

    @Override
    @WebMethod(exclude = true)
    public void AddMemberWithPassword(String lastName, String midName,
            String firstName, Calendar birthday, String gender,
            String title, String address, String postalCode, String email, String PWD) {
        System.out.println("MemberRegistrationModule: addMember():");

        MemberEntity member;

        String hashedpwd = cryptographicHelper.doMD5Hashing(PWD);

        member = new MemberEntity(hashedpwd, lastName, midName, firstName,
                birthday, gender, title, address, postalCode,
                email, Boolean.FALSE);

        member.setMemberlvl(em.find(MembershipLevelEntity.class, 1));
        em.persist(member);
        System.out.println("New Member created!");
        em.flush();

    }

    @Override
    @WebMethod(exclude = true)
    public MemberEntity getMember(String email) {

        List<MemberEntity> memberList = ListMember();

        for (MemberEntity m : memberList) {
            if (m.getEmail().equals(email)) {
                return m;
            }
        }
        return null;

    }

    @WebMethod(operationName = "getMemberById")
    public MemberEntity getMemberById(Long id){
        return em.find(MemberEntity.class, id);
    }
    
    @WebMethod(exclude = true)
    public int CheckFirstTransaction(Long transactionId) {
        TransactionEntity te = em.find(TransactionEntity.class, transactionId);
        if (te == null) {
            System.out.println("Transactoin doesn't exist!");
            return -1;
        } else {
            Calendar today = Calendar.getInstance();
            if (te.getMember() != null) {
                System.out.println("Transactoin has already been rebated!");
                return -2; // transaction has already rebated
            } else if (!(te.getGenerateTime().get(Calendar.YEAR) == today.get(Calendar.YEAR)
                    && te.getGenerateTime().get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR))) {
                System.out.println("Transactoin is not created in today!");
                System.out.println("Today: " + today.getTime().toString());
                System.out.println("Transaction date:" + te.getGenerateTime().getTime().toString());
                return -3;// transaction is not created in today
            } else {
                return 1;
            }
        }
    }

    @WebMethod(exclude = true)
    public Integer upgradeMember(Double totalPoints) {
        //MembershipLevelEntity level6 = em.find(MembershipLevelEntity.class, 6);
        MembershipLevelEntity level5 = em.find(MembershipLevelEntity.class, 5);
        MembershipLevelEntity level4 = em.find(MembershipLevelEntity.class, 4);
        MembershipLevelEntity level3 = em.find(MembershipLevelEntity.class, 3);
        MembershipLevelEntity level2 = em.find(MembershipLevelEntity.class, 2);
        //MembershipLevelEntity level1 = em.find(MembershipLevelEntity.class, 1);
        if (level5.getPointsToUpgrade() <= totalPoints) {
            return 5;
        } else if (level4.getPointsToUpgrade() <= totalPoints) {
            return 4;
        } else if (level3.getPointsToUpgrade() <= totalPoints) {
            return 3;
        } else if (level2.getPointsToUpgrade() <= totalPoints) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    @WebMethod(exclude = true)
    public MemberEntity memberLogin(String email, String pwd) {

        List<MemberEntity> memberList = ListMember();

        for (MemberEntity m : memberList) {
            if (m.getEmail().equals(email)) {
                if (m.getPwd().equals(pwd)) {
                    return m;
                }
            }

        }
        return null;

    }

    @WebMethod(operationName = "getMemberCardIdById")
    public String getMemberCardIdById(Long id) {
        MemberEntity member = em.find(MemberEntity.class, id);

        if (member != null) {
            return member.getCardIdMapping().getCardId();
        } else {
            return null;
        }
    }

    @WebMethod(operationName = "getMemberIdByCardId")
    public Long getMemberIdByCardId(String cardId) {
        MemberCardIdMappingEntity cardIdMapping = em.find(MemberCardIdMappingEntity.class, cardId);

        if (cardIdMapping != null) {
            return cardIdMapping.getMember().getMemberId();
        } else {
            return null;
        }
    }

    @WebMethod(operationName = "addNewPointsForMember")
    public void addNewPointsForMember(Double points, Long memberId) {
        MemberEntity member = em.find(MemberEntity.class, memberId);
        member.setTotalPoints(member.getTotalPoints() + points);
        member.setCurrentPoints(member.getCurrentPoints() + points);

        em.persist(member);
        em.flush();
        member.setMemberlvl(em.find(MembershipLevelEntity.class, upgradeMember(member.getTotalPoints())));
        em.persist(member);
        em.flush();
    }

    @WebMethod(operationName = "redemption")
    public void redemption(Double points, Long memberId) {
        MemberEntity member = em.find(MemberEntity.class, memberId);
        member.setCurrentPoints(member.getCurrentPoints() - points);
        em.persist(member);
        em.flush();
    }

    @Override
    @WebMethod(exclude = true)
    public List<MembershipLevelEntity> getMembership() {
        System.out.println("getMembership():");
        Query q = em.createQuery("SELECT m FROM MembershipLevelEntity m");
        List requiredUserList = new ArrayList();
        for (Object o : q.getResultList()) {
            MembershipLevelEntity u = (MembershipLevelEntity) o;
            requiredUserList.add(u);
            System.out.println(u.toString());
        }
        return requiredUserList;

    }

    @Override
    public int checkEmail(String newEmail) {
        Query q = em.createQuery("SELECT m FROM MemberEntity m WHERE m.email=:email");
        q.setParameter("email", newEmail);
        try {
            MemberEntity me = (MemberEntity) q.getSingleResult();
            if (me != null) {
                return -4;
            }if(me == null){
                return 1;
            }
        } catch (NoResultException e) {
            return 1;
        }

        return -5;
    }

}
