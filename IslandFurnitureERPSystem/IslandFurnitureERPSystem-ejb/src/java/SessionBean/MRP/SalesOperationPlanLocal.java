/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.MRP.SalesForecastEntity;
import Entity.Factory.MRP.SalesOperationPlanEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface SalesOperationPlanLocal {

    public SalesOperationPlanEntity GenerateSalesOperationPlan(Long productId,
            FactoryProductEntity factoryProductEntity,
            ProductionPlanEntity productionPlan,
            Calendar period,
            List<SalesForecastEntity> salesForecast,
            Double plannedEndMonthInventory,
            Integer workingDay);

    public SalesOperationPlanEntity EditSalesOperationPlanEntity(
            Long Id,
            Long productId,
            FactoryProductEntity factoryProductEntity,
            ProductionPlanEntity productionPlan,
            Calendar period,
            List<SalesForecastEntity> salesForecast,
            Double plannedEndMonthInventory,
            Integer workingDay);

    public List<SalesOperationPlanEntity> ListSalesOperationPlan(Long productId, String FactoryId, Calendar startPeriod, Calendar endPeriod);

    public Calendar removeTime(Calendar cal);
}
