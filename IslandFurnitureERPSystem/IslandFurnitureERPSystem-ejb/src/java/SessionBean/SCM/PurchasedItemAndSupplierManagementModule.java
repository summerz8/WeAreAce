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
 * 5. Delete Item
 */
package SessionBean.SCM;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.RawMaterialEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.SupplierEntity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
    public String addSupplier(String itemType, Long itemId, String name, String address, String telephone, String fax,
            String remark, Double contractPrice, Integer leadTime, Calendar contractStartDate, Calendar contractEndDate)
            throws Exception {
        System.out.println("addSupplier():");

        SupplierEntity supplier = new SupplierEntity();
        ContractEntity contract = new ContractEntity();

        String result = null;

        try {
            //create new supplier entity and contract entity
            supplier.create(name, address, telephone, fax, remark);
            contract.create(contractPrice, leadTime, contractStartDate, contractEndDate);
            //create relationship between supplier ad contract
            supplier.getContractList().add(contract);
            contract.setSupplier(supplier);

            //create relationship between contract and Raw material 
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, itemId);
                contract.setFactoryRawMaterialProduct(factoryRawMaterial);
                factoryRawMaterial.getContracts().add(contract);
            } //create relationship between contract and retail products
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
        System.out.println(result);
        return result;
    }

    //edit supplier
    @Override
    public String editSupplier(Long supplierId, String name, String address, String telephone, String fax)
            throws Exception {
        System.out.println("editSupplier():");

        String result = null;
        try {
            SupplierEntity supplier = em.find(SupplierEntity.class, supplierId);
            supplier.setSupplierName(name);
            supplier.setSupplierAddress(address);
            supplier.setSupplierContact(telephone);
            supplier.setSupplierFax(fax);

            em.flush();
            result = "Supplier information changed successfully! ";

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Supplier has not been created successfully.../nPlease try again...";
        }
        System.out.println(result);
        return result;
    }

    //delete supplier
    //only those supplier with all of its contracts expired could be deleted
    @Override
    public String deleteSupplier(Long supplierId) throws Exception {
        System.out.println("deleteSupplier():");

        String result = null;
        try {
            SupplierEntity supplier = em.find(SupplierEntity.class, supplierId);
            String supplierName = supplier.getSupplierName();
            Collection<ContractEntity> contractList = supplier.getContractList();
            Iterator iterator = contractList.iterator();

            //check whether there is an unexpired contract with the supplier
            //if at least one is unexpired, the supplier cannot be deleted
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                ContractEntity contract = (ContractEntity) obj;

                Calendar contractEndDate = contract.getContractEndDate();
                Calendar today = Calendar.getInstance();

                if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {//get unexpired contract
                    result = "Supplier " + supplierName + " contains at least one unexpired contract, it cannot be deleted ";
                    System.out.println(result);
                    return result;
                }
            }
            em.remove(supplier);
            result = "Supplier " + supplierName + " has been deleted.";

            em.flush();

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Supplier has not been deleted successfully.../nPlease try again...";
        }
        System.out.println(result);
        return result;
    }

    //add item 1 (add contract with supplier): 
    //user chooses from the list of available items (raw materials or retail products)
    //
    @Override
    public String addItem(Long factoryId, Long supplierId, String itemType, Long itemId, Double contractPrice, Integer leadTime, Calendar contractStartDate, Calendar contractEndDate)
            throws Exception {
        System.out.println("addItem():");

        String result = null;
        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            SupplierEntity supplier = em.find(SupplierEntity.class, supplierId);
            //create a new contract with given price and date
            ContractEntity contract = new ContractEntity();
            contract.create(contractPrice, leadTime, contractStartDate, contractEndDate);
            //create relationship between supplier and contract
            contract.setSupplier(supplier);
            supplier.getContractList().add(contract);

            if (itemType.equals("RawMaterial")) {
                RawMaterialEntity rawMaterial = em.find(RawMaterialEntity.class, itemId);
                FactoryRawMaterialEntity factoryRawMaterial = new FactoryRawMaterialEntity();
                factoryRawMaterial.create(rawMaterial.getMaterialName(), rawMaterial.getDescription());

                //create relationship between factory and factory raw material
                factory.getFactoryRawMaterials().add(factoryRawMaterial);
                factoryRawMaterial.setFactory(factory);

                //create relationship between contract and factory raw material
                factoryRawMaterial.getContracts().add(contract);
                contract.setFactoryRawMaterialProduct(factoryRawMaterial);

                //create relationship between factory raw material and raw material
                factoryRawMaterial.setRawMaterial(rawMaterial);
                rawMaterial.getFactoryRawMaterials().add(factoryRawMaterial);

                result = "Raw material --" + rawMaterial.getMaterialName() + "-- has been add to supplier --" + supplier.getSupplierName() + "-- account";
            } else {//itemType.equals("RetailProduct")
                RetailProductEntity retailProduct = em.find(RetailProductEntity.class, itemId);
                FactoryRetailProductEntity factoryRetailProduct = new FactoryRetailProductEntity();

                factoryRetailProduct.create(retailProduct.getName(), retailProduct.getDecription());

                //create relationship between factory and factory retail product
                factory.getFactoryRetailProducts().add(factoryRetailProduct);
                factoryRetailProduct.setFactory(factory);

                //create relationship between contract and factory retail product
                factoryRetailProduct.getContracts().add(contract);
                contract.setFactoryRetailProduct(factoryRetailProduct);

                //create relationship between factory retail product and retail product
                factoryRetailProduct.setRetailProduct(retailProduct);
                retailProduct.getFactoryRetailProducts().add(factoryRetailProduct);

                result = "Retail prodcuct --" + retailProduct.getName() + "-- has been add to supplier --" + supplier.getSupplierName() + "-- account";

            }
            em.persist(contract);
            em.flush();

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Item has not been added successfully.../nPlease try again...";
        }
        System.out.println(result);
        return result;

    }

    //delete item
    @Override
    public String deleteItem(String itemType, Long itemFactoryId) throws Exception {
        System.out.println("addSupplier():");

        String result = null;

        try {
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, itemFactoryId);
                String rmName = factoryRawMaterial.getMaterialName();
                Collection<ContractEntity> contractList = factoryRawMaterial.getContracts();

                Iterator iterator = contractList.iterator();
                //check whether there is an unexpired contract with the supplier
                //if at least one is unexpired, the supplier cannot be deleted
                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;

                    Calendar contractEndDate = contract.getContractEndDate();
                    Calendar today = Calendar.getInstance();

                    if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {
                        result = "Raw Material " + factoryRawMaterial.getMaterialName() + " contains at least one unexpired contract, it cannot be deleted ";
                        System.out.println(result);
                        return result;
                    }
                }
                em.remove(factoryRawMaterial);
                result = "Raw Material " + rmName + " has been deleted.";
                em.flush();

            } else {//itemType.equals("RetailProduct")
                FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, itemFactoryId);
                String rpName = factoryRetailProduct.getName();
                Collection<ContractEntity> contractList = factoryRetailProduct.getContracts();
                Iterator iterator = contractList.iterator();

                //check whether there is an unexpired contract with the supplier
                //if at least one is unexpired, the supplier cannot be deleted
                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;

                    Calendar contractEndDate = contract.getContractEndDate();
                    Calendar today = Calendar.getInstance();

                    if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {//check unexpired contract
                        result = "Retail Product " + factoryRetailProduct.getName() + " contains at least one unexpired contract, it cannot be deleted ";
                        System.out.println(result);
                        return result;
                    }
                }
                em.remove(factoryRetailProduct);
                result = "Retail Product " + rpName + " has been deleted.";
                em.flush();
            }

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Supplier has not been created successfully.../nPlease try again...";
        }
        System.out.println(result);
        return result;
    }

    // for comparing two dates
    //function to set all the other attributes to be 0
    public Calendar removeTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
}
