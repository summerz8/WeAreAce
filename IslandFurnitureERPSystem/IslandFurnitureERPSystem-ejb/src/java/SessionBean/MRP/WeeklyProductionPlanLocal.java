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
import javax.ejb.Local;

/**
 *
 * @author hangsun
 */
@Local
public interface WeeklyProductionPlanLocal {

    public List<WeeklyProductionPlanEntity> generateWeeklyProductionPlan(Long productionPlanId) throws Exception;

    public WeeklyProductionPlanEntity editWeeklyProductionPlan(Long id, Calendar month, Integer week, Integer workingDayInWeek, Integer workingDayInMonth, Double weeklyDemand);

    public List<WeeklyProductionPlanEntity> getWeeklyProductionPlan(Long productionPlanId) throws Exception;

    public ProductEntity getProduct(Long factoryProductId) throws Exception;

    public String isProduct(Long factoryProductId);
  
    public void Edit(Long id, String field, Object content);
    
    public List<FactoryProductEntity> getFactoryProductList(Long factoryId);

}
