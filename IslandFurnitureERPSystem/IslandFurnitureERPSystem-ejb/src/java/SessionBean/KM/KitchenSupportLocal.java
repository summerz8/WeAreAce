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

    public DishEntity findDish(Long kitchenId, String id);

    public IngredientEntity findIngredient(Long kitchenId, String id);

    public IngredientSupplierEntity findIngredientSupplier(Long kitchenId, String id);

    public StoragePlaceEntity findStoragePlace(Long kitchenId, String id);

    public ComboEntity findCombo(Long kitchenId, String name);
    
    public KitchenEntity findKitchenByStoreId(Long storeId);
    
}
