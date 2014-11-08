/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.InternalMessageEntity;
import Entity.CommonInfrastructure.InternalMessageReceive;
import Entity.CommonInfrastructure.UserEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author zhengyuan
 */

/*
 Session Bean Functionality:
 1. return all users for selecting receiver
 2. sendMessage (One to One, One to Many)
 3. readMessage 
 4. delete sent Mesage
 5. delete received Message
 6. reply a message
 7. forward a messag 
 8. a. Dispaly receive Message List
    b. Display receive Message List by Sender -- enable by primeface
 9. a. Display sent Message List
 9. b. Display send Message List by Receiver -- enable by primeface

 */
@Stateless
public class InternalMessageModule implements InternalMessageModuleLocal,InternalMessageModuleRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    
//    =====================================GET USER ==========================================
    @Override
    public List<UserEntity> getAllUsers() {
        ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
        Query q = em.createQuery("Select t from UserEntity t");
        for (Object o : q.getResultList()) {
            UserEntity user = (UserEntity) o;
            userList.add(user);

        }
        return userList;
    }
//    =====================================SEND MESSAGE ==========================================
    @Override
    public void sendMessage(String senderId, String title, String content, ArrayList<String> receiverIds) throws Exception {

        UserEntity sender = em.find(UserEntity.class, senderId);
        System.err.println("sessionBean internal message module sendMessage(): getUserId: " + sender.getUserId());
        if (sender == null) {
            throw new Exception("Sender is not found");
        } else {

            //initialise the new message
            //get the list of receive
            //set the senderName
            String firstName = sender.getFirstName();
            String lastName = sender.getLastName();
            String senderName = firstName + " " + lastName;
            ArrayList<String> receiverIdArray = new ArrayList<String>();

            //set the time
            Calendar sendTime = Calendar.getInstance();
//            sendTime.getTime();
//
           SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//            String time = sdf.format(sendTime.getTime()).toString();
            System.out.println("sessionbean internal message module sendMessage(): time : " + sendTime.getTime());
            System.out.println("sessionbean internal message module sendMessage(): title : " + title);
            System.out.println("sessionbean internal message module sendMessage(): content : " + content);
            
            //instanlise a sendmessage entity
            InternalMessageEntity sendNewMessage = new InternalMessageEntity(senderName, title, content, sendTime);
            em.persist(sendNewMessage);
            em.flush();

            sender.getSendMessage().add(sendNewMessage);
            em.flush();
            
            sendNewMessage.setSender(sender);
            em.flush();
            
            System.err.println("sessionbean internal message module setMessageSender(): here message has been set in Internal Message Entity under a sender");
            Integer i;
            Collection<InternalMessageReceive> receiverMessageList = new ArrayList<InternalMessageReceive>();
            for (i = 0; i < receiverIds.size(); i++) {
                UserEntity receiver = em.find(UserEntity.class, receiverIds.get(i));
                if (receiver == null) {
                    throw new Exception("Receiver is not found!");
                } else {
                    
                    String displayReceiverIdAndName = "(" + receiver.getUserId() + ")" + receiver.getFirstName() + " " + receiver.getLastName();
                    receiverIdArray.add(displayReceiverIdAndName);
                    InternalMessageReceive receiveMessage = new InternalMessageReceive(sendNewMessage);
                    em.persist(receiveMessage);
                    em.flush();
                    System.out.println("sessionbean internal message module sendMessage(): recieve message time : " + sdf.format(receiveMessage.getCalendarTime().getTime()).toString());
        
                    
                    receiver.getReceiveMessage().add(receiveMessage);
                    em.flush();
                    
                    receiveMessage.setReceiver(receiver);
                    em.flush();
                    
                    System.err.println("sessionbean internal message module sendMessage(): receiverId: " + receiveMessage.getReceiver().getUserId());

                    receiverMessageList.add(receiveMessage);
                    em.flush();

                }

            }
            sendNewMessage.setReceiverIds(receiverIdArray);
            System.out.println("sessionbean internal message module sendMessage(): time : " + sdf.format(sendNewMessage.getCalendarTime().getTime()).toString());
            sendNewMessage.setReceiveMessages(receiverMessageList);
            em.flush();

        }

    }

    //    =====================================DELETE SEND MESSAGE =====================================
    @Override
    public void deleteSendMessage(Long sendMessageId) throws Exception {

        InternalMessageEntity sendMessage = em.find(InternalMessageEntity.class, sendMessageId);
        if (sendMessage == null) {
            throw new Exception("Sent Message is not found!");
        } else {
             System.out.println("session bean deleteSentMessage: update state!");
            sendMessage.setIsDeleted(true);
            System.out.println("session bean deleteSentMessage: update update!");
        }
        em.flush();

    }

    //    =====================================DELETE RECEIVE MESSAGE ==========================================
    @Override
    public void deleteReceiveMessage(Long receiveMessageId) throws Exception {
        InternalMessageReceive receiveMessage = em.find(InternalMessageReceive.class, receiveMessageId);
        if (receiveMessage == null) {
            throw new Exception("Received Message is not found!");
        } else {
           
            receiveMessage.setDeleted(true);
        }
        
        em.flush();

    }
