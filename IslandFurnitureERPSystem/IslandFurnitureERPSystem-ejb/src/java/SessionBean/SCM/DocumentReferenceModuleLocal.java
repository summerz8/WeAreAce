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
import javax.ejb.Local;

/**
 *
 * @author Yoky
 */
@Local
public interface DocumentReferenceModuleLocal {

    PlannedOrderEntity ViewPlannedOrder(Long id);

    ProductionPlanEntity ViewProductionPlan(Long id);

    List viewBlockedStock();

    List viewReturnedProduct();

    PurchaseOrderEntity viewPurchaseOrder(Long id);

    GoodsReceiptEntity viewGoodsReceipt(Long id);

    ContractEntity viewContract(Long id);

    SupplierEntity viewSupplier(Long id);
    
}
