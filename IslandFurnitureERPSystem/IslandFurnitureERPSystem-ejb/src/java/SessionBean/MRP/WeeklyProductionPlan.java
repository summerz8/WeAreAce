/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.MRP.WeeklyProductionPlanEntity;
import Entity.Factory.ProductEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hangsun
 */
@Stateless
public class WeeklyProductionPlan implements WeeklyProductionPlanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WeeklyProductionPlanEntity> generateWeeklyProductionPlan(Long productionPlanId) {
        try {
            System.out.println("generateWeeklyProductionPlan:()  1");
            ProductionPlanEntity productionPlan = em.find(ProductionPlanEntity.class, productionPlanId);
            Calendar period = productionPlan.getTargetPeriod();
            List<WeeklyProductionPlanEntity> weeklyProductionPlanList = new ArrayList<WeeklyProductionPlanEntity>();

            Calendar cal1 = Calendar.getInstance();
            cal1.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH), 1, 0, 0, 0);
            Calendar cal2 = Calendar.getInstance();
            cal2.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
            Integer daysInMonth = 0;
            System.out.println("generateWeeklyProductionPlan:()  2");
            do {
                if (cal1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    ++daysInMonth;
                }
                cal1.add(Calendar.DAY_OF_MONTH, 1);

                System.out.println("generateWeeklyProductionPlan:()  3");
            } while (cal1.getTimeInMillis() < cal2.getTimeInMillis());

            Calendar cal3 = Calendar.getInstance();
            cal3.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH), 1, 0, 0, 0);
            Calendar cal4 = Calendar.getInstance();
            cal4.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
            Integer week = 0;
            if (cal3.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                week = -1;
            }
            System.out.println("generateWeeklyProductionPlan:()  4");
            do {
                if (cal3.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || (cal3.getTimeInMillis() == cal4.getTimeInMillis() && cal3.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
                    week++;
                }
                cal3.add(Calendar.DAY_OF_MONTH, 1);
            } while (cal3.getTimeInMillis() <= cal4.getTimeInMillis());
            int workingDayInWeek = 1;
            Double weeklyDemand;
            Calendar cal5 = Calendar.getInstance();
            cal5.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH), 1, 0, 0, 0);
            Calendar cal6 = Calendar.getInstance();
            cal6.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
            cal6.add(Calendar.DAY_OF_MONTH, -1);
            System.out.println("generateWeeklyProductionPlan:()  5");
            for (int a = 0; a < week; a++) {
                if (a == 0) {
                    if (cal5.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                        workingDayInWeek = 5;
                    } else if (cal5.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                        workingDayInWeek = 4;
                    } else if (cal5.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                        workingDayInWeek = 3;
                    } else if (cal5.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                        workingDayInWeek = 2;
                    } else if (cal5.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                        workingDayInWeek = 1;
                    }
                    weeklyDemand = productionPlan.getQuantity() / daysInMonth * workingDayInWeek;
                    WeeklyProductionPlanEntity weeklyProductionPlan = new WeeklyProductionPlanEntity();
                    weeklyProductionPlan.setProductionMonth(period);
                    weeklyProductionPlan.setProductionPlan(productionPlan);
                    weeklyProductionPlan.setWeek(a + 1);
                    weeklyProductionPlan.setWeeklyDemand(weeklyDemand);
                    weeklyProductionPlan.setWorkingDayInMonth(daysInMonth);
                    weeklyProductionPlan.setWorkingDayInWeek(workingDayInWeek);
                    em.persist(weeklyProductionPlan);
                    weeklyProductionPlanList.add(weeklyProductionPlan);
                } else if (a == 1) {
                    workingDayInWeek = 5;
                    weeklyDemand = productionPlan.getQuantity() / daysInMonth * workingDayInWeek;
                    WeeklyProductionPlanEntity weeklyProductionPlan = new WeeklyProductionPlanEntity();
                    weeklyProductionPlan.setProductionMonth(period);
                    weeklyProductionPlan.setProductionPlan(productionPlan);
                    weeklyProductionPlan.setWeek(a + 1);
                    weeklyProductionPlan.setWeeklyDemand(weeklyDemand);
                    weeklyProductionPlan.setWorkingDayInMonth(daysInMonth);
                    weeklyProductionPlan.setWorkingDayInWeek(workingDayInWeek);
                    em.persist(weeklyProductionPlan);
                    weeklyProductionPlanList.add(weeklyProductionPlan);
                } else if (a == 2) {
                    workingDayInWeek = 5;
                    weeklyDemand = productionPlan.getQuantity() / daysInMonth * workingDayInWeek;
                    WeeklyProductionPlanEntity weeklyProductionPlan = new WeeklyProductionPlanEntity();
                    weeklyProductionPlan.setProductionMonth(period);
                    weeklyProductionPlan.setProductionPlan(productionPlan);
                    weeklyProductionPlan.setWeek(a + 1);
                    weeklyProductionPlan.setWeeklyDemand(weeklyDemand);
                    weeklyProductionPlan.setWorkingDayInMonth(daysInMonth);
                    weeklyProductionPlan.setWorkingDayInWeek(workingDayInWeek);
                    em.persist(weeklyProductionPlan);
                    weeklyProductionPlanList.add(weeklyProductionPlan);
                } else if (a == 3 && week == 4) {
                    if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                        workingDayInWeek = 1;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                        workingDayInWeek = 2;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                        workingDayInWeek = 3;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                        workingDayInWeek = 4;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                        workingDayInWeek = 5;
                    }
                    weeklyDemand = productionPlan.getQuantity() / daysInMonth * workingDayInWeek;
                    WeeklyProductionPlanEntity weeklyProductionPlan = new WeeklyProductionPlanEntity();
                    weeklyProductionPlan.setProductionMonth(period);
                    weeklyProductionPlan.setProductionPlan(productionPlan);
                    weeklyProductionPlan.setWeek(a + 1);
                    weeklyProductionPlan.setWeeklyDemand(weeklyDemand);
                    weeklyProductionPlan.setWorkingDayInMonth(daysInMonth);
                    weeklyProductionPlan.setWorkingDayInWeek(workingDayInWeek);
                    em.persist(weeklyProductionPlan);
                    weeklyProductionPlanList.add(weeklyProductionPlan);
                } else if (a == 3 && week == 5) {
                    workingDayInWeek = 5;
                    weeklyDemand = productionPlan.getQuantity() / daysInMonth * workingDayInWeek;
                    WeeklyProductionPlanEntity weeklyProductionPlan = new WeeklyProductionPlanEntity();
                    weeklyProductionPlan.setProductionMonth(period);
                    weeklyProductionPlan.setProductionPlan(productionPlan);
                    weeklyProductionPlan.setWeek(a + 1);
                    weeklyProductionPlan.setWeeklyDemand(weeklyDemand);
                    weeklyProductionPlan.setWorkingDayInMonth(daysInMonth);
                    weeklyProductionPlan.setWorkingDayInWeek(workingDayInWeek);
                    em.persist(weeklyProductionPlan);
                    weeklyProductionPlanList.add(weeklyProductionPlan);
                } else if (a == 4 && week == 5) {
                    if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                        workingDayInWeek = 1;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                        workingDayInWeek = 2;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                        workingDayInWeek = 3;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                        workingDayInWeek = 4;
                    } else if (cal6.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                        workingDayInWeek = 5;
                    }
                    weeklyDemand = productionPlan.getQuantity() / daysInMonth * workingDayInWeek;
                    WeeklyProductionPlanEntity weeklyProductionPlan = new WeeklyProductionPlanEntity();
                    weeklyProductionPlan.setProductionMonth(period);
                    weeklyProductionPlan.setProductionPlan(productionPlan);
                    weeklyProductionPlan.setWeek(a + 1);
                    weeklyProductionPlan.setWeeklyDemand(weeklyDemand);
                    weeklyProductionPlan.setWorkingDayInMonth(daysInMonth);
                    weeklyProductionPlan.setWorkingDayInWeek(workingDayInWeek);
                    em.persist(weeklyProductionPlan);
                    weeklyProductionPlanList.add(weeklyProductionPlan);
                }
            }
            System.out.println("generateWeeklyProductionPlan:()  5");
            em.flush();
            productionPlan.setWeeklyProductionPlanEntity(weeklyProductionPlanList);
            em.flush();
            return weeklyProductionPlanList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public WeeklyProductionPlanEntity editWeeklyProductionPlan(Long id, Calendar month, Integer week, Integer workingDayInWeek, Integer workingDayInMonth, Double weeklyDemand) {
        try {
            WeeklyProductionPlanEntity weeklyProductionPlan = em.find(WeeklyProductionPlanEntity.class, id);
            weeklyProductionPlan.setProductionMonth(month);
            weeklyProductionPlan.setWeek(week);
            weeklyProductionPlan.setWorkingDayInWeek(workingDayInWeek);
            weeklyProductionPlan.setWorkingDayInMonth(workingDayInMonth);
            weeklyProductionPlan.setWeeklyDemand(weeklyDemand);
            em.persist(weeklyProductionPlan);
            em.flush();
            return weeklyProductionPlan;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public List<WeeklyProductionPlanEntity> getWeeklyProductionPlan(Long productionPlanId) {
        ProductionPlanEntity productionPlan = em.find(ProductionPlanEntity.class, productionPlanId);
        Query q = em.createQuery("SELECT pp FROM WeeklyProductionPlanEntity pp");
        List<WeeklyProductionPlanEntity> tempList = (List<WeeklyProductionPlanEntity>) q.getResultList();
        List<WeeklyProductionPlanEntity> weeklyProductionPlanList = new ArrayList<>();
        while (!tempList.isEmpty()) {
            if (tempList.get(0).getProductionPlan().getProductionPlanId().equals(productionPlanId)) {
                weeklyProductionPlanList.add(tempList.get(0));
            }
            tempList.remove(0);
        }

        return weeklyProductionPlanList;

    }

    @Override
    public ProductEntity getProduct(Long factoryProductId) {

        FactoryProductEntity factoryProduct = em.find(FactoryProductEntity.class, factoryProductId);

        return factoryProduct.getProduct();
    }

    @Override
    public String isProduct(Long factoryProductId) {
//        try{

        FactoryProductEntity factoryProduct = em.find(FactoryProductEntity.class, factoryProductId);
        if (factoryProduct != null) {
            System.out.println("return yes");
            return "yes";
        } else {
            return "no";
        }
//        }catch (Exception ex){
//            System.out.println("return no");
//        return "no";
//        }
    }

    @Override
    public void Edit(Long id, String field, Object content) {
        WeeklyProductionPlanEntity weeklyProductionPlan = em.find(WeeklyProductionPlanEntity.class, id);
        switch (field) {
            case "demand":
                Double demand = (Double) content;
                weeklyProductionPlan.setWeeklyDemand(demand);
        }
        em.flush();
    }

    @Override
    public List<FactoryProductEntity> getFactoryProductList(Long factoryId) {
        List<FactoryProductEntity> factoryProductList=new ArrayList<>();
        
        Query q = em.createQuery("SELECT pp FROM FactoryProductEntity pp");
        List<FactoryProductEntity> tempList = (List<FactoryProductEntity>) q.getResultList();
        for(FactoryProductEntity p: tempList){
            if(p.getFactory().getFactoryId().equals(factoryId)){
                System.out.println("aaaa");
                factoryProductList.add(p);
            }
        }
        
        return factoryProductList;
    }

}
