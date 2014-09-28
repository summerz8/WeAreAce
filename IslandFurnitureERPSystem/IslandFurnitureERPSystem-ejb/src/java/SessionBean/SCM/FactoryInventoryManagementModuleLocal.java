/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface FactoryInventoryManagementModuleLocal {

    List listStorageBinInformation(long factoryId);

    Long recordInboundMovement(long factoryId, Long goodsReceiptId, Long toBinId, String status, double quantity, Calendar creationDate);

    Long recordFactoryProductOutboundMovement(long factoryId, Long fromBinId, Long factoryProductId, Long toStoreId, double quantity, Calendar creationDate);

    Long recordFactoryRetailProductOutboundMovement(long factoryId, Long fromBinId, Long factoryRetailProductId, Long toStoreId, double quantity, Calendar creationDate);

    Long recordInFactoryRawMaterialMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryRawMaterialId, String status, double quantity, Calendar creationDate);

    Long recordInFactoryProductMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryProductId, String status, double quantity, Calendar creationDate);

    Long recordInFactoryRetailProductMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryRetailProductId, String status, double quantity, Calendar creationDate);

    Long recordRawMaterialInFactoryUseMovement(long factoryId, Long fromBinId, Long factoryRawMaterialId, double quantity, Calendar creationDate);

    void changeFactoryBinStoredProductStatus(Long factoryBinStoredProductId, String toStatus);

    Long recordReturnedProductInboundMovement(long factoryId, Long factoryProductId, Long fromStoreId, Long toBinId, double quantity, Calendar creationDate);

    Long recordReturnedRetailProductInboundMovement(long factoryId, Long factoryRetailProductId, Long fromStoreId, Long toBinId, double quantity, Calendar creationDate);

    long findFactoryIdByUserId(String userId);

    Long recordProductToBinMovement(Long factoryProductId, Long toBinId, String status, double quantity, Calendar creationDate);

    int recordRurrentInventoryLevel(long factoryId);

}
