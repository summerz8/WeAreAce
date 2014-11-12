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

    public void editProductionPlan(Long productionPlanId, String field, Object content) throws Exception;

    public boolean deleteProductionPlan(Long productionPlanId);

    public List<ProductionPlanEntity> getProductionPlanUnconfirmed(Long id,String department) throws Exception;

    public ProductionPlanEntity searchProductionPlan(Long id) throws Exception;

    public List<ProductionPlanEntity> getProductionPlanCancelled(Long id,String department);

    public List<ProductionPlanEntity> getProductionPlanConfirmed(Long id,String departement);

    
}
