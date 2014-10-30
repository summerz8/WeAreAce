/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.TicketEntity;
import Entity.CommonInfrastructure.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    @PersistenceContext
    EntityManager em;

    @Override
    public void raiseATicket(String UserId, String title, String content) throws Exception {
        UserEntity systemUser = em.find(UserEntity.class, UserId);
        if (systemUser == null) {
            throw new Exception("System user is not found!");
        } else {
            TicketEntity ticket = new TicketEntity(title, content);
            
            systemUser.getTickets().add(ticket);
            ticket.setSystemUser(systemUser);
            em.persist(ticket);
            em.flush();

        }
    }

    //once admin opens the ticket, it triggers this    
    @Override
    public void readATicket(Long ticketId) throws Exception {
        TicketEntity ticket = em.find(TicketEntity.class, ticketId);
        if (ticket == null) {
            throw new Exception("Ticket is not found!");

        } else {
            ticket.setStatus(2);
            em.flush();
        }
    }

    //admin will need to update 
    @Override
    public void processATicket(Long ticketId) throws Exception {
        TicketEntity ticket = em.find(TicketEntity.class, ticketId);
        if (ticket == null) {
            throw new Exception("Ticket is not found!");

        } else {
            ticket.setStatus(3);
            em.flush();
        }
    }

    @Override
    public void closeATicket(Long ticketId) throws Exception {
        TicketEntity ticket = em.find(TicketEntity.class, ticketId);
        if (ticket == null) {
            throw new Exception("Ticket is not found!");

        } else {
            ticket.setStatus(4);
            em.flush();
        }
    }

    //for user
    @Override
    public List<TicketEntity> listSystemTicket(String userId)  {
        UserEntity systemUser = em.find(UserEntity.class, userId);
        List<TicketEntity> ticketList;
        
        
        ticketList = (List<TicketEntity>)  systemUser.getTickets();
        System.out.println("Session bean : get the system ticket:" + ticketList.size());

        return ticketList;
    }

    // for system user
    @Override
    public List<TicketEntity> listAllTickets(Integer status) {
        List<TicketEntity> ticketList = new ArrayList<TicketEntity>();
        if (status == 0) {
            Query q = em.createQuery("Select t from TicketEntity t");
            for (Object o : q.getResultList()) {
                TicketEntity ticket = (TicketEntity) o;
                ticketList.add(ticket);
            }
        } else {
            Query q = em.createQuery("Select t from TicketEntity t Where t.status =:s");
            q.setParameter("s", status);
            for (Object o : q.getResultList()) {
                TicketEntity ticket = (TicketEntity) o;
                ticketList.add(ticket);
            }

        }
        return ticketList;
    }

}
