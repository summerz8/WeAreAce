/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.InternalMessageEntity;
import Entity.CommonInfrastructure.InternalMessageReceive;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author zhengyuan
 */
@Local
public interface InternalMessageModuleLocal {


    public void sendMessage(long senderId, String title, String content, String status, String type, String receiverIdString) throws Exception;

    public void readReceiveMessage(long receiveMessageId) throws Exception;
    
    public void deleteSendMessage(long sendMessageId) throws Exception;
    
    public void deleteReceiveMessage(long receiveMessageId) throws Exception;

    public Collection<InternalMessageEntity> viewSendMessage(long senderId) throws Exception;

    public Collection<InternalMessageReceive> viewReceiveMessage(long receiverId) throws Exception;

    public Collection<InternalMessageEntity> viewSendMessageByReceiver(long senderId, long receiverId) throws Exception;

    public Collection<InternalMessageReceive> viewReceiveMessageBySender(long receiverId, long senderId) throws Exception;

    
 
}
