/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.MRP.SalesOperationPlanEntity;
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
public class SalesOperationPlanTest {

    private SalesOperationPlanRemote SalesOperationPlan = lookupRemote();

    public SalesOperationPlanTest() {
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
     * Test of GenerateSalesOperationPlan method, of class SalesOperationPlan.
     */
    @Test
    public void testGenerateSalesOperationPlan() throws Exception {
        System.out.println("GenerateSalesOperationPlan");
        Long factoryProductId = 5L;
        Calendar targetPeriod = Calendar.getInstance();
        targetPeriod.set(Calendar.YEAR, 2014);
        targetPeriod.set(Calendar.MONTH, 11);
        Long integratedSalesForecastId = 2L;
        Double plannedEndMonthInventory = 50D;
        Integer workingDays = 20;
        Double ProductionPlanQuantity = 30D;
        SalesOperationPlanEntity result = SalesOperationPlan.GenerateSalesOperationPlan(factoryProductId, targetPeriod, integratedSalesForecastId, plannedEndMonthInventory, workingDays, ProductionPlanQuantity);
        assertNotNull(result);
    }

    /**
     * Test of EditSalesOperationPlanEntity method, of class SalesOperationPlan.
     */
    @Test
    public void testEditSalesOperationPlanEntity() throws Exception {
        System.out.println("EditSalesOperationPlanEntity");
        Long Id = 1L;
        Double productionPlanQuantity = 20D;
        Calendar targetPeriod = Calendar.getInstance();
        targetPeriod.set(Calendar.YEAR, 2015);
        targetPeriod.set(Calendar.MONTH, 0);
        Double plannedEndMonthInventory = 100D;
        Integer workingDay = 15;
        SalesOperationPlanEntity result = SalesOperationPlan.EditSalesOperationPlanEntity(Id, productionPlanQuantity, targetPeriod, plannedEndMonthInventory, workingDay);
        assertNotNull(result);
    }

    /**
     * Test of ListSalesOperationPlan method, of class SalesOperationPlan.
     */
    @Test
    public void testListSalesOperationPlan() throws Exception {
        System.out.println("ListSalesOperationPlan");
        Long factoryProductId = 3L;
        Calendar startPeriod = Calendar.getInstance();
        startPeriod.set(Calendar.YEAR, 2014);
        startPeriod.set(Calendar.MONTH, 10);
        Calendar endPeriod = Calendar.getInstance();
        endPeriod.set(Calendar.YEAR, 2014);
        endPeriod.set(Calendar.MONTH, 11);
        List<SalesOperationPlanEntity> result = SalesOperationPlan.ListSalesOperationPlan(factoryProductId, startPeriod, endPeriod);
        assertNotNull(result);
    }

    /**
     * Test of createSalesOperationPlan method, of class SalesOperationPlan.
     */
    @Test
    public void testCreateSalesOperationPlan() throws Exception {
        System.out.println("createSalesOperationPlan");
        Long targetProductId = 4L;
        SalesOperationPlanEntity result = SalesOperationPlan.createSalesOperationPlan(targetProductId);
        assertNotNull(result);
    }

    /**
     * Test of confirmSalesOperationPlan method, of class SalesOperationPlan.
     */
    @Test
    public void testConfirmSalesOperationPlan() throws Exception {
        System.out.println("confirmSalesOperationPlan");
        Long salesOperationPlanId = -1L;
        String result = "";
        try {
            SalesOperationPlan.confirmSalesOperationPlan(salesOperationPlanId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Sales Operation Plan is not found!", result);
    }

    /**
     * Test of IsThereSalesOperation method, of class SalesOperationPlan.
     */
    @Test
    public void testIsThereSalesOperation() throws Exception {
        System.out.println("IsThereSalesOperation");
        Long factoryProductId = 50L;
        String result = "";
        try {
            SalesOperationPlan.IsThereSalesOperation(factoryProductId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory Product is not found!", result);
    }

    private SalesOperationPlanRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (SalesOperationPlanRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/SalesOperationPlan!SessionBean.MRP.SalesOperationPlanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
