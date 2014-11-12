/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.ProductEntity;
import Entity.Store.StoreEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hangsun
 */
public class Factory_StoreManagementModuleTest {

    private Factory_StoreManagementModuleRemote Factory_StoreManagementModule = lookupRemote();

    public Factory_StoreManagementModuleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of AddFactory method, of class Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testAddFactory1() {
        System.out.println("AddFactory");
        String country = "Singapore";
        String address = "19 Kent Ridge Crescent, NUS";
        String contact = "93727960";
        String manager = "F1000001";
        Factory_StoreManagementModule.AddFactory(country, address, contact, manager);
    }

    @Test
    public void testAddFactory2() {
        System.out.println("AddFactory");
        String country = "China";
        String address = "Xi'an";
        String contact = "15664855978";
        String manager = "F1000002";
        Factory_StoreManagementModule.AddFactory(country, address, contact, manager);
    }

    /**
     * Test of DeleteFactory method, of class
     * Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testDeleteFactory() {
        System.out.println("DeleteFactory");
        long factoryId = 0L;
        String result = new String();
        try {
            Factory_StoreManagementModule.DeleteFactory(factoryId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }

        assertEquals("Factory is not found!", result);
    }

    /**
     * Test of ModifyFactory method, of class
     * Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testModifyFactory() {
        System.out.println("ModifyFactory");
        long factoryId = 0L;
        String country = "China";
        String address = "Shang Hai";
        String contact = "123456";
        String manager = "F10000002";
        String result = new String();
        try {
            Factory_StoreManagementModule.ModifyFactory(factoryId, country, address, contact, manager);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory is not found!", result);
    }

    /**
     * Test of ListFactory method, of class Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testListFactory() {
        System.out.println("ListFactory");
        List<FactoryEntity> result = Factory_StoreManagementModule.ListFactory();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of AddStore method, of class Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testAddStore() {
        System.out.println("AddStore");
        String country = "China";
        String address = "Shang Hai";
        String contact = "12345";
        String manager = "S1000004";
        Factory_StoreManagementModule.AddStore(country, address, contact, manager);
    }

    /**
     * Test of DeleteStore method, of class Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testDeleteStore() {
        System.out.println("DeleteStore");
        Long storeId = 0L;
        String result = new String();
        try {
            Factory_StoreManagementModule.DeleteStore(storeId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Store is not found!", result);
    }

    /**
     * Test of ModifyStore method, of class Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testModifyStore() {
        System.out.println("ModifyStore");
        long storeId = 0L;
        String country = "Singapore";
        String address = "NUS";
        String contact = "9879262";
        String manager = "S1000004";
        String result = new String();
        try {
            Factory_StoreManagementModule.ModifyStore(storeId, country, address, contact, manager);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Store is not found!", result);
    }

    /**
     * Test of ListStore method, of class Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testListStore() {
        System.out.println("ListStore");
        List<StoreEntity> result = Factory_StoreManagementModule.ListStore();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of viewProductListNotInFactory method, of class
     * Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testViewProductListNotInFactory1() throws Exception {
        System.out.println("viewProductListNotInFactory");
        Long factoryId = -1L;
        Collection<ProductEntity> temp = new ArrayList();
        String result = new String();
        try {
            temp = Factory_StoreManagementModule.viewProductListNotInFactory(factoryId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory is not found!", result);
    }

    @Test
    public void testViewProductListNotInFactory2() throws Exception {
        System.out.println("viewProductListNotInFactory");
        Long factoryId = 1L;
        try {
            Collection<ProductEntity> result = Factory_StoreManagementModule.viewProductListNotInFactory(factoryId);
            assertNotNull(result);
        } catch (Exception ex) {
            String err = ex.getMessage();
        }
    }

    /**
     * Test of listFactoryProduct method, of class
     * Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testListFactoryProduct1() throws Exception {
        System.out.println("listFactoryProduct");
        Long factoryId = -1L;
        Collection<FactoryProductEntity> temp = new ArrayList();
        String result = new String();
        try {
            temp = Factory_StoreManagementModule.listFactoryProduct(factoryId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory is not found!", result);

    }

    @Test
    public void testListFactoryProduct2() throws Exception {
        System.out.println("listFactoryProduct");
        Long factoryId = 1L;
        try {
            Collection<FactoryProductEntity> result = Factory_StoreManagementModule.listFactoryProduct(factoryId);
            assertNotNull(result);
        } catch (Exception ex) {
            String err = ex.getMessage();
        }

    }

    /**
     * Test of deleteFatoryProduct method, of class
     * Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testDeleteFatoryProduct1() {
        System.out.println("deleteFatoryProduct");
        Long factoryProductId = 4L;
        Integer expResult = 1;
        Integer result = Factory_StoreManagementModule.deleteFatoryProduct(factoryProductId);
        assertEquals(expResult, result);
    }

    @Test
    public void testDeleteFatoryProduct2() {
        System.out.println("deleteFatoryProduct");
        Long factoryProductId = 2L;
        Integer expResult = 1;
        Integer result = Factory_StoreManagementModule.deleteFatoryProduct(factoryProductId);
        assertEquals(expResult, result);
    }

    /**
     * Test of addFactoryProduct method, of class
     * Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testAddFactoryProduct1() {
        System.out.println("addFactoryProduct");
        Long FactoryId = -1L;
        Long ProductId = 1L;
        Integer expResult = 0;
        Integer result = Factory_StoreManagementModule.addFactoryProduct(FactoryId, ProductId);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddFactoryProduct2() {
        System.out.println("addFactoryProduct");
        Long FactoryId = 1L;
        Long ProductId = -1L;
        Integer expResult = 1;
        Integer result = Factory_StoreManagementModule.addFactoryProduct(FactoryId, ProductId);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddFactoryProduct3() {
        System.out.println("addFactoryProduct");
        Long FactoryId = 1L;
        Long ProductId = 6L;
        Integer expResult = 2;
        Integer result = Factory_StoreManagementModule.addFactoryProduct(FactoryId, ProductId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFactory method, of class Factory_StoreManagementModuleRemote.
     */
    @Test
    public void testGetFactory1() {
        System.out.println("getFactory");
        Long factoryId = 1L;
        FactoryEntity result = Factory_StoreManagementModule.getFactory(factoryId);
        assertFalse(result == null);
    }

