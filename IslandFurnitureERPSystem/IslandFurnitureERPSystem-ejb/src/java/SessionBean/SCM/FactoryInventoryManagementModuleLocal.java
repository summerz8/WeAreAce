/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.Factory.SCM.GoodsReceiptEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface FactoryInventoryManagementModuleLocal {

    public List listStorageBinInformation(Long factoryId);

    public Long recordInboundMovement(Long factoryId, Long goodsReceiptId, Long toBinId, String status, Double quantity, Calendar creationDate);

    public Long recordFactoryProductOutboundMovement(Long factoryId, Long fromBinId, Long factoryProductId, Long toStoreId, Double quantity, Calendar creationDate);

    public Long recordFactoryRetailProductOutboundMovement(Long factoryId, Long fromBinId, Long factoryRetailProductId, Long toStoreId, Double quantity, Calendar creationDate);

    public Long recordInFactoryRawMaterialMovement(Long factoryId, Long fromBinId, Long toBinId, Long factoryRawMaterialId, String status, Double quantity, Calendar creationDate);

    public Long recordInFactoryProductMovement(Long factoryId, Long fromBinId, Long toBinId, Long factoryProductId, String status, Double quantity, Calendar creationDate);

    public Long recordInFactoryRetailProductMovement(Long factoryId, Long fromBinId, Long toBinId, Long factoryRetailProductId, String status, Double quantity, Calendar creationDate);

    public Long recordRawMaterialInFactoryUseMovement(Long factoryId, Long fromBinId, Long factoryRawMaterialId, Double quantity, Calendar creationDate);

    public Long changeFactoryBinStoredProductStatus(Long factoryId, Long factoryBinStoredProductId, String toStatus);

    public Long recordReturnedProductInboundMovement(Long factoryId, Long factoryProductId, Long fromStoreId, Long toBinId, Double quantity, Calendar creationDate);

    public Long recordReturnedRetailProductInboundMovement(Long factoryId, Long factoryRetailProductId, Long fromStoreId, Long toBinId, Double quantity, Calendar creationDate);

    public Long recordProductToBinMovement(Long factoryID, Long factoryProductId, Long toBinId, String status, Double quantity, Calendar creationDate);

    public int recordCurrentInventoryLevel(Long factoryId);

    List<GoodsReceiptEntity> findUnfulfilledGoodsReceipts(Long factoryId);

}
