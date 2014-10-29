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
import Entity.Kitchen.StoragePlaceEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Yoky
 */
@Local
public interface RawIngredientsManagementModuleLocal {

    Long addIngredient(Long kitchenId, String name, Double price, String unit, String remark, Double lotSize, List<Long> storagePlaceIds, Long supplierId);

    Long deleteIngredient(Long ingredientId);

    Long editIngredient(Long ingredientId, String name, Double price, String unit, String remark, Double lotSize, List<Long> storagePlaces, Long supplierId);

    List<IngredientEntity> getIngredients(Long kitchenId);

    Long addStoragePlace(Long kitchenId, String location);

    Long editStoragePlace(Long storagePlaceId, String location);

    Long deleteStoragePlace(Long storagePlaceId);

    List<StoragePlaceEntity> getStoragePlaces(Long kitchenId);

    IngredientIssueEntity generateIngredientIssue(Long ingredientForecastId);

    Long confirmIngredientIssue(Long ingredientIssueId);

    IngredientForecastEntity findIngredientForecast(Long kitchenId, Date targetDate);
    
    IngredientIssueEntity findIngredientIssue(Long kitchenId, Date targetDate);
    
    List<IngredientItemEntity> getIngredientIssueItems(Long ingredientIssueId);
    
    Long editIngredientIssueItem(Long ingredientForecastItemId, Double quantity);
    
    List<IngredientSupplierEntity> getSuppliers(Long kitchenId);
}
