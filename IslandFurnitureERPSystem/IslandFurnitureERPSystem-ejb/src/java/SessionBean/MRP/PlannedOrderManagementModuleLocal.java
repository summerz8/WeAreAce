/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.BOMEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hangsun
 */
@Local
public interface PlannedOrderManagementModuleLocal {

    public BOMEntity CheckBOM(Long ProductID);

    public void CreatePlannedOrder(Long productID, Integer amount);

    public Boolean GeneratePlannedOrder(Date dateInput, Date targetStartInput, Date targetEndInput, String statusInput, Long productionIdInput, List<Long> rawMaterialList, List<Integer> RawAmount, List<String> Unit);

    public boolean EditPlannedOrder(Long plannedOrderId, Date dateInput, Date targetStartInput, Date targetEndInput, String statusInput, Long productionIdInput, List<Long> rawMaterialList, List<Integer> RawAmount, List<String> Unit);

    public boolean DeletePlannedOrder(Long PlannedOrderId);
    
}
