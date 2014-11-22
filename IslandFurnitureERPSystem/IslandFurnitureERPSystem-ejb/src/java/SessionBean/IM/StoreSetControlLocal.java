/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.IM;

import Entity.Store.OCRM.CountrySetEntity;
import Entity.Store.StoreSetEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author apple
 */
@Local
public interface StoreSetControlLocal {

    public List<StoreSetEntity> getListOfStoreSet(Long storeId);

    public List<CountrySetEntity> getListOfSetNotInStore(Long storeId);
    
    public void addStoreSet(Long storeId,Long countrySetId);

    public void deleteStoreSet(Long storeSetId);
    
    public StoreSetEntity getStoreSet(Long storeSetId);
}
