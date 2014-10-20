/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.KM;

import Entity.Kitchen.KitchenOrderEntity;
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
}
