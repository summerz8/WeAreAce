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
    public ProductionPlanEntity ViewProductionPlan(Long id) {
        try {
            return em.find(ProductionPlanEntity.class, id);
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: ViewProductionPlan(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public PlannedOrderEntity ViewPlannedOrder(Long id) {
        try {
            return em.find(PlannedOrderEntity.class, id);
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: ViewPlannedOrder(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewBlockedStock() {
        try {
            Query q = em.createQuery("SELECT s FROM FactoryBinStoredProduct s WHERE s.status = 'blocked'");
            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewBlockedStock(): Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List viewReturnedProduct() {
        try {
            Query q = em.createQuery("SELECT s FROM FactoryBinStoredProduct s WHERE s.status = 'returned'");
            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("SessionBean.SCM.DocumentReferenceModule: viewReturnedProduct(): Caught an unexpected exception.");
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
