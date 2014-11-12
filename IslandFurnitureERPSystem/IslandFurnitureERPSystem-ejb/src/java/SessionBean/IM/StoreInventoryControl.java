/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.IM;

import Entity.Factory.FactoryProductEntity;
import Entity.Factory.FactoryRetailProductEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Store.IM.StoreBinProductEntity;
import Entity.Store.IM.StoreBinRetailProductEntity;
import Entity.Store.IM.StoreWarehouseBinEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreItemMappingEntity;
import Entity.Store.StoreProductEntity;
import Entity.Store.StoreRetailProductEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author zhengyuan
 */
@Stateless
public class StoreInventoryControl implements StoreInventoryControlLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<StoreProductEntity> getListOfStoreProduct(Long storeId){
       try{
       StoreEntity store = em.find(StoreEntity.class,storeId);
      System.out.println("Session Bean: Store inv Control : getListOfStoreProduct :" + storeId);
      List<StoreProductEntity> storeProductTemp = new ArrayList<StoreProductEntity>();
      
      List<StoreProductEntity>  storeProductList = store.getStoreProducts();
      for(StoreProductEntity aProduct:storeProductList){
       if(!aProduct.isDeleteFlag()){
        storeProductTemp.add(aProduct);
       }
      }  
           System.out.println("Session Bean: Store inv Control : getListOfStoreProduct resultList Size:" + storeProductTemp.size());

      return storeProductTemp;
       }
       
