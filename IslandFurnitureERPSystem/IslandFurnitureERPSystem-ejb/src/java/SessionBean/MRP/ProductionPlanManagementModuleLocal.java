/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hangsun
 */
@Local
public interface ProductionPlanManagementModuleLocal {

    public boolean generateProductionPlan(String status, Calendar generateDate, Calendar targetPeriod, Double output, Long productId, String remark);

    public void editProductionPlan(Long productionPlanId, String field, Object content);

    public boolean deleteProductionPlan(Long productionPlanId);

    public List<ProductionPlanEntity> getProductionPlanUnconfirmed();

    public ProductionPlanEntity searchProductionPlan(Long id);

    public List<ProductionPlanEntity> getProductionPlanCancelled();

    public List<ProductionPlanEntity> getProductionPlanConfirmed();
    
}
