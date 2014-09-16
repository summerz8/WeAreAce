/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.TicketEntity;
import Entity.CommonInfrastructure.UserEntity;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *  
 * @author zhengyuan
 */

// The main purpose for this management module is for our client to raise ticekts when there's any system problem.
@Stateless
public class TicketManageBean implements TicketManageBeanLocal {

    public TicketManageBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    EntityManager em;
    
    @Override
    public void raiseATicket(String UserId, String title, String content) throws Exception{
     UserEntity systemUser = em.find(UserEntity.class, UserId);
     if( systemUser == null ){
         throw new Exception("system user is not found!");
     }
     else{
         TicketEntity ticket = new TicketEntity(title,content);
         systemUser.getTickets().add(ticket);
         
     }
    }
    
 //once admin opens the ticket, it triggers this    
    @Override
    public void readATicket(long ticketId) throws Exception{
        TicketEntity ticket = em.find(TicketEntity.class, ticketId);
        if(ticket == null){
            throw new Exception("Tickey is not found");
            
        }
        else {
           ticket.setStatus(2);
        }
    }
    
 //admin will need to update 
    @Override
        public void processATicket(long ticketId) throws Exception{
        TicketEntity ticket = em.find(TicketEntity.class, ticketId);
        if(ticket == null){
            throw new Exception("Tickey is not found");
            
        }
        else {
           ticket.setStatus(3);
        }
    }
    @Override
            public void closeATicket(long ticketId) throws Exception{
        TicketEntity ticket = em.find(TicketEntity.class, ticketId);
        if(ticket == null){
            throw new Exception("Tickey is not found");
            
        }
        else {
           ticket.setStatus(4);
        }
     }   
        
            
        //yet to implement
        @Override
        public void reply (long userId, String replyType){ 
          switch (replyType){
              case "ReplyToTech":break;
              case "ReplyToUser":break;
            
              }
        
    }
        
        //for user
        public ArrayList<TicketEntity> listSystemTicket ( long userId) throws Exception{
          UserEntity systemUser = em.find(UserEntity.class, userId);
          ArrayList<TicketEntity> ticketList = new ArrayList<TicketEntity>();
          if(systemUser == null){
              throw new Exception("User is not found!");
          }
          else{
              ticketList = (ArrayList<TicketEntity>) systemUser.getTickets();
          }
            
          return ticketList;  
        }
        
        // for system user
        public ArrayList<TicketEntity> listAllTickets (Integer status){
           ArrayList<TicketEntity> ticketList = new ArrayList<TicketEntity>();
            if(status == 0 ){
                Query q = em.createQuery("Select t from TicketEntity t");
                for( Object o : q.getResultList()){
                    TicketEntity ticket = (TicketEntity) o;
                    ticketList.add(ticket);
                }
            }
            else{
               Query q = em.createQuery("Select t from TicketEntity t Where t.status =:s");
               q.setParameter("s", status);
                for( Object o : q.getResultList()){
                    TicketEntity ticket = (TicketEntity) o;
                    ticketList.add(ticket);
                }
                
            }
           return ticketList; 
        }
        
        
   
    
}
