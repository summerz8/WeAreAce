/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.UserEntity;
import Entity.Factory.FactoryEntity;
import Entity.Factory.FactoryProductEntity;
import Entity.Factory.ProductEntity;
import Entity.Store.StoreEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dan
 */
@Stateful
public class Factory_StoreManagementModule implements Factory_StoreManagementModuleLocal {

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private RetailProduct_ProductManagementModuleLocal rpmml;

    public Factory_StoreManagementModule() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void AddFactory(String country, String address, String contact, String manager) {
        System.out.println("Factory_StoreManagementModule: AddFactory(): ");
        FactoryEntity fe = new FactoryEntity(country, address, contact, manager, false);
        em.persist(fe);
        em.flush();
        System.out.println("Factory_StoreManagementModule: AddFactory(): " + fe.getFactoryId());
    }

    @Override
    public void DeleteFactory(long factoryId) {
        System.out.println("Factory_StoreManagementModule: DeleteFactory(): ");
        FactoryEntity fe = em.find(FactoryEntity.class, factoryId);
        fe.setDeleteFlag(Boolean.TRUE);

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
        em.persist(fe);
        em.flush();
        
    }

    @Override
    public List<FactoryEntity> ListFactory() {
        System.out.println("Factory_StoreManagementModule: ListFactory(): ");
        Query q = em.createQuery("SELECT t FROM FactoryEntity t");
        List requiredFactoryList = new ArrayList();
        for (Object o : q.getResultList()) {
            FactoryEntity u = (FactoryEntity) o;
            if (!u.isDeleteFlag()) {
                requiredFactoryList.add(u);
            }
        }
        return requiredFactoryList;
    }

//    @Override
//    public void searchFactory(){}
    @Override
    public void AddStore(String country, String address, String contact, String manager) {
        System.out.println("Factory_StoreManagementModule: AddStore(): ");
        StoreEntity se = new StoreEntity(address, country, contact, manager, false);
        em.persist(se);
        em.flush();
        System.out.println("Factory_StoreManagementModule: AddStore(): " + se.getStoreId());

    }

    @Override
    public void DeleteStore(Long storeId) {
        System.out.println("Factory_StoreManagementModule: DeleteFactory(): ");
        StoreEntity se = em.find(StoreEntity.class, storeId);
        se.setDeleteFlag(Boolean.TRUE);
        em.persist(se);
        em.flush();
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
        em.persist(se);
        em.flush();
    }

    @Override
    public List<StoreEntity> ListStore() {
        System.out.println("Factory_StoreManagementModule: ListStore(): ");
        Query q = em.createQuery("SELECT t FROM StoreEntity t");
        List requiredStoreList = new ArrayList();
        for (Object o : q.getResultList()) {
            StoreEntity u = (StoreEntity) o;
            if(!u.isDeleteFlag()) requiredStoreList.add(u);
            System.out.println("Factory_StoreManagementModule: ListStore(): store added"+u.getStoreId());
        }
        return requiredStoreList;
    }

//    @Override
//    public void searchStore(){
//    
//    }
    
    @Override
      public Integer addFactoryProduct(Long FactoryId, Long ProductId){
          Integer status = 0;
          ProductEntity product = em.find(ProductEntity.class, ProductId);
          FactoryEntity factory = em.find(FactoryEntity.class, FactoryId);
          if(factory ==null){
             return status; // message: factory is not found. 
      }
          else if(product == null){
              status = 1;
              return status;  //  message : product is not found. 
              
          }
          else {
              
              

                  
                FactoryProductEntity newFP = new FactoryProductEntity();
                newFP.setFactory(factory);
                newFP.setDeleteFlag(false);
                newFP.setProduct(product);
                newFP.setUnit(product.getUnit());
                em.persist(newFP);
                factory.getFactoryProducts().add(newFP);
                em.flush();
                status = 2;
              
              
             }
          
          return status;
    }
      
    @Override
    public Collection<ProductEntity> viewProductListNotInFactory(Long factoryId) throws Exception {//test works!!
        System.out.println("viewRawMaterialListNotInFactory():");
        Integer flag = 0;
        Collection<ProductEntity> pdList = new ArrayList<> ();
        FactoryEntity currentFactory = em.find(FactoryEntity.class, factoryId);
        Collection<FactoryProductEntity> currentFactoryProductList = currentFactory.getFactoryProducts();
        try {
            Query q = em.createQuery("Select pd from ProductEntity PD");
            outerLoop:
            for (Object o : q.getResultList()) {
                ProductEntity pd = (ProductEntity) o;
                  for(FactoryProductEntity fpe: currentFactoryProductList){
                      FactoryProductEntity fproduct = fpe;
                      if(fproduct.getProduct().equals(pd) && (!fproduct.isDeleteFlag())){
                           flag = 1;
                           break;
                      }
                  }
                 if(flag == 0){
                     pdList.add(pd);
                 }
                 flag = 0;
               
            }
        }catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        return pdList;
    }
    
    @Override
    public Collection<FactoryProductEntity> listFactoryProduct(Long factoryId) throws Exception{
        Collection<FactoryProductEntity> pdList = new ArrayList<>();
         FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
         if(factory != null){
            Collection<FactoryProductEntity> temList = factory.getFactoryProducts();
            for(FactoryProductEntity pety: temList){
                FactoryProductEntity pe = pety;
                
                if(!pe.isDeleteFlag()){
                    pdList.add(pe);
                }
            }
            return pdList;
         }
         else{
             throw new Exception ("A Excepction occurs! ");
             
         }
        
    }
    
    @Override
    public Integer deleteFatoryProduct (Long factoryProductId){
        FactoryProductEntity fpe = em.find(FactoryProductEntity.class, factoryProductId);
        Integer message = 0;
        if(fpe.getBlockedInventory()!= 0){
              message = 1;
              System.out.println("block inventory is amount. cant delete");
          }
        else if ( fpe.getReturnedInventory() != 0){
            message = 1;  
            System.out.println("Returned inventory is amount. cant delete");
          
          }
        else if (fpe.getUnrestrictedInventory() != 0){
            message = 1;
            System.out.println("Unrestricted inventory is amount. cant delete");
        }else{
            
            fpe.setDeleteFlag(true);
            em.flush();
            System.out.println("Factory Product has been set successfully!");
        }
      
      return message;
      
      
    }
}
