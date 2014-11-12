/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.MRP.IntegratedPlannedOrderEntity;
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
public class RetailProductPurchasePlanModuleTest {

    private RetailProductPurchasePlanModuleRemote RetailProductPurchasePlanModule = lookupRemote();

    public RetailProductPurchasePlanModuleTest() {
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
     * Test of generateRetailProductPurchasePlan method, of class
     * RetailProductPurchasePlanModule.
     */
    @Test
    public void testGenerateRetailProductPurchasePlan() throws Exception {
        System.out.println("generateRetailProductPurchasePlan");
        Long integratedSalesForecastId = 1L;
        Long factoryRetailProductId = 1L;
        RetailProductPurchasePlanModule.generateRetailProductPurchasePlan(integratedSalesForecastId, factoryRetailProductId);
    }

    /**
     * Test of editRetailProductPurchasePlan method, of class
     * RetailProductPurchasePlanModule.
     */
    @Test
    public void testEditRetailProductPurchasePlan() throws Exception {
        System.out.println("editRetailProductPurchasePlan");
        Long id = 0L;
        String field = "status";
        String temp = "cancelled";
        Object content = temp;
        String result = "";
        try {
            RetailProductPurchasePlanModule.editRetailProductPurchasePlan(id, field, content);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Retail Product Purchase Plan is not found!", result);
    }

    /**
     * Test of deleteRetailProductPurchasePlan method, of class
     * RetailProductPurchasePlanModule.
     */
    @Test
    public void testDeleteRetailProductPurchasePlan() throws Exception {
        System.out.println("deleteRetailProductPurchasePlan");
        Long id = 1L;
        boolean result = RetailProductPurchasePlanModule.deleteRetailProductPurchasePlan(id);
        assertTrue(result);
    }

    /**
     * Test of getRetailProductPurchasePlan method, of class
     * RetailProductPurchasePlanModule.
     */
    @Test
    public void testGetRetailProductPurchasePlan() throws Exception {
        System.out.println("getRetailProductPurchasePlan");
        Long id = 1L;
        String department = "H";
        List<IntegratedPlannedOrderEntity> result = RetailProductPurchasePlanModule.getRetailProductPurchasePlan(id, department);
        assertNotNull(result);
    }

    /**
     * Test of getFactoryRetailProductId method, of class
     * RetailProductPurchasePlanModule.
     */
    @Test
    public void testGetFactoryRetailProductId() throws Exception {
        System.out.println("getFactoryRetailProductId");
        Long integratedSalesForecastId = -1L;
        String result = "";
        try {
            RetailProductPurchasePlanModule.getFactoryRetailProductId(integratedSalesForecastId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Integrated Sales Forecast is not found!", result);
    }

    /**
     * Test of findIntegratedSalesForecast method, of class
     * RetailProductPurchasePlanModule.
     */
    @Test
    public void testFindIntegratedSalesForecast() throws Exception {
        System.out.println("findIntegratedSalesForecast");
        Long integratedSalesForecastId = 1L;
        boolean result = RetailProductPurchasePlanModule.findIntegratedSalesForecast(integratedSalesForecastId);
        assertTrue(result);
    }

    private RetailProductPurchasePlanModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (RetailProductPurchasePlanModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/RetailProductPurchasePlanModule!SessionBean.MRP.RetailProductPurchasePlanModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
