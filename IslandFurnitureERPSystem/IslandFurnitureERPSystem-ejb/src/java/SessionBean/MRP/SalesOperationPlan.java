/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.InventoryRecordEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
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

    public SalesOperationPlanEntity GenerateSalesOperationPlan(FactoryProductEntity factoryProduct,
            Calendar targetPeriod,
            IntegratedSalesForecastEntity integratedSalesForecast,
            Double plannedEndMonthInventory
    ) {

        try {
            SalesOperationPlanEntity salesOperationPlan = new SalesOperationPlanEntity();
            salesOperationPlan.setFactoryProduct(factoryProduct);
            salesOperationPlan.setPlannedEndMonthInventory(plannedEndMonthInventory);
            salesOperationPlan.setTargetPeriod(targetPeriod);
            salesOperationPlan.setIntegratedSalesForecast(integratedSalesForecast);

            Calendar cal1 = Calendar.getInstance();
            cal1.set(targetPeriod.get(Calendar.YEAR), targetPeriod.get(Calendar.MONTH), 1, 0, 0, 0);
            Calendar cal2 = Calendar.getInstance();
            cal2.set(targetPeriod.get(Calendar.YEAR), targetPeriod.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
            Integer workDays = 0;
            do {
                if (cal1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    ++workDays;
                }
                cal1.add(Calendar.DAY_OF_MONTH, 1);

            } while (cal1.getTimeInMillis() < cal2.getTimeInMillis());

            salesOperationPlan.setWorkingDay(workDays);

            List<InventoryRecordEntity> inventoryList = factoryProduct.getRecord();
            int size = inventoryList.size();
            Double lastMonthInventory=inventoryList.get(size - 1).getAmount();
            Double productionPlanAmount=plannedEndMonthInventory-lastMonthInventory+integratedSalesForecast.getAmount();
            
            Calendar generatedDate=Calendar.getInstance();
            ProductionPlanEntity productionPlan=new ProductionPlanEntity("Unconfirmed", generatedDate,targetPeriod,productionPlanAmount,  factoryProduct,"");
            salesOperationPlan.setProductionPlan(productionPlan);
            
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
            FactoryProductEntity factoryProduct,
            ProductionPlanEntity productionPlan,
            Calendar targetPeriod,
            IntegratedSalesForecastEntity integratedSalesForecast,
            Double plannedEndMonthInventory,
            Integer workingDay) {
        try {
            SalesOperationPlanEntity salesOperationPlan = em.find(SalesOperationPlanEntity.class, Id);

            salesOperationPlan.setFactoryProduct(factoryProduct);
            salesOperationPlan.setPlannedEndMonthInventory(plannedEndMonthInventory);
            salesOperationPlan.setProductionPlan(productionPlan);
            salesOperationPlan.setTargetPeriod(targetPeriod);
            salesOperationPlan.setWorkingDay(workingDay);
            salesOperationPlan.setIntegratedSalesForecast(integratedSalesForecast);
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
        try {
            Query query = em.createQuery("SELECT t FROM SalesOperationPlanEntity t WHERE t.productId=productId AND t.FactoryId=FactoryId ORDER BY t.period DESC");
            templist = (List<SalesOperationPlanEntity>) query.getResultList();

            while (!templist.isEmpty()) {
                Calendar tempPeriod = templist.get(0).getTargetPeriod();
                if ((removeTime(tempPeriod).compareTo(removeTime(startPeriod)) >= 0) && (removeTime(tempPeriod).compareTo(removeTime(endPeriod)) <= 0)) {
                    list.add(templist.get(0));
                }
                templist.remove(0);
            }

            return list;
        } catch (Exception ex) {
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
