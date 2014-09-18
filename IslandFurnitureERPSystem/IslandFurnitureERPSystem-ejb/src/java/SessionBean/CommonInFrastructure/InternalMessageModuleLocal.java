/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.InternalMessageEntity;
import Entity.CommonInfrastructure.InternalMessageReceive;
import Entity.CommonInfrastructure.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author zhengyuan
 */
@Local
public interface InternalMessageModuleLocal {



    public void readReceiveMessage(String receiveMessageId) throws Exception;
    
    public void deleteSendMessage(String sendMessageId) throws Exception;
    
    public void deleteReceiveMessage(String receiveMessageId) throws Exception;

    public Collection<InternalMessageEntity> viewSendMessage(String senderId) throws Exception;

    public Collection<InternalMessageReceive> viewReceiveMessage(String receiverId) throws Exception;

    public Collection<InternalMessageEntity> viewSendMessageByReceiver(String senderId, String receiverId) throws Exception;

    public Collection<InternalMessageReceive> viewReceiveMessageBySender(String receiverId, String senderId) throws Exception;

    public ArrayList<UserEntity> getAllUsers();

    public void sendMessage(String senderId, String title, String content, String status, String type, ArrayList<String> receiverIds) throws Exception;

    
 
}
