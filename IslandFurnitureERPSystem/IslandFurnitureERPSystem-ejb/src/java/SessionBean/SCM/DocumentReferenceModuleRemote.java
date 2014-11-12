/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.GoodsReceiptEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Yoky
 */
@Remote
public interface DocumentReferenceModuleRemote {

    public PlannedOrderEntity viewPlannedOrder(Long id);

    public ProductionPlanEntity viewProductionPlan(Long id);

    public List viewAllBlockedStock();

    public List viewAllReturnedProduct();

    public PurchaseOrderEntity viewPurchaseOrder(Long id);

    public GoodsReceiptEntity viewGoodsReceipt(Long id);

    public ContractEntity viewContract(Long id);

    public SupplierEntity viewSupplier(Long id);

    public List viewAllProductionPlans();

    public List viewAllPlannedOrders();

    public List viewAllPurchaseOrders();

    public List viewAllGoodsReceipts();

    public List viewAllContracts();

    public List viewAllSuppliers();

    public List viewProductionPlansByFactory(long factoryId);

    public List viewPlannedOrdersByFactory(long factoryId);

    public List viewBlockedStockByFactory(long factoryId);

    public List viewReturnedProductByFactory(long factoryId);

    public List viewPurchaseOrdersByFactory(long factoryId);

    public List viewGoodsReceiptsByFactory(long factoryId);

    public List viewContractsByFactory(long factoryId);

    public List viewSupplierByFactory(long factoryId);

}
