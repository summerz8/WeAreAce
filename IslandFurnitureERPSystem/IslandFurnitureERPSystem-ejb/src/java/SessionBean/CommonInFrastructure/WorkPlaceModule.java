/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.Factory.FactoryEntity;
import Entity.CommonInfrastructure.InternalMessageReceive;
import Entity.CommonInfrastructure.TicketEntity;
import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Kitchen.DailySalesEntity;
import Entity.Kitchen.KitchenEntity;
import Entity.Store.OCRM.TransactionEntity;
import Entity.Store.StoreEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author apple
 */
@Stateless
public class WorkPlaceModule implements WorkPlaceModuleLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<StoreEntity> listStores() {
        Query q = em.createQuery("SELECT s FROM StoreEntity s");
        return (List<StoreEntity>) q.getResultList();
    }
    
    @Override
    public List<FactoryEntity> listFactory() {
        Query q=em.createQuery("SELECT s FROM FactoryEntity s");
        return (List<FactoryEntity>) q.getResultList();
    }

    @Override
    public Integer newMessages(String userId) {
        Integer result = 0;
        System.out.println("userid = " + userId);
        try {
            UserEntity user = em.find(UserEntity.class, userId);
            for (InternalMessageReceive msg : user.getReceiveMessage()) {
                if (!msg.isOpened()) {
                    result++;
                }
            }
        } catch (Exception e) {
            System.err.println("Caught an unexpected error!");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer msgTobeProcessed(String userId) {
        Integer result = 0;
        System.out.println("userid = " + userId);

        try {
            UserEntity user = em.find(UserEntity.class, userId);
            for (InternalMessageReceive msg : user.getReceiveMessage()) {
                if (!msg.isReplied()) {
                    result++;
                }
            }
        } catch (Exception e) {
            System.err.println("Caught an unexpected error!");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer ticketSubmitted(String userId) {
        Integer result = 0;
        System.out.println("userid = " + userId);

        try {
            UserEntity user = em.find(UserEntity.class, userId);
            for (TicketEntity t : user.getTickets()) {
                if (t.getStatus() == 1) {
                    result++;
                }
            }
        } catch (Exception e) {
            System.err.println("Caught an unexpected error!");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer ticketInReview(String userId) {
        Integer result = 0;
        System.out.println("userid = " + userId);

        try {
            UserEntity user = em.find(UserEntity.class, userId);
            for (TicketEntity t : user.getTickets()) {
                if (t.getStatus() == 2) {
                    result++;
                }
            }
        } catch (Exception e) {
            System.err.println("Caught an unexpected error!");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer ticketInProcess(String userId) {
        Integer result = 0;
        System.out.println("userid = " + userId);

        try {
            UserEntity user = em.find(UserEntity.class, userId);
            for (TicketEntity t : user.getTickets()) {
                if (t.getStatus() == 3) {
                    result++;
                }
            }
        } catch (Exception e) {
            System.err.println("Caught an unexpected error!");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Double revK(String userId, Long departmentId) {
        Double rev = 0.0;
        Calendar today = Calendar.getInstance();
        System.out.println("userid = " + userId);
        System.out.println("department = " + departmentId.toString());

        try {
            UserEntity user = em.find(UserEntity.class, userId);
            if (user.getUserLevel() == 2) {
                StoreEntity store = em.find(StoreEntity.class, departmentId);
                KitchenEntity k = store.getKitchen();
//                for (KitchenOrderEntity ko : k.getOrders()) {
////                    if (today.get(Calendar.MONTH) == ko.getCreationTime().get(Calendar.MONTH)) {
//
//                        rev = rev + ko.getTotal();
//                        System.out.println("revSP = " + rev.toString());
////                    }
//                }
                for (DailySalesEntity ds : k.getDailySales()) {
                    if (today.get(Calendar.MONTH) == ds.getSalesDate().get(Calendar.MONTH)) {
                        rev = rev + ds.getSales();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Caught an unexpected error!");
            e.printStackTrace();
        }

        return rev;
    }

    @Override
    public Double revSP(String userId, Long departmentId) {
        Double rev = 0.0;
        Calendar today = Calendar.getInstance();
        System.out.println("userid = " + userId);
        System.out.println("department = " + departmentId.toString());

        try {
            UserEntity user = em.find(UserEntity.class, userId);
            if (user.getUserLevel() == 2) {
                StoreEntity store = em.find(StoreEntity.class, departmentId);

                for (TransactionEntity t : store.getTransactions()) {
//                    if (today.get(Calendar.MONTH) == t.getGenerateTime().get(Calendar.MONTH)
//                            && t.getLocation() == 1) {
                    if (t.getLocation() == 1) {

                        rev = rev + t.getTotalPrice();
                        System.out.println("revSP = " + rev.toString());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Caught an unexpected error!");
            e.printStackTrace();
        }
        return rev;
    }

    @Override
    public Double revSRP(String userId, Long departmentId) {
        Double rev = 0.0;
        Calendar today = Calendar.getInstance();
        System.out.println("userid = " + userId);
        System.out.println("department = " + departmentId.toString());

        try {
            UserEntity user = em.find(UserEntity.class, userId);
            if (user.getUserLevel() == 2) {
                StoreEntity store = em.find(StoreEntity.class, departmentId);

                for (TransactionEntity t : store.getTransactions()) {
//                    if (today.get(Calendar.MONTH) == t.getGenerateTime().get(Calendar.MONTH)
//                            && t.getLocation() == 2) {
                    if (t.getLocation() == 2) {

                        rev = rev + t.getTotalPrice();
                        System.out.println("revSP = " + rev.toString());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Caught an unexpected error!");
            e.printStackTrace();
        }
        return rev;
    }

    @Override
    public Double stockFP(String userId, Long departmentId
    ) {
        Double stock = 0.0;
        System.out.println("userid = " + userId);
        System.out.println("department = " + departmentId.toString());
        try {
            UserEntity user = em.find(UserEntity.class, userId);
            if (user.getUserLevel() == 1) {
                FactoryEntity f = em.find(FactoryEntity.class, departmentId);
                for (FactoryProductEntity fp : f.getFactoryProducts()) {

                    stock = stock + fp.getUnrestrictedInventory();
                }
            }

        } catch (Exception e) {
            System.err.println("Caught an unexpected error!");
            e.printStackTrace();
        }
        return stock;
    }

    @Override
    public Double stockFRP(String userId, Long departmentId
    ) {
        Double stock = 0.0;
        System.out.println("userid = " + userId);
        System.out.println("department = " + departmentId.toString());
        try {
            UserEntity user = em.find(UserEntity.class, userId);
            if (user.getUserLevel() == 1) {
                FactoryEntity f = em.find(FactoryEntity.class, departmentId);
                for (FactoryRetailProductEntity frp : f.getFactoryRetailProducts()) {

                    stock = stock + frp.getUnrestrictedInventory();
                }
            }
        } catch (Exception e) {
            System.err.println("Caught an unexpected error!");
            e.printStackTrace();
        }
        return stock;
    }

    @Override
    public Double stockFRM(String userId, Long departmentId
    ) {
        Double stock = 0.0;
        System.out.println("userid = " + userId);
        System.out.println("department = " + departmentId.toString());
        try {
            UserEntity user = em.find(UserEntity.class, userId);
            if (user.getUserLevel() == 1) {
                FactoryEntity f = em.find(FactoryEntity.class, departmentId);
                for (FactoryRawMaterialEntity frm : f.getFactoryRawMaterials()) {

                    stock = stock + frm.getUnrestrictedInventory();

                }
            }
        } catch (Exception e) {
            System.err.println("Caught an unexpected error!");
            e.printStackTrace();
        }
        return stock;
    }

    @Override

    public StoreEntity getStore(Long storeId) {
        return em.find(StoreEntity.class, storeId);

    }

    @Override
    public FactoryEntity getFactory(Long factoryId) {
        return em.find(FactoryEntity.class, factoryId);
    }

}
