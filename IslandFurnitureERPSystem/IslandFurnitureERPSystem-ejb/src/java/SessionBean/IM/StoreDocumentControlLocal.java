/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.IM;

import Entity.Factory.SCM.DeliveryOrderEntity;
import Entity.Store.IM.StoreGoodReceiptEntity;
import Entity.Store.IM.StoreInStoreMovementRecordEntity;
import Entity.Store.IM.StoreInboundRecordEntity;
import Entity.Store.IM.StoreOutboundRecordEntity;
import Entity.Store.ReturnedItemMovementRecordEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zhengyuan
 */
@Local
public interface StoreDocumentControlLocal {

    public List<StoreGoodReceiptEntity> getStoreGoodReceipt(Long storeId);

    public List<StoreInboundRecordEntity> getStoreInboundRecordEntity(Long storeId);

    public List<StoreInStoreMovementRecordEntity> getStoreInStoreMovementRecord(Long storeId);

    public List<StoreOutboundRecordEntity> getStoreOutboundRecord(Long storeId);

    public List<ReturnedItemMovementRecordEntity> getToProcessReturnIMRE(Long storeId);

    public List<DeliveryOrderEntity> getDeliveryOrder(Long purchaseOrderId);
    
}
