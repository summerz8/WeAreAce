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

import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.RawMaterialEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Factory.SCM.ContractEntity;
import Entity.Factory.SCM.SupplierEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
    //RawMaterials
    @Override
    public Collection<FactoryRawMaterialEntity> viewRawMaterialWithSelectType(Long factoryId) throws Exception {//test works!!
        System.out.println("Session Bean: viewRawMaterialWithSelectType():");
        Collection<FactoryRawMaterialEntity> frmList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            Collection<FactoryRawMaterialEntity> factoryRawMaterialList = factory.getFactoryRawMaterials();

            System.out.println("Session Bean view Raw Material With Select Type: " + factoryRawMaterialList.size());

            //check whehter a factory raw material in the list is marked as deletedxs
            for (FactoryRawMaterialEntity frm : factoryRawMaterialList) {
                if (!frm.getIsDeleted()) {
                    frmList.add(frm);
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return frmList;
    }

    //RetailProduct
    @Override
    public Collection<FactoryRetailProductEntity> viewRetailProductWithSelectType(Long factoryId) throws Exception {//test works!!
        System.out.println("Session Bean : viewRetailProductWithSelectType():");
        Collection<FactoryRetailProductEntity> frpList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            Collection<FactoryRetailProductEntity> factoryRetailProductList = factory.getFactoryRetailProducts();

            for (FactoryRetailProductEntity frp : factoryRetailProductList) {
                if (!frp.getIsDeleted()) {
                    frpList.add(frp);
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return frpList;
    }

    //view all the rm/rp items which are not in the factory, but in the global HQ level
    @Override
    public Collection<RawMaterialEntity> viewRawMaterialListNotInFactory(Long factoryId) throws Exception {//test works!!
        System.out.println("viewRawMaterialListNotInFactory():");

        Integer flag = 0;
        Collection<RawMaterialEntity> rmList = new ArrayList<>();
        FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
        Collection<FactoryRawMaterialEntity> factoryRawMaterialList = factory.getFactoryRawMaterials();
        try {
            Query q = em.createQuery("Select rm from RawMaterialEntity RM");
            outerLoop:
            for (Object o : q.getResultList()) {
                RawMaterialEntity rm = (RawMaterialEntity) o;
                for (FactoryRawMaterialEntity frm : factoryRawMaterialList) {
                    FactoryRawMaterialEntity factoryRawMaterial = frm;
                    if (factoryRawMaterial.getRawMaterial().equals(rm) && (!factoryRawMaterial.getIsDeleted())) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    rmList.add(rm);
                }
                flag = 0;

            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return rmList;
    }

    @Override
    public Collection<RetailProductEntity> viewRetailProductListNotInFactory(Long factoryId) throws Exception {//test works!!
        System.out.println("viewRetailProductListNotInFactory():");
        Integer flag = 0;
        Collection<RetailProductEntity> rpList = new ArrayList<>();
        FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
        Collection<FactoryRetailProductEntity> factoryRetailProductList = factory.getFactoryRetailProducts();
        try {
            Query q = em.createQuery("Select pd from RetailProductEntity PD");
            outerLoop:
            for (Object o : q.getResultList()) {
                RetailProductEntity rp = (RetailProductEntity) o;
                for (FactoryRetailProductEntity frp : factoryRetailProductList) {
                    FactoryRetailProductEntity factoryRetailProduct = frp;
                    if (factoryRetailProduct.getRetailProduct().equals(rp) && (!factoryRetailProduct.getIsDeleted())) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    rpList.add(rp);
                }
                flag = 0;
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return rpList;
    }

//add supplier, must be added with a item contract
//pre-condition: 
//post-condition:
    @Override
    public String addSupplier(String itemType, Long itemId, String name,
            String address, String telephone, String fax,
            String remark, Double contractPrice, Integer leadTime, Double lotSize,
            Calendar contractStartDate, Calendar contractEndDate)
            throws Exception { //test works!!
        System.out.println("SeesionBean: addSupplier():");

        String result = null;
        String unit = null;

//        if (!removeTime(contractStartDate).before(removeTime(contractEndDate))) {
//            result = "\nContract start date is not before contract end date. "
//                    + "\nPlease enter correct date.\n";
//            return result;
//        }
        SupplierEntity supplier = new SupplierEntity();
        ContractEntity contract = new ContractEntity();

        //create new supplier entity and contract entity
        supplier.create(name, address, telephone, fax, remark);
        contract.create(contractPrice, leadTime, unit, lotSize, contractStartDate, contractEndDate);

        try {
            //create relationship between contract and factory Raw material 
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, itemId);
                contract.setFactoryRawMaterial(factoryRawMaterial);
                factoryRawMaterial.getContracts()
                        .add(contract);
                unit = factoryRawMaterial.getUnit();
                contract.setTypeIndicator(1);

            } //create relationship between contract and retail products
            else {//itemType.equals("RetailProducts")
                FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, itemId);
                contract.setFactoryRetailProduct(factoryRetailProduct);

                factoryRetailProduct.getContracts().add(contract);
                unit = factoryRetailProduct.getUnit();
                contract.setTypeIndicator(3);

            }

            //create relationship between supplier ad contract 
            supplier.getContractList().add(contract);
            contract.setSupplier(supplier);
            em.persist(supplier);
            em.persist(contract);
            em.flush();

            result = "Supplier " + supplier.getSupplierName() + " [id = " + supplier.getSupplierId() + " ] created!";
            result = result + "\nContract [id = " + contract.getContractId() + "] created with this supplier!";
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Supplier has not been created successfully...\nPlease try again...";
        }

        System.out.println(result);
        return result;
    }

    //view all suppliers in the factory
    //but exclude the suppleirs which have been marked deleted
    @Override
    public Collection<SupplierEntity> viewAvailSupplier(Long factoryId) throws Exception {
        System.out.println("viewAvailSupplier():");
        Collection<SupplierEntity> availSupplierList = new ArrayList<>();

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            // add suppleirs for raw materials
            Collection<FactoryRawMaterialEntity> factoryRawMaterialList = factory.getFactoryRawMaterials();
            for (FactoryRawMaterialEntity frm : factoryRawMaterialList) {
                Collection<ContractEntity> contractList = frm.getContracts();
                for (ContractEntity contract : contractList) {
                    SupplierEntity supplier = contract.getSupplier();

                    //exclude the duplicated and the deleted suppleirs
                    if (!availSupplierList.contains(supplier) && !supplier.isDeleted()) {
                        availSupplierList.add(supplier);
                    }
                }
            }
            //add the suppliers for retail product, some maybe the same as the raw materials
            Collection<FactoryRetailProductEntity> factoryRetailProductList = factory.getFactoryRetailProducts();
            for (FactoryRetailProductEntity frp : factoryRetailProductList) {
                Collection<ContractEntity> contractList = frp.getContracts();
                for (ContractEntity contract : contractList) {
                    SupplierEntity supplier = contract.getSupplier();
                    //exclude the duplicated and the deleted supplier 
                    if (!availSupplierList.contains(supplier) && !supplier.isDeleted()) {
                        availSupplierList.add(supplier);
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();

        }
        return availSupplierList;
    }

    //view suppliers for a particular item
    @Override
    public Collection<SupplierEntity> viewSupplierForItem(String itemType, Long itemId) throws Exception {
        System.out.println("viewAvailSupplierForItem():");
        Collection<SupplierEntity> availSupplierList = new ArrayList<>();

        try {
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity frm = em.find(FactoryRawMaterialEntity.class, itemId);
                Collection<ContractEntity> contractList = frm.getContracts();
                for (ContractEntity contract : contractList) {
                    SupplierEntity supplier = contract.getSupplier();

                    //exclude the deleted suppleirs
                    if (!supplier.isDeleted()) {
                        availSupplierList.add(supplier);
                    }
                }
            } else {
                FactoryRetailProductEntity frp = em.find(FactoryRetailProductEntity.class, itemId);
                Collection<ContractEntity> contractList = frp.getContracts();
                for (ContractEntity contract : contractList) {
                    SupplierEntity supplier = contract.getSupplier();
                    //exclude the duplicated and the deleted supplier 
                    if (!supplier.isDeleted()) {
                        availSupplierList.add(supplier);
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();

        }
        return availSupplierList;
    }

    //view suppliers that could add new contract with a particular item
    @Override
    public Collection<SupplierEntity> viewSupplierCouldBeAddedForItem(String itemType, Long itemId) throws Exception {
        System.out.println("viewSupplierCouldBeAddedForItem():");
        Collection<SupplierEntity> ableToAddSupplierList = new ArrayList<>();

        try {
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity frm = em.find(FactoryRawMaterialEntity.class, itemId);
                ableToAddSupplierList = viewAvailSupplier(frm.getFactory().getFactoryId());

                Collection<ContractEntity> contractList = frm.getContracts();
                for (ContractEntity contract : contractList) {
                    SupplierEntity supplier = contract.getSupplier();
                    //exclude the deleted suppleirs
                    if (!supplier.isDeleted() && !isExpired(contract)) {
                        ableToAddSupplierList.remove(supplier);
                    }
                }
            } else {
                FactoryRetailProductEntity frp = em.find(FactoryRetailProductEntity.class, itemId);
                Collection<ContractEntity> contractList = frp.getContracts();
                for (ContractEntity contract : contractList) {
                    SupplierEntity supplier = contract.getSupplier();
                    //exclude the duplicated and the deleted supplier 
                    if (!supplier.isDeleted()) {
                        ableToAddSupplierList.add(supplier);
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();

        }
        return ableToAddSupplierList;
    }

    //edit supplier
    //user chooses from a list of available suppliers
    @Override
    public String editSupplier(Long supplierId, String name, String address,
            String telephone, String fax, String remark)
            throws Exception {//test works !!
        System.out.println("editSupplier():");

        String result = null;

        try {
            SupplierEntity supplier = em.find(SupplierEntity.class, supplierId);
            System.out.println("SessionBean: SupplierName + " + name);
            supplier.setSupplierName(name);

            supplier.setSupplierAddress(address);

            supplier.setSupplierContact(telephone);

            supplier.setSupplierFax(fax);

            supplier.setRemark(remark);

            em.flush();
            result = "Supplier [id = " + supplierId + " ] information changed! ";

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Supplier information has not been changed successfully.../nPlease try again...";
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

            //check whether there is an unexpired contract with the supplier
            //if at least one is unexpired, the supplier cannot be deleted
            for (Object obj : contractList) {
                ContractEntity contract = (ContractEntity) obj;

                Calendar contractEndDate = contract.getContractEndDate();
                Calendar today = Calendar.getInstance();

                if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {//get unexpired contract
                    result = "Supplier [id = " + supplier.getSupplierId() + ", Name =" + supplierName + "] contains at least one unexpired contract, it cannot be deleted. ";
                    System.out.println(result);
                    return result;
                }
            }
            //mark the supplier as deleted
            supplier.setIsDeleted(Boolean.TRUE);
            //mark all the contract related to the supplier to be deleted (all should be expired)
            for (ContractEntity contract : contractList) {
                contract.setIsDeleted(Boolean.TRUE);
            }

            result = "Supplier [id = " + supplier.getSupplierId() + ", Name =" + supplierName + "] has been deleted.";
            em.flush();

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Supplier has not been deleted.../nPlease try again...";
        }
        System.out.println(result);
        return result;
    }

    //add item (available in the Global HQ level)
    //user selecet from a given list 
    //itemId is the id of the RM or RP in the global level, meaning the ID of the RawMaterialEntity/RetailProductEntity
    @Override
    public String addItem(Long factoryId, String itemType, Long itemId) throws Exception {
        System.out.println("addItem():");
        String result = null;

        try {
            FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
            if (itemType.equals(
                    "RawMaterial")) {
                RawMaterialEntity rawMaterial = em.find(RawMaterialEntity.class, itemId);
                FactoryRawMaterialEntity factoryRawMaterial = new FactoryRawMaterialEntity();
                em.persist(factoryRawMaterial);

                factoryRawMaterial.setMaterialName(rawMaterial.getMaterialName());
                factoryRawMaterial.setDescription(rawMaterial.getDescription());
                factoryRawMaterial.setUnit(rawMaterial.getUnit());
                //create relationship between factory and factory raw material
                factoryRawMaterial.setFactory(factory);
                factory.getFactoryRawMaterials().add(factoryRawMaterial);
                //create relationship between raw material and factory raw material     
                factoryRawMaterial.setRawMaterial(rawMaterial);
                rawMaterial.getFactoryRawMaterials().add(factoryRawMaterial);
                em.flush();
                result = "Factory Raw material [id = " + factoryRawMaterial.getFactoryRawMaterialId() + " Name = " + factoryRawMaterial.getMaterialName() + "] has been added";

            } else {// itemType.equals("RetailProduct")
                RetailProductEntity retailProduct = em.find(RetailProductEntity.class, itemId);
                FactoryRetailProductEntity factoryRetailProduct = new FactoryRetailProductEntity();
                em.persist(factoryRetailProduct);
                factoryRetailProduct.setName(retailProduct.getName());
                factoryRetailProduct.setDescription(retailProduct.getDescription());
                factoryRetailProduct.setUnit(retailProduct.getUnit());

                //create relationship between factory and factory retail product
                factoryRetailProduct.setFactory(factory);
                factory.getFactoryRetailProducts().add(factoryRetailProduct);
                //create relationship between raw material and factory retail product
                factoryRetailProduct.setRetailProduct(retailProduct);
                retailProduct.getFactoryRetailProducts().add(factoryRetailProduct);
                em.flush();

                result = "Factory Retail prodcuct [id = " + factoryRetailProduct.getFactoryRetailProdctId() + " Name = " + factoryRetailProduct.getName() + "] has been added";
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Item has not been added successfully...\nPlease try again...";
        }
        System.out.println(result);
        return result;
    }

    //add item  (add contract with existing supplier): 
    //user chooses from the list of available items (raw materials or retail products)
    @Override
    public String addContract(Long factoryId, Long supplierId, String itemType, Long itemId,
            Double contractPrice, Integer leadTime, Double lotSize,
            Calendar contractStartDate, Calendar contractEndDate)
            throws Exception {
        System.out.println("addContract():");
        String result = null;
        String unit = null;
        if (!removeTime(contractStartDate).before(removeTime(contractEndDate))) {
            result = "\nContract start date is not before contract end date. "
                    + "\nPlease enter correct date.\n";
            return result;
        }

        try {
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
                contract.setTypeIndicator(1);
                em.persist(contract);
                em.flush();
                result = "New contract [id = " + contract.getContractId() + "] added!";
            } else {//itemType.equals("RetailProduct")
                FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, itemId);

                //create relationship between contract and factory retail product
                factoryRetailProduct.getContracts().add(contract);
                contract.setFactoryRetailProduct(factoryRetailProduct);

                unit = factoryRetailProduct.getUnit();
                contract.setTypeIndicator(3);

                em.persist(contract);
                em.flush();
                result = "New contract [id = " + contract.getContractId() + "] added!";
            }

            contract.create(contractPrice, leadTime, unit, lotSize, contractStartDate, contractEndDate);

            //create relationship between supplier and contract
            contract.setSupplier(supplier);

            supplier.getContractList().add(contract);
            em.flush();

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Contract added failed.../n ";
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

                //check whether there is an unexpired contract with the supplier
                //if at least one is unexpired, the item cannot be deleted
                for (ContractEntity contract : contractList) {

                    Calendar contractEndDate = contract.getContractEndDate();
                    Calendar today = Calendar.getInstance();

                    if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {
                        result = "Raw Material [id = " + factoryRawMaterial.getFactoryRawMaterialId() + "] contains at least one unexpired contract, it cannot be deleted ";
                        System.out.println(result);
                        return result;
                    }

                }
                if (factoryRawMaterial.getBlockedInventory() != 0) {
                    System.out.println("rm : getBlockedInventory");
                    result = "You cannot delete this raw material because it still has inventory.";
                    return result;
                } else if (factoryRawMaterial.getUnrestrictedInventory() != 0) {
                    System.out.println("rm : getUnrestrictedInventory");
                    result = "You cannot delete this raw material because it still has inventory.";
                    return result;
                }

                factoryRawMaterial.setIsDeleted(Boolean.TRUE);
                result = "Raw Material [id = " + factoryRawMaterial.getFactoryRawMaterialId() + "] has been deleted.";

                em.flush();

            } else {//itemType.equals("RetailProduct")
                FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, itemFactoryId);
                String rpName = factoryRetailProduct.getName();
                Collection<ContractEntity> contractList = factoryRetailProduct.getContracts();

                //check whether there is an unexpired contract with the supplier
                //if at least one is unexpired, the supplier cannot be deleted
                for (ContractEntity contract : contractList) {
                    Calendar contractEndDate = contract.getContractEndDate();
                    Calendar today = Calendar.getInstance();

                    if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {//check unexpired contract
                        result = "Retail Product [id = " + factoryRetailProduct.getDescription() + "] contains at least one unexpired contract, it cannot be deleted ";
                        System.out.println(result);
                        return result;
                    }
                }

                if (factoryRetailProduct.getBlockedInventory() != 0) {
                    System.out.println("RP : getBlockedInventory");
                    result = "You cannot delete this raw material because it still has inventory.";
                    return result;
                } else if (factoryRetailProduct.getUnrestrictedInventory() != 0) {
                    System.out.println("RP : getUnrestrictedInventory");
                    result = "You cannot delete this raw material because it still has inventory.";
                    return result;
                } else if (factoryRetailProduct.getReturnedInventory() != 0) {
                    System.out.println("RP : getReturnedInventory");
                    result = "You cannot delete this raw material because it still has inventory.";
                    return result;
                }

                factoryRetailProduct.setIsDeleted(Boolean.TRUE);
                result = "Retail Product [id = " + factoryRetailProduct.getDescription() + "] has been deleted.";
                em.flush();
            }

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Delete item failed.../nPlease try again...";
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

    //check whether a contract has expired
    private boolean isExpired(ContractEntity contract) {
        boolean isExpired = true;

        Calendar contractEndDate = contract.getContractEndDate();
        Calendar today = Calendar.getInstance();

        if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {//if not expired
            isExpired = false;
        }
        return isExpired;
    }

    @Override
    public UserEntity getUser(String userId) throws Exception {
        UserEntity user = em.find(UserEntity.class, userId);
        return user;
    }

}
