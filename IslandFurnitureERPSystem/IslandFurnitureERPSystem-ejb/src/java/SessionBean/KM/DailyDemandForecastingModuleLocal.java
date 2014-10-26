/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.ComboItemEntity;
import Entity.Kitchen.DishItemEntity;
import Entity.Kitchen.IngredientForecastEntity;
import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.MenuItemForecastEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Yoky
 */
@Local
public interface DailyDemandForecastingModuleLocal {

    IngredientForecastEntity generateIngredientForecast(Long menuItemForecastId);

    List<DishItemEntity> getDishForecastItems(Long menuItemForecastId);

    List<ComboItemEntity> getComboForecastItems(Long menuItemForecastId);

    Long editDishForecastItem(Long dishItemId, Integer quantity);

    Long editComboForecastItem(Long comboItemId, Integer quantity);

    List<IngredientItemEntity> getIngredientForecastItems(Long ingredientForecastId);

    Long editIngredientForecastItem(Long ingredientForecastItemId, Double quantity);

    MenuItemForecastEntity findMenuItemForecast(Long kitchenId, Date targetDate);

    IngredientForecastEntity findIngredientForecast(Long kitchenId, Date targetDate);

    DishItemEntity getDishItem(Long dishItemId);

    ComboItemEntity getComboItem(Long comboItemId);

    LinkedHashMap<Calendar, Integer> getWeeklyDishSales(Long kitchenId, Long dishId);
    
    LinkedHashMap<Calendar, Integer> getWeeklyComboSales(Long kitchenId, Long comboId);

    MenuItemForecastEntity generateMenuItemForecast(Long kitchenId, Date targetDate);
}
