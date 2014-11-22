/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.UserEntity;
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
public class InternalUserAccountManagementModuleTest {

    private InternalUserAccountManagementModuleRemote InternalUserAccountManagementModule = lookupRemote();

    public InternalUserAccountManagementModuleTest() {
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
     * Test of AddStaff method, of class InternalUserAccountManagementModule.
     */
    @Test
    public void testAddStaff() throws Exception {
        System.out.println("AddStaff");
        String department = "S";
        Integer userLevel = 2;
        String lastName = "Hang";
        String midName = "";
        String firstName = "Sun";
        String position = "Factory Manager";
        Calendar birthday = Calendar.getInstance();
        birthday.set(1993, 2, 6, 0, 0, 0);
        String gender = "M";
        String title = "Mr";
        String address = "NUS Raffles Hall";
        String postalCode = "119278";
        String email = "sunhang36@gmail.com";
        long departmentId = 1L;
        InternalUserAccountManagementModule.AddStaff(department, userLevel, lastName, midName, firstName, position, birthday, gender, title, address, postalCode, email, departmentId);
    }

    /**
     * Test of DeleteStaff method, of class InternalUserAccountManagementModule.
     */
    @Test
    public void testDeleteStaff() throws Exception {
        System.out.println("DeleteStaff");
        String userId = "-1L";
        String result = "";
        try {
            InternalUserAccountManagementModule.DeleteStaff(userId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Staff is not found!", result);
    }

    /**
     * Test of ModifyStaff method, of class InternalUserAccountManagementModule.
     */
    @Test
    public void testModifyStaff() throws Exception {
        System.out.println("ModifyStaff");
        String userId = "F1111111";
        String department = "F";
        Integer userLevel = null;
        String lastName = "Hang";
        String midName = "";
        String firstName = "Sun";
        String position = "Factory Manager";
        Calendar birthday = Calendar.getInstance();
        birthday.set(1993, 2, 6, 0, 0, 0);
        String gender = "M";
        String title = "Mr";
        String address = "NUS Raffles Hall";
        String postalCode = "119278";
        String email = "sunhang36@gmail.com";
        long departmentId = 1L;
        String result = "";
        try {
            InternalUserAccountManagementModule.ModifyStaff(userId, department, userLevel, lastName, midName, firstName, position, birthday, gender, title, address, postalCode, email, departmentId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Staff is not found!", result);
    }

    /**
     * Test of ListUser method, of class InternalUserAccountManagementModule.
     */
    @Test
    public void testListUser() throws Exception {
        System.out.println("ListUser");
        List<UserEntity> result = InternalUserAccountManagementModule.ListUser();
        assertNotNull(result);
    }

    /**
     * Test of ListFactoryUser method, of class
     * InternalUserAccountManagementModule.
     */
    @Test
    public void testListFactoryUser() throws Exception {
        System.out.println("ListFactoryUser");
        Long id = -1L;
        String result = "";
        try {
            InternalUserAccountManagementModule.ListFactoryUser(id);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Factory is not found!", result);
    }

    /**
     * Test of ListStoreUser method, of class
     * InternalUserAccountManagementModule.
     */
    @Test
    public void testListStoreUser() throws Exception {
        System.out.println("ListStoreUser");
        Long id = -2L;
        String result = "";
        try {
            InternalUserAccountManagementModule.ListStoreUser(id);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Store is not found!", result);
    }

    /**
     * Test of getUser method, of class InternalUserAccountManagementModule.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        String userId = "0L";
        String result = "";
        try {
            InternalUserAccountManagementModule.getUser(userId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("User is not found!", result);
    }

    /**
     * Test of changePass method, of class InternalUserAccountManagementModule.
     */
    @Test
    public void testChangePass() throws Exception {
        System.out.println("changePass");
        String newPass = "123";
        String userId = "0L";
        String result = "";
        try {
            InternalUserAccountManagementModule.changePass(newPass, userId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("User is not found!", result);
    }

    private InternalUserAccountManagementModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (InternalUserAccountManagementModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/InternalUserAccountManagementModule!SessionBean.CommonInFrastructure.InternalUserAccountManagementModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
