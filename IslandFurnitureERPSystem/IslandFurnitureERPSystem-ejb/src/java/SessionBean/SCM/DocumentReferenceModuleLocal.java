/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.Factory.FactoryBin.FactoryBinStoredProductEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.GoodsReceiptEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Yoky
 */
@Local
public interface DocumentReferenceModuleLocal {

    PlannedOrderEntity viewPlannedOrder(Long id);

    ProductionPlanEntity viewProductionPlan(Long id);

    List viewAllBlockedStock();

    List viewAllReturnedProduct();

    PurchaseOrderEntity viewPurchaseOrder(Long id);

    GoodsReceiptEntity viewGoodsReceipt(Long id);

    ContractEntity viewContract(Long id);

    SupplierEntity viewSupplier(Long id);

    List viewAllProductionPlans();

    List viewAllPlannedOrders();

    List viewAllPurchaseOrders();

    List viewAllGoodsReceipts();

    List viewAllContracts();

    List viewAllSuppliers();

    List viewProductionPlansByFactory(long factoryId);

    List viewPlannedOrdersByFactory(long factoryId);

    List viewBlockedStockByFactory(long factoryId);

    List viewReturnedProductByFactory(long factoryId);

    List viewPurchaseOrdersByFactory(long factoryId);

    List viewGoodsReceiptsByFactory(long factoryId);

    List viewContractsByFactory(long factoryId);

    List viewSupplierByFactory(long factoryId);

}
