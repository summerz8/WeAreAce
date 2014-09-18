/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.BOMEntity;
import Entity.Factory.FactoryEntity;
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

    public List<BOMEntity> CheckBOM(Long ProductID);

    public PlannedOrderEntity CreatePlannedOrder(Long productID, Double amount);

    public PlannedOrderEntity CompletePlannedOrder(Long plannedOrderId, Calendar dateInput, Calendar targetDate, String statusInput, Long productionIdInput, List<Long> rawMaterialList, List<Double> RawAmount, List<String> Unit,FactoryEntity factory);

    public boolean EditPlannedOrder(Long plannedOrderId, Calendar dateInput, Calendar targetDate, String statusInput, Long productionIdInput, List<Long> rawMaterialList, List<Double> RawAmount, List<String> Unit);

    public boolean DeletePlannedOrder(Long PlannedOrderId);

    public List<PlannedOrderEntity> getPlannedOrder();
    
}
