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

import Entity.Store.ACRM.CLVEntity;
import Entity.Store.ACRM.RFMEntity;
import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.TransactionEntity;
import Entity.Store.StoreEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
                if (m.getCountry() != null && m.getCountry().equals(country)) {
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
    public Double getRetentionRate(Long storeId, Calendar time, Integer location,
            Boolean isForAllPlace, Boolean isMonthly, Boolean isYearly) throws Exception {
        Double rr = null;
        time = Calendar.getInstance();
        System.out.println("SessionBean: getRetentionRate():");

        try {
            Collection<MemberEntity> lastPeriodActiveCustomers;
            Collection<MemberEntity> currentPeriodActiveCustomers;
            if (isMonthly) {
                System.out.println("isMonthly: ");

                Calendar lastMonth = Calendar.getInstance();
                lastMonth.set(time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH));
                lastMonth.add(Calendar.MONTH, -1);

                Calendar lastMonth2 = Calendar.getInstance();
                lastMonth2.set(time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH));
                lastMonth2.add(Calendar.MONTH, -2);

                lastPeriodActiveCustomers = getActiveMembers(storeId, lastMonth2, lastMonth, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembers(storeId, lastMonth, time, location, isForAllPlace);

            } else if (isYearly) {//isQuarterly
                System.out.println("isYearly: ");

                Calendar lastYear = Calendar.getInstance();
                lastYear.set(time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH));
                lastYear.add(Calendar.YEAR, -1);

                Calendar lastYear2 = Calendar.getInstance();
                lastYear2.set(time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH));
                lastYear2.add(Calendar.YEAR, -2);

                lastPeriodActiveCustomers = getActiveMembers(storeId, lastYear2, lastYear, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembers(storeId, lastYear, time, location, isForAllPlace);

            } else {
                System.out.println("isQuaterly: ");

                Calendar lastQuarter = Calendar.getInstance();
                lastQuarter.set(time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH));
                lastQuarter.add(Calendar.MONTH, -3);

                Calendar lastQuarter2 = Calendar.getInstance();
                lastQuarter2.set(time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH));
                lastQuarter2.add(Calendar.MONTH, -6);

                lastPeriodActiveCustomers = getActiveMembers(storeId, lastQuarter2, lastQuarter, location, isForAllPlace);
                currentPeriodActiveCustomers = getActiveMembers(storeId, lastQuarter, time, location, isForAllPlace);

            }
            System.out.println("lastPeriodActiveCustomers: " + lastPeriodActiveCustomers.toString());
            System.out.println("currentPeriodActiveCustomers: " + currentPeriodActiveCustomers.toString());
            Integer oldQuantity = lastPeriodActiveCustomers.size();
            Integer currentQuantity = currentPeriodActiveCustomers.size();

            System.out.println("oldQuantity: " + oldQuantity.toString());
            System.out.println("currentQuantity: " + currentQuantity.toString());

            rr = (currentQuantity) * 1.0 / (oldQuantity);

            if (currentQuantity == 0) {
                rr = 0.0;
            }
            if (oldQuantity == 0) {
                rr = -1.0;
            }

            System.out.println("Retention Rate: " + rr.toString());
            System.out.println(".............Leaving AnalyticalCRMSessionBean:getRetentionRate()............");

        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getRetentionRate(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return rr;
    }

    @Override
    public Collection<RFMEntity> getAllMembersRFM() {
        System.out.println("AnalyticalCRMSessionBean: getAllMembersRFM()");

        Collection<RFMEntity> RFMList = new ArrayList();
        Collection<MemberEntity> members;
        try {
            members = getAllMembers();
            System.out.println("AnalyticalCRMSessionBean: getAllMembersRFM():  members = getAllMembers();");

            for (MemberEntity m : members) {
                RFMEntity rfm = getMemberRFM(m.getMemberId());
                RFMList.add(rfm);
            }

        } catch (Exception e) {
            System.out.println("Caught an unexpected exception!");
            e.printStackTrace();

        }
        System.out.println("Retunr RFMList");
        return RFMList;
    }

    public RFMEntity getMemberRFM(Long memberId) {
        System.out.println("AnalyticalCRMSessionBean: getMemberRFM()");
        RFMEntity rfm = null;
        MemberEntity member;
        Integer recency = 0;
        Integer frequency = 0;
        Double monetary = null;
        try {
            member = em.find(MemberEntity.class, memberId);
            System.out.println("AnalyticalCRMSessionBean: getMemberRFM(): get member");

            recency = getRecency(memberId);
            System.out.println("AnalyticalCRMSessionBean: getMemberRFM(): Recency =  " + recency);

            frequency = getFrequency(memberId);
            System.out.println("AnalyticalCRMSessionBean: getMemberRFM(): frequency =  " + frequency);

            monetary = getMonetary(memberId);
            System.out.println("AnalyticalCRMSessionBean: getMemberRFM(): monetary =  " + monetary);

            if (member.getRfm() == null) {
                rfm = new RFMEntity(recency, frequency, monetary, member);
                member.setRfm(rfm);
                em.persist(rfm);
            } else {
                rfm = member.getRfm();
                rfm.setRecency(recency);
                rfm.setFrequency(frequency);
                rfm.setMonetary(monetary);

                member.setRfm(rfm);
            }
            em.flush();
        } catch (Exception e) {
            System.out.println("Caught an unexpected exception!");
            e.printStackTrace();
        }
        System.out.println("return rfm");
        return rfm;
    }

    @Override
    public Integer getRecency(Long memberId) {
        MemberEntity member;
        Integer recency = 0;

        try {
            member = em.find(MemberEntity.class, memberId);
            Calendar today = Calendar.getInstance();
            if (member.getLastTransaction() != null) {
                recency = daysBetween(today.getTime(), member.getLastTransaction().getGenerateTime().getTime());
            }
        } catch (Exception e) {
            System.out.println("Caught an unexpected exception!");
            e.printStackTrace();
        }
        return recency;
    }

    @Override
    public Integer getFrequency(Long memberId) {
        MemberEntity member;
        Integer frequency = 0;
        try {
            member = em.find(MemberEntity.class, memberId);
            Calendar today = Calendar.getInstance();
            for (TransactionEntity t : member.getTransactionList()) {
                Integer daysBetween = daysBetween(today.getTime(), t.getGenerateTime().getTime());
                if (daysBetween < 366) {
                    frequency++;
                }
            }
        } catch (Exception e) {
            System.out.println("Caught an unexpected exception!");
            e.printStackTrace();
        }
        return frequency;
    }

    @Override
    public Double getMonetary(Long memberId) {
        MemberEntity member;
        Double monetary = 0.0;
        try {
            member = em.find(MemberEntity.class, memberId);
            for (TransactionEntity t : member.getTransactionList()) {
                monetary = t.getTotalPrice();
            }
        } catch (Exception e) {
            System.out.println("Caught an unexpected exception!");
            e.printStackTrace();
        }
        return monetary;
    }

    @Override
    public Collection<CLVEntity> getCLV(Long storeId, Calendar time,
            Double grossProfitMargin, Double aveAcquisitionCost, Integer location,
            Boolean isForAllPlace, Boolean isFemale, Boolean isMale, Boolean isOthers,
            Boolean checkMemberlvl, Integer memberlvl) throws Exception {
        System.out.println("AnalyticalCRMSessionBean: getCLV: ");
        Collection<CLVEntity> clvList = new ArrayList();
        try {
            CLVEntity clvEntity;
            Double clv = 0.0;
            Double totalExp = 0.0;
            Integer totalVisit = 0;
            Integer visitThisMonth = 0;
            Double aveExp = 0.0;
            Integer cle = 0; //Customer life expectancy
            Collection<MemberEntity> members = getAllMembers();

            if (checkMemberlvl) {
                members = getMembersByMemberLevel(memberlvl, members);
            }

            for (MemberEntity m : members) {
                //check for customer by gender 
                if ((isFemale && m.getGender().equals("Female"))
                        || (isMale && m.getGender().equals("Male"))
                        || (isOthers && m.getGender().equals("Others"))
                        || (!isFemale && !isMale && !isOthers)) {
                    cle = m.getMemberlvl().getCle();
//                    System.out.println("CLE: " + cle);
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
                    if (totalVisit == 0 || totalExp == 0.0) {
                        aveExp = 0.0;
                        clv = 1000.0;
                    } else {
                        aveExp = totalExp / totalVisit;
//                        System.out.println("AveExp: " + aveExp);
//                        System.out.println("visitThisMonth: " + visitThisMonth);
//                        System.out.println("grossProfitMargin: " + grossProfitMargin);
                        clv = cle * aveExp * visitThisMonth * grossProfitMargin - aveAcquisitionCost;
                        if (clv < 0) {
                            clv = 0.0;
                        }
                    }
//                    System.err.println("CLV: " + clv);
                    if (m.getClv() == null) {
                        clvEntity = new CLVEntity(visitThisMonth, aveExp, clv, m);
                        m.setClv(clvEntity);
                        em.persist(clvEntity);
                    } else {
                        clvEntity = m.getClv();
                        clvEntity.setAveExp(aveExp);
                        clvEntity.setVisitThisMonth(visitThisMonth);
                        clvEntity.setClv(clv);
                        m.setClv(clvEntity);
                    }
                    em.flush();
                    clvList.add(clvEntity);
                }
            }
        } catch (Exception e) {
            System.err.println("AnalyticalCRMSessionBean: getCLV(): Caught an unexpected exception.");
            e.printStackTrace();
        }
        return clvList;
    }

    @Override
    public Collection<MemberEntity> getActiveMembers(Long storeId, Calendar startDate, Calendar endDate, Integer location, Boolean isForAllPlace) throws Exception {
        Collection<MemberEntity> members = new ArrayList();
        try {
            System.out.println("Sessionbean: getActiveMembers()");
            Collection<TransactionEntity> transactionList;
            StoreEntity store = em.find(StoreEntity.class, storeId);
            transactionList = store.getTransactions();

            System.out.println("Transactions: " + transactionList.toString());
            for (TransactionEntity transaction : transactionList) {
                System.out.println("For Each transaction: Transaction = " + transaction.toString());
                if (transaction.getMember() != null) {
//                    System.out.println("Not Null Check: ");
//
//                    System.out.println("Transaction Date: " + transaction.getGenerateTime().getTime().toString());
//                    System.out.println("Start Date: " + startDate.getTime().toString());
//                    System.out.println("End Date: " + endDate.getTime().toString());

                    if (removeTime(transaction.getGenerateTime()).after(startDate)
                            && removeTime(transaction.getGenerateTime()).before(endDate)) {
                        System.out.println("Date Check: ");
                        MemberEntity m = em.find(MemberEntity.class, transaction.getMember().getMemberId());
                        System.out.println("Member: MemberId = " + m.getMemberId());

                        if (isForAllPlace) {
                            members.add(m);
                        } else if (transaction.getLocation() == location) {
                            System.out.println("Location is : " + location);
                            //calculate saperately for retail, funiture and marketplace
                            members.add(m);
                        }
                    }
                }
            }

            System.out.println("Members: " + members.toString());
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

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
    }

}
