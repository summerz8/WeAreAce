/*
 * PurchaseItemAndSupplierManagementModule.java
 * 
 * It is a session bean that control the functions for managing purchased items(RM or RP) within a supplier's contract
 * and the contracted supplier's account information.
 * Basic funtions are: 
 * 1. Add supplier (with contract information)
 * 2. Edit supplier account information (e.g. change in price)
 * 3. Delete supplier account (no more coorperation on the item, which also means delete a specific contract)
 * 4. Add Item
 * 5. Edit Item
 * 6. Delete Item
 */
package SessionBean.SCM;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.SupplierEntity;
import java.util.Calendar;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Shiyu
 */
@Stateful
public class PurchasedItemAndSupplierManagementModule implements PurchasedItemAndSupplierManagementModuleLocal {

    @PersistenceContext
    private EntityManager em;

    public PurchasedItemAndSupplierManagementModule() {
    }

    //add supplier
    //pre-condition: 
    //post-condition:
    @Override
    public String addSupplier(String purchaseItem, Long itemId, String name, String address, String telephone, String fax,
            String remark, Double contractPrice, Calendar contractStartDate, Calendar contractEndDate)
            throws Exception {
        SupplierEntity supplier = new SupplierEntity();
        ContractEntity contract = new ContractEntity();

        String result = null;

        try {
            //create new supplier entity and contract entity
            supplier.create(name, address, telephone, fax, remark);
            contract.create(contractPrice, contractStartDate, contractEndDate);
            //create relationship between supplier ad contract
            supplier.getContractList().add(contract);
            contract.setSupplier(supplier);

            //create relationship between contract and Raw material 
            if (purchaseItem.equals("RawMaterial")) {
                FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, itemId);
                contract.setFactoryRawMaterialProduct(factoryRawMaterial);
                factoryRawMaterial.getContracts().add(contract);
            } 
            //create relationship between contract and retail products
            else {
                FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, itemId);
                contract.setFactoryRetailProduct(factoryRetailProduct);
                factoryRetailProduct.getContracts().add(contract);
            }

            em.persist(supplier);
            em.persist(contract);
            em.flush();

            result = "Supplier " + supplier.getSupplierName() + " has been created, with Id number: " + supplier.getSupplierId();
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Supplier has not been created successfully.../nPlease try again...";
        }
        return result;
    }
    //edit supplier
    //delete supplier
    //add item
    //edit item
    //delete item

}
