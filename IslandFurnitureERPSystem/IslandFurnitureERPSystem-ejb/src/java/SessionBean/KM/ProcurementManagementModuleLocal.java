/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.IngredientPurchaseOrderEntity;
import Entity.Kitchen.IngredientPurchaseOrderToSupplierEntity;
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

    public IngredientPurchaseOrderEntity generateIngredientPurchaseOrder(Long ingredientForecastId);

    public Long generateIngredientReceipt(Long ingredientPurchaseOrderId);

    public Long generateIngredientPurchaseOrderToSuppliers(Long ingredientPurchaseOrderId);

    public Long cancelIngredientPurchaseOrder(Long ingredientPurchaseOrderId);

    public Long editPurchaseItem(Long ingredientPurchaseOrderId, Long purchaseItemId, Double quantity);

    public List<IngredientItemEntity> getPurchaseItems(Long purchaseOrderId);

    public IngredientPurchaseOrderEntity findIngredientPurchaseOrder(Long kitchenId, Date targetDate);

    public Long addSupplier(Long kitchenId, String name, String address, String contact, String fax, String remark);

    public Long editSupplier(Long ingredientSupplierId, String name, String address, String contact, String fax, String remark);

    public Long deleteSupplier(Long ingredientSupplierId);

    public List<IngredientSupplierEntity> getSuppliers(Long kitchenId);

    public Double getIPOTotal(Long IPOId);

    public IngredientPurchaseOrderEntity findIngredientPurchaseOrderById(Long IPOId);

//    public List<IngredientPurchaseOrderToSupplierEntity> findIngredientPurchaseOrdersToSuppliersByDate(Long kitchenId, Date selectedDate);

    public List<IngredientPurchaseOrderToSupplierEntity> findIngredientPurchaseOrdersToSuppliersByIPO(Long IPOId);

    public Long confirmAllIPOSs(Long IPOId);

    public Long confirmIPOS(Long IPOSId);

    Long editIPOSActualTotal(Long IPOSId, Double actualTotal);

}
