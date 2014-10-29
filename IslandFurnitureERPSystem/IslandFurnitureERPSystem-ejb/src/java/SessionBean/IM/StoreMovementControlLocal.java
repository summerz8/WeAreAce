/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.IM;

import javax.ejb.Local;

/**
 *
 * @author zhengyuan
 */
@Local
public interface StoreMovementControlLocal {

    public int createInStoreRecord(Long storeId, Long frombinId, Long tobinId, Long productId, Long retailProductId, Double amount);

    public int createInboundRecord();

    public int createOutBoundRecord();
    
}