      catch( Exception e){
           System.err.println("SessionBean.IM.StoreProductControl: getListOfStoreProduct(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
            return null;
      }
      
   
    }
    
    
    @Override
    public List<ProductEntity> getListOfProductNotInStore(Long storeId){
        try{
        StoreEntity store = em.find(StoreEntity.class,storeId);
        Query q = em.createQuery("Select pd From ProductEntity pd where pd.deleteFlag = :flag");
        q.setParameter("flag", false);
        
        List<ProductEntity> globalProductList = new ArrayList<ProductEntity>();
        for(Object o: q.getResultList()){
            ProductEntity p = (ProductEntity) o;
            globalProductList.add(p);
  
        }
        System.out.println("session bean invenrtory control: size1()" + globalProductList.size());
        
        List<ProductEntity> globalProductNotInStore = new ArrayList<ProductEntity>();
        
            Boolean isRepeat = false;
            List<StoreProductEntity> storeProductList = store.getStoreProducts();
            for(ProductEntity aProduct: globalProductList){
                   for(StoreProductEntity aStoreProduct: storeProductList ){
                       if(aStoreProduct.isDeleteFlag() == false  &&  aProduct.equals(aStoreProduct.getProduct())){
                          isRepeat = true;
                          System.out.println("aStoreProduct:" + aStoreProduct.getProduct().getProductId() + "=aProduct:" + aProduct.getProductId());
                       }
                      
                   }
                   System.out.println("2");
                   System.out.println(isRepeat);
                   if(!isRepeat){
                     System.out.println("3");  
                        globalProductNotInStore.add(aProduct);
                        
                   }
                   System.out.println("4");  
                   isRepeat = false;
                   
              }
            
           System.out.println("session bean invenrtory control: size2()" +  globalProductNotInStore.size());

         
          return globalProductNotInStore;  
        }
        catch (Exception e){
            System.err.println("SessionBean.IM.StoreProductControl: getListOfProductNotInStore(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
            return null;
        }
    }
 
    
    @Override
    public int addNewStoreProduct(Long storeId,Long productId, Long factoryProductId, Boolean isSelfPicked, String storeRemark, Double minimumInv, Double onAirInventory){
        
        try{
        StoreEntity store = em.find(StoreEntity.class,storeId);
        ProductEntity product = em.find(ProductEntity.class, productId);
        FactoryProductEntity mapFactoryProduct = em.find(FactoryProductEntity.class, factoryProductId);
        List<StoreProductEntity> currentStoreProductList = store.getStoreProducts();

        StoreProductEntity newStoreProduct = new StoreProductEntity(mapFactoryProduct, store,isSelfPicked, storeRemark,product);

        em.persist(newStoreProduct);
        newStoreProduct.setMinimumInventory(minimumInv);
        newStoreProduct.setWarningOnAirInv(onAirInventory);

//        em.persist(newStoreProduct);
        StoreItemMappingEntity sime = new StoreItemMappingEntity();
        sime.setProductId(productId);
        sime.setRetailProductId(null);
        sime.setStore(store);
        newStoreProduct.setStoreRemark(storeRemark);
        newStoreProduct.setProduct(product);
        
        em.persist(sime);
        
        currentStoreProductList.add(newStoreProduct);
        mapFactoryProduct.getStoreProducts().add(newStoreProduct);
        em.flush();
        return 1;
        }
        catch (Exception e){
            System.err.println("SessionBean.IM.StoreProductControl: addNewStoreProduct(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
        return -1;
        }
        
    }
 
    
    
    //has not check whether the inventory > 0 , 
    //has not set Store Mapping Item 
    @Override
    public int deleteStoreProduct(Long storeId, Long storeProductId, Long factoryProductId){
        
        try{
              StoreEntity store = em.find(StoreEntity.class,storeId);
              StoreProductEntity storeProduct = em.find(StoreProductEntity.class,storeProductId);
              storeProduct.setDeleteFlag(true);
              FactoryProductEntity factoryProduct = em.find(FactoryProductEntity.class,factoryProductId);
              for(StoreProductEntity sp: factoryProduct.getStoreProducts()){
                  StoreProductEntity p = sp;
                  if(p.equals(storeProduct)){
                      factoryProduct.getStoreProducts().remove(p);
                      break;
                  }
              }
              em.flush();
              
              return 1;

        }catch (Exception e){
            System.err.println("SessionBean.IM.StoreProductControl: deleteStoreProduct(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
          return -1;
            
        }
        
        
    }

    
    @Override
    public void editStoreProduct(Long storeId, Long storeProductId, Long oldFactoryProductId, Boolean isSelfPicked, Long newFactoryProductId, Double minimumInventory, String storeRemark){
        try{
        StoreEntity store = em.find(StoreEntity.class, storeId);
        StoreProductEntity storeProduct = em.find(StoreProductEntity.class, storeProductId);
        FactoryProductEntity oldFactoryProduct = em.find(FactoryProductEntity.class,oldFactoryProductId);
        FactoryProductEntity newfactoryProduct = em.find(FactoryProductEntity.class,newFactoryProductId);
        if(newFactoryProductId != oldFactoryProductId ){
        for(StoreProductEntity sp: oldFactoryProduct.getStoreProducts()){
                  StoreProductEntity p = sp;
                  if(p.equals(storeProduct)){
                      oldFactoryProduct.getStoreProducts().remove(p);
                      break;
                  }
              }
        storeProduct.setFactoryProduct(newfactoryProduct);
        newfactoryProduct.getStoreProducts().add(storeProduct);
        
        }
        storeProduct.setSelfPick(isSelfPicked);
        storeProduct.setMinimumInventory(minimumInventory);
        storeProduct.setStoreRemark(storeRemark);
        
        em.flush();
        
        }
        catch (Exception e){
            
           System.err.println("SessionBean.IM.StoreProductControl: editStoreProduct(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
           
        }
        
    }
    
    
    @Override
    public List<FactoryProductEntity> listAvailableFactoryProduct(Long productId){
        try{
           
            Query q = em.createQuery("Select fp From FactoryProductEntity fp where fp.product.productId = :pId and fp.deleteFlag = :flag");
            List<FactoryProductEntity> availableProduct = new ArrayList<FactoryProductEntity>();
            q.setParameter("pId", productId);
            q.setParameter("flag", false);
            for(Object o: q.getResultList()){
                FactoryProductEntity fp = (FactoryProductEntity) o;
                availableProduct.add(fp);
            }
            return availableProduct;
        }catch (Exception e){
            System.err.println("SessionBean.IM.StoreProductControl: listAvailableFactory(): Failed. Caught an unexpected exception.");
            e.printStackTrace();  
            return null;
        }
        
    }
    
    
    @Override
    public List<StoreRetailProductEntity> getListOfStoreRetailProduct(Long storeId){
        try{
       StoreEntity store = em.find(StoreEntity.class,storeId);
       System.out.println("Session Bean: Store inv Control : getListOfStoreRetailProduct :" + storeId);

      List<StoreRetailProductEntity> storeRetailProductTemp = new ArrayList<StoreRetailProductEntity>();
      
      List<StoreRetailProductEntity>  storeRetailProductList = store.getStoreRetailProducts();
      for(StoreRetailProductEntity aRProduct:storeRetailProductList){
       if(!aRProduct.isDeleteFlag()){
        storeRetailProductTemp.add(aRProduct);
       }
      }  
      
     System.out.println("Session Bean: Store inv Control : getListOfStoreRetailProduct resultList Size:" + storeRetailProductTemp.size());

     
      return storeRetailProductTemp;
       }
       
      catch( Exception e){
           System.err.println("SessionBean.IM.StoreProductControl: getListOfStoreRetailProduct(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
            return null;
      }
    }
    
    @Override
    public List<RetailProductEntity> getListOfRetailProductNotInStore(Long storeId){
            try{
        StoreEntity store = em.find(StoreEntity.class,storeId);
        Query q = em.createQuery("Select rpd From RetailProductEntity rpd where rpd.deleteFlag = :flag");
        q.setParameter("flag", false);
        List<RetailProductEntity> globalRetailProductList = new ArrayList<RetailProductEntity>();
        for(Object o: q.getResultList()){
            RetailProductEntity p = (RetailProductEntity) o;
            globalRetailProductList.add(p);
  
        }
        
        
        List<RetailProductEntity> globalRetailProductNotInStore = new ArrayList<RetailProductEntity>();
        
            Boolean isRepeat = false;
            List<StoreRetailProductEntity> storeRetailProductList = store.getStoreRetailProducts();
            for(RetailProductEntity aRProduct: globalRetailProductList){
                   for(StoreRetailProductEntity aStoreRetailProduct: storeRetailProductList ){
                       if(aStoreRetailProduct.isDeleteFlag() == false  &&  aRProduct.equals(aStoreRetailProduct.getRetailProduct())){
                          isRepeat = true;
                          System.out.println("id:" + aRProduct.getRetailProductId());
                          
                       }
                   }
                   System.out.println("1");
                   System.out.println(isRepeat);
                   if(!isRepeat){
                     
                        globalRetailProductNotInStore.add(aRProduct);
                        
                   }
                   
                   isRepeat = false;
              }
         
          return globalRetailProductNotInStore;  
        }
        catch (Exception e){
            System.err.println("SessionBean.IM.StoreProductControl: getListOfRetailProductNotInStore(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public int addNewRetailProduct(Long storeId, Long storeRetailProductId, Long factoryRetailProductId,Double minInv , Double onAir, String storeRemark){
         try{
        StoreEntity store = em.find(StoreEntity.class,storeId);
        RetailProductEntity product = em.find(RetailProductEntity.class, storeRetailProductId);
        FactoryRetailProductEntity mapFactoryRetailProduct = em.find(FactoryRetailProductEntity.class, factoryRetailProductId);
        List<StoreRetailProductEntity> currentStoreRetailProductList = store.getStoreRetailProducts();
        StoreRetailProductEntity newStoreRetailProduct = new StoreRetailProductEntity(mapFactoryRetailProduct,store,storeRemark);
        em.persist(newStoreRetailProduct);
        StoreItemMappingEntity sime = new StoreItemMappingEntity();
        sime.setProductId(null);
        sime.setRetailProductId(newStoreRetailProduct.getStoreRetailProductId());
        sime.setStore(store);
        em.persist(sime);
        newStoreRetailProduct.setStoreRemark(storeRemark);
        newStoreRetailProduct.setOnairInventory(onAir);
        newStoreRetailProduct.setMinimumInventory(minInv);
        newStoreRetailProduct.setRetailProduct(product);
        currentStoreRetailProductList.add(newStoreRetailProduct);
        mapFactoryRetailProduct.getStoreRetailProducts().add(newStoreRetailProduct);
        em.flush();
        return 1;
        }
        catch (Exception e){
            System.err.println("SessionBean.IM.StoreProductControl: addNewStoreRetailProduct(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
        return -1;
        }
    }
  
    @Override
    public int deleteStoreRetailProduct(Long storeId,Long storeRetailProductId, Long FactoryRetailProductId){
        try{
              StoreEntity store = em.find(StoreEntity.class,storeId);
              StoreRetailProductEntity storeRetailProduct = em.find(StoreRetailProductEntity.class,storeRetailProductId);
              storeRetailProduct.setDeleteFlag(true);
              FactoryRetailProductEntity factoryRetailProduct = em.find(FactoryRetailProductEntity.class,FactoryRetailProductId);
              for(StoreRetailProductEntity srp: factoryRetailProduct.getStoreRetailProducts()){
                  StoreRetailProductEntity rp = srp;
                  if(rp.equals(storeRetailProduct)){
                      factoryRetailProduct.getStoreRetailProducts().remove(rp);
                      break;
                  }
              }
              em.flush();
              return 1;

        }catch (Exception e){
            System.err.println("SessionBean.IM.StoreProductControl: deleteStoreProduct(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
          return -1;
            
        }
    }
    
    @Override
    public void editStoreRetailProduct(Long storeId, Long storeRetailProductId, Long oldFactoryRetailProductId, Long newFactoryRetailProductId, Double minimumInventory, String storeRemark){
         try{
             
             System.out.println("editStoreRetailProduct Session Bean: new edit " + minimumInventory);
        StoreEntity store = em.find(StoreEntity.class, storeId);
        StoreRetailProductEntity storeRetailProduct = em.find(StoreRetailProductEntity.class, storeRetailProductId);
        FactoryRetailProductEntity oldFactoryRetailProduct = em.find(FactoryRetailProductEntity.class,oldFactoryRetailProductId);
        FactoryRetailProductEntity newfactoryRetailProduct = em.find(FactoryRetailProductEntity.class,newFactoryRetailProductId);
        if(newFactoryRetailProductId != oldFactoryRetailProductId ){
        for(StoreRetailProductEntity srp: oldFactoryRetailProduct.getStoreRetailProducts()){
                  StoreRetailProductEntity rp = srp;
                  if(rp.equals(storeRetailProduct)){
                      oldFactoryRetailProduct.getStoreRetailProducts().remove(rp);
                      break;
                  }
              }
        storeRetailProduct.setFactoryRetailProduct(newfactoryRetailProduct);
        newfactoryRetailProduct.getStoreRetailProducts().add(storeRetailProduct);
        
        }
        
        storeRetailProduct.setMinimumInventory(minimumInventory);
        storeRetailProduct.setStoreRemark(storeRemark);
        
        em.flush();
        System.out.println("editStoreRetailProduct Session Bean:saved");
        
        }
        catch (Exception e){
            
           System.err.println("SessionBean.IM.StoreProductControl: editStoreProduct(): Failed. Caught an unexpected exception.");
            e.printStackTrace();
           
        }
    }

    @Override
    public List<FactoryRetailProductEntity> listAvailableFactoryRetail(Long rproductId){
        try{
           
            Query q = em.createQuery("Select frp From FactoryRetailProductEntity frp where frp.retailProduct.retailProductId = :rpId and frp.isDeleted = :flag");
            List<FactoryRetailProductEntity> availableRProduct = new ArrayList<FactoryRetailProductEntity>();
            q.setParameter("rpId", rproductId);
            q.setParameter("flag", false);
            for(Object o: q.getResultList()){
                FactoryRetailProductEntity frp = (FactoryRetailProductEntity) o;
                availableRProduct.add(frp);
            }
            return availableRProduct;
        }catch (Exception e){
            System.err.println("SessionBean.IM.StoreProductControl: listAvailableFactory(): Failed. Caught an unexpected exception.");
            e.printStackTrace();  
            return null;
        }
    }
    
    
    
    @Override
    public List<StoreRetailProductEntity> getHaveStockRP(Long storeId){
        List<StoreRetailProductEntity> rplist = getListOfStoreRetailProduct(storeId);
        List<StoreRetailProductEntity> resultList = new ArrayList<>();
        for(StoreRetailProductEntity srp: rplist){
            if(srp.getUnrestrictedInventory()> 0 || srp.getReturnedInventory() >0){
                System.out.println("SRP + " + srp.getStoreRetailProductId());
                resultList.add(srp);
            }
            
        }
        return resultList;
        
    }
     
    @Override
    public List<StoreProductEntity> getHaveStockP(Long storeId){
        List<StoreProductEntity> rplist = getListOfStoreProduct(storeId);
        List<StoreProductEntity> resultList = new ArrayList<>();
        for(StoreProductEntity srp: rplist){
            if(srp.getUnrestrictedInventory()> 0 || srp.getReturnedInventory() >0){
                System.out.println("SP + " + srp.getStoreProductId());
                resultList.add(srp);
            }
            
        }
        return resultList;
        
    }
    
    
    
    @Override
    public List<StoreBinProductEntity> getProductStorageInformation(Long productId){
       try{
           StoreProductEntity sp = em.find(StoreProductEntity.class, productId);
       
        Long storeId = sp.getStore().getStoreId();
        System.out.println("SessionBean:StoreInventoryControl:getProductStorageInformation() StoreId " + storeId);
        List<StoreBinProductEntity> resultList  = new ArrayList<>();
        Query q = em.createQuery("Select sb From StoreBinProductEntity sb Where sb.product.storeProductId = :spId And sb.isDeleted = false And sb.quantity > 0");
        q.setParameter("spId", productId);
        
        for(Object o: q.getResultList()){
            StoreBinProductEntity sbpe = (StoreBinProductEntity) o;
               resultList.add(sbpe);   
        }
        
        System.out.println("SessionBean:StoreInventoryControl:getProductStorageInformation() listOfBin Size" + resultList.size());
        return resultList;
       }
       
       catch (Exception e){
           System.err.println("SessionBean.IM.StoreProductControl: getProductStorageInformation(): Failed. Caught an unexpected exception.");
            e.printStackTrace();  
            return null;
           
    }
        

    }
    
    @Override
        public List<StoreBinRetailProductEntity> getRProductStorageInformation(Long productId){
       try{
           StoreRetailProductEntity sp = em.find(StoreRetailProductEntity.class, productId);
       
        Long storeId = sp.getStore().getStoreId();
        System.out.println("SessionBean:StoreInventoryControl:getRProductStorageInformation() StoreId " + storeId);
        List<StoreBinRetailProductEntity> resultList  = new ArrayList<>();
        Query q = em.createQuery("Select sb From StoreBinRetailProductEntity sb Where sb.retailProduct.storeRetailProductId = :spId And sb.isDeleted = false And sb.quantity > 0");
        q.setParameter("spId", productId);
        
        for(Object o: q.getResultList()){
            StoreBinRetailProductEntity sbpe = (StoreBinRetailProductEntity) o;
               resultList.add(sbpe);   
        }
        
        System.out.println("SessionBean:StoreInventoryControl:getRProductStorageInformation() listOfBin Size" + resultList.size());
        return resultList;
       }
       
       catch (Exception e){
           System.err.println("SessionBean.IM.StoreProductControl: getRProductStorageInformation(): Failed. Caught an unexpected exception.");
            e.printStackTrace();  
            return null;
           
    }
       
        }     
       
    @Override
    public List<StoreBinProductEntity> getReturnedProduct(Long storeId){
        
        Query q = em.createQuery("Select sb From StoreBinProductEntity sb Where sb.status = 1 and sb.swe.store.storeId = :sId");
        q.setParameter("sId", storeId);
        List<StoreBinProductEntity>  resultList = new ArrayList<>();
        for(Object o : q.getResultList()){
            
            StoreBinProductEntity sbpe = (StoreBinProductEntity) o;
            resultList.add(sbpe);
        }
        return resultList;
   
        
    }
          
    
        public List<StoreBinRetailProductEntity> getReturnedRProduct(Long storeId){
        
        Query q = em.createQuery("Select sb From StoreBinRetailProductEntity sb Where sb.status = 1 and sb.swe.store.storeId = :sId");
        q.setParameter("sId", storeId);
        List<StoreBinRetailProductEntity>  resultList = new ArrayList<>();
        for(Object o : q.getResultList()){
            
            StoreBinRetailProductEntity sbpe = (StoreBinRetailProductEntity) o;
            resultList.add(sbpe);
        }
        return resultList;
   
        
    }
       
  
    
}
