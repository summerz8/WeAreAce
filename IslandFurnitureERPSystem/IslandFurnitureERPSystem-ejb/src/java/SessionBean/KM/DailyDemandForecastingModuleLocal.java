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

    public IngredientForecastEntity generateIngredientForecast(Long menuItemForecastId);

    public List<DishItemEntity> getDishForecastItems(Long menuItemForecastId);

    public List<ComboItemEntity> getComboForecastItems(Long menuItemForecastId);

    public Long editDishForecastItem(Long dishItemId, Integer quantity);

    public Long editComboForecastItem(Long comboItemId, Integer quantity);

    public List<IngredientItemEntity> getIngredientForecastItems(Long ingredientForecastId);

    public Long editIngredientForecastItem(Long ingredientForecastItemId, Double quantity);

    public MenuItemForecastEntity findMenuItemForecast(Long kitchenId, Date targetDate);

    public IngredientForecastEntity findIngredientForecast(Long kitchenId, Date targetDate);

    public DishItemEntity getDishItem(Long dishItemId);

    public ComboItemEntity getComboItem(Long comboItemId);

    public LinkedHashMap<Calendar, Integer> getWeeklyDishSales(Long kitchenId, Long dishId);
    
    public LinkedHashMap<Calendar, Integer> getWeeklyComboSales(Long kitchenId, Long comboId);
}
