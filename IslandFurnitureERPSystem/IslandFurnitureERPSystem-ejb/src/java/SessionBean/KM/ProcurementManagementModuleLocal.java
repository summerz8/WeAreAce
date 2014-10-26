/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.KM;

import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.IngredientPurchaseOrderEntity;
import Entity.Kitchen.IngredientSupplierEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Yoky
 */
@Local
public interface ProcurementManagementModuleLocal {

    IngredientPurchaseOrderEntity generateIngredientPurchaseOrder(Long ingredientForecastId);

    Long generateIngredientReceipt(Long ingredientPurchaseOrderId);

    Long confirmIngredientPurchaseOrder(Long ingredientPurchaseOrderId, Double acturalTotal);

    Long cancelIngredientPurchaseOrder(Long ingredientPurchaseOrderId);

    Long editPurchaseItem(Long ingredientPurchaseOrderId, Long purchaseItemId, Double quantity);

    List<IngredientItemEntity> getPurchaseItems(Long purchaseOrderId);
    
    IngredientPurchaseOrderEntity findIngredientPurchaseOrder(Long kitchenId, Date targetDate);
    
    Long addSupplier(Long kitchenId, String name, String address, String contact, String fax, String remark);

    Long editSupplier(Long ingredientSupplierId, String name, String address, String contact, String fax, String remark);

    Long deleteSupplier(Long ingredientSupplierId);

    List<IngredientSupplierEntity> getSuppliers(Long kitchenId);
}
