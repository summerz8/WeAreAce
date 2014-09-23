/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure;

import SessionBean.CommonInFrastructure.TicketManageBeanLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "writeNewTicket")
@ViewScoped
public class WriteNewTicket {

    /**
     * Creates a new instance of WriteNewTicket
     */
    
    private String title;
    private String description;
    private String userId;
    
    @EJB
    TicketManageBeanLocal tm;
    
    public WriteNewTicket() {
        

    }
    
    
    @PostConstruct
    public void init(){
        userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
    }
    
    public void createANewTicket(ActionEvent event) throws Exception{
      
        tm.raiseATicket(userId, title, description); 
        
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("message", "Ticket has been submitted. We will get back to you soon."));
        
        title = null;
        description = null;
        

    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TicketManageBeanLocal getTm() {
        return tm;
    }

    public void setTm(TicketManageBeanLocal tm) {
        this.tm = tm;
    }
    

    
}
