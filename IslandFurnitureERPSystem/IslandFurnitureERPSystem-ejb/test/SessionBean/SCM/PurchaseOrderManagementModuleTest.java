/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

import Entity.Factory.FactoryRawMaterialEntity;
import Entity.Factory.SCM.PurchaseOrderEntity;
import java.util.Calendar;
import java.util.Collection;
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
public class PurchaseOrderManagementModuleTest {

    private PurchaseOrderManagementModuleRemote PurchaseOrderManagementModule = lookupRemote();

    public PurchaseOrderManagementModuleTest() {
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
     * Test of getPO method, of class PurchaseOrderManagementModule.
     */
    @Test
    public void testGetPO() throws Exception {
        System.out.println("getPO");
        Long poId = -2L;
        String result = "";
        try {
            PurchaseOrderManagementModule.getPO(poId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Purchase Order is not found!", result);
    }

    /**
     * Test of viewRawMaterialWithSelectType method, of class
     * PurchaseOrderManagementModule.
     */
    @Test
    public void testViewRawMaterialWithSelectType1() throws Exception {
        System.out.println("viewRawMaterialWithSelectType");
        Long factoryId = 1L;
        Collection<FactoryRawMaterialEntity> result = PurchaseOrderManagementModule.viewRawMaterialWithSelectType(factoryId);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of viewRawMaterialWithSelectType method, of class
     * PurchaseOrderManagementModule.
     */
    @Test
    public void testViewRawMaterialWithSelectType2() throws Exception {
        System.out.println("viewRawMaterialWithSelectType");
        Long factoryId = 1L;
        Collection<FactoryRawMaterialEntity> result = PurchaseOrderManagementModule.viewRawMaterialWithSelectType(factoryId);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of createPurchaseOrder method, of class
     * PurchaseOrderManagementModule.
     */
    @Test
    public void testCreatePurchaseOrder() throws Exception {
        System.out.println("createPurchaseOrder");
        Long factoryId = 1L;
        Long contractId = 2L;
        Double purchaseAmount = 4D;
        Long storeId = 2L;
        String destination = "store";
        Calendar deliveryDate = Calendar.getInstance();
        deliveryDate.set(2015, 0, 13);
        Boolean isManual = false;
        Boolean isToStore = true;
        PurchaseOrderEntity result = PurchaseOrderManagementModule.createPurchaseOrder(factoryId, contractId, purchaseAmount, storeId, destination, deliveryDate, isManual, isToStore);
        assertNotNull(result);
    }

    /**
     * Test of confirmPurchaseOrder method, of class
     * PurchaseOrderManagementModule.
     */
    @Test
    public void testConfirmPurchaseOrder() throws Exception {
        System.out.println("confirmPurchaseOrder");
        String userId = "F1000001";
        Long purchaseOrderId = 1L;
        String result = PurchaseOrderManagementModule.confirmPurchaseOrder(userId, purchaseOrderId);
        assertEquals("Purchase Order Confirmed!", result);
    }

    private PurchaseOrderManagementModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (PurchaseOrderManagementModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/PurchaseOrderManagementModule!SessionBean.SCM.PurchaseOrderManagementModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
