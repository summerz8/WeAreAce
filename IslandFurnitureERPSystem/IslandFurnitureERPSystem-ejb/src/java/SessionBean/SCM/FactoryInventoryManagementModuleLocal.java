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

    public List listStorageBinInformation(long factoryId);

    public Long recordInboundMovement(long factoryId, Long goodsReceiptId, Long toBinId, String status, double quantity, Calendar creationDate);

    public Long recordFactoryProductOutboundMovement(long factoryId, Long fromBinId, Long factoryProductId, Long toStoreId, double quantity, Calendar creationDate);

    public Long recordFactoryRetailProductOutboundMovement(long factoryId, Long fromBinId, Long factoryRetailProductId, Long toStoreId, double quantity, Calendar creationDate);

    public Long recordInFactoryRawMaterialMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryRawMaterialId, String status, double quantity, Calendar creationDate);

    public Long recordInFactoryProductMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryProductId, String status, double quantity, Calendar creationDate);

    public Long recordInFactoryRetailProductMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryRetailProductId, String status, double quantity, Calendar creationDate);

    public Long recordRawMaterialInFactoryUseMovement(long factoryId, Long fromBinId, Long factoryRawMaterialId, double quantity, Calendar creationDate);

    public void changeFactoryBinStoredProductStatus(Long factoryBinStoredProductId, String toStatus);

    public Long recordReturnedProductInboundMovement(long factoryId, Long factoryProductId, Long fromStoreId, Long toBinId, double quantity, Calendar creationDate);

    public Long recordReturnedRetailProductInboundMovement(long factoryId, Long factoryRetailProductId, Long fromStoreId, Long toBinId, double quantity, Calendar creationDate);

    public long findFactoryIdByUserId(String userId);

    public Long recordProductToBinMovement(Long factoryProductId, Long toBinId, String status, double quantity, Calendar creationDate);

    public int recordRurrentInventoryLevel(long factoryId);

}
