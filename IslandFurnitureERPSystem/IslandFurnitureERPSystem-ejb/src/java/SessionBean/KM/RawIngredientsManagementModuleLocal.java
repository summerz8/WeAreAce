/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.KM;

import Entity.Kitchen.IngredientEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Yoky
 */
@Local
public interface RawIngredientsManagementModuleLocal {

    Long addIngredient(Long kitchenId, String name, Double price, String unit, String remark, Double lotSize, List<Long> storagePlaceIds, Long supplierId);

//    Long addStoragePlaces(Long ingredientId, List<Long> storagePlaceIds);
    
    Long deleteIngredient(Long ingredientId);
    
    Long editIngredient(Long ingredientId, String name, Double price, String unit, String remark, Double lotSize, List<Long> storagePlaces, Long supplierId);

    List<IngredientEntity> getIngredients(Long kitchenId);
}
