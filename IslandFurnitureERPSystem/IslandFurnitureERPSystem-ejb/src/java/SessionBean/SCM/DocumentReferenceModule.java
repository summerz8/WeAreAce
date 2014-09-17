/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.Factory.FactoryRawMaterialAmountEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import Entity.Factory.MRP.ProductionPlanEntity;
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
    // return null if error occurred
    public ProductionPlanEntity ViewProductionPlan(Long id) {
        try {
            Query q = em.createQuery("SELECT p FROM ProductionPlan p WHERE p.productionPlanId = :id");
            q.setParameter("id", id);

            if (q.getResultList() == null) {
                System.out.println("SessionBean.SCM.DocumentReferenceModule: ViewProductionPlan():Faild. The Production Plan ID " + id + " is invalid.");
                return null;
            }

            ProductionPlanEntity productionPlan = (ProductionPlanEntity) q.getSingleResult();

            return productionPlan;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: ViewProductionPlan(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public PlannedOrderEntity ViewPlannedOrder(Long id) {
        try {
            Query q = em.createQuery("SELECT p FROM plannedOrder p WHERE p.plannedOrderId = :id");
            q.setParameter("id", id);

            if (q.getResultList() == null) {
                System.out.println("SessionBean.SCM.DocumentReferenceModule: ViewPlannedOrder():Faild. The Planned Order ID " + id + " is invalid.");
                return null;
            }

            PlannedOrderEntity plannedOrder = (PlannedOrderEntity) q.getSingleResult();

            return plannedOrder;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: ViewPlannedOrder(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

//    @Override
//    public List viewBlockedStock() {
//        return null;
//    }
//
//    @Override
//    public List viewReturnedProduct() {
//        return null;
//    }
//
    @Override
    public PurchaseOrderEntity viewPurchaseOrder(Long id) {
        try {
            Query q = em.createQuery("SELECT p FROM PurchaseOrder p WHERE p.id = :id");
            q.setParameter("id", id);

            if (q.getResultList() == null) {
                System.out.println("SessionBean.SCM.DocumentReferenceModule: viewPurchaseOrder():Faild. The Purchase Order ID " + id + " is invalid.");
                return null;
            }

            PurchaseOrderEntity purchaseOrder = (PurchaseOrderEntity) q.getSingleResult();

            return purchaseOrder;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewPurchaseOrder(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public GoodsReceiptEntity viewGoodsReceipt(Long id) {

        try {
            Query q = em.createQuery("SELECT g FROM GoodsReceipt g WHERE p.goodsReceiptId = :id");
            q.setParameter("id", id);

            if (q.getResultList() == null) {
                System.out.println("SessionBean.SCM.DocumentReferenceModule: viewGoodsReceipt():Faild. The Goods Receipt ID " + id + " is invalid.");
                return null;
            }

            GoodsReceiptEntity goodsReceipt = (GoodsReceiptEntity) q.getSingleResult();

            return goodsReceipt;

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewGoodsReceipt(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ContractEntity viewContract(Long id) {

        try {
            Query q = em.createQuery("SELECT c FROM Contract g WHERE p.contractId = :id");
            q.setParameter("id", id);

            if (q.getResultList() == null) {
                System.out.println("SessionBean.SCM.DocumentReferenceModule: viewContract():Faild. The Contract ID " + id + " is invalid.");
                return null;
            }

            ContractEntity contract = (ContractEntity) q.getSingleResult();

            return contract;

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewContract(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public SupplierEntity viewSupplier(Long id) {

        try {
            Query q = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierId = :id");
            q.setParameter("id", id);

            if (q.getResultList() == null) {
                System.out.println("SessionBean.SCM.DocumentReferenceModule: viewSupplier():Faild. The Supplier ID " + id + " is invalid.");
                return null;
            }

            SupplierEntity supplier = (SupplierEntity) q.getSingleResult();

            return supplier;

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewSupplier(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }
}
