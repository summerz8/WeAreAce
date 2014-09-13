/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.MRP.WeeklyProductionPlanEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hangsun
 */
@Stateful
public class WeeklyProductionPlan implements WeeklyProductionPlanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private EntityManager em;
    
    public WeeklyProductionPlanEntity generateWeeklyProductionPlan(Long id,String month,Integer week,Integer workingDayInWeek,Integer workingDayInMonth,Integer weeklyDemand){
        try{
            WeeklyProductionPlanEntity weeklyProductionPlan = new WeeklyProductionPlanEntity();
            weeklyProductionPlan.createWeeklyProductionPlan(id, month, week, workingDayInWeek, workingDayInMonth, weeklyDemand);
            em.persist(weeklyProductionPlan);
            em.flush();
            return weeklyProductionPlan;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    } 
    
    public WeeklyProductionPlanEntity editWeeklyProductionPlan(Long id,String month,Integer week,Integer workingDayInWeek,Integer workingDayInMonth,Integer weeklyDemand){
        try{
           WeeklyProductionPlanEntity weeklyProductionPlan = em.find(WeeklyProductionPlanEntity.class,id);
           weeklyProductionPlan.setMonth(month);
           weeklyProductionPlan.setWeek(week);
           weeklyProductionPlan.setWorkingDayInWeek(workingDayInWeek);
           weeklyProductionPlan.setWorkingDayInMonth(workingDayInMonth);
           weeklyProductionPlan.setWeeklyDemand(weeklyDemand);
           em.persist(weeklyProductionPlan);
           em.flush();
           return weeklyProductionPlan;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    public List<ArrayList> getWeeklyProductionPlan(){
        Query q = em.createQuery("SELECT pp FROM weeklyProductionPlan pp");
        List weeklyProductionPlanList = new ArrayList();
        for(Object o : q.getResultList()){
            WeeklyProductionPlanEntity wpp = (WeeklyProductionPlanEntity) o;
            List weeklyProductionPlan = new ArrayList();
            weeklyProductionPlan.add(0,wpp.getId());
            weeklyProductionPlan.add(1,wpp.getMonth());
            weeklyProductionPlan.add(2,wpp.getWeek());
            weeklyProductionPlan.add(3,wpp.getWorkingDayInWeek());
            weeklyProductionPlan.add(4,wpp.getWorkingDayInMonth());
            weeklyProductionPlan.add(5,wpp.getWeeklyDemand());
            
            weeklyProductionPlanList.add(weeklyProductionPlan);
        }
        
        
        return weeklyProductionPlanList;
    
    }
    
}
