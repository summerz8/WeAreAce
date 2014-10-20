/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.ComboEntity;
import Entity.Kitchen.DishEntity;
import Entity.Kitchen.IngredientEntity;
import Entity.Kitchen.IngredientSupplierEntity;
import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.StoragePlaceEntity;
import Entity.Store.StoreEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Yoky
 */
@Stateless
public class KitchenSupport implements KitchenSupportLocal {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    @Override
    public DishEntity findDish(Long kitchenId, String name) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Query q = em.createQuery("SELECT d FROM DishEntity d WHERE d.kitchen = :kitchen AND d.name = :name");
            q.setParameter("kitchen", kitchen);
            q.setParameter("name", name);
            return (DishEntity) q.getSingleResult();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.KitchenSupport: findDish(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public IngredientEntity findIngredient(Long kitchenId, String name) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Query q = em.createQuery("SELECT i FROM IngredientEntity i WHERE i.kitchen = :kitchen AND i.name = :name");
            q.setParameter("kitchen", kitchen);
            q.setParameter("name", name);
            return (IngredientEntity) q.getSingleResult();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.KitchenSupport: findIngredient(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public IngredientSupplierEntity findIngredientSupplier(Long kitchenId, String name) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Query q = em.createQuery("SELECT igs FROM IngredientSupplierEntity igs WHERE igs.kitchen = :kitchen AND igs.name = :name");
            q.setParameter("kitchen", kitchen);
            q.setParameter("name", name);
            return (IngredientSupplierEntity) q.getSingleResult();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.KitchenSupport: findIngredientSupplier(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public StoragePlaceEntity findStoragePlace(Long kitchenId, String location) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Query q = em.createQuery("SELECT sp FROM StoragePlaceEntity sp WHERE sp.kitchen = :kitchen AND sp.location = :location");
            q.setParameter("kitchen", kitchen);
            q.setParameter("location", location);
            return (StoragePlaceEntity) q.getSingleResult();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.KitchenSupport: findStoragePlace(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ComboEntity findCombo(Long kitchenId, String name) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Query q = em.createQuery("SELECT c FROM ComboEntity c WHERE c.kitchen = :kitchen AND c.name = :name");
            q.setParameter("kitchen", kitchen);
            q.setParameter("name", name);
            return (ComboEntity) q.getSingleResult();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.KitchenSupport: findCombo(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public KitchenEntity findKitchenByStoreId(Long storeId) {
        StoreEntity store = em.find(StoreEntity.class, storeId);
        if (store == null) {
            System.out.println("SessionBean.KM.KitchenSupport: findKitchenByStoreId(): Failed. Store " + store.getStoreId() + " is not found.");
            return null;
        }

        if (store.getKitchen() == null) {
            System.out.println("SessionBean.KM.KitchenSupport: findKitchenByStoreId(): Failed. Store " + store.getStoreId() + " does not has a kitchen.");
            return null;
        }
        return store.getKitchen();
    }

}
