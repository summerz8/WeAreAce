/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.ComboEntity;
import Entity.Kitchen.ComboItemEntity;
import Entity.Kitchen.DailySalesEntity;
import Entity.Kitchen.DishEntity;
import Entity.Kitchen.DishItemEntity;
import Entity.Kitchen.IngredientEntity;
import Entity.Kitchen.IngredientForecastEntity;
import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.KitchenEntity;
import Entity.Kitchen.MenuItemForecastEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
public class DailyDemandForecastingModule implements DailyDemandForecastingModuleLocal {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    public DailyDemandForecastingModule() {
    }

    // return menuItemForecast ID
    //        -1L if unexpected exception occurred
    @Override
    public IngredientForecastEntity generateIngredientForecast(Long menuItemForecastId) {
        try {
            MenuItemForecastEntity menuItemForecast = em.find(MenuItemForecastEntity.class, menuItemForecastId);
            HashMap<IngredientEntity, Double> ingredientForecastMap = new HashMap<>();
            for (IngredientEntity i : menuItemForecast.getKitchen().getIngredients()) {
                ingredientForecastMap.put(i, 0.0);
            }

            for (DishItemEntity di : menuItemForecast.getDishForecastItems()) {
                for (IngredientItemEntity ii : di.getDish().getRecipe()) {
                    Double oldQuantity = ingredientForecastMap.get(ii.getIngredient());
                    Double newQuantity = oldQuantity + di.getQuantity() / di.getDish().getRecipeQuantity() * ii.getQuantity();
                    ingredientForecastMap.put(ii.getIngredient(), newQuantity);
                }
            }

            for (ComboItemEntity ci : menuItemForecast.getComboForecastItems()) {
                for (DishItemEntity di : ci.getCombo().getDishes()) {
                    for (IngredientItemEntity ii : di.getDish().getRecipe()) {
                        Double oldQuantity = ingredientForecastMap.get(ii.getIngredient());
                        Double newQuantity = oldQuantity + ci.getQuantity() * di.getQuantity() / di.getDish().getRecipeQuantity() * ii.getQuantity();
                        ingredientForecastMap.put(ii.getIngredient(), newQuantity);
                    }
                }
            }

            IngredientForecastEntity ingredientForecast = new IngredientForecastEntity(menuItemForecast);
            em.persist(ingredientForecast);
            em.flush();
            menuItemForecast.setIngredientForecast(ingredientForecast);
            for (Map.Entry<IngredientEntity, Double> e : ingredientForecastMap.entrySet()) {
                IngredientItemEntity ii = new IngredientItemEntity(e.getKey(), e.getValue());
                em.persist(ii);
                ingredientForecast.getForecastItems().add(ii);
                ii.getIngredient().getForecasts().add(ingredientForecast);
                em.flush();
            }
            System.out.println("SessionBean.KM.DailyDemandForecastingModule: generateIngredientForecast(): Successful. New Ingredient Forecast " + ingredientForecast.getId() + " is generated.");
            return ingredientForecast;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.DailyDemandForecastingModule: generateIngredientForecast(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DishItemEntity> getDishForecastItems(Long menuItemForecastId) {
        return em.find(MenuItemForecastEntity.class, menuItemForecastId).getDishForecastItems();
    }

    @Override
    public List<ComboItemEntity> getComboForecastItems(Long menuItemForecastId) {
        return em.find(MenuItemForecastEntity.class, menuItemForecastId).getComboForecastItems();
    }

    @Override
    public Long editDishForecastItem(Long dishItemId, Integer quantity) {
        try {
            DishItemEntity di = em.find(DishItemEntity.class, dishItemId);
            di.setQuantity(quantity);
            return di.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.DailyDemandForecastingModule: editDishForecastItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public Long editComboForecastItem(Long comboItemId, Integer quantity) {
        try {
            ComboItemEntity ci = em.find(ComboItemEntity.class, comboItemId);
            ci.setQuantity(quantity);
            return ci.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.DailyDemandForecastingModule: editComboForecastItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public List<IngredientItemEntity> getIngredientForecastItems(Long ingredientForecastId) {
        return em.find(IngredientForecastEntity.class, ingredientForecastId).getForecastItems();
    }

    @Override
    public Long editIngredientForecastItem(Long ingredientForecastItemId, Double quantity) {
        try {
            IngredientItemEntity ii = em.find(IngredientItemEntity.class, ingredientForecastItemId);
            ii.setQuantity(quantity);
            System.out.println("SessionBean.KM.DailyDemandForecastingModule: editIngredientForecastItem(): Successful. Ingredient Forecast item " + ii.getId() + " is edited.");
            return ii.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.DailyDemandForecastingModule: editIngredientForecastItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

//    
    @Override
    public MenuItemForecastEntity findMenuItemForecast(Long kitchenId, Date targetDate) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Calendar targetDateCal = Calendar.getInstance();
            targetDateCal.setTime(targetDate);
            Query q = em.createQuery("SELECT mif FROM MenuItemForecastEntity mif WHERE mif.kitchen = :kitchen AND mif.targetDate = :targetDate");
            q.setParameter("kitchen", kitchen);
            q.setParameter("targetDate", targetDateCal);
            MenuItemForecastEntity mif = (MenuItemForecastEntity) q.getSingleResult();
            System.out.println("SessionBean.KM.DailyDemandForecastingModule: findMenuItemForecast(): Successful. Required Menu Item Forecast " + mif.getId() + " is found.");
            return mif;
        } catch (NoResultException nex) {
            System.out.println("SessionBean.KM.DailyDemandForecastingModule: findMenuItemForecast(): Faild. Required Menu Item Forecast is not found.");
            return null;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.DailyDemandForecastingModule: findMenuItemForecast(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
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
    public DishItemEntity getDishItem(Long dishItemId) {
        return em.find(DishItemEntity.class, dishItemId);
    }

    @Override
    public ComboItemEntity getComboItem(Long comboItemId) {
        return em.find(ComboItemEntity.class, comboItemId);
    }

    @Override
    public LinkedHashMap<Calendar, Integer> getWeeklyDishSales(Long kitchenId, Long dishId) {
        try {
            System.out.println("getWeeklyDishSales#################");
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            DishEntity d = em.find(DishEntity.class, dishId);
            LinkedHashMap<Calendar, Integer> weeklyDishSales = new LinkedHashMap<>();
            for (int i = -7; i < 0; i++) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, i);
                Query q = em.createQuery("SELECT ds FROM DailySalesEntity ds WHERE ds.kitchen = :kitchen AND ds.salesDate = :cal");
                q.setParameter("kitchen", kitchen);
                q.setParameter("cal", cal);
                DailySalesEntity ds = (DailySalesEntity) q.getSingleResult();
                for (DishItemEntity di : ds.getDishes()) {
                    if (di.getDish().equals(d)) {
                        weeklyDishSales.put(cal, di.getQuantity());
                        break;
                    }
                }
            }
            return weeklyDishSales;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.DailyDemandForecastingModule: getWeeklyDishSales(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public LinkedHashMap<Calendar, Integer> getWeeklyComboSales(Long kitchenId, Long comboId) {
        try {
            System.out.println("getWeeklyComboSales#################");
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            ComboEntity c = em.find(ComboEntity.class, comboId);
            LinkedHashMap<Calendar, Integer> weeklyComboSales = new LinkedHashMap<>();
            for (int i = -7; i < 0; i++) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, i);
                Query q = em.createQuery("SELECT ds FROM DailySalesEntity ds WHERE ds.kitchen = :kitchen AND ds.salesDate = :cal");
                q.setParameter("kitchen", kitchen);
                q.setParameter("cal", cal);
                DailySalesEntity ds = (DailySalesEntity) q.getSingleResult();
                for (ComboItemEntity ci : ds.getCombos()) {
                    if (ci.getCombo().equals(c)) {
                        weeklyComboSales.put(cal, ci.getQuantity());
                        break;
                    }
                }
            }
            return weeklyComboSales;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.DailyDemandForecastingModule: getWeeklyComboSales(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
        
        
    }

    @Override
    public MenuItemForecastEntity generateMenuItemForecast(Long kitchenId, Date targetDate) {
        return null;
    }
}
