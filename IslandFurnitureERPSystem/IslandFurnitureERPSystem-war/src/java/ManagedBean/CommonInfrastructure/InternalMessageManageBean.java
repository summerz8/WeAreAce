/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.CommonInfrastructure;


import Entity.CommonInfrastructure.InternalMessageReceive;
import Entity.CommonInfrastructure.UserEntity;
import SessionBean.CommonInFrastructure.InternalMessageModuleLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
@Named(value="internalMessageManageBean")
@ViewScoped
public class InternalMessageManageBean implements Serializable {

    /**
     * Creates a new instance of InternalMessageManageBean
     */
    
    private String receiverIds;
    private String title;
    private String content;
    private String currentUserId;
    private String sendMessageStatus;
    private String sendMessageType;
    private String receiveMessageStatus;
    private String receiveMessageIsDelete;
    private String receiverIdString;
    private String statusMessage;
    private ArrayList<String> receiverIdList;
    
    @EJB
    private InternalMessageModuleLocal im;
    
    private List<UserEntity> userEntities;
    private List<UserEntity> selectedUserEntities;
    
        
        
    public InternalMessageManageBean() {
        
    }
    
    @PostConstruct
    public void init()
    {
        currentUserId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
        userEntities = im.getAllUsers();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userEntities", userEntities);
    }
    
    @PreDestroy
    public void destroy()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userEntities");
    }
    
    public List<UserEntity> completeUserEntity(String query) {
        
        List<UserEntity> filteredUserEntities = new ArrayList<>();
         
        for(UserEntity ue:userEntities)
        {
            String searchString = ue.getFirstName() + " " + ue.getLastName() + " " + ue.getUserId();
            
            if(searchString.toLowerCase().contains(query.toLowerCase()))
            {
                filteredUserEntities.add(ue);
            }
        }
        
        return filteredUserEntities;
    }
    
    public void sendMessage(ActionEvent event) throws Exception
    {   
        receiverIdList = new ArrayList<String> ();
        System.err.println("sendMessage(): TITLE " + title );
        System.err.println("sendMessage(): Content " + content );
        System.err.println("sendMessage(): UserId " + currentUserId );
        for(UserEntity userEntityString:selectedUserEntities)
        {
            System.err.println("userEntityString: " + userEntityString.getUserId());
            receiverIdList.add(userEntityString.getUserId());
        }
        //DO NOT TOUCH - ZY
        im.sendMessage(currentUserId, title, content, null, null,  receiverIdList);
        System.err.println("sendMessage(): Message Sent ");
        
    }
    
    public InternalMessageModuleLocal getIm() {
        return im;
    }

    public void setIm(InternalMessageModuleLocal im) {
        this.im = im;
    }


    public String getReceiverIds() {
        return receiverIds;
    }

    public void setReceiverIds(String receiverIds) {
        this.receiverIds = receiverIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUser(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getSendMessageStatus() {
        return sendMessageStatus;
    }

    public void setSendMessageStatus(String sendMessageStatus) {
        this.sendMessageStatus = sendMessageStatus;
    }

    public String getSendMessageType() {
        return sendMessageType;
    }

    public void setSendMessageType(String sendMessageType) {
        this.sendMessageType = sendMessageType;
    }

    public String getReceiveMessageStatus() {
        return receiveMessageStatus;
    }

    public void setReceiveMessageStatus(String receiveMessageStatus) {
        this.receiveMessageStatus = receiveMessageStatus;
    }

    public String getReceiveMessageIsDelete() {
        return receiveMessageIsDelete;
    }

    public void setReceiveMessageIsDelete(String receiveMessageIsDelete) {
        this.receiveMessageIsDelete = receiveMessageIsDelete;
    }

    public String getReceiverIdString() {
        return receiverIdString;
    }

    public void setReceiverIdString(String receiverIdString) {
        this.receiverIdString = receiverIdString;
    }

    

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
       

    public void displayNewMessageWindow(ActionEvent event){
        
    }
    
    public ArrayList<UserEntity> selectReceiver(String query){
        ArrayList<UserEntity> allUsers = im.getAllUsers();
        ArrayList<UserEntity> filteredUsers = new ArrayList<UserEntity>();
        
        for(int i = 0; i < allUsers.size(); i ++) {
            UserEntity user = allUsers.get(i);
            String fullName = user.getFirstName() + " " + user.getMidName() + " " + user.getLastName();
            if(fullName.toLowerCase().startsWith(query)){
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }
    
    public ArrayList<String> completeText(String query){
        ArrayList<String> results = new ArrayList<String> ();
        for( int i = 0 ; i < 20; i ++){
            results.add(query + i);
        }
        return results;
    }
    
    
//    public void sendNewMessage(ActionEvent event) throws Exception{
//        
//        
//        try{
////          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userId", "123");
//           currentUserId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
//            im.sendMessage(currentUserId, title, content, null, null,  receiverIds);
//            statusMessage = "New Message Sent Successfully!";
//             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_INFO,"Send New Message Result: " + statusMessage + " (New Message is sent from  " + currentUserId + ")", "" ));
//            
//        }
//        catch(Exception e){
//             e.printStackTrace(); 
//        }
//          
//    }
    
    public void receiveMessage(ActionEvent event){
        try{
           currentUserId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
           Collection<InternalMessageReceive> receiveList = im.viewReceiveMessage(currentUserId);
           
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public List<UserEntity> getSelectedUserEntities() {
        return selectedUserEntities;
    }

    public void setSelectedUserEntities(List<UserEntity> selectedUserEntities) {
        this.selectedUserEntities = selectedUserEntities;
    }
}
