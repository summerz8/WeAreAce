/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.TicketEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zhengyuan
 */
@Local
public interface TicketManageBeanLocal {

    public void closeATicket(Long ticketId) throws Exception;

    public void raiseATicket(String UserId, String title, String content) throws Exception;

    public void processATicket(Long ticketId) throws Exception;

    public void readATicket(Long ticketId) throws Exception;

    public List<TicketEntity> listSystemTicket(String userId);

    public List<TicketEntity> listAllTickets(Integer status);

  
    
}
