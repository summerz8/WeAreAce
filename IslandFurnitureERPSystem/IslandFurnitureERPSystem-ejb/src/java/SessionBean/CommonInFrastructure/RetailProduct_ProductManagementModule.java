/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.Factory.BOMEntity;
import Entity.Factory.ProductEntity;
import Entity.Factory.RetailProductEntity;
import Entity.Factory.SetEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dan
 */
@Stateless
public class RetailProduct_ProductManagementModule implements RetailProduct_ProductManagementModuleLocal {

    @PersistenceContext
    private EntityManager em;

    public RetailProduct_ProductManagementModule() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void AddProduct(String name, String description, Double price, Double memberPrice, String unit) {
        System.out.println("RetailProduct_ProductManagementModule: AddProduct(): ");
        ProductEntity pe = new ProductEntity(name, description, price, memberPrice, unit, Boolean.FALSE);
        em.persist(pe);
        em.flush();
    }

    @Override
    public Boolean DeleteProduct(Long productId) {
        System.out.println("RetailProduct_ProductManagementModule: DeleteProduct(): " + productId);
        ProductEntity pe = em.find(ProductEntity.class, productId);
        if (pe.getFactoryProducts().isEmpty() && pe.getStoreProducts().isEmpty()) {
            List<BOMEntity> listofBom = pe.getBom();
            for (BOMEntity b : listofBom) {
                b.setIsDeleted(Boolean.TRUE);
            }
            pe.setDeleteFlag(Boolean.TRUE);
            em.persist(pe);
            em.flush();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void ModifyProduct(Long productId, String name, String description,
            Double price, Double memberPrice, String unit) {
        System.out.println("RetailProduct_ProductManagementModule: ModifyProduct(): " + productId + name);
        ProductEntity pe = em.find(ProductEntity.class, productId);
        pe.setName(name);
        pe.setDescription(description);
        pe.setPrice(price);
        pe.setUnit(unit);

        em.persist(pe);
        em.flush();

    }

    @Override
    public List<ProductEntity> ListProduct() {
        System.out.println("RetailProduct_ProductManagementModule: ListProduct(): ");
        Query q = em.createQuery("SELECT t FROM ProductEntity t");
        List requiredProductList = new ArrayList();
        for (Object o : q.getResultList()) {
            ProductEntity u = (ProductEntity) o;
            if (!u.isDeleteFlag()) {
                requiredProductList.add(u);
            }
        }
        return requiredProductList;
    }

    //public void SearchProduct(){}
    @Override
    public void AddRetailProduct(String name, String description, Double price,
            String unit) {
        System.out.println("RetailProduct_ProductManagementModule: AddRetailProduct(): ");
        RetailProductEntity pe = new RetailProductEntity(name, description, price, unit, Boolean.FALSE);

        em.persist(pe);
        em.flush();

    }

    @Override
    public Boolean DeleteRetailProduct(Long retailProductId) {
        System.out.println("RetailProduct_ProductManagementModule: DeleteRetialProduct(): ");
        RetailProductEntity pe = em.find(RetailProductEntity.class, retailProductId);
        if (pe.getFactoryRetailProducts().isEmpty() && pe.getStoreRetailProducts().isEmpty()) {
            pe.setDeleteFlag(Boolean.TRUE);

            em.persist(pe);
            em.flush();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void ModifyRetailProduct(Long retailProductId, String name, String unit,
            Double price, String description) {
        System.out.println("RetailProduct_ProductManagementModule: ModifyRetailProduct(): ");
        RetailProductEntity pe = em.find(RetailProductEntity.class, retailProductId);
        pe.setName(name);
        pe.setDescription(description);
        pe.setUnit(unit);
        pe.setPrice(price);

        em.persist(pe);
        em.flush();

    }

    @Override
    public List<RetailProductEntity> ListRetailProduct() {
        System.out.println("RetailProduct_ProductManagementModule: ListRetailProduct(): ");
        Query q = em.createQuery("SELECT t FROM RetailProductEntity t");
        List requiredRetailProductList = new ArrayList();
        for (Object o : q.getResultList()) {
            RetailProductEntity u = (RetailProductEntity) o;
            if (!u.isDeleteFlag()) {
                requiredRetailProductList.add(u);
            }
        }
        return requiredRetailProductList;
    }

    @Override
    public List<SetEntity> listSetList() {
        System.out.println("RetailProduct_ProductManagementModule: ListRetailProduct(): ");
        Query q = em.createQuery("SELECT t FROM SetEntity t");
        List requiredSetList = new ArrayList();
        for (Object o : q.getResultList()) {
            SetEntity u = (SetEntity) o;
            if (!u.getDeleteFlag()) {
                requiredSetList.add(u);
            }
        }
        return requiredSetList;
    }

    @Override
    public void ModifySet(Long setId, String name, String description, Double price, Double memberPrice) {
        System.out.println("RetailProduct_ProductManagementModule: ModifySet(): ");
        SetEntity set = em.find(SetEntity.class, setId);
        set.setName(name);
        set.setDescription(description);
        set.setPrice(price);
        set.setMemberPrice(memberPrice);

        em.persist(set);
        em.flush();
    }

    @Override
    public Boolean DeleteSet(Long setId) {
        System.out.println("RetailProduct_ProductManagementModule: DeleteSet(): ");
        SetEntity set = em.find(SetEntity.class, setId);
        if (set.getStoreSetList().isEmpty() && set.getWebSetList().isEmpty()) {
            set.setDeleteFlag(Boolean.TRUE);

            em.persist(set);
            em.flush();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SetEntity createSet(String setName, String description) {
        SetEntity set = new SetEntity();
        set.setName(setName);
        set.setDescription(description);
        em.persist(set);
        em.flush();
        return set;
    }

    @Override
    public SetEntity getSet(Long setId) {
        return em.find(SetEntity.class, setId);
    }

 

    @Override
    public void addItem(Long setId, Long productId) {
        SetEntity set = em.find(SetEntity.class, setId);
        ProductEntity product = em.find(ProductEntity.class, productId);
        set.getProductList().add(product);
        em.flush();
    }

    @Override
    public void deleteSetProduct(Long setId, Long productId) {
        SetEntity set = em.find(SetEntity.class, setId);
        List<ProductEntity> productList=set.getProductList();
        int size=productList.size();
        for(int a=0;a<size;a++){
            if(productList.get(a).getProductId().equals(productId)){
                productList.remove(a);
                break;
            }
        }
    }

}