//    ===================================== READ RECEIVED MESSAGE ==========================================
    @Override
    public void readReceiveMessage(Long receiveMessageId) throws Exception {
        InternalMessageReceive receiveMessage = em.find(InternalMessageReceive.class, receiveMessageId);
        if (receiveMessage == null) {
            throw new Exception("Received Message is not found!");
        } else {
            System.out.println("session bean readReceiveMessage: update state!");
            receiveMessage.setOpened(true);
            System.out.println("session bean readReceiveMessage: update update!");
        }
        em.flush();

    }

    //    =====================================REPLY A MESSAGE ==========================================
    @Override
    public void replyMessage(Long receiveMessageId) throws Exception {
        InternalMessageReceive receiveMessage = em.find(InternalMessageReceive.class, receiveMessageId);
        if (receiveMessage == null) {
            throw new Exception("Received Message is not found!");
        } else {
            System.out.println("session bean readReceiveMessage: update state!");
            receiveMessage.setReplied(true);
            System.out.println("session bean readReceiveMessage: update update!");
        }
        em.flush();

    }
    
 //    ===================================== VIEW SEND MESSAGE  ==========================================   
    @Override
    public Collection<InternalMessageEntity> viewSendMessage(String senderId) {
        Collection<InternalMessageEntity> sendMessageList = new ArrayList<InternalMessageEntity>();
        UserEntity sender = em.find(UserEntity.class, senderId);
        
       
         for( InternalMessageEntity notDeletedMessage :sender.getSendMessage()){
            if(!notDeletedMessage.getIsDeleted()){
                sendMessageList.add(notDeletedMessage);
            }
        }
        
        return sendMessageList;
    }

 //    ===================================== VIEW RECEIVE MESSAGE ==========================================   
    @Override
    public Collection<InternalMessageReceive> viewReceiveMessage(String receiverId)  {
        Collection<InternalMessageReceive> receiveMessageList = new ArrayList<InternalMessageReceive>();
        UserEntity receiver = em.find(UserEntity.class, receiverId);
        System.out.println("InternalMessageModule: ---> receiverId: " + receiver.getUserId() );

        for( InternalMessageReceive notDeletedMessage :receiver.getReceiveMessage()){
            if(!notDeletedMessage.isDeleted()){
                receiveMessageList.add(notDeletedMessage);
            }
        }
        System.out.println("InternalMessageModule: ---> messageSize: " + receiveMessageList.size());
        return receiveMessageList;
    }
    
    
    
    
    
    
    

//    @Override
//    public Collection<InternalMessageReceive> viewReceiveMessageBySender(String receiverId, String senderId) throws Exception {
//        Collection<InternalMessageReceive> receiveMessageList = null;
//        Collection<InternalMessageReceive> receiveMessageListBySender = null;
//        receiveMessageList = viewReceiveMessage(receiverId);
//        for (Object o : receiveMessageList) {
//            InternalMessageReceive receiveMessage = (InternalMessageReceive) o;
//            if (receiveMessage.getSendMessage().getSender().getUserId() == senderId) {
//                receiveMessageListBySender.add(receiveMessage);
//            }
//        }
//        if (receiveMessageListBySender.size() == 0) {
//            System.out.println("InternalMessageModule: ---> Sender info not found");
//        } else {
//            System.out.println("InternalMessageModule: ---> display a list of message received from sender ");
//        }
//        return receiveMessageListBySender;
//    }
//
//    @Override
//    public Collection<InternalMessageEntity> viewSendMessageByReceiver(String senderId, String receiverId) throws Exception {
//        Collection<InternalMessageEntity> sendMessageList = null;
//        Collection<InternalMessageEntity> sendMessageListByReceiver = null;
//        sendMessageList = viewSendMessage(senderId);
//        for (Object o : sendMessageList) {
//            InternalMessageEntity sendMessage = (InternalMessageEntity) o;
//            for (Object e : sendMessage.getReceiveMessages()) {
//                InternalMessageReceive receiveMessage = (InternalMessageReceive) e;
//                if (receiveMessage.getReceiver().getUserId() == receiverId) {
//                    sendMessageListByReceiver.add(sendMessage);
//                    break;
//                }
//
//            }
//        }
//        if (sendMessageListByReceiver.size() == 0) {
//            System.out.println("InternalMessageModule: ---> Receiver info not found");
//        } else {
//            System.out.println("InternalMessageModule: ---> display a list of message received from sender");
//        }
//        return sendMessageListByReceiver;
//    }

}
