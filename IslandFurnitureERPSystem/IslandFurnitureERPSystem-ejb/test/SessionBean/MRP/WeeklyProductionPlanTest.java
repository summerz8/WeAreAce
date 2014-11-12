/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.MRP.WeeklyProductionPlanEntity;
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
public class WeeklyProductionPlanTest {

    private WeeklyProductionPlanRemote WeeklyProductionPlan = lookupRemote();

    public WeeklyProductionPlanTest() {
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
     * Test of generateWeeklyProductionPlan method, of class
     * WeeklyProductionPlan.
     */
    @Test
    public void testGenerateWeeklyProductionPlan1() throws Exception {
        System.out.println("generateWeeklyProductionPlan");
        Long productionPlanId = -1L;
        String result = "";
        try {
            WeeklyProductionPlan.generateWeeklyProductionPlan(productionPlanId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Production Plan is not found!", result);
    }

    /**
     * Test of generateWeeklyProductionPlan method, of class
     * WeeklyProductionPlan.
     */
    @Test
    public void testGenerateWeeklyProductionPlan2() throws Exception {
        System.out.println("generateWeeklyProductionPlan");
        Long productionPlanId = 1L;
        List<WeeklyProductionPlanEntity> result = WeeklyProductionPlan.generateWeeklyProductionPlan(productionPlanId);
        assertNotNull(result);
    }

    /**
     * Test of editWeeklyProductionPlan method, of class WeeklyProductionPlan.
     */
    @Test
    public void testEditWeeklyProductionPlan() throws Exception {
        System.out.println("editWeeklyProductionPlan");
        Long id = 1L;
        Calendar month = Calendar.getInstance();
        month.set(Calendar.YEAR, 2015);
        month.set(Calendar.MONTH, 0);
        Integer week = 3;
        Integer workingDayInWeek = 5;
        Integer workingDayInMonth = 20;
        Double weeklyDemand = 30D;
        WeeklyProductionPlanEntity result = WeeklyProductionPlan.editWeeklyProductionPlan(id, month, week, workingDayInWeek, workingDayInMonth, weeklyDemand);
        assertNotNull(result);
    }

    /**
     * Test of getWeeklyProductionPlan method, of class WeeklyProductionPlan.
     */
    @Test
    public void testGetWeeklyProductionPlan() throws Exception {
        System.out.println("getWeeklyProductionPlan");
        Long productionPlanId = 100L;
        String result = "";
        try {
            WeeklyProductionPlan.getWeeklyProductionPlan(productionPlanId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Weekly Production Plan is not found!", result);
    }

    /**
     * Test of getProduct method, of class WeeklyProductionPlan.
     */
    @Test
    public void testGetProduct() throws Exception {
        System.out.println("getProduct");
        Long factoryProductId = 50L;
        String result = "";
        try {
            WeeklyProductionPlan.getProduct(factoryProductId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory Product is not found!", result);
    }

    private WeeklyProductionPlanRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (WeeklyProductionPlanRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/WeeklyProductionPlan!SessionBean.MRP.WeeklyProductionPlanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
