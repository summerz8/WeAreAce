/*
 * PurchaseItemAndSupplierManagementModuleLocal.java
 * 
 * It is a local interface that control the functions for managing purchased items(RM or RP) within a supplier's contract
 * and the contracted supplier's account information.
 * (Assumption: a supplier should have at least one contract with the factory)
 * Basic funtions are: 
 * 1. Add supplier (with at least one contract information)
 * 2. Edit supplier account information (e.g. change in price)
 * 3. Delete supplier account (no more coorperation on the item, which also means delete a specific contract)
 * 4. Add Item (a. add new item contract with a existing supplier)
 * 5. Delete Item
 */
package SessionBean.SCM;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.RawMaterialEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Factory.SCM.SupplierEntity;
import java.util.Calendar;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Shiyu
 */
@Local
public interface PurchasedItemAndSupplierManagementModuleLocal {

    public Collection<FactoryRawMaterialEntity> viewRawMaterialWithSelectType(Long factoryId) throws Exception;

    public Collection<FactoryRetailProductEntity> viewRetailProductWithSelectType(Long factoryId) throws Exception;

    public Collection<RawMaterialEntity> viewRawMaterialListNotInFactory(Long factoryId) throws Exception;

    public Collection<RetailProductEntity> viewRetailProductListNotInFactory(Long factoryId) throws Exception;

    public String addSupplier(String itemType, Long itemId, String name, String address,
            String telephone, String fax, String remark, Double contractPrice,
            Integer leadTime, Double lotSize, Calendar contractStartDate, Calendar contractEndDate) throws Exception;

    public Collection<SupplierEntity> viewAvailSupplier(Long factoryId) throws Exception;

    public Collection<SupplierEntity> viewSupplierForItem(String itemType, Long itemId) throws Exception;

    public Collection<SupplierEntity> viewSupplierCouldBeAddedForItem(String itemType, Long itemId) throws Exception;

    public String editSupplier(Long supplierId, String name, String address, String telephone, String fax, String remark) throws Exception;

    public String deleteSupplier(Long supplierId) throws Exception;

    public String addItem(Long factoryId, String itemType, Long itemId) throws Exception;

    public String addContract(Long factoryId, Long supplierId, String itemType, Long itemId,
            Double contractPrice, Integer leadTime, Double lotSize, Calendar contractStartDate, Calendar contractEndDate) throws Exception;

    public String deleteItem(String itemType, Long itemFactoryId) throws Exception;
}
