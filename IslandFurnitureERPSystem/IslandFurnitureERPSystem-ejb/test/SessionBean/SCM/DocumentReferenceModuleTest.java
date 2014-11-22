/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.SCM;

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
public class DocumentReferenceModuleTest {

    private DocumentReferenceModuleRemote DocumentReferenceModule = lookupRemote();

    public DocumentReferenceModuleTest() {
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
     * Test of viewAllProductionPlans method, of class DocumentReferenceModule.
     */
    @Test
    public void testViewAllProductionPlans() throws Exception {
        System.out.println("viewAllProductionPlans");
        List result = DocumentReferenceModule.viewAllProductionPlans();
        assertNotNull(result);
    }

    private DocumentReferenceModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (DocumentReferenceModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/DocumentReferenceModule!SessionBean.SCM.DocumentReferenceModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
