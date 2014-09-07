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
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zhengyuan
 */
@Stateless
public class InternalMessageModule implements InternalMessageModuleRemote {

     @PersistenceContext
     private EntityManager em;
     
     public InternalMessageEntity getMessageById( Long id ) throws Exception {
         InternalMessageEntity message = em.find(InternalMessageEntity.class, id);
         if(message == null){
             throw new Exception("Message does not exist.");
         }
         return message;
     }
     
     public void createNewMessage(long senderId, long receiverId, String title, String msg, String type){
         System.out.println("InternalMessageModule ... createNewMessage starts");
         InternalMessageEntity message = new InternalMessageEntity();
         InternalMessageReceive msgReceiver = new InternalMessageReceive();
         msgReceiver.setDeleted(false);
         msgReceiver.setOpened(false);
         msgReceiver.setSenderId(senderId);
         msgReceiver.setReceiverId(receiverId);
         msgReceiver.setSendMessage(message);
         UserEntity sender = em.find(UserEntity.class, senderId);
         message.setSenderName(sender.getFirstName());
         Collection<InternalMessageReceive> receiverInfo = new ArrayList<InternalMessageReceive>();
         receiverInfo.add(msgReceiver);
         message.setReceiverInfo(receiverInfo);
         message.setTittle(title);
         message.setContent(msg);
         message.setType(type);
         Calendar calendar = Calendar.getInstance();
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
         
         try
         {
             
         
         String sendTime = dateFormat.format(calendar.getTime());
         message.setSendTime(sendTime);
         }
         catch (Exception e){
             e.printStackTrace();
         }
         
         
         
         System.out.println("InternalMessageModule ... createNewMessage ends");
         em.persist(message);
           
                  
     }
    
}
     
     
