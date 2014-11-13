/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.ProductEntity;
import Entity.Store.StoreEntity;
import java.util.Collection;
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

    public void DeleteFactory(long factoryId)throws Exception;

    public void ModifyFactory(long factoryId, String country, String address, String contact, String manager) throws Exception;

    public List<FactoryEntity> ListFactory();

    public void AddStore(String country, String address, String contact, String manager);

    public void DeleteStore(Long storeId) throws Exception;

    public void ModifyStore(long storeId, String country, String address, String contact, String manager) throws Exception;

    public List<StoreEntity> ListStore();

    public Collection<ProductEntity> viewProductListNotInFactory(Long factoryId) throws Exception;

    public Collection<FactoryProductEntity> listFactoryProduct(Long factoryId) throws Exception;

    public Integer deleteFatoryProduct(Long factoryProductId);

    public Integer addFactoryProduct(Long FactoryId, Long ProductId);

    public FactoryEntity getFactory(Long factoryId);
    
    public StoreEntity getStore(Long storeId);
}
