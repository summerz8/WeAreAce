/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.BOMEntity;
import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hangsun
 */
@Local
public interface PlannedOrderManagementModuleLocal {

    public List<BOMEntity> CheckBOM(Long ProductID)throws Exception;

//    public PlannedOrderEntity CreatePlannedOrder(Long productID, Double amount);

//    public PlannedOrderEntity CompletePlannedOrder(Long plannedOrderId, Calendar dateInput, Calendar targetDate, String statusInput, Long productionIdInput, List<Long> rawMaterialList, List<Double> RawAmount, List<String> Unit,FactoryEntity factory);

//    public boolean EditPlannedOrder(Long plannedOrderId, Calendar dateInput, Calendar targetDate, String statusInput, Long productionIdInput, List<Long> rawMaterialList, List<Double> RawAmount, List<String> Unit);

    public boolean DeletePlannedOrder(Long PlannedOrderId);

    public List<PlannedOrderEntity> getPlannedOrder(Long id,String department) throws Exception;

    public List<PlannedOrderEntity> getUnconfirmedPlannedOrder(Long id,String department);

    public List<PlannedOrderEntity> getConfirmedPlannedOrder(Long id,String department);

    public List<PlannedOrderEntity> getCancelledPlannedOrder(Long id,String department);

    public void editPlannedOrder(Long id, String field, Object content) throws Exception;

    public void createPlannedOrder(Long factoryProductID) throws Exception;

    public FactoryRawMaterialEntity findFactoryRawMaterial(Long factoryId, Long materialId) throws Exception;
    
}
