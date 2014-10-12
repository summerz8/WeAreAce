/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.OCRM.FeedbackEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hangsun
 */
@Stateless
public class FeedbackModule implements FeedbackModuleLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void createFeedback(String title,String content,String email)throws Exception{
        
        FeedbackEntity feedback = new FeedbackEntity(title,content,email);
        
        em.persist(feedback);
        em.flush();
    
    }
    
    @Override
    public List<FeedbackEntity> getFeedbackList(){
        List<FeedbackEntity> feedbackList = new ArrayList<FeedbackEntity>();
           
        Query q = em.createQuery("Select f from FeedbackEntity f");
            for (Object o : q.getResultList()) {
                FeedbackEntity feedback = (FeedbackEntity) o;
                feedbackList.add(feedback);
            }
        
        return feedbackList;
    }
}
