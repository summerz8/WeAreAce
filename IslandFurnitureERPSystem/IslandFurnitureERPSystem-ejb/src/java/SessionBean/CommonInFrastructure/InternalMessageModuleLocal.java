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
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zhengyuan
 */
@Local
public interface InternalMessageModuleLocal {


//     Session Bean Functionality:
// 1. return all users for selecting receiver
// 2. sendMessage (One to One, One to Many)
// 3. readMessage 
// 4. delete sent Mesage
// 5. delete received Message
// 6. reply a message
// 7. forward a messag 
// 8. a. Dispaly receive Message List
//    b. Display receive Message List by Sender -- enable by primeface
// 9. a. Display sent Message List
// 9. b. Display send Message List by Receiver -- enable by primeface
    
    
    public List<UserEntity> getAllUsers();

    public void sendMessage(String senderId, String title, String content,  ArrayList<String> receiverIds) throws Exception;


    public void readReceiveMessage(Long receiveMessageId) throws Exception;
    
    public void replyMessage(Long receiveMessageId) throws Exception;
    
    public void deleteSendMessage(Long sendMessageId) throws Exception;
    
    public void deleteReceiveMessage(Long receiveMessageId) throws Exception;

    public Collection<InternalMessageEntity> viewSendMessage(String senderId);

    public Collection<InternalMessageReceive> viewReceiveMessage(String receiverId);

 
}
