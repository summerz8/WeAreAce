/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.MRP.SalesOperationPlanEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author apple
 */
@Remote
public interface SalesOperationPlanRemote {

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
    
    public List<FactoryProductEntity> getAllFacotryProduct(Long factoryId);
    
    public SalesOperationPlanEntity createSalesOperationPlan(Long targetProductId);
    
    public SalesOperationPlanEntity getSalesOperationPlan(Long salesOperationPlanId);
    
    public SalesOperationPlanEntity confirmSalesOperationPlan(Long salesOperationPlan) throws Exception;
        
    public SalesOperationPlanEntity cancelSalesOperationPlan(Long salesOperationPlan);
    
    public boolean IsThereSalesOperation(Long factoryProductId) throws Exception;
    
    public boolean IsThereForecast(Long factoryProductId);
}
