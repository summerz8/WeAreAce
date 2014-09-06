/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author hangsun
 */
@Remote
public interface ProductionPlanManagementModuleRemote {

    public void generateProductionPlanManagementModule(String status, Date generateDate, Date targetSalesStartDate, Date targetSalesEndDate, Integer output, Long productId, String remark);

    public void editProductionPlan(Long productionPlanId, String field, Object content);
    
}