/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.KM;

import Entity.Kitchen.IngredientForecastEntity;
import Entity.Kitchen.IngredientItemEntity;
import Entity.Kitchen.IngredientPurchaseOrderEntity;
import Entity.Kitchen.IngredientReceiptEntity;
import Entity.Kitchen.IngredientSupplierEntity;
import Entity.Kitchen.KitchenEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Yoky
 */
@Stateful
public class ProcurementManagementModule implements ProcurementManagementModuleLocal {

    @PersistenceContext(unitName = "IslandFurnitureERPSystem-ejbPU")
    private EntityManager em;

    @Override
    public IngredientPurchaseOrderEntity generateIngredientPurchaseOrder(Long ingredientForecastId) {
        try {
            IngredientForecastEntity ingf = em.find(IngredientForecastEntity.class, ingredientForecastId);
            IngredientPurchaseOrderEntity ipo = new IngredientPurchaseOrderEntity(ingf);
            em.persist(ipo);
            em.flush();
            ingf.setPurchaseOrder(ipo);
            em.flush();
            Double total = 0.0;
            for (IngredientItemEntity iif : ingf.getForecastItems()) {
                Double purchaseLots = Math.max(Math.ceil((iif.getQuantity() - iif.getIngredient().getStock()) / iif.getIngredient().getLotSize()), 0.0);
                IngredientItemEntity iip = new IngredientItemEntity(iif.getIngredient(), purchaseLots * iif.getIngredient().getLotSize());
                iip.getIngredient().getPurchaseOrders().add(ipo);
                em.persist(iip);
                em.flush();
                ipo.getPurchaseItems().add(iip);
                total += iip.getIngredient().getPrice() * purchaseLots;
                em.flush();
            }
            ipo.setTotal(total);
            ipo.setActuralTotal(total);
            em.flush();
            System.out.println("SessionBean.KM.ProcurementManagementModule: generateIngredientPurchaseOrder(): Successful. New Ingredient Purchase Order " + ipo.getId() + " is generated.");
            return ipo;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.ProcurementManagementModule: generateIngredientPurchaseOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Long generateIngredientReceipt(Long ingredientPurchaseOrderId) {
        try {
            IngredientPurchaseOrderEntity ipo = em.find(IngredientPurchaseOrderEntity.class, ingredientPurchaseOrderId);
            IngredientReceiptEntity ir = new IngredientReceiptEntity(ipo);
            em.persist(ir);
            em.flush();
            ipo.setStatus("Received");
            ipo.setReceipt(ir);
            em.flush();
            for (IngredientItemEntity ii : ipo.getPurchaseItems()) {
                Double newStockLevel = ii.getIngredient().getStock() + ii.getQuantity();
                ii.getIngredient().setStock(newStockLevel);
            }
            System.err.println("SessionBean.KM.ProcurementManagementModule: generateIngredientReceipt(): Successful. New Ingredient Recipe " + ir.getId() + " is generated.");
            return ir.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.ProcurementManagementModule: generateIngredientReceipt(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public Long confirmIngredientPurchaseOrder(Long ingredientPurchaseOrderId, Double acturalTotal) {
        try {
            IngredientPurchaseOrderEntity ipo = em.find(IngredientPurchaseOrderEntity.class, ingredientPurchaseOrderId);
            ipo.setActuralTotal(acturalTotal);
            ipo.setStatus("Confirmed");
            em.flush();
            System.out.println("SessionBean.KM.ProcurementManagementModule: confirmIngredientPurchaseOrder(): Successful. New Ingredient Purchase Order " + ipo.getId() + " is comfirmed.");
            return ipo.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.ProcurementManagementModule: confirmIngredientPurchaseOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public Long cancelIngredientPurchaseOrder(Long ingredientPurchaseOrderId) {
        try {
            IngredientPurchaseOrderEntity ipo = em.find(IngredientPurchaseOrderEntity.class, ingredientPurchaseOrderId);
            if (ipo.getStatus().equals("Confirmed")) {
                System.out.println("SessionBean.KM.ProcurementManagementModule: cancelIngredientPurchaseOrder(): Cancellation Failed. Ingredient Purchase Order " + ipo.getId() + " is confirmed already.");
                return -2L;
            }else if (ipo.getStatus().equals("Received")) {
                System.out.println("SessionBean.KM.ProcurementManagementModule: cancelIngredientPurchaseOrder(): Cancellation Failed. Ingredient Purchase Order " + ipo.getId() + " is received already.");
                return -3L;
            }
            ipo.setStatus("Cancelled");
            ipo.getForecast().setPurchaseOrder(null);
            for(IngredientItemEntity ii: ipo.getPurchaseItems()) {
                ii.getIngredient().getPurchaseOrders().remove(ipo);
            }
            em.flush();
            System.out.println("SessionBean.KM.ProcurementManagementModule: cancelIngredientPurchaseOrder(): Successful. New Ingredient Purchase Order " + ipo.getId() + " is cancelled.");
            return ipo.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.ProcurementManagementModule: confirmIngredientPurchaseOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public Long editPurchaseItem(Long ingredientPurchaseOrderId, Long purchaseItemId, Double quantity) {
        try {
            IngredientPurchaseOrderEntity ipo = em.find(IngredientPurchaseOrderEntity.class, ingredientPurchaseOrderId);
            IngredientItemEntity ii = em.find(IngredientItemEntity.class, purchaseItemId);
//            Double temp = quantity / ii.getIngredient().getLotSize();
            if ((quantity / ii.getIngredient().getLotSize()) % 1 != 0) {
                System.out.println("SessionBean.KM.ProcurementManagementModule: editPurchaseItem(): Failed. Quantity does not comply with the lot size constraint.");
                return -1L;
            }
            Double priceChange = ii.getIngredient().getPrice() * ((quantity - ii.getQuantity()) / ii.getIngredient().getLotSize());
            ipo.setTotal(ipo.getTotal() + priceChange);
            ipo.setActuralTotal(ipo.getTotal());
            ii.setQuantity(quantity);
            return ii.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.ProcurementManagementModule: editPurchaseItem(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -2L;
        }
    }

    @Override
    public List<IngredientItemEntity> getPurchaseItems(Long purchaseOrderId) {
        return em.find(IngredientPurchaseOrderEntity.class, purchaseOrderId).getPurchaseItems();
    }

    @Override
    public IngredientPurchaseOrderEntity findIngredientPurchaseOrder(Long kitchenId, Date targetDate) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            Calendar targetDateCal = Calendar.getInstance();
            targetDateCal.setTime(targetDate);
            Query q = em.createQuery("SELECT ipo FROM IngredientPurchaseOrderEntity ipo WHERE ipo.forecast.menuItemForecast.kitchen = :kitchen AND ipo.forecast.menuItemForecast.targetDate = :targetDate");
            q.setParameter("kitchen", kitchen);
            q.setParameter("targetDate", targetDateCal);
            IngredientPurchaseOrderEntity ipo = (IngredientPurchaseOrderEntity) q.getSingleResult();
            System.out.println("SessionBean.KM.ProcurementManagementModule: findIngredientPurchaseOrder(): Successful. Required Ingredient Purchase Order " + ipo.getId() + " is found.");
            return ipo;
        } catch (NoResultException nex) {
            System.out.println("SessionBean.KM.ProcurementManagementModule: findIngredientPurchaseOrder(): Faild. Required Ingredient Purchase Order is not found.");
            return null;
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.ProcurementManagementModule: findIngredientPurchaseOrder(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return null;
        }
    }

    // return ingredient supplier ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long addSupplier(Long kitchenId, String name, String address, String contact, String fax, String remark) {
        try {
            KitchenEntity kitchen = em.find(KitchenEntity.class, kitchenId);
            IngredientSupplierEntity supplier = new IngredientSupplierEntity(name, address, contact, fax, remark, kitchen);
            em.persist(supplier);
            kitchen.getIngredientSuppliers().add(supplier);
            em.flush();
            System.out.println("SessionBean.KM.ProcurementManagementModule: addSupplier(): Succcessful. New Ingredient Supplier " + supplier.getId() + " is added.");
            return supplier.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.ProcurementManagementModule: addSupplier(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    // return ingredient supplier ID
    //        -1L if an unexpected exception occurred
    @Override
    public Long editSupplier(Long ingredientSupplierId, String name, String address, String contact, String fax, String remark) {
        try {
            IngredientSupplierEntity supplier = em.find(IngredientSupplierEntity.class, ingredientSupplierId);
            supplier.setName(name);
            supplier.setAddress(address);
            supplier.setContact(contact);
            supplier.setFax(fax);
            supplier.setRemark(remark);
            em.flush();
            System.out.println("SessionBean.KM.ProcurementManagementModule: editSupplier(): Succcessful. Ingredient Supplier " + supplier.getId() + " is edited.");
            return supplier.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.ProcurementManagementModule: editSupplier(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -1L;
        }
    }

    // return ingredient supplier ID
    //        -1L if ingredient supplier has raw ingredients to supply currently
    //        -2L if an unexpected exception occurred
    @Override
    public Long deleteSupplier(Long ingredientSupplierId) {
        try {
            IngredientSupplierEntity supplier = em.find(IngredientSupplierEntity.class, ingredientSupplierId);
            if (!supplier.getIngredients().isEmpty()) {
                System.out.println("SessionBean.KM.ProcurementManagementModule: deleteSupplier(): Failed. The ingredient supplier has raw ingredients to supply currently.");
                return -1L;
            }
            supplier.getKitchen().getIngredientSuppliers().remove(supplier);
            supplier.setKitchen(null);
            supplier.setDeleted(true);
            em.flush();
            return supplier.getId();
        } catch (Exception ex) {
            System.err.println("SessionBean.KM.ProcurementManagementModule: deleteSupplier(): Failed. Caught an unexpected exception.");
            ex.printStackTrace();
            return -2L;
        }
    }

    @Override
    public List<IngredientSupplierEntity> getSuppliers(Long kitchenId) {
        return em.find(KitchenEntity.class, kitchenId).getIngredientSuppliers();
    }
}
