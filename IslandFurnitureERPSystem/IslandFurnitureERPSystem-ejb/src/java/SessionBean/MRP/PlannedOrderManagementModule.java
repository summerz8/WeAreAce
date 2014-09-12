/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.BOMEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialAmountEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public BOMEntity CheckBOM(Long ProductID) {
        Long productID = ProductID;
        ProductEntity product = em.find(ProductEntity.class, productID);
        return product.getBom();
    }

    //Click the CreatePlannedOrder button when viewing production plan list
    @Override
    public PlannedOrderEntity CreatePlannedOrder(Long productID, Integer amount) {
                ProductEntity product=em.find(ProductEntity.class,productID);
        List<RawMaterialAmountEntity> rawMaterial=new ArrayList<RawMaterialAmountEntity>();
        BOMEntity bom=product.getBom();
        rawMaterial=bom.getRawmaterialList();
        int size=rawMaterial.size();
        for(int a=0;a<size;a++)rawMaterial.get(a).setAmount(amount*rawMaterial.get(a).getAmount());
        PlannedOrderEntity plannedOrder=new PlannedOrderEntity();
        plannedOrder.setRawMaterialAmount(rawMaterial);
        return plannedOrder;
    }

    //Modify the data in the CreatePlannedOrder page//
    @Override
    public PlannedOrderEntity GeneratePlannedOrder(Calendar dateInput,
            Calendar targetStartInput,
            Calendar targetEndInput,
            String statusInput,
            Long productionIdInput,
            List<Long> rawMaterialList,
            List<Integer> RawAmount,
            List<String> Unit) {
        
        try{

            //Create RawMaterialAmount Entity for each material in the plannedOrder//
        Calendar date = dateInput;
        Calendar targetStart = targetStartInput;
        Calendar targetEnd = targetEndInput;
        String status = statusInput;
        List<RawMaterialAmountEntity> MaterialList = new ArrayList<RawMaterialAmountEntity>();

        Long MaterialId;
        String unit;
        Integer amount;

        while (!rawMaterialList.isEmpty()) {
            MaterialId = rawMaterialList.get(0);
            unit = Unit.get(0);
            amount = RawAmount.get(0);
            RawMaterialAmountEntity temp = new RawMaterialAmountEntity();

            temp.setAmount(amount);
            temp.setRawMaterialId(MaterialId);
            temp.setUnit(unit);

            MaterialList.add(temp);
            rawMaterialList.remove(0);
        }

        ProductionPlanEntity productionPlan = em.find(ProductionPlanEntity.class, productionIdInput);

        PlannedOrderEntity order = null;
        order.createPlannedOrder(date, targetStart, targetEnd, status, productionPlan, MaterialList);
        return order;
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
        return null;
    }
    
    @Override
    public boolean EditPlannedOrder(Long plannedOrderId,Calendar dateInput,
            Calendar targetStartInput,
            Calendar targetEndInput,
            String statusInput,
            Long productionIdInput,
            List<Long> rawMaterialList,
            List<Integer> RawAmount,
            List<String> Unit){
        
        try{
        PlannedOrderEntity plannedOrder=em.find(PlannedOrderEntity.class,plannedOrderId);
        plannedOrder.setDate(dateInput);
        plannedOrder.setTargetSalesEndDate(targetStartInput);
        plannedOrder.setTargetSalesEndDate(targetStartInput);
        plannedOrder.setStatus(statusInput);
        
        Long MaterialId;
        String unit;
        Integer amount;
        List<RawMaterialAmountEntity> MaterialList = new ArrayList<RawMaterialAmountEntity>();
        while (!rawMaterialList.isEmpty()) {
            MaterialId = rawMaterialList.get(0);
            unit = Unit.get(0);
            amount = RawAmount.get(0);
            RawMaterialAmountEntity temp = new RawMaterialAmountEntity();

            temp.setAmount(amount);
            temp.setRawMaterialId(MaterialId);
            temp.setUnit(unit);

            MaterialList.add(temp);
            rawMaterialList.remove(0);
        }
        
        plannedOrder.setRawMaterialAmount(MaterialList);
        return true;
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
        return false;
    }
    
    @Override
    public boolean DeletePlannedOrder(Long PlannedOrderId){
        try{
            PlannedOrderEntity plannedOrder=em.find(PlannedOrderEntity.class,PlannedOrderId);
            if(plannedOrder.getStatus().equals("Unconfirmed")) {
                em.remove(plannedOrder);
                return true;
            }
            else return false;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
 }
    
    
