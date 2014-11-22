/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
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
public class IntegratedPlannedOrderManagementTest {

    private IntegratedPlannedOrderManagementRemote IntegratedPlannedOrderManagement = lookupRemote();

    public IntegratedPlannedOrderManagementTest() {
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
     * Test of createIntegratedPlannedOrder method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testCreateIntegratedPlannedOrder() throws Exception {
        System.out.println("createIntegratedPlannedOrder");
        Calendar targetPeriod = Calendar.getInstance();
        targetPeriod.set(Calendar.YEAR, 2014);
        targetPeriod.set(Calendar.MONTH, 10);
        Long factoryRawMaterialId = 0L;
        Long id = 1L;
        String department = "F";
        String result = "";
        try {
            IntegratedPlannedOrderManagement.createIntegratedPlannedOrder(targetPeriod, factoryRawMaterialId, id, department);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory Raw Material is not found!", result);
    }

    /**
     * Test of getConfirmedPlannedOrder method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetConfirmedPlannedOrder1() throws Exception {
        System.out.println("getConfirmedPlannedOrder");
        Long id = 1L;
        String department = "H";
        List<PlannedOrderEntity> result = IntegratedPlannedOrderManagement.getConfirmedPlannedOrder(id, department);
        assertNotNull(result);
    }

    /**
     * Test of getConfirmedPlannedOrder method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetConfirmedPlannedOrder2() throws Exception {
        System.out.println("getConfirmedPlannedOrder");
        Long id = 0L;
        String department = "H";
        String result = "";
        try {
            IntegratedPlannedOrderManagement.getConfirmedPlannedOrder(id, department);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Department is not found!", result);
    }

    /**
     * Test of getConfirmedPlannedOrder method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetConfirmedPlannedOrder3() throws Exception {
        System.out.println("getConfirmedPlannedOrder");
        Long id = 1L;
        String department = "F";
        List<PlannedOrderEntity> result = IntegratedPlannedOrderManagement.getConfirmedPlannedOrder(id, department);
        assertNotNull(result);
    }

    /**
     * Test of getIntegratedPlannedOrder method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetIntegratedPlannedOrder1() throws Exception {
        System.out.println("getIntegratedPlannedOrder");
        Long id = 1L;
        String department = "H";
        List<IntegratedPlannedOrderEntity> result = IntegratedPlannedOrderManagement.getIntegratedPlannedOrder(id, department);
        assertNotNull(result);
    }

    /**
     * Test of getIntegratedPlannedOrder method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetIntegratedPlannedOrder2() throws Exception {
        System.out.println("getIntegratedPlannedOrder");
        Long id = 0L;
        String department = "H";
        String result = "";
        try {
            IntegratedPlannedOrderManagement.getIntegratedPlannedOrder(id, department);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Department is not found!", result);
    }

    /**
     * Test of getIntegratedPlannedOrder method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetIntegratedPlannedOrder3() throws Exception {
        System.out.println("getIntegratedPlannedOrder");
        Long id = 1L;
        String department = "F";
        List<IntegratedPlannedOrderEntity> result = IntegratedPlannedOrderManagement.getIntegratedPlannedOrder(id, department);
        assertNotNull(result);
    }

    /**
     * Test of getRetailProductPurchasePlan method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetRetailProductPurchasePlan1() throws Exception {
        System.out.println("getRetailProductPurchasePlan");
        Long id = 1L;
        String department = "H";
        List<IntegratedPlannedOrderEntity> result = IntegratedPlannedOrderManagement.getRetailProductPurchasePlan(id, department);
        assertNotNull(result);
    }

    /**
     * Test of getRetailProductPurchasePlan method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetRetailProductPurchasePlan2() throws Exception {
        System.out.println("getRetailProductPurchasePlan");
        Long id = 0L;
        String department = "H";
        String result = "";
        try {
            IntegratedPlannedOrderManagement.getRetailProductPurchasePlan(id, department);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Department is not found!", result);
    }

    /**
     * Test of getRetailProductPurchasePlan method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetRetailProductPurchasePlan3() throws Exception {
        System.out.println("getRetailProductPurchasePlan");
        Long id = 1L;
        String department = "F";
        List<IntegratedPlannedOrderEntity> result = IntegratedPlannedOrderManagement.getRetailProductPurchasePlan(id, department);
        assertNotNull(result);
    }

    /**
     * Test of editIntegratedPlannedOrder method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testEditIntegratedPlannedOrder() throws Exception {
        System.out.println("editIntegratedPlannedOrder");
        Long id = 0L;
        String field = "targetPeriod";
        Calendar temp = Calendar.getInstance();
        temp.set(Calendar.YEAR, 2014);
        temp.set(Calendar.MONTH, 11);
        Object content = temp;
        String result = "";
        try {
            IntegratedPlannedOrderManagement.editIntegratedPlannedOrder(id, field, content);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Integrated Planned Order is not found!", result);
    }

    /**
     * Test of findFactoryRawMaterialIdList method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testFindFactoryRawMaterialIdList1() throws Exception {
        System.out.println("findFactoryRawMaterialIdList");
        Long id = 1L;
        String department = "F";
        Long factoryRawMaterialId = 1L;
        boolean result = IntegratedPlannedOrderManagement.findFactoryRawMaterialIdList(id, department, factoryRawMaterialId);
        assertFalse(result);
    }
    
    /**
     * Test of findFactoryRawMaterialIdList method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testFindFactoryRawMaterialIdList2() throws Exception {
        System.out.println("findFactoryRawMaterialIdList");
        Long id = 1L;
        String department = "F";
        Long factoryRawMaterialId = 100L;
        boolean result = IntegratedPlannedOrderManagement.findFactoryRawMaterialIdList(id, department, factoryRawMaterialId);
        assertFalse(result);
    }

    /**
     * Test of getFactoryRawMaterial method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetFactoryRawMaterial1() throws Exception {
        System.out.println("getFactoryRawMaterial");
        Long factoryId = 1L;
        String department = "H";
        List<FactoryRawMaterialEntity> result = IntegratedPlannedOrderManagement.getFactoryRawMaterial(factoryId, department);
        assertNotNull(result);
    }
    
    /**
     * Test of getFactoryRawMaterial method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetFactoryRawMaterial2() throws Exception {
        System.out.println("getFactoryRawMaterial");
        Long factoryId = -1L;
        String department = "H";
        String result = "";
        try {
         IntegratedPlannedOrderManagement.getFactoryRawMaterial(factoryId, department);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Department is not found!", result);
    }

    /**
     * Test of getFactoryRawMaterial method, of class
     * IntegratedPlannedOrderManagement.
     */
    @Test
    public void testGetFactoryRawMaterial3() throws Exception {
        System.out.println("getFactoryRawMaterial");
        Long factoryId = 1L;
        String department = "F";
        List<FactoryRawMaterialEntity> result = IntegratedPlannedOrderManagement.getFactoryRawMaterial(factoryId, department);
        assertNotNull(result);
    }
    
    private IntegratedPlannedOrderManagementRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (IntegratedPlannedOrderManagementRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/IntegratedPlannedOrderManagement!SessionBean.MRP.IntegratedPlannedOrderManagementRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
