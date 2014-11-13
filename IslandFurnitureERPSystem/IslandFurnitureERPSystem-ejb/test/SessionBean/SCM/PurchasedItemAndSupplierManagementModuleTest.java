/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.RawMaterialEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Factory.SCM.SupplierEntity;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yoky
 */
public class PurchasedItemAndSupplierManagementModuleTest {

    private PurchasedItemAndSupplierManagementModuleRemote PurchasedItemAndSupplierManagementModule = lookupRemote();

    public PurchasedItemAndSupplierManagementModuleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of viewRawMaterialWithSelectType method, of class
     * PurchasedItemAndSupplierManagementModule.
     */
    @Test
    public void testViewRawMaterialWithSelectType1() throws Exception {
        System.out.println("viewRawMaterialWithSelectType");
        Long factoryId = 8L;
        String result = "";
        try {
            PurchasedItemAndSupplierManagementModule.viewRawMaterialWithSelectType(factoryId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory is not found!", result);
    }

    /**
     * Test of viewRawMaterialWithSelectType method, of class
     * PurchasedItemAndSupplierManagementModule.
     */
    @Test
    public void testViewRawMaterialWithSelectType2() throws Exception {
        System.out.println("viewRawMaterialWithSelectType");
        Long factoryId = 1L;
        Collection<FactoryRawMaterialEntity> result = PurchasedItemAndSupplierManagementModule.viewRawMaterialWithSelectType(factoryId);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of addItem method, of class
     * PurchasedItemAndSupplierManagementModule.
     */
    @Test
    public void testAddItem() throws Exception {
        System.out.println("addItem");
        Long factoryId = 1L;
        String itemType = "RawMaterial";
        Long itemId = 70L;
        String result = PurchasedItemAndSupplierManagementModule.addItem(factoryId, itemType, itemId);
        assertEquals("Item has not been added successfully...\nPlease try again...", result);

    }

    /**
     * Test of getUser method, of class
     * PurchasedItemAndSupplierManagementModule.
     */
    @Test
    public void testGetUser1() throws Exception {
        System.out.println("getUser");
        String userId = "F1111111";
        String result = "";
        try {
            PurchasedItemAndSupplierManagementModule.getUser(userId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("User is not found!", result);
    }

    /**
     * Test of getUser method, of class
     * PurchasedItemAndSupplierManagementModule.
     */
    @Test
    public void testGetUser2() throws Exception {
        System.out.println("getUser");
        String userId = "F1000001";
        UserEntity result = PurchasedItemAndSupplierManagementModule.getUser(userId);
        assertNotNull(result);
    }

    /**
     * Test of deleteSupplier method, of class
     * PurchasedItemAndSupplierManagementModule.
     */
    @Test
    public void testDeleteSupplier() throws Exception {
        System.out.println("deleteSupplier");
        Long supplierId = 200L;
        String result = PurchasedItemAndSupplierManagementModule.deleteSupplier(supplierId);
        assertEquals("Supplier is not found!", result);
    }

    private PurchasedItemAndSupplierManagementModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (PurchasedItemAndSupplierManagementModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/PurchasedItemAndSupplierManagementModule!SessionBean.SCM.PurchasedItemAndSupplierManagementModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    /**
     * Test of editSupplier method, of class
     * PurchasedItemAndSupplierManagementModule.
     */
    @Test
    public void testEditSupplier() throws Exception {
        System.out.println("editSupplier");
        Long supplierId = 160L;
        String name = "Supplier Test 2";
        String address = "address test 2";
        String telephone = "10203040";
        String fax = "50607080";
        String remark = "Remark test 2";
        String result = PurchasedItemAndSupplierManagementModule.editSupplier(supplierId, name, address, telephone, fax, remark);
        assertEquals("Supplier is not found!", result);
    }

    /**
     * Test of addSupplier method, of class
     * PurchasedItemAndSupplierManagementModule.
     */
    @Test
    public void testAddSupplier() throws Exception {
        System.out.println("addSupplier");
        Long supplierId = 160L;

        String itemType = "RawMaterial";
        Long itemId = 100L;
        String name = "Supplier Test";
        String telephone = "12345678";
        String fax = "12345678";
        String remark = "Remark test";
        Double contractPrice = 12.3;
        Integer leadTime = 5;
        Double lotSize = 10D;
        Calendar contractStartDate = Calendar.getInstance();
        contractStartDate.set(2103, 8, 13, 12, 0, 0);
        Calendar contractEndDate = Calendar.getInstance();
        contractEndDate.set(2105, 8, 13, 12, 0, 0);
        String result = PurchasedItemAndSupplierManagementModule.addSupplier(itemType, itemId, name, name, telephone, fax, remark, contractPrice, leadTime, lotSize, contractStartDate, contractEndDate);
        assertEquals("Factory Raw Material is not found!", result);
    }
}
