/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.IngredientEntity;
import Entity.Kitchen.IngredientSupplierEntity;
import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.StoragePlaceEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yoky
 */
@Stateful
public class RawIngredientsManagementModule implements RawIngredientsManagementModuleLocal {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    // return ingredient ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long addIngredient(Long kitchenId, String name, Double price, String unit, String remark, Double lotSize, List<Long> storagePlaceIds, Long supplierId) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            IngredientSupplierEntity supplier = em.find(IngredientSupplierEntity.class, supplierId);
            ArrayList<StoragePlaceEntity> storagePlaces = new ArrayList<>();
            for (Long spId : storagePlaceIds) {
                storagePlaces.add(em.find(StoragePlaceEntity.class, spId));
            }

            IngredientEntity ingredient = new IngredientEntity(name, price, unit, remark, lotSize, kitchen, supplier);
            em.persist(ingredient);

            for (StoragePlaceEntity storagePlace : storagePlaces) {
                ingredient.getStoragePlaces().add(storagePlace);
                storagePlace.getIngredients().add(ingredient);
            }

            kitchen.getIngredients().add(ingredient);
            supplier.getIngredients().add(ingredient);
            em.flush();
            System.out.println("SessionBean.KM.RawIngredientsManagementModule: addIngredient(): Succcessful. New ingredient " + ingredient.getId() + " is added.");
            return ingredient.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.RawIngredientsManagementModule: addIngredient(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    // return ingredient ID
    //        -1L if its stock is larger than 0
    //        -2L if it is in some dish's recipe
    //        -3L if an unexpected exception occurred
    @Override
    public Long deleteIngredient(Long ingredientId) {
        try {
            IngredientEntity ingredient = em.find(IngredientEntity.class, ingredientId);
            if (ingredient.getStock() > 0.0001) {
                System.out.println("SessionBean.KM.RawIngredientsManagementModule: deleteIngredient(): Failed. The stock is larger than 0.");
                return -1L;
            }
            if (!ingredient.getDishes().isEmpty()) {
                System.out.println("SessionBean.KM.RawIngredientsManagementModule: deleteIngredient(): Failed. The raw ingredient is contained in the recipe of at least one dish.");
                return -2L;
            }
            ingredient.setDeleted(true);
            ingredient.getKitchen().getIngredients().remove(ingredient);
            ingredient.setKitchen(null);
            for (StoragePlaceEntity sp : ingredient.getStoragePlaces()) {
                sp.getIngredients().remove(ingredient);
            }
            ingredient.getStoragePlaces().clear();
            em.flush();
            ingredient.getSupplier().getIngredients().remove(ingredient);
            ingredient.setSupplier(null);
            System.out.println("SessionBean.KM.RawIngredientsManagementModule: deleteIngredient(): Succcessful. The raw ingredient " + ingredient.getId() + " is deleted.");
            return ingredient.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.RawIngredientsManagementModule: deleteIngredient(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -3L;
        }
    }

    // return ingredient ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long editIngredient(Long ingredientId, String name, Double price, String unit, String remark, Double lotSize, List<Long> storagePlaceIds, Long supplierId) {
        try {
            IngredientEntity ingredient = em.find(IngredientEntity.class, ingredientId);
            for (StoragePlaceEntity storagePlace : ingredient.getStoragePlaces()) {
                storagePlace.getIngredients().remove(ingredient);
            }
            ingredient.getStoragePlaces().clear();
            IngredientSupplierEntity oldSupplier = ingredient.getSupplier();
            oldSupplier.getIngredients().remove(ingredient);
            ArrayList<StoragePlaceEntity> newStoragePlaces = new ArrayList<>();
            for (Long spId : storagePlaceIds) {
                StoragePlaceEntity newSp = em.find(StoragePlaceEntity.class, spId);
                newStoragePlaces.add(newSp);
                newSp.getIngredients().add(ingredient);
            }
            IngredientSupplierEntity newSupplier = em.find(IngredientSupplierEntity.class, supplierId);
            ingredient.setName(name);
            ingredient.setPrice(price);
            ingredient.setUnit(unit);
            ingredient.setRemark(remark);
            ingredient.setLotSize(lotSize);
            ingredient.setStoragePlaces(newStoragePlaces);
            ingredient.setSupplier(newSupplier);
            newSupplier.getIngredients().add(ingredient);
            em.flush();
            System.out.println("SessionBean.KM.RawIngredientsManagementModule: editIngredient(): Succcessful. The raw ingredient " + ingredientId + " is edited.");
            return ingredientId;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.RawIngredientsManagementModule: editIngredient(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }

    }

    @Override
    public List<IngredientEntity> getIngredients(Long kitchenId) {
        return em.find(KitchenEntity.class, kitchenId).getIngredients();
    }

    // return ingredient supplier ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long addSupplier(Long kitchenId, String name, String address, String contact, String fax, String remark) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            IngredientSupplierEntity supplier = new IngredientSupplierEntity(name, address, contact, fax, remark, kitchen);
            em.persist(supplier);
            kitchen.getIngredientSuppliers().add(supplier);
            em.flush();
            System.out.println("SessionBean.KM.RawIngredientsManagementModule: addSupplier(): Succcessful. New Ingredient Supplier " + supplier.getId() + " is added.");
            return supplier.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.RawIngredientsManagementModule: addSupplier(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    // return ingredient supplier ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long editSupplier(Long ingredientSupplierId, String name, String address, String contact, String fax, String remark) {
        try {
            IngredientSupplierEntity supplier = em.find(IngredientSupplierEntity.class, ingredientSupplierId);
            supplier.setName(name);
            supplier.setAddress(address);
            supplier.setContact(contact);
            supplier.setFax(fax);
            supplier.setRemark(remark);
            em.flush();
            System.out.println("SessionBean.KM.RawIngredientsManagementModule: editSupplier(): Succcessful. Ingredient Supplier " + supplier.getId() + " is edited.");
            return supplier.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.RawIngredientsManagementModule: editSupplier(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    // return ingredient supplier ID
    //        -1L if ingredient supplier has raw ingredients to supply currently
    //        -2L if an unexpected exception occurred
    @Override
    public Long deleteSupplier(Long ingredientSupplierId) {
        try {
            IngredientSupplierEntity supplier = em.find(IngredientSupplierEntity.class, ingredientSupplierId);
            if (!supplier.getIngredients().isEmpty()) {
                System.out.println("SessionBean.KM.RawIngredientsManagementModule: deleteSupplier(): Failed. The ingredient supplier has raw ingredients to supply currently.");
                return -1L;
            }
            supplier.getKitchen().getIngredientSuppliers().remove(supplier);
            supplier.setKitchen(null);
            supplier.setDeleted(true);
            em.flush();
            return supplier.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.RawIngredientsManagementModule: deleteSupplier(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -2L;
        }
    }

    @Override
    public List<IngredientSupplierEntity> getSuppliers(Long kitchenId) {
        return em.find(KitchenEntity.class, kitchenId).getIngredientSuppliers();
    }

    // return storage place ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long addStoragePlace(Long kitchenId, String location) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            StoragePlaceEntity storagePlace = new StoragePlaceEntity(kitchen, location);
            em.persist(storagePlace);
            kitchen.getStoragePlaces().add(storagePlace);
            em.flush();
            System.out.println("SessionBean.KM.RawIngredientsManagementModule: addStoragePlace(): Succcessful. New Storage Place " + storagePlace.getId() + " is added.");
            return storagePlace.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.RawIngredientsManagementModule: addStoragePlace(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    // return storage place ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long editStoragePlace(Long storagePlaceId, String location) {
        try {
            StoragePlaceEntity storagePlace = em.find(StoragePlaceEntity.class, storagePlaceId);
            storagePlace.setLocation(location);
            em.flush();
            System.out.println("SessionBean.KM.RawIngredientsManagementModule: editStoragePlace(): Succcessful. Storage Place " + storagePlace.getId() + " is edited.");
            return storagePlace.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.RawIngredientsManagementModule: editStoragePlace(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    // return storage place ID
    //        -1L if Storage Place has raw ingredients in it
    //        -2L if an unexpected exception occurred
    @Override
    public Long deleteStoragePlace(Long storagePlaceId) {
        try {
            StoragePlaceEntity storagePlace = em.find(StoragePlaceEntity.class, storagePlaceId);
            if (!storagePlace.getIngredients().isEmpty()) {
                System.out.println("SessionBean.KM.RawIngredientsManagementModule: deleteStoragePlace(): Failed. The Storage Place has raw ingredients in it.");
                return -1L;
            }
            storagePlace.getKitchen().getStoragePlaces().remove(storagePlace);
            storagePlace.setKitchen(null);
            storagePlace.setDeleted(true);
            em.flush();
            return storagePlace.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.RawIngredientsManagementModule: deleteStoragePlace(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -2L;
        }
    }

    @Override
    public List<StoragePlaceEntity> getStoragePlaces(Long kitchenId) {
        return em.find(KitchenEntity.class, kitchenId).getStoragePlaces();
    }

}
