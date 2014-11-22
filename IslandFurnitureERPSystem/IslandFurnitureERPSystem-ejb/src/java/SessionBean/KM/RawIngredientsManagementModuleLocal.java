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

    public Long addIngredient(Long kitchenId, String name, Double price, String unit, String remark, Double lotSize, List<Long> storagePlaceIds, Long supplierId);

    public Long deleteIngredient(Long ingredientId);

    public Long editIngredient(Long ingredientId, String name, Double price, String unit, String remark, Double lotSize, List<Long> storagePlaces, Long supplierId);

    public List<IngredientEntity> getIngredients(Long kitchenId);

    public Long addStoragePlace(Long kitchenId, String location);

    public Long editStoragePlace(Long storagePlaceId, String location);

    public Long deleteStoragePlace(Long storagePlaceId);

    public List<StoragePlaceEntity> getStoragePlaces(Long kitchenId);

    public IngredientIssueEntity generateIngredientIssue(Long ingredientForecastId);

    public Long confirmIngredientIssue(Long ingredientIssueId);

    public IngredientForecastEntity findIngredientForecast(Long kitchenId, Date targetDate);

    public IngredientIssueEntity findIngredientIssue(Long kitchenId, Date targetDate);

    public List<IngredientItemEntity> getIngredientIssueItems(Long ingredientIssueId);

    public Long editIngredientIssueItem(Long ingredientForecastItemId, Double quantity);

    public List<IngredientSupplierEntity> getSuppliers(Long kitchenId);

    public Long issueMoreIngredient(Long ingredientId, Double quantity);

    public IngredientIssueEntity getIngredientIssue(Long ingredientIssueId);
}
