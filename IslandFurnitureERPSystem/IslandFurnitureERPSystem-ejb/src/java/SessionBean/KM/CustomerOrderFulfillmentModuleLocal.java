/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.KM;

import Entity.Kitchen.ComboItemEntity;
import Entity.Kitchen.DailySalesEntity;
import Entity.Kitchen.DishItemEntity;
import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.KitchenOrderEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Yoky
 */
@Local
public interface CustomerOrderFulfillmentModuleLocal {

    KitchenOrderEntity createOrder(Long kitchenId);
    
    Long addDishItem(Long orderId, Long dishId, Integer quantity);
    
    Long addComboItem(Long orderId, Long comboId, Integer quantity);
    
    Long confirmOrder(Long orderId);
    
    Long cancelOrder(Long orderId);

    Long serveOrder(Long orderId);

    List<KitchenOrderEntity> getCurrentDateUnfulfilledOrder(Long kitchenId);

    List<KitchenOrderEntity> getDailyOrders(Long kitchenId, Date selectedDate);
    
    DailySalesEntity findDailySales(Long kitchenId, Date selectedDate);
    
    List<KitchenOrderEntity> getCurrentDateUnservedOrder(Long kitchenId);
    
    Long fulfillDishItem(Long orderId, Long detailedDishItemId);
    
    Long deleteDishItem(Long orderId, Long dishItemId);
    
    Long deleteComboItem(Long orderId, Long comboItemId);

    List<DishItemEntity> findDailySalesDishItems(Long dailySalesId);
    
    List<ComboItemEntity> findDailySalesComboItems(Long dailySalesId);

    List<IngredientItemEntity> findRecipe(Long dishId);
}
