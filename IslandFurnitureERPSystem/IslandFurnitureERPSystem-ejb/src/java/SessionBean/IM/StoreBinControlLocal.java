/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.IM;

import Entity.Store.IM.StoreWarehouseBinEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zhengyuan
 */
@Local
public interface StoreBinControlLocal {

    public int addAStorageBin(String name, String remark, Boolean isBackHouse, Boolean isDisplayArea, Boolean isSelfCollect, Long storeId);

    public void editAStorageBin(String name, String remark, Boolean isBackHouse, Boolean isDisplayArea, Boolean isSelfCollect, Long storeBinId);

    public Integer deleteAStorageBin(Long storeBinId);

    

    public List<StoreWarehouseBinEntity> getAllBackHouseBin(Long storeId);

    public List<StoreWarehouseBinEntity> getAllSelfCollectBin(Long storeId);

    public List<StoreWarehouseBinEntity> getAllDisplayAreaBin(Long storeId);
    
}
