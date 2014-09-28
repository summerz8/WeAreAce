/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.SCM;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Yoky
 */
@Local
public interface RawMaterialInventoryMonitoringModuleLocal {

    public List viewWeeklyRawMaterialInventoryInFlow(long factoryId);
    
    public List viewWeeklyRawMaterialInventoryOutFlow(long factoryId);
    
    public List viewAllWeeklyRawMaterialInventoryInFlow();

    public List viewAllWeeklyRawMaterialInventoryOutFlow();
}
