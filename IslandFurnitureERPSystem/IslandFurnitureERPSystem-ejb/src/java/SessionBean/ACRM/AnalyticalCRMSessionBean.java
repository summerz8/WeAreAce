/*
 Session Bean for ACRM:
 Functions needs to be fulfilled:
 (Monthly for retail outlets, resturant and Quaterly/Yearly for furniture marketplace)
 1. Customer Analysis
 (based on different segmentations)
 a. Customer Retention Rate
 b. Customer Lifetime Expectancy
 c. CLV ??(how)
 d. CAC of all/segmented cutomers 
 e. Customer reponse rate to vouchers/coupons 
 2. Personalized Marketing 
 a. Segmentation of customer according to age, gender, natioanlity, member level
 b. send emails to customers 

 */
package SessionBean.ACRM;

import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.TransactionEntity;
import Entity.Store.StoreEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author summer
 */
@Stateless
public class AnalyticalCRMSessionBean implements AnalyticalCRMSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public AnalyticalCRMSessionBean() {
    }

    @Override
    public Collection<MemberEntity> getMembersByAge(Integer minAge, Integer maxAge) throws Exception {
        Collection<MemberEntity> members = new ArrayList();

        try {
            Collection<MemberEntity> memberList;

            Query q = em.createQuery("SELECT m FROM MemberEntity m");
            memberList = q.getResultList();

            for (MemberEntity m : memberList) {
                int age = Calendar.getInstance().get(Calendar.YEAR) - m.getBirthday().get(Calendar.YEAR);
                if (age <= maxAge && age >= minAge) {
                    members.add(m);
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getMembersByAge(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return members;
    }

    //@Override
    public Collection<MemberEntity> getMembersByAge(Integer minAge, Integer maxAge, Collection<MemberEntity> memberList) throws Exception {
        Collection<MemberEntity> members = new ArrayList();

        try {

            for (MemberEntity m : memberList) {
                int age = Calendar.getInstance().get(Calendar.YEAR) - m.getBirthday().get(Calendar.YEAR);
                if (age <= maxAge && age >= minAge) {
                    members.add(m);
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getMembersByAge(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByGender(String gender) throws Exception {
        Collection<MemberEntity> members = new ArrayList();

        try {
            Collection<MemberEntity> memberList;

            Query q = em.createQuery("SELECT m FROM MemberEntity m");
            memberList = q.getResultList();

            for (MemberEntity m : memberList) {
                if (m.getGender().equals(gender)) {
                    members.add(m);
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getMembersByGender(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return members;
    }

    public Collection<MemberEntity> getMembersByGender(String gender, Collection<MemberEntity> memberList) throws Exception {
        Collection<MemberEntity> members = new ArrayList();

        try {
            for (MemberEntity m : memberList) {
                if (m.getGender().equals(gender)) {
                    members.add(m);
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getMembersByGender(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByNationality(String country) throws Exception {
        Collection<MemberEntity> members = new ArrayList();

        try {
            Collection<MemberEntity> memberList;

            Query q = em.createQuery("SELECT m FROM MemberEntity m");
            memberList = q.getResultList();

            for (MemberEntity m : memberList) {
                if (m.getCountry.equals(country)) {
                    members.add(m);
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getMembersByNationality(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return members;
    }

    public Collection<MemberEntity> getMembersByNationality(String country, Collection<MemberEntity> memberList) throws Exception {
        Collection<MemberEntity> members = new ArrayList();

        try {
            for (MemberEntity m : memberList) {
                if (m.getCountry.equals(country)) {
                    members.add(m);
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getMembersByNationality(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByMemberLevel(Integer memberLevel) throws Exception {
        Collection<MemberEntity> members = new ArrayList();

        try {
            Collection<MemberEntity> memberList;

            Query q = em.createQuery("SELECT m FROM MemberEntity m");
            memberList = q.getResultList();

            for (MemberEntity m : memberList) {
                if (m.getMemberlvl().getLevel().equals(memberLevel)) {
                    members.add(m);
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getMembersByMemberLevel(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return members;
    }

    public Collection<MemberEntity> getMembersByMemberLevel(Integer memberLevel, Collection<MemberEntity> memberList) throws Exception {
        Collection<MemberEntity> members = new ArrayList();

        try {

            for (MemberEntity m : memberList) {
                if (m.getMemberlvl().getLevel().equals(memberLevel)) {
                    members.add(m);
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getMembersByMemberLevel(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public String sendEmailsToSegmentCustomers(Collection<Long> memberIdList) throws Exception {
        String result;
        try {
            for (Long id : memberIdList) {
                //sendemail function
            }
            result = "Email has been sent out successfully!";
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: sendEmailsToSegmentCustomers(): Caught an unexpected exception.");
            e.printStackTrace();
            result = "Email has not been sent out successfully.";
        }

        return result;
    }

    @Override
    public String sendEmailsToAll(Long storeId) throws Exception {
        String result;
        try {
            StoreEntity store = em.find(StoreEntity.class, storeId);
            Collection<MemberEntity> memberList = store.getMembers();
            for (MemberEntity m : memberList) {
                //sendemail function
            }
            result = "Email has been sent out successfully!";
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: sendEmailsToAll(): Caught an unexpected exception.");
            e.printStackTrace();
            result = "Email has not been sent out successfully.";
        }
        return result;
    }

    @Override
    public Float getRetentionRate(Long storeId, Calendar time, Integer location,
            Boolean isForAllPlace, Boolean isMonthly) throws Exception {
        Float rr = null;
        try {
            Collection<TransactionEntity> transactionList = new ArrayList();
            Collection<MemberEntity> lastPeriodActiveCustomers;
            Collection<MemberEntity> currentPeriodActiveCustomers;

            StoreEntity store = em.find(StoreEntity.class, storeId);
            transactionList = store.getTransacion();

            if (isMonthly) {
                Calendar lastMonth = time;
                lastMonth.add(Calendar.MONTH, -1);
                lastPeriodActiveCustomers = getActiveMembersMonthly(transactionList, lastMonth, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembersMonthly(transactionList, time, location, isForAllPlace);
            } else {//isYearly
                Calendar lastYear = time;
                lastYear.add(Calendar.YEAR, -1);
                lastPeriodActiveCustomers = getActiveMembersYearly(transactionList, lastYear, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembersYearly(transactionList, time, location, isForAllPlace);
            }
            Integer oldQuantity = lastPeriodActiveCustomers.size();
            Integer currentQuantity = currentPeriodActiveCustomers.size();

            rr = (oldQuantity.floatValue()) / (currentQuantity.floatValue());

        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getRetentionRate(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return rr;
    }

    @Override
    public Float getRetentionRateByAge(Long storeId, Calendar time, Integer location,
            Boolean isForAllPlace, Boolean isMonthly, Integer minAge, Integer maxAge) throws Exception {
        Float rr = null;
        try {
            Collection<TransactionEntity> transactionList = new ArrayList();
            Collection<MemberEntity> lastPeriodActiveCustomers;
            Collection<MemberEntity> currentPeriodActiveCustomers;

            StoreEntity store = em.find(StoreEntity.class, storeId);
            transactionList = store.getTransacion();

            if (isMonthly) {
                Calendar lastMonth = time;
                lastMonth.add(Calendar.MONTH, -1);
                lastPeriodActiveCustomers = getActiveMembersMonthly(transactionList, lastMonth, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembersMonthly(transactionList, time, location, isForAllPlace);
            } else {//isYearly
                Calendar lastYear = time;
                lastYear.add(Calendar.YEAR, -1);
                lastPeriodActiveCustomers = getActiveMembersYearly(transactionList, lastYear, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembersYearly(transactionList, time, location, isForAllPlace);
            }

            lastPeriodActiveCustomers = getMembersByAge(minAge, maxAge, lastPeriodActiveCustomers);
            currentPeriodActiveCustomers = getMembersByAge(minAge, maxAge, currentPeriodActiveCustomers);

            Integer oldQuantity = lastPeriodActiveCustomers.size();
            Integer currentQuantity = currentPeriodActiveCustomers.size();

            rr = (oldQuantity.floatValue()) / (currentQuantity.floatValue());

        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getRetentionRate(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return rr;
    }

    @Override
    public Float getRetentionRateByGender(Long storeId, Calendar time, Integer location,
            Boolean isForAllPlace, Boolean isMonthly, String gender) throws Exception {
        Float rr = null;
        try {
            Collection<TransactionEntity> transactionList = new ArrayList();
            Collection<MemberEntity> lastPeriodActiveCustomers;
            Collection<MemberEntity> currentPeriodActiveCustomers;

            StoreEntity store = em.find(StoreEntity.class, storeId);
            transactionList = store.getTransacion();

            if (isMonthly) {
                Calendar lastMonth = time;
                lastMonth.add(Calendar.MONTH, -1);
                lastPeriodActiveCustomers = getActiveMembersMonthly(transactionList, lastMonth, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembersMonthly(transactionList, time, location, isForAllPlace);
            } else {//isYearly
                Calendar lastYear = time;
                lastYear.add(Calendar.YEAR, -1);
                lastPeriodActiveCustomers = getActiveMembersYearly(transactionList, lastYear, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembersYearly(transactionList, time, location, isForAllPlace);
            }

            lastPeriodActiveCustomers = getMembersByGender(gender, lastPeriodActiveCustomers);
            currentPeriodActiveCustomers = getMembersByGender(gender, currentPeriodActiveCustomers);

            Integer oldQuantity = lastPeriodActiveCustomers.size();
            Integer currentQuantity = currentPeriodActiveCustomers.size();

            rr = (oldQuantity.floatValue()) / (currentQuantity.floatValue());

        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getRetentionRate(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return rr;
    }

    @Override
    public Float getRetentionRateByNationality(Long storeId, Calendar time, Integer location,
            Boolean isForAllPlace, Boolean isMonthly, String country) throws Exception {
        Float rr = null;
        try {
            Collection<TransactionEntity> transactionList = new ArrayList();
            Collection<MemberEntity> lastPeriodActiveCustomers;
            Collection<MemberEntity> currentPeriodActiveCustomers;

            StoreEntity store = em.find(StoreEntity.class, storeId);
            transactionList = store.getTransacion();

            if (isMonthly) {
                Calendar lastMonth = time;
                lastMonth.add(Calendar.MONTH, -1);
                lastPeriodActiveCustomers = getActiveMembersMonthly(transactionList, lastMonth, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembersMonthly(transactionList, time, location, isForAllPlace);
            } else {//isYearly
                Calendar lastYear = time;
                lastYear.add(Calendar.YEAR, -1);
                lastPeriodActiveCustomers = getActiveMembersYearly(transactionList, lastYear, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembersYearly(transactionList, time, location, isForAllPlace);
            }

            lastPeriodActiveCustomers = getMembersByNationality(country, lastPeriodActiveCustomers);
            currentPeriodActiveCustomers = getMembersByNationality(country, currentPeriodActiveCustomers);

            Integer oldQuantity = lastPeriodActiveCustomers.size();
            Integer currentQuantity = currentPeriodActiveCustomers.size();
            rr = (oldQuantity.floatValue()) / (currentQuantity.floatValue());

        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getRetentionRate(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return rr;
    }

    @Override
    public Float getRetentionRateByMemberLevel(Long storeId, Calendar time, Integer location,
            Boolean isForAllPlace, Boolean isMonthly, Integer memberLevel) throws Exception {
        Float rr = null;
        try {
            Collection<TransactionEntity> transactionList = new ArrayList();
            Collection<MemberEntity> lastPeriodActiveCustomers;
            Collection<MemberEntity> currentPeriodActiveCustomers;

            StoreEntity store = em.find(StoreEntity.class, storeId);
            transactionList = store.getTransacion();

            if (isMonthly) {
                Calendar lastMonth = time;
                lastMonth.add(Calendar.MONTH, -1);
                lastPeriodActiveCustomers = getActiveMembersMonthly(transactionList, lastMonth, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembersMonthly(transactionList, time, location, isForAllPlace);
            } else {//isYearly
                Calendar lastYear = time;
                lastYear.add(Calendar.YEAR, -1);
                lastPeriodActiveCustomers = getActiveMembersYearly(transactionList, lastYear, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembersYearly(transactionList, time, location, isForAllPlace);
            }

            lastPeriodActiveCustomers = getMembersByMemberLevel(memberLevel, lastPeriodActiveCustomers);
            currentPeriodActiveCustomers = getMembersByMemberLevel(memberLevel, currentPeriodActiveCustomers);

            Integer oldQuantity = lastPeriodActiveCustomers.size();
            Integer currentQuantity = currentPeriodActiveCustomers.size();

            rr = (oldQuantity.floatValue()) / (currentQuantity.floatValue());

        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getRetentionRate(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return rr;
    }

    @Override
    public Float getCLE() throws Exception {
        Float cle = null;

        return cle;
    }

    @Override
    public Float getCLV() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Float getCAC() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Float getVoucherResponseRate() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public Collection<MemberEntity> getActiveMembersMonthly(Collection<TransactionEntity> transactionList, Calendar month, Integer location, Boolean isForAllPlace) throws Exception {
        Collection<MemberEntity> members = new ArrayList();
        try {
            for (TransactionEntity transaction : transactionList) {
                if (transaction.getMemberId() != null) {
                    if (transaction.getGenerateTime().get(Calendar.MONTH) == month.get(Calendar.MONTH)) {
                        MemberEntity m = em.find(MemberEntity.class, transaction.getMemberId());

                        if (isForAllPlace) {
                            members.add(m);
                        } else if (transaction.getLocation() == location) {
                            //calculate saperately for retail, funiture and marketplace
                            members.add(m);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getActiveMembersMonthly(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return members;
    }

    public Collection<MemberEntity> getActiveMembersYearly(Collection<TransactionEntity> transactionList, Calendar year, Integer place, Boolean isForAllPlace) throws Exception {
        Collection<MemberEntity> members = new ArrayList();
        try {
            for (TransactionEntity transaction : transactionList) {
                if (transaction.getMemberId() != null) {
                    if (transaction.getGenerateTime().get(Calendar.YEAR) == year.get(Calendar.YEAR)) {
                        MemberEntity m = em.find(MemberEntity.class, transaction.getMemberId());

                        if (isForAllPlace) {
                            members.add(m);
                        } else if (transaction.getLocation() == location) {
                            //calculate saperately for retail, funiture and marketplace
                            members.add(m);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getActiveMembersMonthly(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return members;

    }
}
