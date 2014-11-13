/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.IM;

import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.IM.StoreBinRetailProductEntity;
import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreProductEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author zhengyuan
 */
@Stateless
public class StoreBinControl implements StoreBinControlLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    @Override
    public int addAStorageBin(String name, String remark, Boolean isBackHouse, Boolean isDisplayArea, Boolean isSelfCollect, Long storeId) {

        try {
            StoreEntity store = em.find(StoreEntity.class, storeId);
            StoreWarehouseBinEntity storeBin = new StoreWarehouseBinEntity(name, remark, isBackHouse, isDisplayArea, isSelfCollect);
            em.persist(storeBin);
            store.getStoreBins().add(storeBin);
            storeBin.setStore(store);
            em.flush();
            return 1;
        } catch (Exception e) {

            System.err.println("SessionBean.IM.StoreBinControl: addAStorageBin(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public void editAStorageBin(String name, String remark, Boolean isBackHouse, Boolean isDisplayArea, Boolean isSelfCollect, Long storeBinId) {
        try {
            StoreWarehouseBinEntity storeBin = em.find(StoreWarehouseBinEntity.class, storeBinId);
            storeBin.setName(name);
            storeBin.setRemark(remark);
            storeBin.setIsBackHouse(isBackHouse);
            storeBin.setIsDisplayArea(isDisplayArea);
            storeBin.setIsSelfCollect(isSelfCollect);
            em.flush();

        } catch (Exception e) {
            System.err.println("SessionBean.IM.StoreBinControl: editAStorageBin(): Failed. Caught an unexpected exception.");
            e.printStackTrace();

        }

    }

    @Override
    public Integer deleteAStorageBin(Long storeBinId) {
        Integer result;

        try {
            StoreWarehouseBinEntity storeBin = em.find(StoreWarehouseBinEntity.class, storeBinId);
            if (checkStoreBinEmpty(storeBin)) {
                storeBin.setIsDeleted(true);
                Collection<StoreBinProductEntity> binProductList = storeBin.getStoreBinProducts();
                Collection<StoreBinRetailProductEntity> binRetailProductList = storeBin.getStoreBinRetailProducts();
                for (StoreBinProductEntity sbpe : binProductList) {
                    StoreBinProductEntity aStoreBin = sbpe;
                    aStoreBin.setIsDeleted(true);

                }
                for (StoreBinRetailProductEntity sbrpe : binRetailProductList) {
                    StoreBinRetailProductEntity aStoreBinR = sbrpe;
                    aStoreBinR.setIsDeleted(true);
                }

                result = 1;
                System.out.println("SessionBean.IM.StoreBinControl: deleteAStorageBin(): Delete succesfully!");
                return result;

            } else {

                result = -1;
                System.out.println("SessionBean.IM.StoreBinControl: deleteAStorageBin(): Failed. The storage Bin is not empty!");
                return result;
            }

        } catch (Exception e) {
            System.err.println("SessionBean.IM.StoreBinControl: deleteAStorageBin(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
            result = -2;
            return result;
        }

    }

    private Boolean checkStoreBinEmpty(StoreWarehouseBinEntity storeBin) {
        Boolean isEmpty = true;
        StoreWarehouseBinEntity storeBinToCheck = storeBin;
        Collection<StoreBinProductEntity> binProductList = storeBinToCheck.getStoreBinProducts();
        Collection<StoreBinRetailProductEntity> binRetailProductList = storeBinToCheck.getStoreBinRetailProducts();
        for (StoreBinProductEntity sbpe : binProductList) {
            StoreBinProductEntity aStoreBin = sbpe;
            if (aStoreBin.getQuantity() != 0) {
                isEmpty = false;
                break;
            }

        }
        if (isEmpty) {
            for (StoreBinRetailProductEntity sbrpe : binRetailProductList) {
                StoreBinRetailProductEntity aStoreBinR = sbrpe;
                if (aStoreBinR.getQuantity() != 0) {
                    isEmpty = false;
                    break;
                }
            }

        }

        return isEmpty;
    }

    @Override
    public List<StoreWarehouseBinEntity> getAllBackHouseBin(Long storeId) {

        Query q = em.createQuery("Select b from StoreWarehouseBinEntity b where b.isBackHouse = true and b.store.storeId =:sId and b.isDeleted = false");
        q.setParameter("sId", storeId);
        List<StoreWarehouseBinEntity> binList = new ArrayList<StoreWarehouseBinEntity>();
        for (Object o : q.getResultList()) {
            StoreWarehouseBinEntity swbe = (StoreWarehouseBinEntity) o;
            binList.add(swbe);
        }
        return binList;
    }

    @Override
    public List<StoreWarehouseBinEntity> getAllSelfCollectBin(Long storeId) {
        Query q = em.createQuery("Select b from StoreWarehouseBinEntity b where b.isSelfCollect = true and b.store.storeId =:sId and b.isDeleted = false");
        q.setParameter("sId", storeId);
        List<StoreWarehouseBinEntity> binList = new ArrayList<StoreWarehouseBinEntity>();
        for (Object o : q.getResultList()) {
            StoreWarehouseBinEntity swbe = (StoreWarehouseBinEntity) o;
            binList.add(swbe);
        }
        return binList;
    }

    @Override
    public List<StoreWarehouseBinEntity> getAllDisplayAreaBin(Long storeId) {
        Query q = em.createQuery("Select b from StoreWarehouseBinEntity b where b.isDisplayArea = true and b.store.storeId =:sId and b.isDeleted = false");
        q.setParameter("sId", storeId);
        List<StoreWarehouseBinEntity> binList = new ArrayList<StoreWarehouseBinEntity>();
        for (Object o : q.getResultList()) {
            StoreWarehouseBinEntity swbe = (StoreWarehouseBinEntity) o;
            binList.add(swbe);
        }
        return binList;

    }

    

//      public String[] mobile_getStoreBin(Long storeProductId){
//
//          System.out.println("Testing.");
//          
//          
//          Query q = em.createQuery("Select b from StoreBinProductEntity b where b.isDeleted = false and b.status = 0 and b.product.storeProductId = :spId");
//          
//          q.setParameter("spId", storeProductId);
//          String[] listOfBin = new String[10000];
//          int i = 0;
//          for(Object o : q.getResultList()){
//              
//              StoreBinProductEntity sbpe = (StoreBinProductEntity) o;
//              
//              listOfBin[i] = String.valueOf(sbpe.getSwe().getId());
//             i++ ;
//          }
//          
//          return listOfBin;
//      }
//    
    @Override
    public List<StoreBinProductEntity> ListProductReturnedBin(Long storeProductId) {
        Query q = em.createQuery("Select p From StoreBinProductEntity p Where p.product.storeProductId = :spId and p.status = 1 and p.quantity > 0");
        q.setParameter("spId", storeProductId);

        List<StoreBinProductEntity> result = new ArrayList<>();
        for (Object o : q.getResultList()) {
            StoreBinProductEntity sbp = (StoreBinProductEntity) o;
            result.add(sbp);

        }
        return result;

    }

    @Override
    public List<StoreBinRetailProductEntity> ListRProductReturnedBin(Long storeProductId) {
        Query q = em.createQuery("Select p From StoreBinRetailProductEntity p Where p.retailProduct.storeRetailProductId = :spId and p.status = 1 and p.quantity > 0");
        q.setParameter("spId", storeProductId);

        List<StoreBinRetailProductEntity> result = new ArrayList<>();
        for (Object o : q.getResultList()) {
            StoreBinRetailProductEntity sbp = (StoreBinRetailProductEntity) o;
            result.add(sbp);

        }
        return result;

    }

}
