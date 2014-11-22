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

    public KitchenOrderEntity createOrder(Long kitchenId, Long memberId, String storestaffId,String POSid);

    public Long addDishItem(Long orderId, Long dishId, Integer quantity);

    public Long addComboItem(Long orderId, Long comboId, Integer quantity);

    public Long confirmOrder(Long orderId);
    
    public Double checkout(Long orderId, Double received);

    public Long cancelOrder(Long orderId);

    public Long serveOrder(Long orderId);

    public List<KitchenOrderEntity> getUnfulfilledOrders(Long kitchenId);

    public List<KitchenOrderEntity> getDailyOrders(Long kitchenId, Date selectedDate);

    public DailySalesEntity findDailySales(Long kitchenId, Date selectedDate);

    public List<KitchenOrderEntity> getUnservedOrders(Long kitchenId);

    public Long fulfillDishItem(Long orderId, Long detailedDishItemId);

    public Long deleteDishItem(Long orderId, Long dishItemId);

    public Long deleteComboItem(Long orderId, Long comboItemId);

    public List<DishItemEntity> findDailySalesDishItems(Long dailySalesId);

    public List<ComboItemEntity> findDailySalesComboItems(Long dailySalesId);

    public List<IngredientItemEntity> findRecipe(Long dishId);

    public KitchenOrderEntity findOrderById(Long KitchenOrderId);
}
