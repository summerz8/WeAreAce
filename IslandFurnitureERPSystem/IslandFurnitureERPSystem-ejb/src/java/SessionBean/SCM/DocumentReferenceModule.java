/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.GoodsReceiptEntity;
import Entity.Factory.SCM.InboundMovementEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import Entity.Factory.SCM.SupplierEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Yoky
 */
@Stateful
public class DocumentReferenceModule implements DocumentReferenceModuleLocal {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    @Override
    public List<ArrayList> ViewProductionPlan(Long id) {
        Query q = em.createQuery("SELECT p FROM purchaseOrder p WHERE p.productionPlanId = :id");
        q.setParameter("id", id);

        Object p = q.getResultList().get(0);

        if (p == null) {
            return null;
        }

        ProductionPlanEntity productionPlan = (ProductionPlanEntity) p;
        List productionPlanInfo = new ArrayList();
        productionPlanInfo.add(0, productionPlan.getProductionPlanId());
        productionPlanInfo.add(1, productionPlan.getProduct().getFactoryProductId());
        productionPlanInfo.add(2, productionPlan.getProduct().getProduct().getName());
        productionPlanInfo.add(3, productionPlan.getTargetSalesStartDate());
        productionPlanInfo.add(4, productionPlan.getTargetSalesEndDate());
        productionPlanInfo.add(5, productionPlan.getQuantity());
        productionPlanInfo.add(5, productionPlan.getQuantity());
        productionPlanInfo.add(6, productionPlan.getGenerateDate());
        productionPlanInfo.add(7, productionPlan.getConfirmDate());
        productionPlanInfo.add(8, productionPlan.getStatus());
        productionPlanInfo.add(9, productionPlan.getRemark());
        
//       productionPlanInfo.add(, productionPlan);    // need to add planned order

        return productionPlanInfo;
    }

    @Override
    public List ViewPlannedOrder(Long id) {
        Query q = em.createQuery("SELECT p FROM PlannedOrder p WHERE p.plannedOrderId = :id");
        q.setParameter("id", id);

        Object p = q.getResultList().get(0);

        if (p == null) {
            return null;
        }

        PlannedOrderEntity plannedOrder = (PlannedOrderEntity) p;
        List plannedOrderInfo = new ArrayList();

        plannedOrderInfo.add(0, plannedOrder.getPlannedOrderId());
        plannedOrderInfo.add(1, plannedOrder.getProductionPlan().getProductionPlanId());
        plannedOrderInfo.add(2, plannedOrder.getDate());
        plannedOrderInfo.add(3, plannedOrder.getTargetSalesStartDate());
        plannedOrderInfo.add(4, plannedOrder.getTargetSalesEndDate());
        plannedOrderInfo.add(5, plannedOrder.getStatus());

        List rawMaterialList = plannedOrder.getRawMaterialAmount();
        List items = new ArrayList();
        for (Object o : rawMaterialList) {
            FactoryRawMaterialAmountEntity rma = (FactoryRawMaterialAmountEntity) o;
            List item = new ArrayList();
            item.add(0, rma.getRawMaterialAmountId());
            item.add(1, rma.getFactoryRawMaterial().getRawMaterial().getMaterialId());
            item.add(2, rma.getAmount());
            item.add(3, rma.getUnit());
            items.add(item);
        }

        plannedOrderInfo.add(6, items);

        return plannedOrderInfo;
    }

    @Override
    public List viewBlockedStock() {
        return null;
    }

    @Override
    public List viewReturnedProduct() {
        return null;
    }

