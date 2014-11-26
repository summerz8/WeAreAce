/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.IM;

import Entity.Factory.SCM.OutboundMovementEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zhengyuan
 */
@Local
public interface StoreMovementControlLocal {



    public List<OutboundMovementEntity> viewIncomingGoodsFromFactory(Long storeId);

    public List<PurchaseOrderEntity> viewIncomingGoodsFromSupplier(Long storeId);

    public Long convertProductFToS(Long fproductId, Long storeId);

    public Long convertRProductFToS(Long fRproductId, Long storeId);

    public void fromFactoryGoodReceipts(Long outboungMovementId, Long inventoryId, Integer invType, Double quantity, Long storeId);

    public int inboundMovement(Long storeId, Long storeBinId, Integer invType,  Long storeProductId, Double quantity, Integer status);

    public int inStoreMovement(Long storeId, Integer productType, Long inId, Integer oldStatus, Integer newStatus, Long oldBId, Long newBId, Double quantity);

    public int generateGoodReceiptMannually(Long storeId, Integer invType, Long ivId, Double quantity);

    public int fromSupplierGoodReceipt(Long poId, Long storeId, Integer invType, Long inventoryId, Long deliveryOrderId, Double quantity);

    public Integer handleReturnedProductFromStore(Long recordId, Long storeBinId);

    public Integer sendOutboundMovement(Long storeId, Integer invtype, Long storeBinId, Long inventoryId, Double quantity);
    
}
