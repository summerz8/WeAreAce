/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.BOMEntity;
import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.MRP.PlannedOrderEntity;
import java.util.List;
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
public class PlannedOrderManagementModuleTest {

    private PlannedOrderManagementModuleRemote PlannedOrderManagementModule = lookupRemote();

    public PlannedOrderManagementModuleTest() {
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
     * Test of CheckBOM method, of class PlannedOrderManagementModule.
     */
    @Test
    public void testCheckBOM() throws Exception {
        System.out.println("CheckBOM");
        Long ProductID = 10L;
        String result = "";
        try {
            PlannedOrderManagementModule.CheckBOM(ProductID);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Product is not found!", result);
    }

    /**
     * Test of editPlannedOrder method, of class PlannedOrderManagementModule.
     */
    @Test
    public void testEditPlannedOrder() throws Exception {
        System.out.println("editPlannedOrder");
        Long id = -1L;
        String field = "status";
        String temp = "confirmed";
        Object content = temp;
        String result = "";
        try {
            PlannedOrderManagementModule.editPlannedOrder(id, field, content);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Planned Order is not found!", result);
    }

    /**
     * Test of DeletePlannedOrder method, of class PlannedOrderManagementModule.
     */
    @Test
    public void testDeletePlannedOrder() throws Exception {
        System.out.println("DeletePlannedOrder");
        Long PlannedOrderId = 2L;
        boolean result = PlannedOrderManagementModule.DeletePlannedOrder(PlannedOrderId);
        assertTrue(result);
    }

    /**
     * Test of getPlannedOrder method, of class PlannedOrderManagementModule.
     */
    @Test
    public void testGetPlannedOrder1() throws Exception {
        System.out.println("getPlannedOrder");
        Long id = 1L;
        String department = "H";
        List<PlannedOrderEntity> result = PlannedOrderManagementModule.getPlannedOrder(id, department);
        assertNotNull(result);
    }

    /**
     * Test of getPlannedOrder method, of class PlannedOrderManagementModule.
     */
    @Test
    public void testGetPlannedOrder2() throws Exception {
        System.out.println("getPlannedOrder");
        Long id = -1L;
        String department = "H";
        String result = "";
        try {
            PlannedOrderManagementModule.getPlannedOrder(id, department);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Department is not found!", result);
    }

    /**
     * Test of getPlannedOrder method, of class PlannedOrderManagementModule.
     */
    @Test
    public void testGetPlannedOrder3() throws Exception {
        System.out.println("getPlannedOrder");
        Long id = 1L;
        String department = "F";
        List<PlannedOrderEntity> result = PlannedOrderManagementModule.getPlannedOrder(id, department);
        assertNotNull(result);
    }

    /**
     * Test of getUnconfirmedPlannedOrder method, of class
     * PlannedOrderManagementModule.
     */
    @Test
    public void testGetUnconfirmedPlannedOrder() throws Exception {
        System.out.println("getUnconfirmedPlannedOrder");
        Long id = 1L;
        String department = "F";
        List<PlannedOrderEntity> result = PlannedOrderManagementModule.getUnconfirmedPlannedOrder(id, department);
        assertNotNull(result);
    }

    /**
     * Test of getConfirmedPlannedOrder method, of class
     * PlannedOrderManagementModule.
     */
    @Test
    public void testGetConfirmedPlannedOrder() throws Exception {
        System.out.println("getConfirmedPlannedOrder");
        Long id = 1L;
        String department = "F";
        List<PlannedOrderEntity> result = PlannedOrderManagementModule.getConfirmedPlannedOrder(id, department);
        assertNotNull(result);
    }

    /**
     * Test of getCancelledPlannedOrder method, of class
     * PlannedOrderManagementModule.
     */
    @Test
    public void testGetCancelledPlannedOrder() throws Exception {
        System.out.println("getCancelledPlannedOrder");
        Long id = 1L;
        String department = "F";
        List<PlannedOrderEntity> result = PlannedOrderManagementModule.getCancelledPlannedOrder(id, department);
        assertNotNull(result);
    }

    /**
     * Test of createPlannedOrder method, of class PlannedOrderManagementModule.
     */
    @Test
    public void testCreatePlannedOrder() throws Exception {
        System.out.println("createPlannedOrder");
        Long productionPlanId = 1000L;
        String result = "";
        try {
            PlannedOrderManagementModule.createPlannedOrder(productionPlanId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Production Plan is not found!", result);
    }

    /**
     * Test of findFactoryRawMaterial method, of class
     * PlannedOrderManagementModule.
     */
    @Test
    public void testFindFactoryRawMaterial1() throws Exception {
        System.out.println("findFactoryRawMaterial");
        Long factoryId = 1L;
        Long materialId = 100L;
        String result = "";
        try {
            PlannedOrderManagementModule.findFactoryRawMaterial(factoryId, materialId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Raw Material is not found!", result);
    }

    /**
     * Test of findFactoryRawMaterial method, of class
     * PlannedOrderManagementModule.
     */
    @Test
    public void testFindFactoryRawMaterial2() throws Exception {
        System.out.println("findFactoryRawMaterial");
        Long factoryId = 100L;
        Long materialId = 1L;
        String result = "";
        try {
            PlannedOrderManagementModule.findFactoryRawMaterial(factoryId, materialId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory is not found!", result);
    }

    private PlannedOrderManagementModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (PlannedOrderManagementModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/PlannedOrderManagementModule!SessionBean.MRP.PlannedOrderManagementModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
