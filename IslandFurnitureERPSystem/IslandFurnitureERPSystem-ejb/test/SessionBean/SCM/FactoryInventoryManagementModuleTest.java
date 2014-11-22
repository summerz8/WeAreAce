/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FactoryInventoryManagementModuleTest {
    
    private FactoryInventoryManagementModuleRemote FactoryInventoryManagementModule = lookupRemote();
    
    public FactoryInventoryManagementModuleTest() {
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
     * Test of listStorageBinInformation method, of class FactoryInventoryManagementModule.
     */
    @Test
    public void testListStorageBinInformation1() throws Exception {
        System.out.println("listStorageBinInformation");
        Long factoryId = 2L;
        List result = FactoryInventoryManagementModule.listStorageBinInformation(factoryId);
        assertFalse(result.isEmpty());
    }

/**
     * Test of listStorageBinInformation method, of class FactoryInventoryManagementModule.
     */
    @Test
    public void testListStorageBinInformation2() throws Exception {
        System.out.println("listStorageBinInformation");
        Long factoryId = 90L;
        String result = "";
        try {
            FactoryInventoryManagementModule.listStorageBinInformation(factoryId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory is not found!", result);
    }

    /**
     * Test of recordInboundMovement method, of class FactoryInventoryManagementModule.
     */
    @Test
    public void testRecordInboundMovement1() throws Exception {
        System.out.println("recordInboundMovement");
        Long factoryId = 1L;
        Long goodsReceiptId = 1L;
        Long toBinId = 500L;
        String status = "unrestricted";
        Double quantity = 40D;
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2014, 9, 30, 15, 0, 0);
        Long expResult = -3L;
        Long result = FactoryInventoryManagementModule.recordInboundMovement(factoryId, goodsReceiptId, toBinId, status, quantity, creationDate);
        assertEquals(expResult, result);
    }
    
    

    /**
     * Test of recordInboundMovement method, of class FactoryInventoryManagementModule.
     */
    @Test
    public void testRecordInboundMovement4() throws Exception {
        System.out.println("recordInboundMovement");
        Long factoryId = 1L;
        Long goodsReceiptId = 20L;
        Long toBinId = 5L;
        String status = "";
        Double quantity = 40D;
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2014, 9, 30, 15, 0, 0);
        Long expResult = -1L;
        Long result = FactoryInventoryManagementModule.recordInboundMovement(factoryId, goodsReceiptId, toBinId, status, quantity, creationDate);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of recordCurrentInventoryLevel method, of class FactoryInventoryManagementModule.
     */
    @Test
    public void testRecordCurrentInventoryLevel() throws Exception {
        System.out.println("recordCurrentInventoryLevel");
        Long factoryId = 1L;
        int result = FactoryInventoryManagementModule.recordCurrentInventoryLevel(factoryId);
        assertEquals(0, result);
    }

    private FactoryInventoryManagementModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (FactoryInventoryManagementModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/FactoryInventoryManagementModule!SessionBean.SCM.FactoryInventoryManagementModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
