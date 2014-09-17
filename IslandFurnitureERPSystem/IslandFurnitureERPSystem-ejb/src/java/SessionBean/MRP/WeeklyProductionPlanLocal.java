/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.MRP.WeeklyProductionPlanEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hangsun
 */
@Local
public interface WeeklyProductionPlanLocal {

    public WeeklyProductionPlanEntity generateWeeklyProductionPlan(Long id, String month, Integer week, Integer workingDayInWeek, Integer workingDayInMonth, Double weeklyDemand);

    public WeeklyProductionPlanEntity editWeeklyProductionPlan(Long id, String month, Integer week, Integer workingDayInWeek, Integer workingDayInMonth, Double weeklyDemand);

    public List<ArrayList> getWeeklyProductionPlan();
    
}
