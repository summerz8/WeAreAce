/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
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

    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlan(Long id,String department);

    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanUnconfirmed(Long id,String department);

    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanConfirmed(Long id,String department);

    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlanCancelled(Long id,String department);

    public Long getFactoryRetailProductId(Long integratedSalesForecastId);

    public boolean findIntegratedSalesForecast(Long integratedSalesForecastId);
    
}
