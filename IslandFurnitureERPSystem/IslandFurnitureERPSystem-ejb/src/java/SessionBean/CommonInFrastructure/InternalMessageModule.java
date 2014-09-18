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
   sendMessage (One to One, One to Many)
   readMessage 
   delete sent Mesage
   delete received Message
   Dispaly receive Message List
   Display receive Message List by Sender
   Display sent Message List
   Display send Message List by Receiver

*/
@Stateless
public class InternalMessageModule implements InternalMessageModuleLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public ArrayList<UserEntity> getAllUsers(){
        ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
        Query q = em.createQuery("Select t from UserEntity t");
        for(Object o: q.getResultList()){
            UserEntity user = (UserEntity) o;
            userList.add(user);
            
        }
        return userList;
    }
    
    @Override
    public void sendMessage(String senderId, String title, String content, String status, String type, ArrayList<String> receiverIds) throws Exception{
        
        UserEntity sender = em.find(UserEntity.class, senderId);
        System.err.println("sessionBean internal message module sendMessage(): getUserId: " + sender.getUserId() );
        if(sender == null){
            throw new Exception("Sender is not found");
        }
        else{
            
        //initialise the new message
        //get the list of receive
        
        //set the senderName
        String firstName = sender.getFirstName();
        String lastName = sender.getLastName();
        String senderName = firstName + " " + lastName;
        
        //set the time
        Calendar sendTime = Calendar.getInstance();
        sendTime.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(sendTime.getTime()).toString();
        System.out.println("sessionbean internal message module sendMessage(): time : " + time) ;
        System.out.println("sessionbean internal message module sendMessage(): title : " + title) ;
        System.out.println("sessionbean internal message module sendMessage(): content : " + content) ;
        System.out.println("sessionbean internal message module sendMessage(): status : " + status) ;
        //instanlise a sendmessage entity
        InternalMessageEntity sendNewMessage  = new InternalMessageEntity(senderName, title, content, time, status, type);
        sender.getSendMessage().add(sendNewMessage);
        sendNewMessage.setSender(sender);
        System.err.println("sessionbean internal message module setMessageSender(): here message has been set in Internal Message Entity under a sender");
        Integer i;
        Collection<InternalMessageReceive> receiverMessageList = new ArrayList<InternalMessageReceive>();
        for( i = 0 ; i < receiverIds.size(); i++ ){
            UserEntity receiver = em.find(UserEntity.class, receiverIds.get(i));  
            if(receiver == null){
              throw new Exception("Receiver is not found!");  
            }else{
                
              InternalMessageReceive receiveMessage = new InternalMessageReceive();   
             // receiveMessage.setSenderId(senderId);
              receiveMessage.setMessage(sendNewMessage);
              receiver.getReceiveMessage().add(receiveMessage);
              receiveMessage.setReceiver(receiver);
              System.err.println("sessionbean internal message module sendMessage(): receiverId: " + receiveMessage.getReceiver().getUserId());
              //sendNewMessage.getReceiveMessages().add(receiveMessage);
              receiverMessageList.add(receiveMessage);
              
              }
           
        }
        sendNewMessage.setReceiveMessages(receiverMessageList);
           em.flush();
 
        }
        
    }
    
    @Override
    public void deleteSendMessage (String sendMessageId) throws Exception{
        
        InternalMessageEntity sendMessage = em.find(InternalMessageEntity.class,sendMessageId);
        if (sendMessage == null){
            throw new Exception("Sent Message is not found!");
        }
        else{
            em.remove(sendMessage);
        }
 
    }  
     
        
    @Override
    public Collection<InternalMessageEntity> viewSendMessage(String senderId) throws Exception{
         Collection<InternalMessageEntity> sendMessageList = new ArrayList<InternalMessageEntity>();
         UserEntity sender = em.find(UserEntity.class, senderId);
        if(sender == null){
            throw new Exception("Sender is not found");
        }
        else{
           sendMessageList = sender.getSendMessage();
        }
        return sendMessageList;
    }
    
    
    @Override
    public void deleteReceiveMessage(String receiveMessageId) throws Exception{
        InternalMessageReceive receiveMessage = em.find(InternalMessageReceive.class, receiveMessageId);
        if(receiveMessage == null){
            throw new Exception("Received Message is not found!");
        }
        else{
            receiveMessage.setDeleted(true);
        }
        
    }
    
    @Override
    public void readReceiveMessage(String receiveMessageId) throws Exception {
         InternalMessageReceive receiveMessage = em.find(InternalMessageReceive.class, receiveMessageId);
        if(receiveMessage == null){
            throw new Exception("Received Message is not found!");
        }
        else{
           receiveMessage.setOpened(true);
        }  
        
    }
     
    
    //
    @Override
    public Collection<InternalMessageReceive> viewReceiveMessage(String receiverId) throws Exception{
        Collection<InternalMessageReceive> receiveMessageList = new ArrayList<InternalMessageReceive>();
        UserEntity receiver = em.find(UserEntity.class, receiverId);
        if(receiver == null){
            throw new Exception("Sender is not found");
        }
        else{
            receiveMessageList = receiver.getReceiveMessage();
        }
         return receiveMessageList;
    }
    
    
    @Override
    public Collection<InternalMessageReceive> viewReceiveMessageBySender (String receiverId, String senderId) throws Exception{
       Collection<InternalMessageReceive> receiveMessageList = null;
       Collection<InternalMessageReceive> receiveMessageListBySender = null;
       receiveMessageList = viewReceiveMessage(receiverId);
       for( Object o : receiveMessageList) {
           InternalMessageReceive receiveMessage = (InternalMessageReceive) o;
           if (receiveMessage.getSendMessage().getSender().getUserId() == senderId){
             receiveMessageListBySender.add(receiveMessage);
           }
       }
       if(receiveMessageListBySender.size() == 0 ) {
           System.out.println("InternalMessageModule: ---> Sender info not found");
       }else{
           System.out.println("InternalMessageModule: ---> display a list of message received from sender ");
       }
       return receiveMessageListBySender;   
    }
    
    
    @Override
   public Collection<InternalMessageEntity> viewSendMessageByReceiver ( String senderId, String receiverId) throws Exception{
       Collection<InternalMessageEntity> sendMessageList = null;
       Collection<InternalMessageEntity> sendMessageListByReceiver = null;
       sendMessageList = viewSendMessage(senderId);
       for( Object o : sendMessageList) {
           InternalMessageEntity sendMessage = (InternalMessageEntity) o;
           for( Object e: sendMessage.getReceiveMessages()){
               InternalMessageReceive receiveMessage = (InternalMessageReceive) e;
              if(receiveMessage.getReceiver().getUserId() == receiverId) {
                sendMessageListByReceiver.add(sendMessage);
                break;
             }
               
           }
       } 
       if(sendMessageListByReceiver.size() == 0){
           System.out.println("InternalMessageModule: ---> Receiver info not found");
       }else{
           System.out.println("InternalMessageModule: ---> display a list of message received from sender");
       }
       return sendMessageListByReceiver;
  }
        
    
}
    
    
    
    
     
    
           
    
    
    
