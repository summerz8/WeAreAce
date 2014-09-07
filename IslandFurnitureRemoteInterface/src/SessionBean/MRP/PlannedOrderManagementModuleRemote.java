/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.MRP;

import Entity.Factory.BOMEntity;
import javax.ejb.Remote;

/**
 *
 * @author apple
 */
@Remote
public interface PlannedOrderManagementModuleRemote {

    public BOMEntity CheckBOM(Long ProductID);
    
}
