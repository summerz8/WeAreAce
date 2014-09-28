/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import Entity.CommonInfrastructure.TicketEntity;
import SessionBean.CommonInFrastructure.TicketManageBeanLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author zhengyuan
 */
@Named(value = "ticketManagementForAdminBean")
@ViewScoped
public class ticketManagementForAdminBean {

    /**
     * Creates a new instance of ticketManagementForAdminBean
     */
    public ticketManagementForAdminBean() {
    }

    private List<TicketEntity> submittedTicket;
    private List<TicketEntity> inReviewTicket;
    private List<TicketEntity> inProgressTicket;
    private List<TicketEntity> closeTicket;
    
    private TicketEntity selectedTicket;

    @EJB
    TicketManageBeanLocal tm;

    @PostConstruct
    public void init() {
        submittedTicket = tm.listAllTickets(1);
        inReviewTicket = tm.listAllTickets(2);
        inProgressTicket = tm.listAllTickets(3);
        closeTicket = tm.listAllTickets(4);

    }

    public String displayTicketStatus(TicketEntity ticket) {
        String status = null;
        Integer i = ticket.getStatus();
        Character c = (char) ('0' + i);
        switch (c) {
            case '1':
                status = "Submitted";
                break;
            case '2':
                status = "In Review";
                break;
            case '3':
                status = "In Process";
                break;
            case '4':
                status = "Solved (Closee the tiecket)";
                break;

        }
        return status;
    }

    
    public void readTicket(TicketEntity ticket) throws Exception{
        selectedTicket = ticket;
        System.out.println("ticekt for adminbin: readTicket: ticket Id:" + selectedTicket.getId());
        tm.readATicket(ticket.getId());
        submittedTicket = tm.listAllTickets(1);
        inReviewTicket = tm.listAllTickets(2);
        inProgressTicket = tm.listAllTickets(3);
        closeTicket = tm.listAllTickets(4);
        
        
 
    }
    
        public void processTicket(TicketEntity ticket) throws Exception{
        selectedTicket = ticket;
        System.out.println("ticekt for adminbin: readTicket: ticket Id:" + selectedTicket.getId());
        tm.processATicket(ticket.getId());
        submittedTicket = tm.listAllTickets(1);
        inReviewTicket = tm.listAllTickets(2);
        inProgressTicket = tm.listAllTickets(3);
        closeTicket = tm.listAllTickets(4);
        
        
 
    }
        
          public void closeTicket(TicketEntity ticket) throws Exception{
        selectedTicket = ticket;
        System.out.println("ticekt for adminbin: readTicket: ticket Id:" + selectedTicket.getId());
        tm.closeATicket(ticket.getId());
        submittedTicket = tm.listAllTickets(1);
        inReviewTicket = tm.listAllTickets(2);
        inProgressTicket = tm.listAllTickets(3);
        closeTicket = tm.listAllTickets(4);
        
        
 
    }




public List<TicketEntity> getSubmittedTicket() {
        return submittedTicket;
    }

    public void setSubmittedTicket(List<TicketEntity> submittedTicket) {
        this.submittedTicket = submittedTicket;
    }

    public List<TicketEntity> getInReviewTicket() {
        return inReviewTicket;
    }

    public void setInReviewTicket(List<TicketEntity> inReviewTicket) {
        this.inReviewTicket = inReviewTicket;
    }

    public List<TicketEntity> getInProgressTicket() {
        return inProgressTicket;
    }

    public void setInProgressTicket(List<TicketEntity> inProgressTicket) {
        this.inProgressTicket = inProgressTicket;
    }

    public List<TicketEntity> getCloseTicket() {
        return closeTicket;
    }

    public void setCloseTicket(List<TicketEntity> closeTicket) {
        this.closeTicket = closeTicket;
    }

    public TicketManageBeanLocal getTm() {
        return tm;
    }

    public void setTm(TicketManageBeanLocal tm) {
        this.tm = tm;
    }

    public TicketEntity getSelectedTicket() {
        return selectedTicket;
    }

    public void setSelectedTicket(TicketEntity selectedTicket) {
        this.selectedTicket = selectedTicket;
    }
   
   
   
    
}
