/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.Factory.FactoryBin.FactoryBinEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface FactoryInventoryManagementModuleLocal {

//    List listStorageBinInformation(long factoryId);

    Long recordInboundMovement(long factoryId, Long goodsReceiptId, Long toBinId, double quantity, int year, int month, int day);

    Long recordFactoryProductOutboundMovement(long factoryId, Long fromBinId, Long factoryProductId, Long toStoreId, double quantity, int year, int month, int day);

    Long recordFactoryRetailProductOutboundMovement(long factoryId, Long fromBinId, Long factoryProductId, Long toStoreId, double quantity, int year, int month, int day);

    Long recordInFactoryRawMaterialMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryRawMaterialId, String status, double quantity, int year, int month, int day);

    Long recordInFactoryProductMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryProductId, String status, double quantity, int year, int month, int day);

    Long recordInFactoryRetailProductMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryRetailProductId, String status, double quantity, int year, int month, int day);

    Long recordRawMaterialInFactoryUseMovement(long factoryId, Long fromBinId, Long factoryRawMaterialId, double quantity, int year, int month, int day);

    void changeFactoryBinStoredProductStatus(Long factoryBinStoredProductId, String toStatus);

    Long recordReturnedProductInboundMovement(long factoryId, Long factoryProductId, Long fromStoreId, Long toBinId, double quantity, int year, int month, int day);

    Long recordReturnedRetailProductInboundMovement(long factoryId, Long factoryRetailProductId, Long fromStoreId, Long toBinId, double quantity, int year, int month, int day);

}
