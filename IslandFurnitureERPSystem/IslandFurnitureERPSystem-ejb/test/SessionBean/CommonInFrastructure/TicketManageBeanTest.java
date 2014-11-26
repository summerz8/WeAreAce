/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.TicketEntity;
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
public class TicketManageBeanTest {

    private TicketManageBeanRemote TicketManageBean = lookupRemote();

    public TicketManageBeanTest() {
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
     * Test of raiseATicket method, of class TicketManageBean.
     */
    @Test
    public void testRaiseATicket() throws Exception {
        System.out.println("raiseATicket");
        String UserId = "0L";
        String title = "hello";
        String content = "test";
        String result = "";
        try {
            TicketManageBean.raiseATicket(UserId, title, content);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("System user is not found!", result);
    }

    /**
     * Test of readATicket method, of class TicketManageBean.
     */
    @Test
    public void testReadATicket() throws Exception {
        System.out.println("readATicket");
        Long ticketId = 0L;
        String result = "";
        try {
            TicketManageBean.readATicket(ticketId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Ticket is not found!", result);

    }

    /**
     * Test of processATicket method, of class TicketManageBean.
     */
    @Test
    public void testProcessATicket() throws Exception {
        System.out.println("processATicket");
        Long ticketId = 0L;
        String result = "";
        try {
            TicketManageBean.processATicket(ticketId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Ticket is not found!", result);
    }

    /**
     * Test of closeATicket method, of class TicketManageBean.
     */
    @Test
    public void testCloseATicket() throws Exception {
        System.out.println("closeATicket");
        Long ticketId = 0L;
        String result = "";
        try {
            TicketManageBean.closeATicket(ticketId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Ticket is not found!", result);
    }

    /**
     * Test of listSystemTicket method, of class TicketManageBean.
     */
    @Test
    public void testListSystemTicket() throws Exception {
        System.out.println("listSystemTicket");
        String userId = "F1000000";
        String result = "";
        try {
            TicketManageBean.listSystemTicket(userId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("System user is not found!", result);
    }

    /**
     * Test of listAllTickets method, of class TicketManageBean.
     */
    @Test
    public void testListAllTickets() throws Exception {
        System.out.println("listAllTickets");
        Integer status = 0;
        List<TicketEntity> result = TicketManageBean.listAllTickets(status);
        assertNotNull(result);
    }

    private TicketManageBeanRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (TicketManageBeanRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/TicketManageBean!SessionBean.CommonInFrastructure.TicketManageBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
