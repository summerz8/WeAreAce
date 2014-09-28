/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.CommonInfrastructure.FactoryUserEntity;
import Entity.Factory.FactoryBin.FactoryBinEntity;
import Entity.Factory.FactoryBin.FactoryBinStoredProductEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.InventoryRecordEntity;
import Entity.Factory.SCM.GoodsReceiptEntity;
import Entity.Factory.SCM.InFactoryMovementEntity;
import Entity.Factory.SCM.InboundMovementEntity;
import Entity.Factory.SCM.OutboundMovementEntity;
import Entity.Factory.SCM.ProductToBinMovementEntity;
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
            Query q = em.createQuery("SELECT b FROM FactoryBin b WHERE b.factory.factoryId = :factoryId");
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
    public Long recordInboundMovement(long factoryId, Long goodsReceiptId, Long toBinId, String status, double quantity, Calendar creationDate) {
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

            InboundMovementEntity inboundMovement = new InboundMovementEntity();
            em.persist(inboundMovement);
            if (goodsReceipt.getPurchaseOrder().getContract().getFactoryRetailProduct() == null) {
                if (goodsReceipt.getPurchaseOrder().getContract().getFactoryRawMaterial().getFactory().getFactoryId() != factoryId) {
                    System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInboundMovement():Faild. The Factory " + factoryId + " has no access to Goods Receipt " + goodsReceiptId + ".");
                    return -2L;
                }
                FactoryRawMaterialEntity factoryRawMaterial = goodsReceipt.getPurchaseOrder().getContract().getFactoryRawMaterial();

                FactoryBinStoredProductEntity factoryBinStoredProduct;
                Query q = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryRawMaterial = :factoryRawMaterial AND fbsp.status = :status");
                q.setParameter("toBin", toBin);
                q.setParameter("factoryRawMaterial", factoryRawMaterial);
                q.setParameter("status", status);

                if (q.getResultList().isEmpty()) {
                    factoryBinStoredProduct = new FactoryBinStoredProductEntity();
                    factoryBinStoredProduct.createFactoryBinStoredProduct(factoryRawMaterial, toBin, status);
                    em.persist(factoryBinStoredProduct);
                    factoryRawMaterial.getFactoryBinStoredProducts().add(factoryBinStoredProduct);
                } else {
                    factoryBinStoredProduct = (FactoryBinStoredProductEntity) q.getSingleResult();
                }

                inboundMovement.recordInboundMovement(goodsReceipt, factoryBinStoredProduct, quantity, creationDate);
                em.flush();
            } else {
                if (goodsReceipt.getPurchaseOrder().getContract().getFactoryRetailProduct().getFactory().getFactoryId() != factoryId) {
                    System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInboundMovement():Faild. The Factory " + factoryId + " has no access to Goods Receipt " + goodsReceiptId + ".");
                    return -2L;
                }

                FactoryRetailProductEntity factoryRetailProduct = goodsReceipt.getPurchaseOrder().getContract().getFactoryRetailProduct();

                FactoryBinStoredProductEntity factoryBinStoredProduct;
                Query q = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryRetailProduct = :factoryRetailProduct AND fbsp.status = :status");
                q.setParameter("toBin", toBin);
                q.setParameter("factoryRetailProduct", factoryRetailProduct);
                q.setParameter("status", status);

                if (q.getResultList().isEmpty()) {
                    factoryBinStoredProduct = new FactoryBinStoredProductEntity();
                    factoryBinStoredProduct.createFactoryBinStoredProduct(factoryRetailProduct, toBin, status);
                    em.persist(factoryBinStoredProduct);
                    factoryRetailProduct.getFactoryBinStoredProducts().add(factoryBinStoredProduct);
                } else {
                    factoryBinStoredProduct = (FactoryBinStoredProductEntity) q.getSingleResult();
                }

                inboundMovement.recordInboundMovement(goodsReceipt, factoryBinStoredProduct, quantity, creationDate);
                em.flush();
            }
            em.persist(inboundMovement);
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInboundMovement(): " + inboundMovement.getInboundMovementId() + ": Successful.");

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
    public Long recordFactoryProductOutboundMovement(long factoryId, Long fromBinId, Long factoryProductId, Long toStoreId, double quantity, Calendar creationDate) {
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

            Query q4 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :fromBin AND fbsp.factoryProduct = :factoryProduct AND fbsp.status = 'unrestricted'");
            q4.setParameter("fromBin", fromBin);
            q4.setParameter("factoryProduct", factoryProduct);
            if (q4.getResultList().isEmpty()) {
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
            em.persist(factoryProductOutboundMovement);
            factoryProductOutboundMovement.recordFactoryProductOutboundMovement(factoryBinStoredProduct, toStore, quantity, creationDate);
            em.persist(factoryProductOutboundMovement);
            if (factoryBinStoredProduct.getAmount() == 0) { //not sure about the comparation of double
                em.remove(factoryBinStoredProduct);
            }
            em.flush();
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
    public Long recordFactoryRetailProductOutboundMovement(long factoryId, Long fromBinId, Long factoryRetailProductId, Long toStoreId, double quantity, Calendar creationDate) {
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

            Query q4 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :fromBin AND fbsp.factoryRetailProduct = :factoryRetailProduct AND fbsp.status = 'unrestricted'");
            q4.setParameter("fromBin", fromBin);
            q4.setParameter("factoryRetailProduct", factoryRetailProduct);
            if (q4.getResultList().isEmpty()) {
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
            factoryRetailProductOutboundMovement.recordFactoryRetailProductOutboundMovement(factoryBinStoredProduct, toStore, quantity, creationDate);
            em.persist(factoryRetailProductOutboundMovement);
            if (factoryBinStoredProduct.getAmount() == 0) { //not sure about the comparation of double
                em.remove(factoryBinStoredProduct);
            }
            em.flush();
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
    public Long recordInFactoryRawMaterialMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryRawMaterialId, String status, double quantity, Calendar creationDate) {
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

            Query q4 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin= :fromBin AND fbsp.factoryRawMaterial = :factoryRawMaterial AND fbsp.status = :status");
            q4.setParameter("fromBin", fromBin);
            q4.setParameter("factoryRawMaterial", factoryRawMaterial);
            q4.setParameter("status", status);
            if (q4.getResultList().isEmpty()) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRawMaterialMovement():Faild. The Factory Bin " + fromBinId + " does not contain this factoryRawMaterial with required status.");
                return -4L;
            }
            FactoryBinStoredProductEntity factoryFromBinStoredProduct = (FactoryBinStoredProductEntity) q4.getSingleResult();

            if (factoryFromBinStoredProduct.getAmount() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRawMaterialMovement():Faild. The retuired quantity exceeds the total stock in the Factory Bin" + fromBinId + ".");
                return -5L;
            }

            Query q5 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryRawMaterial = :factoryRawMaterial AND fbsp.status = :status");
            q5.setParameter("toBin", toBin);
            q5.setParameter("factoryRawMaterial", factoryFromBinStoredProduct.getFactoryRawMaterial());
            q5.setParameter("status", factoryFromBinStoredProduct.getStatus());

            FactoryBinStoredProductEntity factoryToBinStoredProduct;
            if (q5.getResultList().isEmpty()) {
                factoryToBinStoredProduct = new FactoryBinStoredProductEntity();
                factoryToBinStoredProduct.createFactoryBinStoredProduct(factoryFromBinStoredProduct.getFactoryRawMaterial(), toBin, factoryFromBinStoredProduct.getStatus());
                em.persist(factoryToBinStoredProduct);
                factoryFromBinStoredProduct.getFactoryRawMaterial().getFactoryBinStoredProducts().add(factoryToBinStoredProduct);
            } else {
                factoryToBinStoredProduct = (FactoryBinStoredProductEntity) q5.getSingleResult();
            }

            InFactoryMovementEntity inFactoryRawMaterialMovement = new InFactoryMovementEntity();
            inFactoryRawMaterialMovement.recordInFactoryRawMaterialMovement(factoryFromBinStoredProduct, factoryToBinStoredProduct, quantity, creationDate);
            em.persist(inFactoryRawMaterialMovement);
            if (factoryFromBinStoredProduct.getAmount() == 0) {
                em.remove(factoryFromBinStoredProduct);
            }
            em.flush();

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
    public Long recordInFactoryProductMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryProductId, String status, double quantity, Calendar creationDate) {
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

            Query q4 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin= :fromBin AND fbsp.factoryProduct = :factoryProduct AND fbsp.status = :status");
            q4.setParameter("fromBin", fromBin);
            q4.setParameter("factoryProduct", factoryProduct);
            q4.setParameter("status", status);
            if (q4.getResultList().isEmpty()) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryProductMovement():Faild. The Factory Bin " + fromBinId + " does not contain this factoryProduct with required status.");
                return -4L;
            }
            FactoryBinStoredProductEntity factoryFromBinStoredProduct = (FactoryBinStoredProductEntity) q4.getSingleResult();

            if (factoryFromBinStoredProduct.getAmount() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryProductMovement():Faild. The retuired quantity exceeds the total stock in the Factory Bin" + fromBinId + ".");
                return -5L;
            }

            Query q5 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryProduct = :factoryProduct AND fbsp.status = :status");
            q5.setParameter("toBin", toBin);
            q5.setParameter("factoryProduct", factoryFromBinStoredProduct.getFactoryProduct());
            q5.setParameter("status", factoryFromBinStoredProduct.getStatus());

            FactoryBinStoredProductEntity factoryToBinStoredProduct;
            if (q5.getResultList().isEmpty()) {
                factoryToBinStoredProduct = new FactoryBinStoredProductEntity();
                factoryToBinStoredProduct.createFactoryBinStoredProduct(factoryFromBinStoredProduct.getFactoryProduct(), toBin, factoryFromBinStoredProduct.getStatus());
                em.persist(factoryToBinStoredProduct);
                factoryFromBinStoredProduct.getFactoryProduct().getFactoryBinStoredProducts().add(factoryToBinStoredProduct);
            } else {
                factoryToBinStoredProduct = (FactoryBinStoredProductEntity) q5.getSingleResult();
            }

            InFactoryMovementEntity inFactoryProductMovement = new InFactoryMovementEntity();
            inFactoryProductMovement.recordInFactoryProductMovement(factoryFromBinStoredProduct, factoryToBinStoredProduct, quantity, creationDate);
            em.persist(inFactoryProductMovement);
            if (factoryFromBinStoredProduct.getAmount() == 0) {
                em.remove(factoryFromBinStoredProduct);
            }
            em.flush();

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
    public Long recordInFactoryRetailProductMovement(long factoryId, Long fromBinId, Long toBinId, Long factoryRetailProductId, String status, double quantity, Calendar creationDate) {
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

            Query q4 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin= :fromBin AND fbsp.factoryRetailProduct = :factoryRetailProduct AND fbsp.status = :status");
            q4.setParameter("fromBin", fromBin);
            q4.setParameter("factoryRetailProduct", factoryRetailProduct);
            q4.setParameter("status", status);
            if (q4.getResultList().isEmpty()) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRetailProductMovement():Faild. The Factory Bin " + fromBinId + " does not contain this factoryRetailProduct with required status.");
                return -4L;
            }
            FactoryBinStoredProductEntity factoryFromBinStoredProduct = (FactoryBinStoredProductEntity) q4.getSingleResult();

            if (factoryFromBinStoredProduct.getAmount() < quantity) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordInFactoryRetailProductMovement():Faild. The retuired quantity exceeds the total stock in the Factory Bin" + fromBinId + ".");
                return -5L;
            }

            Query q5 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryRetailProduct = :factoryRetailProduct AND fbsp.status = :status");
            q5.setParameter("toBin", toBin);
            q5.setParameter("factoryRetailProduct", factoryFromBinStoredProduct.getFactoryRetailProduct());
            q5.setParameter("status", factoryFromBinStoredProduct.getStatus());

            FactoryBinStoredProductEntity factoryToBinStoredProduct;
            if (q5.getResultList().isEmpty()) {
                factoryToBinStoredProduct = new FactoryBinStoredProductEntity();
                factoryToBinStoredProduct.createFactoryBinStoredProduct(factoryFromBinStoredProduct.getFactoryRetailProduct(), toBin, factoryFromBinStoredProduct.getStatus());
                em.persist(factoryToBinStoredProduct);
            } else {
                factoryToBinStoredProduct = (FactoryBinStoredProductEntity) q5.getSingleResult();
            }

            InFactoryMovementEntity inFactoryRetailProductMovement = new InFactoryMovementEntity();
            inFactoryRetailProductMovement.recordInFactoryRetailProductMovement(factoryFromBinStoredProduct, factoryToBinStoredProduct, quantity, creationDate);
            em.persist(inFactoryRetailProductMovement);
            if (factoryFromBinStoredProduct.getAmount() == 0) {
                em.remove(factoryFromBinStoredProduct);
            }
            em.flush();

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
    public Long recordRawMaterialInFactoryUseMovement(long factoryId, Long fromBinId, Long factoryRawMaterialId, double quantity, Calendar creationDate) {
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
            Query q3 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :fromBin AND fbsp.factoryRawMaterial = :factoryRawMaterial AND fbsp.status = 'unrestricted'");
            q3.setParameter("fromBin", fromBin);
            q3.setParameter("factoryRawMaterial", factoryRawMaterial);
            if (q3.getResultList().isEmpty()) {
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

            RawMaterialInFactoryUseMovementEntity rawMaterialInFactoryUseMovement = new RawMaterialInFactoryUseMovementEntity();
            rawMaterialInFactoryUseMovement.recordRawMaterialInFactoryUseMovement(factoryBinStoredProduct, quantity, creationDate);
            em.persist(rawMaterialInFactoryUseMovement);
            if (factoryBinStoredProduct.getAmount() == 0) {
                em.remove(factoryBinStoredProduct);
            }
            em.flush();
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

            Query q2 = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :factoryBin AND fbsp.factoryRawMaterial = :factoryRawMaterial AND fbsp.status = :toStatus");
            q2.setParameter("factoryBin", factoryBinStoredProduct1.getFactoryBin());
            q2.setParameter("factoryRawMaterial", factoryBinStoredProduct1.getFactoryRawMaterial());
            q2.setParameter("toStatus", toStatus);

            if (q2.getResultList().isEmpty()) {
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
    public Long recordReturnedProductInboundMovement(long factoryId, Long factoryProductId, Long fromStoreId, Long toBinId, double quantity, Calendar creationDate) {
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

            Query q = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryProduct = :factoryProduct AND fbsp.status = 'returned'");
            q.setParameter("toBin", toBin);
            q.setParameter("factoryProduct", factoryProduct);

            FactoryBinStoredProductEntity factoryBinStoredProduct;
            if (q.getResultList().isEmpty()) {
                factoryBinStoredProduct = new FactoryBinStoredProductEntity();
                factoryBinStoredProduct.createFactoryBinStoredProduct(factoryProduct, toBin, "returned");
                em.persist(factoryBinStoredProduct);
            } else {
                factoryBinStoredProduct = (FactoryBinStoredProductEntity) q.getSingleResult();
            }

            ReturnedItemInboundMovementEntity returnedFactoryProductInboundMovement = new ReturnedItemInboundMovementEntity();
            returnedFactoryProductInboundMovement.recordReturnedFactoryProductInboundMovement(factoryBinStoredProduct, fromStore, quantity, creationDate);

            em.persist(returnedFactoryProductInboundMovement);
            em.flush();
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedProductInboundMovement(): Successful.");

            return returnedFactoryProductInboundMovement.getReturnedItemInboundMovementId();
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
    public Long recordReturnedRetailProductInboundMovement(long factoryId, Long factoryRetailProductId, Long fromStoreId, Long toBinId, double quantity, Calendar creationDate) {
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

            Query q = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryRetailProduct = :factoryRetailProduct AND fbsp.status = 'returned'");
            q.setParameter("toBin", toBin);
            q.setParameter("factoryRetailProduct", factoryRetailProduct);

            FactoryBinStoredProductEntity factoryBinStoredProduct;
            if (q.getResultList().isEmpty()) {
                factoryBinStoredProduct = new FactoryBinStoredProductEntity();
                factoryBinStoredProduct.createFactoryBinStoredProduct(factoryRetailProduct, toBin, "returned");
                em.persist(factoryBinStoredProduct);
            } else {
                factoryBinStoredProduct = (FactoryBinStoredProductEntity) q.getSingleResult();
            }

            ReturnedItemInboundMovementEntity returnedFactoryRetailProductInboundMovement = new ReturnedItemInboundMovementEntity();
            returnedFactoryRetailProductInboundMovement.recordReturnedFactoryRetailProductInboundMovement(factoryBinStoredProduct, fromStore, quantity, creationDate);

            em.persist(returnedFactoryRetailProductInboundMovement);
            em.flush();
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedRetailProductInboundMovement(): Successful.");

            return returnedFactoryRetailProductInboundMovement.getReturnedItemInboundMovementId();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordReturnedRetailProductInboundMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -4L;
        }

    }

    //return factoryId
    //       -1L if the userId is not a factory userId
    //       -2 if unexpected error occurred
    @Override
    public long findFactoryIdByUserId(String userId) {
        try {
            FactoryUserEntity factoryUser = em.find(FactoryUserEntity.class, userId);
            if (factoryUser == null) {
                return 0L;
            }
            return factoryUser.getDepartmentId();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: findFactoryIdByUserId(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -2L;
        }

    }

    // return -1L if factoryProductId is invalid
    //        -2L if the toBinId is invalid
    //        -3L if unexpected exception occurred 
    //        else return productToBinMovementId
    @Override
    public Long recordProductToBinMovement(Long factoryProductId, Long toBinId, String status, double quantity, Calendar creationDate) {
        try {
            FactoryProductEntity factoryProduct = em.find(FactoryProductEntity.class, factoryProductId);

            if (factoryProduct == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordProductToBinMovement():Faild. The Factory Product " + factoryProductId + " is not found.");
                return -1L;
            }

            FactoryBinEntity toBin = em.find(FactoryBinEntity.class, toBinId);

            if (toBin == null) {
                System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordProductToBinMovement():Faild. The Factory Bin " + toBinId + " is not found.");
                return -2L;
            }

            Query q = em.createQuery("SELECT fbsp FROM FactoryBinStoredProductEntity fbsp WHERE fbsp.factoryBin = :toBin AND fbsp.factoryProduct = :factoryProduct AND fbsp.status = :status");
            q.setParameter("toBin", toBin);
            q.setParameter("factoryProduct", factoryProduct);
            q.setParameter("status", status);

            FactoryBinStoredProductEntity factoryBinStoredProduct;
            if (q.getResultList().isEmpty()) {
                factoryBinStoredProduct = new FactoryBinStoredProductEntity();
                factoryBinStoredProduct.createFactoryBinStoredProduct(factoryProduct, toBin, status);
                em.persist(factoryBinStoredProduct);
            } else {
                factoryBinStoredProduct = (FactoryBinStoredProductEntity) q.getSingleResult();
            }

            ProductToBinMovementEntity productToBinMovement = new ProductToBinMovementEntity();
            productToBinMovement.recordProductToBinMovement(factoryBinStoredProduct, quantity, creationDate);

            em.persist(productToBinMovement);
            em.flush();
            System.out.println("SessionBean.SCM.FactoryInventoryManagementModule: recordProductToBinMovement(): Successful.");

            return productToBinMovement.getProductToBinMovementId();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordProductToBinMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -4L;
        }

    }

    //return 0 if successful
    //       -1 if error occurred        
    @Override
    public int recordRurrentInventoryLevel(long factoryId) {
        try {
            Query q1 = em.createQuery("SELECT frm FROM FactoryRawMaterialEntity frm WHERE frm.factory.factoryId = :factoryId");
            q1.setParameter("factoryId", factoryId);
            for (Object o : q1.getResultList()) {
                FactoryRawMaterialEntity frm = (FactoryRawMaterialEntity) o;
                InventoryRecordEntity frmIr = new InventoryRecordEntity(frm, new GregorianCalendar(), frm.getUnrestrictedInventory());
                em.persist(frmIr);
                frm.getInventoryRecord().add(frmIr);
                em.flush();
            }
            
            Query q2 = em.createQuery("SELECT fp FROM FactoryProductEntity fp WHERE fp.factory.factoryId = :factoryId");
            q2.setParameter("factoryId", factoryId);
            for (Object o : q2.getResultList()) {
                FactoryProductEntity fp = (FactoryProductEntity) o;
                InventoryRecordEntity fpIr = new InventoryRecordEntity(fp, new GregorianCalendar(), fp.getUnrestrictedInventory());
                em.persist(fpIr);
                fp.getRecord().add(fpIr);
                em.flush();
            }
            
            Query q3 = em.createQuery("SELECT frp FROM FactoryRetailProductEntity frp WHERE frp.factory.factoryId = :factoryId");
            q3.setParameter("factoryId", factoryId);
            for (Object o : q3.getResultList()) {
                FactoryRetailProductEntity frp = (FactoryRetailProductEntity) o;
                InventoryRecordEntity frpIr = new InventoryRecordEntity(frp, new GregorianCalendar(), frp.getUnrestrictedInventory());
                em.persist(frpIr);
                frp.getInventoryRecords().add(frpIr);
                em.flush();
            }
            return 0;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.FactoryInventoryManagementModule: recordProductToBinMovement(): Caught an unexpected exception.");
            ex.printStackTrace();
            return -1;
        }
    }

}
