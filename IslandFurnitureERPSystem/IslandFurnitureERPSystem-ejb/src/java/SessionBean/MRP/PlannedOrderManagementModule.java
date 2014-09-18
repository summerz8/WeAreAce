/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.BOMEntity;
import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.ProductEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
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
public class PlannedOrderManagementModule implements PlannedOrderManagementModuleLocal {

    @PersistenceContext
    private EntityManager em;

    public PlannedOrderManagementModule() {
    }

    @Override
    public List<BOMEntity> CheckBOM(Long ProductID) {
        Long productID = ProductID;
        ProductEntity product = em.find(ProductEntity.class, productID);
        return product.getBom();
    }

    //Click the CreatePlannedOrder button when viewing production plan list
    @Override
    public PlannedOrderEntity CreatePlannedOrder(Long factoryProductID, Double amount) {
        try {
            FactoryProductEntity factoryProduct = em.find(FactoryProductEntity.class, factoryProductID);
            List<FactoryRawMaterialAmountEntity> factoryRawMaterialAmountList = new ArrayList<FactoryRawMaterialAmountEntity>();
            List<BOMEntity> bom = factoryProduct.getProduct().getBom();
            FactoryRawMaterialAmountEntity temp = new FactoryRawMaterialAmountEntity();
            int size = bom.size();
            Long factoryId = factoryProduct.getFactory().getFactoryId();
            for (int a = 0; a < size; a++) {
                FactoryRawMaterialEntity factoryRawMaterial = new FactoryRawMaterialEntity();
                Collection<FactoryRawMaterialEntity> factoryRawMaterialList;
                factoryRawMaterialList = bom.get(0).getRawMaterial().getFactoryRawMaterials();

                Iterator iterator = factoryRawMaterialList.iterator();
                Long tempId = 0L;
                while (iterator.hasNext() && (tempId != factoryId)) {
                    Object obj = iterator.next();
                    factoryRawMaterial = (FactoryRawMaterialEntity) obj;
                    tempId = factoryRawMaterial.getFactory().getFactoryId();
                }
                temp.setFactoryRawMaterial(factoryRawMaterial);
                temp.setUnit(bom.get(0).getUnit());
                temp.setAmount(bom.get(0).getAmount() * amount);
                factoryRawMaterialAmountList.add(temp);
                bom.remove(0);
            }

            PlannedOrderEntity plannedOrder = new PlannedOrderEntity();
            plannedOrder.setFactoryRawMaterialAmountList(factoryRawMaterialAmountList);
            return plannedOrder;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    //Modify the data in the CreatePlannedOrder page//
    @Override
    public PlannedOrderEntity CompletePlannedOrder(Long plannedOrderId,Calendar date,
            Calendar targetPeriod,
            String status,
            Long productionId,
            List<Long> rawMaterialList,
            List<Double> RawAmount,
            List<String> Unit,
            FactoryEntity factory) {

        try {

            //Create RawMaterialAmount Entity for each material in the plannedOrder//
            List<FactoryRawMaterialAmountEntity> MaterialList = new ArrayList<FactoryRawMaterialAmountEntity>();

            Long MaterialId;
            String unit;
            Double amount;

            while (!rawMaterialList.isEmpty()) {
                MaterialId = rawMaterialList.get(0);
                unit = Unit.get(0);
                amount = RawAmount.get(0);
                FactoryRawMaterialEntity tempRaw = em.find(FactoryRawMaterialEntity.class, MaterialId);
                FactoryRawMaterialAmountEntity temp = new FactoryRawMaterialAmountEntity();

                temp.setAmount(amount);

                temp.setFactoryRawMaterial(tempRaw);

                temp.setUnit(unit);

                MaterialList.add(temp);

                rawMaterialList.remove(
                        0);
            }

            ProductionPlanEntity productionPlan = em.find(ProductionPlanEntity.class, productionId);

            PlannedOrderEntity order = em.find(PlannedOrderEntity.class, plannedOrderId);
            order.createPlannedOrder(date, targetPeriod, status, productionPlan, MaterialList, factory);
            return order;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean EditPlannedOrder(Long plannedOrderId, Calendar dateInput,
                    Calendar targetPeriod,
                    String statusInput,
                    Long productionIdInput,
                    List<Long> rawMaterialList,
                    List<Double> RawAmount,
                    List<String> Unit) {

        try {
            PlannedOrderEntity plannedOrder = em.find(PlannedOrderEntity.class, plannedOrderId);
            plannedOrder.setTargetPeriod(targetPeriod);

            plannedOrder.setGeneratedDate(dateInput);

            plannedOrder.setStatus(statusInput);

            Long MaterialId;
            String unit;
            Double amount;
            List<FactoryRawMaterialAmountEntity> MaterialList = new ArrayList<FactoryRawMaterialAmountEntity>();

            while (!rawMaterialList.isEmpty()) {
                MaterialId = rawMaterialList.get(0);
                unit = Unit.get(0);
                amount = RawAmount.get(0);
                FactoryRawMaterialEntity tempRaw = em.find(FactoryRawMaterialEntity.class, MaterialId);
                FactoryRawMaterialAmountEntity temp = new FactoryRawMaterialAmountEntity();

                temp.setAmount(amount);
                temp.setFactoryRawMaterial(tempRaw);
                temp.setUnit(unit);

                MaterialList.add(temp);
                rawMaterialList.remove(0);
            }

            plannedOrder.setFactoryRawMaterialAmountList(MaterialList);

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
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
    public List<PlannedOrderEntity> getPlannedOrder(){
        Query q = em.createQuery("SELECT po FROM PlannedOrderEntity po");
        List<PlannedOrderEntity> plannedOrderList = new ArrayList();
        for(Object o : q.getResultList()){
            PlannedOrderEntity po = (PlannedOrderEntity) o;
            plannedOrderList.add(po);
            
        }
        
        
        return plannedOrderList;
    }
}
