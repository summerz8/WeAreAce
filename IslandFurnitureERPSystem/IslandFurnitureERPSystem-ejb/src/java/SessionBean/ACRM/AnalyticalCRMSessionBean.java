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
    public Collection<MemberEntity> getAllMembers() throws Exception {
        Collection<MemberEntity> members = new ArrayList();

        try {
            System.out.println("AnalyticalCRMSessionBean: getAllMembers()");

            Query q = em.createQuery("SELECT m FROM MemberEntity m");
            members = q.getResultList();
            System.out.println("AllMembers: " + members.toString());

        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getAllMembers(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByAge(Integer minAge, Integer maxAge) throws Exception {
        Collection<MemberEntity> members = new ArrayList();

        try {
            System.out.println("AnalyticalCRMSessionBean: getMembersByAge()");

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
                System.out.println("AnalyticalCRMSessionBean: getMembersByGender(): gender = " + gender);

                if (m.getGender().equals(gender)) {
                    members.add(m);
                }
            }
            System.out.println("AnalyticalCRMSessionBean: getMembersByGender(): members ==> " + members.toString());
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
                if (m.getCountry().equals(country)) {
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
                if (m.getCountry().equals(country)) {
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
                if (m.getMemberlvl().getLevelId().equals(memberLevel)) {
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
                if (m.getMemberlvl().getLevelId().equals(memberLevel)) {
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
    public Collection<MemberEntity> getMembersByAG(Integer minAge, Integer maxAge, String gender) throws Exception {
        Collection<MemberEntity> members = new ArrayList<>();
        try {
            members = getAllMembers();
            members = getMembersByAge(minAge, maxAge, members);
            members = getMembersByGender(gender, members);
        } catch (Exception e) {
            System.err.print("Caught an unexpected error!");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByAC(Integer minAge, Integer maxAge, String country) throws Exception {
        Collection<MemberEntity> members = new ArrayList<>();
        try {
            members = getAllMembers();
            members = getMembersByAge(minAge, maxAge, members);
            members = getMembersByNationality(country, members);
        } catch (Exception e) {
            System.err.print("Caught an unexpected error!");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByAM(Integer minAge, Integer maxAge, Integer memberLevel) throws Exception {
        Collection<MemberEntity> members = new ArrayList<>();
        try {
            members = getAllMembers();
            members = getMembersByAge(minAge, maxAge, members);
            members = getMembersByMemberLevel(memberLevel, members);
        } catch (Exception e) {
            System.err.print("Caught an unexpected error!");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByGC(String gender, String country) throws Exception {
        Collection<MemberEntity> members = new ArrayList<>();
        try {
            members = getAllMembers();
            members = getMembersByGender(gender, members);
            members = getMembersByNationality(country, members);
        } catch (Exception e) {
            System.err.print("Caught an unexpected error!");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByGM(String gender, Integer memberLevel) throws Exception {
        Collection<MemberEntity> members = new ArrayList<>();
        try {
            members = getAllMembers();
            members = getMembersByGender(gender, members);
            members = getMembersByMemberLevel(memberLevel, members);
        } catch (Exception e) {
            System.err.print("Caught an unexpected error!");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByCM(String country, Integer memberLevel) throws Exception {
        Collection<MemberEntity> members = new ArrayList<>();
        try {
            members = getAllMembers();
            members = getMembersByNationality(country, members);
            members = getMembersByMemberLevel(memberLevel, members);
        } catch (Exception e) {
            System.err.print("Caught an unexpected error!");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByAGC(Integer minAge, Integer maxAge, String gender, String country) throws Exception {
        Collection<MemberEntity> members = new ArrayList<>();
        try {
            members = getAllMembers();
            members = getMembersByAge(minAge, maxAge, members);
            members = getMembersByGender(gender, members);
            members = getMembersByNationality(country, members);
        } catch (Exception e) {
            System.err.print("Caught an unexpected error!");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByAGM(Integer minAge, Integer maxAge, String gender, Integer memberLevel) throws Exception {
        Collection<MemberEntity> members = new ArrayList<>();
        try {

        } catch (Exception e) {
            System.err.print("Caught an unexpected error!");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByACM(Integer minAge, Integer maxAge, String country, Integer memberLevel) throws Exception {
        Collection<MemberEntity> members = new ArrayList<>();
        try {
            members = getAllMembers();
            members = getMembersByAge(minAge, maxAge, members);
            members = getMembersByNationality(country, members);
            members = getMembersByMemberLevel(memberLevel, members);
        } catch (Exception e) {
            System.err.print("Caught an unexpected error!");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByGCM(String gender, String country, Integer memberLevel) throws Exception {
        Collection<MemberEntity> members = new ArrayList<>();
        try {
            members = getAllMembers();
            members = getMembersByGender(gender, members);
            members = getMembersByNationality(country, members);
            members = getMembersByMemberLevel(memberLevel, members);
        } catch (Exception e) {
            System.err.print("Caught an unexpected error!");
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Collection<MemberEntity> getMembersByAGCM(Integer minAge, Integer maxAge, String gender, String country, Integer memberLevel) throws Exception {
        Collection<MemberEntity> members = new ArrayList<>();
        try {
            members = getAllMembers();
            members = getMembersByAge(minAge, maxAge, members);
            members = getMembersByGender(gender, members);
            members = getMembersByNationality(country, members);
            members = getMembersByMemberLevel(memberLevel, members);
        } catch (Exception e) {
            System.err.print("Caught an unexpected error!");
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
            Query q = em.createQuery("Select m from MemberEntity where m.storeId=:id");
            q.setParameter("storeId", storeId);
            Collection<MemberEntity> memberList = q.getResultList();
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
    public Double getRetentionRate(Long storeId, Calendar time, Integer location,
            Boolean isForAllPlace, Boolean isMonthly, Boolean isYearly) throws Exception {
        Double rr = null;
        try {
            Collection<MemberEntity> lastPeriodActiveCustomers;
            Collection<MemberEntity> currentPeriodActiveCustomers;
            if (isMonthly) {
                Calendar lastMonth = time;
                lastMonth.add(Calendar.MONTH, -1);
                Calendar lastMonth2 = lastMonth;
                lastMonth2.add(Calendar.MONTH, -1);
                lastPeriodActiveCustomers = getActiveMembers(storeId, lastMonth2, lastMonth, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembers(storeId, lastMonth, time, location, isForAllPlace);
            } else if (isYearly) {//isQuarterly
                Calendar lastYear = time;
                lastYear.add(Calendar.YEAR, -1);
                Calendar lastYear2 = lastYear;
                lastYear2.add(Calendar.YEAR, -1);
                lastPeriodActiveCustomers = getActiveMembers(storeId, lastYear2, lastYear, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembers(storeId, lastYear, time, location, isForAllPlace);
            } else {
                Calendar lastQuarter = time;
                lastQuarter.add(Calendar.MONTH, -3);
                Calendar lastQuarter2 = lastQuarter;
                lastQuarter2.add(Calendar.MONTH, -3);
                lastPeriodActiveCustomers = getActiveMembers(storeId, lastQuarter2, lastQuarter, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembers(storeId, lastQuarter, time, location, isForAllPlace);
            }
            Integer oldQuantity = lastPeriodActiveCustomers.size();
            Integer currentQuantity = currentPeriodActiveCustomers.size();

            rr = (oldQuantity) * 1.0 / (currentQuantity);

        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getRetentionRate(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return rr;
    }

//    @Override
//    public Float getRetentionRateByAge(Long storeId, Calendar time, Integer location,
//            Boolean isForAllPlace, Boolean isMonthly, Integer minAge, Integer maxAge) throws Exception {
//        Float rr = null;
//        try {
//            Collection<TransactionEntity> transactionList = new ArrayList();
//            Collection<MemberEntity> lastPeriodActiveCustomers;
//            Collection<MemberEntity> currentPeriodActiveCustomers;
//
//            StoreEntity store = em.find(StoreEntity.class, storeId);
//            transactionList = store.getTransactions();
//
//            if (isMonthly) {
//                Calendar lastMonth = time;
//                lastMonth.add(Calendar.MONTH, -1);
//                lastPeriodActiveCustomers = getActiveMembersMonthly(transactionList, lastMonth, location, isForAllPlace);
//                currentPeriodActiveCustomers = getActiveMembersMonthly(transactionList, time, location, isForAllPlace);
//            } else {//isYearly
//                Calendar lastYear = time;
//                lastYear.add(Calendar.YEAR, -1);
//                lastPeriodActiveCustomers = getActiveMembersYearly(transactionList, lastYear, location, isForAllPlace);
//                currentPeriodActiveCustomers = getActiveMembersYearly(transactionList, time, location, isForAllPlace);
//            }
//
//            lastPeriodActiveCustomers = getMembersByAge(minAge, maxAge, lastPeriodActiveCustomers);
//            currentPeriodActiveCustomers = getMembersByAge(minAge, maxAge, currentPeriodActiveCustomers);
//
//            Integer oldQuantity = lastPeriodActiveCustomers.size();
//            Integer currentQuantity = currentPeriodActiveCustomers.size();
//
//            rr = (oldQuantity.floatValue()) / (currentQuantity.floatValue());
//
//        } catch (Exception e) {
//            System.err.println("AnalyticalCRMSessionBean: getRetentionRate(): Caught an unexpected exception.");
//            e.printStackTrace();
//        }
//        return rr;
//    }
//
//    @Override
//    public Float getRetentionRateByGender(Long storeId, Calendar time, Integer location,
//            Boolean isForAllPlace, Boolean isMonthly, String gender) throws Exception {
//        Float rr = null;
//        try {
//            Collection<TransactionEntity> transactionList = new ArrayList();
//            Collection<MemberEntity> lastPeriodActiveCustomers;
//            Collection<MemberEntity> currentPeriodActiveCustomers;
//
//            StoreEntity store = em.find(StoreEntity.class, storeId);
//            transactionList = store.getTransactions();
//
//            if (isMonthly) {
//                Calendar lastMonth = time;
//                lastMonth.add(Calendar.MONTH, -1);
//                lastPeriodActiveCustomers = getActiveMembersMonthly(transactionList, lastMonth, location, isForAllPlace);
//                currentPeriodActiveCustomers = getActiveMembersMonthly(transactionList, time, location, isForAllPlace);
//            } else {//isYearly
//                Calendar lastYear = time;
//                lastYear.add(Calendar.YEAR, -1);
//                lastPeriodActiveCustomers = getActiveMembersYearly(transactionList, lastYear, location, isForAllPlace);
//                currentPeriodActiveCustomers = getActiveMembersYearly(transactionList, time, location, isForAllPlace);
//            }
//
//            lastPeriodActiveCustomers = getMembersByGender(gender, lastPeriodActiveCustomers);
//            currentPeriodActiveCustomers = getMembersByGender(gender, currentPeriodActiveCustomers);
//
//            Integer oldQuantity = lastPeriodActiveCustomers.size();
//            Integer currentQuantity = currentPeriodActiveCustomers.size();
//
//            rr = (oldQuantity.floatValue()) / (currentQuantity.floatValue());
//
//        } catch (Exception e) {
//            System.err.println("AnalyticalCRMSessionBean: getRetentionRate(): Caught an unexpected exception.");
//            e.printStackTrace();
//        }
//        return rr;
//    }
//
//    @Override
//    public Float getRetentionRateByNationality(Long storeId, Calendar time, Integer location,
//            Boolean isForAllPlace, Boolean isMonthly, String country) throws Exception {
//        Float rr = null;
//        try {
//            Collection<TransactionEntity> transactionList = new ArrayList();
//            Collection<MemberEntity> lastPeriodActiveCustomers;
//            Collection<MemberEntity> currentPeriodActiveCustomers;
//
//            StoreEntity store = em.find(StoreEntity.class, storeId);
//            transactionList = store.getTransactions();
//
//            if (isMonthly) {
//                Calendar lastMonth = time;
//                lastMonth.add(Calendar.MONTH, -1);
//                lastPeriodActiveCustomers = getActiveMembersMonthly(transactionList, lastMonth, location, isForAllPlace);
//                currentPeriodActiveCustomers = getActiveMembersMonthly(transactionList, time, location, isForAllPlace);
//            } else {//isYearly
//                Calendar lastYear = time;
//                lastYear.add(Calendar.YEAR, -1);
//                lastPeriodActiveCustomers = getActiveMembersYearly(transactionList, lastYear, location, isForAllPlace);
//                currentPeriodActiveCustomers = getActiveMembersYearly(transactionList, time, location, isForAllPlace);
//            }
//
//            lastPeriodActiveCustomers = getMembersByNationality(country, lastPeriodActiveCustomers);
//            currentPeriodActiveCustomers = getMembersByNationality(country, currentPeriodActiveCustomers);
//
//            Integer oldQuantity = lastPeriodActiveCustomers.size();
//            Integer currentQuantity = currentPeriodActiveCustomers.size();
//            rr = (oldQuantity.floatValue()) / (currentQuantity.floatValue());
//
//        } catch (Exception e) {
//            System.err.println("AnalyticalCRMSessionBean: getRetentionRate(): Caught an unexpected exception.");
//            e.printStackTrace();
//        }
//        return rr;
//    }
//
//    @Override
//    public Float getRetentionRateByMemberLevel(Long storeId, Calendar time, Integer location,
//            Boolean isForAllPlace, Boolean isMonthly, Integer memberLevel) throws Exception {
//        Float rr = null;
//        try {
//            Collection<TransactionEntity> transactionList = new ArrayList();
//            Collection<MemberEntity> lastPeriodActiveCustomers;
//            Collection<MemberEntity> currentPeriodActiveCustomers;
//
//            StoreEntity store = em.find(StoreEntity.class, storeId);
//            transactionList = store.getTransactions();
//
//            if (isMonthly) {
//                Calendar lastMonth = time;
//                lastMonth.add(Calendar.MONTH, -1);
//                lastPeriodActiveCustomers = getActiveMembersMonthly(transactionList, lastMonth, location, isForAllPlace);
//                currentPeriodActiveCustomers = getActiveMembersMonthly(transactionList, time, location, isForAllPlace);
//            } else {//isYearly
//                Calendar lastYear = time;
//                lastYear.add(Calendar.YEAR, -1);
//                lastPeriodActiveCustomers = getActiveMembersYearly(transactionList, lastYear, location, isForAllPlace);
//                currentPeriodActiveCustomers = getActiveMembersYearly(transactionList, time, location, isForAllPlace);
//            }
//
//            lastPeriodActiveCustomers = getMembersByMemberLevel(memberLevel, lastPeriodActiveCustomers);
//            currentPeriodActiveCustomers = getMembersByMemberLevel(memberLevel, currentPeriodActiveCustomers);
//
//            Integer oldQuantity = lastPeriodActiveCustomers.size();
//            Integer currentQuantity = currentPeriodActiveCustomers.size();
//
//            rr = (oldQuantity.floatValue()) / (currentQuantity.floatValue());
//
//        } catch (Exception e) {
//            System.err.println("AnalyticalCRMSessionBean: getRetentionRate(): Caught an unexpected exception.");
//            e.printStackTrace();
//        }
//        return rr;
//    }
    @Override
    public Collection<Double> getCLV(Long storeId, Calendar time, 
            Double grossProfitMargin, Double aveAcquisitionCost, Integer location,
            Boolean isForAllPlace, Boolean isFemale, Boolean isMale, Boolean isOthers,
            Boolean checkMemberlvl, Integer memberlvl) throws Exception {
        
        Collection<Double> clvList = new ArrayList();
        try {
            Double clv;
            Double totalExp = null;
            Integer totalVisit = 0;
            Integer visitThisMonth = 0;
            Double aveExp = null;
            Integer cle = null; //Customer life expectancy
            Collection<MemberEntity> members = getAllMembers();

            if (checkMemberlvl) {
                members = getMembersByMemberLevel(memberlvl, members);
            }

            for (MemberEntity m : members) {
                //check for customer by gender 
                if ((isFemale && m.getGender().equals("Female"))
                        || (isMale && m.getGender().equals("Male"))
                        || (isOthers && m.getGender().equals("Others"))) {
                    cle = m.getMemberlvl().getCle();
                    for (TransactionEntity t : m.getTransactionList()) {
                        //check for furniture, retail , kitchen separetely
                        if ((!isForAllPlace && t.getLocation() == location) || isForAllPlace) {
                            totalExp = totalExp + t.getTotalPrice();
                            totalVisit++;
                            if (t.getGenerateTime().get(Calendar.MONTH) == time.get(Calendar.MONTH)) {
                                visitThisMonth++;
                            }
                        }
                    }
                    aveExp = totalExp / totalVisit;
                    clv = cle * aveExp * visitThisMonth * grossProfitMargin - aveAcquisitionCost;
                    clvList.add(clv);
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getCLV(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return clvList;
    }
//
//    @Override
//    public Float getCACMonthly(Long storeId, Calendar time, Double totalCost) throws Exception {
//        Float cac = null;
//        Integer newMemberQuantity = 0;
//
//        Collection<TransactionEntity> transactionList;
//        StoreEntity store = em.find(StoreEntity.class, storeId);
//        transactionList = store.getTransactions();
//
//        for (TransactionEntity transaction : transactionList) {
//            if (transaction.getMember().getMemberId() != null) {
//                MemberEntity m = em.find(MemberEntity.class, transaction.getMember().getMemberId());
//
//                //if member is new member
//                if (m.getCreateDate().get(Calendar.MONTH) == time.get(Calendar.MONTH)) {
//                    newMemberQuantity++;
//                }
//            }
//        }
//        cac = totalCost.floatValue() / newMemberQuantity.floatValue();
//        return cac;
//    }

    @Override
    public Collection<MemberEntity> getActiveMembers(Long storeId, Calendar startDate, Calendar endDate, Integer location, Boolean isForAllPlace) throws Exception {
        Collection<MemberEntity> members = new ArrayList();
        try {
            Collection<TransactionEntity> transactionList = new ArrayList();
            StoreEntity store = em.find(StoreEntity.class, storeId);
            transactionList = store.getTransactions();

            for (TransactionEntity transaction : transactionList) {
                if (transaction.getMember().getMemberId() != null) {
                    if (removeTime(transaction.getGenerateTime()).after(startDate)
                            && removeTime(transaction.getGenerateTime()).before(endDate)) {
                        MemberEntity m = em.find(MemberEntity.class, transaction.getMember().getMemberId());

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

    public Calendar removeTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

}
