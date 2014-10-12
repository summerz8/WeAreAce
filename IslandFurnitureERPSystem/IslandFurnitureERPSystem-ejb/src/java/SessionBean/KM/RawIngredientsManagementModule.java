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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
//            System.out.println("2. selected SP: ");
//            for (StoragePlaceEntity o : storagePlaces) {
//                System.out.println("----------SP: " + o.getLocation());
//                System.out.println(o.getClass());
//            }
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! ");
            IngredientEntity ingredient = new IngredientEntity(name, price, unit, remark, lotSize, storagePlaces, kitchen, supplier);
            em.persist(ingredient);

            for (StoragePlaceEntity storagePlace : storagePlaces) {
                ingredient.getStoragePlaces().add(storagePlace);
                storagePlace.getIngredients().add(ingredient);
            }

//            for (StoragePlaceEntity storagePlace : storagePlaces) {
//                
//            }
//            System.out.println("5. selected SP: ");
//            for (StoragePlaceEntity o : ingredient.getStoragePlaces()) {
//                System.out.println("----------SP: " + o.getLocation());
//                System.out.println(o.getClass());
//                System.out.println("FOR EACH SP-------------------: ");
//                for (IngredientEntity i : o.getIngredients()) {
//                    System.out.println("---ING: " + i.getName());
//                    System.out.println(i.getClass());
//                }
//                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//            }
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! ");

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

    @Override
    public Long editIngredient(Long ingredientId, String name, Double price, String unit, String remark, Double lotSize, List<Long> storagePlaceIds, Long supplierId) {
        try {
            IngredientEntity ingredient = em.find(IngredientEntity.class, ingredientId);
//            Iterator<StoragePlaceEntity> it = ingredient.getStoragePlaces().iterator();
//            while (it.hasNext()) {
//                StoragePlaceEntity storagePlace = it.next();
//                storagePlace.getIngredients().remove(ingredient);
//            }
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
}
