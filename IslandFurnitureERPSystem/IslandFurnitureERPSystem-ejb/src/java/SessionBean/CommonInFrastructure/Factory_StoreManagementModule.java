/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.FactoryEntity;
import Entity.Store.StoreEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author dan
 */
@Stateful
public class Factory_StoreManagementModule implements Factory_StoreManagementModuleLocal {

    private EntityManager em;
    public Factory_StoreManagementModule() {
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @Override
    public void AddFactory(String country, String address, String contact, String manager) {
        System.out.println("Factory_StoreManagementModule: AddFactory(): ");
        FactoryEntity fe =new FactoryEntity(country, address, contact, manager, true);
    }

    @Override
    public void DeleteFactory(long factoryId) {
        System.out.println("Factory_StoreManagementModule: DeleteFactory(): ");
        FactoryEntity fe = em.find(FactoryEntity.class, factoryId);
        fe.setDeleteFlag(Boolean.FALSE);
        
    }

    @Override
    public void ModifyFactory(long factoryId, String country, String address, 
            String contact, String manager) {
        System.out.println("Factory_StoreManagementModule: ModifyFactory(): ");
        FactoryEntity fe = em.find(FactoryEntity.class, factoryId);
        fe.setAddress(address);
        fe.setCountry(country);
        fe.setContact(contact);
        fe.setManager(manager);
    }
    
    @Override
    public List<FactoryEntity> ListFactory(){
        System.out.println("Factory_StoreManagementModule: ListFactory(): ");
        Query q = em.createQuery("SELECT t FROM FactoryEntity t");
        List requiredFactoryList = new ArrayList();
        for(Object o:q.getResultList()){
            FactoryEntity u = (FactoryEntity) o;                    
            requiredFactoryList.add(u);         
        }       
        return requiredFactoryList;
    }
    
//    @Override
//    public void searchFactory(){}

    @Override
    public void AddStore(String country, String address, String contact, String manager) {
        System.out.println("Factory_StoreManagementModule: AddStore(): ");
        StoreEntity se =new StoreEntity(country, address, contact, manager, true);
   
    }

    @Override
    public void DeleteStore(Long storeId) {
        System.out.println("Factory_StoreManagementModule: DeleteFactory(): ");
        StoreEntity se = em.find(StoreEntity.class, storeId);
        se.setDeleteFlag(Boolean.FALSE);
    }

    @Override
    public void ModifyStore(long storeId, String country, String address, 
            String contact, String manager) {
        System.out.println("Factory_StoreManagementModule: ModifyStore(): ");
        StoreEntity se = em.find(StoreEntity.class, storeId);
        se.setAddress(address);
        se.setCountry(country);
        se.setContact(contact);
        se.setManager(manager);
    }
    
    @Override
    public List<StoreEntity> ListStore(){
    System.out.println("Factory_StoreManagementModule: ListStore(): ");
        Query q = em.createQuery("SELECT t FROM StoreEntity t");
        List requiredStoreList = new ArrayList();
        for(Object o:q.getResultList()){
            StoreEntity u = (StoreEntity) o;          
            requiredStoreList.add(u);         
        }       
        return requiredStoreList;
    }
    
//    @Override
//    public void searchStore(){
//    
//    }
}
