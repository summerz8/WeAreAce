/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure;

import Entity.CommonInfrastructure.InternalMessageReceive;
import Entity.CommonInfrastructure.TicketEntity;
import SessionBean.CommonInFrastructure.TicketManageBeanLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zhengyuan
 */
@Named(value = "ticketDetailBean")
@ViewScoped
public class TicketDetailBean {

    /**
     * Creates a new instance of TicketDetailBean
     */
    public TicketDetailBean() {
    }
    
    private TicketEntity ticket;
    
    @EJB 
    TicketManageBeanLocal tm;
        
    
    @PostConstruct
    public void init(){
        ticket = (TicketEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("detailTicket");
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("detailTicket");
        
    }
    
    public String displayTicketStatus(TicketEntity ticket){
        String status = null;
        Integer i = ticket.getStatus();
        Character c =  (char) ('0' + i);
        switch (c){
            case '1': status = "Submitted";break;
            case '2': status = "In Review";break;
            case '3': status = "In Process";break;
            case '4': status = "Solved (Closee the tiecket)";break;
            
            
        }
        return status;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }

    public TicketManageBeanLocal getTm() {
        return tm;
    }

    public void setTm(TicketManageBeanLocal tm) {
        this.tm = tm;
    }
    
    
    
}
