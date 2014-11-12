/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.InternalMessageEntity;
import Entity.CommonInfrastructure.InternalMessageReceive;
import Entity.CommonInfrastructure.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
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
public class InternalMessageModuleTest {

    private InternalMessageModuleRemote InternalMessageModule = lookupRemote();

    public InternalMessageModuleTest() {
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
     * Test of getAllUsers method, of class InternalMessageModule.
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("getAllUsers");
        List<UserEntity> result = InternalMessageModule.getAllUsers();
        assertNotNull(result);
    }

    /**
     * Test of sendMessage method, of class InternalMessageModule.
     */
    @Test
    public void testSendMessage() throws Exception {
        System.out.println("sendMessage");
        String senderId = "F1111111";
        String title = "Miss.";
        String content = "12345678";
        ArrayList<String> receiverIds = new ArrayList<>();
        receiverIds.add("S1000001");
        receiverIds.add("F1000002");
        String result = "";
        try {
            InternalMessageModule.sendMessage(senderId, title, content, receiverIds);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Sender is not found!", result);

    }

    /**
     * Test of deleteSendMessage method, of class InternalMessageModule.
     */
    @Test
    public void testDeleteSendMessage() throws Exception {
        System.out.println("deleteSendMessage");
        Long sendMessageId = -1L;
        String result = "";
        try {
            InternalMessageModule.deleteSendMessage(sendMessageId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Sent Message is not found!", result);
    }

    /**
     * Test of deleteReceiveMessage method, of class InternalMessageModule.
     */
    @Test
    public void testDeleteReceiveMessage() throws Exception {
        System.out.println("deleteReceiveMessage");
        Long receiveMessageId = -11L;
        String result = "";
        try {
            InternalMessageModule.deleteReceiveMessage(receiveMessageId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Received Message is not found!", result);
    }

    /**
     * Test of readReceiveMessage method, of class InternalMessageModule.
     */
    @Test
    public void testReadReceiveMessage() throws Exception {
        System.out.println("readReceiveMessage");
        Long receiveMessageId = -1L;
        String result = "";
        try {
            InternalMessageModule.readReceiveMessage(receiveMessageId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Received Message is not found!", result);
    }

    /**
     * Test of replyMessage method, of class InternalMessageModule.
     */
    @Test
    public void testReplyMessage() throws Exception {
        System.out.println("replyMessage");
        Long receiveMessageId = -1L;
        String result = "";
        try {
            InternalMessageModule.replyMessage(receiveMessageId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        assertEquals("Received Message is not found!", result);
    }

    /**
     * Test of viewSendMessage method, of class InternalMessageModule.
     */
    @Test
    public void testViewSendMessage1() throws Exception {
        System.out.println("viewSendMessage");
        String senderId = "F1111111";
        String result = "";
        try {
            InternalMessageModule.viewSendMessage(senderId);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
//        ##################################################################
        String expResult = "User Id is not found!";
        assertEquals(expResult, result);
    }

    /**
     * Test of viewSendMessage method, of class InternalMessageModule.
     */
    @Test
    public void testViewSendMessage2() throws Exception {
        System.out.println("viewSendMessage");
        String senderId = "F1000001";
        Collection<InternalMessageEntity> result = InternalMessageModule.viewSendMessage(senderId);
        assertNotNull(result);
    }

    /**
     * Test of viewReceiveMessage method, of class InternalMessageModule.
     */
    @Test
    public void testViewReceiveMessage1() throws Exception {
        System.out.println("viewReceiveMessage");
        String receiverId = "S1111111";
//      ##############################################################################################
        Collection<InternalMessageReceive> expResult = null;
        Collection<InternalMessageReceive> result = InternalMessageModule.viewReceiveMessage(receiverId);
        assertEquals(expResult, result);
    }

    /**
     * Test of viewReceiveMessage method, of class InternalMessageModule.
     */
    @Test
    public void testViewReceiveMessage2() throws Exception {
        System.out.println("viewReceiveMessage");
        String receiverId = "S1000001";
        Collection<InternalMessageReceive> result = InternalMessageModule.viewReceiveMessage(receiverId);
        assertNotNull(result);
    }

    private InternalMessageModuleRemote lookupRemote() {
        try {
            Context c = new InitialContext();
            return (InternalMessageModuleRemote) c.lookup("java:global/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/InternalMessageModule!SessionBean.CommonInFrastructure.InternalMessageModuleRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
