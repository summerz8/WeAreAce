/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.Factory.ProductEntity;
import Entity.Factory.RetailProductEntity;
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
public class RetailProduct_ProductManagementModuleTest {

    private RetailProduct_ProductManagementModuleRemote RetailProduct_ProductManagementModule = lookupRemote();

    public RetailProduct_ProductManagementModuleTest() {
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
     * Test of AddProduct method, of class
     * RetailProduct_ProductManagementModule.
     */
    @Test
    public void testAddProduct() throws Exception {
        System.out.println("AddProduct");
        String name = "bookcase";
        String description = "this is a bookcase";
        Double price = 500D;
        Double memberPrice = 300D;
        String unit = "set";
        RetailProduct_ProductManagementModule.AddProduct(name, description, price, memberPrice, unit);
    }

    /**
     * Test of DeleteProduct method, of class
     * RetailProduct_ProductManagementModule.
     */
    @Test
    public void testDeleteProduct() throws Exception {
        System.out.println("DeleteProduct");
        Long productId = -1L;
        String result = "";
        try {
            RetailProduct_ProductManagementModule.DeleteProduct(productId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Product is not found!", result);
    }

    /**
     * Test of ModifyProduct method, of class
     * RetailProduct_ProductManagementModule.
     */
    @Test
    public void testModifyProduct() throws Exception {
        System.out.println("ModifyProduct");
        Long productId = 0L;
        String name = "bookcase";
        String description = "this is a bookcase";
        Double price = 500D;
        Double memberPrice = 300D;
        String unit = "set";
        String result = "";
        try {
            RetailProduct_ProductManagementModule.ModifyProduct(productId, name, description, price, memberPrice, unit);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Product is not found!", result);
    }

    /**
     * Test of ListProduct method, of class
     * RetailProduct_ProductManagementModule.
     */
    @Test
    public void testListProduct() throws Exception {
        System.out.println("ListProduct");
        List<ProductEntity> result = RetailProduct_ProductManagementModule.ListProduct();
        assertNotNull(result);
    }

    /**
     * Test of AddRetailProduct method, of class
     * RetailProduct_ProductManagementModule.
     */
    @Test
    public void testAddRetailProduct() throws Exception {
        System.out.println("AddRetailProduct");
        String name = "pencilcase";
        String description = "this is a pencilcase";
        Double price = 10D;
        String unit = "one";
        RetailProduct_ProductManagementModule.AddRetailProduct(name, description, price, unit);
    }

    /**
     * Test of DeleteRetailProduct method, of class
     * RetailProduct_ProductManagementModule.
     */
    @Test
    public void testDeleteRetailProduct() throws Exception {
        System.out.println("DeleteRetailProduct");
        Long retailProductId = 0L;
        String result = "";
        try {
            RetailProduct_ProductManagementModule.DeleteRetailProduct(retailProductId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Retail Product is not found!", result);
    }

    /**
     * Test of ModifyRetailProduct method, of class
     * RetailProduct_ProductManagementModule.
     */
    @Test
    public void testModifyRetailProduct() throws Exception {
        System.out.println("ModifyRetailProduct");
        Long retailProductId = -5L;
        String name = "pencilcase";
        String description = "this is a pencilcase";
        Double price = 10D;
        String unit = "one";
        String result = "";
        try {
        RetailProduct_ProductManagementModule.ModifyRetailProduct(retailProductId, name, unit, price, description);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Retail Product is not found!", result);
        
    }

    /**
     * Test of ListRetailProduct method, of class
     * RetailProduct_ProductManagementModule.
     */
    @Test
    public void testListRetailProduct() throws Exception {
        System.out.println("ListRetailProduct");
        List<RetailProductEntity> result = RetailProduct_ProductManagementModule.ListRetailProduct();
        assertNotNull(result);
    }

    private RetailProduct_ProductManagementModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (RetailProduct_ProductManagementModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/RetailProduct_ProductManagementModule!SessionBean.CommonInFrastructure.RetailProduct_ProductManagementModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
