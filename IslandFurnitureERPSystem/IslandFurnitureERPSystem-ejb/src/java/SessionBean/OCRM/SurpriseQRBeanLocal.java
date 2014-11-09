/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.OCRM;

import Entity.Store.OCRM.SurpriseQREntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dan
 */
@Local
public interface SurpriseQRBeanLocal {

//    public Boolean checkQR(Calendar date);

    public String createQR(Double percentage, Double rewardPoints, Calendar expireDate);

    public List<SurpriseQREntity> getAllQR();
    
}