    @Override
    public List viewPurchaseOrder(Long id) {

        Query q = em.createQuery("SELECT p FROM PurchaseOrder p WHERE p.purchaseOrderId = :id");
        q.setParameter("id", id);

        Object p = q.getResultList().get(0);

        if (p == null) {
            return null;
        }

        PurchaseOrderEntity purchaseOrder = (PurchaseOrderEntity) p;
        List purchaseOrderInfo = new ArrayList();

        purchaseOrderInfo.add(0, purchaseOrder.getPurchasOrderId());
        purchaseOrderInfo.add(1, purchaseOrder.getContract().getSupplier().getSupplierId());
        purchaseOrderInfo.add(2, purchaseOrder.getContract().getSupplier().getSupplierName());
        purchaseOrderInfo.add(3, purchaseOrder.getCreateDate());
        purchaseOrderInfo.add(4, purchaseOrder.getStatus());
        purchaseOrderInfo.add(5, purchaseOrder.getContract().getContractId());

        List plannedOrders = purchaseOrder.getPlannedOrders();
        for (Object o : plannedOrders) {
            PlannedOrderEntity po = (PlannedOrderEntity) o;
            plannedOrders.add(po.getPlannedOrderId());
        }

        purchaseOrderInfo.add(6, plannedOrders);
        purchaseOrderInfo.add(7, purchaseOrder.getTotalPrice());
        purchaseOrderInfo.add(8, purchaseOrder.getGoodsReceipt().getGoodsReceiptId());

        return purchaseOrderInfo;
    }

    @Override
    public List viewGoodsReceipt(Long id) {

        Query q = em.createQuery("SELECT g FROM GoodsReceipt g WHERE p.goodsReceiptId = :id");
        q.setParameter("id", id);

        Object g = q.getResultList().get(0);

        if (g == null) {
            return null;
        }

        GoodsReceiptEntity goodsReceipt = (GoodsReceiptEntity) g;
        List goodsReceiptInfo = new ArrayList();

        goodsReceiptInfo.add(0, goodsReceipt.getGoodsReceiptId());
        goodsReceiptInfo.add(1, goodsReceipt.getCreateDate());
        goodsReceiptInfo.add(2, goodsReceipt.getPurchaseOder().getPurchasOrderId());

        Collection ims = goodsReceipt.getInboundMovements();
        List inboundMovements = new ArrayList();

        for (Object o : ims) {
            InboundMovementEntity im = (InboundMovementEntity) o;
            inboundMovements.add(im.getId());
        }

        goodsReceiptInfo.add(3, inboundMovements);

        return goodsReceiptInfo;
    }

    @Override
    public List viewContract(Long id) {

        Query q = em.createQuery("SELECT c FROM Contract g WHERE p.contractId = :id");
        q.setParameter("id", id);

        Object c = q.getResultList().get(0);

        if (c == null) {
            return null;
        }

        ContractEntity contract = (ContractEntity) c;
        List contractInfo = new ArrayList();

        contractInfo.add(0, contract.getContractId());
        contractInfo.add(1, contract.getFactoryRawMaterialProduct().getFactoryRawMaterialId());
        contractInfo.add(2, contract.getFactoryRawMaterialProduct().getRawMaterial().getMaterialName());
        contractInfo.add(3, contract.getFactoryRetailProduct().getFactoryRetailProdctId());
        contractInfo.add(4, contract.getFactoryRetailProduct().getRetailProduct().getName());
        contractInfo.add(5, contract.getSupplier().getSupplierId());
        contractInfo.add(6, contract.getSupplier().getSupplierName());
        contractInfo.add(7, contract.getContractStartDate());
        contractInfo.add(8, contract.getContractEndDate());
        contractInfo.add(9, contract.getContractPrice());
        contractInfo.add(10, contract.getUnit());

        return contractInfo;
    }

    @Override
    public List viewSupplier(Long id) {

        Query q = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierId = :id");
        q.setParameter("id", id);

        Object s = q.getResultList().get(0);

        if (s == null) {
            return null;
        }

        SupplierEntity supplier = (SupplierEntity) s;
        List supplierInfo = new ArrayList();

        supplierInfo.add(0, supplier.getSupplierId());
        supplierInfo.add(1, supplier.getSupplierName());
        supplierInfo.add(2, supplier.getSupplierContact());
        supplierInfo.add(3, supplier.getSupplierFax());
        supplierInfo.add(4, supplier.getSupplierAddress());
        supplierInfo.add(5, supplier.getremark());

        Collection cl = supplier.getContractList();
        List contractList = new ArrayList();

        for (Object o : cl) {
            ContractEntity c = (ContractEntity) o;
            List contract = new ArrayList();
            contract.add(0, c.getContractId());
            contract.add(1, c.getFactoryRawMaterialProduct().getRawMaterial().getMaterialName());
            contractList.add(contract);
        }

        supplierInfo.add(6, contractList);

        return supplierInfo;
    }

}
