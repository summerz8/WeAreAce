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
import java.awt.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    //view item list available in the factory
    //pre-con: user select a type of items(RM/RP)
    //post-con: a list of item of the select type(RM / RP)
    @Override
    public Collection<Object> viewItemwithSelectType(Long factoryId, String itemType) throws Exception {
        System.out.println("viewItemwithSelectType():");

        Collection<Object> itemList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);

            if (itemType.equals("RawMaterial")) {
                Collection<FactoryRawMaterialEntity> factoryRawMaterialList = factory.getFactoryRawMaterials();
                Iterator iterator = factoryRawMaterialList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    itemList.add(obj);
                }
            } else {// itemType.equals("RetailProduct")
                Collection<FactoryRetailProductEntity> factoryRetailProductList = factory.getFactoryRetailProducts();
                Iterator iterator = factoryRetailProductList.iterator();

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    itemList.add(obj);
                }
                return itemList;
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return itemList;
    }

    //view all the rm/rp items which are not in the factory, but in the global HQ level
    @Override
    public Collection<Object> viewItemListNotInFactory(Long factoryId, String itemType) throws Exception {
        System.out.println("viewItemListNotInFactory():");

        Collection<Object> itemList = new ArrayList<>();
        try {
            if (itemType.equals("RawMaterial")) {
                Query q = em.createQuery("Select rm from RawMaterialEntity RM");
                outerLoop:
                for (Object o : q.getResultList()) {
                    RawMaterialEntity rm = (RawMaterialEntity) o;
                    Collection<FactoryRawMaterialEntity> factoryRawMaterialList = rm.getFactoryRawMaterials();
                    //for the Raw material in each factory
                    for (FactoryRawMaterialEntity frm : factoryRawMaterialList) {
                        //if find a factoryRawMaterial belongs to the user's factory
                        if (frm.getFactory().getFactoryId() == factoryId) {
                            continue outerLoop;//break out the inner loop, continue outer loop  
                        }
                    }
                    //if all the factory not equal to the user's facotry, add to the return list
                    itemList.add((Object) rm);
                }

            } else {// itemType.equals("RetailProduct")
                Query q = em.createQuery("Select rp from RetailProductEntity RP");
                outerLoop:
                for (Object o : q.getResultList()) {
                    RetailProductEntity rp = (RetailProductEntity) o;
                    Collection<FactoryRetailProductEntity> factoryRetailProductList = rp.getFactoryRetailProducts();
                    //for the Retail Product in each factory
                    for (FactoryRetailProductEntity frp : factoryRetailProductList) {
                        //if find a factoryRawMaterial belongs to the user's factory
                        if (frp.getFactory().getFactoryId() == factoryId) {
                            continue outerLoop;//break out the inner loop, continue outer loop                        
                        }
                    }
                    //if all the factory not equal to the user's facotry, add to the return list
                    itemList.add((Object) rp);
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return itemList;

    }

    //add supplier, must be added with a item contract
    //pre-condition: 
    //post-condition:
    @Override
    public String addSupplier(String itemType, Long itemId, String name,
            String address, String telephone, String fax,
            String remark, Double contractPrice, Integer leadTime, String unit,
            Calendar contractStartDate, Calendar contractEndDate)
            throws Exception {
        System.out.println("addSupplier():");

        SupplierEntity supplier = new SupplierEntity();
        ContractEntity contract = new ContractEntity();

        String result = null;

        try {
            //create new supplier entity and contract entity
            supplier.create(name, address, telephone, fax, remark);
            contract.create(contractPrice, leadTime, unit, contractStartDate, contractEndDate);
            //create relationship between supplier ad contract
            supplier.getContractList().add(contract);
            contract.setSupplier(supplier);

            //create relationship between contract and Raw material 
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, itemId);
                contract.setFactoryRawMaterial(factoryRawMaterial);
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
    public String editSupplier(Long supplierId, String name, String address,
            String telephone, String fax, String remark)
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
            result = "Supplier" + supplierId + "information changed successfully! ";

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
                    result = "Supplier " + supplierName + " contains at least one unexpired contract, it cannot be deleted. ";
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

    //add item (available in the Global HQ level)
    //user selecet from a given list 
    @Override
    public String addItem(Long factoryId, String itemType, Long itemId) throws Exception {
        System.out.println("addItem():");
        String result = null;

        //delete later
        String unit = "unit";

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            if (itemType.equals("RawMaterial")) {
                RawMaterialEntity rawMaterial = em.find(RawMaterialEntity.class, itemId);
                FactoryRawMaterialEntity factoryRawMaterial = new FactoryRawMaterialEntity();

                factoryRawMaterial.setMaterialName(rawMaterial.getMaterialName());
                factoryRawMaterial.setDescription(rawMaterial.getDescription());
                factoryRawMaterial.setUnit(unit);
                //create relationship between factory and factory raw material
                factoryRawMaterial.setFactory(factory);
                factory.getFactoryRawMaterials().add(factoryRawMaterial);
                //create relationship between raw material and factory raw material     
                factoryRawMaterial.setRawMaterial(rawMaterial);
                rawMaterial.getFactoryRawMaterials().add(factoryRawMaterial);

                result = "Factory Raw material --" + factoryRawMaterial.getMaterialName() + "-- has been added";

            } else {// itemType.equals("RetailProduct")
                RetailProductEntity retailProduct = em.find(RetailProductEntity.class, itemId);
                FactoryRetailProductEntity factoryRetailProduct = new FactoryRetailProductEntity();

                factoryRetailProduct.setName(retailProduct.getName());
                factoryRetailProduct.setDescription(retailProduct.getDescription());
                factoryRetailProduct.setUnit(unit);

                //create relationship between factory and factory retail product
                factoryRetailProduct.setFactory(factory);
                factory.getFactoryRetailProducts().add(factoryRetailProduct);
                //create relationship between raw material and factory retail product
                factoryRetailProduct.setRetailProduct(retailProduct);
                retailProduct.getFactoryRetailProducts().add(factoryRetailProduct);

                result = "Factory Retail prodcuct --" + factoryRetailProduct.getName() + "-- has been added";

            }

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Item has not been added successfully.../nPlease try again...";
        }
        System.out.println(result);
        return result;
    }

    //add item  (add contract with supplier): 
    //user chooses from the list of available items (raw materials or retail products)
    @Override
    public String addContract(Long factoryId, Long supplierId, String itemType, Long itemId,
            Double contractPrice, Integer leadTime,
            Calendar contractStartDate, Calendar contractEndDate)
            throws Exception {
        System.out.println("addContract():");
        String result = null;
        try {
            String unit = null;

            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            SupplierEntity supplier = em.find(SupplierEntity.class, supplierId);
            //create a new contract with given price and date
            ContractEntity contract = new ContractEntity();

            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, itemId);

                //create relationship between contract and factory raw material
                factoryRawMaterial.getContracts().add(contract);
                contract.setFactoryRawMaterial(factoryRawMaterial);

                unit = factoryRawMaterial.getUnit();

                result = "Contract " + contract.getContractId() + "added";
                result = result + "Factory Raw material --" + factoryRawMaterial.getMaterialName()
                        + "-- has been added to supplier --" + supplier.getSupplierName() + "-- account";
            } else {//itemType.equals("RetailProduct")
                FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, itemId);

                //create relationship between contract and factory retail product
                factoryRetailProduct.getContracts().add(contract);
                contract.setFactoryRetailProduct(factoryRetailProduct);

                unit = factoryRetailProduct.getUnit();
                result = "Contract " + contract.getContractId() + "added";
                result = result + "Factory Retail prodcuct --" + factoryRetailProduct.getName()
                        + "-- has been added to supplier --" + supplier.getSupplierName() + "-- account";
            }

            contract.create(contractPrice, leadTime, unit, contractStartDate, contractEndDate);

            //create relationship between supplier and contract
            contract.setSupplier(supplier);
            supplier.getContractList().add(contract);

            em.persist(contract);
            em.flush();

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Contract has not been added successfully.../nPlease try again...";
        }
        System.out.println(result);
        return result;

    }

    //delete item
    @Override
    public String deleteItem(String itemType, Long itemFactoryId) throws Exception {
        System.out.println("deleteSupplier():");

        String result = null;

        try {
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, itemFactoryId);
                String rmName = factoryRawMaterial.getMaterialName();
                Collection<ContractEntity> contractList = factoryRawMaterial.getContracts();

                Iterator iterator = contractList.iterator();
                //check whether there is an unexpired contract with the supplier
                //if at least one is unexpired, the item cannot be deleted
                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;

                    Calendar contractEndDate = contract.getContractEndDate();
                    Calendar today = Calendar.getInstance();

                    if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {
                        result = "Raw Material " + rmName + " contains at least one unexpired contract, it cannot be deleted ";
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
        System.out.println("removeTime():");

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
}