    @Test
    public void testGetFactory2() {
        System.out.println("getFactory");
        Long factoryId = -2L;
        FactoryEntity result = Factory_StoreManagementModule.getFactory(factoryId);
        assertTrue(result == null);
    }

    public class Factory_StoreManagementModuleRemoteImpl implements Factory_StoreManagementModuleRemote {

        public void AddFactory(String country, String address, String contact, String manager) {
        }

        public void DeleteFactory(long factoryId) {
        }

        public void ModifyFactory(long factoryId, String country, String address, String contact, String manager) {
        }

        public List<FactoryEntity> ListFactory() {
            return null;
        }

        public void AddStore(String country, String address, String contact, String manager) {
        }

        public void DeleteStore(Long storeId) {
        }

        public void ModifyStore(long storeId, String country, String address, String contact, String manager) {
        }

        public List<StoreEntity> ListStore() {
            return null;
        }

        public Collection<ProductEntity> viewProductListNotInFactory(Long factoryId) throws Exception {
            return null;
        }

        public Collection<FactoryProductEntity> listFactoryProduct(Long factoryId) throws Exception {
            return null;
        }

        public Integer deleteFatoryProduct(Long factoryProductId) {
            return null;
        }

        public Integer addFactoryProduct(Long FactoryId, Long ProductId) {
            return null;
        }

        public FactoryEntity getFactory(Long factoryId) {
            return null;
        }
    }

    private Factory_StoreManagementModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (Factory_StoreManagementModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/Factory_StoreManagementModule!SessionBean.CommonInFrastructure.Factory_StoreManagementModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
