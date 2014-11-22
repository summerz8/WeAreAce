/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.OCRM.FeedbackEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hangsun
 */
@Local
public interface FeedbackModuleLocal {

    public void createFeedback(String title, String content, String email,String name) throws Exception;

    public List<FeedbackEntity> getFeedbackList();
    
}
