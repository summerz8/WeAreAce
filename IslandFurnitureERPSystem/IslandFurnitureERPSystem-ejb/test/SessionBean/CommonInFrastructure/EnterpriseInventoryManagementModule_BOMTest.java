/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.Factory.BOMEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hangsun
 */
public class EnterpriseInventoryManagementModule_BOMTest {

    private EnterpriseInventoryManagementModule_BOMRemote BOM = lookupRemote();

    public EnterpriseInventoryManagementModule_BOMTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    /**
     * Test of addANewBOM method, of class
     * EnterpriseInventoryManagementModule_BOM.
     */
    @Test
    public void testAddANewBOM() throws Exception {
        System.out.println("addANewBOM");
        Long productId = 1L;
        Long rawMaterialId = 4L;
        Double quantity = 5.0D;
        Integer expResult = 0;
        Integer result = BOM.addANewBOM(productId, rawMaterialId, quantity);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateANewBom method, of class
     * EnterpriseInventoryManagementModule_BOM.
     */
    @Test
    public void testUpdateANewBom() throws Exception {
        System.out.println("updateANewBom");
        Long bomId = 0L;
        Double quantity = 5.0D;
        String result = new String();
        try{
        BOM.updateANewBom(bomId, quantity);
        }catch(Exception ex){
            result = ex.getMessage();
        }
        
        assertEquals("Product is not found", result);
         
    }

    /**
     * Test of deleteANewBom method, of class
     * EnterpriseInventoryManagementModule_BOM.
     */
    @Test
    public void testDeleteANewBom() throws Exception {
        System.out.println("deleteANewBom");
        Long bomId =  -2L;
        String result = new String();
        try{
        BOM.deleteANewBom(bomId);
        }catch(Exception ex){
            result = ex.getMessage();
        }      
        assertEquals("Product is not found", result);
    }

    /**
     * Test of getAllBOM method, of class
     * EnterpriseInventoryManagementModule_BOM.
     */
    @Test
    public void testGetAllBOM() throws Exception {
        System.out.println("getAllBOM");
        Long productId = 1L;
        List<BOMEntity> result = BOM.getAllBOM(productId);
        assertFalse(result.isEmpty());
    }

    private EnterpriseInventoryManagementModule_BOMRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (EnterpriseInventoryManagementModule_BOMRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/EnterpriseInventoryManagementModule_BOM!SessionBean.CommonInFrastructure.EnterpriseInventoryManagementModule_BOMRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}

