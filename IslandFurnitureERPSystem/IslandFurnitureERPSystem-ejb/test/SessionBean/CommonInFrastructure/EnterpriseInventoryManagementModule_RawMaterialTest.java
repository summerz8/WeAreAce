/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.Factory.RawMaterialEntity;
import java.util.ArrayList;
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
public class EnterpriseInventoryManagementModule_RawMaterialTest {
    
    private EnterpriseInventoryManagementModule_RawMaterialRemote rawMaterial = lookupRemote();
    
    public EnterpriseInventoryManagementModule_RawMaterialTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of listRawMaterial method, of class EnterpriseInventoryManagementModule_RawMaterialRemote.
     */
    @Test
    public void testListRawMaterial() {
        System.out.println("listRawMaterial");
        ArrayList<RawMaterialEntity> result = rawMaterial.listRawMaterial();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of deleteRawMaterial method, of class EnterpriseInventoryManagementModule_RawMaterialRemote.
     */
    @Test
    public void testDeleteRawMaterial() throws Exception {
        System.out.println("deleteRawMaterial");
        Long rawMaterialId = -5L;
        String result = new String();
        try{
            rawMaterial.deleteRawMaterial(rawMaterialId);
        }catch(Exception ex){
          result = ex.getMessage();
        }
        assertEquals("Raw Material is not found.",result);
    }

    /**
     * Test of addRawMaterial method, of class EnterpriseInventoryManagementModule_RawMaterialRemote.
     */
    @Test
    public void testAddRawMaterial() {
        System.out.println("addRawMaterial");
        String name = "";
        String description = "test";
        String unit = "unit";    
        String result = new String();        
         try{
            rawMaterial.addRawMaterial(name, description, unit);  
        }catch(Exception ex){
          result = ex.getMessage();
        }
        assertEquals("Name cannot be empty!",result);
    }

    /**
     * Test of modifyRawMaterial method, of class EnterpriseInventoryManagementModule_RawMaterialRemote.
     */
    @Test
    public void testModifyRawMaterial() throws Exception {
        System.out.println("modifyRawMaterial");
        Long rawMaterialId = -6L;
        String name = "wood";
        String description = "test";
        String unit = "peice";
        String result = new String();        
         try{
            rawMaterial.modifyRawMaterial(rawMaterialId, name, description, unit);
        }catch(Exception ex){
          result = ex.getMessage();
        }
        assertEquals("Raw Material is not found.",result);
       
    }
    
    private EnterpriseInventoryManagementModule_RawMaterialRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (EnterpriseInventoryManagementModule_RawMaterialRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/EnterpriseInventoryManagementModule_RawMaterial!SessionBean.CommonInFrastructure.EnterpriseInventoryManagementModule_RawMaterialRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
