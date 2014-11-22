/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.OCRM;

import Entity.Store.OCRM.PickupListEntity;
import Entity.Store.OCRM.TransactionItemEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dan
 */
@Local
public interface PickupListModuleLocal {
     public List<PickupListEntity> getPickupList();
     
     public List<PickupListEntity> getFinishedList();
     
     public Object[][] listItems(Long pickupListId);
     
     public int markFinish(Long pickupListId);
     
     public int markDistributed(Long pickupListId);
    
}
