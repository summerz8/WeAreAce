/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.OCRM.PickupListEntity;
import Entity.Store.OCRM.TransactionItemEntity;
import Entity.Store.StoreItemMappingEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import Entity.Store.StoreSetEntity;
import SessionBean.IM.StoreBinControl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dan
 */
@Stateless
@WebService
public class PickupListModule implements PickupListModuleLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;

    @Override
    @WebMethod(operationName = "getPickupList")
    public List<PickupListEntity> getPickupList() {
        List<PickupListEntity> pickupList = new ArrayList();
        Query q = em.createQuery("SELECT t FROM PickupListEntity t");
        for (Object o : q.getResultList()) {
            PickupListEntity u = (PickupListEntity) o;
            if (!u.getPicked()) {
                pickupList.add(u);
            } else {
                System.out.println("list pickuplist: " + u.getPickupListId());
            }
        }
        return pickupList;
    }

    @Override
    @WebMethod(operationName = "listItems")
    public Object[][] listItems(@WebParam(name = "pickupListId") Long pickupListId) {
        PickupListEntity pe = em.find(PickupListEntity.class, pickupListId);
        List<TransactionItemEntity> transactionItemList = pe.getTransactoinItems();
        if (transactionItemList != null && transactionItemList.size() > 0) {
            Object[][] data = new Object[transactionItemList.size()][5];
            System.out.println("transactionItem amount: " + transactionItemList.size());
            for (int i = 0; i < transactionItemList.size(); i++) {
                TransactionItemEntity te = transactionItemList.get(i);
                StoreItemMappingEntity sime = em.find(StoreItemMappingEntity.class, te.getItemId());
                String productType = " ";
                String productId = " ";
                String productName = " ";
                String productAmount = " ";
                String productLocation = "Location";
                if (sime.getProductId() == null && sime.getStoreSetId() ==null ) {
                    StoreRetailProductEntity sre = em.find(StoreRetailProductEntity.class, sime.getRetailProductId());
                    productType = "Retail";
                    productId = sre.getStoreRetailProductId().toString();
                    productName = sre.getRetailProduct().getName();
                } else if (sime.getRetailProductId() == null && sime.getStoreSetId() ==null) {
                    StoreProductEntity sre = em.find(StoreProductEntity.class, sime.getProductId());
                    productType = "Finished Goods";
                    productId = sre.getStoreProductId().toString();
                    productName = sre.getProduct().getName();
                    
                    String res = mobile_getStoreBin(sre.getStoreProductId());
                    if(res!=null){
                    productLocation = res;
                    }else productLocation = "no stock";
                }else if(sime.getProductId() == null && sime.getRetailProductId() == null){
                    StoreSetEntity storeSet = em.find(StoreSetEntity.class,sime.getStoreSetId());
                    productType = "Set";
                    productId = storeSet.getId().toString();
                    productName = storeSet.getName();
                    productLocation = "Set Location";
                }
                productAmount = String.valueOf(te.getAmount());

                data[i][0] = productType;
                data[i][1] = productId;
                data[i][2] = productName;
                data[i][3] = productAmount;
                data[i][4] = productLocation;
            }
            return data;
        }

        return null;
    }

    public String mobile_getStoreBin(Long storeProductId) {
        System.out.println("mobile_getStoreBin: " + storeProductId);
        try {
            StoreProductEntity storeProduct = em.find(StoreProductEntity.class, storeProductId);

            String[] listOfBin = new String[10000];
            String result="";
            int i = 0;
            int count = 0;
            Collection<StoreBinProductEntity> sbinlist = storeProduct.getBinProducts();
            System.out.println("mobile_getStoreBin: binlist size: " + sbinlist.size());

            for (StoreBinProductEntity sbp : sbinlist) {
                System.out.println("store bin product entity: "+ sbp.getId());
                if (sbp.getQuantity() > 0 && sbp.getStatus() == 0) {
                    System.out.println("store bin product entity 2 : "+ sbp.getId() + sbp.getSwe().getId());
                    listOfBin[i] = String.valueOf(sbp.getSwe().getId());
                    result =result+" "+  listOfBin[i];
                    System.out.println("result: " + result);
                }
                i = i++;
                if (i > 5) {

                    break;
                }

            }

            return result;
        } catch (NullPointerException ex) {
            return null;
        }
    }
    @Override
    @WebMethod(operationName = "markFinish")
    public int markFinish(@WebParam(name = "pickupListId") Long pickupListId) {
        try {
            PickupListEntity pe = em.find(PickupListEntity.class, pickupListId);
            pe.setPicked(Boolean.TRUE);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    @WebMethod(operationName = "getFinishedList")
    public List<PickupListEntity> getFinishedList() {
        List<PickupListEntity> pickupList = new ArrayList();
        Query q = em.createQuery("SELECT t FROM PickupListEntity t");
        for (Object o : q.getResultList()) {
            PickupListEntity u = (PickupListEntity) o;
            if (u.getPicked() && !u.isDistributed()) {
                pickupList.add(u);
            } else {
                System.out.println("list pickuplist: " + u.getPickupListId());
            }
        }
        return pickupList;
    }

    @Override
    @WebMethod(operationName = "markDistributed")
    public int markDistributed(@WebParam(name = "pickupListId") Long pickupListId) {
        try {
            PickupListEntity pe = em.find(PickupListEntity.class, pickupListId);
            pe.setDistributed(Boolean.TRUE);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
