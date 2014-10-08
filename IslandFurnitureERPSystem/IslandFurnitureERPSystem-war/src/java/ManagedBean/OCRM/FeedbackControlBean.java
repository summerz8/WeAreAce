/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.FeedbackEntity;
import SessionBean.OCRM.FeedbackModuleLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author hangsun
 */
@Named(value = "feedbackControlBean")
@ViewScoped
public class FeedbackControlBean {
    
    @EJB
    private FeedbackModuleLocal fm; 
    
    
    private List<FeedbackEntity> feedbackList;
    private String email;
    private String title;
    private String content;
    /**
     * Creates a new instance of FeedbackControlBean
     */
    public FeedbackControlBean() {
    }
    
    
//    @PostConstruct
//    public void init(){
//        feedbackList = fm.getFeedbackList();
//    }
    
    public List<FeedbackEntity> viewFeedbackList(){
        feedbackList = fm.getFeedbackList();
        return feedbackList;
    }
    
    public String createFeedback() throws Exception{
        
        fm.createFeedback(title, content, email);
        
        return "/secured/restricted/Store/OCRM/viewFeedback?faces-redirect=true";
    }

    public FeedbackModuleLocal getFm() {
        return fm;
    }

    public List<FeedbackEntity> getFeedbackList() {
        return feedbackList;
    }

    public void setFm(FeedbackModuleLocal fm) {
        this.fm = fm;
    }

    public void setFeedbackList(List<FeedbackEntity> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    
    
    
}
