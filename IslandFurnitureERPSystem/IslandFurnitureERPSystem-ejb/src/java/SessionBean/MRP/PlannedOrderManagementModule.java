/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.BOMEntity;
import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateless
public class PlannedOrderManagementModule implements PlannedOrderManagementModuleLocal, PlannedOrderManagementModuleRemote {

    @PersistenceContext
    private EntityManager em;

    public PlannedOrderManagementModule() {
    }

    @Override
    public List<BOMEntity> CheckBOM(Long ProductID) throws Exception {
        Long productID = ProductID;
        ProductEntity product = em.find(ProductEntity.class, productID);
        if (product == null) {
            throw new Exception("Product is not found!");
        }
        return product.getBom();
    }

    //Click the CreatePlannedOrder button when viewing production plan list
//    @Override
//    public PlannedOrderEntity CreatePlannedOrder(Long factoryProductID, Double amount) {
//        try {
//            FactoryProductEntity factoryProduct = em.find(FactoryProductEntity.class, factoryProductID);
//            List<FactoryRawMaterialAmountEntity> factoryRawMaterialAmountList = new ArrayList<FactoryRawMaterialAmountEntity>();
//            List<BOMEntity> bom = factoryProduct.getProduct().getBOM();
//            FactoryRawMaterialAmountEntity temp = new FactoryRawMaterialAmountEntity();
//            int size = bom.size();
//            Long factoryId = factoryProduct.getFactory().getFactoryId();
//            for (int a = 0; a < size; a++) {
//                FactoryRawMaterialEntity factoryRawMaterial = new FactoryRawMaterialEntity();
//                Collection<FactoryRawMaterialEntity> factoryRawMaterialList;
//                factoryRawMaterialList = bom.get(0).getRawMaterial().getFactoryRawMaterials();
//
//                Iterator iterator = factoryRawMaterialList.iterator();
//                Long tempId = 0L;
//                while (iterator.hasNext() && (tempId != factoryId)) {
//                    Object obj = iterator.next();
//                    factoryRawMaterial = (FactoryRawMaterialEntity) obj;
//                    tempId = factoryRawMaterial.getFactory().getFactoryId();
//                }
//                temp.setFactoryRawMaterial(factoryRawMaterial);
//                temp.setUnit(bom.get(0).getUnit());
//                temp.setAmount(bom.get(0).getAmount() * amount);
//                factoryRawMaterialAmountList.add(temp);
//                bom.remove(0);
//            }
//
//            PlannedOrderEntity plannedOrder = new PlannedOrderEntity();
//            plannedOrder.setFactoryRawMaterialAmountList(factoryRawMaterialAmountList);
//            return plannedOrder;
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return null;
//    }
    //Modify the data in the CreatePlannedOrder page//
//    @Override
//    public PlannedOrderEntity CompletePlannedOrder(Long plannedOrderId,Calendar date,
//            Calendar targetPeriod,
//            String status,
//            Long productionId,
//            List<Long> rawMaterialList,
//            List<Double> RawAmount,
//            List<String> Unit,
//            FactoryEntity factory) {
//
//        try {
//
//            //Create RawMaterialAmount Entity for each material in the plannedOrder//
//            List<FactoryRawMaterialAmountEntity> MaterialList = new ArrayList<FactoryRawMaterialAmountEntity>();
//
//            Long MaterialId;
//            String unit;
//            Double amount;
//
//            while (!rawMaterialList.isEmpty()) {
//                MaterialId = rawMaterialList.get(0);
//                unit = Unit.get(0);
//                amount = RawAmount.get(0);
//                FactoryRawMaterialEntity tempRaw = em.find(FactoryRawMaterialEntity.class, MaterialId);
//                FactoryRawMaterialAmountEntity temp = new FactoryRawMaterialAmountEntity();
//
//                temp.setAmount(amount);
//
//                temp.setFactoryRawMaterial(tempRaw);
//
//                temp.setUnit(unit);
//
//                MaterialList.add(temp);
//
//                rawMaterialList.remove(
//                        0);
//            }
//
//            ProductionPlanEntity productionPlan = em.find(ProductionPlanEntity.class, productionId);
//
//            PlannedOrderEntity order = em.find(PlannedOrderEntity.class, plannedOrderId);
//            order.createPlannedOrder(date, targetPeriod, status, productionPlan, MaterialList, factory);
//            return order;
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
//    @Override
//    public boolean EditPlannedOrder(Long plannedOrderId, Calendar dateInput,
//                    Calendar targetPeriod,
//                    String statusInput,
//                    Long productionIdInput,
//                    List<Long> rawMaterialList,
//                    List<Double> RawAmount,
//                    List<String> Unit) {
//
//        try {
//            PlannedOrderEntity plannedOrder = em.find(PlannedOrderEntity.class, plannedOrderId);
//            plannedOrder.setTargetPeriod(targetPeriod);
//
//            plannedOrder.setGeneratedDate(dateInput);
//
//            plannedOrder.setStatus(statusInput);
//
//            Long MaterialId;
//            String unit;
//            Double amount;
//            List<FactoryRawMaterialAmountEntity> MaterialList = new ArrayList<FactoryRawMaterialAmountEntity>();
//
//            while (!rawMaterialList.isEmpty()) {
//                MaterialId = rawMaterialList.get(0);
//                unit = Unit.get(0);
//                amount = RawAmount.get(0);
//                FactoryRawMaterialEntity tempRaw = em.find(FactoryRawMaterialEntity.class, MaterialId);
//                FactoryRawMaterialAmountEntity temp = new FactoryRawMaterialAmountEntity();
//
//                temp.setAmount(amount);
//                temp.setFactoryRawMaterial(tempRaw);
//                temp.setUnit(unit);
//
//                MaterialList.add(temp);
//                rawMaterialList.remove(0);
//            }
//
//            plannedOrder.setFactoryRawMaterialAmountList(MaterialList);
//
//            return true;
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        return false;
//    }
    @Override
    public void editPlannedOrder(Long id, String field, Object content) throws Exception {
        PlannedOrderEntity plannedOrder = em.find(PlannedOrderEntity.class, id);
        if (plannedOrder == null) {
            throw new Exception("Planned Order is not found!");
        }
        switch (field) {
            case "status":
                String status = (String) content;
                plannedOrder.setStatus(status);
                break;
            case "confirmDate":
                Calendar confirmDate = (Calendar) content;
                plannedOrder.setConfirmDate(confirmDate);
                break;

        }

        em.persist(plannedOrder);
        em.flush();
        em.refresh(plannedOrder);
    }

