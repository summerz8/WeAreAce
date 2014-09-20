/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.MRP.WeeklyProductionPlanEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hangsun
 */
@Stateless
public class WeeklyProductionPlan implements WeeklyProductionPlanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private EntityManager em;

    @Override
    public List<WeeklyProductionPlanEntity> generateWeeklyProductionPlan(Calendar period, ProductionPlanEntity productionPlan) {
        try {
            List<WeeklyProductionPlanEntity> weeklyProductionPlanList=new ArrayList<WeeklyProductionPlanEntity>();
            
            Calendar cal1 = Calendar.getInstance();
            cal1.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH), 1, 0, 0, 0);
            Calendar cal2 = Calendar.getInstance();
            cal2.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
            Integer daysInMonth = 0;
            do {
                if (cal1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    ++daysInMonth;
                }
                cal1.add(Calendar.DAY_OF_MONTH, 1);

            } while (cal1.getTimeInMillis() < cal2.getTimeInMillis());

            Calendar cal3 = Calendar.getInstance();
            cal3.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH), 1, 0, 0, 0);
            Calendar cal4 = Calendar.getInstance();
            cal4.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
            Integer week = 0;
            if (cal3.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                week = -1;
            }

            do {
                if (cal3.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || (cal3.getTimeInMillis() == cal4.getTimeInMillis() && cal3.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
                    week++;
                }
                cal3.add(Calendar.DAY_OF_MONTH, 1);
            } while (cal3.getTimeInMillis() < cal4.getTimeInMillis());
            int workingDayInWeek = 1;
            Double weeklyDemand;
            Calendar cal5 = Calendar.getInstance();
            cal5.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH), 1, 0, 0, 0);
            Calendar cal6 = Calendar.getInstance();
            cal6.set(period.get(Calendar.YEAR), period.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
            cal6.add(Calendar.DAY_OF_MONTH, -1);
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
                    weeklyProductionPlan.setWeek(a+1);
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
                    weeklyProductionPlan.setWeek(a+1);
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
                    weeklyProductionPlan.setWeek(a+1);
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
                    weeklyProductionPlan.setWeek(a+1);
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
                    weeklyProductionPlan.setWeek(a+1);
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
                    weeklyProductionPlan.setWeek(a+1);
                    weeklyProductionPlan.setWeeklyDemand(weeklyDemand);
                    weeklyProductionPlan.setWorkingDayInMonth(daysInMonth);
                    weeklyProductionPlan.setWorkingDayInWeek(workingDayInWeek);
                    em.persist(weeklyProductionPlan);
                    weeklyProductionPlanList.add(weeklyProductionPlan);
                }
            }
            
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
    public List<WeeklyProductionPlanEntity> getWeeklyProductionPlan(ProductionPlanEntity productionPlan) {
        Query q = em.createQuery("SELECT pp FROM WeeklyProductionPlanEntity pp" );  
        List<WeeklyProductionPlanEntity> tempList= (List<WeeklyProductionPlanEntity>) q.getResultList();
        List<WeeklyProductionPlanEntity> weeklyProductionPlanList = new ArrayList<>();
        Long productionPlanId=productionPlan.getProductionPlanId();
        while(!tempList.isEmpty()){
        if(tempList.get(0).getProductionPlan().getProductionPlanId().equals(productionPlanId))
            weeklyProductionPlanList.add(tempList.get(0));
            tempList.remove(0);
        }
        
        return weeklyProductionPlanList;

    }

}