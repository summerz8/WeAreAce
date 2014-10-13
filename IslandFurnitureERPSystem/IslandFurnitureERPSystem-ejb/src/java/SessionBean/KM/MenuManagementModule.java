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
import Entity.Kitchen.KitchenEntity;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yoky
 */
@Stateful
public class MenuManagementModule implements MenuManagementModuleLocal {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    @Override
    public DishEntity addDish(Long kitchenId, String name, Double price, String remark, Integer recipeQuantity) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            DishEntity dish = new DishEntity(name, price, remark, recipeQuantity, kitchen);
            em.persist(dish);
            kitchen.getDishes().add(dish);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: addDish(): Succcessful. New dish " + dish.getId() + " is added.");
            return dish;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: addDish(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ComboEntity addCombo(Long kitchenId, String name, Double price, String remark) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            ComboEntity combo = new ComboEntity(name, price, remark, kitchen);
            em.persist(combo);
            kitchen.getCombos().add(combo);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: addCombo(): Succcessful. New combo " + combo.getId() + " is added.");
            return combo;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: addCombo(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    // return dish ID
    //        -1L if it is contained in some combo
    //        -2L if an unexpected exception occurred
    @Override
    public Long deleteDish(Long dishId) {
        try {
            DishEntity dish = em.find(DishEntity.class, dishId);
            if (!dish.getCombos().isEmpty()) {
                System.out.println("SessionBean.KM.MenuManagementModule: deleteDish(): Failed. The dish is contained in at least one combo.");
                return -1L;
            }
            dish.setDeleted(true);
            for (IngredientItemEntity ii : dish.getRecipe()) {
                ii.getIngredient().getDishes().remove(dish);
            }
            dish.getRecipe().clear();
            dish.getKitchen().getDishes().remove(dish);
            dish.setKitchen(null);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: deleteDish(): Succeessful. Dish " + dish.getId() + " is deleted.");
            return dish.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: deleteDish(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -2L;
        }
    }

    // return combo ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long deleteCombo(Long comboId) {
        try {
            ComboEntity combo = em.find(ComboEntity.class, comboId);
            for (DishItemEntity di : combo.getDishes()) {
                di.getDish().getCombos().remove(combo);
            }
            combo.getDishes().clear();
            combo.setDeleted(true);
            combo.getKitchen().getCombos().remove(combo);
            combo.setKitchen(null);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: deleteCombo(): Succeessful. Combo " + combo.getId() + " is deleted.");
            return combo.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: deleteCombo(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    // return dish ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long editDish(Long dishId, String name, Double price, String remark, Integer recipeQuantity) {
        try {
            DishEntity dish = em.find(DishEntity.class, dishId);
            dish.setName(name);
            dish.setPrice(price);
            dish.setRemark(remark);
            dish.setRecipeQuantity(recipeQuantity);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: editDish(): Succcessful. Dish " + dish.getId() + " is edited.");
            return dish.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: editDish(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }
    
    // return dish ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long editCombo(Long comboId, String name, Double price, String remark) {
        try {
            ComboEntity combo = em.find(ComboEntity.class, comboId);
            combo.setName(name);
            combo.setPrice(price);
            combo.setRemark(remark);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: editCombo(): Succcessful. Combo " + combo.getId() + " is edited.");
            return combo.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: editCombo(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    // return ingredient item ID
    //        -1L if the ingredient is already in the recipe
    //        -2L if an unexpected exception occurred
    @Override
    public Long addIngredientItem(Long dishId, Long ingredientId, Double quantity) {
        try {
            DishEntity dish = em.find(DishEntity.class, dishId);
            IngredientEntity ingredient = em.find(IngredientEntity.class, ingredientId);
            for (IngredientItemEntity i : dish.getRecipe()) {
                if (i.getIngredient().getId().equals(ingredient.getId())) {
                    System.out.println("SessionBean.KM.MenuManagementModule: addIngredientItem(): Failed. The ingredient " + ingredient.getName() + " is already in the recipe.");
                    return -1L;
                }
            }
            IngredientItemEntity ii = new IngredientItemEntity(ingredient, quantity);
            em.persist(ii);
            ingredient.getDishes().add(dish);
            dish.getRecipe().add(ii);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: addIngredientItem(): Succcessful. New ingredient item " + ii.getId() + " is added.");
            return ii.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: addIngredientItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -2L;
        }
    }

    // return dish item ID
    //        -1L if the dish is already in the combo
    //        -2L if an unexpected exception occurred
    @Override
    public Long addDishItem(Long comboId, Long dishId, Integer quantity) {
        try {
            ComboEntity combo = em.find(ComboEntity.class, comboId);
            DishEntity dish = em.find(DishEntity.class, dishId);
            for (DishItemEntity d : combo.getDishes()) {
                if (d.getDish().getId().equals(dish.getId())) {
                    System.out.println("SessionBean.KM.MenuManagementModule: addDishItem(): Failed. The dish " + dish.getName() + " is already in the combo.");
                    return -1L;
                }
            }
            DishItemEntity di = new DishItemEntity(dish, quantity);
            em.persist(di);
            dish.getCombos().add(combo);
            combo.getDishes().add(di);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: addDishItem(): Succcessful. New dish item " + di.getId() + " is added.");
            return di.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: addDishItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }

    }

    @Override
    public Long editRecipe(Long ingredientItemId, Double quantity) {
        try {
            IngredientItemEntity ii = em.find(IngredientItemEntity.class, ingredientItemId);
            ii.setQuantity(quantity);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: editRecipe(): Succeessful. Recipe Item " + ingredientItemId + " is edited.");
            return ii.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: editRecipe(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }
    
    @Override
    public Long editDishItem(Long dishItemId, Integer quantity) {
        try {
            DishItemEntity di = em.find(DishItemEntity.class, dishItemId);
            di.setQuantity(quantity);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: editDishItem(): Succeessful. Dish Item " + dishItemId + " is edited.");
            return di.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: editDishItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    // return ingredientitem ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long deleteRecipeItem(Long dishId, Long ingredientItemId) {
        try {
            DishEntity dish = em.find(DishEntity.class, dishId);
            IngredientItemEntity ii = em.find(IngredientItemEntity.class, ingredientItemId);
            dish.getRecipe().remove(ii);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: deleteRecipeItem(): Succeessful. Recipe Item " + ingredientItemId + " is deleted.");
            return dish.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: deleteRecipeItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }
    
    // return ingredientitem ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long deleteDishItem(Long comboId, Long dishItemId) {
        try {
            ComboEntity combo = em.find(ComboEntity.class, comboId);
            DishItemEntity di = em.find(DishItemEntity.class, dishItemId);
            combo.getDishes().remove(di);
            em.flush();
            System.out.println("SessionBean.KM.MenuManagementModule: deleteDishItem(): Succeessful. Dish Item " + dishItemId + " is deleted.");
            return combo.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.MenuManagementModule: deleteDishItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public List<IngredientItemEntity> getRecipeItems(Long dishId) {
        return em.find(DishEntity.class, dishId).getRecipe();
    }
    
    @Override
    public List<DishItemEntity> getDishItems(Long comboId) {
        return em.find(ComboEntity.class, comboId).getDishes();
    }

    @Override
    public List<DishEntity> getDishes(Long kitchenId) {
        return em.find(KitchenEntity.class, kitchenId).getDishes();
    }
    
    @Override
    public List<ComboEntity> getCombos(Long kitchenId) {
        return em.find(KitchenEntity.class, kitchenId).getCombos();
    }

    @Override
    public List<IngredientEntity> getIngredients(Long kitchenId) {
        return em.find(KitchenEntity.class, kitchenId).getIngredients();
    }
    
    
}
