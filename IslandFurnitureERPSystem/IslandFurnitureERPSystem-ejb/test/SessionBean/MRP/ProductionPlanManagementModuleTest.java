/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.MRP;

import Entity.Factory.MRP.ProductionPlanEntity;
import java.util.Calendar;
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
public class ProductionPlanManagementModuleTest {

    private ProductionPlanManagementModuleRemote ProductionPlanManagementModule = lookupRemote();

    public ProductionPlanManagementModuleTest() {
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
     * Test of generateProductionPlan method, of class
     * ProductionPlanManagementModule.
     */
    @Test
    public void testGenerateProductionPlan() throws Exception {
        System.out.println("generateProductionPlan");
        String status = "unconfirmed";
        Calendar generateDate = Calendar.getInstance();
        generateDate.set(2014, 9, 24, 10, 30, 0);
        Calendar targetPeriod = Calendar.getInstance();
        targetPeriod.set(Calendar.YEAR, 2014);
        targetPeriod.set(Calendar.MONTH, 11);
        Double output = 40D;
        Long productId = 1L;
        String remark = "remark";
        boolean result = ProductionPlanManagementModule.generateProductionPlan(status, generateDate, targetPeriod, output, productId, remark);
        assertTrue(result);
    }

    /**
     * Test of editProductionPlan method, of class
     * ProductionPlanManagementModule.
     */
    @Test
    public void testEditProductionPlan() throws Exception {
        System.out.println("editProductionPlan");
        Long productionPlanId = 111L;
        String field = "status";
        String temp = "cancelled";
        Object content = temp;
        String result = "";
        try {
            ProductionPlanManagementModule.editProductionPlan(productionPlanId, field, content);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Production Plan is not found!", result);
    }

    /**
     * Test of deleteProductionPlan method, of class
     * ProductionPlanManagementModule.
     */
    @Test
    public void testDeleteProductionPlan() throws Exception {
        System.out.println("deleteProductionPlan");
        Long productionPlanId = 1L;
        boolean result = ProductionPlanManagementModule.deleteProductionPlan(productionPlanId);
        assertTrue(result);
    }

    /**
     * Test of getProductionPlanUnconfirmed method, of class
     * ProductionPlanManagementModule.
     */
    @Test
    public void testGetProductionPlanUnconfirmed() throws Exception {
        System.out.println("getProductionPlanUnconfirmed");
        Long id = 10L;
        String department = "F";
        String result = "";
        try {
            ProductionPlanManagementModule.getProductionPlanUnconfirmed(id, department);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Department is not found!", result);
    }

    /**
     * Test of searchProductionPlan method, of class
     * ProductionPlanManagementModule.
     */
    @Test
    public void testSearchProductionPlan1() throws Exception {
        System.out.println("searchProductionPlan");
        Long id = 1L;
        ProductionPlanEntity result = ProductionPlanManagementModule.searchProductionPlan(id);
        assertNotNull(result);
    }

    /**
     * Test of searchProductionPlan method, of class
     * ProductionPlanManagementModule.
     */
    @Test
    public void testSearchProductionPlan2() throws Exception {
        System.out.println("searchProductionPlan");
        Long id = -1L;
        String result = "";
        try {
            ProductionPlanManagementModule.searchProductionPlan(id);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Production Plan is not found!", result);

    }

    private ProductionPlanManagementModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (ProductionPlanManagementModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/ProductionPlanManagementModule!SessionBean.MRP.ProductionPlanManagementModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
