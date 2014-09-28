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
import java.util.ArrayList;
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
    public List viewAllProductionPlans() {
        try {
            List<ProductionPlanEntity> productionPlans = new ArrayList();
            Query q = em.createQuery("SELECT p FROM ProductionPlanEntity p");
            for (Object o : q.getResultList()) {
                ProductionPlanEntity productionPlan = (ProductionPlanEntity) o;
                productionPlans.add(productionPlan);
            }
            return productionPlans;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: ViewAllProductionPlans(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ProductionPlanEntity viewProductionPlan(Long id) {
        try {
            return em.find(ProductionPlanEntity.class, id);
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: ViewProductionPlan(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewAllPlannedOrders() {
        try {
            List<PlannedOrderEntity> plannedOrders = new ArrayList();
            Query q = em.createQuery("SELECT p FROM PlannedOrderEntity p");
            for (Object o : q.getResultList()) {
                PlannedOrderEntity plannedOrder = (PlannedOrderEntity) o;
                plannedOrders.add(plannedOrder);
            }
            return plannedOrders;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: ViewAllPlannedOrders(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public PlannedOrderEntity viewPlannedOrder(Long id) {
        try {
            return em.find(PlannedOrderEntity.class, id);
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: ViewPlannedOrder(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewAllBlockedStock() {
        try {
            Query q = em.createQuery("SELECT s FROM FactoryBinStoredProductEntity s WHERE s.status = 'blocked'");
           
            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewBlockedStock(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewAllReturnedProduct() {
        try {
            Query q = em.createQuery("SELECT s FROM FactoryBinStoredProductEntity s WHERE s.status = 'returned'");
            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewReturnedProduct(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewAllPurchaseOrders() {
        try {
            List<PurchaseOrderEntity> purchaseOrders = new ArrayList();
            Query q = em.createQuery("SELECT p FROM PurchaseOrderEntity p");
            for (Object o : q.getResultList()) {
                PurchaseOrderEntity purchaseOrder = (PurchaseOrderEntity) o;
                purchaseOrders.add(purchaseOrder);
            }
            return purchaseOrders;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewAllPurchaseOrders(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public PurchaseOrderEntity viewPurchaseOrder(Long id) {
        try {
            return em.find(PurchaseOrderEntity.class, id);
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewPurchaseOrder(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewAllGoodsReceipts() {
        try {
            List<GoodsReceiptEntity> goodsReceipts = new ArrayList();
            Query q = em.createQuery("SELECT g FROM GoodsReceiptEntity g");
            for (Object o : q.getResultList()) {
                GoodsReceiptEntity goodsReceipt = (GoodsReceiptEntity) o;
                goodsReceipts.add(goodsReceipt);
            }
            return goodsReceipts;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewAllGoodsReceipts(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public GoodsReceiptEntity viewGoodsReceipt(Long id) {

        try {
            return em.find(GoodsReceiptEntity.class, id);

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewGoodsReceipt(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewAllContracts() {
        try {
            List<ContractEntity> contracts = new ArrayList();
            Query q = em.createQuery("SELECT c FROM ContractEntity c");
            for (Object o : q.getResultList()) {
                ContractEntity contract = (ContractEntity) o;
                contracts.add(contract);
            }
            return contracts;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewAllContracts(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ContractEntity viewContract(Long id) {

        try {
            return em.find(ContractEntity.class, id);

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewContract(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewAllSuppliers() {
        try {
            List<SupplierEntity> suppliers = new ArrayList();
            Query q = em.createQuery("SELECT s FROM SupplierEntity s");
            for (Object o : q.getResultList()) {
                SupplierEntity supplier = (SupplierEntity) o;
                suppliers.add(supplier);
            }
            return suppliers;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewAllSuppliers(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public SupplierEntity viewSupplier(Long id) {

        try {
            return em.find(SupplierEntity.class, id);

        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewSupplier(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

}
