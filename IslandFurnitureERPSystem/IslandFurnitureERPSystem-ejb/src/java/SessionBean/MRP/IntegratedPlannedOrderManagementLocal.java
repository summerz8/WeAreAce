/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

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

    public List<PlannedOrderEntity> getConfirmedPlannedOrder();

    public void createIntegratedPlannedOrder(Calendar targetPeriod, Long factoryRawMaterialId);
    
}
