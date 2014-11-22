/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.Factory.SCM.InboundMovementEntity;
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
public class RawMaterialInventoryMonitoringModuleTest {

    private RawMaterialInventoryMonitoringModuleRemote RawMaterialInventoryMonitoringModule = lookupRemote();

    public RawMaterialInventoryMonitoringModuleTest() {
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
     * Test of viewWeeklyRawMaterialInventoryInFlow method, of class
     * RawMaterialInventoryMonitoringModule.
     */
    @Test
    public void testViewWeeklyRawMaterialInventoryInFlow1() throws Exception {
        System.out.println("viewWeeklyRawMaterialInventoryInFlow");
        long factoryId = 1L;
        List<InboundMovementEntity> result = RawMaterialInventoryMonitoringModule.viewWeeklyRawMaterialInventoryInFlow(factoryId);
        assertNotNull(result);
    }

    @Test
    public void testViewWeeklyRawMaterialInventoryInFlow2() throws Exception {
        System.out.println("viewWeeklyRawMaterialInventoryInFlow");
        long factoryId = -1L;
        String result = "";
        try {
            RawMaterialInventoryMonitoringModule.viewWeeklyRawMaterialInventoryInFlow(factoryId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory is not found!", result);
    }

    /**
     * Test of viewAllWeeklyRawMaterialInventoryInFlow method, of class
     * RawMaterialInventoryMonitoringModule.
     */
    @Test
    public void testViewAllWeeklyRawMaterialInventoryInFlow() throws Exception {
        System.out.println("viewAllWeeklyRawMaterialInventoryInFlow");
        List result = RawMaterialInventoryMonitoringModule.viewAllWeeklyRawMaterialInventoryInFlow();
        assertNotNull(result);
    }

    private RawMaterialInventoryMonitoringModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (RawMaterialInventoryMonitoringModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/RawMaterialInventoryMonitoringModule!SessionBean.SCM.RawMaterialInventoryMonitoringModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
