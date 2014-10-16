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
import javax.ejb.Local;

/**
 *
 * @author Yoky
 */
@Local
public interface KitchenSupportLocal {

//    KitchenEntity findKitchen(String id);

    DishEntity findDish(String id);

    IngredientEntity findIngredient(String id);

    IngredientSupplierEntity findIngredientSupplier(String id);

    StoragePlaceEntity findStoragePlace(String id);

    ComboEntity findCombo(String name);
    
    KitchenEntity findKitchenByStoreId(Long storeId);
    
}
