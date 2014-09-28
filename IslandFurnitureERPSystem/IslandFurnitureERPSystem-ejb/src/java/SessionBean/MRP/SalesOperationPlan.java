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
import Entity.Factory.MRP.SalesOperationPlanEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateful
public class SalesOperationPlan implements SalesOperationPlanLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public SalesOperationPlanEntity GenerateSalesOperationPlan(Long factoryProductId,
            Calendar targetPeriod,
            Long integratedSalesForecastId,
            Double plannedEndMonthInventory,
            Integer workingDays,
            Double ProductionPlanQuantity) {

        try {

            FactoryProductEntity fpe = em.find(FactoryProductEntity.class, factoryProductId);
            IntegratedSalesForecastEntity isf = em.find(IntegratedSalesForecastEntity.class, integratedSalesForecastId);

            System.out.println("GenerateSalesOperationPlan(): 1");
            SalesOperationPlanEntity salesOperationPlan = new SalesOperationPlanEntity();
            salesOperationPlan.setFactoryProduct(fpe);
            salesOperationPlan.setPlannedEndMonthInventory(plannedEndMonthInventory);
            salesOperationPlan.setTargetPeriod(targetPeriod);
            salesOperationPlan.setIntegratedSalesForecast(isf);
            salesOperationPlan.setWorkingDay(workingDays);
            salesOperationPlan.setStatus("Unconfirmed");
            salesOperationPlan.setPlannedProductionPlanQuantity(ProductionPlanQuantity);
            System.out.println("GenerateSalesOperationPlan(): 2");

            em.persist(salesOperationPlan);
            em.flush();

            System.out.println("GenerateSalesOperationPlan(): 5");
            return salesOperationPlan;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public SalesOperationPlanEntity EditSalesOperationPlanEntity(
            Long Id,
            Double productionPlanQuantity,
            Calendar targetPeriod,
            Double plannedEndMonthInventory,
            Integer workingDay) {
        try {
            SalesOperationPlanEntity salesOperationPlan = em.find(SalesOperationPlanEntity.class, Id);

            salesOperationPlan.setPlannedEndMonthInventory(plannedEndMonthInventory);
            salesOperationPlan.setPlannedProductionPlanQuantity(productionPlanQuantity);
            salesOperationPlan.setTargetPeriod(targetPeriod);
            salesOperationPlan.setWorkingDay(workingDay);
            em.persist(salesOperationPlan);
            em.flush();
            return salesOperationPlan;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

    @Override
    public List<SalesOperationPlanEntity> ListSalesOperationPlan(Long factoryProductId, Calendar startPeriod, Calendar endPeriod) {
        List<SalesOperationPlanEntity> list = new ArrayList<>();
        List<SalesOperationPlanEntity> templist = new ArrayList<>();
        List<SalesOperationPlanEntity> templist1 = new ArrayList<>();

        try {
            if (startPeriod != null) {
                if (factoryProductId == null) {
                    Query query = em.createQuery("SELECT t FROM SalesOperationPlanEntity t ORDER BY t.targetPeriod DESC");
                    templist1 = (List<SalesOperationPlanEntity>) query.getResultList();
                    while (templist1.isEmpty()) {
                        if (templist1.get(0).getStatus().equals("Cancelled")) {
                            templist.add(templist1.get(0));
                        }
                        templist1.remove(0);
                    }

                    while (!templist.isEmpty()) {
                        Calendar tempPeriod = templist.get(0).getTargetPeriod();
                        if ((removeTime(tempPeriod).compareTo(removeTime(startPeriod)) >= 0) && (removeTime(tempPeriod).compareTo(removeTime(endPeriod)) <= 0)) {
                            list.add(templist.get(0));
                        }
                        templist.remove(0);
                    }

                    return list;
                } else {

                    Query query = em.createQuery("SELECT t FROM SalesOperationPlanEntity t ORDER BY t.targetPeriod DESC");
                    templist = (List<SalesOperationPlanEntity>) query.getResultList();
                    int temp = templist.size();
                    int a = 0;
                    while (a < temp) {
                        if (templist.get(a).getFactoryProduct().getFactoryProductId().equals(factoryProductId)) {
                            a++;
                        } else {
                            templist.remove(a);
                            temp--;
                        }

                    }
                    while (!templist.isEmpty()) {
                        Calendar tempPeriod = templist.get(0).getTargetPeriod();
                        if ((removeTime(tempPeriod).compareTo(removeTime(startPeriod)) >= 0) && (removeTime(tempPeriod).compareTo(removeTime(endPeriod)) <= 0)) {
                            list.add(templist.get(0));
                        }
                        templist.remove(0);
                    }
                    return list;
                }
            } else {
                System.out.println("1");
                if (factoryProductId == null) {

                    Query query = em.createQuery("SELECT t FROM SalesOperationPlanEntity t ORDER BY t.targetPeriod DESC");
                    templist = (List<SalesOperationPlanEntity>) query.getResultList();

                    return templist;

                } else {

                    System.out.println("2");
                    Query query = em.createQuery("SELECT t FROM SalesOperationPlanEntity t ORDER BY t.targetPeriod DESC");
                    templist = (List<SalesOperationPlanEntity>) query.getResultList();

                    System.out.println("333");
                    while (!templist.isEmpty()) {
                        if (templist.get(0).getFactoryProduct().getFactoryProductId().equals(factoryProductId)) {
                            list.add(templist.get(0));
                            templist.remove(0);
                        } else {
                            templist.remove(0);
                        }
                    }
                    if (list.isEmpty()) {
                        System.out.println("4");
                    }
                    for (SalesOperationPlanEntity s : list) {
                        System.out.println(s.toString());
                    }
                    return list;
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<FactoryProductEntity> getAllFacotryProduct() {
        Query query = em.createQuery("SELECT t FROM FactoryProductEntity t");
        return (List<FactoryProductEntity>) query.getResultList();

    }

    @Override
    public SalesOperationPlanEntity createSalesOperationPlan(Long targetProductId) {

        SalesOperationPlanEntity salesOperationPlan = new SalesOperationPlanEntity();
        FactoryProductEntity factoryProduct = em.find(FactoryProductEntity.class, targetProductId);

        System.out.println("1");
        Query q = em.createQuery("SELECT s FROM SalesOperationPlanEntity s ORDER BY s.targetPeriod DESC");
        System.out.println("2");
        Double inventory;
        List<SalesOperationPlanEntity> tempSalesList = (List<SalesOperationPlanEntity>) q.getResultList();
        if (tempSalesList.isEmpty()) {
            inventory = 0D;
        } else {
            List<SalesOperationPlanEntity> List = new ArrayList<>();
            while (!tempSalesList.isEmpty()) {
                if (tempSalesList.get(0).getFactoryProduct().equals(factoryProduct)) {
                    System.out.println("SalesOperationPlan calendar: " + tempSalesList.get(0).getTargetPeriod().getTime());
                    tempSalesList.get(0).getTargetPeriod().get(Calendar.MILLISECOND);
                    tempSalesList.get(0).getTargetPeriod().set(Calendar.MILLISECOND, 0);
                    System.out.println("SalesOperationPlan calendar: " + tempSalesList.get(0).getTargetPeriod().getTime());
                    List.add(tempSalesList.get(0));
                }
                tempSalesList.remove(0);
            }
            if (List.isEmpty()) {
                inventory = 0D;
            } else {
                inventory = List.get(0).getPlannedEndMonthInventory();
            }

        }
        Calendar targetPeriod = Calendar.getInstance();
        targetPeriod.set(targetPeriod.get(Calendar.YEAR), targetPeriod.get(Calendar.MONTH), 1, 0, 0, 0);
        targetPeriod.add(Calendar.MONTH, 2);

        System.out.println("3");
        System.out.println(targetPeriod.getTime());
        Query qq = em.createQuery("SELECT i FROM IntegratedSalesForecastEntity i ORDER BY i.targetPeriod DESC");
        List<IntegratedSalesForecastEntity> tempSalesList1 = (List<IntegratedSalesForecastEntity>) qq.getResultList();
        List<IntegratedSalesForecastEntity> List1 = new ArrayList<>();
        while (!tempSalesList1.isEmpty()) {
            if ((tempSalesList1.get(0).getFactoryProduct() != null) && (tempSalesList1.get(0).getFactoryProduct().equals(factoryProduct))) {
                System.out.println(tempSalesList1.get(0).getTargetPeriod().getTime());
                if ((tempSalesList1.get(0).getTargetPeriod().get(Calendar.MONTH) == (targetPeriod.get(Calendar.MONTH))) && (tempSalesList1.get(0).getTargetPeriod().get(Calendar.YEAR) == (targetPeriod.get(Calendar.YEAR)))) {
                    List1.add(tempSalesList1.get(0));
                    System.out.println("3.1");
                }
            }
            tempSalesList1.remove(0);
        }
        System.out.println("4");

        IntegratedSalesForecastEntity integratedSalesForecastEntity = List1.get(0);

        targetPeriod.add(Calendar.MONTH, -1);

        System.out.println("5");
        Calendar cal1 = Calendar.getInstance();
        cal1.set(targetPeriod.get(Calendar.YEAR), targetPeriod.get(Calendar.MONTH), 1, 0, 0, 0);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(targetPeriod.get(Calendar.YEAR), targetPeriod.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
        System.out.println("6");
        Integer workDays = 0;
        do {
            if (cal1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
            cal1.add(Calendar.DAY_OF_MONTH, 1);

        } while (cal1.getTimeInMillis() < cal2.getTimeInMillis());

        salesOperationPlan.setTargetPeriod(targetPeriod);
        salesOperationPlan.setFactoryProduct(factoryProduct);
        salesOperationPlan.setPlannedEndMonthInventory(inventory);
        salesOperationPlan.setIntegratedSalesForecast(integratedSalesForecastEntity);
        salesOperationPlan.setWorkingDay(workDays);
        System.out.println(salesOperationPlan.getWorkingDay() + salesOperationPlan.getPlannedEndMonthInventory());
        System.out.println("7");
        return salesOperationPlan;
    }

    @Override
    public SalesOperationPlanEntity getSalesOperationPlan(Long salesOperationPlanId) {

        return em.find(SalesOperationPlanEntity.class, salesOperationPlanId);
    }

    @Override
    public SalesOperationPlanEntity confirmSalesOperationPlan(Long salesOperationPlanId) {

        SalesOperationPlanEntity salesOperationPlan = em.find(SalesOperationPlanEntity.class, salesOperationPlanId);

        Calendar generatedDate = Calendar.getInstance();
        Calendar targetPeriod = salesOperationPlan.getTargetPeriod();
        Double productionPlanQuantity = salesOperationPlan.getPlannedProductionPlanQuantity();
        FactoryProductEntity factoryProduct = salesOperationPlan.getFactoryProduct();
        ProductionPlanEntity productionPlan = new ProductionPlanEntity("Unconfirmed", generatedDate, targetPeriod, productionPlanQuantity, factoryProduct, "");

        em.persist(productionPlan);
        em.flush();
        salesOperationPlan.setStatus("Confirmed");
        salesOperationPlan.setProductionPlan(productionPlan);
        em.persist(salesOperationPlan);
        em.flush();
        return salesOperationPlan;
    }

    @Override
    public SalesOperationPlanEntity cancelSalesOperationPlan(Long salesOperationPlanId) {

        SalesOperationPlanEntity salesOperationPlan = em.find(SalesOperationPlanEntity.class, salesOperationPlanId);

        salesOperationPlan.setStatus("Cancelled");

        em.persist(salesOperationPlan);
        em.flush();
        return salesOperationPlan;
    }

    @Override
    public boolean IsThereSalesOperation(Long factoryProductId) {

        Query query = em.createQuery("SELECT t FROM SalesOperationPlanEntity t ORDER BY t.targetPeriod DESC");
        List<SalesOperationPlanEntity> templist = (List<SalesOperationPlanEntity>) query.getResultList();
        Calendar targetPeriod = Calendar.getInstance();
        targetPeriod.add(Calendar.MONTH, 1);
        for (SalesOperationPlanEntity s : templist) {
            if (s.getStatus().equals("Confirmed") && s.getFactoryProduct().getFactoryProductId().equals(factoryProductId) && s.getTargetPeriod().get(Calendar.MONTH) == targetPeriod.get(Calendar.MONTH) && (s.getTargetPeriod().get(Calendar.YEAR) == targetPeriod.get(Calendar.YEAR))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean IsThereForecast(Long factoryProductId) {
        Query query = em.createQuery("SELECT t FROM IntegratedSalesForecastEntity t ORDER BY t.targetPeriod DESC");
        List<IntegratedSalesForecastEntity> templist = (List<IntegratedSalesForecastEntity>) query.getResultList();
        Calendar targetPeriod = Calendar.getInstance();
        targetPeriod.add(Calendar.MONTH, 2);
        for (IntegratedSalesForecastEntity s : templist) {
            if (s.getFactoryProduct() != null) {
                if (s.getFactoryProduct().getFactoryProductId().equals(factoryProductId) && s.getTargetPeriod().get(Calendar.MONTH) == targetPeriod.get(Calendar.MONTH) && (s.getTargetPeriod().get(Calendar.YEAR) == targetPeriod.get(Calendar.YEAR))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Calendar removeTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
}