    @Override
    public boolean DeletePlannedOrder(Long PlannedOrderId) {
        try {
            PlannedOrderEntity plannedOrder = em.find(PlannedOrderEntity.class, PlannedOrderId);
            if (plannedOrder.getStatus()
                    .equals("Unconfirmed")) {
                em.remove(plannedOrder);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<PlannedOrderEntity> getPlannedOrder(Long id, String department) throws Exception {
        Query q1 = em.createQuery("SELECT u FROM UserEntity u WHERE u.department = :department AND u.departmentId = :id");
        q1.setParameter("department", department);
        q1.setParameter("id", id);
        if (q1.getResultList().isEmpty()) {
            throw new Exception("Department is not found!");
        }
        Query q = em.createQuery("SELECT po FROM PlannedOrderEntity po");
        List<PlannedOrderEntity> plannedOrderList = new ArrayList();
        if (department.equals("H")) {
            for (Object o : q.getResultList()) {
                PlannedOrderEntity po = (PlannedOrderEntity) o;
                plannedOrderList.add(po);
            }
        } else {
            for (Object o : q.getResultList()) {
                PlannedOrderEntity po = (PlannedOrderEntity) o;
                Long departmentId = po.getFactory().getFactoryId();
                if (departmentId.equals(id)) {
                    plannedOrderList.add(po);
                }
            }
        }
        return plannedOrderList;
    }

    @Override
    public List<PlannedOrderEntity> getUnconfirmedPlannedOrder(Long id, String department) {
        Query q = em.createQuery("SELECT po FROM PlannedOrderEntity po");
        List<PlannedOrderEntity> plannedOrderList = new ArrayList();
        if (department.equals("H")) {
            for (Object o : q.getResultList()) {
                PlannedOrderEntity po = (PlannedOrderEntity) o;
                if (po.getStatus().equals("unconfirmed")) {
                    plannedOrderList.add(po);
                }
            }
        } else {
            for (Object o : q.getResultList()) {
                PlannedOrderEntity po = (PlannedOrderEntity) o;
                Long departmentId = po.getFactory().getFactoryId();
                if (po.getStatus().equals("unconfirmed") && departmentId.equals(id)) {
                    plannedOrderList.add(po);
                }
            }
        }
        return plannedOrderList;
    }

    @Override
    public List<PlannedOrderEntity> getConfirmedPlannedOrder(Long id, String department) {
        Query q = em.createQuery("SELECT po FROM PlannedOrderEntity po");
        List<PlannedOrderEntity> plannedOrderList = new ArrayList();
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
    public List<PlannedOrderEntity> getCancelledPlannedOrder(Long id, String department) {
        Query q = em.createQuery("SELECT po FROM PlannedOrderEntity po");
        List<PlannedOrderEntity> plannedOrderList = new ArrayList();
        if (department.equals("H")) {
            for (Object o : q.getResultList()) {
                PlannedOrderEntity po = (PlannedOrderEntity) o;
                if (po.getStatus().equals("cancelled")) {
                    plannedOrderList.add(po);
                }
            }
        } else {
            for (Object o : q.getResultList()) {
                PlannedOrderEntity po = (PlannedOrderEntity) o;
                Long departmentId = po.getFactory().getFactoryId();
                if (po.getStatus().equals("cancelled") && departmentId.equals(id)) {
                    plannedOrderList.add(po);
                }
            }
        }
        return plannedOrderList;
    }

    @Override
    public void createPlannedOrder(Long productionPlanId) throws Exception {
        try {
            ProductionPlanEntity productionPlan = em.find(ProductionPlanEntity.class, productionPlanId);
            if (productionPlan == null) {
                throw new Exception("Production Plan is not found!");
            }
            FactoryEntity factory = productionPlan.getFactoryProduct().getFactory();
            Long factoryId = factory.getFactoryId();
            ProductEntity product = productionPlan.getFactoryProduct().getProduct();
            Double quantity = productionPlan.getQuantity();
            Calendar targetPeriod = productionPlan.getTargetPeriod();
            Calendar generatedDate = Calendar.getInstance();

            List<BOMEntity> BOM = product.getBom();
            List<FactoryRawMaterialAmountEntity> factoryRawMaterialAmountList = new ArrayList();

            for (BOMEntity bom : BOM) {
                String unit = bom.getUnit();
                Double BOMamount = bom.getAmount();
                Double amount = quantity * BOMamount;
                Long rawMaterialId = bom.getRawMaterial().getMaterialId();
                FactoryRawMaterialEntity factoryRawMaterial = findFactoryRawMaterial(factoryId, rawMaterialId);
                FactoryRawMaterialAmountEntity factoryRawMaterialAmount = new FactoryRawMaterialAmountEntity();
                factoryRawMaterialAmount.setAmount(amount);
                factoryRawMaterialAmount.setUnit(unit);
                factoryRawMaterialAmount.setFactoryRawMaterial(factoryRawMaterial);
                em.persist(factoryRawMaterialAmount);
                em.flush();
                factoryRawMaterialAmountList.add(factoryRawMaterialAmount);
            }

            PlannedOrderEntity plannedOrder = new PlannedOrderEntity();
            plannedOrder.setFactoryRawMaterialAmountList(factoryRawMaterialAmountList);
            plannedOrder.setStatus("unconfirmed");
            plannedOrder.setTargetPeriod(targetPeriod);
            plannedOrder.setGeneratedDate(generatedDate);
            plannedOrder.setFactory(factory);
            plannedOrder.setProductionPlan(productionPlan);
            em.persist(plannedOrder);
            em.flush();
            productionPlan.setPlannedOrder(plannedOrder);
            em.persist(productionPlan);
            em.flush();

        } catch (Exception ex) {
            if (ex.getMessage().equals("Production Plan is not found!")) {
                throw ex;
            }
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public FactoryRawMaterialEntity findFactoryRawMaterial(Long factoryId, Long materialId) throws Exception {
        FactoryRawMaterialEntity factoryRawMaterial = new FactoryRawMaterialEntity();

        if (em.find(FactoryEntity.class, factoryId) == null) {
            throw new Exception("Factory is not found!");
        }
        if (em.find(RawMaterialEntity.class, materialId) == null) {
            throw new Exception("Raw Material is not found!");
        }

        Query q = em.createQuery("SELECT frm FROM FactoryRawMaterialEntity frm");
        List<FactoryRawMaterialEntity> factoryRawMaterialList = new ArrayList();
        for (Object o : q.getResultList()) {
            FactoryRawMaterialEntity frm = (FactoryRawMaterialEntity) o;
            factoryRawMaterialList.add(frm);
        }
        for (FactoryRawMaterialEntity frm : factoryRawMaterialList) {
            Long FactoryId = frm.getFactory().getFactoryId();
            Long MaterialId = frm.getRawMaterial().getMaterialId();
            if (FactoryId.equals(factoryId) && MaterialId.equals(materialId)) {
                factoryRawMaterial = frm;
                break;
            }
        }
        return factoryRawMaterial;
    }
}
