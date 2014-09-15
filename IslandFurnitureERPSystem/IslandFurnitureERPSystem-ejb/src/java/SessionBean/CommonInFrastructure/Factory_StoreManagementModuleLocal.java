/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.Factory.FactoryEntity;
import Entity.Store.StoreEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dan
 */
@Local
public interface Factory_StoreManagementModuleLocal {
    //public void searchStore();
    //public void searchFactory();

    public void AddFactory(String country, String address, String contact, String manager);

    public void DeleteFactory(long factoryId);

    public void ModifyFactory(long factoryId, String country, String address, String contact, String manager);

    public List<FactoryEntity> ListFactory();

    public void AddStore(String country, String address, String contact, String manager);

    public void DeleteStore(Long storeId);

    public void ModifyStore(long storeId, String country, String address, String contact, String manager);

    public List<StoreEntity> ListStore();
    
}
