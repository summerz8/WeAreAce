/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.CommonInfrastructure.IdNumberEntity;
import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.BOMEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RawMaterialAmountEntity;
import Entity.Factory.RawMaterialEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author apple
 */
@Stateful
public class PlannedOrderManagementModule implements PlannedOrderManagementModuleRemote {

    @PersistenceContext
    private EntityManager em;

    public PlannedOrderManagementModule() {
    }

    public BOMEntity CheckBOM(Long ProductID) {
        Long productID = ProductID;
        ProductEntity product = em.find(ProductEntity.class, productID);
        return product.getBom();
    }

    //Click the CreatePlannedOrder button when viewing production plan list
    public void CreatePlannedOrder(Long productID, Integer amount) {

    }

    //Modify the data in the CreatePlannedOrder page//
    public Boolean GeneratePlannedOrder(Date dateInput,
            Date targetStartInput,
            Date targetEndInput,
            String statusInput,
            Long productionIdInput,
            List<Long> rawMaterialList,
            List<Integer> RawAmount,
            List<String> Unit) {

            //Create RawMaterialAmount Entity for each material in the plannedOrder//
        Date date = dateInput;
        Date targetStart = targetStartInput;
        Date targetEnd = targetEndInput;
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
        return true;
    }

    @Override
    public void GeneratePlannedOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
