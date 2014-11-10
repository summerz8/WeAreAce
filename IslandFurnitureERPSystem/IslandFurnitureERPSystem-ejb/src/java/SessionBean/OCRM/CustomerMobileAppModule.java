/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.SurpriseQREntity;
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
import javax.persistence.Query;

/**
 *
 * @author dan
 */
@Stateless
@WebService
public class CustomerMobileAppModule implements CustomerMobileAppModuleLocal {

    EntityManager em;
    private final int BASENUMBER = 1000000;

    public CustomerMobileAppModule() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    @WebMethod(operationName = "memberLogin")
    public int MemberLogin(@WebParam(name = "email") String email, 
            @WebParam(name = "pwd") String pwd) {
        MemberEntity member;
        int check = 0;

        System.out.println("memberLogin:" + email);
        Query q = em.createQuery("SELECT m FROM MemberEntity m WHERE m.email=:email");
        q.setParameter("email", email);
        try {
            member = (MemberEntity) q.getSingleResult();
            if (member == null) {
                System.out.println("Member Not Found!");
                check = -1;
            } else if (member.isDeleteFlag()) {
                System.out.println("Member is Deleted!");
                check = -1;//member not found
            } else if ((member.getPwd().equals(pwd))) {
                check = 1;
                System.out.println("Member Found!");
            } else {
                System.out.println("Member Found but password inccorect!");
                System.out.println("database password: " + member.getPwd());
                System.out.println("input password: " + pwd);
                check = 0;
            }
        } catch (NoResultException e) {
            check = -1;
        }

        return check;

    }

//    @Override
//    public List<Transaction> viewPointsAccount() {
//    }
    @Override
    @WebMethod(operationName = "getMember")
    public MemberEntity getMember(@WebParam(name = "email") String email) {
        Query q = em.createQuery("SELECT m FROM MemberEntity m WHERE m.email=:email");
        q.setParameter("email", email);
        try {
            MemberEntity member = (MemberEntity) q.getSingleResult();
            return member;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }

    }

    //event entity not added
//    @Override
//    @WebMethod(operationName = "getAllEvent")
//    public List<EventEntity> getAllEvent(){
//        Query q = em.createQuery("SELECT e FROM EventEntity e");
//        List<EventEntity> eventList = new ArrayList();
//        for (Object o : q.getResultList()) {
//            EventEntity event = (EventEntity) o;
//            eventList.add(event);
//        }
//        return eventList;
//    }
//
//    @Override
//    public List<CountryProductEntity> getWishListItem() {
//    }
//    
//    public List<CountrySetEntity> getWishListSet(){
//    }
//
//    @Override
//    public CountryProductEntity viewItemDetail() {
//    }
//
//    public List<CountryProductEntity> getSetItem(){
//    }
//    
//    @Override
//    public List<CountryProductEntity> getWishListItemByCountry() {
//    }
//
//    @Override
//    public List<CountryProductEntity> getWishListItemByStore() {
//    }
//    
//    @Override
//    public List<CountrySetEntity> getWishListSetByCountry() {
//    }
//
//    @Override
//    public List<CountrySetEntity> getWishListSetByStore() {
//    }
@WebMethod(operationName = "checkSurprise")
    @Override
    public Integer checkSurprise(@WebParam(name = "QR")String QR, 
            @WebParam(name = "email")String email) {

        Calendar checkInTime = Calendar.getInstance();
        Integer hour = checkInTime.get(Calendar.HOUR_OF_DAY);
        SurpriseQRBean qrb = new SurpriseQRBean();
        if (qrb.checkSameQR(QR)) {
            Query q = em.createQuery("SELECT s FROM SurpriseQREntity  s WHERE s.randomString:=QR");
            q.setParameter("QR", QR);
            SurpriseQREntity sqe = (SurpriseQREntity) q.getSingleResult();
            Calendar today = Calendar.getInstance();
            if (sqe.getExpireDate().compareTo(today) >= 0) {
                int percentage = (int) (sqe.getPercentage() * BASENUMBER);
                List<Integer> surpriseNum = new ArrayList();
                for (int i = 0; i < percentage; i++) {
                    Random random = new Random();
                    int a = random.nextInt(BASENUMBER + 1) + 0;
                    for (int j = 0; j < surpriseNum.size(); j++) {
                        if (a == surpriseNum.get(j)) {
                            a = random.nextInt(BASENUMBER + 1) + 0;
                            System.out.println("surpriseNum " + surpriseNum.get(j));
                            System.out.println("new a " + a);
                        }
                    }
                    surpriseNum.add(a);
                }
                Random random = new Random();
                int userSurprise = random.nextInt(BASENUMBER + 1) + 0;
                for (int k = 0; k < surpriseNum.size(); k++) {
                    if (userSurprise == k) {
                        Double points = sqe.getRewardPoints();
                        int check = addPoints(email, points);
                        if (check == 1) {
                            return 1;//Congardulations!
                        } else {
                            return check;//error
                        }
                    }
                }

            } else {
                return -1;// not today 
            }
        } else {
            return -1;//not valid qr
        }
        return -3;// another error
    }

    public int addPoints(String email, Double points) {
        MemberRegistrationModule mrm = new MemberRegistrationModule();
        MemberEntity me = getMember(email);
        if (me != null) {
            mrm.addNewPointsForMember(points, Long.MIN_VALUE);
            return 1;
        } else {
            return -2;
        }
    }
}
