/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import javax.ejb.Local;

/**
 *
 * @author zhengyuan
 */
@Local
public interface TicketManageBeanLocal {

    public void closeATicket(long ticketId) throws Exception;

    public void raiseATicket(String UserId, String title, String content) throws Exception;

    public void processATicket(long ticketId) throws Exception;

    public void readATicket(long ticketId) throws Exception;

    public void reply(long userId, String replyType);
    
}
