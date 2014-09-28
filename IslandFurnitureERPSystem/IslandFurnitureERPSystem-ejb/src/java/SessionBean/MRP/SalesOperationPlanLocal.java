/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.MRP.IntegratedSalesForecastEntity;
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

    public SalesOperationPlanEntity GenerateSalesOperationPlan(Long factoryProductId,
            Calendar targetPeriod,
            Long integratedSalesForecastId,
            Double plannedEndMonthInventory,
            Integer workingDays,
            Double ProductionPlanQuantity
                                );

    public SalesOperationPlanEntity EditSalesOperationPlanEntity(
            Long Id,
            Double productionPlanQuantity,
            Calendar targetPeriod,
            Double plannedEndMonthInventory,
            Integer workingDay);

    public List<SalesOperationPlanEntity> ListSalesOperationPlan(Long factoryProductId, Calendar startPeriod, Calendar endPeriod);

    public Calendar removeTime(Calendar cal);
    
    public List<FactoryProductEntity> getAllFacotryProduct();
    
    public SalesOperationPlanEntity createSalesOperationPlan(Long targetProductId);
    
    public SalesOperationPlanEntity getSalesOperationPlan(Long salesOperationPlanId);
    
    public SalesOperationPlanEntity confirmSalesOperationPlan(Long salesOperationPlan);
        
    public SalesOperationPlanEntity cancelSalesOperationPlan(Long salesOperationPlan);
    
    public boolean IsThereSalesOperation(Long factoryProductId);
    
    public boolean IsThereForecast(Long factoryProductId);
}
