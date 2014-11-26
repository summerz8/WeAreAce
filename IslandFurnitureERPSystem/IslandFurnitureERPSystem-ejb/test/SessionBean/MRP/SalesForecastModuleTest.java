/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Store.StoreEntity;
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
public class SalesForecastModuleTest {

    private SalesForecastModuleRemote SalesForecastModule = lookupRemote();

    public SalesForecastModuleTest() {
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
     * Test of GetSalesForecast method, of class SalesForecastModule.
     */
    @Test
    public void testGetSalesForecast() throws Exception {
        System.out.println("GetSalesForecast");
        Long salesForecastId = -3L;
        String result = "";
        try {
            SalesForecastModule.GetSalesForecast(salesForecastId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Sales Forecast is not found!", result);
    }

    /**
     * Test of productListNeededTobeIntegrated method, of class
     * SalesForecastModule.
     */
    @Test
    public void testProductListNeededTobeIntegrated() throws Exception {
        System.out.println("productListNeededTobeIntegrated");
        Long FactoryId = -1L;
        String result = "";
        try {
        SalesForecastModule.productListNeededTobeIntegrated(FactoryId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory is not found!", result);
    }

    /**
     * Test of GenerateIntegratedSalesForecast method, of class
     * SalesForecastModule.
     */
    @Test
    public void testGenerateIntegratedSalesForecast() throws Exception {
        System.out.println("GenerateIntegratedSalesForecast");
        String type = "factoryProduct";
        Long factoryProductId = 3L;
        Calendar period = null;
        SalesForecastModule.GenerateIntegratedSalesForecast(type, factoryProductId, period);
    }

    /**
     * Test of ListStore method, of class SalesForecastModule.
     */
    @Test
    public void testListStore() throws Exception {
        System.out.println("ListStore");
        Long factoryId = 2L;
        List<StoreEntity> result = SalesForecastModule.ListStore(factoryId);
        assertNotNull(result);
    }

    private SalesForecastModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (SalesForecastModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/SalesForecastModule!SessionBean.MRP.SalesForecastModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
