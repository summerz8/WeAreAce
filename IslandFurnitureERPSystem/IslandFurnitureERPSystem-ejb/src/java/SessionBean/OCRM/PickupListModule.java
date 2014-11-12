/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.OCRM.PickupListEntity;
import Entity.Store.OCRM.TransactionItemEntity;
import Entity.Store.StoreItemMappingEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import Entity.Store.StoreSetEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
