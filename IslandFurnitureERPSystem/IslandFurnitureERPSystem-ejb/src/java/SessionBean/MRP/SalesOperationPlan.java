/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

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
            String FactoryId,
            Integer productionPlan,
            Calendar period,
            Integer salesForecast,
            Integer plannedEndMonthInventory,
            Integer workingDay) {

        try {
            SalesOperationPlanEntity salesOperationPlan = new SalesOperationPlanEntity(productId, FactoryId, productionPlan, period, salesForecast, plannedEndMonthInventory, workingDay);
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
            String FactoryId,
            Integer productionPlan,
            Calendar period,
            Integer salesForecast,
            Integer plannedEndMonthInventory,
            Integer workingDay) {
        try {
            SalesOperationPlanEntity salesOperationPlan = em.find(SalesOperationPlanEntity.class, Id);
            salesOperationPlan.EditOperationPlanEntity(productId, FactoryId, productionPlan, period, salesForecast, plannedEndMonthInventory, workingDay);
            em.persist(salesOperationPlan);
            em.flush();
            return salesOperationPlan;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    
    

//    public List<SalesOperationPlanEntity> ListSalesOperationPlan(Long productId, String FactoryId, Calendar startPeriod, Calendar endPeriod){
//        List<SalesOperationPlanEntity> list=new ArrayList<SalesOperationPlanEntity>();
//        List<SalesOperationPlanEntity> templist=new ArrayList<SalesOperationPlanEntity>();
//        Query query = em.createQuery("SELECT t FROM SalesOperationPlanEntity t WHERE t.productId=productId AND t.FactoryId=FactoryId ORDER BY t.period DESC");
//        templist = (List<SalesOperationPlanEntity>) query.getResultList();
//        if(templist)                                                         
//    
//    
//    
//    }

}
}
