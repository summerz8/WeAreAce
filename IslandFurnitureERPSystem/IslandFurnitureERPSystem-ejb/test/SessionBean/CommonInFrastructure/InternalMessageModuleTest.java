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
import javax.ejb.embeddable.EJBContainer;
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
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        InternalMessageModuleLocal instance = (InternalMessageModuleLocal)container.getContext().lookup("java:global/classes/InternalMessageModule");
        List<UserEntity> expResult = null;
        List<UserEntity> result = instance.getAllUsers();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class InternalMessageModule.
     */
    @Test
    public void testSendMessage() throws Exception {
        System.out.println("sendMessage");
        String senderId = "";
        String title = "";
        String content = "";
        ArrayList<String> receiverIds = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        InternalMessageModuleLocal instance = (InternalMessageModuleLocal)container.getContext().lookup("java:global/classes/InternalMessageModule");
        instance.sendMessage(senderId, title, content, receiverIds);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSendMessage method, of class InternalMessageModule.
     */
    @Test
    public void testDeleteSendMessage() throws Exception {
        System.out.println("deleteSendMessage");
        Long sendMessageId = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        InternalMessageModuleLocal instance = (InternalMessageModuleLocal)container.getContext().lookup("java:global/classes/InternalMessageModule");
        instance.deleteSendMessage(sendMessageId);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteReceiveMessage method, of class InternalMessageModule.
     */
    @Test
    public void testDeleteReceiveMessage() throws Exception {
        System.out.println("deleteReceiveMessage");
        Long receiveMessageId = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        InternalMessageModuleLocal instance = (InternalMessageModuleLocal)container.getContext().lookup("java:global/classes/InternalMessageModule");
        instance.deleteReceiveMessage(receiveMessageId);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readReceiveMessage method, of class InternalMessageModule.
     */
    @Test
    public void testReadReceiveMessage() throws Exception {
        System.out.println("readReceiveMessage");
        Long receiveMessageId = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        InternalMessageModuleLocal instance = (InternalMessageModuleLocal)container.getContext().lookup("java:global/classes/InternalMessageModule");
        instance.readReceiveMessage(receiveMessageId);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replyMessage method, of class InternalMessageModule.
     */
    @Test
    public void testReplyMessage() throws Exception {
        System.out.println("replyMessage");
        Long receiveMessageId = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        InternalMessageModuleLocal instance = (InternalMessageModuleLocal)container.getContext().lookup("java:global/classes/InternalMessageModule");
        instance.replyMessage(receiveMessageId);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewSendMessage method, of class InternalMessageModule.
     */
    @Test
    public void testViewSendMessage() throws Exception {
        System.out.println("viewSendMessage");
        String senderId = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        InternalMessageModuleLocal instance = (InternalMessageModuleLocal)container.getContext().lookup("java:global/classes/InternalMessageModule");
        Collection<InternalMessageEntity> expResult = null;
        Collection<InternalMessageEntity> result = instance.viewSendMessage(senderId);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewReceiveMessage method, of class InternalMessageModule.
     */
    @Test
    public void testViewReceiveMessage() throws Exception {
        System.out.println("viewReceiveMessage");
        String receiverId = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        InternalMessageModuleLocal instance = (InternalMessageModuleLocal)container.getContext().lookup("java:global/classes/InternalMessageModule");
        Collection<InternalMessageReceive> expResult = null;
        Collection<InternalMessageReceive> result = instance.viewReceiveMessage(receiverId);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
