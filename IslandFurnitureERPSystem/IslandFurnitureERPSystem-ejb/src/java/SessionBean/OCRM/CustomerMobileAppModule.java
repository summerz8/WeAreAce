/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Factory.ProductEntity;
import Entity.Store.OCRM.CountryProductEntity;
import Entity.Store.OCRM.CountrySetEntity;
import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.MembershipLevelEntity;
import Entity.Store.OCRM.ShoppingCartItemEntity;
import Entity.Store.OCRM.SurpriseQREntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreEventEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreSetEntity;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author dan
 */
@Stateless
@WebService
public class CustomerMobileAppModule implements CustomerMobileAppModuleLocal {

    @PersistenceContext

    EntityManager em;
    private final int BASENUMBER = 100;

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
        System.out.println("memberLogin:" + pwd);
        try {
            Query q = em.createQuery("SELECT m FROM MemberEntity m WHERE m.email=:email");
            q.setParameter("email", email);
            try {
                member = (MemberEntity) q.getResultList().get(0);
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
        } catch (NullPointerException e) {
            e.printStackTrace();
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
            MemberEntity member = (MemberEntity) q.getResultList().get(0);
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
    @WebMethod(operationName = "getWishListItem")
    @Override
    public List<String[]> getWishListItem(@WebParam(name = "email") String email,
            @WebParam(name = "storeId") Long store) {
        System.out.println("getWishListItem(): started + " + email);
        System.out.println("getWishListItem(): started + " + store.toString());
        Query q = em.createQuery("SELECT m FROM MemberEntity m WHERE m.email=:email");
        q.setParameter("email", email);

        MemberEntity member = (MemberEntity) q.getResultList().get(0);
        System.out.println("member email: " + member.getEmail());
        if (member != null) {
            Long memberId = member.getMemberId();
            List<String[]> result = new ArrayList();
            List<ShoppingCartItemEntity> shoppingCartList = member.getShoppingCartList();

            for (ShoppingCartItemEntity s : shoppingCartList) {
                if (s.getStoreId().equals(store)) {
                    System.out.println("required store " + store);
                    System.out.println("shoppingCartItemEntity: " + s.toString());
                    try {
                        s.getType();
                    } catch (NullPointerException e) {
                        e.printStackTrace();

                    }
                    if (s.getType() == null) {
                        System.out.println("the type is null");
                        continue;
                    } else if (s.getType().equals("product")) {
                        CountryProductEntity p = s.getCustomerWebItem();
//                    Collection<StoreProductEntity> storeProductList = p.getProduct().getStoreProducts();
                        ProductEntity pe = p.getProduct();
                        Collection<StoreProductEntity> storeProductList = pe.getStoreProducts();
                        for (StoreProductEntity sp : storeProductList) {
                            if (store.equals(sp.getStore().getStoreId())) {
                                String[] str = new String[3];
                                str[0] = sp.getStoreProductId().toString();
                                str[1] = sp.getName();
                                str[2] = "product";
                                result.add(str);
                                System.out.println("product added " + str[0]);
                                for (int i = 0; i < result.size(); i++) {
                                    System.out.println("the product result is " + result.get(i)[0] + " " + result.get(i)[1] + " " + result.get(i)[2]);
                                }
                            }

                        }

                    } else if (s.getType().equals("set")) {
                        CountrySetEntity cs = s.getCountrySet();
                        List<StoreSetEntity> sse = cs.getSetList();
                        for (StoreSetEntity ss : sse) {
                            if (ss.getStore().getStoreId().equals(store)) {
                                String[] str = new String[3];
                                str[0] = ss.getId().toString();
                                str[1] = ss.getName();
                                str[2] = "set";
                                result.add(str);
                                System.out.println("set added " + str[0]);
                                for (int i = 0; i < result.size(); i++) {
                                    System.out.println("the  set result is " + result.get(i)[0] + " " + result.get(i)[1] + " " + result.get(i)[2]);
                                }
                            }
                        }
                    }

                }
            }
            for (int i = 0; i < result.size(); i++) {
                System.out.println("the result is " + result.get(i)[0] + " " + result.get(i)[1] + " " + result.get(i)[2]);
            }
            return result;
        }
        return null;
    }

    @WebMethod(operationName = "getStoreList")
    @Override
    public List<StoreEntity> getStoreList() {
        Query q = em.createQuery("SELECT s FROM StoreEntity s WHERE s.country=:country");
        q.setParameter("country", "Singapore");
        return (List<StoreEntity>) q.getResultList();
    }

//    public List<CountrySetEntity> getWishListSet(){
//    }
//
    @WebMethod(operationName = "viewItemDetail")
    @Override
    public String[] viewItemDetail(@WebParam(name = "storeProductId") Long storeProductId,
            @WebParam(name = "email") String email) {
        StoreProductEntity sp = em.find(StoreProductEntity.class, storeProductId);
        Query q = em.createQuery("SELECT m FROM MemberEntity m WHERE m.email=:email");
        q.setParameter("email", email);

        MemberEntity member = (MemberEntity) q.getResultList().get(0);
        System.out.println("member email: " + member.getEmail());
        List<ShoppingCartItemEntity> shoppingCartList = member.getShoppingCartList();

        String[] result = new String[10];
        result[0] = sp.getName();
        Double memPrice = sp.getProduct().getMemberPrice();
        result[1] = memPrice.toString();
        Integer amount = 1;
        for (ShoppingCartItemEntity s : shoppingCartList) {
            if (s.getStoreId().equals(sp.getStore().getStoreId())) {
                amount = s.getQuantity();
                result[2] = s.getQuantity().toString();
                String path = "/Users/dan/Desktop/Project/IslandFurnitureERPSystem/CustomerWeb/web/resources/images/" + s.getCustomerWebItem().getPicture();
                File picture = new File(path);
                System.out.println(path);
                result[5] = getItemPicture(picture);
                break;
            }
        }
        Double total = amount * memPrice;
        result[3] = total.toString();
        result[4] = sp.getUnrestrictedInventory().toString();
        return result;
    }

    @Override
    public String getItemPicture(File imageFile) {
        byte[] imageBytes = null;
        try {
            if (imageFile == null) {
                System.out.println("this image file is null!");
            }
            BufferedImage img = ImageIO.read(imageFile.toURI().toURL());
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            imageBytes = baos.toByteArray();
            baos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("Base64 encode?: " + Base64.encodeBase64String(imageBytes));
        return (imageBytes != null) ? Base64.encodeBase64String(imageBytes) : "";
    }
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
    public Integer checkSurprise(@WebParam(name = "QR") String QR,
            @WebParam(name = "email") String email) {
        System.out.println("checkSurprise started " + QR);
        Calendar checkInTime = Calendar.getInstance();
        Integer hour = checkInTime.get(Calendar.HOUR_OF_DAY);

        Query q = em.createQuery("SELECT s FROM SurpriseQREntity  s WHERE s.randomString=:QR");
        q.setParameter("QR", QR);
        List<SurpriseQREntity> qrList = (List<SurpriseQREntity>) q.getResultList();
        if (qrList != null) {
            System.out.println("qr not null!");
            for (SurpriseQREntity sqe : qrList) {
                Calendar today = Calendar.getInstance();
                if (sqe.getExpireDate().compareTo(today) > 0) {
                    int percentage = (int) (sqe.getPercentage() * BASENUMBER);
                    System.out.println("percentage: " + percentage);
                    List<Integer> surpriseNum = new ArrayList();
                    for (int i = surpriseNum.size(); i < percentage;) {
                        Random random = new Random();
                        Integer a = random.nextInt(BASENUMBER + 1) + 0;
                        if (surpriseNum.isEmpty()) {
                            surpriseNum.add(a);
                            System.out.println("new added num empty: " + a);
                            i = surpriseNum.size();
                            continue;
                        } else if (!surpriseNum.contains(a)) {
                            surpriseNum.add(a);
                            i = surpriseNum.size();
                            System.out.println("new added num: " + a);
                            continue;
                        }
                    }
                    System.out.println("surpriseNum list" + surpriseNum.toString());
                    Random random = new Random();
                    int userSurprise = random.nextInt(BASENUMBER + 1) + 0;
                    System.out.println("userSurprise: " + userSurprise);
                    for (int k = 0; k < surpriseNum.size(); k++) {
                        System.out.println("hehe " + surpriseNum.get(k));
                        if (userSurprise == (int) surpriseNum.get(k)) {
                            Double points = sqe.getRewardPoints();
                            int check = addPoints(email, points);
                            if (check == 1) {
                                System.out.println("userSurprise == surpriseNum: " + surpriseNum);
                                return 1;//Congardulations!
                            } else {
                                return check;//error
                            }
                        }
                    } // end for k
                    return 0;
                } else {
                    return -4;// not today 
                }
            }
        } else {
            return -1;//not valid qr
        }
        return -3;// another error
    }

    public int addPoints(String email, Double points) {
        Query q = em.createQuery("SELECT m FROM MemberEntity m WHERE m.email=:email");
        q.setParameter("email", email);

        MemberEntity member = (MemberEntity) q.getResultList().get(0);

        if (member != null) {
            System.out.println("member email: " + member.getEmail() + " " + member.getMemberId().toString());

            member.setTotalPoints(member.getTotalPoints() + points);
            member.setCurrentPoints(member.getCurrentPoints() + points);

            em.persist(member);
            em.flush();
            MembershipLevelEntity msle = em.find(MembershipLevelEntity.class, upgradeMember(member.getTotalPoints()));
            member.setMemberlvl(msle);
            if (msle.getLevelId() < 5) {
                MembershipLevelEntity msle2 = em.find(MembershipLevelEntity.class, (upgradeMember(member.getTotalPoints()) + 1));
                member.setPointsToUpgrade(msle2.getPointsToUpgrade());
            } else if (msle.getLevelId() == 5) {
                member.setPointsToUpgrade(0D);
            }
            em.persist(member);
            em.flush();
            return 1;
        } else {
            return -2;
        }

    }

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

    @WebMethod(operationName = "getSetItemList")
    @Override
    public List<String[]> getSetItemList(@WebParam(name = "email") String email,
            @WebParam(name = "setId") Long setId) {
        System.out.println("getWishListItem(): started + " + email);
        System.out.println("getWishListItem(): started + " + setId.toString());
        Query q = em.createQuery("SELECT m FROM MemberEntity m WHERE m.email=:email");
        q.setParameter("email", email);

        MemberEntity member = (MemberEntity) q.getResultList().get(0);
        System.out.println("member email: " + member.getEmail());
        if (member != null) {
            Long memberId = member.getMemberId();
            List<String[]> result = new ArrayList();
            StoreSetEntity ss = em.find(StoreSetEntity.class, setId);

            List<StoreProductEntity> storeProductList = ss.getStoreProductList();

            for (StoreProductEntity spe : storeProductList) {
                String[] str = new String[3];
                str[0] = spe.getStoreProductId().toString();
                str[1] = spe.getName();
                str[2] = "product";
                result.add(str);
                System.out.println("product added " + str[0]);
                for (int i = 0; i < result.size(); i++) {
                    System.out.println("the product result is " + result.get(i)[0] + " " + result.get(i)[1] + " " + result.get(i)[2]);
                }
            }
            return result;
        }
        return null;
    }

    @WebMethod(operationName = "getEventList")
    @Override
    public List<StoreEventEntity> getEventList(@WebParam(name = "storeId") Long storeId) {
        List<StoreEventEntity> results = new ArrayList<StoreEventEntity>();
        Query q = em.createQuery("SELECT m FROM StoreEventEntity m");

        List<StoreEventEntity> seList = q.getResultList();
        for (StoreEventEntity se : seList) {
            if (se.getStore().getStoreId().equals(storeId)) {
                results.add(se);
            }
        }
        return results;
    }
}
