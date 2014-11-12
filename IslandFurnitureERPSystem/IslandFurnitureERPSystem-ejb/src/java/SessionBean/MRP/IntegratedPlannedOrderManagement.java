/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
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
public class IntegratedPlannedOrderManagement implements IntegratedPlannedOrderManagementLocal, IntegratedPlannedOrderManagementRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    @Override
    public void createIntegratedPlannedOrder(Calendar targetPeriod, Long factoryRawMaterialId, Long id, String department) throws Exception {
        List<PlannedOrderEntity> plannedOrderConfirmed = getConfirmedPlannedOrder(id, department);
        System.out.println("factoryRawMaterialId" + factoryRawMaterialId + " " + targetPeriod.toString());
        FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, factoryRawMaterialId);
        if (factoryRawMaterial == null) {
            throw new Exception("Factory Raw Material is not found!");
        }

        IntegratedPlannedOrderEntity integratedPlannedOrder = new IntegratedPlannedOrderEntity();
        FactoryRawMaterialAmountEntity factoryRawMaterialAmount = new FactoryRawMaterialAmountEntity();

        factoryRawMaterialAmount.setUnit(factoryRawMaterial.getUnit());
        factoryRawMaterialAmount.setFactoryRawMaterial(factoryRawMaterial);
        em.persist(factoryRawMaterialAmount);
        em.flush();

        Calendar generateDate = Calendar.getInstance();
        Calendar targetperiod = targetPeriod;
        String status = "waiting";
        FactoryEntity factory = factoryRawMaterial.getFactory();
        List<PlannedOrderEntity> po = new ArrayList();
        Double amount = 0D;
        int targetMonth = targetPeriod.get(Calendar.MONTH) + 1;
        int targetYear = targetPeriod.get(Calendar.YEAR);

        for (PlannedOrderEntity confirmedPO : plannedOrderConfirmed) {
            Calendar confirmedPOTargetPeriod = confirmedPO.getTargetPeriod();
            int confirmedTargetPeriodMonth = confirmedPOTargetPeriod.get(Calendar.MONTH) + 1;
            int confirmedTargetPeriodYear = confirmedPOTargetPeriod.get(Calendar.YEAR);

            System.out.println("xxx" + targetMonth + "yyy" + confirmedTargetPeriodMonth);

            if (targetMonth == confirmedTargetPeriodMonth && targetYear == confirmedTargetPeriodYear) {

                po.add(confirmedPO);

                List<FactoryRawMaterialAmountEntity> factoryRawMaterialAmountList = confirmedPO.getFactoryRawMaterialAmountList();

                for (FactoryRawMaterialAmountEntity fam : factoryRawMaterialAmountList) {
                    System.out.println("!@#!@$@!" + fam.getAmount());
                    System.out.println("SHSHSHSHS" + factoryRawMaterialId);
                    System.out.println("!@#!$!@$" + fam.getFactoryRawMaterial().getFactoryRawMaterialId());
                    if (fam.getFactoryRawMaterial().getFactoryRawMaterialId().equals(factoryRawMaterialId)) {
                        System.out.println(fam.getAmount() + "QWEQEWEQEEWEQWEW");
                        amount += fam.getAmount();
                        System.out.println("Amount to be stored: " + amount);
                    }//if rawMaterialId 
                }//for factoryRawMaterial
            }//if targetPeriodMonth           
        }//for confirmedPO

        factoryRawMaterialAmount.setAmount(amount);
        em.persist(factoryRawMaterialAmount);
        em.flush();

        integratedPlannedOrder.setFactoryRawMaterialAmount(factoryRawMaterialAmount);
        integratedPlannedOrder.setGeneratedDate(generateDate);
        integratedPlannedOrder.setStatus(status);
        integratedPlannedOrder.setTargetPeriod(targetperiod);
        integratedPlannedOrder.setPlannedOrderList(po);
        integratedPlannedOrder.setFactory(factory);

        em.persist(integratedPlannedOrder);

        factory.getIntegratedPlannedOrders().add(integratedPlannedOrder);
        em.flush();
        factory.getIntegratedPlannedOrders().add(integratedPlannedOrder);
        em.flush();

    }

    @Override
    public List<PlannedOrderEntity> getConfirmedPlannedOrder(Long id, String department) throws Exception {
        Query q = em.createQuery("SELECT po FROM PlannedOrderEntity po");
        List<PlannedOrderEntity> plannedOrderList = new ArrayList();

        Query q1 = em.createQuery("SELECT u FROM UserEntity u WHERE u.department = :department AND u.departmentId = :id");
        q1.setParameter("department", department);
        q1.setParameter("id", id);
        if (q1.getResultList().isEmpty()) {
            throw new Exception("Department is not found!");
        }

        if (department.equals("H")) {
            for (Object o : q.getResultList()) {
                PlannedOrderEntity po = (PlannedOrderEntity) o;
                if (po.getStatus().equals("confirmed")) {
                    plannedOrderList.add(po);
                }
            }
        } else {
            for (Object o : q.getResultList()) {
                PlannedOrderEntity po = (PlannedOrderEntity) o;
                Long departmentId = po.getFactory().getFactoryId();
                if (po.getStatus().equals("confirmed") && departmentId.equals(id)) {
                    plannedOrderList.add(po);
                }
            }
        }

        return plannedOrderList;
    }

    @Override
    public List<IntegratedPlannedOrderEntity> getIntegratedPlannedOrder(Long id, String department) throws Exception {
        Query q = em.createQuery("SELECT ipo FROM IntegratedPlannedOrderEntity ipo");
        List<IntegratedPlannedOrderEntity> integratedPlannedOrderList = new ArrayList();

        Query q1 = em.createQuery("SELECT u FROM UserEntity u WHERE u.department = :department AND u.departmentId = :id");
        q1.setParameter("department", department);
        q1.setParameter("id", id);
        if (q1.getResultList().isEmpty()) {
            throw new Exception("Department is not found!");
        }

        if (department.equals("H")) {
            for (Object o : q.getResultList()) {
                IntegratedPlannedOrderEntity ipo = (IntegratedPlannedOrderEntity) o;
                if (ipo.getFactoryRawMaterialAmount() != null) {
                    integratedPlannedOrderList.add(ipo);
                }
            }
        } else {
            for (Object o : q.getResultList()) {
                IntegratedPlannedOrderEntity ipo = (IntegratedPlannedOrderEntity) o;
                Long departmentId = ipo.getFactory().getFactoryId();
                if (ipo.getFactoryRawMaterialAmount() != null && departmentId.equals(id)) {
                    integratedPlannedOrderList.add(ipo);
                }
            }
        }

        return integratedPlannedOrderList;
    }

    @Override
    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlan(Long id, String department) throws Exception {
        Query q = em.createQuery("SELECT ipo FROM IntegratedPlannedOrderEntity ipo");
        List<IntegratedPlannedOrderEntity> retailProductPurchasePlanList = new ArrayList();

        Query q1 = em.createQuery("SELECT u FROM UserEntity u WHERE u.department = :department AND u.departmentId = :id");
        q1.setParameter("department", department);
        q1.setParameter("id", id);
        if (q1.getResultList().isEmpty()) {
            throw new Exception("Department is not found!");
        }

        if (department.equals("H")) {
            for (Object o : q.getResultList()) {
                IntegratedPlannedOrderEntity ipo = (IntegratedPlannedOrderEntity) o;
                if (ipo.getFactoryRetailProductAmount() != null) {
                    retailProductPurchasePlanList.add(ipo);
                }
            }
        } else {
            for (Object o : q.getResultList()) {
                IntegratedPlannedOrderEntity ipo = (IntegratedPlannedOrderEntity) o;
                Long departmentId = ipo.getFactory().getFactoryId();
                if (ipo.getFactoryRetailProductAmount() != null && departmentId.equals(id)) {
                    retailProductPurchasePlanList.add(ipo);
                }
            }

        }

        return retailProductPurchasePlanList;
    }

    @Override
    public void editIntegratedPlannedOrder(Long id, String field, Object content) throws Exception {

        IntegratedPlannedOrderEntity integratedPlannedOrder = em.find(IntegratedPlannedOrderEntity.class, id);

        if (integratedPlannedOrder == null) {
            throw new Exception("Integrated Planned Order is not found!");
        }
        switch (field) {
            case "targetPeriod":
                Calendar targetPeriod = (Calendar) content;
                integratedPlannedOrder.setTargetPeriod(targetPeriod);
                break;
            case "amount":
                Double amount = (Double) content;
                integratedPlannedOrder.getFactoryRetailProductAmount().setAmount(amount);
                break;
            case "status":
                String status = (String) content;
                integratedPlannedOrder.setStatus(status);
                break;
        }
        em.persist(integratedPlannedOrder);
        em.flush();
    }

    @Override
    public boolean findFactoryRawMaterialIdList(Long id, String department, Long factoryRawMaterialId) {
        try {
            List<PlannedOrderEntity> plannedOrderList = getConfirmedPlannedOrder(id, department);
            boolean flag = Boolean.FALSE;
            for (PlannedOrderEntity po : plannedOrderList) {
                List<FactoryRawMaterialAmountEntity> factoryRawMaterialAmountList = po.getFactoryRawMaterialAmountList();
                for (FactoryRawMaterialAmountEntity frma : factoryRawMaterialAmountList) {
                    Long factoryRawMaterialid = frma.getFactoryRawMaterial().getFactoryRawMaterialId();
                    if (factoryRawMaterialid.equals(factoryRawMaterialId)) {
                        flag = Boolean.TRUE;
                        return flag;
                    }//if        
                }

            }
            return flag;
        } catch (Exception ex) {

            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            return false;
        }
    }

    public List<FactoryRawMaterialEntity> getFactoryRawMaterial(Long factoryId, String department) throws Exception {
        Query q = em.createQuery("SELECT frm FROM FactoryRawMaterialEntity frm");
        List<FactoryRawMaterialEntity> factoryRawMaterialList = new ArrayList();

        if (department.equals("H")) {
            if (em.find(FactoryEntity.class, factoryId) == null) {
                    throw new Exception("Department is not found!");
                }
            for (Object o : q.getResultList()) {
                FactoryRawMaterialEntity frm = (FactoryRawMaterialEntity) o;
                if (!frm.getIsDeleted()) {
                    factoryRawMaterialList.add(frm);
                }
            }
        } else {
            for (Object o : q.getResultList()) {
                if (em.find(FactoryEntity.class, factoryId) == null) {
                    throw new Exception("Department is not found!");
                }
                FactoryRawMaterialEntity frm = (FactoryRawMaterialEntity) o;
                if (!frm.getIsDeleted() && frm.getFactory().getFactoryId().equals(factoryId)) {
                    factoryRawMaterialList.add(frm);
                }
            }
        }
        return factoryRawMaterialList;
    }
}
