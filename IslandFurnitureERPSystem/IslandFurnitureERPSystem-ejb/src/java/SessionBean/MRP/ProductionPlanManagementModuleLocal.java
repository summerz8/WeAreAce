/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author hangsun
 */
@Local
public interface ProductionPlanManagementModuleLocal {

    public boolean generateProductionPlanManagementModule(String status, Date generateDate, Date targetSalesStartDate, Date targetSalesEndDate, Integer output, Long productId, String remark);

    public void editProductionPlan(Long productionPlanId, String field, Object content);

    public boolean deleteProductionPlan(Long productionPlanId);
    
}
