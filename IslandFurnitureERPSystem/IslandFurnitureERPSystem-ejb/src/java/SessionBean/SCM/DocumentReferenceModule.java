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
            Query q = em.createQuery("SELECT p FROM ProductionPlanEntity p");

            return q.getResultList();
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
            Query q = em.createQuery("SELECT p FROM PlannedOrderEntity p");

            return q.getResultList();
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
            Query q = em.createQuery("SELECT p FROM PurchaseOrderEntity p");

            return q.getResultList();
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
            Query q = em.createQuery("SELECT g FROM GoodsReceiptEntity g");

            return q.getResultList();
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
            Query q = em.createQuery("SELECT c FROM ContractEntity c");

            return q.getResultList();
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
            Query q = em.createQuery("SELECT s FROM SupplierEntity s");

            return q.getResultList();
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

    @Override
    public List viewProductionPlansByFactory(long factoryId) {
        try {
            Query q = em.createQuery("SELECT p FROM ProductionPlanEntity p WHERE p.factoryProduct.factory.factoryId = :factoryId");
            q.setParameter("factoryId", factoryId);

            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: ViewAllProductionPlans(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewPlannedOrdersByFactory(long factoryId) {
        try {
            Query q = em.createQuery("SELECT p FROM PlannedOrderEntity p WHERE p.factory.factoryId = :factoryId");
            q.setParameter("factoryId", factoryId);

            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: ViewAllPlannedOrders(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewBlockedStockByFactory(long factoryId) {
        try {
            Query q = em.createQuery("SELECT s FROM FactoryBinStoredProductEntity s WHERE s.status = 'blocked' AND s.factoryBin.factory.factoryId = :factoryId");
            q.setParameter("factoryId", factoryId);

            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewBlockedStock(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewReturnedProductByFactory(long factoryId) {
        try {
            Query q = em.createQuery("SELECT s FROM FactoryBinStoredProductEntity s WHERE s.status = 'returned' AND s.factoryBin.factory.factoryId = :factoryId");
            q.setParameter("factoryId", factoryId);
            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewReturnedProduct(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewPurchaseOrdersByFactory(long factoryId) {
        try {
            Query q = em.createQuery("SELECT p FROM PurchaseOrderEntity p WHERE p.factory.factoryId = :factoryId");
            q.setParameter("factoryId", factoryId);
            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewAllPurchaseOrders(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewGoodsReceiptsByFactory(long factoryId) {
        try {
            Query q = em.createQuery("SELECT g FROM GoodsReceiptEntity g WHERE g.purchaseOrder.factory.factoryId = :factoryId");
            q.setParameter("factoryId", factoryId);

            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewAllGoodsReceipts(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewContractsByFactory(long factoryId) {
        try {
            Query q = em.createQuery("SELECT c FROM ContractEntity c");
            List factoryContracts = new ArrayList();
            for (Object o : q.getResultList()) {
                ContractEntity contract = (ContractEntity) o;
                if (contract.getTypeIndicator() == 1) {
                    if (contract.getFactoryRawMaterial().getFactory().getFactoryId() == factoryId) {
                        factoryContracts.add(contract);
                    }
                } else {
                    if (contract.getFactoryRetailProduct().getFactory().getFactoryId() == factoryId) {
                        factoryContracts.add(contract);
                    }
                }
            }
            return factoryContracts;
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewAllContracts(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewSupplierByFactory(long factoryId) {
        List factorySuppliers = new ArrayList();
        for (Object o : viewContractsByFactory(factoryId)) {
            ContractEntity contract = (ContractEntity) o;
            factorySuppliers.add(contract.getSupplier());
        }
        return factorySuppliers;
    }

}
