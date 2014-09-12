/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

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

    public boolean generateProductionPlan(String status, Calendar generateDate, Calendar targetSalesStartDate, Calendar targetSalesEndDate, Integer output, Long productId, String remark);

    public void editProductionPlan(Long productionPlanId, String field, Object content);

    public boolean deleteProductionPlan(Long productionPlanId);

    public List<ArrayList> getProductionPlan();
    
}
