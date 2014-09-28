/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hangsun
 */
@Local
public interface RetailProductPurchasePlanModuleLocal {

    public void editRetailProductPurchasePlan(Long id, String field, Object content);

    public boolean deleteRetailProductPurchasePlan(Long productionPlanId);

    public void generateRetailProductPurchasePlan(Long salesForecastId,Long factoryRetailProductId);

    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlan();

    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanUnconfirmed();

    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanConfirmed();

    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanCancelled();

    public Long getFactoryRetailProductId(Long integratedSalesForecastId);
    
}
