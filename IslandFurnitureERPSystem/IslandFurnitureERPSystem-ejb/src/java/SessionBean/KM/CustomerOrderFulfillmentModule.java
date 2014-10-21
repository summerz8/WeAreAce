/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.ComboEntity;
import Entity.Kitchen.ComboItemEntity;
import Entity.Kitchen.DailySalesEntity;
import Entity.Kitchen.DishEntity;
import Entity.Kitchen.DishItemEntity;
import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.KitchenOrderEntity;
import java.util.Calendar;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Yoky
 */
@Stateful
public class CustomerOrderFulfillmentModule implements CustomerOrderFulfillmentModuleLocal {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    public CustomerOrderFulfillmentModule() {
    }

    @Override
    public KitchenOrderEntity createOrder(Long kitchenId) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            KitchenOrderEntity order = new KitchenOrderEntity(kitchen);
            em.persist(order);
            kitchen.getOrders().add(order);
            em.flush();
            return order;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Long addDishItem(Long orderId, Long dishId, Integer quantity) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            DishEntity dish = em.find(DishEntity.class, dishId);
            DishItemEntity dishItem = new DishItemEntity(dish, quantity);
            em.persist(dishItem);
            order.getDishes().add(dishItem);
            em.flush();
            return dishItem.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public Long addComboItem(Long orderId, Long comboId, Integer quantity) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            ComboEntity combo = em.find(ComboEntity.class, comboId);
            ComboItemEntity comboItem = new ComboItemEntity(combo, quantity);
            em.persist(comboItem);
            order.getCombos().add(comboItem);
            em.flush();
            return comboItem.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public Long confirmOrder(Long orderId) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            order.setStatus("Confirmed");
            DailySalesEntity dailySales = findDailySales(order.getKitchen());
            if (dailySales == null) {
                dailySales = createDailySales(order.getKitchen());
            }
            for (DishItemEntity odi : order.getDishes()) {
                for (DishItemEntity sdi : dailySales.getDishes()) {
                    if(sdi.getDish().equals(odi.getDish())) {
                        Integer quantity = sdi.getQuantity() + odi.getQuantity();
                        sdi.setQuantity(quantity);
                        break;
                    }
                }
            }
            em.flush();
            for (ComboItemEntity oci : order.getCombos()) {
                for (ComboItemEntity sci : dailySales.getCombos()) {
                    if(sci.getCombo().equals(oci.getCombo())) {
                        Integer quantity = sci.getQuantity() + oci.getQuantity();
                        sci.setQuantity(quantity);
                        break;
                    }
                }
            }
            em.flush();
            return order.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    private DailySalesEntity findDailySales(KitchenEntity kitchen) {
        try {
            Calendar cal = Calendar.getInstance();
            Query q = em.createQuery("SELECT ds FROM DailySalesEntity ds WHERE ds.kitchen = :kitchen AND ds.salesDate = :cal");
            q.setParameter("kitchen", kitchen);
            q.setParameter("cal", cal);
            return (DailySalesEntity) q.getSingleResult();
        } catch (NoResultException nex) {
            return null;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    private DailySalesEntity createDailySales(KitchenEntity kitchen) {
        try {
            DailySalesEntity dailySales = new DailySalesEntity(kitchen);
            em.persist(dailySales);
            kitchen.getDailySales().add(dailySales);
            for (DishEntity d : kitchen.getDishes()) {
                DishItemEntity di = new DishItemEntity(d, 0);
                em.persist(di);
                em.flush();
                dailySales.getDishes().add(di);
                d.getDailySales().add(dailySales);
                em.flush();
            }
            for (ComboEntity c : kitchen.getCombos()) {
                ComboItemEntity ci = new ComboItemEntity(c, 0);
                em.persist(ci);
                em.flush();
                dailySales.getCombos().add(ci);
                c.getDailySales().add(dailySales);
                em.flush();
            }
            em.flush();
            return dailySales;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public Long cancelOrder(Long orderId) {
        try {
            KitchenOrderEntity order = em.find(KitchenOrderEntity.class, orderId);
            if (!order.getStatus().equals("Unconfirmed")) {
                System.out.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. The order is already confirmed or fulfilled.");
                return -1L;
            }
            order.setStatus("Cancelled");
            order.getKitchen().getOrders().remove(order);
            order.setKitchen(null);
            order.getDishes().clear();
            order.getCombos().clear();
            em.flush();
            return order.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.CustomerOrderFulfillmentModule: createOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -2L;
        }
    }
}
