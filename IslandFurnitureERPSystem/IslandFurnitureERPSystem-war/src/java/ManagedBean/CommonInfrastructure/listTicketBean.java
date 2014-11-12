/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure;

import Entity.CommonInfrastructure.TicketEntity;
import SessionBean.CommonInFrastructure.TicketManageBeanLocal;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "listTicketBean")
@ViewScoped
public class listTicketBean {

    /**
     * Creates a new instance of listTicketBean
     */
    public listTicketBean() {
    }
    @EJB
    TicketManageBeanLocal tm;
    
    private List<TicketEntity> ticketList;
    private String currentUserId;
    
    @PostConstruct
    private void init(){
        try {
        currentUserId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
        ticketList = tm.listSystemTicket(currentUserId);} catch (Exception e) {
            System.out.println("unexpected exception occured");
            e.printStackTrace();
        }
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
    
    public void viewTicketDetail(TicketEntity ticket) throws IOException{
        //update path zy
        String path = "/secured/public/readTicketDetail.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("detailTicket",ticket);
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect( url + path );
    
    }
    
    public TicketManageBeanLocal getTm() {
        return tm;
    }

    public void setTm(TicketManageBeanLocal tm) {
        this.tm = tm;
    }



    public List<TicketEntity> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketEntity> ticketList) {
        this.ticketList = ticketList;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }
    

}
