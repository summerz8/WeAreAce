/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.IngredientEntity;
import Entity.Kitchen.IngredientSupplierEntity;
import Entity.Kitchen.StoragePlaceEntity;
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

    Long addSupplier(Long kitchenId, String name, String address, String contact, String fax, String remark);

    Long editSupplier(Long ingredientSupplierId, String name, String address, String contact, String fax, String remark);

    Long deleteSupplier(Long ingredientSupplierId);

    List<IngredientSupplierEntity> getSuppliers(Long kitchenId);

    Long addStoragePlace(Long kitchenId, String location);

    Long editStoragePlace(Long storagePlaceId, String location);

    Long deleteStoragePlace(Long storagePlaceId);

    List<StoragePlaceEntity> getStoragePlaces(Long kitchenId);

}
