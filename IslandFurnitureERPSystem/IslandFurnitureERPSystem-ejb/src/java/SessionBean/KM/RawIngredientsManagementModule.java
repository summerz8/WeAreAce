/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.IngredientEntity;
import Entity.Kitchen.IngredientForecastEntity;
import Entity.Kitchen.IngredientIssueEntity;
import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.IngredientSupplierEntity;
import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.StoragePlaceEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    // return Ingredient Issue ID
    //        -1L if unexpected exception occurred
    @Override
    public IngredientIssueEntity generateIngredientIssue(Long ingredientForecastId) {
        try {
            IngredientForecastEntity ingf = em.find(IngredientForecastEntity.class, ingredientForecastId);
            IngredientIssueEntity iis = new IngredientIssueEntity(ingf);
            em.persist(iis);
            em.flush();
            ingf.setIssue(iis);
            em.flush();
            for (IngredientItemEntity iif : ingf.getForecastItems()) {
                IngredientItemEntity iiis = new IngredientItemEntity(iif.getIngredient(), iif.getQuantity());
                iiis.getIngredient().getIssues().add(iis);
                em.persist(iiis);
                em.flush();
                iis.getIssueItems().add(iiis);
                em.flush();
            }
            System.out.println("SessionBean.KM.RawIngredientsManagementModule: generateIngredientIssue(): Successful. New Ingredient issue " + iis.getId() + " is generated.");
            return iis;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.RawIngredientsManagementModule: generateIngredientIssue(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Long confirmIngredientIssue(Long ingredientIssueId) {
        try {
            IngredientIssueEntity iis = em.find(IngredientIssueEntity.class, ingredientIssueId);
            for (IngredientItemEntity iif : iis.getIssueItems()) {
                Double newStock = iif.getIngredient().getStock() - iif.getQuantity();
                if (newStock < 0) {
                    System.out.println("SessionBean.KM.RawIngredientsManagementModule: confirmIngredientIssue(): Failed. Some request issue quantty is larger than the stock.");
                    return -1L;
                }
                iif.getIngredient().setStock(newStock);
            }
            iis.setConfirmed(true);
            em.flush();
            System.out.println("SessionBean.KM.RawIngredientsManagementModule: confirmIngredientIssue(): Successful. Ingredient issue " + iis.getId() + " is confirmed.");
            return iis.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.RawIngredientsManagementModule: confirmIngredientIssue(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -2L;
        }
    }

    @Override
    public IngredientForecastEntity findIngredientForecast(Long kitchenId, Date targetDate) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Calendar targetDateCal = Calendar.getInstance();
            targetDateCal.setTime(targetDate);
            Query q = em.createQuery("SELECT ingf FROM IngredientForecastEntity ingf WHERE ingf.menuItemForecast.kitchen = :kitchen AND ingf.menuItemForecast.targetDate = :targetDate");
            q.setParameter("kitchen", kitchen);
            q.setParameter("targetDate", targetDateCal);
            IngredientForecastEntity ingf = (IngredientForecastEntity) q.getSingleResult();
            System.out.println("SessionBean.KM.DailyDemandForecastingModule: findIngredientForecast(): Successful. Required Ingredient Forecast " + ingf.getId() + " is found.");
            return ingf;
        } catch (NoResultException nex) {
            System.out.println("SessionBean.KM.DailyDemandForecastingModule: findIngredientForecast(): Faild. Required Ingredient Forecast is not found.");
            return null;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.DailyDemandForecastingModule: findIngredientForecast(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public IngredientIssueEntity findIngredientIssue(Long kitchenId, Date targetDate) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Calendar targetDateCal = Calendar.getInstance();
            targetDateCal.setTime(targetDate);
            Query q = em.createQuery("SELECT iis FROM IngredientIssueEntity iis WHERE iis.forecast.menuItemForecast.kitchen = :kitchen AND iis.forecast.menuItemForecast.targetDate = :targetDate");
            q.setParameter("kitchen", kitchen);
            q.setParameter("targetDate", targetDateCal);
            IngredientIssueEntity iis = (IngredientIssueEntity) q.getSingleResult();
            System.out.println("SessionBean.KM.DailyDemandForecastingModule: findIngredientForecast(): Successful. Required Ingredient Forecast " + iis.getId() + " is found.");
            return iis;
        } catch (NoResultException nex) {
            System.out.println("SessionBean.KM.DailyDemandForecastingModule: findIngredientForecast(): Faild. Required Ingredient Forecast is not found.");
            return null;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.DailyDemandForecastingModule: findIngredientForecast(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<IngredientItemEntity> getIngredientIssueItems(Long ingredientIssueId) {
        return em.find(IngredientIssueEntity.class, ingredientIssueId).getIssueItems();
    }

    @Override
    public Long editIngredientIssueItem(Long ingredientForecastItemId, Double quantity) {
        try {
            IngredientItemEntity ii = em.find(IngredientItemEntity.class, ingredientForecastItemId);
            if (ii.getIngredient().getStock() < quantity) {
                System.out.println("SessionBean.KM.DailyDemandForecastingModule: editIngredientIssueItem(): Failed. Request quantity is larger than the stock.");
                return -1L;
            }
            ii.setQuantity(quantity);
            return ii.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.DailyDemandForecastingModule: editIngredientIssueItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -2L;
        }
    }
    
    @Override
    public List<IngredientSupplierEntity> getSuppliers(Long kitchenId) {
        return em.find(KitchenEntity.class, kitchenId).getIngredientSuppliers();
    }
}
