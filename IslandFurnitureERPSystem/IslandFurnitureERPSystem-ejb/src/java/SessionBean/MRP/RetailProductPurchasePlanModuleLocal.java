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

    public boolean generateRetailProductPurchasePlan(Long factoryRetailproductId, Calendar targetPeriod, Double amount);

    public List<IntegratedPlannedOrderEntity> getRetailProductPurchasePlan();
    
}
