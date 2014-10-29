/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.ComboEntity;
import Entity.Kitchen.ComboItemEntity;
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

    public DishEntity addDish(Long kitchenId, String name, Double price, String remark, Integer recipeQuantity);

    public ComboEntity addCombo(Long kitchenId, String name, Double price, String remark);

    public Long deleteDish(Long dishId);

    public Long deleteCombo(Long comboId);

    public Long editDish(Long dishId, String name, Double price, String remark, Integer recipeQuantity);

    public Long editCombo(Long comboId, String name, Double price, String remark);

    public Long addIngredientItem(Long dishId, Long ingredientId, Double quantity);

    public Long addDishItem(Long comboId, Long dishId, Integer quantity);

    public Long editRecipe(Long ingredientItemId, Double quantity);

    public Long editDishItem(Long dishItemId, Integer quantity);

    public Long deleteRecipeItem(Long dishId, Long ingredientItemId);

    public Long deleteDishItem(Long comboId, Long dishItemId);

    public List<IngredientItemEntity> getRecipeItems(Long dishId);

    public List<DishItemEntity> getDishItems(Long comboId);

    public List<DishEntity> getDishes(Long kitchenId);

    public List<ComboEntity> getCombos(Long kitchenId);

    public List<IngredientEntity> getIngredients(Long kitchenId);
}
