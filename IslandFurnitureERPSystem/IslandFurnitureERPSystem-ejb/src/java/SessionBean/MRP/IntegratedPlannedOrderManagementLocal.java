/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hangsun
 */
@Local
public interface IntegratedPlannedOrderManagementLocal {

    public List<PlannedOrderEntity> getConfirmedPlannedOrder(Long id,String department);

    public void createIntegratedPlannedOrder(Calendar targetPeriod, Long factoryRawMaterialId,Long id,String department);

    public List<IntegratedPlannedOrderEntity> getIntegratedPlannedOrder(Long id,String department);

    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlan(Long id,String department);

    public void editIntegratedPlannedOrder(Long id, String field, Object content);

    public boolean findFactoryRawMaterialIdList(Long id, String department, Long factoryRawMaterialId);

    public List<FactoryRawMaterialEntity> getFactoryRawMaterial(Long factoryId, String department);
    
}
