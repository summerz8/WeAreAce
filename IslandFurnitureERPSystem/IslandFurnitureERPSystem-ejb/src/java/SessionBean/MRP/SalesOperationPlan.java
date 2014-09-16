/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import Entity.Factory.MRP.SalesOperationPlanEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateful
public class SalesOperationPlan implements SalesOperationPlanLocal {

    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public SalesOperationPlanEntity GenerateSalesOperationPlan(Long productId,
            FactoryProductEntity factoryProductEntity,
            ProductionPlanEntity productionPlan,
            Calendar period,
            List<SalesForecastEntity> salesForecast,
            Double plannedEndMonthInventory,
            Integer workingDay) {

        try {
            SalesOperationPlanEntity salesOperationPlan = new SalesOperationPlanEntity();
            salesOperationPlan.setFactoryProduct(factoryProductEntity);
            salesOperationPlan.setPlannedEndMonthInventory(plannedEndMonthInventory);
            salesOperationPlan.setProductionPlan(productionPlan);
            salesOperationPlan.setTargetPeriod(period);
            salesOperationPlan.setWorkingDay(workingDay);
            salesOperationPlan.setSalesForecast(salesForecast);
            
            em.persist(salesOperationPlan);
            em.flush();
            return salesOperationPlan;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public SalesOperationPlanEntity EditSalesOperationPlanEntity(
            Long Id,
            Long productId,
            FactoryProductEntity factoryProduct,
            ProductionPlanEntity productionPlan,
            Calendar period,
            List<SalesForecastEntity> salesForecast,
            Double plannedEndMonthInventory,
            Integer workingDay) {
        try {
            SalesOperationPlanEntity salesOperationPlan = em.find(SalesOperationPlanEntity.class, Id);
            
            salesOperationPlan.setFactoryProduct(factoryProduct);
            salesOperationPlan.setPlannedEndMonthInventory(plannedEndMonthInventory);
            salesOperationPlan.setProductionPlan(productionPlan);
            salesOperationPlan.setTargetPeriod(period);
            salesOperationPlan.setWorkingDay(workingDay);
            salesOperationPlan.setSalesForecast(salesForecast);
            em.persist(salesOperationPlan);
            em.flush();
            return salesOperationPlan;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

    public List<SalesOperationPlanEntity> ListSalesOperationPlan(Long productId, String FactoryId, Calendar startPeriod, Calendar endPeriod) {
        List<SalesOperationPlanEntity> list = new ArrayList<SalesOperationPlanEntity>();
        List<SalesOperationPlanEntity> templist = new ArrayList<SalesOperationPlanEntity>();
        try{
        Query query = em.createQuery("SELECT t FROM SalesOperationPlanEntity t WHERE t.productId=productId AND t.FactoryId=FactoryId ORDER BY t.period DESC");
        templist = (List<SalesOperationPlanEntity>) query.getResultList();

        while (!templist.isEmpty()) {
            Calendar tempPeriod = templist.get(0).getTargetPeriod();
            if ((removeTime(tempPeriod).compareTo(removeTime(startPeriod)) >= 0)&&(removeTime(tempPeriod).compareTo(removeTime(endPeriod))<=0))
                list.add(templist.get(0));
            templist.remove(0);
        }
        
        return list;
        }   catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    
    public Calendar removeTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
}
