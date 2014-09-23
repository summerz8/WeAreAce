/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.Factory.FactoryBin.FactoryBinEntity;
import Entity.Factory.FactoryBin.FactoryBinStoredProductEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.SCM.GoodsReceiptEntity;
import Entity.Factory.SCM.InFactoryMovementEntity;
import Entity.Factory.SCM.InboundMovementEntity;
import Entity.Factory.SCM.OutboundMovementEntity;
import Entity.Factory.SCM.RawMaterialInFactoryUseMovementEntity;
import Entity.Factory.SCM.ReturnedItemInboundMovementEntity;
import Entity.Store.StoreEntity;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateful
public class FactoryInventoryManagementModule implements FactoryInventoryManagementModuleLocal {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    // return null if unexpected exception occurred 
    @Override
    public List listStorageBinInformation(long factoryId) {
        try {
            Query q = em.createQuery("SELECT b FROM FactoryBin b WHERE b.factory.factoryId = :factoryId ORDER BY b.factoryBinId ASC");
            q.setParameter("factoryId", factoryId);

            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: listStorageBinInformation(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    // the inbound movement record is created with a referrence to a goods receipt
    // return -1L if goodsReceiptId is invalid
    //        -2L if the factory has no access to this goods receipt
    //        -3L if the toBinId is invalid
    //        -4L if the factory has no access to this goods receipt
    //        -5L if unexpected exception occurred 
    //        else return inboundMovementId
    @Override
    public Long recordInboundMovement(long factoryId, Long goodsReceiptId, Long toBinId, String status, double quantity, int year, int month, int day) {
        try {
            GoodsReceiptEntity goodsReceipt = em.find(GoodsReceiptEntity.class, goodsReceiptId);
            if (goodsReceipt == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInboundMovement():Faild. The Goods Receipt " + goodsReceiptId + " is not found.");
                return -1L;
            }

            FactoryBinEntity toBin = em.find(FactoryBinEntity.class, toBinId);

            if (toBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInboundMovement():Faild. The Factory Bin " + toBinId + " is not found.");
                return -3L;
            }

            if (toBin.getFactory().getFactoryId() != factoryId) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInboundMovement():Faild. The Factory " + factoryId + " has no access to Factory Bin " + toBinId + ".");
                return -4L;
            }

            Calendar creationDate = new GregorianCalendar(year, month, day);
            InboundMovementEntity inboundMovement = new InboundMovementEntity();
            if (goodsReceipt.getPurchaseOder().getContract().getFactoryRetailProduct() == null) {
                if (goodsReceipt.getPurchaseOder().getContract().getFactoryRawMaterial().getFactory().getFactoryId() != factoryId) {
                    System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInboundMovement():Faild. The Factory " + factoryId + " has no access to Goods Receipt " + goodsReceiptId + ".");
                    return -2L;
                }
                inboundMovement.recordInboundMovement(goodsReceipt, toBin, goodsReceipt.getPurchaseOder().getContract().getFactoryRawMaterial(), status, quantity, creationDate);
            } else {
                if (goodsReceipt.getPurchaseOder().getContract().getFactoryRetailProduct().getFactory().getFactoryId() != factoryId) {
                    System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInboundMovement():Faild. The Factory " + factoryId + " has no access to Goods Receipt " + goodsReceiptId + ".");
                    return -2L;
                }
                inboundMovement.recordInboundMovement(goodsReceipt, toBin, goodsReceipt.getPurchaseOder().getContract().getFactoryRetailProduct(), status, quantity, creationDate);
            }
            em.persist(inboundMovement);
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInboundMovement(): Successful.");

            return inboundMovement.getInboundMovementId();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInboundMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -5L;
        }

    }

    // return -1L if toStoreId is invalid
    //        -2L if the fromBinId is invalid
    //        -3L if the factoryRetailProduct is not found
    //        -4L if the specified storage bin does not contain this factoryRetailProduct available for shipping
    //        -5L if the required quantity exceeds the total stock from this storage bin
    //        -6L if the required quantity exceeds the minimum inventory level in the factory
    //        -7L if unexpected exception occurred 
    //        else return outboundMovementId
    //
    //pre-cond: the status of the product must be unrestricted in order to be shipped out
    @Override
    public Long recordFactoryProductOutboundMovement(long factoryId, Long fromBinId, Long factoryProductId, Long toStoreId, double quantity, int year, int month, int day) {
        try {
            StoreEntity toStore = em.find(StoreEntity.class, toStoreId);

            if (toStore == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement():Faild. The Store " + toStoreId + " is not found.");
                return -1L;
            }

            FactoryBinEntity fromBin = em.find(FactoryBinEntity.class, fromBinId);

            if (fromBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement():Faild. The Factory Bin " + fromBinId + " is not found.");
                return -2L;
            }

            FactoryProductEntity factoryProduct = em.find(FactoryProductEntity.class, factoryProductId);

            if (factoryProduct == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement():Faild. The Factory Product " + factoryProductId + " is not found.");
                return -3L;
            }

            Query q4 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin = :fromBin AND fbsp.factoryProduct. = :factoryProduct AND fbsp.status = 'unrestricted'");
            q4.setParameter("fromBin", fromBin);
            q4.setParameter("factoryProduct", factoryProduct);
            if (q4.getResultList() == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement():Faild. The Factory Bin" + fromBinId + " does not contain the Factory Product " + factoryProductId + " available for shipping.");
                return -4L;
            }
            FactoryBinStoredProductEntity factoryBinStoredProduct = (FactoryBinStoredProductEntity) q4.getSingleResult();

            if (factoryBinStoredProduct.getAmount() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement():Faild. The retuired quantity exceeds the total stock in the Factory Bin" + fromBinId + ".");
                return -5L;
            }

            if (factoryProduct.getUnrestrictedInventory() - factoryProduct.getMinimumInventory() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement():Faild. The retuired quantity exceeds the total inventory in the Factory.");
                return -6L;
            }

            OutboundMovementEntity factoryProductOutboundMovement = new OutboundMovementEntity();
            Calendar creationDate = new GregorianCalendar(year, month, day);
            factoryProductOutboundMovement.recordFactoryProductOutboundMovement(factoryBinStoredProduct, toStore, quantity, creationDate);
            em.persist(factoryProductOutboundMovement);
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement(): Successful.");
            return factoryProductOutboundMovement.getOutboundMovementId();

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -7L;
        }
    }

    // return -1L if toStoreId is invalid
    //        -2L if the fromBinId is invalid
    //        -3L if the factoryRetailProduct is not found
    //        -4L if the specified storage bin does not contain this factoryRetailProduct available for shipping
    //        -5L if the required quantity exceeds the total stock from this storage bin
    //        -6L if the required quantity exceeds the minimum stock level in the factory
    //        -7L if unexpected exception occurred 
    //        else return outboundMovementId
    //
    //pre-cond: the status of the product must be unrestricted in order to be shipped out
    @Override
    // does not verify that the bin belongs to the factory
    public Long recordFactoryRetailProductOutboundMovement(long factoryId, Long fromBinId, Long factoryRetailProductId, Long toStoreId, double quantity, int year, int month, int day) {
        try {
            StoreEntity toStore = em.find(StoreEntity.class, toStoreId);

            if (toStore == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement():Faild. The Store " + toStoreId + " is not found.");
                return -1L;
            }

            FactoryBinEntity fromBin = em.find(FactoryBinEntity.class, fromBinId);

            if (fromBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement():Faild. The Factory Bin " + fromBinId + " is not found.");
                return -2L;
            }

            FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, factoryRetailProductId);

            if (factoryRetailProduct == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryRetailProductOutboundMovement():Faild. The Factory Retail Product " + factoryRetailProductId + " is not found.");
                return -3L;
            }

            Query q4 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin = :fromBin AND fbsp.factoryRetailProduct. = :factoryRetailProduct AND fbsp.status = 'unrestricted'");
            q4.setParameter("fromBin", fromBin);
            q4.setParameter("factoryRetailProduct", factoryRetailProduct);
            if (q4.getResultList() == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryRetailProductOutboundMovement():Faild. The Factory Bin" + fromBinId + " does not contain the Factory Retail Product " + factoryRetailProductId + " available for shipping.");
                return -4L;
            }
            FactoryBinStoredProductEntity factoryBinStoredProduct = (FactoryBinStoredProductEntity) q4.getSingleResult();

            if (factoryBinStoredProduct.getAmount() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryRetailProductOutboundMovement():Faild. The retuired quantity exceeds the total stock in the Factory Bin" + fromBinId + ".");
                return -5L;
            }

            if (factoryRetailProduct.getUnrestrictedInventory() - factoryRetailProduct.getMinimumInventory() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryRetailProductOutboundMovement():Faild. The retuired quantity exceeds the total stock in the Factory.");
                return -6L;
            }

            OutboundMovementEntity factoryRetailProductOutboundMovement = new OutboundMovementEntity();
            Calendar creationDate = new GregorianCalendar(year, month, day);
            factoryRetailProductOutboundMovement.recordFactoryRetailProductOutboundMovement(factoryBinStoredProduct, toStore, quantity, creationDate);
            em.persist(factoryRetailProductOutboundMovement);
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryRetailProductOutboundMovement(): Successful.");
            return factoryRetailProductOutboundMovement.getOutboundMovementId();

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryRetailProductOutboundMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -7L;
        }
    }

    // return -1L if the fromBinId is invalid
    //        -2L if the toBinId is invalid
    //        -3L if the factoryRawMaterial is not found
    //        -4L if the specified storage bin does not contain this factoryRawMaterial with required status
    //        -5L if the required quantity exceeds the total stock from this storage bin
    //        -6L if unexpected exception occurred 
    //        else return inFactoryMovementId
    @Override
    // does not verify that the bin belongs to the factory
    public Long recordInFactoryRawMaterialMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryRawMaterialId, String status, double quantity, int year, int month, int day) {
        try {
            FactoryBinEntity fromBin = em.find(FactoryBinEntity.class, fromBinId);

            if (fromBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement():Faild. The Factory Bin " + fromBinId + " is not found.");
                return -1L;
            }

            FactoryBinEntity toBin = em.find(FactoryBinEntity.class, toBinId);

            if (toBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordFactoryProductOutboundMovement():Faild. The Factory Bin " + toBinId + " is not found.");
                return -2L;
            }

            FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, factoryRawMaterialId);

            if (factoryRawMaterial == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRawMaterialMovement():Faild. The Factory Raw Material " + factoryRawMaterialId + " is not found.");
                return -3L;
            }

            Query q4 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin= :fromBin AND fbsp.factoryRawMaterial = :factoryRawMaterial AND fbsp.status = :status");
            q4.setParameter("fromBin", toBin);
            q4.setParameter("factoryRawMaterial", factoryRawMaterial);
            q4.setParameter("status", status);
            if (q4.getResultList() == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRawMaterialMovement():Faild. The Factory Bin " + fromBinId + " does not contain this factoryRawMaterial with required status.");
                return -4L;
            }
            FactoryBinStoredProductEntity factoryFromBinStoredProduct = (FactoryBinStoredProductEntity) q4.getSingleResult();

            if (factoryFromBinStoredProduct.getAmount() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRawMaterialMovement():Faild. The retuired quantity exceeds the total stock in the Factory Bin" + fromBinId + ".");
                return -5L;
            }

            Calendar creationDate = new GregorianCalendar(year, month, day);
            InFactoryMovementEntity inFactoryRawMaterialMovement = new InFactoryMovementEntity();
            inFactoryRawMaterialMovement.recordInFactoryRawMaterialMovement(factoryFromBinStoredProduct, toBin, quantity, creationDate);
            em.persist(inFactoryRawMaterialMovement);
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRawMaterialMovement(): Successful.");
            return inFactoryRawMaterialMovement.getInFactoryMovementId();

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRawMaterialMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -6L;
        }
    }

    // return -1L if the fromBinId is invalid
    //        -2L if the toBinId is invalid
    //        -3L if the factoryRetailProduct is not found
    //        -4L if the specified storage bin does not contain this factoryRetailProduct with required status
    //        -5L if the required quantity exceeds the total stock from this storage bin
    //        -6L if unexpected exception occurred 
    //        else return inFactoryMovementId
    @Override
    // does not verify that the bin belongs to the factory
    public Long recordInFactoryProductMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryProductId, String status, double quantity, int year, int month, int day) {
        try {
            FactoryBinEntity fromBin = em.find(FactoryBinEntity.class, fromBinId);

            if (fromBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryProductMovement():Faild. The Factory Bin " + fromBinId + " is not found.");
                return -1L;
            }

            FactoryBinEntity toBin = em.find(FactoryBinEntity.class, toBinId);

            if (toBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryProductMovement():Faild. The Factory Bin " + toBinId + " is not found.");
                return -2L;
            }

            FactoryProductEntity factoryProduct = em.find(FactoryProductEntity.class, factoryProductId);

            if (factoryProduct == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryProductMovement():Faild. The Factory Product " + factoryProductId + " is not found.");
                return -3L;
            }

            Query q4 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin= :fromBin AND fbsp.factoryProduct = :factoryProduct AND fbsp.status = :status");
            q4.setParameter("fromBin", fromBin);
            q4.setParameter("factoryProduct", factoryProduct);
            q4.setParameter("status", status);
            if (q4.getResultList() == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryProductMovement():Faild. The Factory Bin " + fromBinId + " does not contain this factoryProduct with required status.");
                return -4L;
            }
            FactoryBinStoredProductEntity factoryFromBinStoredProduct = (FactoryBinStoredProductEntity) q4.getSingleResult();

            if (factoryFromBinStoredProduct.getAmount() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryProductMovement():Faild. The retuired quantity exceeds the total stock in the Factory Bin" + fromBinId + ".");
                return -5L;
            }

            Calendar creationDate = new GregorianCalendar(year, month, day);
            InFactoryMovementEntity inFactoryProductMovement = new InFactoryMovementEntity();
            inFactoryProductMovement.recordInFactoryProductMovement(factoryFromBinStoredProduct, toBin, quantity, creationDate);
            em.persist(inFactoryProductMovement);
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryProductMovement(): Successful.");
            return inFactoryProductMovement.getInFactoryMovementId();

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryProductMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -6L;
        }
    }

    // return -1L if the fromBinId is invalid
    //        -2L if the toBinId is invalid
    //        -3L if the factoryRetailproduct is not found
    //        -4L if the specified storage bin does not contain this factoryRetailproduct with required status
    //        -5L if the required quantity exceeds the total stock from this storage bin
    //        -6L if unexpected exception occurred 
    //        else return inFactoryMovementId
    @Override
    // does not verify that the bin belongs to the factory
    public Long recordInFactoryRetailProductMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryRetailProductId, String status, double quantity, int year, int month, int day) {
        try {

            FactoryBinEntity fromBin = em.find(FactoryBinEntity.class, fromBinId);

            if (fromBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRetailProductMovement():Faild. The Factory Bin " + fromBinId + " is not found.");
                return -1L;
            }

            FactoryBinEntity toBin = em.find(FactoryBinEntity.class, toBinId);

            if (toBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRetailProductMovement():Faild. The Factory Bin " + toBinId + " is not found.");
                return -2L;
            }

            FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, factoryRetailProductId);

            if (factoryRetailProduct == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRetailProductMovement():Faild. The Factory Retail Product " + factoryRetailProductId + " is not found.");
                return -3L;
            }

            Query q4 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin= :fromBin AND fbsp.factoryRetailProduct = :factoryRetailProduct AND fbsp.status = :status");
            q4.setParameter("fromBin", fromBin);
            q4.setParameter("factoryRetailProduct", factoryRetailProduct);
            q4.setParameter("status", status);
            if (q4.getResultList() == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRetailProductMovement():Faild. The Factory Bin " + fromBinId + " does not contain this factoryRetailProduct with required status.");
                return -4L;
            }
            FactoryBinStoredProductEntity factoryFromBinStoredProduct = (FactoryBinStoredProductEntity) q4.getSingleResult();

            if (factoryFromBinStoredProduct.getAmount() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRetailProductMovement():Faild. The retuired quantity exceeds the total stock in the Factory Bin" + fromBinId + ".");
                return -5L;
            }

            Calendar creationDate = new GregorianCalendar(year, month, day);
            InFactoryMovementEntity inFactoryRetailProductMovement = new InFactoryMovementEntity();
            inFactoryRetailProductMovement.recordInFactoryRetailProductMovement(factoryFromBinStoredProduct, toBin, quantity, creationDate);
            em.persist(inFactoryRetailProductMovement);
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRetailProductMovement(): Successful.");
            return inFactoryRetailProductMovement.getInFactoryMovementId();

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRetailProductMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -6L;
        }
    }

    // return -1L if the fromBinId is invalid
    //        -2L if the factoryRawMaterial is not found
    //        -3L if the specified storage bin does not contain this factoryRawMaterial available for use
    //        -4L if the required quantity exceeds the total stock in this storage bin
    //        -5L if the required quantity exceeds the minimum stock level in the factory
    //        -6L if unexpected exception occurred 
    //        else return outboundMovementId
    //
    //pre-cond: the status of the product must be unrestricted in order to be shipped out
    @Override
    // does not verify that the bin belongs to the factory
    public Long recordRawMaterialInFactoryUseMovement(long factoryId, Long fromBinId, Long factoryRawMaterialId, double quantity, int year, int month, int day) {
        try {
            FactoryBinEntity fromBin = em.find(FactoryBinEntity.class, fromBinId);

            if (fromBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordRawMaterialInFactoryUseMovement():Faild. The Factory Bin " + fromBinId + " is not found.");
                return -1L;
            }

            FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, factoryRawMaterialId);

            if (factoryRawMaterial == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordRawMaterialInFactoryUseMovement():Faild. The Factory Raw Material " + factoryRawMaterialId + " is not found.");
                return -2L;
            }

            //The status of the raw material must be unrestricted do that can be used in production
            Query q3 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin = :fromBin AND fbsp.factoryRawMaterial. = :factoryRawMaterial AND fbsp.status = 'unrestricted'");
            q3.setParameter("fromBin", fromBin);
            q3.setParameter("factoryRawMaterial", factoryRawMaterial);
            if (q3.getResultList() == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordRawMaterialInFactoryUseMovement():Faild. The Factory Bin" + fromBinId + " does not contain the Factory Raw Material " + factoryRawMaterialId + " available.");
                return -3L;
            }
            FactoryBinStoredProductEntity factoryBinStoredProduct = (FactoryBinStoredProductEntity) q3.getSingleResult();

            if (factoryBinStoredProduct.getAmount() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordRawMaterialInFactoryUseMovement():Faild. The retuired quantity exceeds the total stock in the Factory Bin" + fromBinId + ".");
                return -4L;
            }

            if (factoryRawMaterial.getUnrestrictedInventory() - factoryRawMaterial.getMinimumInventory() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordRawMaterialInFactoryUseMovement():Faild. The retuired quantity exceeds the total stock in the Factory.");
                return -7L;
            }

            Calendar creationDate = new GregorianCalendar(year, month, day);

            RawMaterialInFactoryUseMovementEntity rawMaterialInFactoryUseMovement = new RawMaterialInFactoryUseMovementEntity();
            rawMaterialInFactoryUseMovement.recordRawMaterialInFactoryUseMovement(factoryBinStoredProduct, quantity, creationDate);
            em.persist(rawMaterialInFactoryUseMovement);
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordRawMaterialInFactoryUseMovement(): Successful.");
            return rawMaterialInFactoryUseMovement.getRawMaterialInFactoryUseMovementId();

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordRawMaterialInFactoryUseMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -7L;
        }
    }

    @Override
    //need further modification
    //do not record date, need modification
    public void changeFactoryBinStoredProductStatus(Long factoryBinStoredProductId, String toStatus) {
        try {
            FactoryBinStoredProductEntity factoryBinStoredProduct1 = em.find(FactoryBinStoredProductEntity.class, factoryBinStoredProductId);

            if (factoryBinStoredProduct1 == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: changeFactoryBinStoredProductStatus():Faild. The factoryBinStoredProduct " + factoryBinStoredProductId + " is not found.");
                System.exit(1);
            }

            Query q2 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProduct fbsp WHERE fbsp.factoryBin = :factoryBin AND fbsp.factoryRawMaterial = :factoryRawMaterial AND fbsp.status = :toStatus");
            q2.setParameter("factoryBin", factoryBinStoredProduct1.getFactoryBin());
            q2.setParameter("factoryRawMaterial", factoryBinStoredProduct1.getFactoryRawMaterial());
            q2.setParameter("toStatus", toStatus);

            if (q2.getResultList() == null) {
                FactoryBinStoredProductEntity factoryBinStoredProduct2 = new FactoryBinStoredProductEntity();
                factoryBinStoredProduct2.createFactoryBinStoredProduct(factoryBinStoredProduct1.getFactoryRawMaterial(), factoryBinStoredProduct1.getFactoryBin(), toStatus);
                factoryBinStoredProduct2.increaseQuantity(factoryBinStoredProduct1.getAmount());
                em.persist(factoryBinStoredProduct2);
                em.remove(factoryBinStoredProduct1);
            } else {
                FactoryBinStoredProductEntity factoryBinStoredProduct2 = (FactoryBinStoredProductEntity) q2.getSingleResult();
                factoryBinStoredProduct2.increaseQuantity(factoryBinStoredProduct1.getAmount());
                em.remove(factoryBinStoredProduct1);
                em.flush();
            }

            factoryBinStoredProduct1.setStatus(toStatus);
            em.flush();
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: changeFactoryBinStoredProductStatus(): Successful.");
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: changeFactoryBinStoredProductStatus(): Caught an unexpected exception.");
            ex.printStackTrace();
            System.exit(2);
        }
    }

    // return -1L if factoryProductId is invalid
    //        -2L if storeId is invalid
    //        -3L if the toBinId is invalid
    //        -4L if unexpected exception occurred 
    //        else return inboundMovementId
    @Override
    public Long recordReturnedProductInboundMovement(long factoryId, Long factoryProductId, Long fromStoreId, Long toBinId, double quantity, int year, int month, int day) {
        try {
            FactoryProductEntity factoryProduct = em.find(FactoryProductEntity.class, factoryProductId);

            if (factoryProduct == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedProductInboundMovement():Faild. The Factory Product " + factoryProductId + " is not found.");
                return -1L;
            }

            StoreEntity fromStore = em.find(StoreEntity.class, fromStoreId);

            if (fromStore == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedProductInboundMovement():Faild. The Store " + fromStoreId + " is not found.");
                return -2L;
            }

            FactoryBinEntity toBin = em.find(FactoryBinEntity.class, toBinId);

            if (toBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedProductInboundMovement():Faild. The Factory Bin " + toBinId + " is not found.");
                return -3L;
            }

            Calendar creationDate = new GregorianCalendar(year, month, day);

            ReturnedItemInboundMovementEntity returnedItemInboundMovement = new ReturnedItemInboundMovementEntity();
            returnedItemInboundMovement.recordReturnedItemInboundMovement(factoryProduct, fromStore, toBin, quantity, creationDate);

            em.persist(returnedItemInboundMovement);
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedProductInboundMovement(): Successful.");

            return returnedItemInboundMovement.getReturnedItemInboundMovementId();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedProductInboundMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -4L;
        }

    }

    // return -1L if factoryRetailProductId is invalid
    //        -2L if storeId is invalid
    //        -3L if the toBinId is invalid
    //        -4L if unexpected exception occurred 
    //        else return inboundMovementId
    @Override
    public Long recordReturnedRetailProductInboundMovement(long factoryId, Long factoryRetailProductId, Long fromStoreId, Long toBinId, double quantity, int year, int month, int day) {
        try {
            FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, factoryRetailProductId);

            if (factoryRetailProduct == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedRetailProductInboundMovement():Faild. The Factory Retail Product " + factoryRetailProductId + " is not found.");
                return -1L;
            }

            StoreEntity fromStore = em.find(StoreEntity.class, fromStoreId);

            if (fromStore == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedRetailProductInboundMovement():Faild. The Store " + fromStoreId + " is not found.");
                return -2L;
            }

            FactoryBinEntity toBin = em.find(FactoryBinEntity.class, toBinId);

            if (toBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedRetailProductInboundMovement():Faild. The Factory Bin " + toBinId + " is not found.");
                return -3L;
            }

            Calendar creationDate = new GregorianCalendar(year, month, day);

            ReturnedItemInboundMovementEntity returnedItemInboundMovement = new ReturnedItemInboundMovementEntity();
            returnedItemInboundMovement.recordReturnedItemInboundMovement(factoryRetailProduct, fromStore, toBin, quantity, creationDate);

            em.persist(returnedItemInboundMovement);
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedRetailProductInboundMovement(): Successful.");

            return returnedItemInboundMovement.getReturnedItemInboundMovementId();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedRetailProductInboundMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -5L;
        }

    }

}
