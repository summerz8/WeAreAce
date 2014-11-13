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
public class Factory_StoreManagementModule implements Factory_StoreManagementModuleLocal, Factory_StoreManagementModuleRemote {

    @PersistenceContext
    private EntityManager em;

//    @EJB
//    private RetailProduct_ProductManagementModuleLocal rpmml;
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
    public void DeleteFactory(long factoryId) throws Exception {
        System.out.println("Factory_StoreManagementModule: DeleteFactory(): ");
        FactoryEntity fe = em.find(FactoryEntity.class, factoryId);
        if (fe == null) {
            throw new Exception("Factory is not found!");
        } else {
            fe.setDeleteFlag(Boolean.TRUE);
            em.persist(fe);
            em.flush();
        }

    }

    @Override
    public void ModifyFactory(long factoryId, String country, String address,
            String contact, String manager) throws Exception {
        System.out.println("Factory_StoreManagementModule: ModifyFactory(): ");
        FactoryEntity fe = em.find(FactoryEntity.class, factoryId);
        if (fe == null) {
            throw new Exception("Factory is not found!");
        } else {
            fe.setAddress(address);
            fe.setCountry(country);
            fe.setContact(contact);
            fe.setManagerId(manager);
            em.persist(fe);
            em.flush();
        }

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

    @Override
    public FactoryEntity getFactory(Long factoryId) {
        System.out.println("Factory_StoreManagementModule: getFactory(): ");
        FactoryEntity fe = em.find(FactoryEntity.class, factoryId);
        return fe;
    }
    
    @Override
    public StoreEntity getStore(Long storeId) {
        System.out.println("Factory_StoreManagementModule: getStore(): ");
        StoreEntity fe = em.find(StoreEntity.class, storeId);
        return fe;
    }

    @Override
    public void AddStore(String country, String address, String contact, String manager) {
        System.out.println("Factory_StoreManagementModule: AddStore(): ");
        StoreEntity se = new StoreEntity(address, country, contact, manager, false);
        em.persist(se);
        em.flush();
        System.out.println("Factory_StoreManagementModule: AddStore(): " + se.getStoreId());

    }

    @Override
    public void DeleteStore(Long storeId) throws Exception {
        System.out.println("Factory_StoreManagementModule: DeleteFactory(): ");
        StoreEntity se = em.find(StoreEntity.class, storeId);
        if (se == null) {
            throw new Exception("Store is not found!");
        } else {
            se.setDeleteFlag(Boolean.TRUE);
            em.persist(se);
            em.flush();
        }
    }

    @Override
    public void ModifyStore(long storeId, String country, String address,
            String contact, String manager) throws Exception {
        System.out.println("Factory_StoreManagementModule: ModifyStore(): ");
        StoreEntity se = em.find(StoreEntity.class, storeId);
        if (se == null) {
            throw new Exception("Store is not found!");
        } else {
            se.setAddress(address);
            se.setCountry(country);
            se.setContact(contact);
            se.setManager(manager);
            em.persist(se);
            em.flush();
        }

    }

    @Override
    public List<StoreEntity> ListStore() {
        System.out.println("Factory_StoreManagementModule: ListStore(): ");
        Query q = em.createQuery("SELECT t FROM StoreEntity t");
        List requiredStoreList = new ArrayList();
        for (Object o : q.getResultList()) {
            StoreEntity u = (StoreEntity) o;
            if (!u.getDeleteFlag()) {
                requiredStoreList.add(u);
            }
            System.out.println("Factory_StoreManagementModule: ListStore(): store added" + u.getStoreId());
        }
        return requiredStoreList;
    }

    @Override
    public Integer addFactoryProduct(Long FactoryId, Long ProductId) {
        Integer status = 0;
        ProductEntity product = em.find(ProductEntity.class, ProductId);
        FactoryEntity fe = em.find(FactoryEntity.class, FactoryId);
        if (fe == null) {
            return status; // message: factory is not found. 
        } else if (product == null) {
            status = 1;
            return status;  //  message : product is not found. 

        } else {

            FactoryProductEntity fpe = new FactoryProductEntity();
            fpe.setFactory(fe);
            fpe.setDeleteFlag(false);
            fpe.setProduct(product);
            fpe.setUnit(product.getUnit());
            em.persist(fpe);
            fe.getFactoryProducts().add(fpe);
            em.flush();
            status = 2;
        }

        return status;
    }

    @Override
    public Collection<ProductEntity> viewProductListNotInFactory(Long factoryId) throws Exception {//test works!!
        System.out.println("viewProductListNotInFactory():");
        Integer flag = 0;
        Collection<ProductEntity> pdList = new ArrayList<>();
        FactoryEntity currentFactory = em.find(FactoryEntity.class, factoryId);
        if(currentFactory == null){
            throw new Exception("Factory is not found!");
        }else{
        Collection<FactoryProductEntity> currentFactoryProductList = currentFactory.getFactoryProducts();
        try {
            Query q = em.createQuery("Select pd from ProductEntity PD");
            for (Object o : q.getResultList()) {
                ProductEntity product = (ProductEntity) o;
                for (FactoryProductEntity fpe : currentFactoryProductList) {
                    if (fpe.getProduct().equals(product) && (!fpe.isDeleteFlag())) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    pdList.add(product);
                }
                flag = 0;

            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
        }
        return pdList;
    }

    @Override
    public Collection<FactoryProductEntity> listFactoryProduct(Long factoryId) throws Exception {
        Collection<FactoryProductEntity> pdList = new ArrayList<>();
        FactoryEntity factory = em.find(FactoryEntity.class, factoryId);
        if (factory != null) {
            Collection<FactoryProductEntity> fpList = factory.getFactoryProducts();
            for (FactoryProductEntity fp : fpList) {
                if (!fp.isDeleteFlag()) {
                    pdList.add(fp);
                }
            }
            return pdList;
        } else {
            throw new Exception("Factory is not found!");
        }
    }

    @Override
    public Integer deleteFatoryProduct(Long factoryProductId) {
        FactoryProductEntity fpe = em.find(FactoryProductEntity.class, factoryProductId);
        Integer message = 0;
        if (fpe.getBlockedInventory() != 0) {
            message = 1;
            System.out.println("block inventory is amount. cant delete");
        } else if (fpe.getReturnedInventory() != 0) {
            message = 1;
            System.out.println("Returned inventory is amount. cant delete");

        } else if (fpe.getUnrestrictedInventory() != 0) {
            message = 1;
            System.out.println("Unrestricted inventory is amount. cant delete");
        } else {

            fpe.setDeleteFlag(true);
            em.flush();
            System.out.println("Factory Product has been set successfully!");
        }

        return message;

    }
}
