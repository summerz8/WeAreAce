/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.ComboEntity;
import Entity.Kitchen.DishEntity;
import Entity.Kitchen.DishItemEntity;
import Entity.Kitchen.IngredientEntity;
import Entity.Kitchen.IngredientItemEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Yoky
 */
@Local
public interface MenuManagementModuleLocal {

    DishEntity addDish(Long kitchenId, String name, Double price, String remark, Integer recipeQuantity);

    ComboEntity addCombo(Long kitchenId, String name, Double price, String remark);

    Long deleteDish(Long dishId);

    Long deleteCombo(Long comboId);

    Long editDish(Long dishId, String name, Double price, String remark, Integer recipeQuantity);
    
    Long editCombo(Long comboId, String name, Double price, String remark);

    Long addIngredientItem(Long dishId, Long ingredientId, Double quantity);

    Long addDishItem(Long comboId, Long dishId, Integer quantity);

    Long editRecipe(Long ingredientItemId, Double quantity);
    
    Long editDishItem(Long dishItemId, Integer quantity);
    
    Long deleteRecipeItem(Long dishId, Long ingredientItemId);
    
    Long deleteDishItem(Long comboId, Long dishItemId);

    List<IngredientItemEntity> getRecipeItems(Long dishId);
    
    List<DishItemEntity> getDishItems(Long comboId);
    
    List<DishEntity> getDishes(Long kitchenId);
    
    List<ComboEntity> getCombos(Long kitchenId);

    List<IngredientEntity> getIngredients(Long kitchenId);
}
