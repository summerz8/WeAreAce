/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.ReturnedItemMovementRecordEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface PostSaleServiceLocal {

    public List<ReturnedItemMovementRecordEntity> ListAllRecords(Long storeId);

    public ReturnedItemMovementRecordEntity createRecord(String type,Long storeId,Long storeProductId,String description,Long MemberId);

    }
