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
<<<<<<< HEAD
            System.out.println("Session Bean view Raw Material With Select Type: " + factoryRawMaterialList.size());
=======

>>>>>>> 5610e888745b99da80e76f3274ecdb8ab3b53f4d
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
        Collection<RawMaterialEntity> rmList = new ArrayList<> ();
        FactoryEntity currentFactory = em.find(FactoryEntity.class, factoryId);
        Collection<FactoryRawMaterialEntity> currentFactoryRawMaterialList = currentFactory.getFactoryRawMaterials();
        try {
            Query q = em.createQuery("Select rm from RawMaterialEntity RM");
            outerLoop:
            for (Object o : q.getResultList()) {
                RawMaterialEntity rawmaterial = (RawMaterialEntity) o;
                  for(FactoryRawMaterialEntity frm: currentFactoryRawMaterialList){
                      FactoryRawMaterialEntity frawmaterial = frm;
                      if(frawmaterial.getRawMaterial().equals(rawmaterial) && (!frawmaterial.getIsDeleted())){
                           flag = 1;
                           break;
                      }
                  }
                 if(flag == 0){
                     rmList.add(rawmaterial);
                 }
                 flag = 0;
               
            }
        }catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return rmList;
    }

    @Override
    public Collection<RetailProductEntity> viewRetailProductListNotInFactory(Long factoryId) throws Exception {//test works!!
        System.out.println("viewRetailProductListNotInFactory():");
        Integer flag = 0;
        Collection<RetailProductEntity> rpList = new ArrayList<> ();
        FactoryEntity currentFactory = em.find(FactoryEntity.class, factoryId);
        Collection<FactoryRetailProductEntity> currentFactoryRetailProductList = currentFactory.getFactoryRetailProducts();
        try {
            Query q = em.createQuery("Select pd from RetailProductEntity PD");
            outerLoop:
            for (Object o : q.getResultList()) {
                RetailProductEntity rp = (RetailProductEntity) o;
                  for(FactoryRetailProductEntity frp: currentFactoryRetailProductList){
                      FactoryRetailProductEntity fRetailproduct = frp;
                      if(fRetailproduct.getRetailProduct().equals(rp) && (!fRetailproduct.getIsDeleted())){
                           flag = 1;
                           break;
                      }
                  }
                 if(flag == 0){
                     rpList.add(rp);
                 }
                 flag = 0;
               
            }
        }catch (Exception ex) {
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

        if (!removeTime(contractStartDate).before(removeTime(contractEndDate))) {
            result = "\nContract start date is not before contract end date. "
                    + "\nPlease enter correct date.\n";
            return result;
        }

        SupplierEntity supplier = new SupplierEntity();
        em.persist(supplier);

        ContractEntity contract = new ContractEntity();
        em.persist(contract);
        try {
            //create relationship between contract and factory Raw material 
            if (itemType.equals("RawMaterial")) {
                FactoryRawMaterialEntity factoryRawMaterial = em.find(FactoryRawMaterialEntity.class, itemId);
                contract.setFactoryRawMaterial(factoryRawMaterial);
                factoryRawMaterial.getContracts()
                        .add(contract);
                unit = factoryRawMaterial.getUnit();
            } //create relationship between contract and retail products
            else {//itemType.equals("RetailProducts")
                FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, itemId);
                contract.setFactoryRetailProduct(factoryRetailProduct);

                factoryRetailProduct.getContracts().add(contract);
                unit = factoryRetailProduct.getUnit();
            }

            //create new supplier entity and contract entity
            supplier.create(name, address, telephone, fax, remark);
            contract.create(contractPrice, leadTime, unit, lotSize, contractStartDate, contractEndDate);

            //create relationship between supplier ad contract 
            supplier.getContractList().add(contract);
            contract.setSupplier(supplier);

            em.flush();

            result = "Supplier " + supplier.getSupplierName() + " [id = " + supplier.getSupplierId() + " ] created!";
            result = result + "\nContract [id = " + contract.getContractId() + "] created with this supplier!";
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            result = "Supplier has not been created successfully.../nPlease try again...";
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
                    result = "Supplier [id = "+supplier.getSupplierId()+", Name =" + supplierName + "] contains at least one unexpired contract, it cannot be deleted. ";
                    System.out.println(result);
                    return result;
                }
            }
            supplier.setIsDeleted(Boolean.TRUE);

            result = "Supplier [id = "+supplier.getSupplierId()+", Name =" + supplierName + "] has been deleted.";
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
                result = "Factory Raw material [id = "+ factoryRawMaterial.getFactoryRawMaterialId()+ " Name = " + factoryRawMaterial.getMaterialName() + "] has been added";

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

                result = "Factory Retail prodcuct [id = "+ factoryRetailProduct.getFactoryRetailProdctId() + " Name = " + factoryRetailProduct.getName() +"] has been added";
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
                em.persist(contract);
                em.flush();
                result = "New contract [id = " + contract.getContractId() + "] added!";
            } else {//itemType.equals("RetailProduct")
                FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class, itemId);

                //create relationship between contract and factory retail product
                factoryRetailProduct.getContracts().add(contract);
                contract.setFactoryRetailProduct(factoryRetailProduct);

                unit = factoryRetailProduct.getUnit();
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

                Iterator iterator = contractList.iterator();
                //check whether there is an unexpired contract with the supplier
                //if at least one is unexpired, the item cannot be deleted

                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;

                    Calendar contractEndDate = contract.getContractEndDate();
                    Calendar today = Calendar.getInstance();

                    if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {
                        result = "Raw Material [id = " + factoryRawMaterial.getFactoryRawMaterialId() + "] contains at least one unexpired contract, it cannot be deleted ";
                        System.out.println(result);
                        return result;
                    }
                   
                }
                if(factoryRawMaterial.getBlockedInventory() != 0){
                    System.out.println("rm : getBlockedInventory");
                   result = "You cannot delete this raw material because it still has inventory.";
                   return result;
                }
                else if ( factoryRawMaterial.getUnrestrictedInventory() != 0 ){
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
                Iterator iterator = contractList.iterator();

                //check whether there is an unexpired contract with the supplier
                //if at least one is unexpired, the supplier cannot be deleted
                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ContractEntity contract = (ContractEntity) obj;

                    Calendar contractEndDate = contract.getContractEndDate();
                    Calendar today = Calendar.getInstance();

                    if (removeTime(today).compareTo(removeTime(contractEndDate)) <= 0) {//check unexpired contract
                        result = "Retail Product [id = " + factoryRetailProduct.getDescription()+ "] contains at least one unexpired contract, it cannot be deleted ";
                        System.out.println(result);
                        return result;
                    }
                }
                
                if(factoryRetailProduct.getBlockedInventory() != 0){
                    System.out.println("RP : getBlockedInventory");
                   result = "You cannot delete this raw material because it still has inventory.";
                   return result;
                }
                else if ( factoryRetailProduct.getUnrestrictedInventory() != 0 ){
                    System.out.println("RP : getUnrestrictedInventory");
                   result = "You cannot delete this raw material because it still has inventory.";
                   return result;
                }
                else if ( factoryRetailProduct.getReturnedInventory() != 0 ){
                   System.out.println("RP : getReturnedInventory");
                   result = "You cannot delete this raw material because it still has inventory.";
                   return result;
                }

                factoryRetailProduct.setIsDeleted(Boolean.TRUE);
                result = "Retail Product [id = " + factoryRetailProduct.getDescription()+ "] has been deleted.";
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

    //create some dummy value
    public void addDB() {

        System.out.println("addDB(): ");
        FactoryEntity factory1 = new FactoryEntity("Country1", "Apple Road", "11111111", "Manager1", false);
        em.persist(factory1);
        em.flush();
        FactoryEntity factory2 = new FactoryEntity("Country1", "Banana Road", "22222222", "Manager2", false);
        em.persist(factory2);
        em.flush();
        FactoryEntity factory3 = new FactoryEntity("Country1", "Coconut Road", "33333333", "Manager3", false);
        em.persist(factory3);
        em.flush();
        FactoryEntity factory4 = new FactoryEntity("Country1", "Durian Road", "44444444", "Manager4", false);
        em.persist(factory4);
        em.flush();
        FactoryEntity factory5 = new FactoryEntity("Country1", "Fig Road", "55555555", "Manager5", false);
        em.persist(factory5);
        em.flush();
        System.out.println("add 5 factorys");

        RawMaterialEntity rm1 = new RawMaterialEntity("RM1", "This is RM1", false, "Unit");
        em.persist(rm1);
        em.flush();
        RawMaterialEntity rm2 = new RawMaterialEntity("RM2", "This is RM2", false, "Unit");
        em.persist(rm2);
        em.flush();
        RawMaterialEntity rm3 = new RawMaterialEntity("RM3", "This is RM3", false, "Unit");
        em.persist(rm3);
        em.flush();
        RawMaterialEntity rm4 = new RawMaterialEntity("RM4", "This is RM4", false, "Unit");
        em.persist(rm4);
        em.flush();
        RawMaterialEntity rm5 = new RawMaterialEntity("RM5", "This is RM5", false, "Unit");
        em.persist(rm5);
        em.flush();
        System.out.println("add 5 raw materials");

        FactoryRawMaterialEntity frm1 = new FactoryRawMaterialEntity("Unit", "RM1", "This is RM1", false, factory1, rm1);
        em.persist(frm1);
        em.flush();
        FactoryRawMaterialEntity frm2 = new FactoryRawMaterialEntity("Unit", "RM2", "This is RM2", false, factory1, rm2);
        em.persist(frm2);
        em.flush();
        FactoryRawMaterialEntity frm3 = new FactoryRawMaterialEntity("Unit", "RM3", "This is RM3", false, factory1, rm3);
        em.persist(frm3);
        em.flush();
        FactoryRawMaterialEntity frm4 = new FactoryRawMaterialEntity("Unit", "RM4", "This is RM4", false, factory1, rm4);
        em.persist(frm4);
        em.flush();
        FactoryRawMaterialEntity frm5 = new FactoryRawMaterialEntity("Unit", "RM5", "This is RM5", false, factory1, rm5);
        em.persist(frm5);
        em.flush();
        FactoryRawMaterialEntity frm6 = new FactoryRawMaterialEntity("Unit", "RM2", "This is RM2", false, factory2, rm2);
        em.persist(frm6);
        em.flush();
        FactoryRawMaterialEntity frm7 = new FactoryRawMaterialEntity("Unit", "RM3", "This is RM3", false, factory2, rm3);
        em.persist(frm7);
        em.flush();
        FactoryRawMaterialEntity frm8 = new FactoryRawMaterialEntity("Unit", "RM3", "This is RM3", false, factory3, rm3);
        em.persist(frm8);
        em.flush();
        FactoryRawMaterialEntity frm9 = new FactoryRawMaterialEntity("Unit", "RM4", "This is RM4", false, factory3, rm4);
        em.persist(frm9);
        em.flush();
        FactoryRawMaterialEntity frm10 = new FactoryRawMaterialEntity("Unit", "RM4", "This is RM4", false, factory4, rm4);
        em.persist(frm10);
        em.flush();
        System.out.println("add 10 frms");

        factory1.getFactoryRawMaterials().add(frm1);
        factory1.getFactoryRawMaterials().add(frm2);
        factory1.getFactoryRawMaterials().add(frm3);
        factory1.getFactoryRawMaterials().add(frm4);
        factory1.getFactoryRawMaterials().add(frm5);
        factory2.getFactoryRawMaterials().add(frm6);
        factory2.getFactoryRawMaterials().add(frm7);
        factory3.getFactoryRawMaterials().add(frm8);
        factory3.getFactoryRawMaterials().add(frm9);
        factory4.getFactoryRawMaterials().add(frm10);
        System.out.println("Factory1: RM1,RM2,RM3,RM4, RM5");
        System.out.println("Factory2: RM2,RM3");
        System.out.println("Factory3: RM3,RM4");
        System.out.println("Factory4: RM4");

        rm1.getFactoryRawMaterials().add(frm1);
        rm2.getFactoryRawMaterials().add(frm2);
        rm2.getFactoryRawMaterials().add(frm6);
        rm3.getFactoryRawMaterials().add(frm3);
        rm3.getFactoryRawMaterials().add(frm7);
        rm3.getFactoryRawMaterials().add(frm8);
        rm4.getFactoryRawMaterials().add(frm4);
        rm4.getFactoryRawMaterials().add(frm9);
        rm4.getFactoryRawMaterials().add(frm10);
        rm5.getFactoryRawMaterials().add(frm5);
        System.out.println("rm1 add frm1");
        System.out.println("rm2 add frm2, frm6");
        System.out.println("rm3 add frm3, frm7, frm8");
        System.out.println("rm4 add frm4, frm8, frm10");
        System.out.println("rm5 add frm5");
        em.flush();
//    }
//
//    public void addDBSuppleirAndContract() {
        System.out.println("addDBSuppleirAndContract():");
        SupplierEntity s1 = new SupplierEntity("Suppleir 1", "Australia", "1111", "1111", "Supplier 1 in Australia");
        em.persist(s1);
        em.flush();
        SupplierEntity s2 = new SupplierEntity("Suppleir 2", "Brazil", "2222", "2222", "Supplier 2 in Brazil");
        em.persist(s2);
        em.flush();
        SupplierEntity s3 = new SupplierEntity("Suppleir 3", "China", "3333", "3333", "Supplier 3 in China");
        em.persist(s3);
        em.flush();
        SupplierEntity s4 = new SupplierEntity("Suppleir 4", "Demark", "4444", "4444", "Supplier 4 in Demark");
        em.persist(s4);
        em.flush();
        SupplierEntity s5 = new SupplierEntity("Suppleir 5", "Egypt", "5555", "5555", "Supplier 5 in Egypt");
        em.persist(s5);
        em.flush();
        System.out.println("add 5 suppliers");
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date1.set(2014, 0, 1);
        date2.set(2015, 0, 1);
        ContractEntity c1 = new ContractEntity(1D, 1, "unit", 10D, date1, date2, frm1, s1);
        em.persist(c1);
        em.flush();
        date1.set(2014, 1, 1);
        date2.set(2015, 1, 1);
        ContractEntity c2 = new ContractEntity(1D, 1, "unit", 10D, date1, date2, frm1, s2);
        em.persist(c2);
        em.flush();
        date1.set(2014, 2, 1);
        date2.set(2015, 2, 1);
        ContractEntity c3 = new ContractEntity(1D, 1, "unit", 10D, date1, date2, frm1, s3);
        em.persist(c3);
        em.flush();
        date1.set(2014, 3, 1);
        date2.set(2015, 3, 1);
        ContractEntity c4 = new ContractEntity(1D, 1, "unit", 10D, date1, date2, frm2, s4);
        em.persist(c4);
        em.flush();
        date1.set(2014, 4, 1);
        date2.set(2015, 4, 1);
        ContractEntity c5 = new ContractEntity(1D, 1, "unit", 10D, date1, date2, frm2, s5);
        em.persist(c5);
        em.flush();
        date1.set(2014, 5, 1);
        date2.set(2015, 5, 1);
        ContractEntity c6 = new ContractEntity(1D, 1, "unit", 10D, date1, date2, frm3, s1);
        em.persist(c6);
        em.flush();
        date1.set(2014, 6, 1);
        date2.set(2015, 6, 1);
        ContractEntity c7 = new ContractEntity(1D, 1, "unit", 10D, date1, date2, frm4, s1);
        em.persist(c7);
        em.flush();
        System.out.println("add 7 contracts");

        s1.getContractList().add(c1);
        s1.getContractList().add(c6);
        s1.getContractList().add(c7);
        s2.getContractList().add(c2);
        s3.getContractList().add(c3);
        s4.getContractList().add(c4);
        s5.getContractList().add(c5);

        System.out.println("s1 add c1, c6, c7");
        System.out.println("s2 add c2");
        System.out.println("s3 add c3");
        System.out.println("s4 add c4");
        System.out.println("s5 add c5");

        frm1.getContracts().add(c1);
        frm1.getContracts().add(c2);
        frm1.getContracts().add(c3);
        frm2.getContracts().add(c4);
        frm2.getContracts().add(c5);
        frm3.getContracts().add(c6);
        frm3.getContracts().add(c7);
        System.out.println("frm1 add c1, c2, c3");
        System.out.println("frm2 add c4, c5");
        System.out.println("frm3 add c6, c7");

        em.flush();

    }

}
